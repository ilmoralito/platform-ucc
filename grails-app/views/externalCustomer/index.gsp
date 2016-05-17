<g:applyLayout name="threeColumns">
    <head>
        <title>Clientes externos</title>
    </head>

    <content tag="main">
        <g:if test="${externalCustomers}">
            <table class="table table-hover">
                <thead>
                    <th>Cliente externo</th>
                </thead>
                <tbody>
                    <g:each in="${externalCustomers}" var="client">
                        <tr>
                            <td>
                                <g:link action="edit" id="${client.id}">
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
        <g:set var="tab" value="${params.tab ?: 'create'}"/>

        <g:render template="nav"/>

            <g:form action="index">
                <g:if test="${tab == 'create'}">
                    <g:render template="form"/>

                    <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
                </g:if>
                <g:else>
                    <p>To be implemented</p>
                </g:else>
            </g:form>
    </content>
</g:applyLayout>
