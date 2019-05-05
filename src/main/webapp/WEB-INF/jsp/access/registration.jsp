<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="info" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/enter/bootstrap.css" type="text/css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/enter/custom.css" type="text/css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/enter/loginStyle.css" type="text/css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/enter/SidebarNav.min.css" type="text/css" media="all">

</head>

<body>

<div class="main-content">
    <div class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" id="cbp-spmenu-s1">
        <!--left-fixed -navigation-->
        <aside class="sidebar-left">
            <nav class="navbar navbar-inverse">
                <div class="navbar-header"></div>
            </nav>
        </aside>
    </div>

    <div id="page-wrapper">
        <div class="main-page login-page ">
            <h2 class="title1">Registration</h2>
            <div class="widget-shadow">
                <div class="login-body">
                    <form action="<c:url value="/registration"/>" method="post" onsubmit="return checkRegistration(this);" enctype="multipart/form-data">
                        <input type="text" class="user" id="userLogin" name="userLogin"  placeholder="Enter Your Login" value=${user.login}>
                        <c:if test="${isLoginExist == true}">
                            <BLOCKQUOTE>
                                <h4 style="color: red">
                                    <strong>Login is exist!</strong>
                                </h4>
                            </BLOCKQUOTE>
                        </c:if>
                        <input type="text" class="user" id="name" name="name" placeholder="Enter Your Name" value=${user.firstName}>
                        <input type="text" class="user" name="lastName" id="lastName" placeholder="Enter Your LastName" value=${user.lastName} >
                        <input type="password" class="user" id="password" name="password" placeholder="Enter Your Password" value=${user.password} >
                        <input type="password" class="user" id="repeatedPassword" name="repeatedPassword" placeholder="Enter Your Password Again" >
                        <input type="text" class="user" id="email" name="email" placeholder="Enter Your Email"  value=${user.email}>

                        <input type="file" name="avatar" id="avatar">
                        <info:captcha captchaId="${captchaId}" image="${pageContext.request.contextPath}/images/registrCaptcha.jpeg"/>
                        <input type="text" id="captcha" name="captcha" placeholder="Numbers from picture">

                        <c:if test="${isCaptcha == true}">
                            <BLOCKQUOTE>
                                <h4 style="color: red">
                                    <strong>Invalid Captcha!</strong>
                                </h4>
                            </BLOCKQUOTE>
                        </c:if>

                        <div class="forgot-grid">
                            <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>Remember me</label>
                            <div class="forgot">
                                <a href="#">forgot password?</a>
                            </div>
                            <div class="clearfix"> </div>
                        </div>
                        <input type="submit" name="login" id="login" value="To register">
                        <div class="registration">
                            Don't have an account ?
                            <a class="" href="signup.html">
                                Create an account
                            </a>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
    <!--footer-->
    <div class="footer">
        <p>&copy; 2018 Glance Design Dashboard. All Rights Reserved | Design by <a href="https://w3layouts.com/" target="_blank">w3layouts</a></p>
    </div>
    <!--//footer-->
</div>

    <script src="${pageContext.request.contextPath}/scripts/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/login-2.3.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/main-1.3.js"></script>

</body>

</html>