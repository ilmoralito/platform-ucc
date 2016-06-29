<g:applyLayout name="threeColumns">
    <head>
        <title>Coordinaciones</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 5%;">
                <col span="1" style="width: 95%;">
            </colgroup>
            <thead>
                <th style="text-align: center;">#</th>
                <th>Nombre de coordinacion</th>
            </thead>
            <tbody>
                <g:each in="${coordinations}" var="c" status="idx">
                    <tr>
                        <td style="text-align: center;">${idx + 1}</td>
                        <td>
                            <g:link action="show" id="${c.id}">${c.name}</g:link>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
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
            <g:form name="createCoordinationForm" action="create" autocomplete="off">
                <g:render template="form"/>

                <button type="submit" class="btn btn-primary btn-block">Agregar</button>
            </g:form>
        </g:if>
        <g:else>
            <g:form name="filterCoordinationForm" action="index" autocomplete="off">
                <div class="form-group">
                    <label for="extensionNumber">Numero de extension</label>
                    <g:textField name="extensionNumber" value="${params?.extensionNumber}" class="form-control"/>
                </div>

                <div class="form-group">
                    <label style="display: block; margin-bottom: 1px;">Area</label>
                    <div class="checkbox">
                        <label>
                            <g:checkBox name="location" value="Administrative" checked="${params.list('location').contains('Administrative')}"/>
                            Administrativo
                        </label>
                    </div>

                    <div class="checkbox">
                        <label>
                            <g:checkBox name="location" value="Academic" checked="${params.list('location').contains('Academic')}"/>
                            Academico
                        </label>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary btn-block">Filtrar</button>
            </g:form>
        </g:else>
    </content>
</g:applyLayout>
