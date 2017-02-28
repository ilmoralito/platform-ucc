<g:applyLayout name="twoColumns">
    <head>
        <title>Panel de control</title>
    </head>

    <content tag="main">
        <div class="row">
            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Cuota de impresiones
                    </div>
                    <g:render template="/shared/copyStatusWidget" model="[copyStatus: copyStatus]"/>
                </div>
            </div>

            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Feriados
                    </div>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Mes</th>
                                <th>Dia</th>
                                <th>Feriado</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td rowspan="2" style="vertical-align: middle;">
                                    Diciembre
                                </td>
                                <td>8</td>
                                <td>Día de Concepción</td>
                            </tr>
                            <tr>
                                <td>25</td>
                                <td>Navidad</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Cumpleañeros del mes
                    </div>
                    <g:render template="/shared/birthdays" model="[birthdaysMonth: birthdaysMonth]"/>
                </div>
            </div>
        </div>
    </content>
</g:applyLayout>
