<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><g:layoutTitle default="uccleon"/></title>
    <asset:stylesheet src="auth.css"/>
    <g:layoutHead/>
</head>
<body>
    <g:render template="/layouts/navbar"/>

    <div class="container">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <div class="well">
                    <div class="text-center">
                        <asset:image src="logoucc.png"/>
                    </div>

                    <br>
                    <g:layoutBody/>

                    <g:if test="${flash?.message}">
                        <br>
                        <p>${flash.message}</p>
                    </g:if>
                </div>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>

    <asset:javascript src="app.js"/>
</body>
</html>