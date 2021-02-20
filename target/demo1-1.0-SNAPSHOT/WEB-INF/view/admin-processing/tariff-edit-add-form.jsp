<%--
  Created by IntelliJ IDEA.
  User: owners
  Date: 07.02.2021
  Time: 11:35
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
<%--    <a href="${pageContext.request.contextPath}/main/clients" class="navbar-brand letter"--%>
<%--       style="color: ghostwhite; font-size:14pt" ><fmt:message key="navbar.clients"/></a>--%>
    <a href="${pageContext.request.contextPath}/main/tariffs?service=internet" class="navbar-brand letter"
       style="color: ghostwhite; font-size:14pt" ><fmt:message key="navbar.internet"/></a>
    <a href="${pageContext.request.contextPath}/main/tariffs?service=tv" class="navbar-brand letter"
       style="color: ghostwhite; font-size:14pt" ><fmt:message key="navbar.tv"/></a>
    <a href="${pageContext.request.contextPath}/main/tariffs?service=telephony" class="navbar-brand letter"
       style="color: ghostwhite; font-size:14pt" ><fmt:message key="navbar.telephony"/></a>
    <a href="${pageContext.request.contextPath}/main/logout" class="navbar-brand letter"
       style="color: ghostwhite; font-size:14pt" ><fmt:message key="navbar.logout"/></a>
    <form method="get" action="${pageContext.request.contextPath}/change-language/main/tariff-edit-form">
        <input type="hidden" name="id" value="${requestScope.tariff.id}"/>
        <label for="language"></label>
        <select id="language" name="language"
                onchange="submit()" style="font-size: 11pt; background-color: ghostwhite; color: deepskyblue">
            <option value="en" ${language == 'en' ? 'selected' : ''} style="color: deepskyblue">English</option>
            <option value="ukr" ${language == 'ukr' ? 'selected' : ''} style="color: deepskyblue">Українська</option>
        </select>
    </form>
</nav>
<br>

<div class="form">
    <p align="center" style="color: deepskyblue; font-size: 22pt">
        <c:choose>
            <c:when test="${requestScope.tariff != null}">
                <fmt:message key="button.edit.tariff"/>
            </c:when>
            <c:otherwise>
                <fmt:message key="button.add.tariff"/>
            </c:otherwise>
        </c:choose>
    </p><br>
    <c:choose>
        <c:when test="${requestScope.tariff != null}">
            <form method="post" action="${pageContext.request.contextPath}/tariff-edit">
                <p align="center">
                    <input type="hidden" name="id" value="${requestScope.tariff.id}"/>
                    <input type="text" placeholder="${requestScope.tariff.tariffNameUkr}" name="nameUkr"><br><br>
                    <input type="text" placeholder="${requestScope.tariff.tariffNameEn}" name="nameEn"><br><br>
                    <input type="text" placeholder="${requestScope.tariff.tariffPrice}" name="price"><br><br>
                    <input type="text" placeholder="${requestScope.tariff.tariffService}" name="service"><br><br>
                    <c:if test="${not empty requestScope.warning}">
                        <fmt:message key="${requestScope.warning}"/>
                    </c:if><br>
                    <button class="btn btn-success" style="background-color: dodgerblue"><fmt:message key="button.edit.tariff"/></button>
                </p>
            </form>
        </c:when>
        <c:otherwise>
            <form method="post" action="${pageContext.request.contextPath}/tariff-add">
                <p align="center">
                    <input type="text" required placeholder="<fmt:message key="field.tariff.name.ukr"/>" name="nameUkr"><br><br>
                    <input type="text" required placeholder="<fmt:message key="field.tariff.name.en"/>" name="nameEn"><br><br>
                    <input type="text" required placeholder="<fmt:message key="field.tariff.price"/>" name="price"><br><br>
                    <input type="text" required placeholder="<fmt:message key="field.tariff.service"/>" name="service"><br><br>
                    <c:if test="${not empty requestScope.warning}">
                        <fmt:message key="${requestScope.warning}"/>
                    </c:if><br>
                    <button class="btn btn-success" style="background-color: dodgerblue"><fmt:message key="button.add.tariff"/></button>
                </p>
            </form>
        </c:otherwise>
    </c:choose>
</div>
<p align="center">
    <button type="button" class="btn btn-primary hBack" onclick="history.back()" style="background-color: dodgerblue">
        <fmt:message key="button.cancel"/>
    </button>
</p>

</body>
</html>