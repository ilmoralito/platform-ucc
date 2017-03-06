<g:applyLayout name="threeColumns">
    <head>
        <title>Roles</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <thead>
                <th>Roles</th>
            </thead>
            <tbody>
                <g:each in="${roles}" var="role">
                    <tr>
                        <td>
                            <g:link action="edit" id="${role.id}">
                                <g:fieldValue bean="${role}" field="authority"/>
                            </g:link>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </content>

    <content tag="right-column">
        <section>
            <g:form action="save" autocomplete="off">
                <g:render template="form"/>

                <g:submitButton name="send" value="Confirmar" class="btn btn-primary btn-block"/>
            </g:form>
        </section>
    </content>
</g:applyLayout>
