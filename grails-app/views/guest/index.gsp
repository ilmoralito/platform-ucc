<g:applyLayout name="threeColumns">
    <head>
        <title>Invitados</title>
    </head>

    <content tag="main">
        <g:if test="${guests}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 88%;">
                    <col span="1" style="width: 12%;">
                </colgroup>
                <thead>
                    <th>Lista de invitados</th>
                    <th>Estado</th>
                </thead>
                <tbody>
                    <g:each in="${guests}" var="guest">
                        <tr>
                            <td>
                                <g:link action="show" id="${guest.id}">
                                    <g:fieldValue bean="${guest}" field="fullName"/>
                                </g:link>
                            </td>
                            <td>
                                <g:if test="${guest.enabled}">
                                    Habilitado
                                </g:if>
                                <g:else>
                                    No habilitado
                                </g:else>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>

    <content tag="right-column">
        <g:form action="create" autocomplete="off">
            <g:render template="form"/>

            <g:submitButton name="createGuest" value="Agregar" class="btn btn-primary btn-block"/>
        </g:form>
    </content>
</g:applyLayout>
