<div class="form-group">
    <label for="code">Codigo</label>
    <g:textField name="code" value="${classroom?.code}" class="form-control"/>
</div>

<div class="form-group">
    <label for="name">Nombre</label>
    <g:textField name="name" value="${classroom?.name}" class="form-control"/>
</div>

<div class="form-group">
    <label for="capacity">Capacidad</label>
    <g:textField name="capacity" value="${classroom?.capacity}" class="form-control"/>
</div>

<div class="checkbox">
    <label>
        <g:checkBox name="airConditioned" value="${classroom?.airConditioned}"/>
        Aire acondicionado
    </label>
</div>
