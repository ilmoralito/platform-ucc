<g:applyLayout name="twoColumns">
    <head>
        <title>Contrasena</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:form action="password" autocomplete="off">
            <div class="row">
                <div class="col-xs-6">
                    <div class="form-group">
                        <label for="currentPassword">Contrasena actual</label>
                        <g:passwordField name="currentPassword" class="form-control" autofocus="true"/>
                    </div>
                    
                    <div class="form-group">
                        <label for="newPassword">Nueva contrasena</label>
                        <g:passwordField name="newPassword" class="form-control"/>
                    </div>
                    
                    <div class="form-group">
                        <label for="repeatNewPassword">Repetir vueva contrasena</label>
                        <g:passwordField name="repeatNewPassword" class="form-control"/>
                    </div>
                </div>
            </div>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
        </g:form>
    </content>
</g:applyLayout>
