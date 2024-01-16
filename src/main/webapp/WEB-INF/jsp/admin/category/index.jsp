<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../tag.jsp" %>
<c:set var="title" scope="request" value="Categories"/>
<jsp:include flush="true" page="../admin.jsp"/>

        <div class="container col-9">
            <h1>Available categories</h1>
            <a class="btn btn-link" href="${UrlRoute.URL_ADMIN_CATEGORY_NEW}">
                New
            </a>
            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${categories}" var="category">
                        <tr>
                            <td>
                                ${category.name}
                            </td>
                            <td>
                                <a class="btn-link" href="${UrlRoute.URL_ADMIN_CATEGORY_EDIT}/${category.id}">
                                    Edit
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="../../footer.jsp" %>
