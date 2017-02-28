<div class="form-group">
    <label for="documentDescription">Descripcion del documento</label>
    <g:textField name="documentDescription" value="${copy?.documentDescription}" class="form-control"/>
</div>

<div class="form-group">
    <label for="copies">Copias</label>
    <g:field type="number" name="copies" value="${copy?.copies}" class="form-control" min="1"/>
</div>

<g:if test="${params.copyType == 'extraCopy'}">
    <div class="form-group">
        <label for="description" style="display: block;">Descripcion</label>
        <small>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempor incididunt ut labore et dolore magna aliqua.
        </small>
        <g:textArea name="description" value="${copy?.description}" class="form-control"/>
    </div>
</g:if>

