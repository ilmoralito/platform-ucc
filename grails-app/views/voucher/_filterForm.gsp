<g:if test="${members}">
    <div class="well well-sm">
        <g:form action="filter" autocomplete="off">
            <g:hiddenField name="approvalDate" value="${params.approvalDate}"/>
            <g:hiddenField name="type" value="${type}"/>

            <g:each in="${members}" var="instance">
                <div class="radio">
                    <label>
                        <g:radio name="id" value="${instance.id}" checked="${instance.id == params.int('id')}"/>
                        ${instance[property]}
                    </label>
                </div>
            </g:each>
            <button type="submit" class="btn btn-primary btn-block">Filtrar ${label.toLowerCase()}</button>
        </g:form>
    </div>
</g:if>
