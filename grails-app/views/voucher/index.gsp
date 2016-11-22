<g:applyLayout name="threeColumns">
    <head>
        <title>Vales</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${vouchers}">
            <g:if test="${params.status == 'pending'}">
                <g:render template="vouchersByDateAndActivity" vouchers="${vouchers}"/>

                <div class="clearfix">
                    <g:form name="notify" action="sendNotification" class="pull-left" style="margin-right: 5px;">
                        <g:submitButton name="send" value="Notificar" class="btn btn-primary"/>
                    </g:form>

                    <g:form name="batch" action="batchDelete">
                        <g:hiddenField name="returnPlace" value="index"/>

                        <g:submitButton name="send" value="Eliminar" class="btn btn-primary btn-danger"/>
                    </g:form>
                </div>
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
