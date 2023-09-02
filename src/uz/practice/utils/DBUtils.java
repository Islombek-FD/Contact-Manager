package uz.practice.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
    public static void main(String[] args) {
        createTable();
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contact_manager", "postgres", "2001");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void createDatabase() {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "create database contact_manager";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void dropDatabase() {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "drop database contact_manager";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createTable() {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "create table if not exists contact (" +
                    " id serial primary key," +
                    " name varchar(25) not null, " +
                    " surname varchar(25) not null, " +
                    " phone varchar(13) not null unique" +
                    ")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void dropTable() {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "drop table contact";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void truncateTable() {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "truncate table contact";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
