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

import de.linearbits.examples.ExampleData;
import de.linearbits.examples.Examples;

/**
 * This class implements examples for swing
 * 
 * @author Fabian Prasser
 */
class ExamplesSwing extends ExampleData {

    /**
     * The main method.
     * 
     * @param args the arguments
     */
    public static void main(String[] args) {

        Examples.example1(new WindowSwing());
        Examples.example2(new WindowSwing());
        Examples.example3(new WindowSwing());
        Examples.example4(new WindowSwing());
        Examples.example5(new WindowSwing());
        Examples.example6(new WindowSwing());
        Examples.example7(new WindowSwing());
    }
}
