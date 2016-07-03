<g:applyLayout name="threeColumns">
    <head>
        <title>Aulas</title>
    </head>

    <content tag="main">
        <g:if test="${classrooms}">
            <table class="table table-hover">
                <thead>
                    <th colspan="2">Aulas</th>
                </thead>
                <tbody>
                    <g:each in="${classrooms}" var="c">
                        <tr>
                            <td colspan="2">
                                <strong>${c.name}</strong>
                            </td>
                        </tr>
                        <g:each in="${c.classrooms}" var="classroom">
                            <tr>
                                <td>
                                    <g:link action="edit" id="${classroom.id}">
                                        ${classroom.code}
                                    </g:link>
                                </td>
                                <td>${classroom.name}</td>
                            </tr>
                        </g:each>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>

    <content tag="right-column">
        <ul class="nav nav-tabs">
            <li role="presentation" class="${!params.tab || params.tab != 'filter' ? 'active' : ''}">
                <g:link action="index">Crear</g:link>
            </li>
            <li role="presentation" class="${params.tab == 'filter' ? 'active' : ''}">
                <g:link action="index" params="[tab: 'filter']">Filtrar</g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || params.tab != 'filter'}">
            <g:form action="create" name="createClassroomForm" autocomplete="off">
                <g:render template="form"/>

                <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
            </g:form>
        </g:if>
        <g:else>
            <g:form action="index" name="filterForm" params="[tab: 'filter']" autocomplete="off">
                <label>Piso</label>
                <div class="checkbox">
                    <label>
                        <g:checkBox name="floor" value="1" checked="${params.list('floor').contains('1')}"/> 1
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="floor" value="2" checked="${params.list('floor').contains('2')}"/> 2
                    </label>
                </div>

                <label>Codigo</label>
                <g:each in="${['C', 'B', 'D', 'E', 'K']}" var="code">
                    <div class="checkbox">
                        <label>
                            <g:checkBox name="code" value="${code}" checked="${params.list('code').contains(code)}"/>
                            ${code}
                        </label>
                    </div>
                </g:each>

                <label>Aire acondicionado</label>
                <div class="checkbox">
                    <label>
                        <g:checkBox name="airConditioned" value="true" checked="${params.list('airConditioned').contains('true')}"/>
                        Climatizado
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="airConditioned" value="false" checked="${params.list('airConditioned').contains('false')}"/>
                        No climatizado
                    </label>
                </div>

                <button type="submit" class="btn btn-primary btn-block">Filtrar</button>
            </g:form>
        </g:else>
    </content>
</g:applyLayout>
