<g:applyLayout name="twoColumns">
    <head>
        <title>Usuario</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <g:link action="show" id="${employee.id}" class="btn btn-default pull-right">Regresar</g:link>
        </div>

        <g:form action="update" autocomplete="off">
            <g:hiddenField name="id" value="${employee.id}"/>
            <g:render template="form" model="[coordinationList: employeeWidget.coordinationList, roleList: employeeWidget.roleList]"/>

            <g:submitButton name="updateEmployee" value="Confirmar" class="btn btn-primary"/>
        </g:form>
    </content>
</g:applyLayout>
