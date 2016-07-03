<sec:ifAllGranted roles='ROLE_ADMIN'>
    <li class="${controllerName == 'coordination' ? 'active' : ''}">
        <g:link controller="coordination">
            Administrar coordinaciones
        </g:link>
    </li>
    <li class="${controllerName == 'user' && !(actionName in ['profile', 'password']) ? 'active' : ''}">
        <g:link controller="user">
            Administrar usuarios
        </g:link>
    </li>
    <li class="${controllerName == 'classroom' ? 'active' : ''}">
        <g:link controller="classroom">
            Administrar aulas
        </g:link>
    </li>
</sec:ifAllGranted>
