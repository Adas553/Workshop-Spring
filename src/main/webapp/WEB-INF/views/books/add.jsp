<%--
  Created by IntelliJ IDEA.
  User: adam
  Date: 27.02.2021
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--style="margin: 20px 0;box-sizing: border-box;width: 30%;padding: 12px 20px;border-width: 5px;alignment-baseline: auto"--%>
<form:form method="post" modelAttribute="book">
    ISBN: <form:input path="isbn"/><br>
<%--    <form:errors path="isbn" cssClass="error"/><br>--%>
    Title: <form:input path="title"/><br>
    <form:errors path="title" cssClass="error"/><br>
    Author: <form:input path="author"/> <br>
    <form:errors path="author" cssClass="error"/><br>
    Publisher: <form:input path="publisher"/>  <br>
    <form:errors path="publisher" cssClass="error"/><br>
    Type: <form:input path="type"/> <br>
    <input type="submit" value="Send">
</form:form>

</body>
</html>
