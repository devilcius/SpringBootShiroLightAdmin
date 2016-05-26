<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>
        <div class="col-md-8  form-container col-md-offset-2">
            <h1>Reset Password</h1>
            <form action="recover" method="post">
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="email" name="email" class="form-control" id="email" placeholder="Email">
                </div>
                <button type="submit" class="btn btn-default">Reset</button>
            </form> 
        </div>
    </jsp:body>
</t:base>
