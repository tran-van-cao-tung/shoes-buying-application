<%-- 
    Document   : staffPage
    Created on : Jul 5, 2021, 3:35:18 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Page</title>
    </head>
    <body>
        <h1>Welcome staff ${CURRENT_USER.fullName}</h1>
        <a href="MainController?btAction=Insert">Insert new Products</a>
        <a href="MainController?btAction=Logout">Logout</a>
        <p style="color: red">${SEARCH_ERROR}</p>
        <form action="MainController">
            <input type="text" name="searchValue" value="${sessionScope.CURRENT_SEARCH}" required="">
            <input type="submit" name="btAction" value="Search">
        </form>
        <p style="color: red">${STATEMENT_DELETE}</p>
        <p style="color: red">${STATEMENT_UPDATE}</p>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Shoes Id</th>
                    <th>Shoes Name</th>
                    <th>Price</th>
                    <th>Description</th>                   
                    <th>Quantity</th>
                    <th>Image</th>
                    <th>Not Sale</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>           
            <tbody> 
                <%int count = 0;%>
                <c:forEach items="${sessionScope.SEARCH_LIST}" var="shoes">
                <form action="MainController" method="POST">
                    <tr>    
                        <td><%=++count%></td>
                        <td>${pageScope.shoes.shoesId}</td>
                        <td>${pageScope.shoes.shoesName}</td>
                        <td>
                            <input type="number" name="price" min="0" value="${pageScope.shoes.price}">    
                        </td>
                        <td>${pageScope.shoes.description}</td>
                        <td>
                            <input type="number" name="quantity" min="0" value="${pageScope.shoes.quantity}">
                        </td>
                        <td>                               
                            <img src="images/${pageScope.shoes.image}" width="80px" height="50px">                                
                        </td>
                        <td>
                            <input type="checkbox" name="notSale" value="ON"
                                   <c:if test="${pageScope.shoes.notSale}">                            
                                       checked = "checked"
                                   </c:if>
                                   />
                        </td>
                        <td>
                            <input type="hidden" name="shoesId" value="${pageScope.shoes.shoesId}">
                            <input type="hidden" name="current_Search" value="${sessionScope.CURRENT_SEARCH}">
                            <input type="submit" name="btAction" onclick="return confirm('Are you sure about that?')" value="Delete">
                        </td>
                        <td>                 
                            <input type="hidden" name="shoesId" value="${sessionScope.SEARCH_LIST}">
                            <input type="hidden" name="current_Search" value="${sessionScope.CURRENT_SEARCH}">
                            <input type="submit" name="btAction" value="Update">
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
