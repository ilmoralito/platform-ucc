<g:applyLayout name="twoColumns">
    <head>
        <title>Editar cliente externo</title>
    </head>

    <content tag="main">
        <g:form action="update">
            <g:hiddenField name="id" value="${externalCustomer.id}"/>
            <div class="row">
                <div class="col-md-6">
                    <g:render template="form"/>
                </div>
            </div>

            <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>
        </g:form>

        <g:hasErrors bean="${externalCustomer}">
            <g:renderErrors bean="${externalCustomer}" as="list" />
        </g:hasErrors>
    </content>
</g:applyLayout>
