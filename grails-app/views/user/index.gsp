<g:applyLayout name="threeColumns">
    <head>
        <title>Usuarios</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 5%;">
                <col span="1" style="width: 95%;">
            </colgroup>
            <thead>
                <th style="text-align: center;">#</th>
                <th>Nombre y apellido</th>
            </thead>
            <tbody>
                <g:each in="${users}" var="user" status="idx">
                    <tr>
                        <td style="text-align: center;">${idx + 1}</td>
                        <td>
                            <g:link action="show" id="${user.id}">${user.username}</g:link>
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
