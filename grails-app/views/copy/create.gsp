<g:applyLayout name="threeColumns">
    <head>
        <title>Copia</title>
    </head>

    <content tag="main">
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
