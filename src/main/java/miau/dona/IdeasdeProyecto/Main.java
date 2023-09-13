package miau.dona.IdeasdeProyecto;

import java.io.File;

public class Main {
    public static void main(String[] args) { // Sistema de mensajeria
        new File(System.getProperty("user.dir") + "\\run").mkdir();
        Server.main();
        Client.main();

    }
}
