<g:applyLayout name="threeColumns">
    <head>
        <title>Lista de copias</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${copyList}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 1%;">
                    <col span="1" style="width: 12%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 18%;">
                    <col span="1" style="width: 44%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <th></th>
                    <th>Fecha</th>
                    <th>Coordinacion</th>
                    <th>Empleado</th>
                    <th>Nombre documento</th>
                    <th>Copias</th>
                </thead>
                <tbody>
                    <g:each in="${copyList}" var="copy">
                        <tr class="${copy.type == 'extraCopy' ? 'warning' : ''}">
                            <td class="vertical-align text-center">
                                <g:link action="detail" id="${copy.id}">
                                    <li class="fa fa-folder-o"></li>
                                </g:link>
                            </td>
                            <td class="vertical-align">${copy.dateCreated}</td>
                            <td class="vertical-align">${copy.coordination.name}</td>
                            <td class="vertical-align">${copy.employee.fullName}</td>
                            <td class="vertical-align">${copy.documentDescription}</td>
                            <td class="vertical-align text-center">${copy.copies}</td>
                        </tr>
                    </g:each>
                    <tr>
                        <td colspan="5">TOTAL</td>
                        <td class="vertical-align text-center">${copyList.copies.sum()}</td>
                    </tr>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin ordenes pendientes que atender</p>
        </g:else>
    </content>

    <content tag="right-column">
        <section>
            <g:render template="filterWidget" model="[coordinationList: copyFilter.coordinationList, employeeList: copyFilter.employeeList]"/>
        </section>
    </content>
</g:applyLayout>
