package kmeans;

import java.util.ArrayList;

public class Cluster {
	private Ponto center;
	private ArrayList<Ponto> contains;
	
	//Constructor
	
	public Cluster(){
		double x = Math.random(); 
		double y = Math.random();
		this.center = new Ponto(x,y);
		this.contains = new ArrayList<Ponto>();
		
	}
	
	//Adds Ponto to itself
	
	public void addPonto(Ponto p){
		this.contains.add(p);
	}
	
	//Recalculate new center based on its points and 
	
	public void CalculateNewCenter(){
		int size = this.contains.size();
		if(size == 0){
			System.out.println("Empty Cluster");
		}
		else{
		double sumX = 0;
		double sumY = 0;
		for (int i = 0; i < size ; i++){
			sumX += this.contains.get(i).getX();
			sumY += this.contains.get(i).getY();
		}
		this.center.setX(sumX/size);
		this.center.setY(sumY/size);
	}
	}
	//Verify if the Cluster has changed position
	
	public boolean hasChangedPosition(){
		double oldx = this.center.getX();
		double oldy = this.center.getY();
		this.CalculateNewCenter();
		if(this.getCenter().getX() == oldx && this.getCenter().getY() == oldy){
			return false;
		}
		return true;
	}
	
	
	// Getters & setters
	
	public Ponto getCenter() {
		return center;
	}
	public void setCenter(Ponto center) {
		this.center = center;
	}
	public ArrayList<Ponto> getContains() {
		return contains;
	}
	public void setContains(ArrayList<Ponto> contains) {
		this.contains = contains;
	}
	
}
