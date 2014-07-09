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

import java.text.DateFormat;
import java.text.DecimalFormat;

/**
 * This class provides an abstract type for scales
 * 
 * @author Fabian Prasser
 * 
 * @param <T> the generic type
 */
public abstract class JHCScale<T> {

    /**
     * Adjust.
     * 
     * @param length the length
     * @return the scale
     */
    protected abstract JHCScale<T> adjust(int length);

    /**
     * Check.
     * 
     * @param length the length
     */
    protected abstract void check(int length);

    /**
     * Gets the label at.
     * 
     * @param index the index
     * @return the label at
     */
    protected abstract java.lang.String getLabelAt(int index);
    
    /**
     * This class implements a data/time scale
     * 
     * @author Fabian Prasser
     */
    public static class Date extends JHCScale<java.util.Date> {

        /** The format. */
        private final DateFormat format;

        /** The from. */
        private final java.util.Date       from;

        /** The length. */
        private final int        length;

        /** The to. */
        private final java.util.Date       to;

        /**
         * Instantiates a new scale date.
         * 
         * @param from the from
         * @param to the to
         */
        public Date(java.util.Date from, java.util.Date to) {
            this(from, to, null);
        }

        /**
         * Instantiates a new scale date.
         * 
         * @param from the from
         * @param to the to
         * @param format the format
         */
        public Date(java.util.Date from, java.util.Date to, DateFormat format) {
            this.from = from;
            this.to = to;
            this.length = 1;
            this.format = format;

            if (from.getTime() > to.getTime()) {
                throw new IllegalArgumentException("Lower bound must be <= upper bound");
            }
        }

        /**
         * Instantiates a new scale date.
         * 
         * @param format the format
         * @param from the from
         * @param to the to
         * @param length the length
         */
        private Date(DateFormat format, java.util.Date from, java.util.Date to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
            this.format = format;
        }

        /*
         * (non-Javadoc)
         * 
         * @see de.linearbits.jhc.Scale#adjust(int)
         */
        @Override
        protected JHCScale<java.util.Date> adjust(int length) {
            return new Date(format, from, to, length);
        }

        /*
         * (non-Javadoc)
         * 
         * @see de.linearbits.jhc.Scale#check(int)
         */
        @Override
        protected void check(int length) {

            long from = this.from.getTime();
            long to = this.to.getTime();

            if (to - from + 1 < length) {
                throw new IllegalArgumentException("Scale does not provide enough ticks for data of given length");
            }
        }

        /**
         * Gets the from.
         * 
         * @return the from
         */
        protected java.util.Date getFrom() {
            return from;
        }

        /*
         * (non-Javadoc)
         * 
         * @see de.linearbits.jhc.Scale#getLabelAt(int)
         */
        @Override
        protected java.lang.String getLabelAt(int index) {
            if (index >= length) {
                throw new IllegalArgumentException("Label index (" + index + ") out of range [0, " + length + "[");
            } else {
                double offset = (double) (to.getTime() - from.getTime()) / (double) length;
                long lower = (long) (from.getTime() + index * offset);
                long upper = (long) (from.getTime() + (index + 1) * offset);
                if (format == null) {
                    return "[" + new java.util.Date(lower) + ", " + new java.util.Date(upper) + "[";
                } else {
                    return "[" + format.format(new java.util.Date(lower)) + ", " + format.format(new java.util.Date(upper)) + "[";
                }
            }
        }

        /**
         * Gets the to.
         * 
         * @return the to
         */
        protected java.util.Date getTo() {
            return to;
        }
    }
    
    /**
     * This class implements a decimal scale
     * 
     * @author Fabian Prasser
     */
    public static class Decimal extends JHCScale<Double> {

        /** The format. */
        private final DecimalFormat format;

        /** The from. */
        private final double        from;

        /** The length. */
        private final int           length;

        /** The to. */
        private final double        to;

        /**
         * Instantiates a new scale decimal.
         * 
         * @param from the from
         * @param to the to
         */
        public Decimal(double from, double to) {
            this(from, to, null);
        }

        /**
         * Instantiates a new scale decimal.
         * 
         * @param from the from
         * @param to the to
         * @param format the format
         */
        public Decimal(double from, double to, DecimalFormat format) {
            this.from = from;
            this.to = to;
            this.length = 1;
            this.format = format;

            if (from > to) {
                throw new IllegalArgumentException("Lower bound must be <= upper bound");
            }
        }

        /**
         * Instantiates a new scale decimal.
         * 
         * @param format the format
         * @param from the from
         * @param to the to
         * @param length the length
         */
        private Decimal(DecimalFormat format, double from, double to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
            this.format = format;
        }

        /*
         * (non-Javadoc)
         * 
         * @see de.linearbits.jhc.Scale#adjust(int)
         */
        @Override
        protected JHCScale<Double> adjust(int length) {
            return new Decimal(format, from, to, length);
        }

        /*
         * (non-Javadoc)
         * 
         * @see de.linearbits.jhc.Scale#check(int)
         */
        @Override
        protected void check(int length) {
            // Nothing to do
        }

        /**
         * Gets the from.
         * 
         * @return the from
         */
        protected double getFrom() {
            return from;
        }

