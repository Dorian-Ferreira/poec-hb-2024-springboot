<%@ include file="../base.jsp" %>

<div class="container">
    <div class="row">
        <h1>${publisher.name}</h1>
    </div>

    <h2 class="my-5">Released Games:</h2>
    <div class="row">
        <c:forEach items="${publisher.publishedGames}" var="game">
            <a class="col-4 mt-2 main-game-card" href="${s:mvcUrl('AppGame#show').arg(1, game.slug).build()}">
                <div class="game-card">
                    <div class="game-card-img">
                        <img alt="${game.name}" src="${game.thumbnailCover}">
                    </div>
                    <div class="d-flex justify-content-between">
                        <p>${game.name}</p>
                        <p>${game.price}&euro;</p>
                    </div>
                </div>
            </a>
        </c:forEach>
    </div>

</div>

<%@ include file="../footer.jsp" %>
