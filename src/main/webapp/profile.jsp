<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<t:base>
    <jsp:body>
        <div class="col-md-8 form-container col-md-offset-2 text-left">
            <h3>Profile</h3>
            <table class="table">
                <thead>
                    <tr><th>Name</th> <th>Email</th><th>Description</th><th>Status</th><th>Is Admin</th></tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${user.getName()}</td>
                        <td>${user.getEmail()}</td>
                        <td>${user.getDescription()}</td>
                        <td><c:out default="None" escapeXml="true" value="${user.getEnabled()? 'Enabled':'Disabled'}"/></td>
                        <td><c:out default="None" escapeXml="true" value="${isAdmin? 'True':'False'}"/></td>
                    </tr>
                </tbody> 
                    <c:if test="${user.getApplications() != null}" >
                        <tfoot>
                            <tr>
                                <td colspan="5">User applications:</td>
                            </tr>
                                <c:forEach items="${user.getApplications()}" var="application">
                                    <tr>
                                        <td colspan="5"><strong>${application.getName()}</strong></td>
                                    </tr>
                                </c:forEach>                            
                        </tfoot>
                    </c:if>                
            </table>          
        </div>
    </jsp:body>
</t:base>
