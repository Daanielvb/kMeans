package kmeans;

import java.awt.Color;
import java.io.FileNotFoundException;
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

public class XYLogAxes extends ApplicationFrame {

    
    public XYLogAxes(final String title, ArrayList<Cluster> centros,ArrayList<Ponto> db) {
    	
    	
        super("Kmeans");

        final XYSeries c1 = new XYSeries("Pontos");
        final XYSeries c2 = new XYSeries("Centros");
       

        for(int i = 0; i < db.size(); i++){
        	c1.add(db.get(i).getX(),db.get(i).getY());
        }
        
        for(int i = 0; i < centros.size(); i++){
        	c2.add(centros.get(i).getCenter().getX(),centros.get(i).getCenter().getY());
        }
        
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(c1);
        dataset.addSeries(c2);
        

        final JFreeChart chart = ChartFactory.createScatterPlot(
            "Gráfico Kmeans",          // chart title
            "X",               // domain axis label
            "Y",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,
            false
        );

        final XYPlot plot = chart.getXYPlot();
        final NumberAxis domainAxis = new NumberAxis("X");
        final NumberAxis rangeAxis = new LogarithmicAxis("Y");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);
        final XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.gray);
        renderer.setSeriesPaint(1, Color.red);
        chart.setBackgroundPaint(Color.white);
        plot.setOutlinePaint(Color.black);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 570));
        setContentPane(chartPanel);

    }

}
