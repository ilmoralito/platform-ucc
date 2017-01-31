<g:applyLayout name="threeColumns">
    <head>
        <title>Vales</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>
        <div class="clearfix">
            <g:link action="approvalDates" class="btn btn-default pull-right">Regresar</g:link>
        </div>

        <g:if test="${summary}">
            <table class="table table-hover">
                
                <thead>
                    <tr>
                        <th colspan="2">${params.year}</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${summary}" var="data">
                        <tr>
                            <td colspan="2">${data.month}</td>
                        </tr>
                        <tr>
                            <td>Dias</td>
                            <td>Numero de vales aprobados</td>
                        </tr>
                        <g:each in="${data.dates}" var="approvalDate">
                            <tr>
                                <td>
                                    <g:link action="approved" params="[approvalDate: approvalDate.date]">
                                        <g:formatDate date="${approvalDate.date}" format="d"/>
                                    </g:link>
                                </td>
                                <td>${approvalDate.count}</td>
                            </tr>
                        </g:each>
                        <tr>
                            <td></td>
                            <td>${data.count}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar en ${params.year}</p>
        </g:else>

        <%--
            <g:if test="${approvalDates}">
                <table class="table table-hover">
                    <tbody>
                        <g:each in="${approvalDates}" var="date">
                            <tr>
                                <td>
                                    <g:link action="approved" params="[approvalDate: date.format('yyyy-MM-dd')]">
                                        <g:formatDate date="${date}" format="yyyy-MM-dd"/>
                                    </g:link>
                                </td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </g:if>
            <g:else>
                <p>No hay vales aprobados que mostrar</p>
            </g:else>
        --%>
    </content>

    <content tag="right-column">
        <g:render template="createVoucher"/>
    </content>
</g:applyLayout>
