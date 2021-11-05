
package system.comunication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import sistema.comunication.Protocol;
import system.logic.Service;
import system.logic.Cliente;

public class Server {
    ServerSocket ss;
    
    public Server() throws IOException {
        ss = new ServerSocket(Protocol.PORT);
        System.out.println("Servidor iniciado...");
    }
    
    public void run(){
        boolean continuar = true;
        Cliente usuario=null;
        Socket s;
        ObjectInputStream in;
        ObjectOutputStream out;
        while (continuar) {
            try {
                s = ss.accept();
                out = new ObjectOutputStream(s.getOutputStream() );
                in = new ObjectInputStream(s.getInputStream());                
                try {
                    usuario=(Cliente)in.readObject();
                    usuario=Service.instance().login(usuario);
                    out.writeObject(usuario);
                    out.flush();
                    System.out.println("Conexion Establecida...");
                    Worker worker = new Worker(s,in,out,usuario); 
                    worker.start();                    
                } catch (Exception ex) {   
                    out.writeObject(new Cliente("ERROR","",0));
                    out.flush();
                    s.close();
                }                

            } catch (IOException ex) { }
        }
    }
    
}