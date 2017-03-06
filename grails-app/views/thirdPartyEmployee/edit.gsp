<g:applyLayout name="twoColumns">
    <head>
        <title>Editar Empleado</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <g:link action="show" params="[thirdPartyId: thirdPartyEmployee.thirdParty.id, thirdPartyEmployeeId: thirdPartyEmployee.id]" class="btn btn-default pull-right">Regresar</g:link>
        </div>

        <g:form action="update" autocomplete="off">
            <g:hiddenField name="thirdPartyEmployeeId" value="${thirdPartyEmployee.id}"/>
            <g:hiddenField name="_method" value="PUT"/>
            <g:render template="form"/>

            <button type="submit" class="btn btn-primary">Confirmar</button>
        </g:form>
    </content>
</g:applyLayout>
