<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 10.01.2021
  Time: 00:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add department</title>
</head>
<body>
    <form action="MyServlet" method="post">
        <label>Name:</label>
        <input type="text" name="name"><br>
        <label>Address:</label>
        <input type="text" name="address"><br>
        <input type="submit" value="submit"> <input type="reset" value="reset">
        <input type="hidden" name="jspname" value="addDepartment.jsp"/>
    </form>
</body>
</html>
