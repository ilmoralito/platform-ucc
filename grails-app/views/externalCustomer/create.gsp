<g:applyLayout name="twoColumns">
    <head>
        <title>Cliente externo</title>
    </head>

    <content tag="main">
        <g:form action="create" autocomplete="off">
            <g:render template="form"/>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
        </g:form>

        <br>
        <g:hasErrors bean="${externalCustomer}">
            <g:renderErrors bean="${externalCustomer}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
