package services;

import dao.VideojuegoDAO;
import dao.HistoriaDAO;
import models.Videojuego;
import models.Historia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VideojuegoService {
    private final VideojuegoDAO videojuegoDAO;
    private final HistoriaDAO historiaDAO;

    public VideojuegoService(Connection conn) {
        this.videojuegoDAO = new VideojuegoDAO(conn);
        this.historiaDAO = new HistoriaDAO(conn);
    }

    public void insertarConHistoria(Videojuego videojuego, Historia historia) throws SQLException {
        if (videojuego.getTitulo() == null || videojuego.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("El título del videojuego no puede estar vacío.");
        }

        try {
            // Usar el connection del DAO
            videojuegoDAO.getConn().setAutoCommit(false);

            // Insertar videojuego y obtener su ID
            int idVideojuego = videojuegoDAO.insertar(videojuego);

            // Asignar el ID a la historia
            historia.setId_videojuego(idVideojuego);

            // Insertar historia
            historiaDAO.insertar(historia);

            // Commit
            videojuegoDAO.getConn().commit();
        } catch (SQLException e) {
            videojuegoDAO.getConn().rollback();
            throw e;
        } finally {
            videojuegoDAO.getConn().setAutoCommit(true);
        }
    }


    public List<Videojuego> listar() throws SQLException {
        return videojuegoDAO.listar();
    }

    public void eliminar(int id) throws SQLException {
        videojuegoDAO.eliminar(id);
    }
    public void modificarTitulo(int id, String nuevoTitulo) throws SQLException {
        if (nuevoTitulo == null || nuevoTitulo.isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        videojuegoDAO.modificarTitulo(nuevoTitulo, id);
    }

    public void modificarAnio(int id, int nuevoAnio) throws SQLException {
        if (nuevoAnio <= 0) {
            throw new IllegalArgumentException("El año debe ser mayor que 0.");
        }
        videojuegoDAO.modificarAnio(nuevoAnio, id);
    }

    public void modificarPlataforma(int id, String nuevaPlataforma) throws SQLException {
        if (nuevaPlataforma == null || nuevaPlataforma.isEmpty()) {
            throw new IllegalArgumentException("La plataforma no puede estar vacía.");
        }
        videojuegoDAO.modificarPlataforma(nuevaPlataforma, id);
    }
}
