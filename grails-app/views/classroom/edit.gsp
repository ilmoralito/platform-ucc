<g:applyLayout name="twoColumns">
    <head>
        <title>Aula</title>
    </head>

    <content tag="main">
        <div class="row">
            <div class="col-md-6">
                <g:form action="update" name="updateClassroom" autocomplete="off">
                    <g:hiddenField name="id" value="${classroom.id}"/>
                    <g:render template="form"/>

                    <button type="submit" class="btn btn-primary">Confirmar</button>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
