<g:applyLayout name="threeColumns">
    <head>
        <title>Usuarios</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <thead>
                <th>Usuarios</th>
            </thead>
            <tbody>
                <g:each in="${users}" var="user">
                    <tr>
                        <td>
                            <g:link action="edit" id="${user.id}">
                                <g:fieldValue bean="${user}" field="username"/>
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

                <g:submitButton name="send" value="Confirmar" class="btn btn-primary btn-block"/>
            </g:form>
        </div>
    </content>
</g:applyLayout>
