<%-- 
    Document   : viewCart
    Created on : Jul 9, 2021, 1:56:30 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="viewCart.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Your Cart!</h1>
        <p style="color: red">${BUY_ERROR}</p>
        <p style="color: red">${STATEMENT_UPDATE}</p>
        <p style="color: red">${DELETE_CART}</p>
        <p style="color: red">${STATEMENT_BUY}</p>
        <a href="MainController?btAction=">Back to home page</a>
        <br>
        <table border="1px">
            <tr>
                <th>No.</th>
                <th>Order Id</th>
                <th>Shoes id</th>
                <th>Shoes name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Images</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>
            <% int count = 0;%>
            <c:forEach items="${sessionScope.ORDER_DETAIL_LIST}" var="order">

                <form action="MainController">
                    <tr>
                        <td><%=++count%></td>
                        <td>
                            ${pageScope.order.orderId}
                            <input type="hidden" name="orderId" value="${pageScope.order.orderId}">
                        </td>
                        <td>
                            ${pageScope.order.shoesId}
                            <input type="hidden" name="shoesId" value="${pageScope.order.shoesId}">
                        </td>
                        <td>
                            ${pageScope.order.shoesName}
                            <input type="hidden" name="shoesName" value="${pageScope.order.shoesName}">
                        </td>
                        <td>
                            <input type="number" min="1" name="orderQuantity" value="${pageScope.order.orderQuantity}" max="${STORE_QUANTITY}">                     
                        </td>
                        <td>
                            ${pageScope.order.price}
                            <input type="hidden" name="price" value="${pageScope.order.price}">
                        </td>
                        <td>
                            <img src="images/${pageScope.order.images}">
                            <input type="hidden" name="image" value="images/${pageScope.order.images}">
                        </td>
                        <td><a href="MainController?btAction=Remove&shoesId=${pageScope.order.shoesId}" onclick="return confirm('Are you sure about that?')">Remove</a></td>
                        <td>

                            <input type="hidden" name="shoesId" value="${pageScope.order.shoesId}">                          
                            <input type="submit" name="btAction" value="Update cart">

                        </td>
                    </tr>
                    <c:set var = "total" scope = "page" value = "${order.price + total}"/>
                </form>
            </c:forEach>
        </table>
        <h1>Total: ${total}</h1>

        <form action="MainController">
            <input type="hidden" name="total" value="${total}">
            <input type="submit" name="btAction" onclick="return confirm('Are you sure about that?')" value="Buy">
        </form>


    </body>
</html>
