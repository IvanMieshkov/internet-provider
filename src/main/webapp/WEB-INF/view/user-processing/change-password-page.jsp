<%--
  Created by IntelliJ IDEA.
  User: owners
  Date: 06.02.2021
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <title><fmt:message key="button.edit.password"/></title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light sticky-top justify-content-between"
     style="background-color: deepskyblue ">
    <a href="${pageContext.request.contextPath}/" class="navbar-brand">
        <img src="https://i.ibb.co/nR7vNFX/pngwing-com.png" width="100" height="50" alt="logo"></a>
    <a href="${pageContext.request.contextPath}/main/menu" class="navbar-brand letter"
       style="color: ghostwhite; font-size:14pt" ><fmt:message key="navbar.client.menu"/></a>
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

<div align="center" class="form">
    <p  style="color: deepskyblue; font-size: 22pt"><fmt:message key="button.edit.password"/></p>
    <br>
    <form method="post" action="${pageContext.request.contextPath}/main/change-password-commit">
        <p>
            <input type="password" required placeholder="<fmt:message key="field.enter.current.password"/>" name="current-password"><br><br>
            <input type="password" required placeholder="<fmt:message key="field.enter.new.password"/>" name="new-password"><br><br>
            <c:if test="${not empty requestScope.warning}">
                <fmt:message key="${requestScope.warning}"/>
            </c:if><br>
            <button class="btn btn-success" style="background-color: dodgerblue"><fmt:message key="button.edit.password"/></button>
        </p>
    </form>
</div>
</body>
</html>