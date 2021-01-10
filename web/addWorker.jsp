<%@ page import="ua.com.model.Department" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.com.services.DepartmentService" %>
<%@ page import="ua.com.services.impl.DepartmentServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 09.01.2021
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Adding worker</title>
</head>
<body>
<% DepartmentService departmentService = new DepartmentServiceImpl();
    List<Department> departments = departmentService.selectAllDepartments();
    request.setAttribute("deps", departments);
%>

    <form action="MyServlet" method="post">
        <label>Login:</label>
        <input type="text" name="login"><br>
        <label>First name:</label>
        <input type="text" name="firstName"><br>
        <label>Last name:</label>
        <input type="text" name="lastName"><br>
        <label>Birthday date(IN FORMAT dd.mm.yyyy)</label>
        <input type="text" name="birthdayDate"><br>
        <label>Email</label>
        <input type="text" name="email"><br>
        <label>Select department:</label>
        <div>
            <table border="3" width="2">
                <tr>
                    <td></td>
                    <td>Name</td>
                    <td>Address</td>
                </tr>
                <c:forEach items="${deps}" var="d">
                    <tr>
                        <td><input type="radio" name="departmentId" value="${d.id}"></td>
                        <td>${d.name}</td>
                        <td>${d.address}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <input type="submit" value="submit"> <input type="reset" value="reset">
        <input type="hidden" name="jspname" value="addWorker.jsp" />
    </form>
</body>
</html>
