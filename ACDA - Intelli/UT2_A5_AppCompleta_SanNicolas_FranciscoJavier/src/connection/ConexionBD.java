package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static ConexionBD instancia;
    private Connection conexion;

    private static final String DB = "ACDA_UT2";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB;
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private ConexionBD() {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión creada");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }

    public static ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

}
