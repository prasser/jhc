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
package de.linearbits.jhc;

import org.eclipse.swt.graphics.Image;

/**
 * This class implements a painter for SWT. The only difference to the standard painter is that
 * the legend is disposed proberly
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
