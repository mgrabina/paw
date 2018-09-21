package ar.edu.itba.paw.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import ar.edu.itba.paw.interfaces.ImageUploaderService;


@Service
public class ImageUploaderServiceImpl implements ImageUploaderService {
	
	private final String UPLOAD_URL_PATH = "https://s3-sa-east-1.amazonaws.com/itba.paw.pictures/";
	private final ArrayList<String> ACCEPTED_MIME_TYPES= new ArrayList<String>(Arrays.asList("png", "jpg", "jpeg", "bmp"));
	private final long MAX_SIZE_BYTES = 10485760; //10MB
	
	private boolean isValid(BufferedImage image, String imageType, long size) {
		
    	return ACCEPTED_MIME_TYPES.contains(imageType) && size < MAX_SIZE_BYTES;
       
	}

	public String uploadImage(BufferedImage image, String imageType, String fileName, long size) throws IOException {

		if (!isValid(image, imageType, size)) {
			System.out.println("Imagen invalida");
			return null; //HANDLEAR ESTO
		}
		
		//Browser cache
		String imageSrc = new StringBuilder(UPLOAD_URL_PATH).append(fileName).toString();

		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		ImageIO.write(image, imageType, outstream);
		byte[] buffer = outstream.toByteArray();
		InputStream is = new ByteArrayInputStream(buffer);
		
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentType("image/" + imageType);
		meta.setContentLength(buffer.length);
		
		BasicAWSCredentials creds = new BasicAWSCredentials("AKIAI7O4Y2NAW3WZLD5A", "PHZZqjTk0emxLqiuw6ZvdAXDcknSYn/yws3lL0YJ"); 
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("sa-east-1").withCredentials(new AWSStaticCredentialsProvider(creds)).build();
		s3Client.putObject(new PutObjectRequest("itba.paw.pictures", fileName, is, meta).withCannedAcl(CannedAccessControlList.PublicRead));
		
		return imageSrc;
		
	}
	
}