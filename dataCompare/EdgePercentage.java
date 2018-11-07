package dataCompare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class EdgePercentage {

	String edgeFolderPath= "D:\\ShaansDrawer\\Semester6\\AI\\ImageDataset\\ZainulAbedinArt\\edgeZPercentage";
	String pixelFolderPath ="D:\\ShaansDrawer\\Semester6\\AI\\ImageDataset\\ZainulAbedinArt\\edgeZainulData";
	File file;
	BufferedReader br;
	String line = "";
    String cvsSplitBy = ",";
    
	public void calculateEdgePercentage() throws IOException {
		file = new File(pixelFolderPath);
		FileWriter csvFile = new FileWriter(new File(edgeFolderPath+"\\edgePZA.csv"));
		CSVWriter writer = new CSVWriter(csvFile);
		String[] header= {"ImageName","EdgePercentage"};
		writer.writeNext(header);
		
		for(File f1:file.listFiles()) {
			int edge=0;
			int nonEdge=0;
			String sourceFile = f1.getName();
			br = new BufferedReader(new FileReader(pixelFolderPath+"\\"+sourceFile));
			int count =0;
			while((line = br.readLine()) != null) {
				if(count!=0) {
					String[] pixel=line.split(cvsSplitBy);
					//System.out.println(Integer.parseInt(pixel[2].substring(1, pixel[2].length() - 1)));
					int ered=Integer.parseInt(pixel[2].substring(1, pixel[2].length() - 1));
				    int egreen=Integer.parseInt(pixel[3].substring(1, pixel[3].length() - 1));
				    int eblue=Integer.parseInt(pixel[4].substring(1, pixel[4].length() - 1));
				    if(ered <100 && egreen<100 && eblue<100) {
				    	nonEdge++;
				    }
				    if(ered >190 && egreen>190 && eblue>190) {
				    	edge++;
				    }
				}
				else {
					count++;
				}
			}
			System.out.println("edge "+edge);
			System.out.println("nonedge "+nonEdge);
			double totalPixel=(double)edge+nonEdge;
			double value = (double)edge/totalPixel;
			double edgePercentage = value*100;
			
			System.out.println(edgePercentage);
			String percentageString = Double.toString(edgePercentage);
			String[] edges = {sourceFile,percentageString};
			writer.writeNext(edges);
		}
		writer.close();
	}
}
