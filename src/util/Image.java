package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Image {

	 static List<String> image=new ArrayList<String>();
	
	 public static String createImageRanDom() {
		 
		    image.add("/resource/images/007149216X.jpg");
		    image.add("/resource/images/007149328X.jpg");
		    image.add("/resource/images/007222438X.jpg");
		    image.add("/resource/images/007222942X.jpg");
		    image.add("/resource/images/012372533X.jpg");
		    image.add("/resource/images/012387582X.jpg");
		    image.add("/resource/images/007149216X.jpg");
		  
		    Random rand = new Random();
		    String fileName= image.get(rand.nextInt(image.size()));
		    return fileName;
		}
	
}
