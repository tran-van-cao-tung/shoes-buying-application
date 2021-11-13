<%-- 
    Document   : login
    Created on : Jul 4, 2021, 4:08:03 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link   <link rel="stylesheet" href="login.css">      
        <title>Login Page</title>
    </head>
    <body>
        <div class="login">
            <form action="MainController" method="POST">
                <h1> Login</h1>
                <p style="color: red">${LOGIN_ERROR}</p>
                <input type="text" name="userId" value="" required="" placeholder="User Id"><br>
                <input type="text" name="pwd" value="" required="" placeholder="Password"><br>
                <input class="submit" type="submit" name="btAction" value="Login">
            </form>
            <a href="userCreate.jsp">Create new login</a><br><br>
            <a href="home.jsp">Back to home</a>
        </div>
    </body>
</html>
