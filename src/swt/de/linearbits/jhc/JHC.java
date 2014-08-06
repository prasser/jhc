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

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * This class implements a heatmap widget for SWT
 * 
 * @author Fabian Prasser
 */
public class JHC {
    
    /**
     * Checks the style
     * 
     * @param style
     */
    private static int checkStyle(int style) {
        return style;
    }

    /** The real jhc*/
    private _JHC jhc;
    
    /**
     * Creates a new instance
     * 
     * @param parent the parent
     * @param style the style
     */
    public JHC(Composite parent, int style) {
        this.jhc = new _JHC(parent, checkStyle(style));
    }

    /**
     * Returns the background color
     * @return
     */
    public Color getBackground() {
        return jhc.getBackground();
    }

    /**
     * Returns the font
     * @return
     */
    public Font getFont() {
        return jhc.getFont();
    }

    /**
     * Sets the background color
     * @param arg0
     */
    public void setBackground(Color arg0) {
        jhc.setBackground(arg0);
    }

    /**
     * Sets the data to be displayed by this widget
     * @param data
     * @param config
     */
    public void setData(JHCData data, JHCConfiguration config) {
        jhc.setData(data, config);
    }

    /**
     * Sets the font
     * @param arg0
     */
    public void setFont(Font arg0) {
        jhc.setFont(arg0);
    }

    /**
     * Sets the layout data
     * @param arg0
     */
    public void setLayoutData(Object arg0) {
        jhc.setLayoutData(arg0);
    }

    /**
     * Returns this instance's display
     * @return
     */
    public Device getDisplay() {
        return jhc.getDisplay();
    }

    /**
     * Returns the underlying control
     * @return
     */
    public Control getControl() {
        return jhc;
    }
}
