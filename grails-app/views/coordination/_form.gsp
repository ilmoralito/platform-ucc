<div class="form-group">
    <label for="name">Nombre de la coordinacion</label>
    <g:textField name="name" value="${params?.name}" class="form-control"/>
</div>

<div class="form-group">
    <label for="extensionNumber">Numero de extension</label>
    <g:textField name="extensionNumber" value="${params?.extensionNumber}" class="form-control"/>
</div>

<div class="form-group">
    <label for="location">Area</label>
    <g:select name="location" from="['Administrative', 'Academic']" class="form-control"/>
</div>

<ucc:getColors colorList="${colorList}" colorParamList="${params.colors}"/>