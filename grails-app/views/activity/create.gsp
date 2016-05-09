<g:applyLayout name="threeColumns">
    <head>
        <title>Actividad</title>
    </head>

    <content tag="main">
        <g:form id="form" action="create" autocomplete="off">
            <div class="form-group">
                <label for="name">Nombre de la actividad</label>
                <g:textField name="name" class="form-control" autofocus="true"/>
            </div>

            <g:submitButton name="send" value="Confirmar y continuar" class="btn btn-primary"/> 
        </g:form>
    </content>

    <content tag="right-column">
        <ucc:profile/>
    </content>
</g:applyLayout>
