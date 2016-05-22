<g:applyLayout name="threeColumns">
    <head>
        <title>Actividad</title>
    </head>

    <content tag="main">
        
    </content>

    <content tag="right-column">
        <ucc:activityWidget activityWidget="${activityWidget}"/>

        <g:if test="${activity.notified == false && daysAllowedToNotify >= 3}">
            <g:link action="sendNotification" id="${activity.id}" class="btn btn-primary btn-block">
                Notificar
            </g:link>
        </g:if>
    </content>
</g:applyLayout>
