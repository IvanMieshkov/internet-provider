<%--
  Created by IntelliJ IDEA.
  User: owners
  Date: 09.01.2021
  Time: 15:29
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
<!doctype html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

    <title><fmt:message key="title.main.page"/></title>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light sticky-top justify-content-between"
     style="background-color: deepskyblue ">
    <a href="${pageContext.request.contextPath}/" class="navbar-brand">
        <img src="https://i.ibb.co/nR7vNFX/pngwing-com.png" width="100" height="50" alt="logo">
    </a>
    <p align="center">
        <a href="${pageContext.request.contextPath}/" class="navbar-brand letter my-2 my-lg-0"
           style="color: ghostwhite; font-size:18pt" >
            <fmt:message key="title.index"/>
        </a>
    </p>
    <form method="get" action="${pageContext.request.contextPath}/change-language/" class="form-inline my-2 my-lg-0">
        <label for="language"></label>
        <select id="language" name="language"
                onchange="submit()" style="font-size: 11pt; background-color: ghostwhite; color: deepskyblue">
            <option value="en" ${language == 'en' ? 'selected' : ''} style="color: deepskyblue">English</option>
            <option value="ukr" ${language == 'ukr' ? 'selected' : ''} style="color: deepskyblue">Українська</option>
        </select>
    </form>
</nav>
<p align="center">
    <a href="${pageContext.request.contextPath}/login"
   style="color: dodgerblue; font-size: 22pt"><fmt:message key="title.main.menu"/>
</p>

<img src="https://www.semana.com/resizer/QmndRzvszxIfaUAxpbR26C-YJEs=/arc-anglerfish-arc2-prod-semana/public/XT45D6GCXNABJFIOKTAJT5KIAM.jpg" width="100%" align="center" alt="city">

</body>
</html>
