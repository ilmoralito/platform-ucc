<g:set var="activityList" value="${grailsApplication.config.ni.edu.uccleon.activityList}"/>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <g:link class="navbar-brand" controller="birthday">
                UCCLEON
            </g:link>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
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
                                <g:if test="${activityList}">
                                    <i class="fa fa-bell" aria-hidden="true"></i>
                                </g:if>
                                <g:else>
                                    <i class="fa fa-bell-o" aria-hidden="true"></i>
                                </g:else>

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
                            <li class="${controllerName == 'user' && actionName == 'profile' ? 'active' : ''}">
                                <g:link controller="user" action="profile">Perfil</g:link>
                            </li>
                            <li role="separator" class="divider"></li>
                            <li><g:link controller="logout">Salir</g:link></li>
                        </ul>
                    </li>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <g:if test="${controllerName != 'login'}">
                        <li>
                            <g:link controller="login" action="auth">Iniciar sesion</g:link>
                        </li>
                    </g:if>
                </sec:ifNotLoggedIn>
            </ul>
        </div>
    </div>
</nav>