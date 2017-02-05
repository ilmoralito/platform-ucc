<table class="table table-hover">
    <colgroup>
        <col span="1" style="width: 40%;">
        <col span="1" style="width: 55%;">
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
                                ${instance.member}
                            </g:link>
                        </td>
                        <td>
                            <ucc:foodInSpanish foods="${instance.foods}"/>
                        </td>
                        <td>
                            ${instance.value}
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
