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
            <g:if test="${data}">
                <g:each in="${data}" var="d" status="idx">
                    <table class="table table-hover">
                        <caption><g:formatDate format="yyyy-MM-dd" date="${d.date}"/></caption>
                        <colgroup>
                            <col span="1" style="width: 50%;">
                            <col span="1" style="width: 50%;">
                        </colgroup>
                        <g:if test="${idx == 0}">
                            <thead>
                                <th>Nombre de la actividad</th>
                                <th>Estado</th>
                            </thead>
                        </g:if>
                            <tbody>
                                <g:each in="${d.activities}" var="a">
                                    <tr>
                                        <td>
                                            <g:link action="show" id="${a.id}">
                                                ${a.name}
                                                ${a?.externalCustomer?.name}
                                            </g:link>
                                        </td>
                                        <td>
                                            <span class="label label-default">
                                                ${a.status}
                                            </span>
                                        </td>
                                    </tr>
                                </g:each>
                            </tbody>
                    </table>
                </g:each>
            </g:if>
            <g:else>
                <p>Nada que mostrar</p>
            </g:else>
        </g:if>
    </content>

    <content tag="right-column">
        <g:link action="init" class="btn btn-primary btn-block">Crear</g:link>
    </content>
</g:applyLayout>
