package ar.edu.itba.paw.interfaces;

import java.awt.image.BufferedImage;
import java.io.IOException;
 

public interface ImageUploaderService {

	public String uploadImage(BufferedImage image, String imageType, String fileName, long size) throws IOException;

}
