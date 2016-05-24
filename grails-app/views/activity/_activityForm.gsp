<g:set var="name" value="${actionName == 'show' ? activity.name : session?.activity?.name}"/>
<g:set var="externalCustomer" value="${actionName == 'show' ? activity?.externalCustomer?.id : session?.activity?.externalCustomer?.attach()?.id}"/>

<div class="form-group">
    <label for="name">Nombre de la actividad</label>
    <g:textField
        name="name"
        value="${name}"
        class="form-control"/>
</div>

<sec:ifAnyGranted roles="ROLE_PROTOCOL_COORDINATOR">
    <div class="form-group">
        <label for="externalCustomer">Cliente externo</label>
        <ucc:externalCustomers
            customers="${externalCustomers}"
            externalCustomer="${externalCustomer}"/>
    </div>
</sec:ifAnyGranted>