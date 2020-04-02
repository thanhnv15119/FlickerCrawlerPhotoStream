package thanhnv.utils;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class ImageIOUtils {
    public static void saveImageByURL(String uri, String name) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(uri).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream( name + ".jpg")) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
