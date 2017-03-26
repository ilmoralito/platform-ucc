<g:applyLayout name="threeColumns">
    <head>
        <title>Copias</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:render template="copy" model="[copy: copy]"/>
    </content>

    <content tag="right-column">
        <section>
            <g:form action="changeToAttended">
                <g:hiddenField name="id" value="${copy.id}"/>
                <g:hiddenField name="status" value="ATTENDED"/>
                
                <button type="submit" class="btn btn-block btn-primary">
                    Marcar como atendido
                </button>
            </g:form>
        </section>

        <div class="panel panel-danger">
            <div class="panel-heading">Zona de peligro</div>
            <div class="panel-body">
                <p>Eliminar orden de copia</p>
                <p>Una vez eliminado la orden de copia, no hay vuelta atraz. Por favor, asegúrese</p>
                <g:link action="delete" id="${copy.id}" class="btn btn-danger btn-block">
                    <i class="fa fa-trash-o" aria-hidden="true"></i> Eliminar
                </g:link>
            </div>
        </div>
    </content>
</g:applyLayout>

