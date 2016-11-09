<g:if test="${birthdaysMonth}">
    <table class="table table-hover">
        <colgroup>
            <col span="1" style="width: 5%;">
            <col span="1" style="width: 95%;">
        </colgroup>
        <thead>
            <th class="text-center">Dia</th>
            <th>Cumpleañeros</th>
        </thead>
        <tbody>
            <g:each in="${birthdaysMonth}" var="b">
                <tr class="${b.day.toInteger() == today ? 'active' : ''}">
                    <td class="text-center">
                        ${b.day}
                    </td>
                    <td>${b.birthdays}</td>
                </tr>
            </g:each>
        </tbody>
    </table>
</g:if>
<g:else>
    <g:if test="${controllerName == 'panel'}">
        <div class="panel-body">
            No hay cumpleañeros este mes
        </div>
    </g:if>
    <g:else>
        <p>No hay cumpleañeros este mes</p>
    </g:else>
</g:else>
