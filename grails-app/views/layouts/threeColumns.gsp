<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="layout" content="main"/>
        <title><g:layoutTitle/></title>
        <g:layoutHead/>
    </head>
    <body>
        <div class="col-md-2">
            <g:render template="/layouts/sidebar"/>
        </div>
        <div class="col-md-8">
            <g:pageProperty name="page.main"/>
        </div>
        <div class="col-md-2">
            <g:pageProperty name="page.right-column"/>

            <g:set var="message" value="${flash.message}"/>
            <g:if test="${message}">
                <div class="message">${message}</div>
            </g:if>
        </div>
    </body>
</html>