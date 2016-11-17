<div class="form-group">
    <label for="value">A nombre de</label>
    <select name="user" class="form-control">
        <g:each in="${users}" var="user">
            <g:set var="selected" value="${user.id == params.int('user')}"/>

            <option value="${user.id}" ${selected ? 'selected=selected' : ''}>
                ${type == 'user' ? user['username'] : user['fullName']}
            </option>
        </g:each>
    </select>
</div>

<div class="form-group">
    <label for="value">Valor</label>
    <g:textField name="value" value="${params?.value}" class="form-control"/>
</div>

<div class="form-group">
    <label style="margin-bottom: 0;">Medios</label>

    <g:each in="${foods}" var="food">
        <div class="checkbox">
            <label>
                <g:checkBox name="foods" value="${food.english}" checked="${food.english in params.list('foods')}"/>
                ${food.spanish}
            </label>
        </div>
    </g:each>
</div>
