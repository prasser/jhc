/* ******************************************************************************
 * Copyright (c) 2014 - 2015 Fabian Prasser.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Fabian Prasser - initial API and implementation
 ******************************************************************************/

package de.linearbits.jhc;

import java.util.Iterator;

import de.linearbits.jhc.JHCHeatmap.Point;

/**
 * This class controls the asynchronous rendering process
 * 
 * @author Fabian Prasser
 * 
 * @param <T> the generic type
 */
abstract class Renderer<T> {

    /** Resize delay */
    public static final int WAKEUP_DELAY    = 100;

    /** Resize delay */
    public static final int RENDERING_DELAY = 500;

    /**
     * A job to be run by the renderer
     * @author Fabian Prasser
     *
     */
    private class Job {
        
        /** Timestamp*/
        public final long timestamp = System.currentTimeMillis();
        /** Data*/
        public final JHCData data;
        /** Config*/
        public final JHCConfiguration config;
        
        /** 
         * Creates a new instance
         * 
         * @param data
         * @param config
         */
        public Job(JHCData data, JHCConfiguration config) {
            this.data = data;
            this.config = config;
        }
    }

    /** The canvas. */
    private Canvas<?, ?, ?> canvas;

    /** The jhc. */
    private _JHC            jhc;

    /** The next job */
    private Job             next = null;

    /**
     * Adds a new job
     * @param data
     * @param config
     */
    private void addJob(JHCData data, JHCConfiguration config){
        synchronized(this) {
            this.next = new Job(data, config);
        }
    }
    
    /**
     * Returns the current job, if any
     * @return
     */
    private Job getJob(){
        synchronized(this) {
            if (this.next != null && System.currentTimeMillis() - this.next.timestamp >= RENDERING_DELAY) {
                Job result = this.next;
                this.next = null;
                return result;
            } else {
                return null;
            }
        }
    }
    
    /**
     * Processes the given job
     * @param job
     */
    private void processJob(Job job){
        
        if (job.data == null) {
            jhc.setHeatmap(RenderedHeatmap.NO_DATA);
            return;
        }

        RenderedLayout layout = job.config.getLayout().getRenderedLayout(job.config, canvas, job.data);

        if (layout.getHeatWidth() == 0 || layout.getHeatHeight() == 0) {
            jhc.setHeatmap(RenderedHeatmap.TOO_SMALL);
            return;
        }

        JHCHeatmap heat = job.data.getHeatmap(layout.getHeatWidth(), layout.getHeatHeight());
        Pixels<T> pixels = getPixels(heat, job.config);
        double max = heat.getMax();
        double min = heat.getMin();

        Iterator<Point> iter = heat.iterator();
        while (iter.hasNext()) {
            Point point = iter.next();
            int color = getColor(job.config, point.getValue(), min, max);
            int x = point.getX();
            int y = point.getY();
            if (x<0 || y<0 || x>=heat.getWidth() || y>=heat.getHeight()) {
                throw new IllegalStateException("Point coordinates ("+x+", "+y+") out of range ("+heat.getWidth()+", "+heat.getHeight()+")");
            }
            pixels.set(point.getX(), point.getY(), color);
        }

        pixels.update();

        RenderedHeatmap<T> rendered = new RenderedHeatmap<T>(pixels.getImage(), heat, job.config);
        jhc.setHeatmap(rendered);
    }

    /**
     * Instantiates a new renderer.
     * 
     * @param jhc the jhc
     * @param canvas the canvas
     */
    protected Renderer(_JHC jhc, Canvas<?, ?, ?> canvas) {
        
        // Init
        this.jhc = jhc;
        this.canvas = canvas;
        
        // Create rendering thread
        Runnable r = new Runnable() {
            public void run(){
                while (true) {
                    try {
                        Thread.sleep(WAKEUP_DELAY);
                    } catch (InterruptedException e) {
                        /** Ignore*/
                    }
                    Job job = getJob();
                    if (job != null) {
                        processJob(job);
                    }
                }
            }
        };
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.start();
    }

    /**
     * Render.
     * 
     * @param data the data
     * @param config the config
     */
    protected void render(JHCData data, JHCConfiguration config) {
        this.addJob(data, config);
    }

    /**
     * Gets the color.
     * 
     * @param config the config
     * @param value the value
     * @param max the max
     * @return the color
     */
    protected int getColor(JHCConfiguration config, double value, double min, double max) {
        int index = (int) Math.round((value - min) / (max - min) * (config.getGradient().getSteps() - 1));
        return config.getGradient().getColor(index);
    }

    /**
     * Gets the pixels.
     * 
     * @param heat the heat
     * @param config the config
     * @return the pixels
     */
    protected abstract Pixels<T> getPixels(JHCHeatmap heat, JHCConfiguration config);
}
