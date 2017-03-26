<g:form action="status" autocomplete="off">
    <g:if test="${coordinationList}">
        <p>Coordinaciones</p>
        <g:each in="${coordinationList}" var="coordination">
            <div class="checkbox">
                <label>
                    <g:checkBox name="coordinationList" value="${coordination.id}" checked="${coordination.id in params.list('coordinationList')*.toInteger()}"/>
                    ${coordination.name}
                </label>
            </div>
        </g:each>
    </g:if>

    <g:if test="${employeeList}">
        <p>Solicitados por</p>
        <g:each in="${employeeList}" var="employee">
            <div class="checkbox">
                <label>
                    <g:checkBox name="employeeList" value="${employee.id}" checked="${employee.id in params.list('employeeList')*.toInteger()}"/>
                    ${employee.fullName}
                </label>
            </div>
        </g:each>
    </g:if>

    <sec:ifAnyGranted roles="ROLE_ADMINISTRATIVE_SUPERVISOR">
        <p>Estados</p>
        <g:each in="${copyStatusList}" var="status">
            <div class="checkbox">
                <label>
                    <g:checkBox name="copyStatusList" value="${status.key}" checked="${status.key in copyStatusList}"/>
                    ${status.value}
                </label>
            </div>
        </g:each>

        <g:if test="${authorizedByList}">
            <p>Autorizados por</p>
            <g:each in="${authorizedByList}" var="authorizedBy">
                <div class="checkbox">
                    <label>
                        <g:checkBox name="authorizedByList" value="${authorizedBy.id}" checked="${authorizedBy.id in authorizedByList}"/>
                        ${authorizedBy.fullName}
                    </label>
                </div>
            </g:each>
        </g:if>

        <g:if test="${canceledByList}">
            <p>Cancelado por</p>
            <g:each in="${canceledByList}" var="canceledBy">
                <div class="checkbox">
                    <label>
                        <g:checkBox name="canceledByList" value="${canceledBy.id}" checked="${canceledBy.id in canceledByList}"/>
                        ${canceledBy.fullName}
                    </label>
                </div>
            </g:each>
        </g:if>
    </sec:ifAnyGranted>

    <button type="submit" class="btn btn-primary btn-block">Filtrar</button>
</g:form>
