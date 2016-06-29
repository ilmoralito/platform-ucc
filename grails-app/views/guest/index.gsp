<g:applyLayout name="threeColumns">
    <head>
        <title>Invitados</title>
    </head>

    <content tag="main">
        <g:if test="${guests}">
            <table class="table table-hover">
                <thead>
                    <th>Invitados</th>
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
        <ul class="nav nav-tabs">
            <li role="presentation" class="${params.tab == 'index' || !params.tab ? 'active' : ''}">
                <g:link action="index">Crear</g:link>
            </li>
            <li role="presentation" class="${params.tab == 'filter' ? 'active' : ''}">
                <g:link action="index" params="[tab: 'filter']">Filtro</g:link>
            </li>
        </ul>

        <g:if test="${params.tab == 'index' || !params.tab ? 'active' : ''}">
            <g:form name="createGuestForm" action="create">
                <label for="fullName">Nombre completo</label>
                <div class="form-group">
                    <g:textField name="fullName" value="${params?.fullName}" class="form-control"/>
                </div>

                <g:submitButton name="createGuest" value="Agregar" class="btn btn-primary btn-block"/>
            </g:form>
        </g:if>
        <g:else>
            <g:form name="filterForm" action="index" params="[tab: 'filter']">
                <p>Estado</p>
                <div class="checkbox">
                    <label>
                        <g:checkBox name="enabled" value="true" checked="${'true' in params.enabled}"/>
                        Habilitado
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="enabled" value="false" checked="${'false' in params.enabled}"/>
                        No habilitado
                    </label>
                </div>

                <g:submitButton name="filterGuest" value="Confirmar" class="btn btn-primary btn-block"/>
            </g:form>
        </g:else>
    </content>
</g:applyLayout>
