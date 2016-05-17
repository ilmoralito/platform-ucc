<p>Cliente</p>
<div class="form-group">
    <label for="name">Nombre del cliente</label>
    <g:textField name="name" value="${externalCustomer?.name}" class="form-control"/>
</div>

<div class="form-group">
    <label for="email">Email</label>
    <g:field type="email" name="email" value="${externalCustomer?.email}" class="form-control"/>
</div>

<div class="form-group">
    <label for="telephoneNumber">Numero telefonico</label>
    <g:field type="tel" minlength="8" maxlength="8" name="telephoneNumber" value="${externalCustomer?.telephoneNumber}" class="form-control"/>
</div>

<p>Contacto</p>
<div class="form-group">
    <label for="fullName">Nombre y apellido</label>
    <g:textField name="contact.fullName" value="${externalCustomer?.contact?.fullName}" class="form-control"/>
</div>

<div class="form-group">
    <label for="identityCard">Cedula</label>
    <g:textField name="contact.identityCard" value="${externalCustomer?.contact?.identityCard}" class="form-control"/>
</div>

<div class="form-group">
    <label for="address">Direccion</label>
    <g:textField name="contact.address" value="${externalCustomer?.contact?.address}" class="form-control"/>
</div>

<div class="form-group">
    <label for="contact.email">Email</label>
    <g:field type="email" name="contact.email" value="${externalCustomer?.contact?.email}" class="form-control"/>
</div>

<div class="form-group">
    <label for="telephoneNumber">Numero telefonico</label>
    <g:field type="tel" minlength="8" maxlength="8" name="contact.telephoneNumber" value="${externalCustomer?.contact?.telephoneNumber}" class="form-control"/>
</div>