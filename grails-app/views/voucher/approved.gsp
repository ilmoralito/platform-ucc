<g:applyLayout name="threeColumns">
    <head>
        <title>Vales</title>
        </style>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${vouchers}">
            <g:render template="vouchersByDateAndActivity" vouchers="${vouchers}" model="[form: 'notify']"/>

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
    </content>
</g:applyLayout>
