<g:applyLayout name="twoColumns">
    <head>
        <title>Clientes externos</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <g:link action="create" class="btn btn-primary pull-right">Agregar cliente</g:link>
        </div>

        <g:if test="${externalCustomers}">
            <table class="table table-hover">
                <thead>
                    <th>Cliente externo</th>
                </thead>
                <tbody>
                    <g:each in="${externalCustomers}" var="client">
                        <tr>
                            <td>
                                <g:link action="show" id="${client.id}">
                                    <g:fieldValue bean="${client}" field="name" />
                                </g:link>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>Nada que mostrar</g:else>
    </content>

    <content tag="right-column">
        <g:form action="store" autocomplete="off">
            <g:render template="form"/>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary btn-block"/>
        </g:form>
    </content>
</g:applyLayout>
