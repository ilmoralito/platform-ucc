<g:set var="status" value="${params?.status}"/>

<ul class="nav nav-tabs">
    <li role="presentation" class="${status == 'pending' ? 'active' : ''}">
        <g:link action="index" params="[status: 'pending']">
            Pendientes
        </g:link>
    </li>
    <li role="presentation" class="${status == 'notified' ? 'active' : ''}">
        <g:link action="index" params="[status: 'notified']">
            Notificados
        </g:link>
    </li>
    <li role="presentation" class="${actionName in ['approvalDates', 'approved', 'filter'] ? 'active' : ''}">
        <g:link action="approvalDates">
            Aprobados
        </g:link>
    </li>
</ul>
