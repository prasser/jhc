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

import java.util.Random;

/**
 * This class provides random example data
 * 
 * @author Fabian Prasser
 */
class ExampleData {

    /**
     * Gets the array1.
     * 
     * @return the array1
     */
    protected static int[][] getArray1() {
        int WIDTH = 200;
        int HEIGHT = 200;
        int[][] result = new int[HEIGHT][WIDTH];
        Random random = new Random();
        for (int y = 0; y < result.length; y++) {
            result[y] = new int[WIDTH];
            for (int x = 0; x < WIDTH; x++) {
                result[y][x] = random.nextInt(200);
            }
        }
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                result[y][x] = 1000;
            }
        }
        return result;
    }

    /**
     * Gets the array2.
     * 
     * @return the array2
     */
    protected static int[][] getArray2() {
        int WIDTH = 1000;
        int HEIGHT = 1000;
        int[][] result = new int[HEIGHT][WIDTH];
        for (int y = 0; y < result.length; y++) {
            result[y] = new int[WIDTH];
            for (int x = 0; x < WIDTH; x++) {
                double distance = Math.sqrt(Math.pow(WIDTH / 2 - x, 2) + Math.pow(HEIGHT / 2 - y, 2));
                result[y][x] = (int) distance;
            }
        }
        return result;
    }

    /**
     * Gets the array3.
     * 
     * @return the array3
     */
    protected static int[][] getArray3() {
        int WIDTH = 20;
        int HEIGHT = 20;
        Random random = new Random();
        int[][] result = new int[HEIGHT][WIDTH];
        for (int y = 0; y < result.length; y++) {
            result[y] = new int[WIDTH];
            for (int x = 0; x < WIDTH; x++) {
                result[y][x] = random.nextInt(10000);
            }
        }
        return result;
    }

    /**
     * Gets the array4.
     * 
     * @return the array4
     */
    protected static int[][] getArray4() {
        int WIDTH = 20000;
        int HEIGHT = 20000;
        Random random = new Random();
        int[][] result = new int[HEIGHT][WIDTH];
        for (int y = 0; y < result.length; y++) {
            result[y] = new int[WIDTH];
            for (int x = 0; x < WIDTH; x++) {
                result[y][x] = random.nextInt(10000);
            }
        }
        return result;
    }

    /**
     * Gets the array5.
     * 
     * @return the array5
     */
    protected static int[][] getArray5() {
        int WIDTH = 200;
        int HEIGHT = 20;
        Random random = new Random();
        int[][] result = new int[HEIGHT][WIDTH];
        for (int y = 0; y < result.length; y++) {
            result[y] = new int[WIDTH];
            for (int x = 0; x < WIDTH; x++) {
                result[y][x] = random.nextInt(10000);
            }
        }
        return result;
    }

    /**
     * Gets the array6.
     * 
     * @return the array6
     */
    protected static int[][] getArray6() {
        int WIDTH = 20;
        int HEIGHT = 200;
        Random random = new Random();
        int[][] result = new int[HEIGHT][WIDTH];
        for (int y = 0; y < result.length; y++) {
            result[y] = new int[WIDTH];
            for (int x = 0; x < WIDTH; x++) {
                result[y][x] = random.nextInt(10000);
            }
        }
        return result;
    }

    /**
     * Gets the array7.
     * 
     * @return the array7
     */
    protected static int[][] getArray7() {
        int WIDTH = 4000;
        int HEIGHT = 4000;
        Random random = new Random();
        int[][] result = new int[HEIGHT][WIDTH];
        for (int y = 0; y < result.length; y++) {
            result[y] = new int[WIDTH];
            for (int x = 0; x < WIDTH; x++) {
                result[y][x] = random.nextInt(10000);
            }
        }
        return result;
    }

    /**
     * Gets the array8.
     * 
     * @return the array8
     */
    protected static int[][] getArray8() {
        int WIDTH = 20;
        int HEIGHT = 5;
        Random random = new Random();
        int[][] result = new int[HEIGHT][WIDTH];
        for (int y = 0; y < result.length; y++) {
            result[y] = new int[WIDTH];
            for (int x = 0; x < WIDTH; x++) {
                result[y][x] = random.nextInt(10000);
            }
        }
        return result;
    }

    /**
     * Gets the array9.
     * 
     * @return the array9
     */
    protected static int[][] getArray9() {
        int WIDTH = 5;
        int HEIGHT = 5;
        Random random = new Random();
        int[][] result = new int[HEIGHT][WIDTH];
        for (int y = 0; y < result.length; y++) {
            result[y] = new int[WIDTH];
            for (int x = 0; x < WIDTH; x++) {
                result[y][x] = random.nextInt(10000);
            }
        }
        return result;
    }

    /**
     * Gets the array10.
     * 
     * @return the array10
     */
    protected static int[][] getArray10() {
        int WIDTH = 100;
        int HEIGHT = 100;
        Random random = new Random();
        int[][] result = new int[HEIGHT][WIDTH];
        for (int y = 0; y < result.length; y++) {
            result[y] = new int[WIDTH];
            for (int x = 0; x < WIDTH; x++) {
                result[y][x] = random.nextInt(10000);
            }
        }
        return result;
    }
}
