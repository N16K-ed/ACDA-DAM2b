import connection.ConexionBD;
import dao.ClienteDAO;
import models.*;
import services.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConexionBD.getInstancia().getConexion()) {
            if (conn == null) {
                System.err.println("No se pudo conectar a la base de datos.");
                return;
            }

            VideojuegoService videojuegoService = new VideojuegoService(conn);
            ClienteService clienteService = new ClienteService(conn);
            PedidoService pedidoService = new PedidoService(conn);

            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("=== MENÚ PRINCIPAL ===");
                System.out.println("1. Alta de videojuego");
                System.out.println("2. Baja de videojuego");
                System.out.println("3. Modificación de videojuego");
                System.out.println("4. Listado de videojuegos");
                System.out.println("5. Alta de cliente");
                System.out.println("6. Baja de cliente");
                System.out.println("7. Modificación de cliente");
                System.out.println("8. Listado de clientes");
                System.out.println("9. Crear pedido");
                System.out.println("10. Visualizar pedidos con detalles");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (opcion) {
                    case 1 -> {
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Año: ");
                        int anio = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Plataforma: ");
                        String plataforma = scanner.nextLine();
                        System.out.print("Historia: ");
                        String descripcion = scanner.nextLine();

                        Videojuego videojuego = new Videojuego(titulo, anio, plataforma);
                        Historia historia = new Historia(descripcion, 0);

                        try {
                            videojuegoService.insertarConHistoria(videojuego, historia);
                            System.out.println("Videojuego y su historia insertados correctamente.");
                        } catch (SQLException | IllegalArgumentException e) {
                            System.err.println("Error al insertar el videojuego: " + e.getMessage());
                        }
                    }
                    case 2 -> {
                        System.out.print("ID del videojuego a eliminar: ");
                        int id = scanner.nextInt();
                        try {
                            videojuegoService.eliminar(id);
                            System.out.println("Videojuego eliminado correctamente.");
                        } catch (SQLException e) {
                            System.err.println("Error al eliminar el videojuego: " + e.getMessage());
                        }
                    }
                    case 3 -> {
                        System.out.print("ID del videojuego a modificar: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consumir salto de línea

                        System.out.print("Nuevo título: ");
                        String nuevoTitulo = scanner.nextLine();

                        System.out.print("Nuevo año: ");
                        int nuevoAnio = scanner.nextInt();
                        scanner.nextLine(); // Consumir salto de línea

                        System.out.print("Nueva plataforma: ");
                        String nuevaPlataforma = scanner.nextLine();

                        try {
                            videojuegoService.modificarTitulo(id, nuevoTitulo);
                            videojuegoService.modificarAnio(id, nuevoAnio);
                            videojuegoService.modificarPlataforma(id, nuevaPlataforma);
                            System.out.println("Videojuego modificado correctamente.");
                        } catch (SQLException | IllegalArgumentException e) {
                            System.err.println("Error al modificar el videojuego: " + e.getMessage());
                        }
                    }
                    case 4 -> {
                        try {
                            List<Videojuego> lista = videojuegoService.listar();
                            if (lista.isEmpty()) System.out.println("No hay videojuegos.");
                            else lista.forEach(v -> System.out.println(v.getId() + ": " + v.getTitulo() + " - " + v.getAnio()+ " - " + v.getPlataforma()));
                        } catch (SQLException e) {
                            System.err.println("Error al listar videojuegos: " + e.getMessage());
                        }
                    }
                    case 5 -> {
                        System.out.print("Nombre del cliente: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Correo: ");
                        String correo = scanner.nextLine();

                        Cliente cliente = new Cliente(nombre, correo); // solo nombre y correo
                        try {
                            clienteService.insertar(cliente);
                            System.out.println("Cliente insertado correctamente.");
                        } catch (SQLException | IllegalArgumentException e) {
                            System.err.println("Error al insertar el cliente: " + e.getMessage());
                        }
                    }
                    case 6 -> {
                        System.out.print("ID del cliente a eliminar: ");
                        int id = scanner.nextInt();
                        try {
                            clienteService.eliminar(id);
                            System.out.println("Cliente eliminado correctamente.");
                        } catch (SQLException e) {
                            System.err.println("Error al eliminar el cliente: " + e.getMessage());
                        }
                    }
                    case 7 -> {
                        System.out.print("ID del cliente a modificar: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // consumir salto de línea
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nuevo correo: ");
                        String nuevoCorreo = scanner.nextLine();
                        try {
                            clienteService.modificarNombre(id, nuevoNombre);
                            clienteService.modificarCorreo(id, nuevoCorreo);
                            System.out.println("Cliente modificado correctamente.");
                        } catch (SQLException | IllegalArgumentException e) {
                            System.err.println("Error al modificar el cliente: " + e.getMessage());
                        }
                    }
                    case 8 -> {
                        try {
                            List<Cliente> clientes = clienteService.listar();
                            if (clientes.isEmpty()) System.out.println("No hay clientes.");
                            else clientes.forEach(c -> System.out.println(c.getId() + ": " + c.getNombre() + " - " + c.getCorreo()));
                        } catch (SQLException e) {
                            System.err.println("Error al listar clientes: " + e.getMessage());
                        }
                    }
                    case 9 -> {
                        try {
                            System.out.print("ID del cliente: ");
                            int idCliente = scanner.nextInt();
                            scanner.nextLine();

                            List<DetallePedido> detalles = new ArrayList<>();
                            boolean agregar = true;
                            while (agregar) {
                                System.out.print("ID del videojuego: ");
                                int idVideojuego = scanner.nextInt();
                                System.out.print("Cantidad: ");
                                int cantidad = scanner.nextInt();
                                System.out.print("Precio unitario: ");
                                double precio = scanner.nextDouble();
                                scanner.nextLine();

                                detalles.add(new DetallePedido(0, idVideojuego, cantidad, precio));

                                System.out.print("¿Agregar otro videojuego al pedido? (s/n): ");
                                String resp = scanner.nextLine();
                                agregar = resp.equalsIgnoreCase("s");
                            }

                            Pedido pedido = new Pedido(new java.util.Date(), idCliente);
                            pedidoService.insertarPedidoConDetalles(pedido, detalles);

                            System.out.println("Pedido creado correctamente.");
                        } catch (SQLException | IllegalArgumentException e) {
                            System.err.println("Error al crear el pedido: " + e.getMessage());
                        }
                    }
                    case 10 -> {
                        try {
                            // Inicializar DAO de clientes una sola vez
                            ClienteDAO clienteDAO = new ClienteDAO(ConexionBD.getInstancia().getConexion());

                            Map<Pedido, List<DetallePedido>> pedidos = pedidoService.listarPedidosConDetalles();
                            for (Pedido pedido : pedidos.keySet()) {
                                Cliente cliente = clienteDAO.obtenerPorId(pedido.getId_cliente());
                                System.out.println("Id Pedido = " + pedido.getId());
                                System.out.println("Cliente = " + (cliente != null ? cliente.getNombre() : "Desconocido"));

                                for (DetallePedido detalle : pedidos.get(pedido)) {
                                    System.out.println("  Id videojuego: " + detalle.getId_videojuego() +
                                            " - Cantidad: " + detalle.getCantidad() +
                                            " - Precio unitario: " + detalle.getPrecio_unitario());
                                }
                                System.out.println();
                            }
                        } catch (SQLException e) {
                            System.err.println("Error al listar pedidos con detalles: " + e.getMessage());
                        }
                    }
                    case 0 -> System.out.println("Saliendo del programa...");
                    default -> System.out.println("Opción no válida.");
                }
            } while (opcion != 0);

        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
