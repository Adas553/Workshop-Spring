<%--
  Created by IntelliJ IDEA.
  User: adam
  Date: 27.02.2021
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<style input[type=text] {
       width: 100%;
       padding: 12px 20px;
       margin: 8px 0;
       box-sizing: border-box;
       }
></style>

<form action="/books" method="post">
    ID: <input type="number" name="id"> <br>
    ISBN: <input type="text" name="isbn">
    Title: <input type="text" name="title">
    Author: <input type="text" name="author">
    Publisher: <input type="text" name="publisher">
    Type: <input type="text" name="type">
    <input type="submit" value="Send">
</form>

</body>
</html>
