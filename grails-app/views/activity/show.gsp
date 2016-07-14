<g:applyLayout name="threeColumns">
    <head>
        <title>Actividad</title>
    </head>

    <content tag="main">
        <g:set var="position" value="${params.int('event') ?: 0}"/>

        <ul id="nav" class="nav nav-tabs">
            <g:each in="${events}" var="event" status="idx">
                <li role="presentation" class="${params.int('event') == idx || !params.event && idx == 0 ? 'active' : ''}">
                    <g:link action="show" params="[id: params.id, event: idx]">
                        ${idx + 1}
                    </g:link>
                </li>
            </g:each>
        </ul>

        <%-- <ucc:eventWidget eventWidget="${eventWidget}" position="${position}"/> --%>

        <p>Detalle</p>
        <hr>

        <table class="table table-hover table-borderless">
            <colgroup>
                <col span="1" style="width: 26%;">
                <col span="1" style="width: 74%;">
            </colgroup>
            <tbody>
                <tr>
                    <td>Fecha</td>
                    <td><g:formatDate date="${events[position].date}" format="yyyy-MM-dd"/></td>
                </tr>

                <tr>
                    <td>Lugar</td>
                    <td><ucc:getClassroom id="${events[position].location}"/></td>
                </tr>

                <tr>
                    <td>Hora de inicio</td>
                    <td><ucc:getHour hour="${events[position].startTime}"/></td>
                </tr>

                <tr>
                    <td>Hora final</td>
                    <td><ucc:getHour hour="${events[position].endingTime}"/></td>
                </tr>

                <tr>
                    <td>Numero de asistentes</td>
                    <td>${events[position].numberOfPeople}</td>
                </tr>
            </tbody>
        </table>

        <p>Requerimientos</p>
        <hr>

        <div class="row">
            <div class="col-md-3">
                <p>Medios</p>

                <g:each in="${means}" var="m">
                    <p>
                        <g:if test="${events[position][m.key]}">
                            <i class="fa fa-check-square-o"></i>
                        </g:if>
                        <g:else>
                            <i class="fa fa-square-o"></i>
                        </g:else>
                        ${m.value}
                    </p>
                </g:each>
            </div>
            <div class="col-md-3">
                <p>Refrescos</p>

                <p>
                    <g:if test="${events[position].water}">
                        <i class="fa fa-check-square-o"></i>
                    </g:if>
                    <g:else>
                        <i class="fa fa-square-o"></i>
                    </g:else>
                    Agua
                </p>

                <p>
                    <g:if test="${events[position].coffee}">
                        <i class="fa fa-check-square-o"></i>
                    </g:if>
                    <g:else>
                        <i class="fa fa-square-o"></i>
                    </g:else>
                    Cafe
                </p>

                <p>${events[position].cookies} Galletas</p>

                <p>${events[position].waterBottles} Botellas de agua</p>
            </div>
            <div class="col-md-3">
                <p>Montaje</p>

                <label>Tipo de montaje</label>
                <p><i class="fa fa-dot-circle-o"></i> ${events[position].mountingType}</p>

                <label>Elementos</label>
                <g:each in="${elements}" var="property">
                    <p>
                        <g:if test="${events[position][property.key]}">
                            <i class="fa fa-check-square-o"></i>
                        </g:if>
                        <g:else>
                            <i class="fa fa-square-o"></i>
                        </g:else>
                        ${property.value}
                    </p>
                </g:each>

                <label>Tipo de mesas</label>
                <g:each in="${tableTypes}" var="property">
                    <p>
                        <g:if test="${property in events[position].tableTypes.name}">
                            <i class="fa fa-check-square-o"></i>
                        </g:if>
                        <g:else>
                            <i class="fa fa-square-o"></i>
                        </g:else>
                        ${property}
                    </p>
                </g:each>

                <label>Tipo de sillas</label>
                <g:each in="${chairTypes}" var="property">
                    <p>
                        <g:if test="${property in events[position].chairTypes.name}">
                            <i class="fa fa-check-square-o"></i>
                        </g:if>
                        <g:else>
                            <i class="fa fa-square-o"></i>
                        </g:else>
                        ${property}
                    </p>
                </g:each>

                <p>${events[position].presidiumTable} Mesa presidium</p>
            </div>
            <div class="col-md-3">
                <p>Alimentos</p>

                <p>${events[position].refreshment} Refrigerios</p>

                <p>${events[position].breakfast} Desayuno</p>

                <p>${events[position].lunch} Almuerzo</p>

                <p>${events[position].dinner} Cena</p>
            </div>
        </div>


        <g:if test="${events[position].observation}">
            <p>Observacion</p>
            <hr>

            <p>${events[position].observation}</p>
        </g:if>
    </content>

    <content tag="right-column">
        <ul class="nav nav-tabs">
            <li role="presentation" class="${!params.tab || params.tab == 'activityInfo' ? 'active' : ''}">
                <g:link action="show" params="[id: activity.id, tab: 'activityInfo']">
                    <i class="fa fa-info"></i>
                </g:link>
            </li>
            <li role="presentation" class="${params.tab == 'edit' ? 'active' : ''}">
                <g:link action="show" params="[id: activity.id, tab: 'edit']">
                    <i class="fa fa-pencil"></i>
                </g:link>
            </li>
            <li role="presentation" class="${params.tab == 'notification' ? 'active' : ''}">
                <g:link action="show" params="[id: activity.id, tab: 'notification']">
                    <i class="fa fa-paper-plane-o" aria-hidden="true"></i>
                </g:link>
            </li>
            <sec:access expression="hasRole('ROLE_PROTOCOL_SUPERVISOR')">
                <li role="presentation" class="${params.tab == 'print' ? 'active' : ''}">
                    <g:link action="show" params="[id: activity.id, tab: 'print']">
                        <i class="fa fa-print" aria-hidden="true"></i>
                    </g:link>
                </li>
            </sec:access>
        </ul>

        <g:if test="${!params.tab || params.tab == 'activityInfo'}">
            <div class="clearfix">
                <div class="btn-group pull-right">
                    <g:link
                        action="show"
                        params="[id: activity.id, tab: 'activityInfo', task: 'update']"
                        class="btn btn-default btn-sm ${!params.task || params.task == 'update' ? 'active' : ''}">
                        <i class="fa fa-pencil"></i>
                    </g:link>
                    <g:link
                        action="show"
                        params="[id: activity.id, tab: 'activityInfo', task: 'resumen']"
                        class="btn btn-default btn-sm ${params.task == 'resumen' ? 'active' : ''}">
                        <i class="fa fa-align-justify"></i>
                    </g:link>
                </div>
            </div>

            <g:if test="${!params.task || params.task == 'update'}">
                <g:form action="updateActivity" autocomplete="off">
                    <g:hiddenField name="id" value="${activity.id}"/>
                    <g:render template="activityForm"/>

                    <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
                </g:form>
            </g:if>
            <g:else>
                <ucc:activityWidget activityWidget="${activityWidget}"/>
            </g:else>
        </g:if>

        <g:if test="${params.tab == 'edit'}">
            <g:link action="edit" id="${activity.id}" class="btn btn-block btn-primary">Editar</g:link>
        </g:if>

        <g:if test="${params.tab == 'notification'}">
            <g:form action="sendNotification">
                <g:hiddenField name="id" value="${activity.id}"/>

                <button type="submit" class="btn btn-block btn-primary">
                    <ucc:notificationMessage status="${activity.status}" location="${activity.location}" coordination="${activity.coordination}"/>
                </button>
            </g:form>
        </g:if>

        <sec:access expression="hasRole('ROLE_PROTOCOL_SUPERVISOR')">
            <g:if test="${params.tab == 'print'}">
                <g:link
                    action="printActivity"
                    params="[id: activity.id]"
                    class="btn btn-block btn-primary">
                    Imprimir
                </g:link>
            </g:if>
        </sec:access>
    </content>
</g:applyLayout>
