<g:applyLayout name="twoColumns">
    <head>
        <title>Vales a aprobar</title>
        </style>
    </head>

    <content tag="main">
        <g:render template="toApproveNav"/>

        <g:if test="${vouchers}">
            <g:render template="vouchersByDateAndActivity" vouchers="${vouchers}"  model="[form: 'approve']"/>

            <g:form name="approve" action="approve">
                <g:submitButton name="send" value="Aprobar" class="btn btn-primary"/>
            </g:form>
        </g:if>
        <g:else>
            <p>No hay vales que aprobar</p>
        </g:else>
    </content>
</g:applyLayout>
