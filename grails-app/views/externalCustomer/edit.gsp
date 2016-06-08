<g:applyLayout name="twoColumns">
    <head>
        <title>Editar cliente externo</title>
    </head>

    <content tag="main">
        <g:form name="updateExternalCustomerForm" action="update" autocomplete="off">
            <g:hiddenField name="id" value="${externalCustomer.id}"/>
            <g:render template="form"/>

            <g:submitButton name="send" value="Actualizar" class="btn btn-primary pull-right"/>
        </g:form>

        <g:hasErrors bean="${flash.bag}">
            <br>
            <g:renderErrors bean="${flash.bag}" as="list"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
