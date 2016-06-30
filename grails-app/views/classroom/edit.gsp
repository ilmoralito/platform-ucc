<g:applyLayout name="twoColumns">
    <head>
        <title>Aula</title>
    </head>

    <content tag="main">
        <g:link action="index" class="btn-back">Regresar</g:link>

        <g:form action="update" name="updateClassroom" autocomplete="off">
            <g:hiddenField name="id" value="${classroom.id}"/>

            <g:render template="form"/>

            <button type="submit" class="btn btn-primary">Confirmar</button>
        </g:form>
    </content>
</g:applyLayout>
