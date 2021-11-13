<%-- 
    Document   : newjsp
    Created on : Jul 3, 2021, 11:35:57 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="home.css">
        <title>THD's Shop</title>
    </head>
    <body>
        <div class="header">
            <div class="container">
                <div class="navbar">
                    <div class="logo">
                        <img src="images/3.jpg" width="50px" alt="logo"> &nbsp;
                        &nbsp;&nbsp;&nbsp;
                    </div>  
                    <div class="searchbar">
                        <form action="MainController">                                               
                            <input type="text" name="userSearch" value="${sessionScope.CURRENT_USER_SEARCH}" required="">
                            <input type="submit" name="btAction" value="Search products">
                            <p style="color: red">${INPUT_SEARCH_ERROR}</p>
                        </form>                       
                    </div>
                    <nav>
                        <ul> 
                            <li>
                                <h3 style="color: red">${STATEMENT_BUY}</h3>
                            </li>
                            <c:if test="${CURRENT_USER != null}" scope="session" var="user">
                                <li><a href="viewCart.jsp">My Cart</a></li>   
                                </c:if>                                                              
                            <li> 
                                <c:if test="${CURRENT_USER == null}" scope="session" var="user">
                                    <a href="MainController?btAction=Sign in"><h1>SIGN IN</h1> </a>
                                </c:if>
                                <c:if test="${CURRENT_USER != null}" scope="session" var="user">
                                    <h3>${CURRENT_USER.fullName}</h3>
                                </c:if>
                            </li>
                            <li>
                                <c:if test="${CURRENT_USER != null}" scope="session" var="user">
                                    <a href="MainController?btAction=Logout">Logout</a>
                                </c:if>
                            </li>
                        </ul>
                    </nav>

                </div> 
                <div class="row">
                    <div class="col-2">
                        <h1>THD's SHOP</h1>
                        <p>It was founded in 1964 as Blue Ribbon Sports by Bill Bowerman,
                            <br>a track-and-field coach at the University of Oregon, and his former student Phil Knight.
                            <br>They opened their first retail outlet in 1966 and launched the Nike brand shoe in 1972.
                            <br>The company was renamed Nike, Inc., in 1978 and went public two years later.
                            <br>All shoes all around the world can find in her just 2 minutes!
                        </p>
                    </div>
                    <div class="col-2">
                        <img src="images/3.jpg">
                    </div>
                </div>
            </div>
        </div>

        <!------- grid categories ----->
        <div class="categories">
            <div class="small-container">
                <div class ="row">
                    <div class="col-3">
                        <img src="images/features4.jpeg" alt="features4">
                    </div>
                    <div class="col-3">
                        <img src="images/features2.jpg" alt="features2">
                    </div>
                    <div class="col-3">
                        <img src="images/features3.jpg" alt="features3">
                    </div>
                </div>
            </div>
        </div>

        <!------ grid products ----->
        <div class="small-container">
            <h2 class="title">Featured Products</h2>

            <div class="row">
                <c:forEach items="${sessionScope.USER_SEARCH_LIST}" var="p">
                    <c:if test="${CURRENT_USER == null}" scope="session" var="user">
                        <a href="MainController?btAction=Login">
                            <div class="col-4">
                                <img src="images/${p.image}" alt="">
                                <h4>${pageScope.p.shoesName}</h4>
                                <p>${pageScope.p.description}</p>
                                <p>${pageScope.p.price}</p>
                                <h1>Add to cart</a></h1>
                            </div>
                        </a>
                    </c:if>
                    <c:if test="${CURRENT_USER != null}" scope="session" var="user">                                                
                        <div class="col-4">
                            <img src="images/${p.image}" alt="">
                            <h4>${pageScope.p.shoesName}</h4>
                            <p>${pageScope.p.description}</p>
                            <p>${pageScope.p.price}</p>
                            <h1><a href="MainController?btAction=AddToCart&shoesId=${pageScope.p.shoesId}">Add to cart</a></h1>
                        </div>                                                                     
                    </c:if>
                </c:forEach>

                <c:if test="${sessionScope.USER_SEARCH_LIST.size() == 0}" scope="session" var="list" >
                    <h1>NOT FOUND ANY PRODUCTS</h1>
                </c:if>
            </div>

        </div>
        <!------ footer ------->
        <footer>
            <div class="container">
                <div class="row">

                    <div class="footer-col-1">
                        <h3>Download for app</h3>
                        <p>Google</p>
                        <p>Chrome</p>
                        <p>FireFox</p>
                        <p>Microsoft</p>
                    </div>

                    <div class="footer-col-2">
                        <h3>Brand</h3>
                        <p>VANS</p>
                        <p>NIKE</p>
                        <p>ADIDAS</p>
                    </div>

                    <div class="footer-col-3">
                        <h3>Sponserd by:</h3>
                        <ul>
                            <li>Trần Văn Cao Tùng</li>
                            <li>Dương Cẩm Nhung</li>
                            <li>Đoàn Nguyễn Quốc Huy</li>                           
                        </ul>
                    </div>

                    <div class="footer-col-4">
                        <h3>Follow us</h3>
                        <ul>
                            <li>Facebook</li>
                            <li>Instagram</li>
                            <li>Twitter</li>
                            <li>Snapchat</li>
                        </ul>
                    </div>

                </div>
            </div>
        </footer>
    </body>
</html>
