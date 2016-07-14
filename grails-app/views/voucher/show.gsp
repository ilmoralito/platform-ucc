<g:applyLayout name="threeColumns">
    <head>
        <title>Vales</title>
    </head>

    <content tag="main">
        <g:if test="${vouchers}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 40%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 35%;">
                </colgroup>
                <thead>
                    <th class="text-center"><i class="fa fa-pencil"></i></th>
                    <th>Empleado</th>
                    <th>Actividad</th>
                    <th>Valor</th>
                    <th>Servicios</th>
                </thead>
                <tbody>
                    <g:each in="${vouchers}" var="v">
                        <tr>
                            <td style="text-align: center">
                                <g:link action="edit" params="[id: v.id]" class="btn btn-default btn-xs">
                                    <i class="fa fa-pencil"></i>
                                </g:link>
                            </td>
                            <td><ucc:employee id="${v.employee}"/></td>
                            <td><g:fieldValue bean="${v}" field="activity"/></td>
                            <td><g:fieldValue bean="${v}" field="value"/></td>
                            <td>
                                <g:if test="${v.refreshment}">Refigerio</g:if>
                                <g:if test="${v.breakfast}">Desayuno</g:if>
                                <g:if test="${v.lunch}">Almuerzo</g:if>
                                <g:if test="${v.dinner}">Cena</g:if>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>

    <content tag="right-column">
        <ul class="nav nav-tabs">
            <li role="presentation" class="${!params.tab || params.tab == 'resume' ? 'active' : ''}">
                <g:link action="show" params="[date: params.date]">
                    <i class="fa fa-info" aria-hidden="true"></i>
                </g:link>
            </li>
            <li role="presentation" class="${params.tab == 'print' ? 'active' : ''}">
                <g:link action="show" params="[date: params.date, tab: 'print']">
                    <i class="fa fa-print" aria-hidden="true"></i>
                </g:link>
            </li>
            <li role="presentation" class="${params.tab == 'notify' ? 'active' : ''}">
                <g:link action="show" params="[date: params.date, tab: 'notify']">
                    <i class="fa fa-paper-plane-o" aria-hidden="true"></i>
                </g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || params.tab == 'resume'}">
            <label>Fecha</label>
            <p><b><g:formatDate date="${voucherViewModel.date}" format="yyyy-MM-dd"/></b></p>

            <label>Estado</label>
            <p><ucc:voucherStatus status="${voucherViewModel.status}"/></p>

            <label>Total</label>
            <p>${voucherViewModel.total}</p>
        </g:if>

        <g:if test="${params.tab == 'print' ? 'active' : ''}">
            <g:link action="printSetOfVouchers" params="[date: params.date]" class="btn btn-primary btn-block">
                Imprimir vales
            </g:link>
        </g:if>

        <g:if test="${params.tab == 'notify'}">
            <g:link action="sendAll" params="[date: params.date]" class="btn btn-primary btn-block">
                <sec:ifAllGranted roles="ROLE_PROTOCOL_SUPERVISOR">
                    Notificar
                </sec:ifAllGranted>
                <sec:ifAllGranted roles="ROLE_ADMINISTRATIVE_SUPERVISOR">
                    Autorizar
                </sec:ifAllGranted>
            </g:link>
        </g:if>
    </content>
</g:applyLayout>
