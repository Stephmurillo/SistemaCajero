package system.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import system.logic.Cliente;

public class ClienteDAO {
    DataBase db;
    
    public ClienteDAO(){
        db = DataBase.instance();
    }

    public void create(Cliente c) throws Exception{
        String sql="insert into cliente (usuario, clave, saldo) "+
                "values(?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, c.getUsuario());
        stm.setString(2, c.getClave());
        stm.setString(3, String.valueOf(c.getSaldoCuenta()));
      
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Cliente ya existe.");
        }
    }
    
    public Cliente read(String cedula) throws Exception{
        String sql="select * from Cliente c where usuario=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, cedula);
        ResultSet rs =  db.executeQuery(stm);
        if (rs.next()) {
            Cliente c = from(rs, "c"); 
            return c;
        }
        else{
            throw new Exception ("Cliente no existe.");
        }
    }
    public void update(Cliente c) throws Exception{
        String sql="update cliente set clave=?, saldo=? "+
                "where usuario=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, c.getUsuario());
        stm.setString(2, c.getClave());
        stm.setString(3, String.valueOf(c.getSaldoCuenta()));       
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Cliente no existe");
        }        
    }

    public void delete(Cliente c) throws Exception{
        String sql="delete from cliente where usuario=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, c.getUsuario());
        int count=db.executeUpdate(stm);
        if (count==0){
            throw new Exception("Cliebte no existe.");
        }
    }
    
    
    public List<Cliente> findAll(){
        List<Cliente> resultado=new ArrayList<>();
        try {
            String sql="select * from Cliente c";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs =  db.executeQuery(stm);
            Cliente c;
            while (rs.next()) {
                c = from(rs, "c"); 
                resultado.add(c);
            }
        } catch (SQLException ex) { }
        return resultado;        
    }

    public List<Cliente> findByCedula(String cedula){
        List<Cliente> resultado = new ArrayList<>();
        try {
            String sql="select * from cliente c "+
                    "where c.usuario like ?";            
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, cedula+"%");
            ResultSet rs =  db.executeQuery(stm); 
            Cliente c;
            while (rs.next()) {
                c = from(rs, "c"); 
                resultado.add(c);
            }
        } catch (SQLException ex) {  }
        return resultado;
    }
    
    public Cliente from(ResultSet rs, String alias){
        try {
            Cliente c= new Cliente();
            c.setUsuario(rs.getString(alias+".usuario"));
            c.setClave(rs.getString(alias+".clave"));
            c.setSaldoCuenta(Double.parseDouble(rs.getString(alias+".saldo")));
            return c;
        } catch (SQLException ex) {
            return null;
        }
    }    
}
