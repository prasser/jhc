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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.linearbits.jhc.JHC;
import de.linearbits.jhc.JHCConfiguration;
import de.linearbits.jhc.JHCData;
import de.linearbits.jhc.JHCDataProvider.Orientation;
import de.linearbits.jhc.JHCGradient;
import de.linearbits.jhc.JHCLayout;
import de.linearbits.jhc.JHCScale;

public class Example {

    public static void main(String[] args) {

        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Example");
        shell.setSize(200, 200);
        shell.setLayout(new FillLayout());

        // Define scales
        JHCScale<String> xScale = new JHCScale.String(new String[] { "X1", "X2", "X3" });
        JHCScale<String> yScale = new JHCScale.String(new String[] { "Y1", "Y2", "Y3" });

        // Configure
        JHCLayout layout = new JHCLayout(false, 20, true, 20);
        JHCConfiguration config = new JHCConfiguration("x-label",
                                                       "y-label",
                                                       JHCGradient.GRADIENT_HEAT,
                                                       layout);

        // Create array
        int[][] array = { { 0, 1, 0 }, { 1, 2, 1 }, { 0, 1, 0 } };

        // Create data object
        JHCData data = JHCData.create(array, Orientation.ROW, xScale, yScale);

        // Create JHC widget
        JHC jhc = new JHC(shell, SWT.NONE);
        jhc.setData(data, config);
        
        // Enter event loop
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        display.dispose();
    }
}
