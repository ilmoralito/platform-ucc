<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <form action="${postUrl ?: '/login/authenticate'}" method="POST" autocomplete="off">
        <div class="form-group">
            <input
                type="text"
                name="${usernameParameter ?: 'username'}"
                id="username"
                class="form-control"
                placeholder="correo institucional"/>
        </div>
        <div class="form-group">
            <input
                type="password"
                name="${passwordParameter ?: 'password'}"
                class="form-control"
                id="password"
                placeholder="clave de paso"/>
        </div>

        <input
            type="submit"
            id="submit"
            value="${message(code: "springSecurity.login.button")}"
            class="btn btn-primary btn-block"
            placeholder="Clave de paso"/>
    </form>
</body>
</html>