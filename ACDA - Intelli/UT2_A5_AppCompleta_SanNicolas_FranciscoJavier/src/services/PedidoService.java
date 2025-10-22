package services;

import dao.PedidoDAO;
import models.DetallePedido;
import models.Pedido;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PedidoService {
    private final PedidoDAO pedidoDAO;

    public PedidoService(Connection conn) {
        this.pedidoDAO = new PedidoDAO(conn);
    }
    // Inserción de pedido con validaciones
    public void insertarPedidoConDetalles(Pedido pedido, List<DetallePedido> detalles) throws SQLException {
        if (pedido.getId_cliente() <= 0) {
            throw new IllegalArgumentException("El pedido debe tener un cliente válido.");
        }
        if (detalles == null || detalles.isEmpty()) {
            throw new IllegalArgumentException("El pedido debe contener al menos un videojuego.");
        }

        try {
            pedidoDAO.getConn().setAutoCommit(false);

            // Insertar pedido y obtener el ID generado
            int idPedido = pedidoDAO.insertar(pedido);

            // Asignar el ID del pedido a cada detalle
            for (DetallePedido detalle : detalles) {
                detalle.setId_pedido(idPedido);
                pedidoDAO.insertarDetalle(detalle);
            }

            pedidoDAO.getConn().commit();
        } catch (SQLException e) {
            pedidoDAO.getConn().rollback();
            throw e;
        } finally {
            pedidoDAO.getConn().setAutoCommit(true);
        }
    }

    public Map<Pedido, List<DetallePedido>> listarPedidosConDetalles() throws SQLException {
        return pedidoDAO.listarPedidosConDetalles();
    }
}
