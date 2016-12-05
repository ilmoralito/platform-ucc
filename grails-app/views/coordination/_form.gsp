<div class="form-group">
    <label for="name">Nombre de la coordinacion</label>
    <g:textField name="name" value="${params?.name}" class="form-control"/>
</div>

<div class="form-group">
    <label for="extensionNumber">Numero de extension</label>
    <g:textField name="extensionNumber" value="${params?.extensionNumber}" class="form-control"/>
</div>

<div class="form-group">
    <label for="printQuota">Cuota de impresion</label>
    <g:textField name="printQuota" value="${params?.printQuota}" class="form-control"/>
</div>

<div class="form-group">
    <label style="margin-bottom: 0;">Area</label>

    <div class="radio">
        <label>
            <g:radio name="location" value="Administrative"/>
            Administrativa
        </label>
    </div>

    <div class="radio">
        <label>
            <g:radio name="location" value="Academic"/>
            Academica
        </label>
    </div>
</div>

<div class="form-group">
    <label style="margin-bottom: 0;">Colores</label>

    <g:each in="${colors}" var="color">
        <div class="checkbox">
            <label>
                <g:checkBox name="colors" value="${color.id}" checked="false"/>
                ${color.name}
            </label>
        </div>
    </g:each>
</div>
