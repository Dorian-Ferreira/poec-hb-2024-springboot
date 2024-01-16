<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../tag.jsp" %>
<c:set var="title" scope="request" value="Platforms"/>
<jsp:include flush="true" page="../admin.jsp"/>

        <div class="container col-9">
            <h1>Available platforms</h1>
            <a class="btn btn-link" href="${UrlRoute.URL_ADMIN_PLATFORM_NEW}">
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
                    <c:forEach items="${platforms}" var="platform">
                        <tr>
                            <td>
                                ${platform.name}
                            </td>
                            <td>
                                <a class="btn-link" href="${UrlRoute.URL_ADMIN_PLATFORM_EDIT}/${platform.id}">
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
