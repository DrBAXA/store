package sombra.util;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class ImageSaver {

    public static final String RESOURCES_FOLDER = "\\resources\\img\\";

    private static final Logger logger = Logger.getLogger(ImageSaver.class.getName());

    public static void saveImage(MultipartFile image, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/") + RESOURCES_FOLDER;
        File file = new File(path + image.getOriginalFilename());
        try {
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException ioe) {
            logger.error(ioe.getMessage());
            logger.debug(ioe, ioe);
        }
    }
}
