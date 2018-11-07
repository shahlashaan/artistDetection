package dataCompare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class Dataset {
	String edgeFolderPath= "D:\\ShaansDrawer\\Semester6\\AI\\ImageDataset\\ZainulAbedinArt\\pixelZainulData";
	String pixelFolderPath ="D:\\ShaansDrawer\\Semester6\\AI\\ImageDataset\\ZainulAbedinArt\\ZainulHSBRGB";
	File file;
	BufferedReader br;
	String line = "";
    String cvsSplitBy = ",";
    PixelStore []pvalues;
    
    public void creatingDataset() throws IOException {
    	file = new File(pixelFolderPath);
		FileWriter csvFile = new FileWriter(new File(edgeFolderPath+"\\pixelZainulData.csv"));
		CSVWriter writer = new CSVWriter(csvFile);
		String[] header= {"ImageName","AvgRed","AvgGreen","AvgBlue","AvgHue","AvgSaturation","AvgBrightness"};
		writer.writeNext(header);
		
		for(File f1:file.listFiles()) {
			pvalues = new PixelStore[380];
			
			for (int i = 0 ; i != pvalues.length ; i++) {
				pvalues[i] = new PixelStore();
			}
			
			int Red=0;
			int Green=0;
			int Blue=0;
			double Hue=0;
			double Sat=0;
			double Br=0;
			String sourceFile = f1.getName();
			br = new BufferedReader(new FileReader(pixelFolderPath+"\\"+sourceFile));
			int count =0;
			while((line = br.readLine()) != null) {
				if(count!=0) {
					String[] pixelData=line.split(cvsSplitBy);
					int dx = Integer.parseInt(pixelData[0].substring(1, pixelData[0].length() - 1));
					int dy = Integer.parseInt(pixelData[1].substring(1, pixelData[1].length() - 1));
					int dred=Integer.parseInt(pixelData[2].substring(1, pixelData[2].length() - 1));
				    int dgreen=Integer.parseInt(pixelData[3].substring(1, pixelData[3].length() - 1));
				    int dblue=Integer.parseInt(pixelData[4].substring(1, pixelData[4].length() - 1));
				    double dhue=Double.parseDouble(pixelData[5].substring(1, pixelData[5].length() - 1));
				    double dsat=Double.parseDouble(pixelData[6].substring(1, pixelData[6].length() - 1));
				    double dbr=Double.parseDouble(pixelData[7].substring(1, pixelData[7].length() - 1));
				    
				    Red+=dred;
				    Green+=dgreen;
				    Blue+=dblue;
				    Hue+=dhue;
				    Sat+=dsat;
				    Br+=dbr;
				    if(dy==379) {
				    	//System.out.println(dx);
				    	//System.out.println(Red);
				    	//System.out.println(Red/380);
				    	pvalues[dx].red=(double)Red/380;
				    	pvalues[dx].green=(double)Green/380;
				    	pvalues[dx].blue=(double)Blue/380;
				    	pvalues[dx].hue=(double)Hue/380;
				    	pvalues[dx].sat=(double)Sat/380;
				    	pvalues[dx].bright=(double)Br/380;
				    	
				    	Red=0;
						Green=0;
						Blue=0;
						Hue=0;
						Sat=0;
						Br=0;
				    	
				    }
				}
				else {
					count++;
				}
			}
			double totalRed=0;
			double totalGreen=0;
			double totalBlue=0;
			double totalHue=0;
			double totalSat=0;
			double totalBright=0;
			for(int i=0;i<380;i++) {
				totalRed+=pvalues[i].red;
				totalGreen+=pvalues[i].green;
				totalBlue+=pvalues[i].blue;
				totalHue+=pvalues[i].hue;
				totalSat+=pvalues[i].sat;
				totalBright+=pvalues[i].bright;
			}
			double avgRed=totalRed/380;
			double avgGreen=totalGreen/380;
			double avgBlue=totalBlue/380;
			double avgHue=totalHue/380;
			double avgSat=totalSat/380;
			double avgBright=totalBright/380;
			
			String stAvgRed = Double.toString(avgRed);
			String stAvgGreen = Double.toString(avgGreen);
			String stAvgBlue = Double.toString(avgBlue);
			String stAvgHue = Double.toString(avgHue);
			String stAvgSat = Double.toString(avgSat);
			String stAvgBright = Double.toString(avgBright);
			String[] VALUES = {sourceFile,stAvgRed,stAvgGreen,stAvgBlue,stAvgHue,stAvgSat,stAvgBright};
			writer.writeNext(VALUES);
			
		}
		writer.close();
    }
}
