<g:applyLayout name="twoColumns">
    <head>
        <title>Empleado</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <g:link action="edit" params="[thirdPartyId: thirdPartyEmployee.thirdParty.id, thirdPartyEmployeeId: thirdPartyEmployee.id]" class="btn btn-default pull-right">Editar</g:link>
        </div>

        <g:render template="fields" model="[thirdPartyEmployee: thirdPartyEmployee]"/>
    </content>
</g:applyLayout>
