<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Conference - Pagina personale</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="author" content="Nextech">
        <meta name="description" content="Pagina personale dell'utente">
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
        <script type="text/javascript" src="js/checkArticleEvent.js"></script>
        <script type="text/javascript" src="js/fontSize.js"></script>
    </head>
    
    <body>
        <jsp:include page="commonHeader.jsp">
            <jsp:param name="paginaCorrente" value="personal"/>
        </jsp:include>
        
        <c:if test="${empty userData}">
            <c:redirect url="login.jsp"/>
        </c:if>
        
        <c:if test="${not empty userData}">
            <div class="col-d-8 col-s-12 userProfileBox">
                <img alt="${userData.getInfoImmagine().getDescrizioneImg()}" title ="Foto Profilo" src=${userData.getFotoProfilo()}>
                <div>
                    <p class="testoS testoLarge">Bentornato utente ${userData.getUsername()}!</p>
                    <p class="testoS testoLarge"><b>Nome:</b> ${userData.getNome()}</p>
                    <p class="testoS testoLarge"><b>Cognome:</b> ${userData.getCognome()}</p>
                    <p class="testoS testoLarge"><b>Indirizzo email:</b> ${userData.getEmail()}</p>
                    <p class="testoS testoLarge"><b>Regione:</b> ${userData.getRegione()}</p>
                    <p class="testoS testoLarge"><b>Citta':</b> ${userData.getCitta()}</p>
                    <p class="testoS testoLarge"><b>Argomento di Competenza:</b> ${userData.getArgomentoCompetenza()}</p>
                    <p class="testoS testoLarge"><b>Titolo di Studio:</b> ${userData.getTitoloStudio()}</p>
                    <p class="testoS testoLarge"><b>Numero di Telefono:</b> ${userData.getNumeroTelefono()}</p>
                    <p class="testoS testoLarge"><b>Impiego Attuale:</b> ${userData.getImpiegoAttuale()}</p>
                    <button type="button" class="testoS testoLarge"><a href="login.jsp">Torna al login</a></button>
                </div>
            </div>             
            <jsp:include page="commonFooter.jsp"/> 
        </c:if>
    </body>
</html>