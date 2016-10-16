<ul class="nav nav-pills nav-stacked">
    <li role="presentation" class="${controllerName == 'panel' ? 'active' : ''}">
        <g:link controller="panel">Panel</g:link>
    </li>
    <li role="presentation" class="${controllerName == 'activity' ? 'active' : ''}">
        <g:link controller="activity">Protocolo</g:link>
    </li>
    <sec:ifAnyGranted roles="ROLE_PROTOCOL_SUPERVISOR">
        <li role="presentation" class="${controllerName == 'voucher' ? 'active' : ''}">
            <g:link controller="voucher">Vales</g:link>
        </li>
        <li role="presentation" class="${controllerName == 'guest' ? 'active' : ''}">
            <g:link controller="guest">Visitas</g:link>
        </li>
        <li role="presentation" class="${controllerName == 'externalCustomer' ? 'active' : ''}">
            <g:link controller="externalCustomer">Clientes externos</g:link>
        </li>
    </sec:ifAnyGranted>
    <li role="presentation" class="${controllerName == 'copy' ? 'active' : ''}">
        <g:link controller="copy">Copias</g:link>
    </li>
</ul>
