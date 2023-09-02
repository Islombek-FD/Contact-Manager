package uz.practice.repository;

import uz.practice.dto.ContactDTO;
import uz.practice.utils.DBUtils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ContactRepository {

    public List<ContactDTO> getList() {
        List<ContactDTO> response = new LinkedList<>();
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from contact;");
            while (resultSet.next()) {
                ContactDTO contactDTO = new ContactDTO();
                contactDTO.setId(resultSet.getInt("id"));
                contactDTO.setName(resultSet.getString("name"));
                contactDTO.setSurname(resultSet.getString("surname"));
                contactDTO.setPhone(resultSet.getString("phone"));
                response.add(contactDTO);
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

    public ContactDTO getByPhone(String phone) {
        ContactDTO response = null;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            String sql = "select * from contact where phone = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ContactDTO contactDTO = new ContactDTO();
                contactDTO.setId(resultSet.getInt("id"));
                contactDTO.setName(resultSet.getString("name"));
                contactDTO.setSurname(resultSet.getString("surname"));
                contactDTO.setPhone(resultSet.getString("phone"));
                response = contactDTO;
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

    public boolean save(ContactDTO contactDTO) {
        boolean response = false;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            String sql = "insert into contact(name, surname, phone) values(?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, contactDTO.getName());
            preparedStatement.setString(2, contactDTO.getSurname());
            preparedStatement.setString(3, contactDTO.getPhone());
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

    public List<ContactDTO> search(String query) {
        List<ContactDTO> response = new LinkedList<>();
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
                ContactDTO contactDTO = new ContactDTO();
                contactDTO.setId(resultSet.getInt("id"));
                contactDTO.setName(resultSet.getString("name"));
                contactDTO.setSurname(resultSet.getString("surname"));
                contactDTO.setPhone(resultSet.getString("phone"));
                response.add(contactDTO);
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
