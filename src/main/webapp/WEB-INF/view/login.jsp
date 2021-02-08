
<%--
  Created by IntelliJ IDEA.
  User: owners
  Date: 09.01.2021
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1", shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

    <title><fmt:message key="button.sign-in"/></title>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light sticky-top justify-content-between"
     style="background-color: deepskyblue ">
    <a href="${pageContext.request.contextPath}/" class="navbar-brand">
        <img src="https://i.ibb.co/nR7vNFX/pngwing-com.png" width="100" height="50" alt="logo">
    </a>
    <p align="center">
        <a href="${pageContext.request.contextPath}/login" class="navbar-brand letter my-2 my-lg-0"
           style="color: ghostwhite; font-size:18pt" >
            <fmt:message key="title.index"/>
        </a>
    </p>
    <form method="get" action="${pageContext.request.contextPath}/change-language/login" class="form-inline my-2 my-lg-0">
        <label for="language"></label>
        <select id="language" name="language"
                onchange="submit()" style="font-size: 11pt; background-color: ghostwhite; color: deepskyblue">
            <option value="en" ${language == 'en' ? 'selected' : ''} style="color: deepskyblue">English</option>
            <option value="ukr" ${language == 'ukr' ? 'selected' : ''} style="color: deepskyblue">Українська</option>
        </select>
    </form>
</nav>
<br>
<p align="center" style="color: deepskyblue; font-size: 22pt"><fmt:message key="button.sign-in"/></p>
<div class="form">
    <br>
    <form method="post" action="${pageContext.request.contextPath}/main/menu">
        <p align="center">
            <input type="text" required placeholder="<fmt:message key="field.user.login"/>" name="login"><br><br>

            <input type="password" required placeholder="<fmt:message key="field.user.password"/>" name="password"><br>
            <c:if test="${not empty requestScope.warning}">
                <fmt:message key="${requestScope.warning}"/>
            </c:if><br>
            <button class="btn btn-success" style="background-color: dodgerblue"><fmt:message key="button.sign-in"/></button>
        </p>
    </form>
</div>
<img src="https://www.semana.com/resizer/QmndRzvszxIfaUAxpbR26C-YJEs=/arc-anglerfish-arc2-prod-semana/public/XT45D6GCXNABJFIOKTAJT5KIAM.jpg" width="100%" align="center" alt="city">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous">
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous">
</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous">

</script>
</body>
</html>
