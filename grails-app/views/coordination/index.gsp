<g:applyLayout name="threeColumns">
    <head>
        <title>Coordinaciones</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <thead>
                <th>Coordinaciones</th>
            </thead>
            <tbody>
                <g:each in="${coordinations}" var="coordination">
                    <tr>
                        <td>
                            <g:link action="show" id="${coordination.id}">
                                ${coordination.name}
                            </g:link>
                        </td>
                    </tr>
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
