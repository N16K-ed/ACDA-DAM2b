package Refuerzo2_5;

import Refuerzo2_5.connection.ConexionBD;
import Refuerzo2_5.dao.ProductoDAO;
import Refuerzo2_5.models.Producto;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal(){
        ProductoDAO pDAO = new ProductoDAO(ConexionBD.getInstancia().getConexion());

        mostrarMenu();
        int opcion = teclado.nextInt();
        while (opcion > 4 || opcion < 1){
            System.out.print("\nElige una opción válida: ");
            opcion = teclado.nextInt();
        }

        try{
            switch (opcion){
                case 1:
                    listarMenu(pDAO);
                    menuPrincipal();
                    break;
                case 2:
                    introducirMenu(pDAO);
                    menuPrincipal();
                    break;
                case 3:
                    eliminarMenu(pDAO);
                    menuPrincipal();
                    break;
                case 4:
                    break;
            }
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void mostrarMenu(){
        System.out.println("********************************************\n" +
                           "**************ELIGE UNA OPCIÓN**************\n" +
                           "********************************************\n" +
                           "1 - Listar productos\n" +
                           "2 - Insertar producto\n" +
                           "3 - Eliminar producto\n" +
                           "4 - Salir\n");
        System.out.print("Opción: ");
    }

    public static void listarMenu(ProductoDAO pDAO) throws SQLException{
        List<Producto> productos = pDAO.listarProducto();
        System.out.println("\nListado de productos:");
        int contador = 1;
        for(Producto p : productos){
            System.out.println(contador + " - " + p.getNombre() + " - " + p.getPrecio());
            System.out.println();
            contador++;
        }
    }

    public static void introducirMenu(ProductoDAO pDAO) throws SQLException{
        System.out.println("Introduzca el nombre del producto:");
        String nombre = teclado.nextLine();
        System.out.println("Introduzca el precio del producto: (Formato: %,%)");
        double precio = teclado.nextDouble();
        pDAO.insertarProducto(new Producto(nombre, precio));
    }

    public static void eliminarMenu(ProductoDAO pDAO) throws SQLException{
        System.out.println("Introduzca el id del producto a eliminar:");
        int id = teclado.nextInt();
        pDAO.eliminarProducto(id);
    }

}
