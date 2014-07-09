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
