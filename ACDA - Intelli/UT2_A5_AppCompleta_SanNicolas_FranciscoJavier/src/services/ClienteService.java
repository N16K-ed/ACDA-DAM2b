package services;

import dao.ClienteDAO;
import models.Cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClienteService {
    private final ClienteDAO clienteDAO;

    public ClienteService(Connection conn) {
        this.clienteDAO = new ClienteDAO(conn);
    }

    public void insertar(Cliente cliente) throws SQLException {
        if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
        if (cliente.getCorreo() == null || !cliente.getCorreo().contains("@")) {
            throw new IllegalArgumentException("El correo debe contener '@'.");
        }
        clienteDAO.insertar(cliente);
    }

    public void eliminar(int id) throws SQLException {
        clienteDAO.eliminar(id);
    }

    public void modificarNombre(int id, String nuevoNombre) throws SQLException {
        if (nuevoNombre == null || nuevoNombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
        clienteDAO.modificarNombre(id, nuevoNombre);
    }

    public void modificarCorreo(int id, String nuevoCorreo) throws SQLException {
        if (nuevoCorreo == null || !nuevoCorreo.contains("@")) {
            throw new IllegalArgumentException("El correo debe contener '@'.");
        }
        clienteDAO.modificarCorreo(nuevoCorreo, id);
    }

    public List<Cliente> listar() throws SQLException {
        return clienteDAO.listar();
    }
}
