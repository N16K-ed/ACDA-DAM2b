package Refuerzo2_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UT2_2_A_ConexionBD {

    private static UT2_2_A_ConexionBD instancia;

    private Connection conexion;

    private static final String URL = "jdbc:mysql://localhost:3306/ACDA_UT2";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private UT2_2_A_ConexionBD(){
        try{
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión creada");
        }catch (SQLException e){
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }

    public static UT2_2_A_ConexionBD getInstancia(){
        if (instancia == null){
            instancia = new UT2_2_A_ConexionBD();
        }
        return instancia;
    }

    public Connection getConexion(){
        return conexion;
    }
}
