<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Instant Faking Admin"/>
<jsp:include flush="true" page="admin.jsp"/>

        <div class="col-1">
        </div>
        <div class="col-6">
            <p class="m-0">Last Created Users :</p>
            <ul class="list-unstyled">
                <c:forEach items="${lastUsers}" var="user">
                    <li>
                        <p> ${user.name} : ${user.createdAt} </p>
                    </li>
                </c:forEach>
            </ul>

            <p class="m-0">Last Sales :</p>
            <ul class="list-unstyled">
                <c:forEach items="${lastSales}" var="sale">
                    <li>
                        <p> <a class="link-if" href="${s:mvcUrl('AppGame#show').arg(0, sale.game.slug).build()}"> ${sale.game.name} </a> : ${sale.createdAt} </p>
                    </li>
                </c:forEach>
            </ul>

            <p class="m-0">Last Reviews :</p>
            <ul class="list-unstyled">
                <c:forEach items="${lastReviews}" var="review">
                    <li>
                        <p> ${review.user.name} (<a class="link-if" href="${s:mvcUrl('AppGame#show').arg(0, review.game.slug).build()}"> ${review.game.name} </a>) : ${review.createdAt} </p>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
