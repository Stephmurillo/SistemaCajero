package system;

import java.io.IOException;
import system.comunication.Server;


public class Application {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.run();
    }
}
