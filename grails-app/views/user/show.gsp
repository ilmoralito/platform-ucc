<g:applyLayout name="threeColumns">
    <head>
        <title>Usuario</title>
    </head>

    <content tag="main">
        <g:form action="update">
            <g:render template="form"/>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
        </g:form>
    </content>

    <content tag="right-column">
    </content>
</g:applyLayout>
