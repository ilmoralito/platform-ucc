<g:set var="activityList" value="${grailsApplication.config.ni.edu.uccleon.activityList}"/>
<g:set var="voucherList" value="${grailsApplication.config.ni.edu.uccleon.voucherList}"/>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                ${grailsApplication.config.ni.edu.uccleon.companyInformation.name}
            </a>
        </div>

        <ul class="nav navbar-nav navbar-right">
            <sec:ifLoggedIn>
                <sec:ifAnyGranted roles="ROLE_ADMINISTRATIVE_SUPERVISOR, ROLE_ACADEMIC_SUPERVISOR, ROLE_PROTOCOL_SUPERVISOR">
                    <li class="dropdown">
                        <a href="#"
                            class="dropdown-toggle"
                            data-toggle="dropdown"
                            role="button"
                            aria-haspopup="true"
                            aria-expanded="false">
                            <span class="glyphicon glyphicon-bell" aria-hidden="true" style="color: ${activityList || voucherList ? 'gold;' : ''}"></span>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <g:if test="${activityList}">
                                <li class="dropdown-header">Actividades</li>
                                <g:each in="${activityList}" var="activity">
                                    <li>
                                        <g:link controller="activity" action="show" id="${activity.id}">
                                            <g:fieldValue bean="${activity}" field="notificationMessage" />
                                        </g:link>
                                    </li>
                                </g:each>
                            </g:if>
                            <g:if test="${voucherList}">
                                <li class="dropdown-header">Vales</li>
                                <g:each in="${voucherList}" var="voucher">
                                    <li>
                                        <g:link controller="voucher" action="show" params="[date: voucher.date]">
                                            ${voucher.size} - ${voucher.date.format('yyyy-MM-dd')}
                                        </g:link>
                                    </li>
                                </g:each>
                            </g:if>
                            <sec:ifAnyGranted roles="ROLE_ADMINISTRATIVE_SUPERVISOR"></sec:ifAnyGranted>
                        </ul>
                    </li>
                </sec:ifAnyGranted>

                <li class="dropdown">
                    <a href="#"
                        class="dropdown-toggle"
                        data-toggle="dropdown"
                        role="button"
                        aria-haspopup="true"
                        aria-expanded="false">
                        <sec:loggedInUserInfo field="username"/> <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <sec:ifAllGranted roles='ROLE_ADMIN'>
                            <li class="${controllerName == 'user' && !(actionName in ['profile', 'password']) ? 'active' : ''}">
                                <g:link controller="user">
                                    Administrar usuarios
                                </g:link>
                            </li>
                            <li class="${controllerName == 'coordination' ? 'active' : ''}">
                                <g:link controller="coordination">
                                    Administrar coordinaciones
                                </g:link>
                            </li>
                        </sec:ifAllGranted>
                        <li class="${controllerName == 'user' && actionName == 'profile' ? 'active' : ''}">
                            <g:link controller="user" action="profile">Perfil</g:link>
                        </li>
                        <li role="separator" class="divider"></li>
                        <li><g:link controller="logout">Salir</g:link></li>
                    </ul>
                </li>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
                <li>
                    <g:link controller="login" action="auth">Iniciar sesion</g:link>
                </li>
            </sec:ifNotLoggedIn>
        </ul>
    </div>
</nav>