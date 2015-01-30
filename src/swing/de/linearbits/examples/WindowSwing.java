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
