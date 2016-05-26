<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>
        <div class="col-md-8  form-container col-md-offset-2">
            <h1>New user</h1>
            <form action="register" method="post">
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="email" name="email" class="form-control" id="email" placeholder="Email">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" class="form-control" id="password" >
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" id="role-admin" name="role-admin" value="1" /> Is admin
                    </label>
                </div>
                <button type="submit" class="btn btn-default">Sign up</button>
            </form> 
        </div>
    </jsp:body>
</t:base>
