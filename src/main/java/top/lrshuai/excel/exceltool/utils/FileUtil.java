package top.lrshuai.excel.exceltool.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {


    /**
     * inputStream 转 file
     * @param inputStream input
     * @param file file
     * @throws IOException error
     */
    public static void copyInputStreamToFile(InputStream inputStream, File file)  throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            // commons-io
//            IOUtils.copy(inputStream, outputStream);

        }

    }
}
