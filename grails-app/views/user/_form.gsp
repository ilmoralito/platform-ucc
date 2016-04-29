<div class="form-group">
    <g:textField
        name="username"
        value="${user?.username}"
        class="form-control"
        placeholder="Nombre de usuario"/>
</div>

<div class="form-group">
    <g:textField
        name="email"
        value="${user?.email}"
        class="form-control"
        placeholder="Correo institucional"/>
</div>

<p>Perfil</p>

<div class="form-group">
    <g:textField
        name="fullName"
        value="${employee?.fullName}"
        class="form-control"
        placeholder="Nombre completo"/>
</div>

<div class="form-group">
    <g:textField
        name="position"
        value="${employee?.position}"
        class="form-control"
        placeholder="Posicion"/>
</div>

<div class="form-group">
    <g:select
        name="authority"
        from="['Manager', 'Assistant']"
        value="${employee?.authority}"
        class="form-control"/>
</div>

<div class="form-group">
    <g:textField
        name="identityCard"
        value="${employee?.identityCard}"
        class="form-control"
        placeholder="Cedula"/>
</div>

<div class="form-group">
    <g:textField
        name="inss"
        value="${employee?.inss}"
        class="form-control"
        placeholder="INSS"/>
</div>

<div class="form-group">
    <g:textField
        name="employee.coordination"
        value="${employee.coordination.name}"
        class="form-control"
        placeholder="Coordinacion"/>
</div>
