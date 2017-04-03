<g:applyLayout name="twoColumns">
    <head>
        <title>Resumen por coordinacion y año</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${summary}">
            <table class="table table-hover">
                <caption>${summary.coordination.name} ${summary.year}</caption>
                <thead>
                    <tr>
                        <th>Mes</th>
                        <th>Copias atendidas</th>
                        <th>Copias canceladas</th>
                        <th>Total copias</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${summary.summary.months.flatten()}" var="month">
                        <tr>
                            <td>
                                <g:if test="${month.total}">
                                    <g:link action="summaryByCoordinationAndYearAndMonth" params="[coordinationID: summary.coordination.id, year: summary.year, month: month.month]">
                                        ${month.month}
                                    </g:link>
                                </g:if>
                                <g:else>
                                    ${month.month}
                                </g:else>
                            </td>
                            <td>${month.attended}</td>
                            <td>${month.canceled}</td>
                            <td>${month.total}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
