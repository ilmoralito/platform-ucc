<g:applyLayout name="threeColumns">
    <head>
        <title>Usuarios</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <thead>
                <th>Empleados</th>
            </thead>
            <tbody>
                <g:each in="${userList}" var="coordination">
                    <tr>
                        <td>${coordination.coordination}</td>
                    </tr>
                    <g:each in="${coordination.employees}" var="employee">
                        <tr>
                            <td>
                                <g:link action="show" id="${employee.id}">
                                    ${employee.fullName}
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
                <g:render template="form" model="[coordinationList: employeeWidget.coordinationList, roleList: employeeWidget.roleList]"/>

                <g:submitButton name="send" value="Confirmar" class="btn btn-primary btn-block"/>
            </g:form>
        </div>
    </content>
</g:applyLayout>
