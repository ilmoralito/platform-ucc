<g:applyLayout name="threeColumns">
    <head>
        <title>Eventos</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:form id="form" action="create" autocomplete="off">
            <g:render template="form"/>

            <g:submitButton name="send" value="Guardar" class="btn btn-primary"/> 
        </g:form>
    </content>

    <content tag="right-column">
        <label>Nombre de la actividad</label>
        <p>
            <g:link action="updateActivityName">
                ${session.activity.name}
            </g:link>
        </p>

        <ucc:profile/>
    </content>
</g:applyLayout>
