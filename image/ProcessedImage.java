package image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ProcessedImage {
	File f;
	String imageFolderPath = "D:\\ShaansDrawer\\Semester6\\AI\\ImageDataset\\ZainulAbedinArt\\OriginalZ";
	private static final int IMG_WIDTH = 380;
	private static final int IMG_HEIGHT = 380;
	
	public void imageResizer() throws IOException {
		f =  new File(imageFolderPath);
		for(File f1:f.listFiles()) {
			if(f1.getName().endsWith(".jpg")) {
//				System.out.println(f1.getName());
				BufferedImage bi2 = ImageIO.read(f1);
				//BufferedImage bi2 = ImageIO.read(new File(imageFolderPath+File.separator+f1.getName().replace(".png", ".jpg")));
				int type = bi2.getType();
				BufferedImage resizeImageJpg = resizeImage(bi2, type);
				ImageIO.write(resizeImageJpg, "jpg", new File("D:\\ShaansDrawer\\Semester6\\AI\\ImageDataset\\ZainulAbedinArt\\resizedZ\\"+f1.getName())); 
				
			}
		}

	}
	private static BufferedImage resizeImage(BufferedImage originalImage, int type){
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
			
		return resizedImage;
	    }

	
	
}
