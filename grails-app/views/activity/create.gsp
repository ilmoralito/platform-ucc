<g:applyLayout name="threeColumns">
    <head>
        <title>Actividad</title>
    </head>

    <content tag="main">
        <g:form id="form" action="save" autocomplete="off">
            <g:render template="form"/>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/> 
        </g:form>
    </content>

    <content tag="right-column">
        <label>Nombre de usuario</label>
        <p><ucc:getUserInfo field="fullName"/></p>

        <label>Coordinacion</label>
        <p><ucc:getUserInfo field="name"/></p>

        <label>Color</label>
        <p><ucc:getUserInfo field="color"/></p>

        <label>Area</label>
        <p><ucc:getUserInfo field="location"/></p>
    </content>
</g:applyLayout>
