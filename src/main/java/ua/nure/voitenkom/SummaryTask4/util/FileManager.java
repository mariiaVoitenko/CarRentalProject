package ua.nure.voitenkom.SummaryTask4.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Mariia Voitenko
 */
public class FileManager {

    public static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024 * 4];
        int n;
        while ((n = input.read(buffer)) != -1) {
            output.write(buffer, 0, n);
        }
        output.flush();
    }

}
