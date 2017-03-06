<g:applyLayout name="twoColumns">
    <head>
        <title>Empleado</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <g:link action="edit" params="[thirdPartyId: thirdPartyEmployee.thirdParty.id, thirdPartyEmployeeId: thirdPartyEmployee.id]" class="btn btn-default pull-right">Editar</g:link>
        </div>

        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>
            <tbody>
                <tr>
                    <td>Nombre completo</td>
                    <td>${thirdPartyEmployee.fullName}</td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>${thirdPartyEmployee.email}</td>
                </tr>
                <tr>
                    <td>Estado</td>
                    <td>
                        <ucc:enabled enabled="${thirdPartyEmployee.enabled}"/>
                    </td>
                </tr>
            </tbody>
        </table>
    </content>
</g:applyLayout>
