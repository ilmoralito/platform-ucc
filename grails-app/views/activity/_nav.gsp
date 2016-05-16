<g:if test="${session?.events}">
    <ul id="nav" class="nav nav-tabs">
        <g:each in="${session?.events}" var="event" status="index">
            <li role="presentation" class="${params?.index?.toInteger() == index ? 'active' : ''}">
                <g:link action="events" params="[index: index]">${index + 1}</g:link>
            </li>
        </g:each>
        <li role="presentation">
            <g:link action="cloneEvent" params="[index: params?.index]">+</g:link>
        </li>
    </ul>
    <div class="row">
        <div class="col-md-12">
            <div class="pull-right">
            <g:link action="removeEvent" params="[index: params?.index]">
                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
            </g:link>
            </div>
        </div>
    </div>
</g:if>
