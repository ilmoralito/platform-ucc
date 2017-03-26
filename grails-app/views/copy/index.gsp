<g:applyLayout name="threeColumns">
    <head>
        <title>Copias</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${copyList}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 65%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <tr>
                        <th>Nombre del documento</th>
                        <th>Estado</th>
                        <th>Copias</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${copyList}" var="${copy}">
                        <tr>
                            <td colspan="4">${copy.coordination}</td>
                        </tr>
                        <g:each in="${copy.copies}" var="copyInstance">
                            <tr>
                                <td>
                                    <g:link action="show" id="${copyInstance.id}">
                                        ${copyInstance.documentDescription}
                                    </g:link>
                                </td>
                                <td>
                                    <ucc:copyStatus status="${copyInstance.status}"/>
                                </td>
                                <td>${copyInstance.copies}</td>
                                <td>
                                    <g:if test="${copyInstance.copyType == 'extraCopy'}">
                                        <span class="label label-primary">Extra</span>
                                    </g:if>
                                </td>
                            </tr>
                        </g:each>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin copies que mostrar</p>
        </g:else>
    </content>

    <content tag="right-column">
        <section>
            <g:render template="/shared/copyStatusWidget" model="[copyStatusList: copyStatus.statusList]"/>
        </section>

        <section>
            <g:render template="createCopyWidget" model="[coordinationList: createCopy.coordinationList, copyTypeList: createCopy.copyTypeList]"/>
        </section>
    </content>
</g:applyLayout>
