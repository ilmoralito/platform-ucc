<g:applyLayout name="twoColumns">
    <head>
        <title>Tercero</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <g:link action="show" id="${thirdParty.id}" class="btn btn-default pull-right">Regresar</g:link>
        </div>

        <g:form action="update" autocomplete="off">
            <g:hiddenField name="id" value="${thirdParty.id}"/>
            <g:hiddenField name="_method" value="PUT"/>
            <g:render template="form"/>

            <button type="submit" class="btn btn-primary">Confirmar</button>
        </g:form>
    </content>
</g:applyLayout>

