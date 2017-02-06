<div class="form-group">
    <label for="name">Nombre de la coordinacion</label>
    <g:textField name="name" value="${coordination?.name}" class="form-control"/>
</div>

<div class="form-group">
    <label for="extensionNumber">Numero de extension</label>
    <g:textField name="extensionNumber" value="${coordination?.extensionNumber}" class="form-control"/>
</div>

<div class="form-group">
    <label for="printQuota">Cuota de copias</label>
    <g:textField name="printQuota" value="${coordination?.printQuota}" class="form-control"/>
</div>

<div class="form-group">
    <label>Area</label>

    <div class="radio">
        <label>
            <g:radio name="location" value="Administrative" checked="${coordination?.location == 'Administrative'}"/>
            Administrativa
        </label>
    </div>

    <div class="radio">
        <label>
            <g:radio name="location" value="Academic" checked="${coordination?.location == 'Academic'}"/>
            Academica
        </label>
    </div>
</div>

<div class="form-group">
    <label>Colores</label>

    <g:each in="${colors}" var="color">
        <div class="checkbox">
            <label>
                <g:checkBox name="colors" value="${color.id}" checked="${color.name in coordination?.colors?.name}"/>
                ${color.name}
            </label>
        </div>
    </g:each>
</div>
