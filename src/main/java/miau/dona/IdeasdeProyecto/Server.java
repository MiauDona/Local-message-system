package miau.dona.IdeasdeProyecto;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    Thread serverThread;

    public void startServerThread() {
        serverThread = new Thread(this);
        serverThread.start();
    }

    @Override
    public void run() {
        while (serverThread != null) {

        }

        //try (ServerSocket serverSocket = new ServerSocket(38528)) {
        //    JOptionPane.showMessageDialog(null, "Esperando cliente");
        //    while (serverThread != null) {
        //        try (Socket clienteSocket = serverSocket.accept()) {
        //            JOptionPane.showMessageDialog(null, "Cliente conectado desde " + clienteSocket.getInetAddress());
        //            Thread clienteThread = new Thread(new clienteHandler(clienteSocket));
        //            clienteThread.start();
        //        } catch (IOException e) {
        //            JOptionPane.showMessageDialog(null, "Error recibiendo socket");
        //        }
        //    }
        //} catch (IOException e) {
        //    JOptionPane.showMessageDialog(null, "Error abriendo server");
        //}
    }
}
