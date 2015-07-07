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

package de.linearbits.jhc;

import org.eclipse.swt.graphics.Image;

/**
 * This class implements a painter for SWT. The only difference to the standard painter is that
 * the legend is disposed properly
 * 
 * @author Fabian Prasser
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */
class PainterSWT<T, U, V> extends Painter<T, U, V> {

    /**
     * Creates a new instance
     * @param canvas
     */
	protected PainterSWT(Canvas<T, U, V> canvas) {
		super(canvas);
	}

	@Override
	protected void disposeLegend(T legend) {

        if (legend != null) {
            if (legend instanceof Image) {
                if (!((Image) legend).isDisposed()) {
                	((Image) legend).dispose();
                }
            }
        }
	}
}
