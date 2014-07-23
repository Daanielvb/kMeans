package kmeans;

import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.ShapeUtilities;

/**
 * A demo showing the use of log axes.
 *
 */
public class XYLogAxesLines extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public XYLogAxesLines(final String title,ArrayList<Cluster> centros,ArrayList<Ponto> db) {

        super(title);

        //Object[][][] data = new Object[3][50][2];
        final XYSeries s1 = new XYSeries("Cluster 1");
        final XYSeries s2 = new XYSeries("Cluster 2");
        final XYSeries s3 = new XYSeries("Cluster 3");
        final XYSeries s4 = new XYSeries("Centros");
        
        
        for (int i = 0; i < centros.get(0).getContains().size(); i++){
        	s1.add(centros.get(0).getContains().get(i).getX(),centros.get(0).getContains().get(i).getY());	
        }
        
        for (int i = 0; i < centros.get(1).getContains().size(); i++){
        	s2.add(centros.get(1).getContains().get(i).getX(),centros.get(1).getContains().get(i).getY());	
        }
        
        for (int i = 0; i < centros.get(2).getContains().size(); i++){
        	s3.add(centros.get(2).getContains().get(i).getX(),centros.get(2).getContains().get(i).getY());	
        }

        for (int i = 0; i < centros.size(); i++){
        	s4.add(centros.get(i).getCenter().getX(),centros.get(i).getCenter().getY());  	
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        dataset.addSeries(s3);
        dataset.addSeries(s4);
        
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Grafico Kmeans",          // chart title
            "X",               // domain axis label
            "Y",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,
            false
        );

        final XYPlot plot = chart.getXYPlot();
        final NumberAxis domainAxis = new NumberAxis("x");
        final NumberAxis rangeAxis = new LogarithmicAxis("y");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);
        final XYItemRenderer renderer = plot.getRenderer();
        Shape square = ShapeUtilities.createUpTriangle(1);
        renderer.setSeriesShape(4, square);
        renderer.setSeriesPaint(0, Color.gray);
        renderer.setSeriesPaint(1, Color.black);
        chart.setBackgroundPaint(Color.white);
        plot.setOutlinePaint(Color.black);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

//    public static void main(final String[] args) {
//
//        final XYLogAxesLines demo = new XYLogAxesLines("XY Log Axes Demo");
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);
//
//    }

}