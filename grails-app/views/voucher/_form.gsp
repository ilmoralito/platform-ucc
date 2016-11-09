<div class="form-group">
    <label for="value">A nombre de</label>
    <g:select name="user" from="${users}" optionKey="id" optionValue="${type == 'user' ? 'username' : 'fullName'}" class="form-control"/>
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
                <g:checkBox name="foods" value="${food.english}" checked="${food in params.list('foods')}"/>
                ${food.spanish}
            </label>
        </div>
    </g:each>
</div>