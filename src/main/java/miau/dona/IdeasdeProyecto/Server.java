package miau.dona.IdeasdeProyecto;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main() {
        try (ServerSocket serverSocket = new ServerSocket(38528)) {
            JOptionPane.showMessageDialog(null, "Esperando cliente");
            while (true) {
                try (Socket clienteSocket = serverSocket.accept()) {
                    JOptionPane.showMessageDialog(null, "Cliente conectado desde " + clienteSocket.getInetAddress());
                    Thread clienteThread = new Thread(new clienteHandler(clienteSocket));
                    clienteThread.start();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error recibiendo socket");
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error abriendo server");
        }
    }
}
class clienteHandler implements Runnable {
    public Socket clienteSocket;
    public clienteHandler(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
    }
    @Override
    public void run() {
        try {
            // Streams de entrada y salida para comunicarse con el cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);

            // Leer datos del cliente
            String mensajeCliente = entrada.readLine();
            System.out.println("Cliente dice: " + mensajeCliente);

            // Enviar una respuesta al cliente
            salida.println("Hola desde el servidor!");

            // Cerrar la conexión
            clienteSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
