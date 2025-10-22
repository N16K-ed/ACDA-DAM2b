package dao;

import models.Historia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoriaDAO extends GenericDAO{
    public HistoriaDAO(Connection conn) {
        super(conn);
    }

    public void insertar(Historia h) throws SQLException {
        String sql = "INSERT INTO historia (descripcion, id_videojuego) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, h.getDescripcion());
        stmt.setInt(2, h.getId_videojuego());
        stmt.executeUpdate();
    }

}
