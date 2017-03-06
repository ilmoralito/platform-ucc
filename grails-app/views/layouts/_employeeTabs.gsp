<ul class="nav nav-tabs">
    <li class="${controllerName == 'user' ? 'active' : ''}">
        <g:link controller="user">Empledados</g:link>
    </li>
    <li class="${controllerName == 'thirdPartyEmployee' ? 'active' : ''}">
        <g:link controller="thirdPartyEmployee">Empleados de tercero</g:link>
    </li>
</ul>
