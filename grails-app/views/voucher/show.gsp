<g:applyLayout name="twoColumns">
    <head>
        <title>Vale</title>
    </head>

    <content tag="main">
        <section class="clearfix">
            <sec:ifAnyGranted roles="ROLE_PROTOCOL_SUPERVISOR, ROLE_ASSISTANT_ADMINISTRATIVE_SUPERVISOR">
                <g:if test="${voucher.status == 'approved'}">
                    <g:link action="cancel" id="${voucher.id}" class="btn btn-warning">Cancelar vale</g:link>
                </g:if>
            </sec:ifAnyGranted>
            <g:link action="delete" id="${voucher.id}" class="btn btn-danger">Eliminar</g:link>
            <g:link action="edit" id="${voucher.id}" class="btn btn-primary">Editar</g:link>

            <g:link uri="${request.getHeader('referer')}" class="btn btn-default pull-right">Regresar</g:link>
        </section>

        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>
            <tbody>
                <tr>
                    <td>A nombre de</td>
                    <td>
                        <g:formatBoolean boolean="${voucher.user != null}" true="${voucher?.user?.username}" false="${voucher?.guest?.fullName}"/>
                    </td>
                </tr>
                <tr>
                    <td>Nombre de la actividad</td>
                    <td>
                        <g:fieldValue bean="${voucher}" field="activity"/>
                    </td>
                </tr>
                <tr>
                    <td>Fecha</td>
                    <td>
                        <g:formatDate date="${voucher.date}" format="yyyy-MM-dd"/>
                    </td>
                </tr>
                <tr>
                    <td>Alimentos</td>
                    <td>
                        <ucc:foodInSpanish foods="${voucher.foods.name}"/>
                    </td>
                </tr>
                <tr>
                    <td>Valor de vale</td>
                    <td>
                        <g:formatNumber number="${voucher.value}" type="currency" currencyCode="NIO"/>
                    </td>
                </tr>
                <tr>
                    <td>Estado</td>
                    <td>
                        <ucc:voucherStatusInSpanish status="${voucher.status}"/>
                    </td>
                </tr>
                <g:if test="${voucher.status == 'canceled'}">
                    <tr>
                        <td>Motivo de la cancelacion</td>
                        <td>
                            <g:fieldValue bean="${voucher}" field="reasonForCancellation"/>
                        </td>
                    </tr>
                </g:if>
                <tr>
                    <td colspan="2">
                        <i class="fa fa-bookmark-o" aria-hidden="true"></i>
                    </td>
                </tr>
                <tr>
                    <td>Creador por</td>
                    <td>
                        <g:fieldValue bean="${voucher}" field="createdBy.username"/>
                    </td>
                </tr>
                <tr>
                    <td>Fecha de creacion</td>
                    <td>
                        <g:formatDate date="${voucher.dateCreated}" format="yyyy-MM-dd HH:mm"/>
                    </td>
                </tr>
                <g:if test="${voucher.dateCreated != voucher.lastUpdated}">
                    <tr>
                        <td>Actualizacion mas reciente realizada por</td>
                        <td>
                            <g:fieldValue bean="${voucher}" field="lastUpdatedBy.username"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Mas reciente actualizacion</td>
                        <td>
                            <g:formatDate date="${voucher.lastUpdated}" format="yyyy-MM-dd HH:mm"/>
                        </td>
                    </tr>
                </g:if>
                <g:if test="${voucher?.approvalDate}">
                    <tr>
                        <td>Fecha de autorizacion</td>
                        <td>
                            <g:formatDate date="${voucher.approvalDate}" format="yyyy-MM-dd HH:mm"/>
                        </td>
                    </tr>
                </g:if>
            </tbody>
        </table>
    </content>
</g:applyLayout>
