package dao;

import java.sql.Connection;

public abstract class GenericDAO {
    protected Connection conn;

    public GenericDAO(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        return conn;
    }
}