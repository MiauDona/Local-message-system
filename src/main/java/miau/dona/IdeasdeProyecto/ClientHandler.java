package miau.dona.IdeasdeProyecto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    public Socket clienteSocket;

    public ClientHandler(Socket clienteSocket) {
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
