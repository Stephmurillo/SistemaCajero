package system.logic;

import java.util.List;
import system.data.ClienteDAO;

public class Service {

    // Singleton implementation
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) {
            theInstance = new Service();
        }
        return theInstance;
    }

    // Service data
    ClienteDAO cDao;

    // Service methods
    public Cliente clienteGet(String cedula) throws Exception {
        return cDao.read(cedula);
    }

    public List<Cliente> clienteSearch(String cedula) {
        return cDao.findByCedula(cedula);
    }

    public List<Cliente> clienteAll() {
        return cDao.findAll();
    }

    public void clienteAdd(Cliente cliente) throws Exception {
        cDao.create(cliente);
    }

    public Service() {
        try {
            cDao = new ClienteDAO();
        } catch (Exception e) {}
    }
}
