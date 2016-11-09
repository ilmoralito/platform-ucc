<div class="row">
    <div class="col-md-6">
        <p>Datos de empresa</p>
        <div class="form-group">
            <label for="name">Nombre</label>
            <g:textField name="name" value="${externalCustomer?.name}" class="form-control"/>
        </div>

        <div class="form-group">
            <label for="city">Ciudad</label>
            <g:select
                name="city"
                from="['Leon', 'Chinandega', 'Managua']"
                noSelection="['':'-Selecciona ciudad-']"
                value="${externalCustomer?.city}"
                class="form-control"/>
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <g:field type="email" name="email" value="${externalCustomer?.email}" class="form-control"/>
        </div>

        <div class="form-group">
            <label for="telephoneNumber">Numero telefonico</label>
            <g:field type="tel" minlength="8" maxlength="8" name="telephoneNumber" value="${externalCustomer?.telephoneNumber}" class="form-control"/>
        </div>

        <p>Datos de contacto</p>
        <div class="form-group">
            <label for="contact.fullName">Nombre y apellido</label>
            <g:textField name="contact.fullName" value="${externalCustomer?.contact?.fullName}" class="form-control"/>
        </div>

        <div class="form-group">
            <label for="contact.identityCard">Cedula</label>
            <g:textField name="contact.identityCard" value="${externalCustomer?.contact?.identityCard}" class="form-control"/>
        </div>

        <div class="form-group">
            <label for="contact.address">Direccion</label>
            <g:textField name="contact.address" value="${externalCustomer?.contact?.address}" class="form-control"/>
        </div>

        <div class="form-group">
            <label for="contact.email">Email</label>
            <g:field type="email" name="contact.email" value="${externalCustomer?.contact?.email}" class="form-control"/>
        </div>

        <div class="form-group">
            <label for="contact.telephoneNumber">Numero telefonico</label>
            <g:field type="tel" name="contact.telephoneNumber" minlength="8" maxlength="8" value="${externalCustomer?.contact?.telephoneNumber}" class="form-control"/>
        </div>
    </div>
</div>
