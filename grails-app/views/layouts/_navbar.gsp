<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Brand</a>
        </div>

        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                    <sec:loggedInUserInfo field="username"/> <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><g:link controller="user" action="profile">Perfil</g:link></li>
                    <li role="separator" class="divider"></li>
                    <li><g:link controller="logout">Salir</g:link></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>