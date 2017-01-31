<g:applyLayout name="threeColumns">
    <head>
        <title>Cancelados</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${summary}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 75%;">
                </colgroup>
                <thead>
                    <tr>
                        <th>Año</th>
                        <th>Numero de vales cancelados</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${summary}" var="data">
                        <tr>
                            <td>
                                <g:link action="vouchersByYear" params="[year: data.year]">
                                    ${data.year}
                                </g:link>
                            </td>
                            <td>${data.count}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>

    <content tag="right-column">
        <g:render template="createVoucher"/>
    </content>
</g:applyLayout>
