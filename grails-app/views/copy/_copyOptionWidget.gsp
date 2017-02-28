<g:form action="create" autocomplete="off">
    <g:if test="${coordinationList.size() > 1}">
        <label>Coordinaciones</label>
        <g:each in="${coordinationList}" var="coordination" status="index">
            <div class="radio">
                <label>
                    <g:radio name="coordination" value="${coordination.id}"/>
                    ${coordination.name}
                </label>
            </div>
        </g:each>
    </g:if>
    <g:else>
        <g:hiddenField name="coordination" value="${coordinationList[0].id}"/>
    </g:else>

    <!-- Show this if balance is positive -->
    <label>Tipo de copia</label>
    <div class="radio">
        <label>
            <g:radio name="copyType" value="copy"/>
            General
        </label>
    </div>
    <!-- Show this if balance is positive -->

    <div class="radio">
        <label>
            <g:radio name="copyType" value="extraCopy"/>
            Copia extra
        </label>
    </div>

    <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
</g:form>

