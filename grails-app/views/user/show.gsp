<g:applyLayout name="twoColumns">
    <head>
        <title>Empleado</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <g:link action="edit" id="${employee.id}" class="btn btn-default pull-right">Editar</g:link>
        </div>
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>
            <tbody>
                <tr>
                    <td>Nombre y apellido</td>
                    <td>${employee.fullName}</td>
                </tr>
                <tr>
                    <td>Correo institucional</td>
                    <td>${employee.institutionalMail}</td>
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
                    <td colspan="2">Datos de coordinacion</td>
                </tr>
                <g:each in="${employee.coordinations}" var="coordination">
                    <tr>
                        <td>Nombre</td>
                        <td>
                            <g:link controller="coordination" action="show" params="[name: coordination.name]">
                                ${coordination.name}
                            </g:link>
                        </td>
                    </tr>
                    <tr>
                        <td>Area</td>
                        <td>
                            <ucc:coordinationLocation location="${coordination.location}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Numero de extension</td>
                        <td>${coordination.extensionNumber}</td>
                    </tr>
                </g:each>
                <tr>
                    <td colspan="2">Roles</td>
                </tr>
                <tr>
                    <td>Autoridades</td>
                    <td><g:join in="${user.authorities.authority*.replaceAll('_', ' ')}"/></td>
                </tr>
            </tbody>
        </table>
    </content>
</g:applyLayout>
