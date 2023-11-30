package com.example.ops;

import com.example.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeOPS {
    private String jdbcURL = "jdbc:mysql://localhost:3306/employees?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String SELECT_ALL_USERS = "SELECT * FROM employee";
    private static final String INSERT_RECORD = "INSERT INTO employee (fname, lname, company, email, role, dept) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM employee WHERE id=?";
    private static final String UPDATE_USER = "UPDATE employee SET fname=?, lname=?, company=?, email=?, role=?, dept=? WHERE id=?";
    private static final String DELETE_USER = "DELETE FROM employee WHERE id=?";


    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public List<Employee> searchRecords(String searchQuery) {
        List<Employee> users = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS + " WHERE fname LIKE ? OR lname LIKE ? OR company LIKE ? OR email LIKE ? OR role LIKE ? OR dept LIKE ?")) {

            String searchPattern = "%" + searchQuery + "%";
            for (int i = 1; i <= 6; i++) {
                preparedStatement.setString(i, searchPattern);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String fname = resultSet.getString("fname");
                    String lname = resultSet.getString("lname");
                    String company = resultSet.getString("company");
                    String email = resultSet.getString("email");
                    String role = resultSet.getString("role");
                    String dept = resultSet.getString("dept");

                    Employee user = new Employee(id, fname, lname, company, email, role, dept);
                    users.add(user);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    public List<Employee> getAllEmployees() {
        List<Employee> users = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fname = resultSet.getString("fname");
                String lname = resultSet.getString("lname");
                String company = resultSet.getString("company");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                String dept = resultSet.getString("dept");


                Employee user = new Employee(id, fname, lname, company, email, role, dept);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void insertEmployee(Employee employee) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECORD)) {

            preparedStatement.setString(1, employee.getFname());
            preparedStatement.setString(2, employee.getLname());
            preparedStatement.setString(3, employee.getCompany());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setString(5, employee.getRole());
            preparedStatement.setString(6, employee.getDept());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployeeById(int id) {
        Employee user = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String fname = resultSet.getString("fname");
                    String lname = resultSet.getString("lname");
                    String company = resultSet.getString("company");
                    String email = resultSet.getString("email");
                    String role = resultSet.getString("role");
                    String dept = resultSet.getString("dept");

                    user = new Employee(id, fname, lname, company, email, role, dept);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean updateEmployee(Employee user) {
        boolean rowUpdated = false;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {

            preparedStatement.setString(1, user.getFname());
            preparedStatement.setString(2, user.getLname());
            preparedStatement.setString(3, user.getCompany());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.setString(6, user.getDept());
            preparedStatement.setInt(7, user.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowUpdated;
    }

    public boolean deleteEmployee(int id) {
        boolean rowDeleted = false;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {

            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowDeleted;
    }
    public boolean registerUser(String username, String password) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (username, password) VALUES (?, ?)")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
