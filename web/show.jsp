<%@ page import="ua.com.services.WorkerService" %>
<%@ page import="ua.com.services.impl.WorkerServiceImpl" %>
<%@ page import="ua.com.services.DepartmentService" %>
<%@ page import="ua.com.services.impl.DepartmentServiceImpl" %>
<%@ page import="ua.com.model.Worker" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.com.model.Department" %><%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 10.01.2021
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show info</title>
</head>
<body>
    <%WorkerService workerService = new WorkerServiceImpl();
        DepartmentService departmentService = new DepartmentServiceImpl();
        List<Worker> workers = workerService.selectAllWorkers();
        List<Department> departments = departmentService.selectAllDepartments();
        request.setAttribute("workers", workers);
        request.setAttribute("departments", departments);
    %>
    <div>
        <form action="MyServlet" method="get">
            <label>Workers:</label>
            <table border="2" width="3">
                <tr>
                    <td></td>
                    <td>login</td>
                    <td>first name</td>
                    <td>last name</td>
                    <td>department id</td>
                    <td>email</td>
                    <td>birthday date</td>
                </tr>
                <c:forEach items="${workers}" var="w">
                    <tr>
                        <td><input type="radio" name="workerId" value="${w.id}"></td>
                        <td>${w.login}</td>
                        <td>${w.firstName}</td>
                        <td>${w.lastName}</td>
                        <td>${w.departmentId}</td>
                        <td>${w.email}</td>
                        <td>${w.birthdayDate}</td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" name="delete" value="Delete">
            <input type="submit" name="update" value="Update">
        </form>
    </div>
</body>
</html>