        /*
         * (non-Javadoc)
         * 
         * @see de.linearbits.jhc.Scale#getLabelAt(int)
         */
        @Override
        protected java.lang.String getLabelAt(int index) {
            if (index >= length) {
                throw new IllegalArgumentException("Label index (" + index + ") out of range [0, " + length + "[");
            } else {
                double offset = (to - from) / length;
                int lower = (int) (from + index * offset);
                int upper = (int) (from + (index + 1) * offset);
                if (format == null) {
                    return "[" + lower + ", " + upper + "[";
                } else {
                    return "[" + format.format(lower) + ", " + format.format(upper) + "[";
                }
            }
        }

        /**
         * Gets the to.
         * 
         * @return the to
         */
        protected double getTo() {
            return to;
        }
    }
    
    /**
     * This class implements an integer scale
     * 
     * @author Fabian Prasser
     */
    public static class Integer extends JHCScale<java.lang.Integer> {

        /** The from. */
        private final int from;

        /** The length. */
        private final int length;

        /** The to. */
        private final int to;

        /**
         * Instantiates a new scale integer.
         * 
         * @param from the from
         * @param to the to
         */
        public Integer(int from, int to) {
            this.from = from;
            this.to = to;
            this.length = 1;

            if (from > to) {
                throw new IllegalArgumentException("Lower bound must be <= upper bound");
            }
        }

        /**
         * Instantiates a new scale integer.
         * 
         * @param from the from
         * @param to the to
         * @param length the length
         */
        private Integer(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }

        /*
         * (non-Javadoc)
         * 
         * @see de.linearbits.jhc.Scale#adjust(int)
         */
        @Override
        protected Integer adjust(int length) {
            return new Integer(from, to, length);
        }

        /*
         * (non-Javadoc)
         * 
         * @see de.linearbits.jhc.Scale#check(int)
         */
        @Override
        protected void check(int length) {
            if (to - from + 1 < length) {
                throw new IllegalArgumentException("Scale does not provide enough ticks for data of given length");
            }
        }

        /**
         * Gets the from.
         * 
         * @return the from
         */
        protected int getFrom() {
            return from;
        }

        /*
         * (non-Javadoc)
         * 
         * @see de.linearbits.jhc.Scale#getLabelAt(int)
         */
        @Override
        protected java.lang.String getLabelAt(int index) {
            if (index >= length) {
                throw new IllegalArgumentException("Label index (" + index + ") out of range [0, " + length + "[");
            } else {
                double offset = (double) (to - from) / (double) length;
                int lower = (int) (from + index * offset);
                int upper = (int) (from + (index + 1) * offset);
                return "[" + lower + ", " + upper + "[";
            }
        }

        /**
         * Gets the to.
         * 
         * @return the to
         */
        protected int getTo() {
            return to;
        }
    }
    
    /**
     * This class implements a string scale
     * 
     * @author Fabian Prasser
     */
    public static class String extends JHCScale<java.lang.String> {

        /** The values. */
        private final java.lang.String[] values;

        /**
         * Instantiates a new scale string.
         * 
         * @param values the values
         */
        public String(java.lang.String[] values) {
            this.values = values;
            if (values == null || values.length == 0) {
                throw new IllegalArgumentException("Labels must not be empty");
            }
        }

        /**
         * Instantiates a new scale string.
         * 
         * @param values the values
         * @param length the length
         */
        private String(java.lang.String[] values, int length) {
            this.values = adjust(length, values);
        }

        /*
         * (non-Javadoc)
         * 
         * @see de.linearbits.jhc.Scale#adjust(int)
         */
        @Override
        protected JHCScale<java.lang.String> adjust(int length) {
            return new String(values, length);
        }

        /*
         * (non-Javadoc)
         * 
         * @see de.linearbits.jhc.Scale#check(int)
         */
        @Override
        protected void check(int length) {
            if (length != values.length) {
                throw new IllegalArgumentException("Number of labels must match data length");
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see de.linearbits.jhc.Scale#getLabelAt(int)
         */
        @Override
        protected java.lang.String getLabelAt(int index) {
            if (index >= values.length) {
                throw new IllegalArgumentException("Label index (" + index + ") out of range [0, " + values.length + "[");
            } else {
                return values[index];
            }
        }

        /**
         * Gets the values.
         * 
         * @return the values
         */
        protected java.lang.String[] getValues() {
            return this.values;
        }

        /**
         * Adjust.
         * 
         * @param target the target
         * @param source the source
         * @param index the index
         * @return the int
         */
        protected int adjust(int target, int source, int index) {
            index = (int) Math.round((double) target / (double) source * index);
            return index < target ? index : target - 1;
        }

        /**
         * Adjust.
         * 
         * @param target the target
         * @param source the source
         * @return the string[]
         */
        protected java.lang.String[] adjust(int target, java.lang.String[] source) {

            if (source == null) {
                return null;
            }

            if (target == source.length) {
                return source;
            }

            StringBuilder[] buffers = new StringBuilder[target];

            for (int x = 0; x < target; x++) {
                buffers[x] = new StringBuilder("{");
            }

            for (int x = 0; x < source.length; x++) {
                int index = adjust(target, source.length, x);
                if (buffers[index].length() == 1) {
                    buffers[index].append(source[x]);
                } else {
                    buffers[index].append(", ").append(source[x]);
                }
            }

            java.lang.String[] result = new java.lang.String[target];

            for (int x = 0; x < target; x++) {
                result[x] = buffers[x].append("}").toString();
            }
            return result;
        }
    }
}
