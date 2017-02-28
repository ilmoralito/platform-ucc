<g:applyLayout name="twoColumns">
    <head>
        <title>Copia</title>
    </head>

    <content tag="main">
        <section class="clearfix">
            <g:link action="index" class="btn btn-default">Regresar</g:link>

            <div class="pull-right">
                <g:link action="delete" id="${copy.id}" class="btn btn-warning">
                    <i class="fa fa-trash-o" aria-hidden="true"></i> Eliminar
                </g:link>
                <g:link action="edit" params="[id: copy.id, copyType: copy.containsKey('type') ? copy.type : 'extraCopy']" class="btn btn-default">
                    <i class="fa fa-pencil" aria-hidden="true"></i> Editar
                </g:link>
            </div>
        </section>

        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>
            <tbody>
                <tr>
                    <td>Descripcion del documento</td>
                    <td>${copy.documentDescription}</td>
                </tr>
                <tr>
                    <td>Copias</td>
                    <td>${copy.copies}</td>
                </tr>
                <tr>
                    <td>Estado</td>
                    <td>
                        <ucc:copyStatus status="${copy.status}"/>
                    </td>
                </tr>
                <tr>
                    <td>Fecha de creasion</td>
                    <td>
                        ${copy.dateCreated}
                    </td>
                </tr>
            </tbody>
        </table>
    </content>
</g:applyLayout>

