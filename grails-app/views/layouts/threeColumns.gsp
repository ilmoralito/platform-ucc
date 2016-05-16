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

            <g:if test="${flash.message}">
                <p><br>${flash.message}</p>
            </g:if>
        </div>
        <div class="col-md-2">
            <g:pageProperty name="page.right-column"/>
        </div>
    </body>
</html>