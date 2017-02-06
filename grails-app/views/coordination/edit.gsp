<g:applyLayout name="twoColumns">
    <head>
        <title>Coordinacion</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <g:link action="show" params="[name: coordination.name]" class="btn btn-default pull-right">Regresar</g:link>
        </div>

        <div class="row">
            <div class="col-md-6">
                <g:form action="update" autocomplete="off">
                    <g:hiddenField name="id" value="${params.id}"/>
                    <g:render template="form"/>

                    <button type="submit" class="btn btn-primary">Actualizar</button>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
