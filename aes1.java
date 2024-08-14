import javax.crypto.Cipher;
import javax.crypto.KeyGenerator; 
import javax.crypto.SecretKey;  
import java.util.Base64;
import java.util.Scanner;
public class aes1 {
     // Method to generate a secret key
     public static SecretKey generateKey(int n) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(n); 
        SecretKey sk = kg.generateKey();
        return sk;
     }
     // Method to encrypt a plaintext using a secret key
     public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
     }
     // Method to decrypt a ciphertext using a secret key
     public static String decrypt(String cipherText, SecretKey secretKey) throws Exception 
    {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(decryptedBytes);
     }
     // Main method to demonstrate encryption and decryption
     public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the plaintext:");
            String plaintext = scanner.nextLine();

            System.out.println("Enter the key length (128, 192, or 256):");
            int keyLength = scanner.nextInt();

            SecretKey secretKey = generateKey(keyLength);

            String encryptedText = encrypt(plaintext, secretKey);
            System.out.println("Encrypted Text: " + encryptedText);

            String decryptedText = decrypt(encryptedText, secretKey);
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



























// import java.util.Scanner;

// class aes1 {

//     private static final String key = "123456789012345"; // 128 bit key

//     public static byte[] encrypt(String strToEncrypt, String key) throws Exception {
//         byte[] keyBytes = key.getBytes();
//         byte[] dataBytes = strToEncrypt.getBytes();
//         int dataLength = dataBytes.length;
//         int blockLength = 16;
//         int paddedLength = blockLength * ((dataLength + blockLength - 1) / blockLength);
//         byte[] paddedData = new byte[paddedLength];
//         System.arraycopy(dataBytes, 0, paddedData, 0, dataLength);

//         byte[] result = new byte[paddedLength];
//         for (int i = 0; i < paddedLength; i += blockLength) {
//             for (int j = 0; j < blockLength; j++) {
//                 result[i + j] = (byte) (paddedData[i + j] ^ keyBytes[j]);
//             }
//         }
//         return result;
//     }

//     public static String decrypt(byte[] encryptedBytes, String key) throws Exception {
//         byte[] keyBytes = key.getBytes();
//         int blockLength = 16;
//         int dataLength = encryptedBytes.length;
//         byte[] decryptedData = new byte[dataLength];
//         for (int i = 0; i < dataLength; i += blockLength) {
//             for (int j = 0; j < blockLength; j++) {
//                 decryptedData[i + j] = (byte) (encryptedBytes[i + j] ^ keyBytes[j]);
//             }
//         }
//         return new String(decryptedData).trim();
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.println("Enter the plaintext: ");
//         String originalString = scanner.nextLine();

//         try {
//             byte[] encryptedBytes = encrypt(originalString, key);
//             StringBuilder encryptedStringBuilder = new StringBuilder();
//             for (byte encryptedByte : encryptedBytes) {
//                 encryptedStringBuilder.append(String.format("%02x", encryptedByte));
//             }
//             String encryptedString = encryptedStringBuilder.toString();

//             byte[] encryptedBytesArray = new byte[encryptedString.length() / 2];
//             for (int i = 0; i < encryptedString.length(); i += 2) {
//                 encryptedBytesArray[i / 2] = (byte) ((Character.digit(encryptedString.charAt(i), 16) << 4)
//                         + Character.digit(encryptedString.charAt(i + 1), 16));
//             }
//             String decryptedString = decrypt(encryptedBytesArray, key);

//             System.out.println("Original String: " + originalString);
//             System.out.println("Encrypted String: " + encryptedString);
//             System.out.println("Decrypted String: " + decryptedString);
//         } catch (Exception e) {
//             System.out.println("Encryption/Decryption error: " + e.getMessage());
//         } finally {
//             scanner.close();
//         }
//     }
// }

