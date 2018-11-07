package image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.opencsv.CSVWriter;

public class ImageData {

	File file;
	String imageFolderPath = "D:\\ShaansDrawer\\Semester6\\AI\\ImageDataset\\HashemKhanArt\\edgeH";
	String pixelFolderPath = "D:\\ShaansDrawer\\Semester6\\AI\\ImageDataset\\HashemKhanArt\\edgeHData\\";
	private static final int IMG_WIDTH = 380;
	private static final int IMG_HEIGHT = 380;
	
	public void getPixels() throws IOException {
		file = new File(imageFolderPath);
		for(File f1: file.listFiles()) {
			BufferedImage imageFile= ImageIO.read(f1);
			
			FileWriter outputFile = new FileWriter(new File(pixelFolderPath+f1.getName()+".csv"));
			CSVWriter writer = new CSVWriter(outputFile);
			String[] header = { "Height", "Width", "Red","Green","Blue","Hue","Saturation","Brightness" }; 
	        writer.writeNext(header);
			for(int y=0;y<IMG_HEIGHT;y++) {
				for(int x=0;x<IMG_WIDTH;x++) {
					Color imgColor = new Color(imageFile.getRGB(x, y));
					
					int red = imgColor.getRed();
					int blue = imgColor.getBlue();
					int green = imgColor.getGreen();
					float[] hsb = Color.RGBtoHSB(red, green, blue, null);
					float hue = hsb[0];
					float saturation = hsb[1];
					float brightness = hsb[2];
					
					String strx = Integer.toString(x);
					String stry =  Integer.toString(y);
					String strred =  Integer.toString(red);
					String strgreen = Integer.toString(green);
					String strblue = Integer.toString(blue);
					String strHue = Float.toString(hue);
					String strSaturation = Float.toString(saturation);
					String strBrightness = Float.toString(brightness);
					
					String[] pixel = {stry,strx,strred,strgreen,strblue,strHue,strSaturation,strBrightness};
					writer.writeNext(pixel);
				}
			}
			writer.close();
		}
	}
}
