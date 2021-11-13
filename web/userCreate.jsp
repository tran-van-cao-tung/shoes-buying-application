<%-- 
    Document   : userCreate
    Created on : Jul 7, 2021, 11:00:20 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Users Page</title>
    </head>
    <body>
        <h1>Create new users</h1>       
        <form action="MainController">
            <table>
                <tr>
                    <th>USER ID</th>
                    <td>
                        <input type="text" name="userId" value="${CURRENT_ID}" required="">                       
                    </td>
                    <td style="color: red">${ID_USER_ERROR}</td>
                </tr>
                <tr>
                    <th>FULL NAME</th>
                    <td>
                        <input type="text" name="fullName" value="${CURRENT_FULLNAME}" required="">
                    </td>
                    <td style="color: red">${FULLNAME_USER_ERROR}</td>
                </tr>              
                <tr>
                    <th>PASSWORD</th>
                    <td>
                        <input type="text" name="password" value="${CURRENT_PASSWORD}" required="">
                    </td>
                    <td style="color: red">${PASSWORD_USER_ERROR}</td>
                </tr>
                <tr>
                    <th>PHONE</th>
                    <td>
                        <input type="number" name="phone" value="${CURRENT_PHONE}" required="" min="0">
                    </td>
                    <td style="color: red">${PHONE_USER_ERROR}</td>
                </tr>              
                <tr>
                    <th>ADDRESS</th>
                    <td>
                        <input type="text" name="address" value="${CURRENT_ADDRESS}" required="">
                    </td>
                    <td style="color: red">${ADDRESS_USER_ERROR}</td>                   
                </tr>                                                     
            </table>
            <input type="submit" name="btAction" value="Create">           
        </form>
                <form action="MainController">
            <input type="submit" name="btAction" value="Login">   
        </form>
    </body>
</html>
