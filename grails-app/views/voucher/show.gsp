<g:applyLayout name="twoColumns">
    <head>
        <title>Vale</title>
    </head>

    <content tag="main">
        <section>
            <div class="clearfix">
                <div class="pull-right">
                    <g:link action="delete" id="${voucher?.id}" class="btn btn-danger">Eliminar</g:link>
                    <g:link action="edit" id="${voucher?.id}" class="btn btn-primary">Editar</g:link>
                </div>
            </div>
        </section>
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>
            <tbody>
                <tr>
                    <td>A nombre de</td>
                    <td>
                        <g:formatBoolean boolean="${voucher.user != null}" true="${voucher?.user?.username}" false="${voucher?.guest?.fullName}"/>
                    </td>
                </tr>
                <tr>
                    <td>Nombre de la actividad</td>
                    <td>${voucher.activity}</td>
                </tr>
                <tr>
                    <td>Fecha</td>
                    <td>
                        <g:formatDate date="${voucher.date}" format="yyyy-MM-dd"/>
                    </td>
                </tr>
                <tr>
                    <td>Alimentos</td>
                    <td>
                        <ucc:foodInSpanish foods="${voucher.foods.name}"/>
                    </td>
                </tr>
                <tr>
                    <td>Valor de vale</td>
                    <td>
                        <g:formatNumber number="${voucher.value}" type="currency" currencyCode="NIO"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="label-data">Metadata</td>
                </tr>
                <tr>
                    <td>Fecha de creacion</td>
                    <td><g:formatDate date="${voucher.dateCreated}" format="yyyy-MM-dd HH:mm"/></td>
                </tr>
                <tr>
                    <td>Mas reciente actualizacion</td>
                    <td><g:formatDate date="${voucher.lastUpdated}" format="yyyy-MM-dd HH:mm"/></td>
                </tr>
                <g:if test="${voucher?.approvalDate}">
                    <tr>
                        <td>Fecha de autorizacion</td>
                        <td><g:formatDate date="${voucher.approvalDate}" format="yyyy-MM-dd HH:mm"/></td>
                    </tr>
                </g:if>
            </tbody>
        </table>
    </content>
</g:applyLayout>
