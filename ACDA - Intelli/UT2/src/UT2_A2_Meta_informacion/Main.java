package UT2_A2_Meta_informacion;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        ConexionBD con1 = ConexionBD.getInstancia();
        Connection conexion1 = con1.getConexion();
        if (conexion1 == null){
            System.err.println("No se pudo establecer la conexión a la BBDD. Saliendo...");
            return;
        }

        try{
            DatabaseMetaData dbMetaData = conexion1.getMetaData();
            mostrarTablas(dbMetaData);
        } catch (SQLException e) {
            System.err.println("Ha ocurrido un error al obtener los metadatos: " + e.getMessage());
        }


    }

    public static void mostrarTablas(DatabaseMetaData dbMetadata) throws SQLException{
        ResultSet tablas = dbMetadata.getTables(null, null, "%", new String[]{"TABLE"});
        while(tablas.next()){
            String tabla = tablas.getString("TABLE_NAME");
            System.out.println("TABLA: " + tabla);
            mostrarColumnas(dbMetadata, tabla);
            mostrarClavesPrimarias(dbMetadata, tabla);
            mostrarClavesAjenas(dbMetadata,tabla);
            System.out.println();
        }

    }

    /**
     * Muestra las columnas de una tabla con su nombre y tipo de dato.
     *
     * @param dbMetaData objeto DatabaseMetaData
     * @param tabla nombre de la tabla
     * @throws SQLException si ocurre un error en la consulta de metadatos
     */
    private static void mostrarColumnas(DatabaseMetaData dbMetaData, String tabla) throws SQLException {
        ResultSet rsColumnas = dbMetaData.getColumns(
                null,
                null,
                tabla,
                null);

        while (rsColumnas.next()) {
            System.out.println("\t" + rsColumnas.getString("COLUMN_NAME") +
                    ": " + rsColumnas.getString("TYPE_NAME"));
        }
    }

    /**
     * Muestra las claves primarias de una tabla.
     *
     * @param dbMetaData objeto DatabaseMetaData
     * @param tabla nombre de la tabla
     * @throws SQLException si ocurre un error en la consulta de metadatos
     */
    private static void mostrarClavesPrimarias(DatabaseMetaData dbMetaData, String tabla) throws SQLException {
        ResultSet rsPK = dbMetaData.getPrimaryKeys(
                null,
                null,
                tabla);

        System.out.println("\t\tClaves Primarias:");
        while (rsPK.next()) {
            System.out.println("\t\t\t" + rsPK.getString("COLUMN_NAME"));
        }
    }

    /**
     * Muestra las claves foráneas de una tabla, indicando la columna y la tabla a la que apuntan.
     *
     * @param dbMetaData objeto DatabaseMetaData
     * @param tabla nombre de la tabla
     * @throws SQLException si ocurre un error en la consulta de metadatos
     */
    private static void mostrarClavesAjenas(DatabaseMetaData dbMetaData, String tabla) throws SQLException {
        ResultSet rsFK = dbMetaData.getImportedKeys(
                null,
                null,
                tabla);

        System.out.println("\t\tClaves Ajenas:");
        while (rsFK.next()) {
            System.out.println("\t\t\t" + rsFK.getString("FKCOLUMN_NAME") +
                    " --> " + rsFK.getString("PKTABLE_NAME"));
        }
    }
}
