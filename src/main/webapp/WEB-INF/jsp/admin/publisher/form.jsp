<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../tag.jsp" %>
<c:set var="title" scope="request" value="Form Publisher"/>
<jsp:include flush="true" page="../admin.jsp"/>

        <div class="container col-9">

            <c:if test="${isEdit}">
                <h1>Edit Publisher ${publisher.name}</h1>
            </c:if>
            <c:if test="${!isEdit}">
                <h1>Create Publisher</h1>
            </c:if>
            <f:form modelAttribute="publisher" method="post" action="${action}" cssClass="p-5">
                <div class="mb-3 row">
                    <f:label path="name" class="col-sm-2 col-form-label">Name : </f:label>
                    <div class="col-sm-10">
                        <f:input type="text" cssClass="form-control" path="name"/>
                        <f:errors path="name" cssClass="invalid-feedback"/>
                    </div>
                </div>
                <div class="mb-3 row">
                    <f:label path="createdAt" class="col-sm-2 col-form-label">Creation Date : </f:label>
                    <div class="col-sm-10">
                        <f:input type="text" cssClass="form-control" path="createdAt"/>
                        <f:errors path="createdAt" cssClass="invalid-feedback"/>
                    </div>
                </div>
                <div class="mb-3 row">
                    <f:label path="website" class="col-sm-2 col-form-label">Website : </f:label>
                    <div class="col-sm-10">
                        <f:input type="text" cssClass="form-control" path="website"/>
                        <f:errors path="website" cssClass="invalid-feedback"/>
                    </div>
                </div>
                <div class="mb-3 row">
                    <f:label path="country" class="col-sm-2 col-form-label">Country : </f:label>
                    <div class="col-sm-10">
                        <f:select path="country"
                                  items="${countries}"
                                  cssClass="form-select"
                                  itemLabel="name"
                        >
                        </f:select>
                        <f:errors path="country" cssClass="invalid-feedback"/>
                    </div>
                </div>
                <f:button class="btn btn-secondary" type="reset">Reset</f:button>
                <f:button class="btn btn-primary">Submit</f:button>
            </f:form>
        </div>
    </div>
</div>

<%@ include file="../../footer.jsp" %>
