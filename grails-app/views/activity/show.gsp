<g:applyLayout name="threeColumns">
    <head>
        <title>Actividad</title>
    </head>

    <content tag="main">
        <g:set var="events" value="${activity.events}"/>
        <g:set var="eventId" value="${params.long('eventId') ?: activity.events[0].id}"/>

        <g:render template="showNav"/>
    </content>

    <content tag="right-column">
        <g:render template="activityNav"/>

        <g:if test="${params?.tab == 'data' || !params?.tab}">
            <ucc:activityWidget activityWidget="${activityWidget}"/>
        </g:if>

        <g:if test="${params?.tab == 'edit'}">
            <g:form name="form" action="updateActivity" autocomplete="off">
                <g:hiddenField name="id" value="${params.id}"/>
                <g:hiddenField name="tab" value="${params?.tab}"/>

                <g:render template="activityForm"/>

                <g:submitButton name="send" value="Actualizar" class="btn btn-primary btn-block"/>
            </g:form>
        </g:if>

        <g:if test="${params?.tab == 'notification'}">
            <g:if test="${activity.notified == false && daysAllowedToNotify >= 3}">
                <g:link action="sendNotification" id="${activity.id}" class="btn btn-danger btn-block">
                    Notificar
                </g:link>
            </g:if>
        </g:if>
    </content>
</g:applyLayout>
