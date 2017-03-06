<g:if test="${thirdPartyList.size() == 1}">
    <g:hiddenField name="thirdParty" value="${thirdPartyList[0].id}"/>
</g:if>
<g:else>
    <div class="form-group">
        <label for="thirdParty">Tercero</label>
        <g:select name="thirdParty" from="${thirdPartyList}" value="${thirdPartyEmployee?.thirdParty?.id}" optionKey="id" optionValue="name" class="form-control"/>
    </div>
</g:else>

<div class="form-group">
    <label for="fullName">Nombre completo</label>
    <g:textField name="fullName" value="${thirdPartyEmployee?.fullName}" class="form-control"/>
</div>

<div class="form-group">
    <label for="email">Correo electronico</label>
    <g:textField name="email" value="${thirdPartyEmployee?.email}" class="form-control"/>
</div>

<g:if test="${actionName == 'edit'}">
    <div class="checkbox">
        <label>
            <g:checkBox name="enabled" value="${thirdPartyEmployee.enabled}"/>
            <ucc:enabled enabled="${thirdPartyEmployee.enabled}"/>
        </label>
    </div>
</g:if>
