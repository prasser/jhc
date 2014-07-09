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
