<g:applyLayout name="threeColumns">
    <head>
        <title>Coordinaciones</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Coordinaciones</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${coordinationsByLocation}" var="location">
                    <tr>
                        <td>
                            ${location.location}
                        </td>
                    </tr>
                    <g:each in="${location.coordinations}" var="coordination">
                        <tr>
                            <td>
                                <g:link action="show" params="[name: coordination.name]">
                                    ${coordination.name}
                                </g:link>
                            </td>
                        </tr>
                    </g:each>
                </g:each>
            </tbody>
        </table>
    </content>

    <content tag="right-column">
        <div class="well well-sm">
            <g:form action="save" autocomplete="off">
                <g:render template="form"/>

                <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
            </g:form>
        </div>
    </content>
</g:applyLayout>
