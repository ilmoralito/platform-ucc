<g:applyLayout name="threeColumns">
    <head>
        <title>Resumen</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${copies}">

        </g:if>
        <g:else>
            <p>Sin copies que mostrar</p>
        </g:else>
    </content>

    <content tag="right-column">
        ${years}
        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
    </content>
</g:applyLayout>
