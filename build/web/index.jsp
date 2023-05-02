<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Conference&Culture - Home</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="author" content="Nextech">
        <meta name="description" content="Sito per la vendita di biglietti per convegni e conferenze, inoltre anche blog con articoli e pubblicazioni di esperti">
        <meta name="keywords" content="cultura, conferenza, convegno, evento, articolo, scienza">
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
    </head>

    <body>
        <jsp:include page="commonHeader.jsp">
            <jsp:param name="paginaCorrente" value="index"/>
        </jsp:include>
        
        <c:if test="${empty listaArticoli}">
            <c:redirect url="home"/>
        </c:if>
        
        <c:if test="${not empty listaArticoli}">    
            <div class="col-d-12 col-s-12" id="benvenuto">
                <h2 class="testoXXLarge">Benvenuto!</h2>
                <p class="testoS testoLarge">Qui troverai gli articoli più interessanti e recenti e le pubblicazioni dei nostri esperti. 
                Se vuoi saperne di più su qualcosa, <i>questo</i> è il tuo posto.</p>
            </div>

            <div class="mobilePadding col-d-8 col-s-12">
                <c:forEach items="${listaArticoli}" var="articolo">
                    <div class="userArticle">
                        <h3 class="testoXLarge"><b>${articolo.getTitolo()}</b></h3>
                        <div class="testoS testoLarge"><span>Autore articolo: </span>${articolo.getId_utente()}</div>
                        <h4 class="testoLarge"><i>${articolo.getSottotitolo()}</i></h4>
                        <img alt="${articolo.getInfoImmagine().getDescrizioneImg()}" title="Foto Articolo" src="${articolo.getImmagineArticolo()}" width="570" height="381" class="col-d-5 col-s-12">
                        <p class="testoN testoMedium">${articolo.getTesto()}</p>
                    </div>
                </c:forEach>
            </div>
            
            <jsp:include page="commonFooter.jsp"/>  
        </c:if>   
    </body>
</html>