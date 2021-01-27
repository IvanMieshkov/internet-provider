<%--
  Created by IntelliJ IDEA.
  User: owners
  Date: 22.01.2021
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<html lang="${language}">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

    <title><fmt:message key="navbar.internet"/></title>
    <style>
        .table {
            width: 40%;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light sticky-top justify-content-between"
     style="background-color: deepskyblue ">
    <a href="${pageContext.request.contextPath}/" class="navbar-brand">
        <img src="https://i.ibb.co/nR7vNFX/pngwing-com.png" width="100" height="50" alt="logo"></a>
    <a href="${pageContext.request.contextPath}/main/menu" class="navbar-brand letter"
       style="color: ghostwhite; font-size:14pt" ><fmt:message key="navbar.client.menu"/></a>
    <a href="${pageContext.request.contextPath}/main/tariffs?service=internet" class="navbar-brand letter"
       style="color: ghostwhite; font-size:14pt" ><fmt:message key="navbar.internet"/></a>
    <a href="${pageContext.request.contextPath}/main/tariffs?service=tv" class="navbar-brand letter"
       style="color: ghostwhite; font-size:14pt" ><fmt:message key="navbar.tv"/></a>
    <a href="${pageContext.request.contextPath}/main/tariffs?service=telephony" class="navbar-brand letter"
       style="color: ghostwhite; font-size:14pt" ><fmt:message key="navbar.telephony"/></a>
    <a href="${pageContext.request.contextPath}/main/logout" class="navbar-brand letter"
       style="color: ghostwhite; font-size:14pt" ><fmt:message key="navbar.logout"/></a>
    <form method="get" action="${pageContext.request.contextPath}/change-language/main/tariffs">
        <input type="hidden" name="service" value="${requestScope.service}"/>
        <label for="language"></label>
        <select id="language" name="language"
                onchange="submit()" style="font-size: 11pt; background-color: ghostwhite; color: deepskyblue">
            <option value="en" ${language == 'en' ? 'selected' : ''} style="color: deepskyblue">English</option>
            <option value="ukr" ${language == 'ukr' ? 'selected' : ''} style="color: deepskyblue">Українська</option>
        </select>
    </form>
</nav>
<br>

<table class="table" style="color: dodgerblue">
    <tbody>
    <tr>
        <th>

            <fmt:message key="title.tariff.name"/>
            <a href="${pageContext.request.contextPath}/main/tariffs?service=${requestScope.service}&sortBy=${language == 'ukr' ? 'tariff_name_ukr' : 'tariff_name_en'}&order=ASC">&#8593</a>
            <a href="${pageContext.request.contextPath}/main/tariffs?service=${requestScope.service}&sortBy=${language == 'ukr' ? 'tariff_name_ukr' : 'tariff_name_en'}&order=DESC">&#8595</a>
        </th>
        <th><fmt:message key="title.tariff.price"/>
            <a href="${pageContext.request.contextPath}/main/tariffs?service=${requestScope.service}&sortBy=tariff_price&order=ASC">&#8593</a>
            <a href="${pageContext.request.contextPath}/main/tariffs?service=${requestScope.service}&sortBy=tariff_price&order=DESC">&#8595</a>
        </th>

        <c:forEach var="tariff" items="${requestScope.tariffs}">
    <tr>
        <td><c:out value="${tariff.name}"/></td>
        <td><c:out value="${tariff.tariff.tariffPrice}"/> <fmt:message key="currency"/> </td>
    </tr>
    </c:forEach>
    </tbody>
</table>

<p align="center" style="color: dodgerblue">
    <form method="get" target="_blank" action="${pageContext.request.contextPath}/pdf">
        <input type="hidden" name="service" value="${requestScope.service}"/>
<p align="center">
    <button class="btn btn-success" style="background-color: deepskyblue" type="submit">
        <fmt:message key="button.pdf"/>
    </button>
</p>
</form>
</p>

</body>
</html>
