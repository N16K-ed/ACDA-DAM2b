package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {

    public static String url = "jdbc:sqlite:./src/files/app.db"; //A cambiar

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(url)) {
            if(con != null){

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void crearTabla(Connection con) throws SQLException{
        try(Statement statement = con.createStatement()){
            statement.execute("CREATE TABLE IF NOT EXISTS Productos (\n" +
                    "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  nombre TEXT NOT NULL,\n" +
                    "  precio NUMERIC NOT NULL CHECK(precio >= 0)\n" +
                    ");");
            System.out.println("Tabla 'Productos' creada con éxito.");
        }
    }

    public static void listarProductos(Connection con) throws  SQLException{
        try(Statement statement = con.createStatement()){
            statement.execute("");


        }
    }

}