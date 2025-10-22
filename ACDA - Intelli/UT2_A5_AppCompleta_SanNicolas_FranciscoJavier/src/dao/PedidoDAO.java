package dao;

import models.DetallePedido;
import models.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PedidoDAO extends GenericDAO  {
    public PedidoDAO(Connection conn) {
        super(conn);
    }

    public int insertar(Pedido p) throws SQLException {
        String sql = "INSERT INTO pedido (fecha, id_cliente) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
        stmt.setDate(1, new java.sql.Date(p.getFecha().getTime()));
        stmt.setInt(2, p.getId_cliente());
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1); // devuelve el ID generado automáticamente
        } else {
            throw new SQLException("No se pudo obtener el ID del pedido insertado");
        }
    }

    public void insertarDetalle(DetallePedido detalle) throws SQLException {
        String sql = "INSERT INTO detalle_pedido (id_pedido, id_videojuego, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, detalle.getId_pedido());
        stmt.setInt(2, detalle.getId_videojuego());
        stmt.setInt(3, detalle.getCantidad());
        stmt.setDouble(4, detalle.getPrecio_unitario());
        stmt.executeUpdate();
    }

    public Map<Pedido, List<DetallePedido>> listarPedidosConDetalles() throws SQLException {
        Map<Pedido, List<DetallePedido>> pedidosConDetalles = new LinkedHashMap<>();

        String sql = """
        SELECT p.id AS pedido_id, p.fecha, p.id_cliente,
               dp.id_videojuego, dp.cantidad, dp.precio_unitario
        FROM pedido p
        LEFT JOIN detalle_pedido dp ON p.id = dp.id_pedido
        ORDER BY p.id
        """;

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        Pedido pedidoActual = null;
        int ultimoId = -1;

        while (rs.next()) {
            int idPedido = rs.getInt("pedido_id");

            if (idPedido != ultimoId) {
                ultimoId = idPedido;
                pedidoActual = new Pedido(
                        idPedido,
                        rs.getDate("fecha"),
                        rs.getInt("id_cliente")
                );
                pedidosConDetalles.put(pedidoActual, new ArrayList<>());
            }

            int idVideojuego = rs.getInt("id_videojuego");
            if (!rs.wasNull()) {
                DetallePedido detalle = new DetallePedido(
                        idPedido,
                        idVideojuego,
                        rs.getInt("cantidad"),
                        rs.getDouble("precio_unitario")
                );
                pedidosConDetalles.get(pedidoActual).add(detalle);
            }
        }

        return pedidosConDetalles;
    }

}
