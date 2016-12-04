<ul class="nav nav-pills nav-stacked">
    <li role="presentation" class="${controllerName == 'panel' ? 'active' : ''}">
        <g:link controller="panel">Panel</g:link>
    </li>

    <sec:ifAllGranted roles="ROLE_ADMIN">
        <li class="${controllerName == 'user' && !(actionName in ['profile', 'password']) ? 'active' : ''}">
            <g:link controller="user">Usuarios</g:link>
        </li>
        <li class="${controllerName == 'coordination' ? 'active' : ''}">
            <g:link controller="coordination">Coordinaciones</g:link>
        </li>
        <li class="${controllerName == 'classroom' ? 'active' : ''}">
            <g:link controller="classroom">Aulas</g:link>
        </li>
        <li class="${controllerName == 'role' ? 'active' : ''}">
            <g:link controller="role">Roles</g:link>
        </li>
    </sec:ifAllGranted>

    <sec:ifAllGranted roles="ROLE_ADMINISTRATIVE_SUPERVISOR">
        <li role="presentation" class="${controllerName == 'voucher' && actionName in ['vouchersToApprove', 'summary', 'show'] ? 'active' : ''}">
            <g:link controller="voucher" action="vouchersToApprove">Vales</g:link>
        </li>
    </sec:ifAllGranted>

    <sec:ifAnyGranted roles="ROLE_PROTOCOL_SUPERVISOR, ROLE_ASSISTANT_ADMINISTRATIVE_SUPERVISOR">
        <li role="presentation" class="${controllerName == 'voucher' ? 'active' : ''}">
            <g:link controller="voucher" params="[status: 'pending']">Vales</g:link>
        </li>
        <li role="presentation" class="${controllerName == 'guest' ? 'active' : ''}">
            <g:link controller="guest">Visitas</g:link>
        </li>
    </sec:ifAnyGranted>

    <sec:ifAllGranted roles="ROLE_PROTOCOL_SUPERVISOR">
        <li role="presentation" class="${controllerName == 'externalCustomer' ? 'active' : ''}">
            <g:link controller="externalCustomer">Clientes externos</g:link>
        </li>
    </sec:ifAllGranted>
</ul>
