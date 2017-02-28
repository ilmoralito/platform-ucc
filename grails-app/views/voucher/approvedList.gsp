<g:applyLayout name="threeColumns">
    <head>
        <title>Vales</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <div class="clearfix">
            <g:link uri="${request.getHeader('referer')}" class="btn btn-default pull-right">Regresar</g:link>
        </div>

        <g:if test="${vouchers}">
            <g:render template="vouchersByDateAndActivity" vouchers="${vouchers}"/>

             <g:form name="notify" action="printVouchers">
                <button type="submit" class="btn btn-primary">
                    <i class="fa fa-print" aria-hidden="true"></i> Imprimir
                </button>
            </g:form>
        </g:if>
        <g:else>
            <p>No hay vales aprobados que mostrar</p>
        </g:else>
    </content>

    <content tag="right-column">
        <g:render template="createVoucher"/>

        <div class="well well-sm">
            <g:link action="approved" params="[approvalDate: params.approvalDate]">
                <i class="fa fa-calendar" aria-hidden="true"></i> ${params.approvalDate}
            </g:link>
        </div>

        <g:render template="filterForm" model="[label: 'Internos', members: users, property: 'username', type: 'user']"/>

        <g:render template="filterForm" model="[label: 'Visitas', members: guests, property: 'fullName', type: 'guest']"/>
    </content>
</g:applyLayout>
