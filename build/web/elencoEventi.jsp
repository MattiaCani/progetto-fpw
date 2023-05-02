<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <title>Conference - Eventi</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="author" content="Nextech">
        <meta name="description" content="Sito con vendita di biglietti per convegni e blog con articoli e pubblicazioni di esperti">
        <meta name="keywords" content="cultura, conferenza, convegno, evento">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <link rel="icon" href="img\loghi\logoColorato.png">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@300&display=swap" rel="stylesheet">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Square+Peg&display=swap" rel="stylesheet">
        
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/fontSize.js"></script>
        <script type="text/javascript" src="js/compraBiglietto.js"></script>
    </head>

    <body>
        <jsp:include page="commonHeader.jsp">
            <jsp:param name="paginaCorrente" value="events"/>
        </jsp:include>
        
        <c:if test="${empty userData}">
            <c:redirect url="login.jsp"/>
        </c:if>
        
        <c:if test="${empty listaEventi}">
            <c:redirect url="eventi"/>
        </c:if>
        
        <c:if test="${not empty listaEventi}">
            <div class="mobilePadding col-d-8 col-s-12">
                <c:forEach items="${listaEventi}" var="evento">
                    <div class="userEvent">
                        <h3 class="testoXLarge"><b>${evento.getTitolo()}</b></h3>
                        <p class="testoS testoLarge">${evento.getDescrizione()}</p>
                        <img alt="${evento.getInfoImmagine().getDescrizioneImg()}" title="Foto Evento" src="${evento.getImmagineEvento()}" width="300" height="150" class="eventImage col-d-5 col-s-10">
                        <div>
                            <p class="testoN testoMedium"><b>Data: </b>${evento.getDataInizio()} - ${evento.getDataFine()}</p>
                            <p class="testoN testoMedium"><b>Luogo: </b>${evento.getLuogo()}</p>
                            <p class="testoN testoMedium"><b>Organizzatore: </b>${evento.getOrganizzatore()}</p>
                            <p class="testoN testoMedium"><b>Evento pubblicato dall'utente: </b>${evento.getId_utente()}</p>
                            <p class="testoN testoMedium"><b>Costo del biglietto: </b>${evento.getCosto()} Euro
                                <button type="button" class="compraBiglietto col-s-12 testoN testoMedium" value="compra">Acquista Biglietto</button>
                            </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
          <jsp:include page="commonFooter.jsp"/>  
        </c:if>   
    </body>
</html>