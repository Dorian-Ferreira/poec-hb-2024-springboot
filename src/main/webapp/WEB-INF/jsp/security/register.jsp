<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Inscription"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
    <h1>Créer son compte</h1>

    <f:form modelAttribute="userPostDTO"  method="post" action="${s:mvcUrl('AppRegister#registerHandler').build()}" cssClass="p-5 col-lg-6 col-md-8 col-sm-12 mx-auto">
        <div class="mb-3 row">
            <f:label path="name" class="col-sm-2 col-form-label">Nom</f:label>
            <div class="col-sm-10">
                <f:input type="text" cssClass="form-control" path="name"/>
                <f:errors path="name" cssClass="invalid-feedback"/>
            </div>
        </div>
        <div class="mb-3 row">
            <f:label path="email" class="col-sm-2 col-form-label">Email</f:label>
            <div class="col-sm-10">
                <f:input type="text" cssClass="form-control" path="email"/>
                <f:errors path="email" cssClass="invalid-feedback"/>
            </div>
        </div>
        <div class="mb-3 row">
            <f:label path="password" class="col-sm-2 col-form-label">Mot de passe</f:label>
            <div class="col-sm-10">
                <f:input type="password" cssClass="form-control" path="password"/>
                <f:errors path="password" cssClass="invalid-feedback"/>
            </div>
        </div>
        <f:button class="btn btn-secondary" type="reset">Reset</f:button>
        <f:button class="btn btn-primary">Submit</f:button>
    </f:form>

</div>

<%@ include file="../footer.jsp" %>
