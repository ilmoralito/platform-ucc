<g:applyLayout name="threeColumns">
    <head>
        <title>Vales</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${vouchers}">
            <g:if test="${params.status == 'pending'}">
                <g:render template="vouchersByDateAndActivity" vouchers="${vouchers}" model="[form: 'notify']"/>

                <g:form name="notify" action="sendNotification">
                    <g:submitButton name="send" value="Notificar" class="btn btn-primary"/>
                </g:form>
            </g:if>
            <g:elseif test="${params.status == 'notified'}">
                <g:render template="vouchers" vouchers="${vouchers}"/>
            </g:elseif>
        </g:if>
        <g:else>
            <p>No hay vales que mostrar</p>
        </g:else>
    </content>

    <content tag="right-column">
        <g:render template="createVoucher"/>
    </content>
</g:applyLayout>
