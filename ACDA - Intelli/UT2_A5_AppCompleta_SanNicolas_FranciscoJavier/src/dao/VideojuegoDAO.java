package dao;

import models.Videojuego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoDAO {
    private Connection conn;

    public VideojuegoDAO(Connection conn){
        this.conn = conn;
    }

    public void insertar(Videojuego v) throws SQLException {
        String sql = "INSERT INTO videojuego (titulo, anio, plataforma) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, v.getTitulo());
        stmt.setInt(2, v.getAnio());
        stmt.setString(3, v.getPlataforma());
        stmt.executeUpdate();
    }

    public void eliminar(int id) throws SQLException{
        String sql = "DELETE FROM videojuego WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public void modificarTitulo(String newTitulo, int id) throws SQLException{
        String sql = "UPDATE videojuego SET titulo = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,newTitulo);
        stmt.setInt(2,id);
        stmt.executeUpdate();
    }

    public void modificarAnio(int newAnio, int id) throws SQLException{

    }

    public void modificarPlataforma(String newPlataforma, int id) throws SQLException{

    }

    public List<Videojuego> listar() throws SQLException {
        List<Videojuego> lista = new ArrayList<>();
        String sql = "SELECT * FROM videojuego";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Videojuego v = new Videojuego(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getInt("anio"),
                    rs.getString("plataforma")
            );
            lista.add(v);
        }
        return lista;
    }
}
