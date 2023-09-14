package miau.dona.IdeasdeProyecto;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        // Start the server thread
        Thread serverThread = new Thread(() -> {
            try {
                // Create a server socket and bind it to a specific port
                ServerSocket serverSocket = new ServerSocket(38528);
                JOptionPane.showMessageDialog(null, "Connection opened, waiting for the client", "Waiting...", JOptionPane.PLAIN_MESSAGE);

                // Listen for incoming client connections
                Socket clientSocket = serverSocket.accept();
                JOptionPane.showMessageDialog(null, "Client from " + clientSocket.getInetAddress().getHostAddress() + "has connected", "Connection successful", JOptionPane.PLAIN_MESSAGE);

                // Create input and output streams for communication
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read and process messages from the client
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Client: " + message);

                    // Send a response back to the client
                    out.println("Server received: " + message);
                }

                // Close the connections
                in.close();
                out.close();
                clientSocket.close();
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("An error has occurred serverside");
            }
        });
        serverThread.start();

        // Start the client thread
        Thread clientThread = new Thread(() -> {
            try {
                // Create a socket and connect it to the server's IP address and port
                Socket socket = new Socket("localhost", 38528);

                // Create input and output streams for communication
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Read user input and send it to the server
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                String message;
                while ((message = userInput.readLine()) != null) {
                    // Send the message to the server
                    out.println(message);

                    // Receive and display the response from the server
                    String response = in.readLine();
                    System.out.println("Server: " + response);
                }

                // Close the connections
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                System.out.println("An error occurred clientside");
            }
        });
        clientThread.start();
    }
}

