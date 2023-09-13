package miau.dona.IdeasdeProyecto;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static String testFile = System.getProperty("user.dir") + "\\run\\Prueba.txt";

    public static void main() {
        try (Socket socket = new Socket("localhost", 38528)) {
            try (OutputStream outputStream = new FileOutputStream(testFile)){
                Scanner scanner = new Scanner(System.in);
                String texto;
                texto = JOptionPane.showInputDialog(null,"Que mensaje quieres enviar?", "Envia un mensaje", JOptionPane.INFORMATION_MESSAGE);
                byte[] textoBytes = texto.getBytes(StandardCharsets.UTF_8);
                outputStream.write(textoBytes); // Recoge en forma de bytes lo que se quiere enviar y lo escribe

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(testFile))){
                    String linea;
                    while ((linea = bufferedReader.readLine()) != null) {
                        JOptionPane.showMessageDialog(null, linea, "Recibiste un mensaje", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error" + e);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error" + e, "Error outputStream", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el puerto", "Error puerto", JOptionPane.ERROR_MESSAGE);
        }


    }

}
