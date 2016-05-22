<g:applyLayout name="threeColumns">
    <head>
        <title>Actividades</title>
    </head>

    <content tag="main">
        <sec:ifAnyGranted roles="ROLE_PROTOCOL_COORDINATOR">
            <div class="row">
                <div class="col-md-12">
                    <div class="pull-right">
                        <div class="btn-group">
                            <g:link
                                action="index"
                                params="[calendarType: 'schedule']"
                                class="btn btn-default ${calendarType == 'schedule'  || !calendarType ? 'active' : ''}">
                                Programa
                            </g:link>
                            <g:link
                                action="index"
                                params="[calendarType: 'year']"
                                class="btn btn-default ${calendarType == 'year' ? 'active' : ''}">
                                Anio
                            </g:link>
                            <g:link
                                action="index"
                                params="[calendarType: 'month']"
                                class="btn btn-default ${calendarType == 'month' ? 'active' : ''}">
                                Mes
                            </g:link>
                            <g:link
                                action="index"
                                params="[calendarType: 'week']"
                                class="btn btn-default ${calendarType == 'week' ? 'active' : ''}">
                                Semana
                            </g:link>
                            <g:link
                                action="index"
                                params="[calendarType: 'day']"
                                class="btn btn-default ${calendarType == 'day' ? 'active' : ''}">
                                Dia
                            </g:link>
                        </div>
                    </div>
                </div>
            </div>
            <br>
        </sec:ifAnyGranted>

        <g:if test="${calendarType == 'schedule' || !calendarType}">
            <g:if test="${activities}">
                <table class="table table-hover">
                    <colgroup>
                        <col span="1" style="width: 40%;">
                        <col span="1" style="width: 60%;">
                    </colgroup>
                    <thead>
                        <th>Nombre</th>
                        <th>Estado</th>
                    </thead>
                    <tbody>
                        <g:each in="${activities}" var="activity">
                            <tr>
                                <td>
                                    <g:link action="show" id="${activity.id}">
                                        <g:fieldValue bean="${activity}" field="name"/>
                                    </g:link>
                                </td>
                                <td><ucc:activityStatus status="${activity.status}"/></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </g:if>
            <g:else>
                <p>Sin actividades recientes que mostrar</p>
            </g:else>
        </g:if>
    </content>

    <content tag="right-column">
        <g:link action="init" class="btn btn-primary btn-block">Crear</g:link>
    </content>
</g:applyLayout>
