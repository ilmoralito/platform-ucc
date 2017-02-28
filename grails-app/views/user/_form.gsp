<div class="form-group">
    <label for="fullName">Nombre y apellido</label>
    <g:textField name="fullName" value="${employee?.fullName}" class="form-control"/>
</div>

<div class="form-group">
    <label for="institutionalMail">Correo institucional</label>
    <g:field type="email" name="institutionalMail" value="${employee?.institutionalMail}" class="form-control"/>
</div>

<div class="form-group">
    <label for="identityCard">Cedula</label>
    <g:textField name="identityCard" value="${employee?.identityCard}" class="form-control"/>
</div>

<div class="form-group">
    <label for="inss">INSS</label>
    <g:textField name="inss" value="${employee?.inss}" class="form-control"/>
</div>

<div class="form-group">
    <label style="margin-bottom: 0;">Autoridad</label>
    
    <div class="radio">
        <label>
            <g:radio name="authority" value="Manager" checked="${employee?.authority == 'Manager'}"/>
            Coordinador
        </label>
    </div>

    <div class="radio">
        <label>
            <g:radio name="authority" value="Assistant" checked="${employee?.authority == 'Assistant'}"/>
            Asistente
        </label>
    </div>
</div>

<div class="form-group">
    <label style="margin-bottom: 0;">Coordinaciones</label>

    <g:each in="${coordinationList}" var="coordination">
        <div class="checkbox">
            <label>
                <g:checkBox name="coordinations" value="${coordination.name}" checked="${coordination.name in employee?.coordinations?.name}"/>
                ${coordination.name}
            </label>
        </div>
    </g:each>
</div>

<div class="form-group">
    <label style="margin-bottom: 0;">Roles</label>

    <g:each in="${roleList}" var="role">
        <div class="checkbox">
            <label>
                <g:checkBox name="authorities" value="${role.authority}" checked="${user?.authorities?.authority?.contains(role.authority)}"/>
                ${role.authority.toLowerCase().tokenize('_')[1..-1].join(' ').capitalize()}
            </label>
        </div>
    </g:each>
</div>

