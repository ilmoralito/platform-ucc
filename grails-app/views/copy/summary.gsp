<g:applyLayout name="twoColumns">
    <head>
        <title>Resumen</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25.5%;">
                <col span="1" style="width: 25.5%;">
                <col span="1" style="width: 25.5%;">
                <col span="1" style="width: 25.5%;">
            </colgroup>
            <thead>
                <tr>
                    <th>Año</th>
                    <th>Copias atendidas</th>
                    <th>Copias canceladas</th>
                    <th>Total copias</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${summary}" var="s">
                    <tr>
                        <td colspan="4">${s.coordination.name}</td>
                    </tr>
                    <g:each in="${s.summary}" var="context">
                        <tr>
                            <td>
                                <g:link action="summaryByCoordinationAndYear" params="[coordinationID: s.coordination.id, year: context.year]">
                                    ${context.year}
                                </g:link>
                            </td>
                            <td>${context.attended}</td>
                            <td>${context.canceled}</td>
                            <td>${context.total}</td>
                        </tr>
                    </g:each>
                    <g:if test="${s.summary.size() > 1}">
                        <tr>
                            <td>TOTAL</td>
                            <td>${s.summary.attended.sum()}</td>
                            <td>${s.summary.canceled.sum()}</td>
                            <td>${s.summary.total.sum()}</td>
                        </tr>
                    </g:if>
                </g:each>
            </tbody>
        </table>
    </content>
</g:applyLayout>
