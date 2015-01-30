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
