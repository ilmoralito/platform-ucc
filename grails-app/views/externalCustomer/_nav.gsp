<ul class="nav nav-tabs">
    <li role="presentation" class="${tab == 'create' ? 'active' : ''}">
        <g:link action="index" params="[tab: 'create']">Crear</g:link>
    </li>
    <li role="presentation" class="${tab == 'filter' ? 'active' : ''}">
        <g:link action="index" params="[tab: 'filter']">Filtro</g:link>
    </li>
</ul>