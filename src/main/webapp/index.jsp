<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title> Bienvenido a la Hierberia</title>        
        <jsp:include page="/WEB-INF/jspf/header.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />

            <p>
                Bienvenido a la Hierberia
            </p>
            <img class="img-fluid" src="img/compras1.jpg" alt="Sistema de Gestión de Marihuana" />
        </div>

        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>