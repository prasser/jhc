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

import de.linearbits.jhc.JHCConfiguration;
import de.linearbits.jhc.JHCData;

/**
 * This interface describes a basic window used for displaying examples
 * 
 * @author Fabian Prasser
 * 
 * @param <T> the generic type
 * @param <U> the generic type
 */
interface Window<T, U> {

    /**
     * Open.
     * 
     * @param data the data
     * @param config the config
     */
    public void open(JHCData data, JHCConfiguration config);
}
