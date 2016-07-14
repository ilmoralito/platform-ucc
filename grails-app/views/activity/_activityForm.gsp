<g:set var="name" value="${actionName == 'show' ? activity.name : session?.activity?.name}"/>
<g:set var="externalCustomer" value="${actionName == 'show' ? activity?.externalCustomer?.id : session?.activity?.externalCustomer?.id}"/>

<div class="form-group">
    <label for="name">Nombre de la actividad</label>
    <g:textField
        name="name"
        value="${name}"
        class="form-control"/>
</div>

<g:if test="${coordinations?.size() > 1}">
    <div class="form-group">
        <label for="coordination">Coordinacion</label>
        <g:select
            name="coordination"
            from="${coordinations}"
            optionKey="name"
            optionValue="name"
            value="${activity?.coordination ?: session?.coordination}"
            class="form-control"/>
    </div>
</g:if>
<g:else>
    <g:hiddenField name="coordination" value="${coordinations[0].name}"/>
</g:else>

<sec:ifAnyGranted roles="ROLE_PROTOCOL_SUPERVISOR">
    <div class="form-group">
        <label for="externalCustomer">Cliente externo</label>
        <ucc:externalCustomers
            customers="${externalCustomers}"
            externalCustomer="${externalCustomer}"/>
    </div>
</sec:ifAnyGranted>
