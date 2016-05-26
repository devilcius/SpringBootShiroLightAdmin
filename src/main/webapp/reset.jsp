<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>
        <div class="col-md-8  form-container col-md-offset-2">
            <h1>Type new password</h1>
            <form action="reset" method="post">
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" class="form-control" id="password" placeholder="Password" />
                </div>
                <div class="form-group">
                    <label for="confirm-password">Repeat Password</label>
                    <input type="password" name="confirm-password" class="form-control" id="confirm-password" />
                </div>
                <input type="hidden" name="token" value="${token}" />
                <button type="submit" class="btn btn-default">Reset</button>
            </form> 
        </div> 
        <script>
            var password = document.getElementById("password");
            var confirm_password = document.getElementById("confirm-password");

            function validatePassword() {
                if (password.value !== confirm_password.value) {
                    confirm_password.setCustomValidity("Passwords don't match");
                    confirm_password.style.borderColor = 'red';
                    confirm_password.style.borderWidth = '3px';

                } else {
                    confirm_password.setCustomValidity('');
                    confirm_password.style.borderColor = 'green';
                    confirm_password.style.borderWidth = '3px';
                }
            }
            password.onchange = validatePassword;
            confirm_password.onkeyup = validatePassword;
        </script>
    </jsp:body>
</t:base>
