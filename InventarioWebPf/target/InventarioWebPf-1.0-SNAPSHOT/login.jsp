<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Capturamos si el login falló desde el parámetro de la URL
    String error = request.getParameter("error");
    String mensaje = null;

    if (error != null && error.equals("true")) {
        mensaje = "Usuario o contraseña incorrectos. Intente nuevamente.";
    }

    // Si el usuario ya está logueado, se redirige al inicio
    if (session.getAttribute("usuario") != null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login - Sistema</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- FontAwesome -->
    <link href="<%=request.getContextPath()%>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">

    <!-- SB Admin 2 CSS -->
    <link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet">

   
</head>
<body>
    
<body class="bg-gradient-info"> 
    
<div class="container">

    <!-- Fila centrada -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-10 col-md-9">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">

                    <!-- Contenido del login dividido en dos columnas -->
                    <div class="row">
                        <!-- Columna con imagen -->
                         <div class="col-lg-5 d-none d-lg-block bg-login-image"
                            style="background: url('<%=request.getContextPath()%>/img/imfgn.png') no-repeat center center;
                            background-size: cover;">
                         </div>

                        <!-- Columna con formulario -->
                         <div class="col-lg-6 d-flex align-items-center">
                                <div class="p-5 w-100">

                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Iniciar Sesión</h1>
                                </div>

                                <% if (request.getParameter("error") != null) { %>
                                    <div class="alert alert-danger text-center">
                                        Usuario o contraseña incorrectos
                                    </div>
                                <% } %>

                                <form class="user" action="controlLogin" method="post" onsubmit="return validarFormulario();">
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user"
                                               id="usuario" name="usuario"
                                               placeholder="Ingrese su usuario">
                                    </div>
                                    
                                    
                                    <div class="form-group">
                                        <input type="password" class="form-control form-control-user"
                                               id="password" name="password"
                                               placeholder="Ingrese su contraseña">
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-user btn-block">
                                        Ingresar
                                    </button>
                                    <hr>

                                </form>
                                <hr>
                              
                               
                                <div class="text-center">
                                    <small>© <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()) %> - Sistema de Gestión</small>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Fin row -->
                </div>
            </div>
        </div>

    </div>

</div>

<!-- Bootstrap core JavaScript-->
<script src="<%=request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="<%=request.getContextPath()%>/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- SB Admin 2 JavaScript-->
<script src="<%=request.getContextPath()%>/js/sb-admin-2.min.js"></script>

<script>
    function validarFormulario() {
        const usuario = document.getElementById("usuario").value.trim();
        const password = document.getElementById("password").value.trim();
        if (usuario === "" || password === "") {
            alert("Por favor, complete todos los campos.");
            return false;
        }
        return true;
    }
</script>

</body>
</html>

