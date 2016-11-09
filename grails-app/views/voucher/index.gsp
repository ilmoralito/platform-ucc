<g:applyLayout name="threeColumns">
    <head>
        <title>Vales</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${vouchers}">
            <g:render template="vouchersByDateAndActivity" vouchers="${vouchers}" model="[form: 'notify']"/>

            <g:if test="${params?.status == 'pending'}">
                <g:form name="notify" action="sendNotification">
                    <g:submitButton name="send" value="Notificar" class="btn btn-primary"/>
                </g:form>
            </g:if>
        </g:if>
        <g:else>
            <p>No hay vales que mostrar</p>
        </g:else>
    </content>

    <content tag="right-column">
        <g:render template="createVoucher"/>
    </content>
</g:applyLayout>
