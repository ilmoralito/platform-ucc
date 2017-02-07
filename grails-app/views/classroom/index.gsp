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
                            <td colspan="2">${c.name}</td>
                        </tr>
                        <tr>
                            <td>Codigo</td>
                            <td>Nombre</td>
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
        <div class="well well-sm">
            <g:form action="create" name="createClassroomForm" autocomplete="off">
                <g:render template="form"/>

                <button type="submit" class="btn btn-primary btn-block">Agregar aula</button>
            </g:form>
        </div>
    </content>
</g:applyLayout>
