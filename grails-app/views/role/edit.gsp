<g:applyLayout name="twoColumns">
    <head>
        <title>Rol</title>
    </head>

    <content tag="main">
        <g:form action="update" autocomplete="off">
            <g:hiddenField name="id" value="${role.id}"/>
            <g:render template="form"/>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
        </g:form>

        <br>
        <g:hasErrors bean="${flash?.errors}">
            <g:renderErrors bean="${flash?.errors}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
