package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DaoContabilidad;

/**
 *
 * @author Ing. Moises Romero Mojica
 */
public class ServletContabilidad extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String Accion = request.getParameter("form-Accion");
        int Msg = 0; // ESTA VARIABLE SERA PARA CONTROLAR LOS MENSAJES DE ERROR Y ENVIARLO A LA VISTA
        //---------------------------------------------------------------------//
        // IF PARA AGREGAR UN NUEVO TIPO DE CUENTA CONTABLE
        if (Accion.equals("AddTipoCta")) {
            int CompanyId = 1;
            int GLTPCLSID = Integer.valueOf(request.getParameter("form-NumeroCuenta"));
            String GLTPNAME = request.getParameter("form-NombreCuenta");
            String GLTPACCID = request.getParameter("form-TipoCuenta");
            DaoContabilidad datos = new DaoContabilidad();
            datos.VerifarNombre(GLTPNAME);
            String GetGLTPNAME = datos.GetGLTPNAME;
            datos.VerificarCod(GLTPCLSID);
            int GetGLTPCLSID = datos.GetGLTPCLSID;
            if (GLTPNAME.equals(GetGLTPNAME)) {
                Msg = 1;
                //MENSAJE = 1 EL NOMBRE DE CUENTA YA EXISTE
                //response.sendRedirect("AgregarTipoCuenta.jsp?Msg=" + Msg + "");
                //request.getRequestDispatcher("/contabilidad/AgregarTipoCuenta.jsp?Msg=" + Msg).forward(request, response);
                response.sendRedirect("Contabilidad/AgregarTipoCuenta.jsp?Msg=" + Msg);
            } else if (GLTPCLSID == GetGLTPCLSID) {
                Msg = 2;
                //MENSAJE = 2 EL CODIGO DE CUENTA YA EXISTE
                //response.sendRedirect("AgregarTipoCuenta.jsp?Msg=" + Msg + "");
                //request.getRequestDispatcher("/contabilidad/AgregarTipoCuenta.jsp?Msg=" + Msg).forward(request, response);
                response.sendRedirect("Contabilidad/AgregarTipoCuenta.jsp?Msg=" + Msg);
            } else {
                try {
                    datos.GLADDTYPACC(CompanyId, GLTPCLSID, GLTPNAME, GLTPACCID, "Moises Romero", "");
                } catch (Exception e) {
                    e.getMessage();
                }
                //response.sendRedirect("contabilidad/TipoCuentas.jsp");               
            }
            response.sendRedirect("Contabilidad/TiposCuenta.jsp");
        }//FIN DEL IF PARA AGREGAR UN NUEVO TIPO DE CUENTA CONTABLE

        //---------------------------------------------------------------------//
        // IF PARA ACTUALIZAR UN TIPO DE CUENTA CONTABLE
        if (Accion.equals("UpdateTipoCta")) {
            int CompanyId = 1;
            int GLTPCLSID = Integer.valueOf(request.getParameter("form-NumeroCuenta"));
            String GLTPNAME = request.getParameter("form-NombreCuenta");
            String GLTPACCID = request.getParameter("form-TipoCuenta");
            DaoContabilidad datos = new DaoContabilidad();
            datos.VerifarNombre(GLTPNAME);
            String GetGLTPNAME = datos.GetGLTPNAME;
            int GetGLTPCLSID = datos.GetGLTPCLSID;
            if (GLTPNAME.equals(GetGLTPNAME) && GLTPCLSID == GetGLTPCLSID) {
                try {
                    datos.GLUPDTYPACC(GLTPCLSID, GLTPNAME, GLTPACCID, "Moises Romero", "");
                } catch (Exception e) {
                    e.getMessage();
                }
            } else {
                Msg = 1;
                //MENSAJE = 1 EL NOMBRE DE CUENTA YA EXISTE
                //response.sendRedirect("AgregarTipoCuenta.jsp?Msg=" + Msg + "");
                //request.getRequestDispatcher("/contabilidad/ModificarTipoCuenta.jsp?GLTPCLSID=" + GLTPCLSID + "&Msg=" + Msg).forward(request, response);
                response.sendRedirect("Contabilidad/ModificarTipoCuenta.jsp?GLTPCLSID=" + GLTPCLSID + "&Msg=" + Msg);
            }
            response.sendRedirect("Contabilidad/TiposCuenta.jsp");
        }//FIN DEL IF PARA ACTUALIZAR UN TIPO DE CUENTA CONTABLE

        //---------------------------------------------------------------------//
        // IF PARA REGISTRAR UNA CUENTA EN EL CATALOGO CONTABLE
        if (Accion.equals("AddCtaContable")) {
            //RECUPERO LOS PARAMETROS DEL JSP
            int IdTipoCta = Integer.valueOf(request.getParameter("form-TipoCuenta"));
            String NombreCta = request.getParameter("form-NombreCuenta");
            String NumeroCta = request.getParameter("form-NumeroCuenta");
            String Descripcion = request.getParameter("form-Descripcion");
            int SubCta = 0;
            if (request.getParameter("form-CuentaPadre") != null) {
                SubCta = Integer.valueOf(request.getParameter("form-CuentaPadre"));
            }
            int GetLevel1, GetLevel2, GetLevel3, GetLevel4, GetLevel5, GetLevel6;

            DaoContabilidad datos = new DaoContabilidad();
            //MANDO A VERIFICAR SI EL NOMBRE DE CUENTA CONTABLE YA EXISTE
            datos.CheckNameIBGLACCNTS(NombreCta);
            String GetName = datos.GetAccountName;
            //MANDO A VERIFICAR SI EL NUMERO DE CUENTA CONTABLE YA EXISTE
            datos.CheckNumberIBGLACCNTS(NumeroCta);
            String GetNumber = datos.GetAccountNumber;
            if (NombreCta.equals(GetName)) {
                //MENSAJE = 1 EL NOMBRE DE CUENTA YA EXISTE
                Msg = 1;
                //request.getRequestDispatcher("/contabilidad/AgregaCuentaContable.jsp?Msg=" + Msg).forward(request, response);
                response.sendRedirect("Contabilidad/AgregaCuentaContable.jsp?Msg=" + Msg + "&IdTipoCuenta=" + IdTipoCta + "&Name=" + NombreCta + "&Num=" + NumeroCta + "&Desc=" + Descripcion);
            } else if (NumeroCta.equals(GetNumber)) {
                //MENSAJE = 2 EL NUMERO DE CUENTA YA EXISTE
                Msg = 2;
                //request.getRequestDispatcher("/contabilidad/AgregaCuentaContable.jsp?Msg=" + Msg).forward(request, response);
                response.sendRedirect("Contabilidad/AgregaCuentaContable.jsp?Msg=" + Msg + "&IdTipoCuenta=" + IdTipoCta + "&Name=" + NombreCta + "&Num=" + NumeroCta + "&Desc=" + Descripcion);
            } else {
                //SI SUB-CTA = 0; QUIERE DECIR QUE SERA UNA CUENTA DE NIVEL 1.
                if (SubCta == 0) {
                    //MANDO A BUSCAR EL NUMERO ACTUAL DE NUMERACION DEL NIVEL
                    datos.GetCountLevel();
                    //RECUPERO EL CONTEO QUE LLEVA EL NIVEL 1
                    GetLevel1 = Integer.valueOf(datos.GetAccountLevel1) + 1;
                    try {
                        //MANDO A GUARDAR EL REGISTRO PARA GUARDAR LA CUENTA CONTABLE EL LA TABLA 'IBGLACCNTS'
                        datos.GLADDACNTS(String.valueOf(GetLevel1), "000", "000", "000", "000", "000",
                                NumeroCta, NombreCta, Descripcion, "Moises Romero", "", IdTipoCta, 1);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    //REDIRECCIONO A LA PAGINA DEL CATALOGO CONTABLE
                    response.sendRedirect("Contabilidad/CatalogoContable.jsp");
                }
                //SI SUB-CTA > 0; QUIERE DECIR QUE SERA UNA CUENTA DE NIVEL 2,3,4,5,6
                if (SubCta > 0) {
                    //PRIMERO MANDO A RECUPERAR LA NUMERACION DE NIVEL QUE TIENE LA CUENTA PADRE
                    datos.BuscarIBGLACCNTS(SubCta);
                    GetLevel1 = Integer.valueOf(datos.GetAccountLevel1);
                    GetLevel2 = Integer.valueOf(datos.GetAccountLevel2);
                    GetLevel3 = Integer.valueOf(datos.GetAccountLevel3);
                    GetLevel4 = Integer.valueOf(datos.GetAccountLevel4);
                    GetLevel5 = Integer.valueOf(datos.GetAccountLevel5);
                    GetLevel6 = Integer.valueOf(datos.GetAccountLevel6);
                    int NewNivel = 0;
                    //AHORA MANDO VERIFICAR QUE NIVEL TIENE LA QUE SERA LA CUENTA PADRE DE LA NUEVA CUENTA CONTABLE
                    if (GetLevel1 > 0 && GetLevel2 == 0 && GetLevel3 == 0) {
                        NewNivel = 2; //LA CUENTA SERA DE NIVEL 2
                    }
                    if (GetLevel2 > 0 && GetLevel3 == 0 && GetLevel4 == 0) {
                        NewNivel = 3; //LA CUENTA SERA DE NIVEL 3
                    }
                    if (GetLevel3 > 0 && GetLevel4 == 0 && GetLevel5 == 0) {
                        NewNivel = 4; //LA CUENTA SERA DE NIVEL 4
                    }
                    if (GetLevel4 > 0 && GetLevel5 == 0 && GetLevel6 == 0) {
                        NewNivel = 5; //LA CUENTA SERA DE NIVEL 5
                    }
                    if (GetLevel5 > 0 && GetLevel6 == 0) {
                        NewNivel = 6; //LA CUENTA SERA DE NIVEL 6
                    }
                    switch (NewNivel) {
                        // EN CASO DE QUE SERA DE NIVEL 2 MANDO A RECUPERAR EL CONTEO DE CUENTA DE ESE NIVEL Y LE SUMO +1 Y LO GUARDO EN LA BASE DE DATOS.
                        case 2:
                            GetLevel2 = 0;
                            datos.GetCountLevel();
                            GetLevel2 = Integer.valueOf(datos.GetAccountLevel2) + 1;
                            try {
                                datos.GLADDACNTS(String.valueOf(GetLevel1), String.valueOf(GetLevel2), "000", "000", "000", "000",
                                        NumeroCta, NombreCta, Descripcion, "Moises Romero", "", IdTipoCta, 1);
                            } catch (Exception e) {
                                e.getMessage();
                            }
                            break;
                        // EN CASO DE QUE SERA DE NIVEL 3 MANDO A RECUPERAR EL CONTEO DE CUENTA DE ESE NIVEL Y LE SUMO +1 Y LO GUARDO EN LA BASE DE DATOS.
                        case 3:
                            GetLevel3 = 0;
                            datos.GetCountLevel();
                            GetLevel3 = Integer.valueOf(datos.GetAccountLevel3) + 1;
                            try {
                                datos.GLADDACNTS(String.valueOf(GetLevel1), String.valueOf(GetLevel2), String.valueOf(GetLevel3), "000", "000", "000",
                                        NumeroCta, NombreCta, Descripcion, "Moises Romero", "", IdTipoCta, 1);
                            } catch (Exception e) {
                                e.getMessage();
                            }
                            break;
                        // EN CASO DE QUE SERA DE NIVEL 4 MANDO A RECUPERAR EL CONTEO DE CUENTA DE ESE NIVEL Y LE SUMO +1 Y LO GUARDO EN LA BASE DE DATOS.
                        case 4:
                            GetLevel4 = 0;
                            datos.GetCountLevel();
                            GetLevel4 = Integer.valueOf(datos.GetAccountLevel4) + 1;
                            try {
                                datos.GLADDACNTS(String.valueOf(GetLevel1), String.valueOf(GetLevel2), String.valueOf(GetLevel3), String.valueOf(GetLevel4),
                                        "000", "000", NumeroCta, NombreCta, Descripcion, "Moises Romero", "", IdTipoCta, 1);
                            } catch (Exception e) {
                                e.getMessage();
                            }
                            break;
                        // EN CASO DE QUE SERA DE NIVEL 5 MANDO A RECUPERAR EL CONTEO DE CUENTA DE ESE NIVEL Y LE SUMO +1 Y LO GUARDO EN LA BASE DE DATOS.
                        case 5:
                            GetLevel5 = 0;
                            datos.GetCountLevel();
                            GetLevel5 = Integer.valueOf(datos.GetAccountLevel5) + 1;
                            try {
                                datos.GLADDACNTS(String.valueOf(GetLevel1), String.valueOf(GetLevel2), String.valueOf(GetLevel3), String.valueOf(GetLevel4),
                                        String.valueOf(GetLevel5), "000", NumeroCta, NombreCta, Descripcion, "Moises Romero", "", IdTipoCta, 1);
                            } catch (Exception e) {
                                e.getMessage();
                            }
                            break;
                        // EN CASO DE QUE SERA DE NIVEL 6 MANDO A RECUPERAR EL CONTEO DE CUENTA DE ESE NIVEL Y LE SUMO +1 Y LO GUARDO EN LA BASE DE DATOS.
                        case 6:
                            GetLevel6 = 0;
                            datos.GetCountLevel();
                            GetLevel6 = Integer.valueOf(datos.GetAccountLevel6) + 1;
                            try {
                                datos.GLADDACNTS(String.valueOf(GetLevel1), String.valueOf(GetLevel2), String.valueOf(GetLevel3), String.valueOf(GetLevel4),
                                        String.valueOf(GetLevel5), String.valueOf(GetLevel6), NumeroCta, NombreCta, Descripcion, "Moises Romero", "", IdTipoCta, 1);
                            } catch (Exception e) {
                                e.getMessage();
                            }
                            break;
                    }
                    //REDIRECCIONO A LA PAGINA DEL CATALOGO CONTABLE
                    response.sendRedirect("Contabilidad/CatalogoContable.jsp");
                }
            }
        } //FIN DEL IF PARA REGISTRAR UNA CUENTA EN EL CATALOGO CONTABLE

        //---------------------------------------------------------------------//
        // IF PARA ACTUALIZAR UNA CUENTA EN EL CATALOGO CONTABLE
        if (Accion.equals("UpdateCtaContable")) {
            int CompanyId = 1;
            //RECUPERO LOS PARAMETROS DEL JSP
            int IdCatalogo = Integer.valueOf(request.getParameter("form-IdCatalogo"));
            String NombreCta = request.getParameter("form-NombreCuenta");
            String NumeroCta = request.getParameter("form-NumeroCuenta");
            String Descripcion = request.getParameter("form-Descripcion");
            DaoContabilidad datos = new DaoContabilidad();
            int GetIdCatalogo = 0;// ESTA VARIABLE ME SERVIRA PARA RECUPERAR EL ID DE LA CUENTA QUE TIENE EL MISMO NOMBRE Ó NUMERO
            datos.CheckNameIBGLACCNTS(NombreCta); // MANDO A VERIFICAR SI HAY COINCIDENCIA CON EL NOMBRE DE LA CUENTA
            GetIdCatalogo = datos.GetIdCatalogo;
            if (IdCatalogo != GetIdCatalogo) {
                //MENSAJE = 1 EL NOMBRE DE CUENTA YA EXISTE
                Msg = 1;
                response.sendRedirect("Contabilidad/EditarCuentaContable.jsp?IDCATALOGO=" + IdCatalogo + "&Msg=" + Msg);
            } else {
                datos.CheckNumberIBGLACCNTS(NumeroCta); // MANDO A VERIFICAR SI HAY COINCIDENCIA CON EL NUMERO DE LA CUENTA
                GetIdCatalogo = datos.GetIdCatalogo;
                if (IdCatalogo != GetIdCatalogo) {
                    //MENSAJE = 1 EL NUMERO DE CUENTA YA EXISTE
                    Msg = 2;
                    response.sendRedirect("Contabilidad/EditarCuentaContable.jsp?IDCATALOGO=" + IdCatalogo + "&Msg=" + Msg);
                } else {
                    try {
                        datos.GLUPDACNTS(IdCatalogo, NumeroCta, NombreCta, Descripcion, "Moises Romero", "", CompanyId);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    response.sendRedirect("Contabilidad/CatalogoContable.jsp");
                }
            }

        }//FIN DEL IF PARA ACTUAIZAR UNA CUENTA EN EL CATALOGO CONTABLE
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
