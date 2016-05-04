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
    <g:set var="companyInformation" value="${grailsApplication.config.ni.edu.uccleon.companyInformation}"/>

    <div class="container">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <h4>${companyInformation.name}</h4>
                <g:layoutBody/>

                <g:if test="${flash?.message}">
                    <br>${flash.message}
                </g:if>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</body>
</html>