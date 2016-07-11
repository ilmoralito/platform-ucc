<g:applyLayout name="threeColumns">
    <head>
        <title>Actividad</title>
    </head>

    <content tag="main">
        <g:set var="events" value="${activity.events}"/>
        <g:set var="eventId" value="${params.long('eventId') ?: activity.events[0].id}"/>
        <g:set var="index" value="${events.findIndexOf { it == ni.edu.uccleon.Event.get(eventId) }}"/>

        <g:render template="showNav"/>

        <g:form name="updateEventForm" action="updateEvent" autocomplete="off">
            <g:hiddenField name="id" value="${params.id}"/>
            <g:hiddenField name="tab" value="${params.tab ?: 'data'}"/>
            <g:hiddenField name="eventId" value="${eventId}"/>

            <g:render template="form"/>

            <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>
        </g:form>
    </content>

    <content tag="right-column">
        <ul class="nav nav-tabs">
            <li role="presentation" class="${params.tab == 'data' || !params.tab ? 'active' : ''}">
                <g:link action="show" params="[id: params.id, tab: 'data', eventId: eventId]">
                    <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
                </g:link>
            </li>
            <li role="presentation" class="${params.tab == 'edit' ? 'active' : ''}">
                <g:link action="show" params="[id: params.id, tab: 'edit', eventId: eventId]">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                </g:link>
            </li>
            <li role="presentation" class="${params.tab == 'notification' ? 'active' : ''}">
                <g:link action="show" params="[id: params.id, tab: 'notification', eventId: eventId]">
                    <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
                </g:link>
            </li>
            <li role="presentation" class="${params.tab == 'remove' ? 'active' : ''}">
                <g:link action="show" params="[id: params.id, tab: 'remove', eventId: eventId]">
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                </g:link>
            </li>
            <sec:access expression="hasRole('ROLE_PROTOCOL_SUPERVISOR')">
                <li role="presentation" class="${params.tab == 'print' ? 'active' : ''}">
                    <g:link action="show" params="[id: params.id, tab: 'print', eventId: eventId]">
                        <span class="glyphicon glyphicon-print" aria-hidden="true"></span>
                    </g:link>
                </li>
            </sec:access>
        </ul>

        <g:if test="${params.tab == 'data' || !params.tab}">
            <ucc:activityWidget activityWidget="${activityWidget}"/>
        </g:if>

        <g:if test="${params.tab == 'edit'}">
            <g:form name="updateActivityForm" action="updateActivity" autocomplete="off">
                <g:hiddenField name="id" value="${params.id}"/>
                <g:hiddenField name="tab" value="${params.tab ?: data}"/>
                <g:hiddenField name="eventId" value="${eventId}"/>

                <g:render template="activityForm"/>

                <g:submitButton name="send" value="Actualizar" class="btn btn-primary btn-block"/>
            </g:form>
        </g:if>

        <g:if test="${params.tab == 'notification'}">
            <g:form action="sendNotification">
                <g:hiddenField name="id" value="${activity.id}"/>

                <button type="submit" class="btn btn-block btn-warning">
                    <ucc:notificationMessage status="${activity.status}" location="${activity.location}"/>
                </button>
            </g:form>
        </g:if>

        <g:if test="${params.tab == 'remove'}">
            <g:form action="removeActivity">
                <g:hiddenField name="id" value="${activity.id}"/>

                <g:submitButton name="send" value="Eliminar" class="btn btn-danger btn-block"/>
            </g:form>
        </g:if>

        <sec:access expression="hasRole('ROLE_PROTOCOL_SUPERVISOR')">
            <g:if test="${params.tab == 'print'}">
                <g:link
                    action="printActivity"
                    params="[id: activity.id]"
                    class="btn btn-block btn-default">
                    Imprimir
                </g:link>
            </g:if>
        </sec:access>
    </content>
</g:applyLayout>
