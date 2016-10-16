<div class="form-group">
    <label for="fullName">Nombre completo</label>
    <g:textField name="fullName" value="${guest?.fullName}" class="form-control"/>
</div>

<g:if test="${actionName == 'show'}">
    <div class="form-group">
        <div class="checkbox">
            <label>
                <g:checkBox name="enabled" value="${guest?.enabled}"/> Habilitado
            </label>
        </div>
    </div>
</g:if>
