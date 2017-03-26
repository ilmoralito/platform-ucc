<g:applyLayout name="twoColumns">
    <head>
        <title>Lista de copias fuera de rango</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>


        <table class="table table-hover">
            <caption>Copias fuera de rango</caption>
            <colgroup>
                <col span="1" style="width: 12.5%;">
                <col span="1" style="width: 12.5%;">
                <col span="1" style="width: 40%;">
                <col span="1" style="width: 20%;">
                <col span="1" style="width: 10%;">
                <col span="1" style="width: 5%;">
            </colgroup>
            <thead>
                <tr>
                    <th>Coordinacion</th>
                    <th>Solicitado por</th>
                    <th>Descripcion del documento</th>
                    <th>Estado</th>
                    <th>Solicitud</th>
                    <th>Copias</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${copyListOutOfRange}" var="copy">
                    <tr>
                        <td>${copy.coordination.name}</td>
                        <td>${copy.employee.fullName}</td>
                        <td>${copy.documentDescription}</td>
                        <td>
                            <ucc:copyStatus status="${copy.status}"/>
                        </td>
                        <td>${copy.dateCreated}</td>
                        <td>${copy.copies}</td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </content>
</g:applyLayout>
