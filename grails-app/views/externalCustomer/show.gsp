<g:applyLayout name="twoColumns">
    <head>
        <title>Cliente externo</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <div class="pull-right">
                <g:link action="edit" id="${externalCustomer?.id}" class="btn btn-default">Editar</g:link>
                <g:link action="index" class="btn btn-default">Regresar</g:link>
            </div>
        </div>

        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 20%;">
                <col span="1" style="width: 80%;">
            </colgroup>
            <tbody>
                <tr>
                    <td colspan="2">Datos de empresa</td>
                </tr>
                <tr>
                    <td>Nombre</td>
                    <td><g:fieldValue bean="${externalCustomer}" field="name"/></td>
                </tr>
                <tr>
                    <td>Ciudad</td>
                    <td><g:fieldValue bean="${externalCustomer}" field="city"/></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><g:fieldValue bean="${externalCustomer}" field="email"/></td>
                </tr>
                <tr>
                    <td>Telefono</td>
                    <td><g:fieldValue bean="${externalCustomer}" field="telephoneNumber"/></td>
                </tr>
                <tr>
                    <td colspan="2">Datos de contacto</td>
                </tr>
                <tr>
                    <td>Nombre y apellido</td>
                    <td><g:fieldValue bean="${externalCustomer.contact}" field="fullName"/></td>
                </tr>
                <tr>
                    <td>Cedula</td>
                    <td><g:fieldValue bean="${externalCustomer.contact}" field="identityCard"/></td>
                </tr>
                <tr>
                    <td>Direccion</td>
                    <td><g:fieldValue bean="${externalCustomer.contact}" field="address"/></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><g:fieldValue bean="${externalCustomer.contact}" field="email"/></td>
                </tr>
                <tr>
                    <td>Telefono</td>
                    <td><g:fieldValue bean="${externalCustomer.contact}" field="telephoneNumber"/></td>
                </tr>
            </tbody>
        </table>
    </content>
</g:applyLayout>
