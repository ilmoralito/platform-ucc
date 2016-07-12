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

        <p>Requerimientos</p>
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
    </content>

    <content tag="right-column">
        <ul class="nav nav-tabs">
            <li role="presentation" class="${!params.tab || params.tab == 'eventDetail' ? 'active' : ''}">
                <g:link action="show" params="[id: activity.id, tab: 'eventDetail']">
                    <i class="fa fa-file-text-o"></i>
                </g:link>
            </li>
            <li role="presentation" class="${params.tab == 'activityInfo' ? 'active' : ''}">
                <g:link action="show" params="[id: activity.id, tab: 'activityInfo']">
                    <i class="fa fa-info"></i>
                </g:link>
            </li>
            <li role="presentation" class="${params.tab == 'edit' ? 'active' : ''}">
                <g:link action="show" params="[id: activity.id, tab: 'edit']">
                    <i class="fa fa-pencil"></i>
                </g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || params.tab == 'eventDetail'}">
            <label>Fecha</label>
            <p><g:formatDate date="${events[position].date}" format="yyyy-MM-dd"/></p>

            <label>Lugar</label>
            <p><ucc:getClassroom id="${events[position].location}"/></p>

            <label>Hora de inicio</label>
            <p>${events[position].startTime}</p>

            <label>Hora final</label>
            <p>${events[position].endingTime}</p>

            <label>Numero de asistentes</label>
            <p>${events[position].numberOfPeople}</p>

            <g:if test="${events[position].observation}">
                <label>Observacion</label>
                <p>${events[position].observation}</p>
            </g:if>
        </g:if>

        <g:if test="${params.tab == 'activityInfo'}">
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
    </content>
</g:applyLayout>
