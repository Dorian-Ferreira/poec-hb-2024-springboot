<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../tag.jsp" %>
<c:set var="title" scope="request" value="Countries"/>
<jsp:include flush="true" page="../admin.jsp"/>

        <div class="container col-9">
            <h1>Available countries</h1>
            <a class="btn btn-link" href="${UrlRoute.URL_ADMIN_COUNTRY_NEW}">
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
                    <c:forEach items="${countries}" var="country">
                        <tr>
                            <td>
                                ${country.name}
                            </td>
                            <td>
                                <a class="btn-link" href="${UrlRoute.URL_ADMIN_COUNTRY_EDIT}/${country.id}">
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