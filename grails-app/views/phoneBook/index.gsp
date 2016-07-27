<g:applyLayout name="oneColumn">
    <head>
        <title>Directorio telefonico</title>
    </head>

    <content tag="main">
        <g:render template="/layouts/nav"/>

        <table class="table table-hover table-bordered">
            <colgroup>
                <col span="1" style="width: 5%;">
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 45%;">
            </colgroup>
            <thead>
                <th>Extension</th>
                <th>Coordinacion</th>
                <th>Director</th>
                <th>Asistentes</th>
            </thead>
            <tbody>
                <g:each in="${phoneBook}" var="pb">
                    <g:if test="${pb.coordinations.size() == 1}">
                        <tr>
                            <td class="text-center" rowspan="${pb.coordinations.size()}">
                                ${pb.extensionNumber}
                            </td>
                            <td>${pb.coordinations[0].name}</td>
                            <td>${pb.coordinations[0].manager}</td>
                            <td>${pb.coordinations[0].assistants}</td>
                        </tr>
                    </g:if>
                    <g:else>
                        <g:each in="${pb.coordinations}" var="c" status="index">
                            <tr>
                                <g:if test="${index == 0}">
                                    <td class="text-center" style="vertical-align: middle;" rowspan="${pb.coordinations.size()}">
                                        ${pb.extensionNumber}
                                    </td>
                                </g:if>
                                <td>${c.name}</td>
                                <td>${c.manager}</td>
                                <td>${c.assistants}</td>
                            </tr>
                        </g:each>
                    </g:else>
                </g:each>
            </tbody>
        </table>

        <g:link action="printPhoneBook" class="btn btn-primary">
            <i class="fa fa-print" aria-hidden="true"></i>
        </g:link>
    </content>
</g:applyLayout>
