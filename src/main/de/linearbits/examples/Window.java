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
