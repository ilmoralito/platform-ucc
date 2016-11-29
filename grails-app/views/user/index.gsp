<g:applyLayout name="threeColumns">
    <head>
        <title>Usuarios</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <thead>
                <th>Usuarios</th>
            </thead>
            <tbody>
                <g:each in="${users}" var="user">
                    <tr>
                        <td>
                            <g:link action="edit" id="${user.id}">${user.username}</g:link>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </content>

    <content tag="right-column">
        <g:if test="${employees}">
            <g:form action="create" autocomplete="off">
                <div class="form-group">
                    <ucc:getEmployeeList employees="${employees}"/>
                </div>

                <div id="target"></div>

                <g:submitButton name="send" value="Confirmar" class="btn btn-primary btn-block"/>
            </g:form>

            <script id="template" type="x-tmpl-mustache">
                <label>Nombre</label>
                <p>{{ fullName }}</p>

                <label>Mail</label>
                <p>{{ institutionalMail }}</p>

                <label>Puesto</label>
                <p>{{ position }}</p>

                <label>Rol</label>
                <p>{{ authority }}</p>

                <label>Numero de cedula</label>
                <p>{{ identityCard }}</p>

                <label>Numero de INSS</label>
                <p>{{ inss }}</p>

                {{ #coordinations }}
                    <label>Coordinacion</label>
                    <p>{{ name }}</p>

                    <label>Numero de extension</label>
                    <p>{{ extensionNumber }}</p>

                    <label>Direccion</label>
                    <p>{{ location }}</p>
                {{ /coordinations }}
            </script>
        </g:if>
        <g:else>
            <p>Todos los empleados fueron agregados</p>
        </g:else>
    </content>
</g:applyLayout>
