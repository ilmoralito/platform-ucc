<g:applyLayout name="threeColumns">
    <head>
        <title>Actividad</title>
    </head>

    <content tag="main">
        <g:set var="eventId" value="${params.long('eventId') ?: activity.events[0].id}"/>
        <g:set var="index" value="${events.findIndexOf { it == ni.edu.uccleon.Event.get(eventId) }}"/>

        <ul id="nav" class="nav nav-tabs">
            <g:each in="${events}" var="event" status="idx">
                <li role="presentation" class="${event.id == eventId ? 'active' : ''}">
                    <g:link action="edit" params="[id: params.id, tab: params.tab, eventId: event.id]">
                        ${idx + 1}
                    </g:link>
                </li>
            </g:each>
            <li role="presentation">
                <g:link
                    action="cloneActivityEvent"
                    params="[id: params.id, tab: params.tab, eventId: eventId]">
                        <i class="fa fa-plus" aria-hidden="true"></i>
                    </g:link>
            </li>
        </ul>

        <div class="row">
            <div class="col-md-12">
                <div class="btn-group pull-right">
                    <g:link
                        action="removeActivityEvent"
                        params="[id: params.id, tab: params?.tab, eventId: eventId]"
                        class="btn btn-default">
                        <i class="fa fa-trash-o" aria-hidden="true"></i>
                    </g:link>
                </div>
            </div>
        </div>

        <g:form action="updateEvent" autocomplete="off">
            <g:hiddenField name="id" value="${params.id}"/>
            <g:hiddenField name="tab" value="${params.tab ?: 'edit'}"/>
            <g:hiddenField name="eventId" value="${eventId}"/>

            <g:render template="form"/>

            <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>
        </g:form>
    </content>

    <content tag="right-column">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active">
                <g:link action="edit" params="[id: params.id, eventId: eventId]">
                    <i class="fa fa-trash-o" aria-hidden="true"></i>
                </g:link>
            </li>
        </ul>

        <g:form action="removeActivity">
            <g:hiddenField name="id" value="${activity.id}"/>

            <g:submitButton name="send" value="Eliminar" class="btn btn-danger btn-block"/>
        </g:form>
    </content>
</g:applyLayout>
