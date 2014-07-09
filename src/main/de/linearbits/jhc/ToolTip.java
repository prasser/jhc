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

/**
 * This class provides helper methods for displaying tooltips
 * 
 * @author Fabian Prasser
 */
class ToolTip {

    /**
     * Gets the tool tip.
     * 
     * @param heatmap the heatmap
     * @param painter the painter
     * @param x the x
     * @param y the y
     * @return the tool tip
     */
    public static String getToolTip(RenderedHeatmap<?> heatmap, Painter<?, ?, ?> painter, int sx, int sy, boolean html) {
        Rectangle heatmapPosition = painter.getHeatmapPosition();
        if (heatmapPosition != null) {
            int x = sx - heatmapPosition.x;
            int y = heatmapPosition.height - (sy - heatmapPosition.y);
            if (x >= 0 && y >= 0 && x < heatmapPosition.width && y < heatmapPosition.height) {
                int dX = (int) ((double) x / (double) heatmapPosition.width * heatmap.getWidth());
                int dY = (int) ((double) y / (double) heatmapPosition.height * heatmap.getHeight());
                StringBuilder b = new StringBuilder();
                if (html) b.append("<html>");
                b.append("x=").append(heatmap.getXLabel(dX));
                if (html) b.append("<br>");
                else b.append("\n");
                b.append("y=").append(heatmap.getYLabel(dY));
                if (html) b.append("</html>");
                return b.toString();
            }
        }
        Rectangle legendPosition = painter.getLegendPosition();
        if (legendPosition != null) {
            int x = sx - legendPosition.x;
            int y = legendPosition.height - (sy - legendPosition.y);
            if (x >= 0 && y >= 0 && x < legendPosition.width && y < legendPosition.height) {
                double dY = ((double) y / (double) legendPosition.height) * (heatmap.getMax() - heatmap.getMin()) + heatmap.getMin();
                StringBuilder b = new StringBuilder();
                if (html) b.append("<html>");
                b.append(dY);
                if (html) b.append("</html>");
                return b.toString();
            }
        }
        return null;
    }
}
