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

/**
 * This class implements examples for SWT
 * 
 * @author Fabian Prasser
 */
class ExamplesSWT extends ExampleData {

    /**
     * The main method.
     * 
     * @param args the arguments
     */
    public static void main(String[] args) {

        Examples.example1(new WindowSWT());
        Examples.example2(new WindowSWT());
        Examples.example3(new WindowSWT());
        Examples.example4(new WindowSWT());
        Examples.example5(new WindowSWT());
        Examples.example6(new WindowSWT());
        Examples.example7(new WindowSWT());
    }
}
