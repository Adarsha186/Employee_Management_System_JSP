<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Employee Management Application - Edit User</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: coral">
        <div>
            <a href="" class="navbar-brand"> Employee Management Application </a>
        </div>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/employee-list" class="nav-link">Employees</a></li>
        </ul>
    </nav>
</header>

<br>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">

            <form action="<%=request.getContextPath()%>/update" method="post">

                <caption>
                    <h2>Edit User</h2>
                </caption>

                <input type="hidden" name="id" value="${user.id}" />

                <fieldset class="form-group">
                    <label>First Name</label>
                    <input type="text" value="${user.fname}" class="form-control" name="fname" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Last Name</label>
                    <input type="text" value="${user.lname}" class="form-control" name="lname" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Company</label>
                    <input type="text" value="${user.company}" class="form-control" name="company" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Email</label>
                    <input type="text" value="${user.email}" class="form-control" name="email" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Role</label>
                    <input type="text" value="${user.role}" class="form-control" name="role" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Department</label>
                    <select class="form-control" name="dept" required="required">
                        <option value="Engineering & Technology" ${user.dept == 'Engineering & Technology' ? 'selected' : ''}>Engineering & Technology</option>
                        <option value="Sales & Support" ${user.dept == 'Sales & Support' ? 'selected' : ''}>Sales & Support</option>
                        <option value="Marketing & Communications" ${user.dept == 'Marketing & Communications' ? 'selected' : ''}>Marketing & Communications</option>
                        <option value="Finance" ${user.dept == 'Finance' ? 'selected' : ''}>Finance</option>
                        <option value="Legal" ${user.dept == 'Legal' ? 'selected' : ''}>Legal</option>
                        <option value="Facilities" ${user.dept == 'Facilities' ? 'selected' : ''}>Facilities</option>
                        <option value="Business Strategy" ${user.dept == 'Business Strategy' ? 'selected' : ''}>Business Strategy</option>
                    </select>
                </fieldset>

                <button type="submit" class="btn btn-success">Save Changes</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
