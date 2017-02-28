<g:applyLayout name="twoColumns">
    <head>
        <title>Copia</title>
    </head>

    <content tag="main">
        <section class="clearfix">
            <g:link action="show" id="${copy.id}" class="btn btn-default">Regresar</g:link>
        </section>

        <g:form action="update" autocomplete="off">
            <g:hiddenField name="id" value="${copy.id}"/>
            <g:render template="form"/>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
        </g:form>
    </content>
</g:applyLayout>

