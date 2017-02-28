<g:applyLayout name="threeColumns">
    <head>
        <title>Copia</title>
    </head>

    <content tag="main">
        <section class="clearfix">
            <g:link action="index" class="btn btn-default">Regresar</g:link>
        </section>

        <g:form action="${params.copyType == 'copy' ? 'post' : 'postExtraCopy'}" autocomplete="off">
            <g:hiddenField name="coordination" value="${params.coordination}"/>
            <g:render template="form"/>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
        </g:form>
    </content>

    <content tag="right-column">
        <section>
            <g:render template="/shared/copyStatusWidget" model="[copyStatus: copyStatus]"/>
        </section>
    </content>
</g:applyLayout>
