<g:applyLayout name="oneColumn">
    <head>
        <title>Cumpleañeros</title>
    </head>

    <content tag="main">
        <g:render template="/layouts/nav"/>

        <g:render template="/shared/birthdays" model="[birthdaysMonth: birthdaysMonth]"/>
    </content>
</g:applyLayout>
