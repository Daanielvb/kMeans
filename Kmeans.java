package kmeans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jfree.ui.RefineryUtilities;


public class Kmeans {
	private ArrayList<Ponto> database;
	private ArrayList<Cluster> clusters;
	
	//Constructor
	
	public Kmeans(){
		this.database = new ArrayList<Ponto>();
		this.clusters = new ArrayList<Cluster>();
	}
	
	//Add Ponto to database
	
	public void addPonto(Ponto p){
		this.database.add(p);
	}
	

	//Generate clusters
	
	public void generateKClusters(int k){
		for (int i = 0; i < k; i++){
			this.clusters.add(new Cluster());
		}
	}

	// Euclidean distance
	
	public double calculateDist(Ponto X, Ponto Y){
		double result = Math.sqrt(Math.pow((Y.getX() - X.getX()),2) + Math.pow((Y.getY() - X.getY()), 2));
		return result;
		
	}
	
	public double calculateDist(Ponto X, Cluster C){
		double result = Math.sqrt(Math.pow((C.getCenter().getX() - X.getX()),2) + Math.pow((C.getCenter().getY() - X.getY()), 2));
		return result;
		
	}
	
	//Reads File to create database
	
	public void readDatabase(String s) throws FileNotFoundException{    
	    try {               
	        final BufferedReader br = new BufferedReader(new FileReader(s));
	        final Scanner trainFile = new Scanner(br);
	        while (trainFile.hasNextDouble()) {      
	            double x = trainFile.nextDouble();
	            double y = trainFile.nextDouble();
	            this.database.add(new Ponto(x,y));
	        }   
	        trainFile.close();
	        br.close();
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
	
	//Calculate Distance from every single Ponto to every Cluster
	
	public void calculateRelativeDistances(){
		for(int i = 0;i < this.database.size(); i++){
			for(int j=0; j < this.clusters.size(); j++){
				this.database.get(i).addRelativeDistances(calculateDist(this.database.get(i),this.clusters.get(j)));
			}
		}
	}
	
	//Insert Ponto into the closest Cluster
	
	public void insertIntoClusters(){
		for(int i = 0; i < this.database.size(); i++){
			int index = this.database.get(i).getClosestElementIndex();
			this.clusters.get(index).addPonto(this.database.get(i));
		}
	}
	
	
	// Recalculate Clusters centers after Ponto addition
	
	public boolean recalculateClusters(){
		int counter = 0;
		for(int i = 0; i < this.clusters.size(); i++){
			boolean flag = this.clusters.get(i).hasChangedPosition();
			if(flag == false){
				counter++;
				System.out.println(counter);
			}
		}
		if (counter == this.clusters.size()){
			return true;
		}
		return false;
	}
	
	// Clears Clusters' contains
	
	public void clearClusters(){
		for(int i = 0; i < this.clusters.size(); i++){
			this.clusters.get(i).getContains().clear();
			}
	}
	
	// Clear Pontos
	
		public void clearPontos(){
			for(int i = 0; i < this.database.size(); i++){
				this.database.get(i).getDistances().clear();
				}
		}
		
	// Loops until clusters are correctly placed
	
	public void kmeans(int k,String db) throws FileNotFoundException{
		int count = 1;
		generateKClusters(k);
		this.readDatabase(db);
		this.calculateRelativeDistances();
		this.insertIntoClusters();
		boolean flag = this.recalculateClusters();
//		XYLogAxesLines grafico_lines = new XYLogAxesLines("Kmeans",this.getClusters(),this.getDatabase());
//        grafico_lines.pack();
//        RefineryUtilities.centerFrameOnScreen(grafico_lines);
//        grafico_lines.setVisible(true);
        XYLogAxes grafico_pontos = new XYLogAxes("Kmeans",this.getClusters(),this.getDatabase());
        grafico_pontos.pack();
        RefineryUtilities.centerFrameOnScreen(grafico_pontos);
		while(flag != true){
			this.clearClusters();
			this.clearPontos();
			this.calculateRelativeDistances();
			this.insertIntoClusters();
			flag = this.recalculateClusters();
			try {
			    Thread.sleep(3000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
//			grafico_lines = new XYLogAxesLines("K means",this.getClusters(),this.getDatabase());
//	        grafico_lines.pack();
//	        RefineryUtilities.centerFrameOnScreen(grafico_lines);
//	        grafico_pontos.setVisible(true);
	        grafico_pontos = new XYLogAxes("K means",this.getClusters(),this.getDatabase());
	        grafico_pontos.pack();
	        RefineryUtilities.centerFrameOnScreen(grafico_pontos);
	        grafico_pontos.setVisible(true);
	        count++;
			System.out.println("Treinamento numero:" + count);
		}			
		}
	
	// Getters & setters

	public ArrayList<Ponto> getDatabase() {
		return database;
	}


	public void setDatabase(ArrayList<Ponto> database) {
		this.database = database;
	}


	public ArrayList<Cluster> getClusters() {
		return clusters;
	}


	public void setClusters(ArrayList<Cluster> clusters) {
		this.clusters = clusters;
	}

}
