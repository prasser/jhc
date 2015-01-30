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

import java.awt.Toolkit;

/**
 * This class implements examples for AWT
 * 
 * @author Fabian Prasser
 */
class ExamplesAWT extends ExampleData {

    /**
     * The main method.
     * 
     * @param args the arguments
     */
    public static void main(String[] args) {

        Toolkit.getDefaultToolkit().setDynamicLayout(true);
        System.setProperty("sun.awt.noerasebackground", "true");
        System.setProperty("sun.awt.erasebackgroundonresize", "false");

        Examples.example1(new WindowAWT());
        Examples.example2(new WindowAWT());
        Examples.example3(new WindowAWT());
        Examples.example4(new WindowAWT());
        Examples.example5(new WindowAWT());
        Examples.example6(new WindowAWT());
        Examples.example7(new WindowAWT());
    }
}
