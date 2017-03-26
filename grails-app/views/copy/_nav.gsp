<ul class="nav nav-tabs">
    <sec:ifAnyGranted roles="ROLE_COPY_MANAGER, ROLE_COPY_ASSISTANT">
        <li role="presentation" class="${actionName in ['status', 'detail'] ? 'active' : ''}">
            <g:link action="status">
                Estado
            </g:link>
        </li>
        <li role="presentation" class="${actionName in ['report', 'reportCopyListOutOfRange'] ? 'active' : ''}">
            <g:link action="report">
                Reporte
            </g:link>
        </li>
    </sec:ifAnyGranted>

    <sec:ifAllGranted roles="ROLE_ADMINISTRATIVE_SUPERVISOR">
        <li role="presentation" class="${actionName == 'index' ? 'active' : ''}">
            <g:link action="index">
                Estado
            </g:link>
        </li>
        <li role="presentation" class="${actionName == 'requestingAuthorization' || actionName == 'requestAuthorizationDetail' ? 'active' : ''}">
            <g:link action="requestingAuthorization">
                Requiere autorizacion
            </g:link>
        </li>
        <li role="presentation" class="${actionName in ['report', 'reportCopyListOutOfRange'] ? 'active' : ''}">
            <g:link action="report">
                Reporte
            </g:link>
        </li>
    </sec:ifAllGranted>

    <sec:ifAnyGranted roles="ROLE_USER, ROLE_PROTOCOL_SUPERVISOR, ROLE_ACADEMIC_SUPERVISOR, ROLE_ASSISTANT_ADMINISTRATIVE_SUPERVISOR, 'ROLE_ACADEMIC_SUPERVISOR">
        <li role="presentation" class="${actionName == 'index' ? 'active' : ''}">
            <g:link action="index">
                Estado
            </g:link>
        </li>
    </sec:ifAnyGranted>
</ul>
