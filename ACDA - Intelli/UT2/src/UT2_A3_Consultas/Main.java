package UT2_A3_Consultas;

import UT2_A3_Consultas.ConexionBD;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        UT2_A3_Consultas.ConexionBD con1 = ConexionBD.getInstancia();
        Connection conexion1 = con1.getConexion();
        if (conexion1 == null){
            System.err.println("No se pudo establecer la conexión a la BBDD. Saliendo...");
            return;
        }
        try{
            menuPrincipal(conexion1);

        } catch (SQLException e) {
            System.err.println("Ha ocurrido un error: " + e.getMessage());
        }
    }

    public static void menuPrincipal(Connection connection) throws SQLException{
        mostrarMenu();
        int opcion = teclado.nextInt();
        while (opcion > 5 || opcion < 1){
            System.out.println();
            System.out.println("Elige una opción válida");
            opcion = teclado.nextInt();
        }

        switch (opcion){
            case 1:
                insertarMoto(connection);
                menuPrincipal(connection);
                break;
            case 2:
                listarMotos(connection);
                menuPrincipal(connection);
                break;
            case 3:
                actualizarPrecio(connection);
                menuPrincipal(connection);
                break;
            case 4:
                eliminarMoto(connection);
                menuPrincipal(connection);
                break;
            case 5:
                break;
        }
    }

    public static void mostrarMenu(){
        System.out.println("**********************************");
        System.out.println("*********Elige una opción*********");
        System.out.println("**********************************");
        System.out.println("1- Insertar nueva Moto");
        System.out.println("2- Mostrar almacén de Motos");
        System.out.println("3- Actualizar precio de Moto");
        System.out.println("4- Eliminar Moto del almacén");
        System.out.println("5- Salir");
        System.out.print("Opción: ");
    }

    public static void insertarMoto(Connection connection) throws SQLException{
        System.out.println("Introduzca la MARCA de la nueva moto: (OBLIGATORIO)");
        String marca = teclado.nextLine();
        System.out.println("Introduzca el MODELO de la nueva moto: (OPCIONAL)");
        String modelo = teclado.nextLine();
        System.out.println("Introduzca la CILINDRADA de la nueva moto: (OPCIONAL)");
        int cilindrada = teclado.nextInt();
        System.out.println("Introduzca el PRECIO de la nueva moto: (Formato %.%)(OPCIONAL)");
        double precio = teclado.nextDouble();

        String sql = "INSERT INTO motos (marca, modelo, cilindrada, precio) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, marca);
        ps.setString(2, modelo);
        ps.setInt(3, cilindrada);
        ps.setDouble(4, precio);
        ps.executeUpdate();
    }

    public static void listarMotos(Connection connection) throws SQLException{
        String sql = "SELECT * FROM motos";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()) {
            System.out.println();
            System.out.println("ID - " + rs.getInt("id") +
                               "\nMARCA - " + rs.getString("marca") +
                               "\nMODELO - " + rs.getString("modelo")  +
                               "\nCILINDRADA - " + rs.getInt("cilindrada") +
                               "\nPRECIO - " + rs.getDouble("precio"));
            System.out.println();
        }
    }

    public static void actualizarPrecio(Connection connection){

    }

    public static void eliminarMoto(Connection connection){

    }
}
