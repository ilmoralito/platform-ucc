<g:if test="${events}">
    <ul id="nav" class="nav nav-tabs">
        <g:each in="${events}" var="event">

            <li role="presentation" class="${event.id == eventId ? 'active' : ''}">
                <g:link action="edit" params="[id: params.id, tab: params?.tab, eventId: event.id]">
                    <g:formatDate date="${event.date}" format="MM-dd"/>
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
</g:if>
