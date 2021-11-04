package system.logic;

public class Cliente {

    public Cliente(String usuario, String clave, double saldoCuenta) {
        this.usuario = usuario;
        this.clave = clave;
        this.saldoCuenta = saldoCuenta;
    }
    
    public Cliente() {
        this.usuario = "";
        this.clave = "";
        this.saldoCuenta = 0;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public double getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setSaldoCuenta(double saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }
    
    public String usuario;
    public String clave;
    public double saldoCuenta;
    
}
