<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Form platform"/>
<jsp:include flush="true" page="../base.jsp"/>

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

<%@ include file="../footer.jsp" %>
