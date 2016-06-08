<g:applyLayout name="twoColumns">
    <head>
        <title>Cliente externo</title>
    </head>

    <content tag="main">
        <g:form action="create" autocomplete="off">
            <g:render template="form"/>

            <g:submitButton name="send" value="Agregar" class="btn btn-primary pull-right"/>
        </g:form>
    </content>
</g:applyLayout>
