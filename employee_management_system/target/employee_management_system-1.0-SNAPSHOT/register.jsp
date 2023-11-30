<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>User Registration</title>
  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>

<header>
  <nav class="navbar navbar-expand-md navbar-dark" style="background-color: coral">
    <div>
      <a href="<%=request.getContextPath()%>/list" class="navbar-brand"> User Management Application </a>
    </div>
    <ul class="navbar-nav">
      <li><a href="<%=request.getContextPath()%>/new" class="nav-link">Add New User</a></li>
    </ul>
  </nav>
</header>

<br>

<div class="container">
  <% if (request.getParameter("registrationSuccess") != null) { %>
  <div class="alert alert-success" role="alert">
    Registration successful! Please log in.
  </div>
  <% } else if (request.getParameter("registrationError") != null) { %>
  <div class="alert alert-danger" role="alert">
    Registration failed. Please try again.
  </div>
  <% } else if (request.getParameter("loginError") != null) { %>
  <div class="alert alert-danger" role="alert">
    Invalid username or password. Please try again.
  </div>
  <% } %>
  <h3 class="text-center">User Registration</h3>
  <div class="row justify-content-center">
    <div class="col-md-4">
      <form class="form-signin my-4" action="<%=request.getContextPath()%>/register" method="post">
        <input type="text" class="form-control mb-2" placeholder="Username" name="username" required>
        <input type="password" class="form-control mb-2" placeholder="Password" name="password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
      </form>
      <p class="text-center">Already have an account? <a href="<%=request.getContextPath()%>/index.jsp">Login</a></p>
    </div>
  </div>
</div>

</body>
</html>
