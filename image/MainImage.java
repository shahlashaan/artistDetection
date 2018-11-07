package image;

import java.io.IOException;

public class MainImage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*ProcessedImage img = new ProcessedImage();
		try {
			img.imageResizer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		ImageData imgd = new ImageData();
		try {
			imgd.getPixels();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ImageEdgeDetection imged = new ImageEdgeDetection();
		//imged.detectEdge();
	}

}
