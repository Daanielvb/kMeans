package kmeans;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TestClass {
	
	
public static void main(String[] args) throws FileNotFoundException {
			Kmeans k = new Kmeans();
			//path to File
			//String db = "C:\\Users\\YourUser\\Desktop\\clusters.txt";
			k.kmeans(5, db);
			
}
}

