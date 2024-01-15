<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<%
    Object title = request.getAttribute("title");
    if (title == null) {
        title = "Instant-Faking";
    }
    request.setAttribute("title", title);
%>

<html>
    <head>
        <title>${title}</title>
        <link href="<c:url value="../../css/main.css" />" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
        <script type="text/javascript" src="../../js/page/search-bar.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="row w-100">
                <div class="col-2">
                    <a class="navbar-brand ms-3" href="${s:mvcUrl('AppHome#index').build()}">
                        <i class="fa-brands fa-steam fa-2x"></i>
                    </a>
                </div>
                <div class="col-2">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav">
                            <a class="nav-link" href="/platform">Platform</a>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <div class="main-container p-2">
                        <div class="d-flex">
                            <input type="text"
                                   class="form-control"
                                   placeholder="Starcraft, FPS, ..."
                                   data-search-bar-games
                            >
                            <a class="my-auto me-3">
                                <i class="fa fa-magnifying-glass"></i>
                            </a>
                        </div>
                        <div class="search-response-container">
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <a class="nav-link" href="${s:mvcUrl('AppRegister#register').build()}">
                        Créer son compte
                    </a>
                </div>
            </div>
        </nav>