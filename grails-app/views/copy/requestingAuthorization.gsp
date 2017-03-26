<g:applyLayout name="twoColumns">
    <head>
        <title>Solicitudes de autorizacion</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${extraCopyList}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 1%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 44%;">
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 15%;">
                </colgroup>
                <thead>
                    <tr>
                        <th></th>
                        <th>Coordinacion</th>
                        <th>Solicitado por</th>
                        <th>Descripcion del documento</th>
                        <th>Copias</th>
                        <th>Fecha de solicitud</th>
                    </tr>
                    <tbody>
                        <g:each in="${extraCopyList}" var="extraCopy">
                            <tr>
                                <td>
                                    <g:link action="requestAuthorizationDetail" id="${extraCopy.id}">
                                        <li class="fa fa-folder-o"></li>
                                    </g:link>
                                </td>
                                <td>${extraCopy.coordination.name}</td>
                                <td>${extraCopy.employee.fullName}</td>
                                <td>${extraCopy.documentDescription}</td>
                                <td>${extraCopy.copies}</td>
                                <td>${extraCopy.dateCreated}</td>
                            </tr>
                        </g:each>
                    </tbody>
                </thead>
            </table>
        </g:if>
        <g:else>
            <p>Sin ordenes de copia por autorizar</p>
        </g:else>
    </content>
</g:applyLayout>
