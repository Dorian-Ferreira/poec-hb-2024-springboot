<%@ include file="../base.jsp" %>

<div class="container">

    <h1>Add a review for ${game.name}</h1>
    <f:form modelAttribute="review" method="post" action="${action}" cssClass="p-5">
        <div class="mb-3 row">

            <f:label path="title" class="col-sm-2 col-form-label">Title</f:label>
            <div class="col-sm-10">
                <f:input type="text" cssClass="form-control" path="title"/>
                <f:errors path="title" cssClass="invalid-feedback"/>
            </div>

            <f:label path="content" class="col-sm-2 col-form-label">Content</f:label>
            <div class="col-sm-10">
                <f:input type="text" cssClass="form-control" path="content"/>
                <f:errors path="content" cssClass="invalid-feedback"/>
            </div>

            <f:label path="rating" class="col-sm-2 col-form-label">Rating</f:label>
            <div class="col-sm-10">
                <f:input type="text" cssClass="form-control" path="rating"/>
                <f:errors path="rating" cssClass="invalid-feedback"/>
            </div>
        </div>
        <f:button class="btn btn-secondary" type="reset">Reset</f:button>
        <f:button class="btn btn-primary">Submit</f:button>
    </f:form>
</div>

<%@ include file="../footer.jsp" %>
