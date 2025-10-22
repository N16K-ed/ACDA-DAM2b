package dao;

import models.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends GenericDAO {

    public ClienteDAO(Connection conn) {
        super(conn);
    }

    public void insertar(Cliente c) throws SQLException {
        String sql = "INSERT INTO cliente (nombre, correo) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, c.getNombre());
        stmt.setString(2, c.getCorreo());
        stmt.executeUpdate();
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public void modificarNombre(int id, String nuevoNombre) throws SQLException {
        String sql = "UPDATE cliente SET nombre = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nuevoNombre);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }

    public void modificarCorreo(String nuevoCorreo, int id) throws SQLException {
        String sql = "UPDATE cliente SET correo = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nuevoCorreo);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }

    public List<Cliente> listar() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Cliente c = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo")
            );
            lista.add(c);
        }
        return lista;
    }

    public Cliente obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("correo"));
        }
        return null;
    }


}
