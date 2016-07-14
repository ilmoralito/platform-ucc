<g:applyLayout name="twoColumns">
    <head>
        <title>Vale</title>
    </head>

    <content tag="main">
        <g:link action="show" params="[date: voucher.date]" class="btn-back">Regresar</g:link>

        <div class="clearfix">
            <div class="btn-group pull-right">
                <sec:ifAllGranted roles="ROLE_PROTOCOL_SUPERVISOR">
                    <g:link action="print" id="${voucher.id}" class="btn btn-default">
                        <i class="fa fa-print" aria-hidden="true"></i>
                    </g:link>
                </sec:ifAllGranted>
                <g:link action="delete" params="[id: voucher.id]" class="btn btn-default">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                </g:link>
            </div>
        </div>
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

                    <button type="submit" class="btn btn-primary">Confirmar</button>
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
    </content>
</g:applyLayout>
