<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

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
                    <h2 class="title1">Login</h2>
                    <div class="widget-shadow">
                        <div class="login-body">
                            <form action="${pageContext.request.contextPath}/login" method="post" onsubmit="return (checkForm(this));">
                                <input type="text" class="user" id="userLogin" name="userLogin" placeholder="Enter Your Login">
                                <input type="password" name="password" id="password" class="lock" placeholder="Password">

                                <c:if test="${isLogin}">
                                    <BLOCKQUOTE>
                                        <h4 style="color: red">
                                            <strong>Invalid User or Password</strong>
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
                                <input type="submit" name="login" id="login" value="Login">
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



</body>

  <script src="scripts/jquery-3.3.1.min.js"></script>
    <script src="scripts/login-1.4.js"></script>
    <script src="scripts/main-1.0.js"></script>

</html>