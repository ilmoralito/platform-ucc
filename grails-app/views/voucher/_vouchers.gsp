<table class="table table-hover">
    <colgroup>
        <col span="1" style="width: 25%;">
        <col span="1" style="width: 70%;">
        <col span="1" style="width: 5%;">
    </colgroup>
    <tbody>
        <g:each in="${vouchers}" var="voucher">
            <tr>
                <td colspan="3">
                    <g:formatDate date="${voucher.date}" format="yyyy-MM-dd"/>
                </td>
            </tr>
            <g:each in="${voucher.activities}" var="activity">
                <tr>
                    <td colspan="3">
                        ${activity.name}
                    </td>
                </tr>
                <g:each in="${activity.vouchers}" var="instance">
                    <tr>
                        <td>
                            <g:link action="show" id="${instance.id}">
                                <g:formatBoolean boolean="${instance.user == null}" true="${instance?.guest?.fullName}" false="${instance?.user?.username}"/>
                            </g:link>
                        </td>
                        <td>
                            <ucc:foodInSpanish foods="${instance.foods.name}"/>
                        </td>
                        <td>
                            <g:fieldValue bean="${instance}" field="value"/>
                        </td>
                    </tr>
                </g:each>
            </g:each>
        </g:each>
        <tr>
            <td colspan="2"></td>
            <td>
                <b>${vouchers.activities.vouchers.value.flatten().sum()}</b>
            </td>
        </tr>
    </tbody>
</table>
