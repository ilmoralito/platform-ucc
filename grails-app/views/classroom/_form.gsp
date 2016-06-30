<div class="form-group">
    <label for="code">Codigo</label>
    <g:textField name="code" value="${params?.code}" class="form-control"/>
</div>

<div class="form-group">
    <label for="name">Nombre</label>
    <g:textField name="name" value="${params?.name}" class="form-control"/>
</div>

<div class="form-group">
    <label for="capacity">Capacidad</label>
    <g:textField name="capacity" value="${params?.capacity}" class="form-control"/>
</div>

<div class="checkbox">
    <label>
        <g:checkBox name="airConditioned" value="${params?.airConditioned}"/>
        Aire acondicionado
    </label>
</div>
