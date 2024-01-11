<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <title>${game.name}</title>
        <link href="<c:url value="../../../css/bootstrap/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="../../../css/main.css" />" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="text-center"><h1>${game.name}</h1></div>
            <br/>
            <div class="text-center">Price : ${game.price}€</div>
            <br/>
            <br/>
            <div class="text-center">
                <h2>Description</h2>
                <br/>
                <c:out value="${game.description}" escapeXml="false"/>
            </div>

        </div>
    </body>
</html>
