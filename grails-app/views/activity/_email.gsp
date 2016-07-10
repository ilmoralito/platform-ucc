<%@ page contentType="text/html" %>

<p>Nombre de la actividad: ${name}</p>
<p>Coordinacion: ${coordination}</p>
<g:if test="${client}">
    <p>Cliente: ${client}</p>
</g:if>
<p><a href="${host}">Mas detalles</a></p>
