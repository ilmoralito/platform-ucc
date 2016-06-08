<g:applyLayout name="threeColumns">
    <head>
        <title>Clientes externos</title>
    </head>

    <content tag="main">
        <g:if test="${externalCustomers}">
            <table class="table table-hover">
                <thead>
                    <th>Cliente externo</th>
                </thead>
                <tbody>
                    <g:each in="${externalCustomers}" var="client">
                        <tr>
                            <td>
                                <g:link action="edit" id="${client.id}">
                                    <g:fieldValue bean="${client}" field="name" />
                                </g:link>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>Nada que mostrar</g:else>
    </content>

    <content tag="right-column">
        <g:set var="tab" value="${params.tab ?: 'create'}"/>
        <g:render template="nav"/>

        <g:if test="${!tab || tab != 'filter'}">
            <g:link action="create" class="btn btn-primary btn-block">Crear cliente</g:link>
        </g:if>
        <g:else>
            <g:form name="filterForm" action="index" params="[tab: 'filter']" autocomplete="off">
                <div class="form-group">
                    <label for="name">Nombre</label>
                    <g:textField name="name" value="${params?.name}" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="city">Ciudades</label>
                    <g:each in="['Leon', 'Chinandega', 'Managua']" var="city">
                        <div class="checkbox">
                            <label>
                                <g:checkBox name="cities" value="${city}" checked="${city in params.list('cities')}"/>
                                ${city}
                            </label>
                        </div>
                    </g:each>
                </div>

                <button type="submit" class="btn btn-primary btn-block">
                    Filtrar
                </button>
            </g:form>
        </g:else>
    </content>
</g:applyLayout>
