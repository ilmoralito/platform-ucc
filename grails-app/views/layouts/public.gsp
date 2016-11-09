<!doctype html>
<html lang="en" class="no-js">
<head>
    <title>
        <g:layoutTitle default="ni.edu.uccleon.app"/>
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:stylesheet src="public.css"/>
    <g:layoutHead/>
</head>
<body>
    <g:render template="/layouts/navbar"/>

    <div class="container">
        <div class="row">
            <g:layoutBody/>
        </div>
    </div>

    <asset:javascript src="app.js"/>
</body>
</html>
