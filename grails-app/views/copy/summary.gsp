<g:applyLayout name="threeColumns">
    <head>
        <title>Resumen</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${copyList}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 1%;">
                    <col span="1" style="width: 30%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 45%;">
                    <col span="1" style="width: 4%;">
                </colgroup>
                <thead>
                    <tr>
                        <th></th>
                        <th>Cordinacion</th>
                        <th>Empleado</th>
                        <th>Descripcion del documento</th>
                        <th>Copias</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${copyList}" var="copy">
                        <tr>
                            <td>
                                <g:link action="summaryDetail" id="${copy.id}">
                                    <i class="fa fa-plus"></i>
                                </g:link>
                            </td>
                            <td>${copy.coordination.name}</td>
                            <td>${copy.employee.fullName}</td>
                            <td>${copy.documentDescription}</td>
                            <td>${copy.copies}</td>
                        </tr>
                    </g:each>
                    <tr>
                        <td colspan="4"></td>
                        <td colspan="1">${copyList.copies.sum()}</td>
                    </tr>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin copies que mostrar</p>
        </g:else>
    </content>

    <content tag="right-column">
        <section>
            <g:render template="filterWidget" model="[coordinationList: copyFilter.coordinationList, employeeList: copyFilter.employeeList, authorizedByList: copyFilter.authorizedByList, canceledByList: copyFilter.canceledByList, copyStatusList: copyFilter.copyStatusList]"/>
        </section>
    </content>
</g:applyLayout>
