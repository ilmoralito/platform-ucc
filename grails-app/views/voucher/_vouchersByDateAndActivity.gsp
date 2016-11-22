<table class="table table-hover">
    <colgroup>
        <col span="1" style="width: 5%;">
        <col span="1" style="width: 25%;">
        <col span="1" style="width: 65%;">
        <col span="1" style="width: 5%;">
    </colgroup>
    <thead>
        <tr>
            <th class="text-center">
                <input type="checkbox" id="trigger">
            </th>
            <th colspan="3"></th>
        </tr>
    </thead>
    <tbody>
        <g:each in="${vouchers}" var="voucher">
            <tr>
                <td colspan="4">
                    <g:formatDate date="${voucher.date}" format="yyyy-MM-dd"/>
                </td>
            </tr>
            <g:each in="${voucher.activities}" var="activity">
                <tr>
                    <td colspan="4">
                        ${activity.name}
                    </td>
                </tr>
                <g:each in="${activity.vouchers}" var="instance">
                    <tr>
                        <td width="1" class="text-center">
                            <input type="checkbox" name="vouchers" form="notify" value="${instance.id}">
                        </td>
                        <td style="vertical-align: middle;">
                            <g:link action="show" id="${instance.id}">
                                <g:formatBoolean boolean="${instance.user == null}" true="${instance?.guest?.fullName}" false="${instance?.user?.username}"/>
                            </g:link>
                        </td>
                        <td style="vertical-align: middle;">
                            <ucc:foodInSpanish foods="${instance.foods.name}"/>
                        </td>
                        <td style="vertical-align: middle;">
                            <g:fieldValue bean="${instance}" field="value"/>
                        </td>
                    </tr>
                </g:each>
            </g:each>
        </g:each>
        <tr>
            <td colspan="3"></td>
            <td>
                <b>${vouchers.activities.vouchers.value.flatten().sum()}</b>
            </td>
        </tr>
    </tbody>
</table>
