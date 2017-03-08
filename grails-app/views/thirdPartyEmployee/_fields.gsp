<table class="table table-hover">
    <colgroup>
        <col span="1" style="width: 25%;">
        <col span="1" style="width: 75%;">
    </colgroup>
    <tbody>
        <tr>
            <td style="border-top: 0;">Nombre completo</td>
            <td style="border-top: 0;">${thirdPartyEmployee.fullName}</td>
        </tr>
        <tr>
            <td>Email</td>
            <td>${thirdPartyEmployee.email}</td>
        </tr>
        <tr>
            <td>Estado</td>
            <td>
                <ucc:enabled enabled="${thirdPartyEmployee.enabled}"/>
            </td>
        </tr>
    </tbody>
</table>
