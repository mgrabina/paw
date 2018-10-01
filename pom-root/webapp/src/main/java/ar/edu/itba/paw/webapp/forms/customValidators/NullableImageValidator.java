package ar.edu.itba.paw.webapp.forms.customValidators;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;

public class NullableImageValidator implements ConstraintValidator<NullableImage, MultipartFile> {
	
	private final ArrayList<String> ACCEPTED_MIME_TYPES= new ArrayList<String>(Arrays.asList("png", "jpg", "jpeg", "bmp"));
	private final long MAX_SIZE_BYTES = 10485760; //10MB

	
	public void initialize(NullableImage constraintAnnotation) {
	}
	
    public boolean isValid(MultipartFile object, ConstraintValidatorContext constraintContext) {
    	
    	if (object.isEmpty()) {
    		return true;
    	}
    	
    	String extension = FilenameUtils.getExtension(object.getOriginalFilename());
    	long size = object.getSize();

    	return ACCEPTED_MIME_TYPES.contains(extension) && size < MAX_SIZE_BYTES;
        //return true;
    }


}
