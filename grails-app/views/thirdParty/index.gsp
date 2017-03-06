<g:applyLayout name="threeColumns">
    <head>
        <title>Lista de terceros</title>
    </head>

    <content tag="main">
        <g:if test="${thirdPartyList}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 88%;">
                    <col span="1" style="width: 12%;">
                </colgroup>
                <thead>
                    <tr>
                        <th colspan="2">Lista de terceros</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${thirdPartyList}" var="${thirdParty}">
                        <tr>
                            <td>
                                <g:link action="show" id="${thirdParty.id}">
                                    ${thirdParty.name}
                                </g:link>
                            </td>
                            <td>
                                <ucc:enabled enabled="${thirdParty.enabled}"/>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin terceros que mostrar</p>
        </g:else>
    </content>

    <content tag="right-column">
        <section>
            <g:form action="save" autocomplete="off">
                <g:render template="form"/>

                <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
            </g:form>
        </section>

        <%--
        <br>

        <section>
            <g:form action="createThirdPartyEmployee" autocomplete="off">
                <g:if test="${thirdPartyList.size() == 1}">
                    <g:hiddenField name="thirdPartyId" value="${thirdPartyList[0].id}"/>
                </g:if>
                <g:else>
                    <div class="form-group">
                        <label for="thirdPartyId">Tercero</label>
                        <g:select name="thirdPartyId" from="${thirdPartyList}" optionKey="id" optionValue="name" class="form-control"/>
                    </div>
                </g:else>

                <div class="form-group">
                    <label for="fullName">Nombre completo</label>
                    <g:textField name="fullName" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="email">Correo electronico</label>
                    <g:textField name="email" class="form-control"/>
                </div>

                <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
            </g:form>
            --%>
        </section>
    </content>
</g:applyLayout>
