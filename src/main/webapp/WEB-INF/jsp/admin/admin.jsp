<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
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
        <link href="${contextPath}/css/main.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
        <script type="text/javascript" src="${contextPath}/js/page/multiple-select.js"></script>
    </head>
    <body>


<div class="container">
    <div class="row">
        <div class="col-2">
            <h1> Admin </h1>
            <h2><a class="link-if" href="${UrlRoute.URL_ADMIN}"> Panel </a></h2>
            <h2><a class="link-if" href="${UrlRoute.URL_ADMIN_PLATFORM}"> Platform </a></h2>
            <h2><a class="link-if" href="${UrlRoute.URL_ADMIN_COUNTRY}"> Country </a></h2>
            <h2><a class="link-if" href="${UrlRoute.URL_ADMIN_CATEGORY}"> Category </a></h2>
            <h2><a class="link-if" href="${UrlRoute.URL_ADMIN_PUBLISHER}"> Publisher </a></h2>
            <h2><a class="link-if" href="${UrlRoute.URL_ADMIN_GAME}"> Game </a></h2>
        </div>