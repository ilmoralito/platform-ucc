<g:applyLayout name="threeColumns">
    <head>
        <title>Eventos</title>
    </head>

    <content tag="main">
        <g:set var="events" value="${session?.events}"/>
        <g:set var="index" value="${params.int('index') ?: 0}"/>

        <g:render template="nav"/>

        <g:form id="form" action="events" params="[index: params?.index]" autocomplete="off">
            <g:render template="form"/>

            <g:submitButton name="send" value="Guardar" class="btn btn-primary"/> 
        </g:form>
    </content>

    <content tag="right-column">
        <label>Nombre de la actividad</label>
        <p>
            <g:link action="activityName" params="[index: params?.index]">
                ${session?.activity?.name}
            </g:link>
        </p>

        <g:if test="${events}">
            <g:form action="save">
                <g:submitButton name="send" value="Confirmar" class="btn btn-block btn-primary"/>
            </g:form>
        </g:if>
    </content>
</g:applyLayout>
