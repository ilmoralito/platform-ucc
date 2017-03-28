<g:form action="create" autocomplete="off">
    <div class="form-group">
        <label for="copyType">Tipo de copia</label>
        <g:select name="copyType" from="${copyTypeList}" optionKey="key" optionValue="value" class="form-control"/>
    </div>

    <g:if test="${coordinationList.size() > 1}">
        <div class="form-group">
            <label for="coordination">Coordinacion</label>
            <g:select name="coordination" from="${coordinationList}" optionKey="id" optionValue="name" class="form-control"/>
        </div>
    </g:if>
    <g:else>
        <g:hiddenField name="coordination" value="${coordinationList[0].id}"/>
    </g:else>

    <div class="form-group">
        <label for="documentDescription">Descripcion del documento</label>
        <g:field name="documentDescription" list="documentDescriptionList" class="form-control"/>
        <datalist id="documentDescriptionList">
            <g:each in="${documentDescriptionList}" var="documentDescription">
                <option value="${documentDescription}"></option>
            </g:each>
        </datalist>
    </div>

    <div class="form-group">
        <label for="copies">Copias</label>
        <g:field type="number" name="copies" min="1" class="form-control"/>
    </div>

    <div class="form-group hide">
        <label for="description">Descripcion</label>
        <g:textArea name="description" class="form-control"/>
    </div>

    <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
</g:form>
