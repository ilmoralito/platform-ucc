<g:set var="idx" value="${params.int('index')}"/>

<g:if test="${session?.events}">
    <ul id="nav" class="nav nav-tabs">
        <g:each in="${session?.events}" var="event" status="index">
            <li role="presentation" class="${idx == index ? 'active' : ''}">
                <g:link action="events" params="[index: index]">
                    <g:formatDate date="${event.date}" format="MM-dd"/>
                </g:link>
            </li>
        </g:each>
        <li role="presentation">
            <g:link action="cloneEvent" params="[index: idx]">+</g:link>
        </li>
    </ul>
    <div class="row">
        <div class="col-md-12">
            <div class="pull-right">
                <g:link action="removeEvent" params="[index: idx]">
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                </g:link>
            </div>
        </div>
    </div>
</g:if>
