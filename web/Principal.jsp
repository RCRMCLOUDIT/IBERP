<%-- 
    Document   : Principal
    Created on : 08-20-2018, 02:36:05 PM
    Author     : Ing. Moises Romero Mojica
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/estilos.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/blocker.js"></script>
        <script src="js/cross-browser.js"></script>
        <title>Principal</title>
    </head>
    <body>
        <%-- MENU --%>
        <nav class="navbar navbar-expand-sm bg-light navbar-light">
            <!-- Brand -->
            <a class="navbar-brand" href="Principal.jsp"><img src="images/LOGOIB.png" title="Infinity Business"></a>
            <!-- Links -->
            <ul class="navbar-nav">
                <!-- Dropdown -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Administracion</a>
                    <div class="dropdown-menu">

                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Producto</a>
                    <div class="dropdown-menu">

                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Bodega</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="Importaciones/BuscarPolizas.jsp">Importaciones</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Ventas</a>
                    <div class="dropdown-menu">

                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Compras</a>
                    <div class="dropdown-menu">

                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">CxC</a>
                    <div class="dropdown-menu">

                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">CxP</a>
                    <div class="dropdown-menu">

                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Contabilidad</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="Contabilidad/centrosdecosto/ListaNivelCC.jsp">Nivel de CC</a>
                        <a class="dropdown-item" href="Contabilidad/TiposCuenta.jsp">Tipos Cuentas</a>
                        <a class="dropdown-item" href="Contabilidad/CatalogoContable.jsp">Catalogo Contable</a>
                        <div class="dropdown-divider"> Comprobantes </div>
                        <a class="dropdown-item" href="Contabilidad/PlantillaComprobante.jsp">Plantilla Comprobante</a>
                        <a class="dropdown-item" href="Contabilidad/BuscarComprobante.jsp">Buscar Comprobante</a>
                        <div class="dropdown-divider"> Reportes </div>
                        <a class="dropdown-item" href="Contabilidad/Reportes/DetalleTransCuenta.jsp">Detalle Trans. Por Cuenta</a>
                    </div>
                </li>
            </ul>
        </nav>
        <%-- END OF MENU --%> 
    </body>
</html>