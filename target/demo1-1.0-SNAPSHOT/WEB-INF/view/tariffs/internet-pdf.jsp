<%--
  Created by IntelliJ IDEA.
  User: owners
  Date: 22.01.2021
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="application/pdf;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<c: var="pdf" items="${requestScope.pdf.writeTo(res.getOutputStream())}"></c:>--%>
${requestScope.pdf.writeTo(response.getOutputStream())}
</body>
</html>
