<g:applyLayout name="twoColumns">
    <head>
        <title>Vale</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <g:link action="show" id="${voucher.id}" class="btn btn-default pull-right">Regresar</g:link>
        </div>

        <div class="row">
            <div class="col-md-6">
                <g:form action="update" autocomplete="off">
                    <g:hiddenField name="type" value="${type}"/>
                    <g:hiddenField name="id" value="${voucher.id}"/>

                    <div class="form-group">
                        <label for="user">A nombre de</label>
                        <select name="user" class="form-control">
                            <g:each in="${users}" var="user">
                                <g:set var="selected" value="${user.id == voucher[type].id}"/>

                                <option value="${user.id}" ${selected ? 'selected=selected' : ''}>
                                    ${type == 'user' ? user['username'] : user['fullName']}
                                </option>
                            </g:each>
                        </select>
                    </div>

                    <ucc:activityDatalist activities="${activities}" activity="${voucher.activity}"/>

                    <div class="form-group">
                        <label for="voucherDate">Fecha</label>
                        <g:textField name="voucherDate" value="${voucher?.date}" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label for="value">Valor</label>
                        <g:textField name="value" value="${voucher?.value}" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label style="margin-bottom: 0;">Medios</label>

                        <g:each in="${foods}" var="food">
                            <div class="checkbox">
                                <label>
                                    <g:checkBox name="foods" value="${food.english}" checked="${food.english in voucher.foods.name}"/>
                                    ${food.spanish}
                                </label>
                            </div>
                        </g:each>
                    </div>

                    <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
