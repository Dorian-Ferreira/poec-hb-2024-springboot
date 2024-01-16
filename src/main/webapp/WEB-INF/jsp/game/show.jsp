<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="${game.name}"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container mt-5">
    <div class="row">
        <div class="col-6">
            <div class="container-img p-3">
                <img alt="${game.name}" src="${game.thumbnailCover}">
            </div>
        </div>
        <div class="col-6">
            <h1>${game.name}</h1>
            <div class="d-flex">
                <c:forEach items="${game.countries}" var="country">
                    <a class="link-if" href="${s:mvcUrl('AppGame#search').arg(0, country.name.toLowerCase()).build()}">
                        <img class="me-1"
                             src="${country.urlFlag}"
                             alt="${country.name}"
                             title="${country.nationality}"
                        >
                    </a>
                </c:forEach>
            </div>
            <p class="m-0 mt-1">Genres :
                <c:forEach items="${game.categories}" var="category">
                    <a class="link-if" href="${s:mvcUrl('AppGame#search').arg(0, category.slug).build()}">${category.name}</a>
                </c:forEach>
            </p>
            <c:if test="${game.platforms.size() > 0}">
                <p class="m-0">Disponible sur :</p>
                <ul class="list-unstyled">
                    <c:forEach items="${game.platforms}" var="platform">
                        <li>
                            <a class="link-if" href="${s:mvcUrl('AppGame#search').arg(0, platform.slug).build()}">
                                ${platform.name}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
            <p class="fs-2">${game.price}€</p>
        </div>
    </div>

    <h2 class="my-5">Description</h2>

    <div class="text-center description">
        <c:out value="${game.description}" escapeXml="false"/>
    </div>

    <h2 class="my-3">Commentaires</h2>
        <c:if test="${game.reviews.size() > 0}">
            <div class="row">
                <c:set value="12" scope="request" var="max"/>
                <c:if test="${game.reviews.size() <= 12}">
                    <c:set value="${game.reviews.size()}" scope="request" var="max"/>
                </c:if>
                <c:forEach items="${game.reviews.subList(0, max)}" var="review">
                    <div class="col-3">
                        <div>${review.rating}/5</div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <security:authorize access="isAuthenticated()">
            <f:form modelAttribute="reviewDto" method="post" action="${s:mvcUrl('AppReview#create').arg(0, game.slug).build()}" cssClass="p-5 col-lg-6 col-md-8 col-sm-12 mx-auto">
                <div class="mb-3 row">
                    <f:label path="title" class="col-sm-2 col-form-label">Title</f:label>
                    <div class="col-sm-10">
                        <f:input type="text" cssClass="form-control" path="title"/>
                        <f:errors path="title" cssClass="invalid-feedback"/>
                    </div>
                </div>
                <div class="mb-3 row">
                    <f:label path="content" class="col-sm-2 col-form-label">Content</f:label>
                    <div class="col-sm-10">
                        <f:textarea cssClass="form-control review-content" path="content"/>
                        <f:errors path="content" cssClass="invalid-feedback"/>
                    </div>
                </div>
                <div class="mb-3 row">
                    <f:label path="rating" class="col-sm-2 col-form-label">Rating</f:label>
                    <div class="col-sm-10">
                        <f:input type="number" min="0" max="5" step="0.5" cssClass="form-control" path="rating"/>
                        <f:errors path="rating" cssClass="invalid-feedback"/>
                    </div>
                </div>
                <f:input type="number" path="userId" hidden="hidden"/>
                <f:input type="number" path="gameId" hidden="hidden"/>
                <f:button class="btn btn-secondary" type="reset">Reset</f:button>
                <f:button class="btn btn-primary">Submit</f:button>
            </f:form>
        </security:authorize>
</div>

<%@ include file="../footer.jsp" %>
