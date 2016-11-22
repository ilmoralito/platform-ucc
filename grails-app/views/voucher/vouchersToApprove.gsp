<g:applyLayout name="twoColumns">
    <head>
        <title>Vales a aprobar</title>
    </head>

    <content tag="main">
        <g:render template="toApproveNav"/>

        <g:if test="${vouchers}">
            <g:render template="vouchersByDateAndActivity" vouchers="${vouchers}"/>

            <div class="clearfix">
                <g:form name="notify" action="approve" class="pull-left" style="margin-right: 5px;">
                    <g:submitButton name="send" value="Aprobar" class="btn btn-primary"/>
                </g:form>

                <g:form name="batch" action="batchDelete">
                    <g:submitButton name="send" value="Eliminar" class="btn btn-primary btn-danger"/>
                </g:form>
            </div>
        </g:if>
        <g:else>
            <p>No hay vales que aprobar</p>
        </g:else>
    </content>
</g:applyLayout>
