<g:applyLayout name="twoColumns">
    <head>
        <title>Usuario</title>
    </head>

    <content tag="main">
        <g:link action="index" class="btn-back">Regresar</g:link>

        <g:form action="update" autocomplete="off">
            <g:hiddenField name="id" value="${user.id}"/>
            <g:render template="form"/>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary pull-right"/>
        </g:form>
    </content>

    <content tag="right-column">
    </content>
</g:applyLayout>
