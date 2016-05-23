<g:applyLayout name="twoColumns">
    <head>
        <title>Actividad</title>
    </head>

    <content tag="main">
        <g:form id="form" action="activityName" params="[index: params?.index]" autocomplete="off">
            <g:render template="activityForm"/>

            <g:submitButton name="send" value="Confirmar y continuar" class="btn btn-primary"/> 
        </g:form>
    </content>
</g:applyLayout>
