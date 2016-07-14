<g:applyLayout name="threeColumns">
    <head>
        <title>Vales</title>
    </head>

    <content tag="main">
        <g:if test="${data}">
            <table class="table table-hover">
                <caption>${data*.size.sum()} vales no aprobados</caption>
                <colgroup>
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 70%;">
                </colgroup>
                <thead>
                    <th>Fecha</th>
                    <th>Vales</th>
                    <th>Estado</th>
                </thead>
                <tbody>
                    <g:each in="${data}" var="d">
                        <tr>
                            <td>
                                <g:link action="show" params="[date: d.date.format('yyyy-MM-dd')]">
                                    <g:formatDate date="${d.date}" format="yyyy-MM-dd"/>
                                </g:link>
                            </td>
                            <td>${d.size}</td>
                            <td><ucc:voucherStatus status="${d.status.unique()[0]}"/></td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>No hay vales que mostrar</p>
        </g:else>
    </content>

    <content tag="right-column">
        <ul class="nav nav-tabs">
            <li role="presentation" class="${!params.tab || params.tab != 'guest' ? 'active' : ''}">
                <g:link action="index">Interno</g:link>
            </li>
            <li role="presentation" class="${params.tab == 'guest' ? 'active' : ''}">
                <g:link action="index" params="[tab: 'guest']">Visita</g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || params.tab != 'guest'}">
            <g:form action="create" params="[type: 'interval']" autocomplete="off">
                <div class="form-group">
                    <label for="voucherDate">Fecha</label>
                    <g:field id="voucherDate" name="date" value="${params?.date}" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="employees">Empleados</label>
                    <ucc:getEmployees multiple="multiple" name="employees"/>
                </div>

                <div class="form-group">
                    <label for="activity">Actividad</label>
                    <g:textArea name="activity" value="${params?.activity}" rows="1" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="value">Valor</label>
                    <g:textField name="value" value="${params?.value}" class="form-control"/>
                </div>

                <label for="foods">Alimentos</label>
                <g:each in="${foods}" var="food">
                    <div class="checkbox">
                        <label>
                            <g:checkBox name="${food.english}" value="${params[food.english]}" checked=""/>
                            ${food.spanish}
                        </label>
                    </div>
                </g:each>

                <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
            </g:form>
        </g:if>
        <g:else>
            <%--only show this if there are activities--%>
            <g:form action="create" params="[type: 'guest']" autocomplete="off">
                <ucc:getGuests/>
            </g:form>
        </g:else>
    </content>
</g:applyLayout>
