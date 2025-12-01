import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class HashGenerator {
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("admin123: " + hashPassword("admin123"));
        System.out.println("teacher123: " + hashPassword("teacher123"));
        System.out.println("student123: " + hashPassword("student123"));
    }
}
