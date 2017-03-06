<ul class="list-group">
    <li role="presentation" class="${controllerName == 'panel' ? 'active' : ''} list-group-item">
        <g:link controller="panel">Panel</g:link>
    </li>

    <sec:ifAllGranted roles="ROLE_ADMIN">
        <li class="${controllerName == 'user' || controllerName == 'thirdPartyEmployee' ? 'active' : ''} list-group-item">
            <g:link controller="user">Empleados</g:link>
        </li>
        <li class="${controllerName == 'thirdParty' ? 'active' : ''} list-group-item">
            <g:link controller="thirdParty">Tercero</g:link>
        </li>
        <li class="${controllerName == 'coordination' ? 'active' : ''} list-group-item">
            <g:link controller="coordination">Coordinaciones</g:link>
        </li>
        <li class="${controllerName == 'classroom' ? 'active' : ''} list-group-item">
            <g:link controller="classroom">Aulas</g:link>
        </li>
        <li class="${controllerName == 'role' ? 'active' : ''} list-group-item">
            <g:link controller="role">Roles</g:link>
        </li>
    </sec:ifAllGranted>

    <sec:ifAllGranted roles="ROLE_ADMINISTRATIVE_SUPERVISOR">
        <li role="presentation" class="${controllerName == 'voucher' && actionName in ['vouchersToApprove', 'summary', 'show', 'edit'] ? 'active' : ''} list-group-item">
            <g:link controller="voucher" action="vouchersToApprove">Vales</g:link>
        </li>
    </sec:ifAllGranted>

    <sec:ifAnyGranted roles="ROLE_PROTOCOL_SUPERVISOR, ROLE_ASSISTANT_ADMINISTRATIVE_SUPERVISOR">
        <li role="presentation" class="${controllerName == 'voucher' ? 'active' : ''} list-group-item">
            <g:link controller="voucher" params="[status: 'pending']">Vales</g:link>
        </li>
        <li role="presentation" class="${controllerName == 'guest' ? 'active' : ''} list-group-item">
            <g:link controller="guest">Visitas</g:link>
        </li>
    </sec:ifAnyGranted>

    <sec:ifAllGranted roles="ROLE_PROTOCOL_SUPERVISOR">
        <li role="presentation" class="${controllerName == 'externalCustomer' ? 'active' : ''} list-group-item">
            <g:link controller="externalCustomer">Clientes externos</g:link>
        </li>
    </sec:ifAllGranted>

    <li role="presentation" class="${controllerName == 'copy' ? 'active' : ''} list-group-item">
        <g:link controller="copy">Copias</g:link>
    </li>
</ul>
