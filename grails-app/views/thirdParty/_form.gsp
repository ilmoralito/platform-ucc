<div class="form-group">
    <label for="name">Nombre de tercero</label>
    <g:textField name="name" value="${thirdParty?.name}" class="form-control"/>
</div>

<g:if test="${actionName == 'edit'}">
    <div class="checkbox">
        <label>
            <g:checkBox name="enabled" value="${thirdParty.enabled}"/>
            <ucc:enabled enabled="${thirdParty.enabled}"/>
        </label>
    </div>
</g:if>
