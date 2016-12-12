<g:applyLayout name="twoColumns">
    <head>
        <title>Usuario</title>
    </head>

    <content tag="main">
        <div class="row">
            <div class="col-md-6">
                <g:form action="update" autocomplete="off">
                    <g:hiddenField name="id" value="${user.id}"/>
                    <div class="form-group">
                        <label for="username">Nombre de usuario</label>
                        <g:textField name="username" value="${user.username}" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label style="margin-bottom: 0;">Roles</label>
                        <g:each in="${roles}" var="role">
                            <g:set var="authority" value="${role.authority}"/>

                            <div class="checkbox">
                                <label>
                                    <g:checkBox name="authorities" value="${authority}" checked="${authority in user.authorities.authority}"/>
                                    ${authority.tokenize('_')[1..-1].join(' ')}
                                </label>
                            </div>
                        </g:each>
                    </div>

                    <div class="form-group">
                        <label for="enabled">Estado</label>
                        <div class="checkbox">
                            <label>
                                <g:checkBox name="enabled" value="${user.enabled}"/>
                                <g:formatBoolean boolean="${user.enabled}" true="Habilitado" false="No habilitado"/>
                            </label>
                        </div>
                    </div>

                    <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
                </g:form>
            </div>
            <div class="col-md-6">
                <g:form action="updateEmployee" autocomplete="off">
                    <g:hiddenField name="id" value="${user.id}"/>
                    <g:hiddenField name="employeeId" value="${user.employee}"/>
                    <g:render template="form"/>

                    <g:submitButton name="updateEmployee" value="Confirmar" class="btn btn-primary"/>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
