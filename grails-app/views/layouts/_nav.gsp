<ul class="nav nav-tabs">
    <li role="presentation" class="${controllerName == 'birthday' ? 'active' : ''}">
        <g:link controller="birthday">Cumpleañeros</g:link>
    </li>
    <li role="presentation" class="${controllerName == 'phoneBook' ? 'active' : ''}">
        <g:link controller="phoneBook">Directorio telefonico</g:link>
    </li>
    <li role="presentation" class="${controllerName == 'news' ? 'active' : ''}">
        <g:link controller="news">Noticias</g:link>
    </li>
</ul>
