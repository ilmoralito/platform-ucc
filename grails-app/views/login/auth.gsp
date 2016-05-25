<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <form action="${postUrl ?: '/login/authenticate'}" method="POST" autocomplete="off">
        <div class="form-group">
            <label for="username">Correo institucional</label>
            <input
                type="email"
                name="${usernameParameter ?: 'username'}"
                id="username"
                class="form-control input-lg"/>
        </div>
        <div class="form-group">
            <label for="password">Clave de paso</label>
            <input
                type="password"
                name="${passwordParameter ?: 'password'}"
                class="form-control input-lg"
                id="password"/>
        </div>

        <input
            type="submit"
            id="submit"
            value="${message(code: "springSecurity.login.button")}"
            class="btn btn-primary btn-lg btn-block"
            placeholder="Clave de paso"/>
    </form>
</body>
</html>