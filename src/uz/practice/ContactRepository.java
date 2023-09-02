package uz.practice;

import uz.practice.utils.DBUtils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ContactRepository {

    public List<Contact> getList() {
        List<Contact> response = new LinkedList<>();
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from contact;");
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setName(resultSet.getString("name"));
                contact.setSurname(resultSet.getString("surname"));
                contact.setPhone(resultSet.getString("phone"));
                response.add(contact);
            }
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
        return response;
    }

    public Contact getByPhone(String phone) {
        Contact response = null;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            String sql = "select * from contact where phone = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setName(resultSet.getString("name"));
                contact.setSurname(resultSet.getString("surname"));
                contact.setPhone(resultSet.getString("phone"));
                response = contact;
            }
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
        return response;
    }

    public boolean save(Contact contact) {
        boolean response = false;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            String sql = "insert into contact(name, surname, phone) values(?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurname());
            preparedStatement.setString(3, contact.getPhone());
            preparedStatement.executeUpdate();
            response = true;
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
        return response;
    }

    public int delete(String phone) {
        int response = 0;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            String sql = "delete from contact where phone = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            response = preparedStatement.executeUpdate();
            ;
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
        return response;
    }

    public List<Contact> search(String query) {
        List<Contact> response = new LinkedList<>();
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            String sql = "select * from contact where lower(name) like ? or lower(surname) like ? or phone like ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            String param = "%" + query.toLowerCase() + "%";
            preparedStatement.setString(1, param);
            preparedStatement.setString(2, param);
            preparedStatement.setString(3, param);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setName(resultSet.getString("name"));
                contact.setSurname(resultSet.getString("surname"));
                contact.setPhone(resultSet.getString("phone"));
                response.add(contact);
            }
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
        return response;
    }
}
