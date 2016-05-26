<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:body>
        <div class="col-lg-12 text-center">
            <h1>SBLSA</h1>
            <p class="lead">${application.getDescription()}</p>
            <ul class="list-unstyled">
                <c:forEach items="${application.getFeatures()}" var="feature">
                    <li >${feature.getName()} ${feature.getReleaseVersion()}</li>
                    </c:forEach>
            </ul>
        </div>
        <a href="https://github.com/devilcius/SpringBootShiroLightAdmin"><img style="position: absolute; top: 0; right: 0; border: 0; z-index:9999;" src="https://camo.githubusercontent.com/52760788cde945287fbb584134c4cbc2bc36f904/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f6769746875622f726962626f6e732f666f726b6d655f72696768745f77686974655f6666666666662e706e67" alt="Fork me on GitHub" data-canonical-src="https://s3.amazonaws.com/github/ribbons/forkme_right_white_ffffff.png"></a>
    </jsp:body>
</t:base>

