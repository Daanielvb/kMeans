package kmeans;

import java.util.ArrayList;


public class Ponto {
		private double x;
		private double y;
		private ArrayList<Double> distances;
		
		// Constructor 
		
		Ponto(double x,double y){
			this.x = x;
			this.y = y;
			this.distances = new ArrayList<Double>();
		}
		
		// Adds a new distance to the relativeDistances Array
		
		public void addRelativeDistances(double dist){
			this.distances.add(new Double(dist));
		}
		
		// Auxiliary functions to get smallest distance element and index 
		
		public double getClosest(){
			double min = this.distances.get(0);
			for(int i = 0; i < this.distances.size(); i++){
				if(this.distances.get(i) < min){
					min = distances.get(i);
				}
			}
			return min;
		}
		
		public int getClosestElementIndex(){
			double min = this.distances.get(0);
			int index = 0;
			for(int i = 0; i < this.distances.size(); i++){
				if(this.distances.get(i) < min){
					min = distances.get(i);
					index = distances.indexOf(min);
				}
			}
			return index;
		}
		
		
		// Getters & setters
		public double getX() {
			return x;
		}
		public void setX(double x) {
			this.x = x;
		}
		public double getY() {
			return y;
		}
		public void setY(double y) {
			this.y = y;
		}
		
		public ArrayList<Double> getDistances() {
			return distances;
		}
		public void setDistances(ArrayList<Double> distances) {
			this.distances = distances;
		}

}
