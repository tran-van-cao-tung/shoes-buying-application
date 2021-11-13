<%-- 
    Document   : staffInsert
    Created on : Jul 6, 2021, 11:45:39 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Products Page</title>
    </head>
    <body>
        <h1>Insert new products</h1>
        <form action="StaffInsertController" enctype="multipart/form-data" method="post">
            <table>
                <tr>
                    <th>SHOES ID</th>
                    <td>
                        <input type="text" name="shoesId" required="" value="${CURRENT_SHOESID}">
                    </td>
                    <td style="color: red">${SHOES_ID_ERROR}</td>
                </tr>
                <tr>
                    <th>SHOES NAME</th>
                    <td>
                        <input type="text" name="shoesName" required="" value="${CURRENT_SHOESNAME}">
                    <td style="color: red">${SHOES_NAME_ERROR}</td>
                    </td>
                </tr>
                <tr>
                    <th>PRICE</th>
                    <td>
                        <input type="number" name="price" min="0" required="" value="${CURRENT_PRICE}">
                    </td>
                </tr>
                <tr>
                    <th>DESCRIPTION</th>
                    <td>                        
                        <input type="text" name="description" required="" value="${CURRENT_DESCRIPTION}">
                    <td style="color: red">${SHOES_DESCRIPTION_ERROR}</td>
                    </td>
                </tr>
                <tr>
                    <th>QUANTITY</th>
                    <td>
                        <input type="number" name="quantity" required="" value="${CURRENT_QUANTITY}">
                    </td>
                </tr>              
                <tr>
                    <th>NOT SALE</th>
                    <td>
                        <input type="text" name="notSale" required="" value="${CURRENT_STRINGNOTSALE}">
                    </td>
                    <td style="color: red">${NOT_SALE_ERROR}</td>
                </tr>
                <tr>
                    <th>IMAGE</th>
                    <td>
                        <input type="file" name="photo">
                    </td>
                </tr>
            </table>
            <input type="submit" name="btAction" value="Create">    
        </form>
        <form action="MainController">
            <input type="submit" name="btAction" value="Back to Page">
        </form>
    </body>
</html>
