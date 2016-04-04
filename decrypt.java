/*
 * For this to work, you must have the Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy in your Java install.
 *
 */
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;

class decrypt {
    
public static byte[] KEY = DatatypeConverter.parseHexBinary("4E9906E8FCB66CC9FAF49310620FFEE8F496E806CC057990209B09A433B66C1B");
public static String CIPHERTEXT = "j1Uyj3Vx8TY9LtLZil2uAuZkFQA/4latT76ZwgdHdhw";

    public void run() 
    {
        try 
        {            // Create key and cipher
            String padding = new String("");
            for (int i = 0; i <= ((4 - CIPHERTEXT.length() % 4) -1); i++){
            padding += "=";
            }
            byte[] decodedValue = DatatypeConverter.parseBase64Binary(CIPHERTEXT + padding);
            String password = new String(decodedValue, StandardCharsets.UTF_8);
            Key aesKey = new SecretKeySpec(KEY, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(password.getBytes());
            System.out.println(new String(encrypted));
            // decrypt the text
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(encrypted));
            System.out.println(decrypted);
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }


public static void main(String[] args) 
{
    decrypt pass = new decrypt();
    pass.run();
}
}