<%@ include file="../base.jsp" %>

<div class="container">
    <h1>Available platforms</h1>
    <a class="btn btn-link" href="${s:mvcUrl('AppPlatform#new').build()}">
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
                        <a class="btn-link" href="${s:mvcUrl('AppPlatform#edit').arg(2, platform.id).build()}">
                            Edit
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="../footer.jsp" %>
