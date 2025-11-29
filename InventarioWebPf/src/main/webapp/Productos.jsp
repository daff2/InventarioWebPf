 <%@page import="Modelo.Producto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sistema de inventario Neo-Tech</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    
    <link href="css/estilos.css" rel="stylesheet">
 
    
      <style>
    .card-img-top {
      display: block;
      margin: 0 auto;
      width: auto;
      max-width: 100%;
      height: 200px;
      object-fit: contain;
      background-color: #f8f9fc;
      padding: 10px;
    }
  </style>

  
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-info sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-solid fa-laptop-code"></i>
                </div>
                <div class="sidebar-brand-text mx-3">NeoTech</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="index.jsp">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>

          
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    
                    <i class="fas fa-solid fa-shop"></i>
                    <span>Productos</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Categorias:</h6>
                        <a class="collapse-item" href="Productos.jsp">Todos los productos</a>
                        <a class="collapse-item" href="auriculares.jsp">Auriculares</a>
                        <a class="collapse-item" href="mouse.jsp">Mouse Gaming</a>
                        <a class="collapse-item" href="teclados.jsp">Teclados</a>
                    </div>
                </div>
            </li>

               <!-- Nav Item - Utilities Collapse Menu -->
             <li class="nav-item">
               <a class="nav-link" href="Listapedidos.html">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Lista de Pedidos</span>
                </a>
            </li>


        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

  

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - Alerts -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-danger badge-counter"></span>
                            </a>
                           
                        </li>

                        <!-- Nav Item - Messages -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-envelope fa-fw"></i>
                                <!-- Counter - Messages -->
                                <span class="badge badge-danger badge-counter"></span>
                            </a>
                           
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Admin</span>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Perfil
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Configuracion
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Salir
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->
            
            
    <!-- Page Content -->
    
    <div class="container-fluid">

    <div id="page-content-wrapper">

     <div class="d-flex justify-content-between align-items-center mb-4 flex-wrap">
    <!-- Formulario de búsqueda -->
    <form action="productos" method="get" class="form-inline">
        <input type="hidden" name="accion" value="buscar">

        <div class="form-group mr-2">
            <label for="nombre" class="mr-2">Nombre:</label>
            <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Ej. mouse logitech">
        </div>

        <div class="form-group mr-2">
            <label for="categoria" class="mr-2">Categoría:</label>
            <input type="text" class="form-control" name="categoria" id="categoria" placeholder="Ej. mouse">
        </div>

        <button type="submit" class="btn btn-primary">
            <i class="fas fa-search"></i> Buscar
        </button>
        
        <a href="productos" class="btn btn-secondary ml-2">
    <i class="fas fa-times"></i> Limpiar
     </a>
    </form>

    <!-- Botón añadir producto -->
    <a href="registrarProducto" class="btn btn-success btn-sm shadow-sm mt-2 mt-sm-0">
        <i class="fas fa-plus fa-sm text-white-50"></i> Añadir Nuevo Producto
    </a>
</div>
        <!-- Panel productos-->   
         <div class="row">
    <c:forEach var="p" items="${productos}">
        <div class="col-md-4 mb-4">
            <div class="card border-left-info shadow h-100">
                <img src="img/${p.codigo}.jpg" class="card-img-top img-fluid rounded-top" alt="${p.nombre}" style="height: 200px; object-fit: cover;">
                <div class="card-body">
                    <h5 class="card-title text-primary font-weight-bold">${p.nombre}</h5>
                    <p class="card-text text-muted">${p.descripcion}</p>
                    <p><strong>Precio:</strong> <span class="text-success">$${p.precio_venta}</span></p>
                    <p><strong>Stock:</strong> 
                        <span class="badge badge-pill ${p.cantidad > 0 ? 'badge-warning' : 'badge-danger'}">
                            ${p.cantidad}
                        </span>
                    </p>

                    <div class="d-flex justify-content-between mt-3">
                        <a href="editarProducto?id=${p.id_producto}" class="btn btn-outline-primary btn-sm">
                            <i class="fas fa-edit"></i> Editar
                        </a>
                        <a href="eliminarProducto?id=${p.id_producto}" class="btn btn-outline-danger btn-sm"
                           onclick="return confirm('¿Estás segura de eliminar este producto?');">
                            <i class="fas fa-trash"></i> Eliminar
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
        
    </div>
        
            
  <!-- Scripts -->
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <script>
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>
</body>
</html>
