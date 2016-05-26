<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<t:base>
    <jsp:body>
        <div class="col-md-8 form-container col-md-offset-2">
            <h1>Login</h1>
            <form action="login" method="post">
                <div class="form-group">
                    <label for="email">Email Address</label>
                    <input type="email" name="email" class="form-control" id="email" placeholder="Email">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" class="form-control" id="password" >
                </div>
                <div class="checkbox">
                    <label>
                        <input name="rememberme" value="1" type="checkbox"> Remember me
                    </label>
                </div>
                <button type="submit" class="btn btn-default">Validate</button>
                <hr/>
                <a href="<spring:url value="/recover" />">Forgot password?</a>
            </form>
        </div>
    </jsp:body>
</t:base>
