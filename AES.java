import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AES {
    private static final String AES_KEY = "ThisIsASecretKey";

    public static void operateEncryption() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();

        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[fis.available()];
            fis.read(data);
            fis.close();

            // Encrypt the data using AES
            byte[] encryptedData = encryptAES(data, AES_KEY);

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(encryptedData);
            fos.close();

            JOptionPane.showMessageDialog(null, "Encryption done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void operateDecryption() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();

        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[fis.available()];
            fis.read(data);
            fis.close();

            // Decrypt the data using AES
            byte[] decryptedData = decryptAES(data, AES_KEY);

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(decryptedData);
            fos.close();

            JOptionPane.showMessageDialog(null, "Decryption done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] encryptAES(byte[] data, String key) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    private static byte[] decryptAES(byte[] data, String key) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    public static void main(String args[]) {
        System.out.println("This is testing");
        JFrame f = new JFrame();
        f.setTitle("Image Operation");
        f.setSize(400, 400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font = new Font("Roboto", Font.BOLD, 25);
        JButton encryptButton = new JButton();
        encryptButton.setText("Encrypt Image");
        encryptButton.setFont(font);
        JButton decryptButton = new JButton();
        decryptButton.setText("Decrypt Image");
        decryptButton.setFont(font);
        JTextField textField = new JTextField(10);
        textField.setFont(font);
        encryptButton.addActionListener(e -> {
            System.out.println("Encrypt button clicked");
            operateEncryption();
        });
        decryptButton.addActionListener(e -> {
            System.out.println("Decrypt button clicked");
            operateDecryption();
        });
        f.setLayout(new FlowLayout());
        f.add(encryptButton);
        f.add(decryptButton);
        f.add(textField);
        f.setVisible(true);
    }
}