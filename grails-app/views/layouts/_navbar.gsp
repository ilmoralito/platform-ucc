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
                            ${session?.activityList?.size()} solicitudes <ucc:requestName/>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <g:if test="${session?.activityList}">
                                <li class="dropdown-header">Actividades</li>
                                <g:each in="${session.activityList}" var="activity">
                                    <li>
                                        <g:link controller="activity" action="show" id="${activity.id}">
                                            <g:fieldValue bean="${activity}" field="notificationMessage" />
                                        </g:link>
                                    </li>
                                </g:each>
                            </g:if>
                            <!-- Example about new features to come -->
                            <sec:ifAnyGranted roles="ROLE_ADMINISTRATIVE_SUPERVISOR">
                                <li class="dropdown-header">Mercadeo</li>
                                <li><a href="#" id="">lorem</a></li>
                                <li><a href="#" id="">lorem ipsum</a></li>
                                <li class="dropdown-header">Copias</li>
                                <li><a href="#" id="">Lorem ipsum dolor sit amet</a></li>
                            </sec:ifAnyGranted>
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
                        <li class="${actionName == 'profile' ? 'active' : ''}">
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