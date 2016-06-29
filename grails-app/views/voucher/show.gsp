<g:applyLayout name="threeColumns">
    <head>
        <title>Vales</title>
    </head>

    <content tag="main">
        <g:link action="index" class="btn btn-default">Regresar</g:link>

        <g:if test="${vouchers}">
            <table class="table table-hover">
                <caption>Vales de ${params.date("date", "yyyy-MM-dd").format("yyyy-MM-dd")}</caption>
                <colgroup>
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 35%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 35%;">
                </colgroup>
                <thead>
                    <th style="text-align: center"><i class="fa fa-pencil"></i></th>
                    <th>Empleado</th>
                    <th>Actividad</th>
                    <th>Valor</th>
                    <th>Estado</th>
                    <th>Servicios</th>
                </thead>
                <tbody>
                    <g:each in="${vouchers}" var="v">
                        <tr>
                            <td style="text-align: center">
                                <g:link action="edit" id="${v.id}" class="btn btn-default btn-xs">
                                    <i class="fa fa-pencil"></i>
                                </g:link>
                            </td>
                            <td><ucc:employee id="${v.employee}"/></td>
                            <td><g:fieldValue bean="${v}" field="activity"/></td>
                            <td><g:fieldValue bean="${v}" field="value"/></td>
                            <td><ucc:voucherStatus status="${v.status}"/></td>
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
            <li role="presentation" class="${!params.tab || params.tab != 'send' ? 'active' : ''}">
                <g:link action="edit" params="[date: params.date]">
                    <span class="glyphicon glyphicon-print" aria-hidden="true"></span>
                </g:link>
            </li>
            <li role="presentation" class="${params.tab == 'send' ? 'active' : ''}">
                <g:link action="edit" params="[date: params.date, tab: 'send']">
                    <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
                </g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || params.tab != 'send' ? 'active' : ''}">
            <g:link action="printItAll" params="[date: params.date]" class="btn btn-primary btn-block">
                Imprimir vales
            </g:link>
        </g:if>
        <g:else>
            <g:link action="sendAll" params="[date: params.date]" class="btn btn-primary btn-block">
                <sec:ifAllGranted roles="ROLE_PROTOCOL_SUPERVISOR">
                    Notificar
                </sec:ifAllGranted>
                <sec:ifAllGranted roles="ROLE_ADMINISTRATIVE_SUPERVISOR">
                    Autorizar
                </sec:ifAllGranted>
            </g:link>
        </g:else>
    </content>
</g:applyLayout>
