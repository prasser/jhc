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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;

/**
 * This class provides a heatmap control for Swing
 * @author Fabian Prasser
 */
public class JHC extends JComponent {

    /** SUID */
    private static final long serialVersionUID = -2953032744074537804L;
    
    /** The real widget*/
    private _JHC jhc = new _JHC();

    /** Creates a new instance*/
    public JHC(){
        this.setLayout(new BorderLayout());
        this.add(jhc, BorderLayout.CENTER);
    }

    /**
     * Updates the data displayed by this control
     * @param data
     * @param config
     */
    public void setData(JHCData data, JHCConfiguration config) {
        jhc.setData(data, config);
    }

    /**
     * Sets the font
     */
    public void setFont(Font font) {
        jhc.setFont(font);
    }

    /**
     * Sets the background color
     */
    public void setBackground(Color bg) {
        jhc.setBackground(bg);
    }
}
