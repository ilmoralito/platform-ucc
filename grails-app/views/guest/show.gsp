<g:applyLayout name="threeColumns">
    <head>
        <title>Invitado</title>
    </head>

    <content tag="main">
        <g:form name="updateGuestForm" action="update">
            <g:hiddenField name="id" value="${params.id}"/>

            <label for="fullName">Nombre completo</label>
            <div class="form-group">
                <g:textField name="fullName" value="${guest.fullName}" class="form-control"/>
            </div>

            <div class="checkbox">
                <label>
                    <g:checkBox name="enabled" value="${guest.enabled}"/>
                    Habilitado
                </label>
            </div>

            <g:submitButton name="send" value="Agregar" class="btn btn-primary"/>
        </g:form>
    </content>
</g:applyLayout>
