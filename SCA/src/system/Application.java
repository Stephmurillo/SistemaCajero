package system;

import system.logic.Cliente;
import system.logic.Service;

public class Application {

    public static void main(String[] args) {
        Cliente clien1 = new Cliente();
        
        Cliente clien2 = new Cliente("Mari1970", "1234", 10000);
        
        try {
            Service.instance().clienteAdd(clien2);
            Service.instance().clienteGet("Mari1970");
            
            
        } catch (Exception ex) {
        }
         
        System.out.println("Usuario: " + clien2.getUsuario());
    }
}
