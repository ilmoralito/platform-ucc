<g:applyLayout name="twoColumns">
    <head>
        <title>Vale</title>
    </head>

    <content tag="main">
        <g:link action="show" id="${voucher.id}">Regresar</g:link>

        <div class="row">
            <div class="col-md-6">
                <g:form action="update" autocomplete="off">
                    <g:hiddenField name="id" value="${voucher.id}"/>

                    <g:render template="form"/>

                    <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
