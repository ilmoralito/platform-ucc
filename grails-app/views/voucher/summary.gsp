<g:applyLayout name="twoColumns">
    <head>
        <title>Vales a aprobar</title>
    </head>

    <content tag="main">
        <g:render template="toApproveNav"/>

        <g:if test="${vouchers}">
            <g:each in="${vouchers}" var="voucher">
                <table class="table table-hover table-bordered">
                    <colgroup>
                        <col span="1" style="width: 14.285714286%;">
                        <col span="1" style="width: 14.285714286%;">
                        <col span="1" style="width: 14.285714286%;">
                        <col span="1" style="width: 14.285714286%;">
                        <col span="1" style="width: 14.285714286%;">
                        <col span="1" style="width: 14.285714286%;">
                        <col span="1" style="width: 14.285714286%;">
                    </colgroup>

                    <caption>Resumen ${voucher.year}</caption>
                    <thead>
                        <tr>
                            <th colspan="3"></th>
                            <th colspan="2">Visita</th>
                            <th colspan="2">Interno</th>
                        </tr>
                        <tr>
                            <th>Mes</th>
                            <th>Total</th>
                            <th>Total NIO</th>
                            <th>Total</th>
                            <th>Total NIO</th>
                            <th>Total</th>
                            <th>Total NIO</th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${voucher.summary}" var="summary">
                            <tr>
                                <td>${summary.month}</td>
                                <td>${summary.total}</td>
                                <td>
                                    <g:formatNumber number="${summary.totalNIO}" type="currency" currencyCode="NIO"/>
                                </td>
                                <td>${summary.totalGuest}</td>
                                <td>
                                    <g:formatNumber number="${summary.totalGuestNIO}" type="currency" currencyCode="NIO"/>
                                </td>
                                <td>${summary.totalInternal}</td>
                                <td>
                                    <g:formatNumber number="${summary.totalInternalNIO}" type="currency" currencyCode="NIO"/>
                                </td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </g:each>


            <g:link action="printSummary" class="btn btn-primary">
                <i class="fa fa-print" aria-hidden="true"></i> Imprimir
            </g:link>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
