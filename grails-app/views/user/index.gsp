<g:applyLayout name="threeColumns">
    <head>
        <title>Usuarios</title>
    </head>

    <content tag="main">
        <table class="table table-hover table-striped">
            <colgroup>
                <col span="1" style="width: 1%;">
                <col span="1" style="width: 99%;">
            </colgroup>
            <thead>
                <th>#</th>
                <th>Nombre y apellido</th>
            </thead>
            <tbody>
                <g:each in="${users}" var="user" status="idx">
                    <tr>
                        <td>${idx + 1}</td>
                        <td>
                            <g:link action="show" id="${user.id}">${user.username}</g:link>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </content>

    <content tag="right-column">
        <g:form action="index" autocomplete="off">
            <div class="form-group">
                <ucc:getEmployee/>
            </div>

            <div id="target"></div>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary btn-block"/>
        </g:form>

        <script id="template" type="x-tmpl-mustache">
            <div class='panel panel-default'>
                <table class='table table'>
                    <tr>
                        <td colspan='2'><b>Nombre</b></td>
                    </tr>
                    <tr>
                        <td colspan='2'>{{ fullName }}</td>
                    </tr>

                    <tr>
                        <td colspan='2'><b>Email</b></td>
                    </tr>
                    <tr>
                        <td colspan='2'>{{ institutionalMail }}</td>
                    </tr>

                    <tr>
                        <td colspan='2'><b>Puesto</b></td>
                    </tr>
                    <tr>
                        <td colspan='2'>{{ position }}</td>
                    </tr>

                    <tr>
                        <td colspan='2'><b>Rol</b></td>
                    </tr>
                    <tr>
                        <td colspan='2'>{{ authority }}</td>
                    </tr>

                    <tr>
                        <td colspan='2'><b>Cedula</b></td>
                    </tr>
                    <tr>
                        <td colspan='2'>{{ identityCard }}</td>
                    </tr>

                    <tr>
                        <td colspan='2'><b>INSS</b></td>
                    </tr>
                    <tr>
                        <td colspan='2'>{{ inss }}</td>
                    </tr>

                    <tr>
                        <td colspan='2'><b>Coordinacion</b></td>
                    </tr>
                    <tr>
                        <td colspan='2'>{{ coordination.name }}</td>
                    </tr>

                    <tr>
                        <td colspan='2'><b>Extension</b></td>
                    </tr>
                    <tr>
                        <td colspan='2'>{{ coordination.extensionNumber }}</td>
                    </tr>
                </table>
            </div>
        </script>
    </content>
</g:applyLayout>
