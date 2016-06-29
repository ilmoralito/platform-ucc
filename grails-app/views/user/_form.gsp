<div class="row">
    <div class="col-md-6">
        <div class="form-group">
            <p>Cuenta</p>
            <label for="username">Nombre de usuario</label>
            <g:textField
                name="username"
                value="${user?.username}"
                class="form-control"/>
        </div>

        <div class="checkbox">
            <label for="enabled">
                <g:checkBox name="enabled" value="${user.enabled}"/>
                Habilitado
            </label>
        </div>

    </div>

    <div class="col-md-6">
        <p>Perfil</p>
        <div class="form-group">
            <label for="fullName">Nombre</label>
            <g:textField
                name="fullName"
                value="${employee?.fullName}"
                class="form-control"/>
        </div>

        <div class="form-group">
            <label for="institutionalMail">Correo institucional</label>
            <g:textField
                name="institutionalMail"
                value="${employee?.institutionalMail}"
                class="form-control"/>
        </div>

        <div class="form-group">
            <label for="position">Cargo</label>
            <g:textField
                name="position"
                value="${employee?.position}"
                class="form-control"/>
        </div>

        <div class="form-group">
            <label for="authority">Autoridad</label>
            <g:select
                name="authority"
                from="['Manager', 'Assistant']"
                value="${employee?.authority}" class="form-control"/>
        </div>

        <div class="form-group">
            <label for="identityCard">Numero de Cedula</label>
            <g:textField
                name="identityCard"
                value="${employee?.identityCard}"
                class="form-control"/>
        </div>

        <div class="form-group">
            <label for="inss">INSS</label>
            <g:textField
                name="inss"
                value="${employee?.inss}"
                class="form-control"/>
        </div>

        <div class="form-group">
            <label>Nombre de coordinaciones</label>
            <p>${employee.coordinations.name.join(', ')}</p>
        </div>
    </div>
</div>
