<g:applyLayout name="twoColumns">
    <head>
        <title>Perfil</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 20%;">
                <col span="1" style="width: 80%;">
            </colgroup>
            <tbody>
                <tr>
                    <td style="border-top: 0;">Nombre y apellido</td>
                    <td style="border-top: 0;">${employee.fullName}</td>
                </tr>
                <tr>
                    <td>Correo institucional</td>
                    <td>${employee.institutionalMail}</td>
                </tr>
                <tr>
                    <td>Autoridad</td>
                    <td>
                        <g:formatBoolean boolean="${employee.authority == 'Manager'}" true="Responsable" false="Asistente"/>
                    </td>
                </tr>
                <tr>
                    <td>Cedula</td>
                    <td>${employee.identityCard}</td>
                </tr>
                <tr>
                    <td>Numero de INSS</td>
                    <td>${employee.inss}</td>
                </tr>
                <tr>
                    <td>Coordinacion</td>
                    <td>
                        <g:join in="${employee.coordinations.name}"/>
                    </td>
                </tr>
                <tr>
                    <td>Autoridades</td>
                    <td>
                        <g:join in="${currentUserAuthorities}"/>
                    </td>
                </tr>
            </tbody>
        </table>
    </content>
</g:applyLayout>
