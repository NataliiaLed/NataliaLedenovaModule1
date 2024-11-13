package javarush.nataliia.ledenova.caesar;

import java.io.IOException;

public class Runner {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.!?:'\"";

    public static void main(String[] args) throws IOException {
        if (args.length == 3) {
            String command = args[0];
            String sourceFilePath = args[1];
            int key = Integer.parseInt(args[2]);

            process(command, sourceFilePath, key);
        } else {
            System.out.println("Enter correct arguments");
        }
    }

    private static void process(String command, String sourceFilePath, int key) throws IOException {
        System.out.printf("%s %s %s%n", command, sourceFilePath, key);
        switch (command) {
            case "ENCRYPT" -> {
                encryptFile(sourceFilePath, "[ENCRYPTED]", key);
            }
            case "DECRYPT" -> {
                encryptFile(sourceFilePath, "[DECRYPTED]", ALPHABET.length() - key);
            }
            case "BRUTEFORCE" -> System.out.println("Bruteforce is not implemented yet");
            default -> System.out.println("Command is invalid");
        }
    }

    private static void encryptFile(String sourceFilePath, String targetFileSuffix, int shift) throws IOException {
        FileService fileService = new FileService();
        String targetFilePath = createTargetFilePath(sourceFilePath, targetFileSuffix);
        CaesarCipher transformer = new CaesarCipher(ALPHABET, shift);
        fileService.transformFile(sourceFilePath, targetFilePath, transformer);
    }

    private static String createTargetFilePath(String filePath, String suffix) {
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return filePath + suffix;
        }
        String prefix = filePath.substring(0, lastDotIndex);
        String extension = filePath.substring(lastDotIndex);
        return prefix + suffix + extension;
    }
}
