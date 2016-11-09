<g:applyLayout name="threeColumns">
    <head>
        <title>Vales</title>
    </head>

    <content tag="main">
        <g:if test="${vouchers}">
            <table class="table table-hover">
                <!--<caption>Vales</caption>-->
                <colgroup>
                    <col span="1" style="width: 40%;">
                    <col span="1" style="width: 50%;">
                    <col span="1" style="width: 10%;">
                </colgroup>
                <tbody>
                    <g:each in="${vouchers}" var="voucher">
                        <tr>
                            <td>
                                <g:link action="show" id="${voucher.id}">
                                    <g:formatBoolean boolean="${voucher.user != null}" true="${voucher?.user?.username}" false="${voucher?.guest?.fullName}"/>
                                </g:link>
                            </td>
                            <td>
                                <ucc:foodInSpanish foods="${voucher.foods.name}"/>
                            </td>
                            <td>
                                <g:formatNumber number="${voucher.value}" type="currency" currencyCode="NIO"/>
                            </td>
                        </tr>
                    </g:each>
                    <tr>
                        <td colspan="2"></td>
                        <td>
                            <g:formatNumber number="${vouchers.value.sum()}" type="currency" currencyCode="NIO"/>
                        </td>
                    </tr>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>No hay actividades registradas en esta fecha</p>
        </g:else>
    </content>

    <content tag="right-column">
        <div class="well well-sm">
            <label>Fecha</label>
            <p>${params.date}</p>

            <label>Actividad</label>
            <p>${params.activity}</p>
        </div>

        <div class="well well-sm">
            <g:form action="store" autocomplete="off">
                <g:hiddenField name="date" id="voucherDate" value="${date.format('yyyy-MM-dd')}"/>
                <g:hiddenField name="activity" value="${activity}"/>
                <g:hiddenField name="type" value="${type}"/>

                <g:render template="form"/>

                <g:submitButton name="send" value="Confirmar" class="btn btn-primary btn-block"/>
            </g:form>
        </div>
    </content>
</g:applyLayout>
