<g:applyLayout name="twoColumns">
    <head>
        <title>Resumen por coordinacion y año y mes</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${summary}">
            <table class="table table-hover">
                <caption>${summary.metadata.coordination} ${summary.metadata.year} ${summary.metadata.month}</caption>
                <colgroup>
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 45%;">
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 10%;">
                </colgroup>

                <thead>
                    <tr>
                        <th>Solicitado por</th>
                        <th>Descripcion del documento</th>
                        <th>Copias</th>
                        <th>Fecha de solicitud</th>
                        <th>Estado</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${summary.summary}" var="s" status="index">
                        <tr>
                            <td rowspan="${s.copyList.size() + 1}" style="vertical-align: middle;">${s.requestedBy}</td>
                            <g:each in="${s.copyList}" var="copy">
                                <tr>
                                    <td>${copy.documentDescription}</td>
                                    <td>${copy.copies}</td>
                                    <td>${copy.dateCreated}</td>
                                    <td>
                                        <ucc:copyStatus status="${copy.status}"/>
                                    </td>
                                </tr>
                            </g:each>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
