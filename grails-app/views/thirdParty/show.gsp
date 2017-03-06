<g:applyLayout name="twoColumns">
    <head>
        <title>Tercero</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <g:link action="edit" id="${thirdParty.id}" class="btn btn-default pull-right">Editar</g:link>
        </div>

        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>
            <tbody>
                <tr>
                    <td>Nombre</td>
                    <td>${thirdParty.name}</td>
                </tr>
                <tr>
                    <td>Estado</td>
                    <td>
                        <ucc:enabled enabled="${thirdParty.enabled}"/>
                    </td>
                </tr>
            </tbody>
        </table>

        <g:if test="${thirdParty.thirdPartyEmployees}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 37.5%;">
                    <col span="1" style="width: 37.5%;">
                </colgroup>
                <thead>
                    <tr>
                        <th>Nombre completo</th>
                        <th>Email</th>
                        <th>Estado</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${thirdParty.thirdPartyEmployees}" var="thirdPartyEmployee">
                        <tr>
                            <td>${thirdPartyEmployee.fullName}</td>
                            <td>${thirdPartyEmployee.email}</td>
                            <td>
                                <ucc:enabled enabled="${thirdPartyEmployee.enabled}"/>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin empleados registrados</p>
        </g:else>
    </content>
</g:applyLayout>
