<g:applyLayout name="twoColumns">
    <head>
        <title>Cancelar vale</title>
    </head>

    <content tag="main">
        <section class="clearfix">
            <div class="pull-right">
                <g:link action="show" id="${voucher.id}" class="btn btn-default">Regresar</g:link>
            </div>
        </section>

        <g:form action="cancel" autocomplete="off">
            <g:hiddenField name="id" value="${voucher.id}"/>

            <div class="form-group">
                <label>Motivo de la anulacion</label>
                <g:textArea name="reasonForCancellation" value="${voucher?.reasonForCancellation}" class="form-control"/>
            </div>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
        </g:form>

        <br>
        <g:hasErrors bean="${voucher}">
            <g:renderErrors bean="${voucher}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
