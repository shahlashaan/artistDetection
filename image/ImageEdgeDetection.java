package image;
import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
public class ImageEdgeDetection {

	
	public void detectEdge() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		File file;
		String folder = "D:\\ShaansDrawer\\Semester6\\AI\\ImageDataset\\ZainulAbedinArt\\resizedZ\\";
		String edgedFolderPath = "D:\\ShaansDrawer\\Semester6\\AI\\ImageDataset\\ZainulAbedinArt\\edgeZainul\\";
		file = new File(folder);
		for(File f1: file.listFiles()) {
			 String imgFile = folder+f1.getName();

		      // Reading the image
		      Mat src = Imgcodecs.imread(imgFile);

		      // Creating an empty matrix to store the result
		      Mat gray = new Mat();

		      // Converting the image from color to Gray
		      Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		      Mat edges = new Mat();

		      // Detecting the edges
		      Imgproc.Canny(gray, edges, 60, 60*3);

		      // Writing the image
		      Imgcodecs.imwrite(edgedFolderPath+f1.getName(), edges);
		      System.out.println("Image Loaded");
		}
	}
}
