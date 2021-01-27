<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: owners
  Date: 26.12.2020
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tariff list</title>
</head>
<body>
<h1 align="center"> ТАРИФИ ІНТЕРНЕТУ</h1>

    <table border="1" cellpadding="20%" align="center" bordercolor=#82E830>
        <tr>
            <th>Номер</th>
            <th>Назва</th>
            <th>Ціна</th>
        </tr>
        <c:forEach var="tariff" items="${tarifflist}">
            <tr>
                <td> ${tariff.id}</td>
                <td> ${tariff.name}</td>
                <td> ${tariff.price}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
