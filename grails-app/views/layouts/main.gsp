<!doctype html>
<html lang="en" class="no-js">
<head>
    <title>
        <g:layoutTitle default="ni.edu.uccleon.app"/>
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>
<body>
    <sec:ifLoggedIn>
        <g:render template="/layouts/navbar"/>
    </sec:ifLoggedIn>

    <div class="container-fluid">
        <div class="row">
            <g:layoutBody/>
        </div>
        <br>
    </div>
    <asset:javascript src="application.js"/>
</body>
</html>
