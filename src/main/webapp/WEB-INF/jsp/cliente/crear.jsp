<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Crear Cliente</title>

        <jsp:include page="/WEB-INF/jspf/header.jsp" />
        <style>
            fieldset {
                border: 1px solid #ccc;
                padding: 10px;
            }

            legend {
                display: inline;
                padding: 0 10px;
                width: auto;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />
            <jsp:include page="/WEB-INF/jspf/mensajes.jsp" />
        

                <h1>Carrito de Compras</h1>

                <form method="get" action="carrito">
                    <fieldset>
                        <legend>Datos del Cliente</legend>

                        <input type="hidden" name="id" value="0" />
                        <div class="form-row">
                            <div class="form-group col-md-2">
                                <label>RUT</label>
                                <input type="text" id="rut" name="rut" class="form-control" />    
                            </div>
                            <div class="form-group col-md-1">
                                <label>DV</label>
                                <input type="text" name="dv" id="dv" class="form-control" />
                            </div>
                            <div class="form-group col-md-5">
                                <label>Nombre Completo</label>
                                <input type="text" name="nombre" id="nombre" class="form-control" />    
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label>Dirección</label>
                                <input type="text" name="direccion" id="direccion" class="form-control" />    
                            </div>
                            <div class="form-group col-md-3">
                                <label>Comuna</label>
                                <select name="comuna" id="comuna" class="form-control">
                                    <option>Seleccione una comuna</option>
                                    <option>Cerrillos</option>
                                    <option>Cerro Navia</option>
                                    <option>Maipú</option>
                                    <option>Santiago</option>
                                    <option>Pudahuel</option>
                                    <option>Puente Alto</option>
                                </select>
                            </div>
                            <div class="form-group col-md-2">
                                <label>Fecha de Nacimiento</label>
                                <input type="text" class="form-control" name="fechanacimiento" id="fechanacimiento" />
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <button type="submit" class="btn btn-success">Crear</button>
                            </div>
                        </div>

                    </fieldset>

                    <input type="hidden" name="op" value="crear" />

                </form>
                
        </div>

        <jsp:include page="/WEB-INF/jspf/footer.jsp" />

        <script type="text/javascript">
            jQuery(function () {
                jQuery("#fechanacimiento").datepicker({
                    changeMonth: true
                    , changeYear: true
                    , dateFormat: 'yy-mm-dd'
                    , minDate: new Date(1940, 1, 1)
                    , maxDate: new Date()
                });
                
                jQuery("#rut").blur(function(){                         
                    var url = "${pageContext.request.contextPath}/clientes";
                    var d = {op:"ws", rut: jQuery(this).val()};
                    jQuery.ajax({
                        type: "GET"
                        ,url: url
                        ,data: d
                        ,dataType: "json"
                    }).done(function(res){
                        console.log("procesando respuesta JSON");
                        jQuery("#dv").val(res.dv);
                        jQuery("#nombre").val(res.nombre);
                        jQuery("#direccion").val(res.direccion);
                        jQuery("#comuna").val(res.dv);
                        jQuery("#fechanacimiento").val(res.fechaNacimiento);
                    }).fail(function(jqXHR, textStatus, errorThrown){
                        console.log("AJAX call failed: " + textStatus + ", " + errorThrown);
                    });
                });
            });

        </script>
    </body>
</html>