<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <title>Form Platform</title>
        <link href="<c:url value="../../../css/bootstrap/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="../../../css/main.css" />" rel="stylesheet">
    </head>
    <body>
        <div class="container">

            <c:if test="${isEdit}">
                <h1>Edit Platform ${platform.name}</h1>
            </c:if>
            <c:if test="${!isEdit}">
                <h1>Create Platform</h1>
            </c:if>
            <f:form modelAttribute="platform" method="post" action="${action}" cssClass="p-5">
                <div class="mb-3 row">
                    <f:label path="name" class="col-sm-2 col-form-label">Name</f:label>
                    <div class="col-sm-10">
                        <f:input type="text" cssClass="form-control" path="name"/>
                        <f:errors path="name" cssClass="invalid-feedback"/>
                    </div>
                </div>
                <f:button class="btn btn-secondary" type="reset">Reset</f:button>
                <f:button class="btn btn-primary">Submit</f:button>
            </f:form>
        </div>
    </body>
</html>
