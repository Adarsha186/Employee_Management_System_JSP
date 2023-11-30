<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Employee Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script>
        function confirmDelete() {
            return confirm("Are you sure you want to delete this employee?");
        }
    </script>
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: coral">
        <div>
            <a href="<%=request.getContextPath()%>/employee-list" class="navbar-brand"> Employee Management Application </a>
        </div>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/new" class="nav-link">Add New Employee</a></li>
        </ul>
        <div class="navbar-text">
            <a class="mb-0">Hello <strong><%= request.getSession().getAttribute("loggedInUser") %></strong></a>
        </div>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/logout">Logout</a>
            </li>
        </ul>
    </nav>
</header>

<br>

<div class="container">
    <% if (request.getParameter("loginSuccess") != null) { %>
    <div class="alert alert-success" role="alert">
        Login successful!
    </div>
    <% } else if (request.getParameter("deleteSuccess") != null) { %>
    <div class="alert alert-success" role="alert">
        Employee deleted successfully!
    </div>
    <% } %>
    <h3 class="text-center">List of Employees</h3>
    <hr>
    <div class="row mb-3">
        <div class="col-md-6 offset-md-3">
            <form class="form-inline" action="<%=request.getContextPath()%>/employee-list" method="get">
                <div class="form-group mx-sm-3 mb-2">
                    <label for="searchQuery" class="sr-only">Search</label>
                    <input type="text" class="form-control" id="searchQuery" name="searchQuery" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-primary mb-2">Search</button>
            </form>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Company</th>
            <th>Email</th>
            <th>Role</th>
            <th>Department</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="employee" items="${listEmployee}">
            <tr>
                <td><c:out value="${employee.id}" /></td>
                <td><c:out value="${employee.fname}" /></td>
                <td><c:out value="${employee.lname}" /></td>
                <td><c:out value="${employee.company}" /></td>
                <td><c:out value="${employee.email}" /></td>
                <td><c:out value="${employee.role}" /></td>
                <td><c:out value="${employee.dept}" /></td>
                <td>
                    <a href="<%=request.getContextPath()%>/edit?id=${employee.id}" class="btn btn-warning">Edit</a>
                    <a href="<%=request.getContextPath()%>/delete?id=${employee.id}" class="btn btn-danger" onclick="return confirmDelete();">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
