<g:applyLayout name="threeColumns">
    <head>
        <title>Cancelados</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <p>${params.year}</p>
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 35%;">
                <col span="1" style="width: 65%;">
            </colgroup>
            <thead>
                <tr>
                    <td>Mes</td>
                    <td>Cantidad de vales cancelados en el mes</td>
                </tr>
            </thead>
            <g:each in="${voucherList}" var="voucher">
                <tbody>
                    <tr>
                        <td>${voucher.month}</td>
                        <td>${voucher.count}</td>
                    </tr>
                    <g:each in="${voucher.vouchers}" var="v">
                        <tr>
                            <td>
                                <g:link action="show" id="${v.id}">
                                    ${v.member}
                                </g:link>
                            </td>
                            <td>
                                <ucc:slug text="${v?.reasonForCancellation}"/>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </g:each>
        </table>
    </content>

    <content tag="right-column">
        <g:render template="createVoucher"/>
    </content>
</g:applyLayout>
