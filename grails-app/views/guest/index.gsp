<g:applyLayout name="threeColumns">
    <head>
        <title>Invitados</title>
    </head>

    <content tag="main">
        <g:if test="${guests}">
            <table class="table table-hover">
                <thead>
                    <th>Visitas</th>
                </thead>
                <tbody>
                    <g:each in="${guests}" var="guest">
                        <tr>
                            <td>
                                <g:link action="show" id="${guest.id}">
                                    <g:fieldValue bean="${guest}" field="fullName"/>
                                </g:link>
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
        <div class="well well-sm">
            <g:form action="create" autocomplete="off">
                <g:render template="form"/>

                <g:submitButton name="createGuest" value="Confirmar" class="btn btn-primary btn-block"/>
            </g:form>
        </div>

        <div class="well well-sm">
            <g:form action="index" autocomplete="off">
                <div class="checkbox">
                    <label>
                        <g:checkBox name="enabled" value="true" checked="${true in enabled}"/>
                        Habilitados
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="enabled" value="false" checked="${false in enabled}"/>
                        No habilitados
                    </label>
                </div>

                <g:submitButton name="filterGuests" value="Filtrar" class="btn btn-primary btn-block"/>
            </g:form>
        </div>
    </content>
</g:applyLayout>
