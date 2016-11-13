<div class="well well-sm">
    <g:form action="create" method="GET" autocomplete="off">
        <div class="form-group">
            <label for="date">Fecha</label>
            <g:textField name="date" id="voucherDate" value="${params?.date}" class="form-control"/>
        </div>

        <ucc:activityDatalist activities="${activities}" activity="${params?.activity}"/>

        <label style="margin-bottom: 0;">Tipo de vale</label>
        <div class="radio">
            <label>
                <g:radio name="type" value="user"/>
                Interno
            </label>
        </div>
        <div class="radio">
            <label>
                <g:radio name="type" value="guest"/>
                Visita
            </label>
        </div>

        <g:submitButton name="send" value="Confirmar" class="btn btn-primary btn-block"/>
    </g:form>
</div>
