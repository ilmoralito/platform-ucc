<g:applyLayout name="threeColumns">
    <head>
        <title>Solicitud de autorizacion</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:render template="copy" model="[copy: extraCopy]"/>
    </content>

    <content tag="right-column">
        <section>
            <g:form action="authorizeCopyOrder" autocomplete="off">
                <g:hiddenField name="id" value="${extraCopy.id}"/>
                <button type="submit" class="btn btn-warning btn-block">Autorizar</button>
            </g:form>
        </section>

        <div class="panel panel-danger">
            <div class="panel-heading">Zona de peligro</div>
            <div class="panel-body">
                <g:form action="denyCopyOrder" autocomplete="off">
                    <g:hiddenField name="id" value="${extraCopy.id}"/>
                    <div class="form-group">
                        <label for="reasonForCancellation">Motivo de la cancelacion</label>
                        <g:textArea name="reasonForCancellation" class="form-control"/>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
