<g:applyLayout name="threeColumns">
    <head>
        <title>Vales</title>
        </style>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${approvalDates}">
            <table class="table table-hover">
                <tbody>
                    <g:each in="${approvalDates}" var="approvalDate" status="index">
                        <tr>
                            <td>
                                <g:link action="approved" params="[approvalDate: approvalDate.format('yyyy-MM-dd')]">
                                    <g:formatDate date="${approvalDate}" format="yyyy-MM-dd"/>
                                </g:link>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>No hay vales aprobados que mostrar</p>
        </g:else>
    </content>

    <content tag="right-column">
        <g:render template="createVoucher"/>
    </content>
</g:applyLayout>
