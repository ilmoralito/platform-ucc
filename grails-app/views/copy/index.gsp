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
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <tr>
                        <th>Nombre del documento</th>
                        <th>Copias</th>
                        <th>Estado</th>
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
                                <td style="vertical-align: middle;">
                                    <g:link action="show" id="${copyInstance.id}">
                                        ${copyInstance.documentDescription}
                                    </g:link>
                                </td>
                                <td style="vertical-align: middle;">${copyInstance.copies}</td>
                                <td>
                                    <g:if test="${copyInstance.status == 'PENDING'}">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <ucc:copyStatus status="${copyInstance.status}"/> <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li>
                                                    <g:link action="updateStatus" params="[id: copyInstance.id, status: 'NOTIFIED']">
                                                        Notificar
                                                    </g:link>
                                                </li>
                                            </ul>
                                        </div>
                                    </g:if>
                                    <g:else>
                                        <ucc:copyStatus status="${copyInstance.status}"/>
                                    </g:else>
                                </td>
                                <td style="vertical-align: middle;">
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
            <g:render template="/shared/copyStatusWidget" model="[copyStatus: copyStatus]"/>
        </section>

        <section class="well well-sm">
            <g:render template="copyOptionWidget" model="[coordinationList: copyOption.coordinationList]"/>
        </section>
    </content>
</g:applyLayout>

