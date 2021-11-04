package system.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import system.logic.Cliente;

public class ClienteDAO {

    DataBase db;

    public ClienteDAO() {
        db = DataBase.instance();
    }

    public void create(Cliente c) throws Exception {
        String sql = "insert into cliente (user, password, balance)"
                + "values(?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, c.getUsuario());
        stm.setString(2, c.getClave());
        stm.setString(3, String.valueOf(c.getSaldoCuenta()));

        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Cliente ya existe.");
        }
    }

    public Cliente read(String usuario) throws Exception {
        String sql = "select * from Cliente c where user=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, usuario);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            Cliente c = from(rs, "c");
            return c;
        } else {
            throw new Exception("Cliente no existe.");
        }
    }

    public void update(Cliente c) throws Exception {
        String sql = "update cliente set password=?, balance=? "
                + "where user=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, c.getUsuario());
        stm.setString(2, c.getClave());
        stm.setString(3, String.valueOf(c.getSaldoCuenta()));
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Cliente no existe");
        }
    }

    public void delete(Cliente c) throws Exception {
        String sql = "delete from cliente where user=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, c.getUsuario());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Cliente no existe.");
        }
    }

    public List<Cliente> findAll() {
        List<Cliente> resultado = new ArrayList<>();
        try {
            String sql = "select * from Cliente c";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);
            Cliente c;
            while (rs.next()) {
                c = from(rs, "c");
                resultado.add(c);
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<Cliente> findByCedula(String usuario) {
        List<Cliente> resultado = new ArrayList<>();
        try {
            String sql = "select * from cliente c "
                    + "where c.user like ?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, usuario + "%");
            ResultSet rs = db.executeQuery(stm);
            Cliente c;
            while (rs.next()) {
                c = from(rs, "c");
                resultado.add(c);
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public Cliente from(ResultSet rs, String alias) {
        try {
            Cliente c = new Cliente();
            c.setUsuario(rs.getString(alias + ".user"));
            c.setClave(rs.getString(alias + ".password"));
            c.setSaldoCuenta(rs.getDouble(alias + ".balance"));
            return c;
        } catch (SQLException ex) {
            return null;
        }
    }
}
