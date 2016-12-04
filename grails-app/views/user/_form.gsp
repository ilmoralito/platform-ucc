<div class="form-group">
    <label for="fullName">Nombre y apellido</label>
    <g:textField name="fullName" class="form-control"/>
</div>

<div class="form-group">
    <label for="institutionalMail">Correo institucional</label>
    <g:field type="email" name="institutionalMail" class="form-control"/>
</div>

<div class="form-group">
    <label style="margin-bottom: 0;">Autoridad</label>
    
    <div class="radio">
        <label>
            <g:radio name="authority" value="Manager"/>
            Coordinador
        </label>
    </div>

    <div class="radio">
        <label>
            <g:radio name="authority" value="Assistant"/>
            Asistente
        </label>
    </div>
</div>

<div class="form-group">
    <label for="identityCard">Cedular</label>
    <g:textField name="identityCard" class="form-control"/>
</div>

<div class="form-group">
    <label for="inss">INSS</label>
    <g:textField name="inss" class="form-control"/>
</div>

<div class="form-group">
    <label style="margin-bottom: 0;">Coordinaciones</label>

    <g:each in="${coordinations}" var="coordination">
        <div class="checkbox">
            <label>
                <g:checkBox name="coordinations" value="${coordination.id}" checked="false"/>
                ${coordination.name}
            </label>
        </div>
    </g:each>
</div>
