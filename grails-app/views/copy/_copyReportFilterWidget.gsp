<g:form action="report" autocomplete="off">
    <div class="form-group">
        <g:if test="${yearList.size() == 1}">
            <g:hiddenField name="year" value="${yearList[0]}"/>
        </g:if>
        <g:else>
            <label for="year" style="margin-bottom: 0">Año</label>
            <g:each in="${yearList}" var="year">
                <div class="radio">
                    <label>
                        <g:radio name="year" value="${year}" checked="${year == currentYear}"/>
                        ${year}
                    </label>
                </div>
            </g:each>
        </g:else>
    </div>
    <div class="form-group">
        <label for="months" style="margin-bottom: 0">Mes</label>
        <g:each in="${monthList}" var="month" status="index">
            <div class="radio">
                <label>
                    <g:radio name="monthInstance" value="${index}" checked="${index == currentMonth}"/>
                    ${month.name}
                </label>
            </div>
        </g:each>
    </div>

    <button type="submit" class="btn btn-primary btn-block">Filtrar</button>
</g:form>
