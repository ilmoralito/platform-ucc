<g:applyLayout name="twoColumns">
    <head>
        <title>Invitado</title>
    </head>

    <content tag="main">
        <g:form action="update" autocomplete="off">
            <g:hiddenField name="id" value="${params.id}"/>

            <g:render template="form"/>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
        </g:form>
    </content>
</g:applyLayout>
