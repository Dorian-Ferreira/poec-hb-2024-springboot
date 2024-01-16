<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../tag.jsp" %>
<c:set var="title" scope="request" value="Form Country"/>
<jsp:include flush="true" page="../admin.jsp"/>

        <div class="container col-9">

            <c:if test="${isEdit}">
                <h1>Edit Country ${country.name}</h1>
            </c:if>
            <c:if test="${!isEdit}">
                <h1>Create Country</h1>
            </c:if>
            <f:form modelAttribute="country" method="post" action="${action}" cssClass="p-5">
                <div class="mb-3 row">
                    <f:label path="name" class="col-sm-2 col-form-label">Name : </f:label>
                    <div class="col-sm-10">
                        <f:input type="text" cssClass="form-control" path="name"/>
                        <f:errors path="name" cssClass="invalid-feedback"/>
                    </div>
                </div>
                <div class="mb-3 row">
                    <f:label path="nationality" class="col-sm-2 col-form-label">Nationality : </f:label>
                    <div class="col-sm-10">
                        <f:input type="text" cssClass="form-control" path="nationality"/>
                        <f:errors path="nationality" cssClass="invalid-feedback"/>
                    </div>
                </div>
                <div class="mb-3 row">
                    <f:label path="code" class="col-sm-2 col-form-label">Code : </f:label>
                    <div class="col-sm-10">
                        <f:input type="text" cssClass="form-control" path="code"/>
                        <f:errors path="code" cssClass="invalid-feedback"/>
                    </div>
                </div>
                <f:button class="btn btn-secondary" type="reset">Reset</f:button>
                <f:button class="btn btn-primary">Submit</f:button>
            </f:form>
        </div>
    </div>
</div>

<%@ include file="../../footer.jsp" %>
