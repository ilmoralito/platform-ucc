<table class="table table-hover">
    <g:if test="${params.status == 'pending' || actionName == 'approved' || actionName == 'vouchersToApprove'}">
        <thead>
            <tr>
                <th colspan="2">
                    <input type="checkbox" id="trigger">
                </th>
            </tr>
        </thead>
    </g:if>
    <tbody>
        <g:each in="${vouchers}" var="voucher">
            <tr>
                <td colspan="2">
                    <g:formatDate date="${voucher.date}" format="yyyy-MM-dd"/>
                </td>
            </tr>
            <g:each in="${voucher.activities}" var="activity">
                <tr>
                    <td colspan="2">${activity.name}</td>
                </tr>
                <g:each in="${activity.vouchers}" var="instance">
                    <tr>
                        <!--Show only vouchers whit pending and approved status-->
                        <g:if test="${!params?.status || params?.status in ['pending', 'approved']}">
                            <td width="1">
                                <input type="checkbox" name="vouchers" form="${form}" value="${instance.id}">
                            </td>
                        </g:if>
                        <td>
                            <g:link action="show" id="${instance.id}">
                                <g:formatBoolean boolean="${instance.user == null}" true="${instance?.guest?.fullName}" false="${instance?.user?.username}"/>
                            </g:link>
                        </td>
                    </tr>
                </g:each>
            </g:each>
        </g:each>
    </tbody>
</table>
