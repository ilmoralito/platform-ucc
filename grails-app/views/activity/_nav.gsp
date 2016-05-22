<g:if test="${events}">
    <ul id="nav" class="nav nav-tabs">
        <g:each in="${events}" var="event" status="idx">
            <li role="presentation" class="${index == idx ? 'active' : ''}">
                <g:link action="events" params="[index: idx]">
                    <g:formatDate date="${event.date}" format="MM-dd"/>
                </g:link>
            </li>
        </g:each>
        <li role="presentation">
            <g:link action="cloneEvent" params="[index: index]">+</g:link>
        </li>
    </ul>
    <div class="row">
        <div class="col-md-12">
            <div class="pull-right">
                <g:link action="removeEvent" params="[index: index]">
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                </g:link>
            </div>
        </div>
    </div>
</g:if>
