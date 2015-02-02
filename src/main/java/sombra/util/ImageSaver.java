package sombra.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Service
public class ImageSaver {
    private static final Logger logger = Logger.getLogger(ImageSaver.class.getName());

    @Autowired
    ServletContext servletContext;

    public void saveImage(MultipartFile image){
        try {
            String path = servletContext.getRealPath("resources\\img");
            File file = new File(path + "\\" + image.getOriginalFilename());
            image.transferTo(file);
            //FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException ioe) {
            logger.error(ioe.getMessage());
            logger.debug(ioe, ioe);
        }
    }
}
