<g:applyLayout name="twoColumns">
    <head>
        <title>Coordinacion</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <g:link action="edit" id="${coordination.id}" class="btn btn-default pull-right">Editar</g:link>
        </div>

        <table class="table">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>
            <tbody>
                <tr>
                    <td>Nombre</td>
                    <td>${coordination.name}</td>
                </tr>
                <tr>
                    <td>Numero de extension</td>
                    <td>${coordination.extensionNumber}</td>
                </tr>
                <tr>
                    <td>Cuota de copias</td>
                    <td>${coordination.printQuota}</td>
                </tr>
                <tr>
                    <td>Area</td>
                    <td>${coordination.location}</td>
                </tr>
                <tr>
                    <td>Director</td>
                    <td>${coordination?.manager?.fullName ?: 'Sin asignar'}</td>
                </tr>
                <tr>
                    <td>Colaboradores</td>
                    <td>
                        <g:if test="${coordination.assistants}">
                            <g:join in="${coordination.assistants.fullName}"/>
                        </g:if>
                        <g:else>
                            Sin asignar
                        </g:else>
                    </td>
                </tr>
                <g:if test="${coordination.colors}">
                    <tr>
                        <td>Colores</td>
                        <td>
                            <g:join in="${coordination.colors.name}"/>
                        </td>
                    </tr>
                </g:if>
            </tbody>
        </table>

    </content>
</g:applyLayout>
