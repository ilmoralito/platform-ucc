<g:applyLayout name="threeColumns">
    <head>
        <title>Empleados</title>
    </head>

    <content tag="main">
        <g:render template="/layouts/employeeTabs"/>

        <g:if test="${thirdPartyList}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 45%;">
                    <col span="1" style="width: 43%;">
                    <col span="1" style="width: 12%;">
                </colgroup>
                <tbody>
                    <g:each in="${thirdPartyList}" var="thirdParty">
                        <tr>
                            <td colspan="3">${thirdParty.name}</td>
                        </tr>
                        <g:if test="${thirdParty.thirdPartyEmployees}">
                            <g:each in="${thirdParty.thirdPartyEmployees}" var="${thirdPartyEmployee}">
                                <tr>
                                    <td>
                                        <g:link action="show" params="[thirdPartyId: thirdParty.id, thirdPartyEmployeeId: thirdPartyEmployee.id]">
                                            ${thirdPartyEmployee.fullName}
                                        </g:link>
                                    </td>
                                    <td>${thirdPartyEmployee.email}</td>
                                    <td>
                                        <ucc:enabled enabled="${thirdPartyEmployee.enabled}"/>
                                    </td>
                                </tr>
                            </g:each>
                            </g:if>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>

    <content tag="right-column">
        <section>
            <g:form action="save" autocomplete="off">
                <g:render template="form"/>

                <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
            </g:form>
        </section>
    </content>
</g:applyLayout>
