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
