package pt.sapiens.sapiensAPI.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    public String saveImage(MultipartFile image) throws IOException {

        String fileType = image.getContentType();
        if (fileType == null || (!fileType.equals("image/png") && !fileType.equals("image/jpeg"))) {
            throw new IllegalArgumentException();
        }

        BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
        if (bufferedImage == null) {
            throw new IllegalArgumentException();
        }

        String image_path = "images";
        Path folder = Paths.get(image_path);
        if (!Files.exists(folder)) {
            Files.createDirectories(folder);
        }

        String extension = getFileExtension(image.getOriginalFilename());
        String fileName = UUID.randomUUID().toString() + "." + extension;

        Path fullPath = folder.resolve(fileName);
        Files.copy(image.getInputStream(), fullPath);

        return "/image/" + fileName;
    }

    private String getFileExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "png";
    }
}
