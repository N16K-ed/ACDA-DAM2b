package Refuerzo2_5.dao;

import Refuerzo2_5.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductoDAO {
    private Connection conn;

    public ProductoDAO(Connection conn){
        this.conn = conn;
    }

    public void insertarProducto(Producto p) throws SQLException{
        String sql = "INSERT INTO producto (nombre, precio) VALUES(?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,p.getNombre());
        statement.setDouble(2,p.getPrecio());
        statement.executeUpdate();
        statement.close();
    }

    public List<Producto> listarProducto() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            Producto p = new Producto(
              rs.getString("nombre"),
              rs.getDouble("precio")
            );
            productos.add(p);
        }
        rs.close();
        statement.close();
        return productos;
    }
    public void eliminarProducto(int id) throws SQLException{
        String sql = "DELETE FROM producto WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,id);
        statement.executeUpdate();
        statement.close();
    }


}
