<g:applyLayout name="oneColumn">
    <head>
        <title>Cumpleañeros</title>
    </head>

    <content tag="main">
        <g:render template="/layouts/nav"/>

        <g:if test="${birthdaysMonth}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 90%;">
                </colgroup>
                <thead>
                    <th>Dia</th>
                    <th>Cumpleañeros</th>
                </thead>
                <tbody>
                    <g:each in="${birthdaysMonth}" var="b">
                        <tr class="${b.day.toInteger() == today ? 'active' : ''}">
                            <td>${b.day}</td>
                            <td>${b.birthdays}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>No hay cumpleañeros este mes... T_T</p>
        </g:else>
    </content>
</g:applyLayout>
