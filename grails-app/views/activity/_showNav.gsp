<ul class="nav nav-tabs">
    <li role="presentation" class="${params?.tab == 'data' || !params?.tab ? 'active' : ''}">
        <g:link action="show" params="[id: params.id, tab: 'data']">
            <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> 
        </g:link>
    </li>
    <li role="presentation" class="${params?.tab == 'edit' ? 'active' : ''}">
        <g:link action="show" params="[id: params.id, tab: 'edit']">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
        </g:link>
    </li>
    <li role="presentation" class="${params?.tab == 'notification' ? 'active' : ''}">
        <g:link action="show" params="[id: params.id, tab: 'notification']">
            <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
        </g:link>
    </li>
</ul>
