JHC - Java Heat Map Control
====

This project implements a widget/control that displays [heat maps](https://en.wikipedia.org/wiki/Heat_map). 
JHC is available for all major Java toolkits: 

* Standard Widget Toolkit (SWT)
* Swing
* Abstract Window Toolkit (AWT)

For rendering, the native interfaces of the toolkits are used (avoiding the infamous SWT_AWT bridge). The main feature of JHC is that it is able 
to display very large heat maps efficiently.

[![Screenshot-1](https://raw.github.com/prasser/jhc/master/img/screenshot1.png)](https://raw.github.com/prasser/jhc/master/img/screenshot1.png)

Introduction
------

In JHC heat maps are not represented as two-dimensional arrays but as sets of points. This allows to only sparsely populate the space 
defined by the domains of the attributes involved. Moreover, heat maps are computed on-demand for the current size of the widget, 
respecting a pre-defined maximal size. When scaling a heat map to the current size of the widget, the data itself is scaled down 
instead of a rendered image. Computations are performed in a background thread, allowing time-consuming processes, e.g. 
disk access, during rendering.  

When scaling heat maps down, JHC currently uses the arithmetic mean to aggregate values and dynamically
adjusts the minimum and maximum of the scale to these aggregates. In future releases, it is planned to
make this process configurable (e.g. using static minima and maxima or different aggregate functions).

There are multiple ways in which data can be provided to JHC. Firstly, different variants of the static method JHCData.create(...) 
accept two-dimensional arrays of primitive Java data types. Secondly, the interface JHCDataProvider can be implemented
to access arbitrary two-dimensional data, e.g. from disk (again via JHCData.create(...). Finally, the abstract class
JHCData can itself be extended to implement a custom process for computing heat maps. To this end, the following classes must be 
implemented:

[![Classes](https://raw.github.com/prasser/jhc/master/img/classes.png)](https://raw.github.com/prasser/jhc/master/img/classes.png)

JHC will repeatedly call JHCData.getHeatmap(...) to retrieve an instance of the heat map for a specific 
size. This call will be performed from a background thread. An instance of JHCHeatmap must be returned
that provides data points via an iterator over instances of the interface JHCHeatmap.Point. Each data point
associates a value with an x-coordinate and a y-coordinate. Each coordinate represents one tick on the according
axis. Associated labels must be returned from calls to JHCHeatmap.getXLabel(x) and JHCHeatmap.getYLabel(y), respectively.

Customization
------

JHC currently supports a customizable layout as well as different gradients for the heat map. Gradients are
inspired by [plotly](https://plot.ly/). See [here](http://nbviewer.ipython.org/github/plotly/python-user-guide/blob/master/s5_heatmaps/s5_heatmaps.ipynb#Plotly%27s-pre-defined-color-scales)
for a description of the color scales currently implemented.

The class JHCLayout allows defining the following offsets and lengths: 
* Offsets: from left, right, top, bottom, between heat map and scale (center) and between labels and the plot
* Length: length of small and large ticks
* Rotation: labels for ticks can be rotated and drawn within a given bound 

Compatibility
------
JHC has been tested on all major platforms, MS Windows, Linux and OS X (with and without Retina-Displays), and all major toolkits,
SWT, AWT and Swing.

Example (SWT)
------

```Java
// Define scales
JHCScale<String> xScale = new JHCScale.String(new String[] { "X1", "X2", "X3" });
JHCScale<String> yScale = new JHCScale.String(new String[] { "Y1", "Y2", "Y3" });

// Configure
JHCLayout layout = new JHCLayout(false, 20, true, 20);
JHCConfiguration config = new JHCConfiguration("x-label", "y-label", 
                                               JHCGradient.GRADIENT_HEAT, layout);

// Create array
int[][] array = {{0,1,0}, {1,2,1}, {0,1,0}};

// Create data object
JHCData data = JHCData.create(array, Orientation.ROW, xScale, yScale);

// Create JHC widget
JHC jhc = new JHC(shell, SWT.NONE);
jhc.setData(data, config);
```

The result looks like this:

[![Screenshot-2](https://raw.github.com/prasser/jhc/master/img/screenshot2.png)](https://raw.github.com/prasser/jhc/master/img/screenshot2.png)

Download
------
A binary version for AWT is available for download [here](https://rawgithub.com/prasser/jhc/master/jars/jhc-1.0.0-awt.jar)
([Javadoc](https://rawgithub.com/prasser/jhc/master/jars/jhc-1.0.0-awt-doc.jar)).

A binary version for Swing is available for download [here](https://rawgithub.com/prasser/jhc/master/jars/jhc-1.0.0-swing.jar)
([Javadoc](https://rawgithub.com/prasser/jhc/master/jars/jhc-1.0.0-swing-doc.jar)).

A binary version for SWT is available for download [here](https://rawgithub.com/prasser/jhc/master/jars/jhc-1.0.0-swt.jar)
([Javadoc](https://rawgithub.com/prasser/jhc/master/jars/jhc-1.0.0-swt-doc.jar)).

Documentation
------
Online Javadoc for AWT can be found [here](https://rawgithub.com/prasser/jhc/master/doc/awt/index.html).

Online Javadoc for Swing can be found [here](https://rawgithub.com/prasser/jhc/master/doc/swing/index.html).

Online Javadoc for SWT can be found [here](https://rawgithub.com/prasser/jhc/master/doc/swt/index.html). 