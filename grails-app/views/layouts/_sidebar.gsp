<ul class="nav nav-pills nav-stacked">
    <li role="presentation" class="${controllerName == 'panel' ? 'active' : ''}">
        <g:link controller="panel">Panel</g:link>
    </li>
    <li role="presentation" class="${controllerName == 'activity' ? 'active' : ''}">
        <g:link controller="activity">Actividades</g:link>
    </li>
    <li role="presentation" class="${controllerName == 'copy' ? 'active' : ''}">
        <g:link controller="copy">Copias</g:link>
    </li>

    <hr>

    <li role="presentation" class="${controllerName == 'ext' ? 'active' : ''}">
        <g:link controller="copy">Extenciones</g:link>
    </li>
    <li role="presentation" class="${controllerName == 'birthday' ? 'active' : ''}">
        <g:link controller="birthday" action="list">Cumpleaneros del mes</g:link>
    </li>
    <li role="presentation" class="${controllerName == 'ext' ? 'active' : ''}">
        <g:link controller="news">Noticias</g:link>
    </li>
</ul>