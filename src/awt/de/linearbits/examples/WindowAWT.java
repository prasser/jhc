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

package de.linearbits.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import de.linearbits.examples.Window;
import de.linearbits.jhc.JHC;
import de.linearbits.jhc.JHCConfiguration;
import de.linearbits.jhc.JHCData;

/**
 * This class implements a window for AWT
 * 
 * @author Fabian Prasser
 */
class WindowAWT extends Frame implements Window<Font, Color> {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -24480552155549293L;

    /** The panel. */
    private JHC               panel;

    /**
     * Instantiates a new window awt.
     */
    protected WindowAWT() {

        this.setTitle("AWT");
        this.setSize(200, 200);
        this.setLayout(new BorderLayout());
        this.panel = new JHC();
        this.add(panel, BorderLayout.CENTER);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                WindowAWT.this.dispose();
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.linearbits.jhc.examples.Window#open(de.linearbits.jhc.Data, de.linearbits.jhc.Configuration)
     */
    @Override
    public void open(JHCData data, JHCConfiguration config) {
        panel.setData(data, config);
        this.setVisible(true);
        this.panel.setFont(this.panel.getFont().deriveFont(12f));
    }
}
