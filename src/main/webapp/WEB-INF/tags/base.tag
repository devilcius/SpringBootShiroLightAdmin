<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="currentUser" fragment="true" %>
<%@tag import="org.apache.shiro.SecurityUtils"%>
<%@tag import="org.apache.shiro.subject.Subject" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel='shortcut icon' type='image/x-icon' href='${pageContext.request.contextPath}/favicon.ico' />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Springboot, Shiro and Lightadmin</title>
        <!-- Bootstrap Core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                padding-top: 70px;
                /* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
            }
        </style>        
    </head>    
    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<spring:url value="/" />">SBLS</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <%
                        Subject currentUser = SecurityUtils.getSubject();
                    %>
                    <ul class="nav navbar-nav">
                        <%
                            if (currentUser.hasRole("admin")) {
                        %>                            
                        <li>
                            <a href="<spring:url value="/ladmin" />">Administration</a>
                        </li>                       
                        <%
                            }
                        %>                             
                        <%
                            if (!currentUser.isAuthenticated() && !currentUser.isRemembered()) {
                        %>                    
                        <li>
                            <a href="<spring:url value="/login" />">Sign in</a>
                        </li>
                        <li>
                            <a href="<spring:url value="/register" />">Signup</a>
                        </li>                         
                        <%
                        } else {
                        %>
                        <li>
                            <a href="<spring:url value="/profile" />">View profile</a>
                        <li>
                            <a href="<spring:url value="/login?logout=true" />">Disconnect</a>
                        </li>                        
                        <%
                            }
                        %>                           
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>
        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <c:if test="${not empty message}">
                    <div class="alert alert-${alert}" role="alert">
                        ${message}            
                    </div>                    
                </c:if>                
            </div>
            <div class="row">
                <jsp:doBody/> 
            </div>
        </div>
    </body>    
</html>
