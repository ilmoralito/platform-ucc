<g:applyLayout name="twoColumns">
    <head>
        <title>Actividad</title>
    </head>

    <content tag="main">
        <g:form id="form" action="activityName" params="[index: params?.index]" autocomplete="off">
            <div class="form-group">
                <label for="name">Nombre de la actividad</label>
                <g:textField
                    name="name"
                    value="${session?.activity?.name}"
                    class="form-control"
                    autofocus="true"/>
            </div>

            <sec:ifAnyGranted roles="ROLE_PROTOCOL_COORDINATOR">
                <div class="form-group">
                    <label for="externalCustomer">Cliente externo</label>
                    <ucc:externalCustomers
                        customers="${externalCustomers}"
                        externalCustomer="${session?.activity?.externalCustomer?.attach()?.id}"/>
                </div>
            </sec:ifAnyGranted>

            <g:submitButton name="send" value="Confirmar y continuar" class="btn btn-primary"/> 
        </g:form>
    </content>
</g:applyLayout>
