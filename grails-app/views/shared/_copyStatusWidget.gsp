<table class="table table-hover table-bordered">
    <tbody>
        <g:each in="${copyStatusList}" var="status">
            <tr>
                <td colspan="2">${status.name}</td>
            </tr>
            <tr>
                <td>Cuota</td>
                <td>${status.quota}</td>
            </tr>
            <tr>
                <td>Copias a la fecha</td>
                <td>${status.copiesToDate}</td>
            </tr>
            <tr>
                <td>Balance</td>
                <td>${status.balanceToDate}</td>
            </tr>
        </g:each>
    </tbody>
</table>
