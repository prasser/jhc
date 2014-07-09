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

import javax.swing.JFrame;

import de.linearbits.jhc.JHC;
import de.linearbits.jhc.JHCConfiguration;
import de.linearbits.jhc.JHCData;

/**
 * This class implements a window for Swing
 * 
 * @author Fabian Prasser
 */
class WindowSwing extends JFrame implements Window<Font, Color> {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6498074114197249215L;

    /** The panel. */
    private JHC               panel;

    /**
     * Instantiates a new window swing.
     */
    protected WindowSwing() {

        this.setTitle("Swing");
        this.setSize(200, 200);
        this.setLayout(new BorderLayout());
        this.panel = new JHC();
        this.add(panel, BorderLayout.CENTER);
        this.panel.setFont(this.panel.getFont().deriveFont(12f));
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
    }
}
