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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.linearbits.jhc.JHC;
import de.linearbits.jhc.JHCConfiguration;
import de.linearbits.jhc.JHCData;

/**
 * This class implements a window for SWT
 * 
 * @author Fabian Prasser
 */
class WindowSWT implements Window<Font, Color> {

    /** The display. */
    private Display display;

    /** The panel. */
    private JHC     jhc;

    /** The shell. */
    private Shell   shell;

    @Override
    public void open(JHCData data, JHCConfiguration config) {

        this.display = new Display();
        this.shell = new Shell(display);
        shell.setText("SWT");
        shell.setSize(200, 200);

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        shell.setLayout(gridLayout);

        jhc = new JHC(shell, SWT.DOUBLE_BUFFERED);
        GridData gridData = new GridData();
        gridData.grabExcessVerticalSpace = true;
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.FILL;
        jhc.setLayoutData(gridData);
        jhc.setData(data, config);

        // Update some settings
        FontData[] fd = jhc.getFont().getFontData();
        fd[0].setHeight(8);
        jhc.setFont(new Font(Display.getCurrent(), fd[0]));

        // Enter event loop
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        display.dispose();
    }
}
