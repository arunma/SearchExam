<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
    <title>Search Evaluation Page</title>
    <!--<link type="text/css" rel="stylesheet" href="../../assets/css/bootstrap.css"/>-->
    <link type="text/css" rel="stylesheet" href="<c:url value="/assets/css/bootstrap.css" />"/>
</head>

<body>

<div class="container">
    <div class="row" >
        <div class="col-lg-12">

            <div class="bs-component">

                <div class="navbar navbar-default">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">NutraSpace</a>
                    </div>
                    <div class="navbar-collapse collapse navbar-responsive-collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="http://www.nutraspace.com" target="_blank">Go to nutraspace.com</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please Sign In</h3>
                </div>
                <div class="panel-body">
                    <c:if test="${not empty error}">
                        <div class="error">
                            Your login attempt was not successful, try again.<br /> Caused by :
                            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                        </div>
                    </c:if>

                    <c:if test="${not empty msg}">
                        <div class="msg">${msg}</div>
                    </c:if>

                    <form role="form" method="POST" name="loginform" action="<c:url value='/j_spring_security_check' />">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="Username" name="username" type="username" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Password" name="password" type="password" value="">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <button type="submit" class="btn btn-lg btn-success btn-block">Login</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>
