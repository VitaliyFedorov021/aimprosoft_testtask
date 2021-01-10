<%@ page import="ua.com.model.Department" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.com.services.DepartmentService" %>
<%@ page import="ua.com.services.impl.DepartmentServiceImpl" %>
<%@ page import="ua.com.model.Worker" %>
<%@ page import="ua.com.services.WorkerService" %>
<%@ page import="ua.com.services.impl.WorkerServiceImpl" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 10.01.2021
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<%
    WorkerService workerService = new WorkerServiceImpl();
    Integer id = Integer.parseInt(request.getParameter("workerId"));
    Worker worker = workerService.selectWorkerById(id);
    request.setAttribute("work", worker);
    DepartmentService departmentService = new DepartmentServiceImpl();
    List<Department> departmentList = departmentService.selectAllDepartments();
    request.setAttribute("deps", departmentList);
%>
<form action="MyServlet" method="get">
    <label>Login:</label>
    <input type="text" name="login" value="${work.login}"><br>
    <label>First name:</label>
    <input type="text" name="firstName" value="${work.firstName}"><br>
    <label>Last name:</label>
    <input type="text" name="lastName" value="${work.lastName}"><br>
    <label>Birthday date(IN FORMAT dd.mm.yyyy)</label>
    <input type="text" name="birthdayDate" value="${work.birthdayDate}"><br>
    <label>Email</label>
    <input type="text" name="email" value="${work.email}"><br>
    <label>Select department:</label>

        <table border="3" width="2">
            <tr>
                <td></td>
                <td>Name</td>
                <td>Address</td>
            </tr>
            <c:forEach items="${deps}" var="d">
                <tr>
                    <td><input type="radio" name="departmentId" value="${d.id}" checked="${work.departmentId}"></td>
                    <td>${d.name}</td>
                    <td>${d.address}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <input type="submit" value="submit"> <input type="reset" value="reset">
    <input type="hidden" name="jspname" value="update.jsp" />
</form>

</body>
</html>
