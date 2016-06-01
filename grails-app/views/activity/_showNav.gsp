<g:if test="${events}">
    <ul id="nav" class="nav nav-tabs">
        <g:each in="${events}" var="event">

            <li role="presentation" class="${event.id == eventId ? 'active' : ''}">
                <g:link action="show" params="[id: params.id, tab: params?.tab, eventId: event.id]">
                    <g:formatDate date="${event.date}" format="MM-dd"/>
                </g:link>
            </li>

        </g:each>
        <li role="presentation">
            <g:link
                action="cloneActivityEvent"
                params="[id: params.id, tab: params?.tab, eventId: eventId]">+</g:link>
        </li>
    </ul>
    <div class="row">
        <div class="col-md-12">
            <div class="pull-right">
                <sec:access expression="hasRole('ROLE_PROTOCOL_SUPERVISOR')">
                    <g:link action="printEvent" params="[eventId: eventId]">
                        <span class="glyphicon glyphicon-print" aria-hidden="true"></span>
                    </g:link>
                    </sec:access>
                <g:link action="removeActivityEvent" params="[id: params.id, tab: params?.tab, eventId: eventId]">
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                </g:link>
            </div>
        </div>
    </div>
</g:if>
