package javarush.nataliia.ledenova.caesar;

import java.io.*;
import java.nio.charset.Charset;

public class FileService {
    /**
     * Reads chars from sourceFile, transforms each char using charTransformer and writes to targetFilePath
     */
    public void transformFile(String sourceFilePath, String targetFilePath, CharTransformer charTransformer) throws IOException {
        try (InputStream in = new FileInputStream(sourceFilePath);
             Reader reader = new InputStreamReader(in, Charset.defaultCharset());
             Reader bufferedReader = new BufferedReader(reader);

             OutputStream out = new FileOutputStream(targetFilePath);
             PrintWriter printWriter = new PrintWriter(out)) {
            int r;
            while ((r = bufferedReader.read()) != -1) {
                char ch = (char) r;
                char transformed = charTransformer.transform(ch);
                printWriter.print(transformed);
            }

        }
    }
}
