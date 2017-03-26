<table class="table table-hover">
    <colgroup>
        <col span="1" style="width: 25%;">
        <col span="1" style="width: 75%;">
    </colgroup>
    <tbody>
        <g:if test="${actionName.toLowerCase().contains('detail')}">
            <tr>
                <td style="border-top: 0;">Solicitado por</td>
                <td style="border-top: 0;">${copy.employee.fullName}</td>
            </tr>
            <tr>
                <td>Coordinacion</td>
                <td>${copy.coordination.name}</td>
            </tr>
        </g:if>
        <tr>
            <td>Copias</td>
            <td>${copy.copies}</td>
        </tr>
        <tr>
            <td>Estado</td>
            <td>
                <ucc:copyStatus status="${copy.status}"/>
            </td>
        </tr>
        <tr>
            <td>Fecha de solicitud</td>
            <td>${copy.dateCreated}</td>
        </tr>
        <tr>
            <td>Descripcion del documento</td>
            <td>${copy.documentDescription}</td>
        </tr>
        <g:if test="${copy.type == 'extraCopy'}">
            <tr>
                <td>Motivo de copia extra</td>
                <td>${copy.description}</td>
            </tr>
        </g:if>
    </tbody>
</table>
