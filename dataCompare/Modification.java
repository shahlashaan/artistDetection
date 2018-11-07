package dataCompare;

import java.io.IOException;

public class Modification {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//EdgePercentage ed = new EdgePercentage();
		Dataset ds = new Dataset();
		try {
			//ed.calculateEdgePercentage();
			ds.creatingDataset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
