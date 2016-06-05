<g:applyLayout name="threeColumns">
    <head>
        <title>Vales</title>
    </head>

    <content tag="main">

    </content>

    <content tag="right-column">
        <ul class="nav nav-tabs">
            <li role="presentation" class="${!params.tab || params.tab != 'guest' ? 'active' : ''}">
                <g:link action="index">Interno</g:link>
            </li>
            <li role="presentation" class="${params.tab == 'guest' ? 'active' : ''}">
                <g:link action="index" params=[tab: 'guest']>Visita</g:link>
            </li>
        </ul>
    </content>
</g:applyLayout>
