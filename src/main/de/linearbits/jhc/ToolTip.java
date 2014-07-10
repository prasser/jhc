/*
 * JHC - Java Heatmap Control
 * Copyright (C) 2014 Fabian Prasser
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.linearbits.jhc;

/**
 * This class provides helper methods for displaying tool tips
 * 
 * @author Fabian Prasser
 */
class ToolTip {
    
    /** Maxmimal width of a line in the tool tip*/
    private static final int LINE_WIDTH = 200;
    /** Maxmimal number of lines*/
    private static final int LINES = 10;
    /** Are we on unix*/
    private boolean isUnix = isUnix();
    
    /**
     * Cache entry for tool tip labels
     * @author Fabian Prasser
     */
    private class CacheEntry {
        public final RenderedHeatmap<?> heatmap;
        public final int x;
        public final int y;
        public final String tooltip;
        
        public CacheEntry(RenderedHeatmap<?> heatmap, int x, int y, String tooltip) {
            super();
            this.heatmap = heatmap;
            this.x = x;
            this.y = y;
            this.tooltip = tooltip;
        }

        public boolean matches(RenderedHeatmap<?> heatmap, int x, int y) {
            if (this.heatmap == null) {
                if (heatmap != null) return false;
            } else if (!this.heatmap.equals(heatmap)) return false;
            if (this.x != x) return false;
            if (this.y != y) return false;
            return true;
        }
    }
    
    /** The cached entry*/
    private CacheEntry cache = null;
    
    /**
     * Gets the tool tip.
     * 
     * @param heatmap the heatmap
     * @param painter the painter
     * @param x the x
     * @param y the y
     * @return the tool tip
     */
    public String getToolTip(RenderedHeatmap<?> heatmap, Painter<?, ?, ?> painter, int sx, int sy, boolean html, boolean swt) {
        
        Rectangle heatmapPosition = painter.getHeatmapPosition();
        
        // Tool tip for the heatmap
        if (heatmapPosition != null) {
            int x = sx - heatmapPosition.x;
            int y = heatmapPosition.height - (sy - heatmapPosition.y);
            if (x >= 0 && y >= 0 && x < heatmapPosition.width && y < heatmapPosition.height) {
                
                // Check cache
                if (cache != null && cache.matches(heatmap, x, y)) {
                    return cache.tooltip;
                }
                
                // Build tool tip text
                int dX = (int) ((double) x / (double) heatmapPosition.width * heatmap.getWidth());
                int dY = (int) ((double) y / (double) heatmapPosition.height * heatmap.getHeight());
                StringBuilder b = new StringBuilder();
                if (html) b.append("<html>");
                String xlabel = heatmap.getXLabel(dX);
                b.append("x=").append(swt && isUnix ? xlabel : getTickLabel(xlabel, html));
                
                if (html) b.append("<br>");
                else b.append("\n");
                
                String ylabel = heatmap.getYLabel(dY);
                b.append("y=").append(swt && isUnix ? ylabel : getTickLabel(ylabel, html));
                if (html) b.append("</html>");
                
                // Store and return
                String tooltip = b.toString();
                cache = new CacheEntry(heatmap, x, y, tooltip);
                return tooltip;
            }
        }
        
        // Tool tip for the legend
        Rectangle legendPosition = painter.getLegendPosition();
        if (legendPosition != null) {
            int x = sx - legendPosition.x;
            int y = legendPosition.height - (sy - legendPosition.y);
            if (x >= 0 && y >= 0 && x < legendPosition.width && y < legendPosition.height) {
                double dY = ((double) y / (double) legendPosition.height) * (heatmap.getMax() - heatmap.getMin()) + heatmap.getMin();
                StringBuilder b = new StringBuilder();
                if (html) b.append("<html>");
                b.append(dY);
                if (html) b.append("</html>");
                return b.toString();
            }
        }
        return null;
    }

    /**
     * Returns a (potentially) compressed version of the given label
     * @param label
     * @param html
     * @return
     */
    private String getTickLabel(String label, boolean html) {
        
        if (label == null) return label;
        
        if (label.length()<=LINE_WIDTH) {
            return label;
        }
        
        StringBuilder builder = new StringBuilder();
        
        int offset = 0;
        for (int i=0; i<LINES; i++) {
            if (offset >= label.length()) break;
            
            if (offset != 0) {
                builder.append("...");
            }
            
            int endindex = offset + LINE_WIDTH;
            endindex = endindex<label.length() ? endindex : label.length();
            builder.append(label.substring(offset, endindex));
            if (endindex != label.length()) {
                builder.append("...");
                if (i!=LINES-1){
                    if (html) builder.append("<br>");
                    else builder.append("\n");
                }
            }
            
            offset += LINE_WIDTH;
        }
        
        return builder.toString();
    }
    
    /**
     * Are we on unix
     * @return
     */
    private boolean isUnix() {
        final String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0 );
    }
}
