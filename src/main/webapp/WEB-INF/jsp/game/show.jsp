<%@ include file="../base.jsp" %>

<div class="container">
    <div class="row">
        <img class="col-6 p-3 image-fluid" alt="${game.name}" src="${game.thumbnailCover}">
        <div class="col-6">
            <h1>${game.name}</h1>
            <h3 class="mt-4">Price: ${game.price}&euro;</h3>
            <c:if test="${game.publisher != null}">
                <h3 class="mt-4">Published by <a href="${s:mvcUrl('AppPublisher#show').arg(1, game.publisher.slug).build()}">${game.publisher.name}</a></h3>
            </c:if>
            <h3 class="mb-0 mt-4">Categories:</h3>
            <c:forEach items="${game.categories}" var="category">
                <a href="${s:mvcUrl('AppGame#search').arg(0, category.name).build()}" class="mb-0">${category.name}</a>
            </c:forEach>

            <h3 class="mb-0 mt-4">Available on:</h3>
            <c:forEach items="${game.platforms}" var="platform">
                <a href="${s:mvcUrl('AppGame#search').arg(0, platform.name).build()}" class="mb-0">${platform.name}</a>
            </c:forEach>

            <h3 class="mb-0 mt-4">Available in:</h3>
            <c:forEach items="${game.countries}" var="language">
                <a href="${s:mvcUrl('AppGame#search').arg(0, language.name).build()}">
                    <img class="mb-0" alt="${language.name}" src="${language.urlFlag}">
                </a>
            </c:forEach>
        </div>
    </div>

    <h2 class="my-5">Description</h2>

    <div class="text-center">
        <c:out value="${game.description}" escapeXml="false"/>
    </div>


    <c:if test="${game.reviews.size() > 2}">
        <h2 class="my-5">Recent reviews: </h2>
        <div class="row">
            <c:forEach items="${game.reviews.subList(0, 3)}" var="review">
                <div class="col-4 mt-2 game-review">
                    <div class="review-card">
                        <div class="d-flex justify-content-between">
                            <img alt="${review.user.name}" src="${review.user.profileImage}">
                            <p>${review.user.name}</p>
                        </div>
                        <div class="review-card">
                            <h3>${review.title}</h3>
                            <div class="text-truncate review-content">
                                ${review.content}
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
    <a href="${s:mvcUrl('AppReview#add').arg(2, game.slug).build()}">Add a review</a>

</div>

<%@ include file="../footer.jsp" %>
