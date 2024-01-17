<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="${user.name}"/>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container mt-5">
    <div class="row">
        <div class="col-6">
            <div class="container-img p-3">
                <img alt="${user.name}" src="${user.profileImage}">
            </div>
        </div>
        <div class="col-6">
            <h1>${user.name}</h1>
            <div class="d-flex">
                <p class="m-0 mt-1"> Created : <fmt:formatDate value="${user.createdAt}" pattern="dd-MM-yyyy" /></p>
            </div>
            <div class="d-flex">
                <c:if test="${user.country != null}">
                    <p class="m-0 mt-1"> Country :
                        <a class="link-if" href="${s:mvcUrl('AppGame#search').arg(0, user.country.name.toLowerCase()).build()}">
                            <img class="me-1"
                                 src="${user.country.urlFlag}"
                                 alt="${user.country.name}"
                                 title="${user.country.nationality}"
                            >
                        </a>
                    </p>
                </c:if>
            </div>
            <c:if test="${loggedUser}">
                <div class="d-flex">
                    <p class="m-0 mt-1"> Wallet : ${user.wallet}&euro;</p>
                </div>
            </c:if>
            <div class="d-flex">
                <c:if test="${user.nickname != null}">
                    <p class="m-0 mt-1"> Nickname : ${user.nickname}</p>
                </c:if>
            </div>
        </div>
    </div>

    <c:if test="${user.reviews.size() > 0}">
        <h2 class="my-3">Reviews :</h2>
        <div class="row">
            <c:forEach items="${user.reviews}" var="review">
                <div class="col-3">
                    <a class="link-if" href="${s:mvcUrl('AppGame#show').arg(0, review.game.slug).build()}">${review.game.name} : </a>
                    <div>${review.rating}/5</div>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${user.userOwnGames.size() > 0}">
        <h2 class="my-3">Owned Games :</h2>
        <div class="row">
            <c:forEach items="${user.userOwnGames}" var="ownGame">
                <div class="col-3">
                    <a class="link-if" href="${s:mvcUrl('AppGame#show').arg(0, ownGame.game.slug).build()}">${ownGame.game.name}</a>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${user.wantedGames.size() > 0}">
        <h2 class="my-3">Owned Games :</h2>
        <div class="row">
            <c:forEach items="${user.wantedGames}" var="wantedGame">
                <div class="col-3">
                    <a class="link-if" href="${s:mvcUrl('AppGame#show').arg(0, wantedGame.slug).build()}">${wantedGame.name}</a>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>

<%@ include file="../footer.jsp" %>
