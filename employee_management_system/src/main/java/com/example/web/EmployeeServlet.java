package com.example.web;

import com.example.entity.Employee;
import com.example.ops.EmployeeOPS;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet({"/employee-list", "/new", "/insert", "/edit", "/update", "/delete", "/login", "/logout", "/register"})
public class EmployeeServlet extends HttpServlet {
    private EmployeeOPS ops;
    public void init() {
        ops = new EmployeeOPS();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteEmployee(request, response);
                    break;
                case "/employee-list":
                    listEmployees(request, response);
                    break;
                case "/login":
                    showLoginForm(request, response);
                    break;
                case "/logout":
                    logoutUser(request, response);
                    break;
                case "/register":
                    showRegisterForm(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath());
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/insert":
                    insertEmployee(request, response);
                    break;
                case "/update":
                    updateEmployee(request, response);
                    break;
                case "/login":
                    loginUser(request, response);
                    break;
                case "/employee-list":
                    listEmployees(request, response);
                    break;
                case "/logout":
                    logoutUser(request, response);
                    break;
                case "/register":
                    registerUser(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath());
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
    private void showLoginForm(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
    private void logoutUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login");
    }
    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isValidLogin = isValidUser(username, password);

        if (isValidLogin) {
            request.getSession().setAttribute("loggedInUser", username);
            response.sendRedirect("employee-list?loginSuccess=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/login?loginError=true");
        }
    }


    private boolean isValidUser(String username, String password) {
        try (Connection connection = ops.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username=? AND password=?")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void listEmployees(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String searchQuery = request.getParameter("searchQuery");
        List<Employee> employeeList;

        if (searchQuery != null && !searchQuery.isEmpty()) {
            employeeList = ops.searchRecords(searchQuery);
        } else {
            employeeList = ops.getAllEmployees();
        }

        request.setAttribute("listEmployee", employeeList);
        request.getRequestDispatcher("employee-list.jsp").forward(request, response);

    }


    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.getRequestDispatcher("user-form.jsp").forward(request, response);
    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String company = request.getParameter("company");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String dept = request.getParameter("dept");

        Employee newUser = new Employee(fname, lname, company, email, role, dept);

        ops.insertEmployee(newUser);
        response.sendRedirect("employee-list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee existingUser = ops.getEmployeeById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String company = request.getParameter("company");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String dept = request.getParameter("dept");

        Employee updatedUser = new Employee(id, fname, lname, company, email, role, dept);
        ops.updateEmployee(updatedUser);
        response.sendRedirect("employee-list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        ops.deleteEmployee(id);
        response.sendRedirect("employee-list?deleteSuccess=true");
    }
    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isRegistered = ops.registerUser(username, password);

        if (isRegistered) {
            response.sendRedirect(request.getContextPath() + "/login?registrationSuccess=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/register?registrationError=true");
        }
    }
}
