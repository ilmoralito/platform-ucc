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
                    <tr>
                        <td>${pb.extensionNumber}</td>
                        <td>${pb.coordinationName}</td>
                        <td>${pb.manager}</td>
                        <td>${pb.assistants}</td>
                    </tr>
                </g:each>
            </tbody>
        </table>

        <g:link action="printPhoneBook" class="btn btn-primary">
            <i class="fa fa-print" aria-hidden="true"></i>
        </g:link>
    </content>
</g:applyLayout>
