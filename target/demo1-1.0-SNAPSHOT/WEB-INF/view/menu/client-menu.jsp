<%--
  Created by IntelliJ IDEA.
  User: owners
  Date: 10.01.2021
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title><fmt:message key="title.main.menu"/></title>
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
    <form method="get" action="${pageContext.request.contextPath}/change-language/main/menu">
        <label for="language"></label>
        <select id="language" name="language"
                onchange="submit()" style="font-size: 11pt; background-color: ghostwhite; color: deepskyblue">
            <option value="en" ${language == 'en' ? 'selected' : ''} style="color: deepskyblue">English</option>
            <option value="ukr" ${language == 'ukr' ? 'selected' : ''} style="color: deepskyblue">Українська</option>
        </select>
    </form>
</nav>
<br>
<table class="table" style="color: dodgerblue" >
    <tbody>
    <tr>
        <th scope="row"><fmt:message key="field.user.login"/></th>
        <td align="right">${sessionScope.user.login}</td>
    </tr>
    <tr>
        <th scope="row"><fmt:message key="field.user.name"/></th>
        <td align="right">${sessionScope.user.fullName}</td>
    </tr>
    <tr>
        <th scope="row"><fmt:message key="field.user.email"/></th>
        <td align="right">${sessionScope.user.email}</td>
    </tr>
    <tr>
        <th scope="row"><fmt:message key="field.user.address"/></th>
        <td align="right">${sessionScope.user.address}</td>
    </tr>
    <tr>
        <th scope="row"><fmt:message key="field.user.phone"/></th>
        <td align="right">${sessionScope.user.phoneNumber}</td>
    </tr>
    <tr>
        <th scope="row"><fmt:message key="field.user.balance"/></th>
        <c:choose>
            <c:when test="${sessionScope.user.balance==0}">
                <td align="right" scope="row" style="color: red">${sessionScope.user.balance}</td>
            </c:when>
            <c:otherwise><td align="right">${sessionScope.user.balance}</td></c:otherwise>
        </c:choose>

        <td>
            <form method="get" action="${pageContext.request.contextPath}/main/payment">
<%--                <p align="center">--%>
                    <input type="text" name="payment" required placeholder="<fmt:message key="field.user.payment"/>"/>
        <td>
            <button class="btn btn-success" style="background-color: dodgerblue" type="submit"><fmt:message key="field.user.pay"/></button>
        </td>
        </p>
        </form>
        </td>

    </tr>
    <tr>
        <th scope="row"><fmt:message key="field.user.tariffs"/></th>

        <c:forEach var="userTariff" items="${sessionScope.userTariffs}">
    <tr>
        <c:if test="${userTariff.tariff.tariffService=='internet'}"><td align="right"><fmt:message key="navbar.internet"/></td></c:if>
        <c:if test="${userTariff.tariff.tariffService=='tv'}"><td align="right"><fmt:message key="navbar.tv"/></td></c:if>
        <c:if test="${userTariff.tariff.tariffService=='telephony'}"><td align="right"><fmt:message key="navbar.telephony"/></td></c:if>
        <td align="right"><c:out value="${language == 'en' ? userTariff.tariff.tariffNameEn : userTariff.tariff.tariffNameUkr}"/></td>
    </tr>
    </c:forEach>
    </tr>
    </tbody>
</table>
<p align="center" style="color: dodgerblue">
    <form method="get" action="${pageContext.request.contextPath}/main/change-password">
<p align="center"><button class="btn btn-success" style="background-color: deepskyblue" type="submit">
    <fmt:message key="button.edit.password"/>
</button>
</p>
</form>

</p>
</body>
</html>
