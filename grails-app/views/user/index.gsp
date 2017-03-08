<g:applyLayout name="threeColumns">
    <head>
        <title>Usuarios</title>
    </head>

    <content tag="main">
        <g:render template="/layouts/employeeTabs"/>

        <table class="table table-hover">
            <tbody>
                <g:each in="${userList}" var="coordination">
                    <tr>
                        <td style="border-top: 0;">${coordination.coordination}</td>
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
        <section>
            <g:form action="save" autocomplete="off">
                <g:render template="form" model="[coordinationList: employeeWidget.coordinationList, roleList: employeeWidget.roleList]"/>

                <g:submitButton name="send" value="Confirmar" class="btn btn-primary btn-block"/>
            </g:form>
        </section>
    </content>
</g:applyLayout>
