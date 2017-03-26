<g:applyLayout name="threeColumns">
    <head>
        <title>Reporte</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${report}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 60%;">
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <tr>
                        <th>Nombre de coordinacion</th>
                        <th>Cuota</th>
                        <th>Copias</th>
                        <th>Copias extra</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${report}" var="reportInstance">
                        <tr>
                            <td colspan="5">
                                <ucc:coordinationLocation location="${reportInstance.location}"/>
                            </td>
                        </tr>
                        <g:each in="${reportInstance.coordinations}" var="coordination">
                            <tr>
                                <td>${coordination.name}</td>
                                <td>${coordination.copyFee}</td>
                                <td>${coordination.copies}</td>
                                <td>${coordination.extraCopies}</td>
                                <td>${coordination.totalCopies}</td>
                            </tr>
                        </g:each>
                        <tr>
                            <td colspan="2">SUB TOTAL</td>
                            <td>${reportInstance.totalCopies}</td>
                            <td>${reportInstance.totalExtraCopies}</td>
                            <td>${reportInstance.totalCopiesInMonth}</td>
                        </tr>
                    </g:each>
                    <tr>
                        <td colspan="2">TOTAL</td>
                        <td>${report.totalCopies.sum()}</td>
                        <td>${report.totalExtraCopies.sum()}</td>
                        <td>${report.totalCopiesInMonth.sum()}</td>
                    </tr>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>No existen registros en estos parametros</p>
        </g:else>

        <g:if test="${copiesOutOfRange}">
            <g:link action="reportCopyListOutOfRange">* ${copiesOutOfRange} copias fuera de rango</g:link>
        </g:if>
    </content>

    <content tag="right-column">
        <g:render
            template="copyReportFilterWidget"
            model="[
                yearList: copyReportFilter.yearList,
                monthList: copyReportFilter.monthList,
                currentYear: copyReportFilter.currentYear,
                currentMonth: copyReportFilter.currentMonth]"/>
    </content>
</g:applyLayout>
