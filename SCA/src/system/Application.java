package system;

/*
   @Authors:
      Yoselin Rojas Fuentes | 207700499 | Grupo 04
      Cinthya Murillo Hidalgo | 305260682 | Grupo 03
*/
import system.logic.Cliente;
import system.logic.Service;

public class Application {

    public static void main(String[] args) {
        Cliente clien1 = new Cliente();
        //Cliente clien2 = new Cliente("Mari1970", "password", 20000);
        
        try {
            clien1 = Service.instance().clienteGet("Pedro89");
            //Service.instance().clienteAdd(clien2);
        } catch (Exception ex) {}
         
        System.out.println("Usuario: " + clien1.getUsuario() + "\nSaldo: " + clien1.getSaldoCuenta());
    }
}
