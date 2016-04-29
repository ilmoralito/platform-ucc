<g:applyLayout name="threeColumns">
    <head>
        <title>Actividad</title>
    </head>

    <content tag="main">
        <g:form action="save" autocomplete="off">
            <g:render template="form"/>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/> 
        </g:form>
    </content>

    <content tag="right-column">
        <div class="panel panel-default">
            <div class="panel-heading">Informacion</div>
            <table class="table">
                <tbody>
                    <tr>
                        <td>Usuario</td>
                    </tr>
                    <tr>
                        <td><ucc:getUserInfo field="fullName"/></td>
                    </tr>
                    <tr>
                        <td>Coordinacion</td>
                    </tr>
                    <tr>
                        <td><ucc:getUserInfo field="name"/></td>
                    </tr>
                    <tr>
                        <td>Color</td>
                    </tr>
                    <tr>
                        <td><ucc:getUserInfo field="color"/></td>
                    </tr>
                    <tr>
                        <td>Area</td>
                    </tr>
                    <tr>
                        <td><ucc:getUserInfo field="location"/></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <button class="btn btn-default btn-block">1</button>
    </content>
</g:applyLayout>
