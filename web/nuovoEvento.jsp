<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <title>Conference - Inserimento Eventi e Articoli</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="author" content="Nextech">
        <meta name="description" content="Pagina per l'inserimento di Articoli o Eventi nel sito di Conference">
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
            <jsp:param name="paginaCorrente" value="new"/>
        </jsp:include>
        
        <c:if test="${empty userData}">
            <c:redirect url="login.jsp"/>
        </c:if>
        
        <c:if test="${not empty userData}"> 
            <div class="subtitle testoS testoLarge">
                    Qui puoi inserire tutte le informazioni necessarie perchè le persone possano
                    partecipare al tuo evento. <br> Ricorda di inserire una descrizione accurata e
                    accattivante per attirare l'attenzione delle persone ! <br> Puoi scegliere se pubblicare 
                    un <b>evento</b> oppure un <b>articolo informativo</b>:
                    <ul>
                        <li><a><b>Evento</b> - Una vola pubblicato comparirà nella pagina degli Eventi</a></li>
                        <li><a><b>Articolo</b> - Una volta pubblicato comparità nella Homepage</a></li>
                    </ul>
            </div>

            <div class="mobilePadding col-d-8 col-s-12">
                <div id="newEvent">
                    <form action="NuovoEventoServlet" method="post" enctype="multipart/form-data" accept-charset="utf-8">
                        <h2 class="testoXXLarge">Aggiungi nuovo evento</h2>
                        
                        <label for="titoloEvento"  class="testoS testoLarge">Titolo: </label>
                        <input class = "cento testoN testoMedium" type="text" id="titoloEvento" name="titoloEvento">
                        <p class="charCount testoN testoMedium">Caratteri: <span id="titoloEventoConteggio"> 0</span>/100</p>
                        <br>
                        
                        <label for="descrizione"  class="testoS testoLarge">Descrizione: </label>
                        <textarea class="testoN testoMedium" id="descrizione" name="descrizione"></textarea>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="descrizioneConteggio"> 0</span>/5000</p>
                        <br>
                        
                        <label for="organizzatore"  class="testoS testoLarge">Organizzatore: </label>
                        <input class = "cento testoN testoMedium" type="text" id="organizzatore" name="organizzatore">
                        <p class="charCount testoN testoMedium">Caratteri: <span id="organizzatoreConteggio"> 0</span>/100</p>
                        <br>
                        
                        <label for="luogo"  class="testoS testoLarge">Luogo: </label>
                        <input class = "cento testoN testoMedium" type="text" id="luogo" name="luogo">
                        <p class="charCount testoN testoMedium">Caratteri: <span id="luogoConteggio"> 0</span>/100</p>
                        <br>
                        
                        <label type="datetime-local" name="dataInizio"  class="testoS testoLarge">Data d'inizio: </label>
                        <input class="testoN testoMedium" type="datetime-local" name="dataInizio">
                        <br>
                        
                        <label type="datetime-local" name="dataFine"  class="testoS testoLarge">Data fine: </label>
                        <input class="testoN testoMedium" type="datetime-local" name="dataFine">
                        <br>
                        
                        <label for="costo"  class="testoS testoLarge">Prezzo: </label>
                        <input class="testoN testoMedium" type="text" name="costo" id="costo"/>
                        <br>
                        
                        <label for="file"  class="testoS testoLarge">Inserisci un'immagine:</label>
                        <input class="testoN testoMedium" name="file" type="file" accept="image/*"/>
                        <br>
                        
                        <label for="descrizioneImmagineEvento"  class="testoS testoLarge">Descrizione Immagine</label>
                        <input class = "duecento testoN testoMedium" type="text" name="descrizioneImmagineEvento" id="descrizioneImmagineEvento"/>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="descrizioneImmagineEventoConteggio"> 0</span>/200</p>
                        <br><br>
                        
                        <input type="submit" value="Pubblica" class="testoS testoLarge"/>
                    </form>
                </div>

                <div id="newArticle">
                    <form action="NuovoArticoloServlet" method="post" enctype="multipart/form-data"  accept-charset="utf-8">
                        <h2 class="testoXXLarge">Aggiungi nuovo articolo</h2>
                        
                        <label for="titoloArticolo"  class="testoS testoLarge">Titolo: </label>
                        <input class = "cento testoN testoMedium" type="text" id="titoloArticolo" name="titoloArticolo">
                        <p class="charCount testoN testoMedium">Caratteri: <span id="titoloArticoloConteggio"> 0</span>/100</p>
                        <br>
                        
                        <label for="sottotitolo"  class="testoS testoLarge">Sottotitolo:</label>
                        <input class = "duecento testoN testoMedium" type="text" id="sottotitolo" name="sottotitolo">
                        <p class="charCount testoN testoMedium">Caratteri: <span id="sottotitoloConteggio"> 0</span>/200</p>
                        <br>
                        
                        <label for="testo"  class="testoS testoLarge">Testo: </label>
                        <textarea class="testoN testoMedium" id="testo" name="testo"></textarea>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="testoConteggio"> 0</span>/5000</p>
                        <br>
                        
                        <label for="file"  class="testoS testoLarge">Inserisci un'immagine: </label>
                        <input class="testoN testoMedium" name="file" type="file" accept="image/*"/>
                        <br>
                        
                        <label for="descrizioneImmagineArticolo"  class="testoS testoLarge">Descrizione Immagine</label>
                        <input class = "duecento testoN testoMedium" type="text" name="descrizioneImmagineArticolo" id="descrizioneImmagineArticolo"/>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="descrizioneImmagineArticoloConteggio"> 0</span>/200</p>
                        <br><br>
                        
                        <input type="submit" value="Pubblica" class="testoS testoLarge"/>
                    </form>
                </div>
            </div> 
            <jsp:include page="commonFooter.jsp"/> 
        </c:if>
    </body>
</html>