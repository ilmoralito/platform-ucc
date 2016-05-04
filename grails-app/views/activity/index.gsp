<g:applyLayout name="threeColumns">
    <head>
        <title>Actividades</title>
    </head>

    <content tag="main">
        <div class="row">
            <div class="col-md-12">
                <div class="pull-right">
                    <div class="btn-group">
                        <g:link
                            action="index"
                            params="[calendarType: 'schedule']"
                            class="btn btn-default ${params?.calendarType == 'schedule' ? 'active' : ''}">
                            Programa
                        </g:link>
                        <g:link
                            action="index"
                            params="[calendarType: 'year']"
                            class="btn btn-default ${params?.calendarType == 'year' ? 'active' : ''}">
                            Anio
                        </g:link>
                        <g:link
                            action="index"
                            params="[calendarType: 'month']"
                            class="btn btn-default ${params?.calendarType == 'month' ? 'active' : ''}">
                            Mes
                        </g:link>
                        <g:link
                            action="index"
                            params="[calendarType: 'week']"
                            class="btn btn-default ${params?.calendarType == 'week' ? 'active' : ''}">
                            Semana
                        </g:link>
                        <g:link
                            action="index"
                            params="[calendarType: 'day']"
                            class="btn btn-default ${params?.calendarType == 'day' || !params.calendarType ? 'active' : ''}">
                            Dia
                        </g:link>
                    </div>
                </div>
            </div>
        </div>
        <br>

        ${activities}

        <g:if test="${params?.calendarType == 'schedule'}">
            Feature to be implemented...
        </g:if>

        <g:if test="${params?.calendarType == 'year'}">
            <table class="table table-bordered table-condensed">
                <tbody>
                    <tr>
                        <td class="calendar-day vertical-align">Enero</td>
                        <td class="calendar-day vertical-align">Febrero</td>
                        <td class="calendar-day vertical-align">Marzo</td>
                        <td class="calendar-day vertical-align">Abril</td>
                    </tr>
                    <tr>
                        <td class="calendar-day vertical-align">Mayo</td>
                        <td class="calendar-day vertical-align">Junio</td>
                        <td class="calendar-day vertical-align">Julio</td>
                        <td class="calendar-day vertical-align">Agosto</td>
                    </tr>
                    <tr>
                        <td class="calendar-day vertical-align">Septiembre</td>
                        <td class="calendar-day vertical-align">Octubre</td>
                        <td class="calendar-day vertical-align">Noviembre</td>
                        <td class="calendar-day vertical-align">Diciembre</td>
                    </tr>
                </tbody>
            </table>
        </g:if>

        <g:if test="${params?.calendarType == 'month'}">
            <table class="table table-bordered table-condensed">
                <thead>
                    <th class="text-center">Domingo</th>
                    <th class="text-center">Lunes</th>
                    <th class="text-center">Martes</th>
                    <th class="text-center">Miercoles</th>
                    <th class="text-center">Jueves</th>
                    <th class="text-center">Viernes</th>
                    <th class="text-center">Sabado</th>
                </thead>
                <tbody>
                    <%--
                    <g:set var="helperService" bean="helperService"/>
                    <g:set var="dayOfWeek" value="${helperService.getDayOfWeek(day)}"/>
                    <g:set var="firstDayOfMonth" value="${helperService.getMonthFirstDayOfWeek()}"/>
                    --%>
                    
                </tbody>
            </table>
        </g:if>

        <g:if test="${params?.calendarType == 'week'}">
            <table class="table table-hover table-bordered table-condensed">
                <thead>
                    <th class="text-center">Domingo</th>
                    <th class="text-center">Lunes</th>
                    <th class="text-center">Martes</th>
                    <th class="text-center">Miercoles</th>
                    <th class="text-center">Jueves</th>
                    <th class="text-center">Viernes</th>
                    <th class="text-center">Sabado</th>
                </thead>
                <tbody>
                    <tr>
                        <td class="calendar-day">
                            ${interval[0].format("dd")}

                            <p>
                                <span class="label label-default">nombre actividad, jp</span>
                            </p>
                            <p>
                                <span class="label label-default">nombre actividad, jp</span>
                            </p>
                            <p>
                                <span class="label label-default">nombre actividad, jp</span>
                            </p>
                        </td>
                        <td class="calendar-day">
                            ${interval[1].format("dd")}
                        </td>
                        <td class="calendar-day">
                            ${interval[2].format("dd")}
                            <p><span class="span label label-danger">nombre actividad, jp</span></p>
                        </td>
                        <td class="calendar-day">
                            ${interval[3].format("dd")}
                        </td>
                        <td class="calendar-day">
                            ${interval[4].format("dd")}
                        </td>
                        <td class="calendar-day">
                            ${interval[5].format("dd")}
                        </td>
                        <td class="calendar-day">
                            ${interval[6].format("dd")}
                        </td>
                    </tr>
                </tbody>
            </table>
        </g:if>

        <g:if test="${params?.calendarType == 'day' || !params.calendarType}">
            <table class="table table-hover">
                <thead>
                    <colgroup>
                        <col span="1" style="width: 10%;">
                        <col span="1" style="width: 90%;">
                    </colgroup>
                    <th></th>
                    <th class="text-center" colspan="6">
                        ${dayName}
                    </th>
                </thead>
                <tbody>
                    <g:each in="${['8:00', '9:00', '10:00', '11:00', '12:00', '1:00', '2:00', '3:00', '4:00', '5:00']}" var="hour">
                        <tr>
                            <td><b>${hour}</b></td>
                            <td>
                                <span class></span>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
    </content>

    <content tag="right-column">
        <g:link action="create" class="btn btn-primary btn-block">Crear</g:link>
        </content>
</g:applyLayout>
