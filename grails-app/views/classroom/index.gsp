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
            <p>Onder construction</p>
        </g:else>
    </content>
</g:applyLayout>
