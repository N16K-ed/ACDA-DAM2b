package Refuerzo2_2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UT2_2_A_Main {
    public static void main(String[] args) {
        UT2_2_A_ConexionBD con1 = UT2_2_A_ConexionBD.getInstancia();
        Connection conexion1 = con1.getConexion();

        try{
            Statement statement = conexion1.createStatement();

            ResultSet rs = statement.executeQuery("SELECT NOW()");
            while(rs.next()){
                System.out.println(rs.getString("now()"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
