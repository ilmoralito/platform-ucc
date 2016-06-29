<g:applyLayout name="twoColumns">
    <head>
        <title>Vale</title>
    </head>

    <content tag="main">
        <g:link action="show" params="[date: voucher.date]" class="btn-back">Regresar</g:link>

        <div class="panel panel-default" style="margin-top: 10px;">
            <div class="panel-heading">
                <div class="pull-left" style="margin-top: 4px;">
                    <ucc:voucherStatus status="${voucher.status}"/>
                </div>
                <div class="btn-group btn-group-sm pull-right" role="group" aria-label="...">
                    <sec:ifAllGranted roles="ROLE_PROTOCOL_SUPERVISOR">
                        <g:link action="printIt" id="${voucher.id}" class="btn btn-default">
                            <span class="glyphicon glyphicon-print" aria-hidden="true"></span>
                        </g:link>
                    </sec:ifAllGranted>
                    <g:link action="delete" params="[id: voucher.id, date: params.date]" class="btn btn-default">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </g:link>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="panel-body">
                <g:form action="update" autocomplete="off">
                    <g:hiddenField name="id" value="${voucher.id}"/>

                   <div class="row">
                       <div class="col-md-9">
                           <div class="form-group">
                                <label for="employee">Empleado</label>
                                <ucc:getEmployees currentEmployee="${voucher.employee}"/>
                            </div>

                            <div class="form-group">
                                <label for="value">Valor</label>
                                <g:textField name="value" value="${voucher.value}" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label for="activity">Actividad</label>
                                <g:textArea name="activity" value="${voucher?.activity}" rows="1" class="form-control"/>
                            </div>

                            <button type="submit" class="btn btn-primary btn-sm">Actualizar</button>
                       </div>

                        <div class="col-md-3">
                            <g:each in="${foods}" var="food">
                                <div class="checkbox">
                                    <label>
                                        <g:checkBox
                                            name="${food.english}"
                                            value="${voucher[food.english]}"
                                            checked="${voucher[food.english]}"/>
                                        ${food.spanish}
                                    </label>
                                </div>
                            </g:each>
                        </div>
                   </div>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
