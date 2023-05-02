<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <title>Conference - Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="author" content="Nextech">
        <meta name="description" content="Pagina di login e registrazione al sito Conference">
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
        <script type="text/javascript" src="js/checkUser.js"></script>
        <script type="text/javascript" src="js/fontSize.js"></script>
        <script type="text/javascript" src="js/showLoginRegistration.js"></script>
    </head>

    <body>
        <jsp:include page="commonHeader.jsp">
            <jsp:param name="paginaCorrente" value="login"/>
        </jsp:include>
        
        <c:if test="${not empty userData}">
            <form action="LogoutServlet" method="post" class="col-d-8 col-s-12">
                <p class="testoS testoLarge">Stai già andando via ? <input class="testoN testoSmall" type="submit" value="Logout"/></p>
            </form>            
        </c:if>
        
        <c:if test="${empty userData}">  
            <div>
                <p class="subtitle testoS testoLarge">
                    <b>Hai delle competenze di uno specifico argomento ? Vuoi organizzare eventi culturali per trasmettere queste tue conoscenze ?</b>
                    <br>
                    <b>Ti serve una piattaforma per condividere i tuoi articoli ?</b> Allora questo è il posto giusto per te !
                </p>
            </div>
        
            <div class="credentials col-d-8 col-s-12">
                <div id="login">
                    <form action="LoginServlet" method="post" accept-charset="utf-8">
                        <h2 class="testoXXLarge">Login</h2>
                        
                        <p class="testoN testoMedium">Sei nuovo ? <button type="button" value="registration" class="showRegistration testoS testoLarge">Registrati!</button></p>
                        
                        <label for="usernameLogin" class="testoS testoLarge">Username</label>
                        <input class="testoN testoMedium" type="text" name="usernameLogin" id="usernameLogin"/>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="usernameLoginConteggio"> 0</span>/50</p>
                        
                        <label for="passwordLogin" class="testoS testoLarge">Password</label>
                        <input class="testoN testoMedium" type="password" name="passwordLogin" id="passwordLogin"/>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="passwordLoginConteggio"> 0</span>/30</p>
                        
                        <button type="submit" class="testoS testoLarge">Invia Dati</button>
                    </form>
                </div>
                
                <div id="sign-up" class="hideBox">
                    <form action="SignUpServlet" method="post" enctype="multipart/form-data"  accept-charset="utf-8">
                        <h2 class="testoXXLarge">Sign-Up</h2>
                        
                        <p class="testoN testoMedium">Fai già parte di Conference ? <button type="button" value="login" class="showLogin testoS testoLarge">Accedi!</button></p>
                        
                        <label for="nome" class="testoS testoLarge">Nome</label>
                        <input class="cento testoN testoMedium" type="text" name="nome" id="nome"/>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="nomeConteggio"> 0</span>/100</p>
                        
                        <label for="cognome" class="testoS testoLarge">Cognome</label>
                        <input class="cento testoN testoMedium" type="text" name="cognome" id="cognome"/>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="cognomeConteggio"> 0</span>/100</p>
                        
                        <label for="username" class="testoS testoLarge">Username</label>
                        <input class="testoN testoMedium" type="text" name="username" id="username"/>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="usernameConteggio"> 0</span>/50</p>
                        
                        <label for="email" class="testoS testoLarge">Email</label>
                        <input class="cento testoN testoMedium" type="email" name="email" id="email"/>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="emailConteggio"> 0</span>/100</p>
                        
                        <label for="password" class="testoS testoLarge">Password</label>
                        <input class="testoN testoMedium" type="password" name="password" id="password"/>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="passwordConteggio"> 0</span>/30</p>
                        
                        <label for="regione" class="testoS testoLarge">Regione</label>
                            <select class="testoN testoMedium" name="regione">
                                <option value="Abruzzo">Abruzzo</option>
                                <option value="Basilicata">Basilicata</option>
                                <option value="Calabria">Calabria</option>
                                <option value="Campania">Campania</option>
                                <option value="Emilia-Romagna">Emilia-Romagna</option>
                                <option value="Friuli Venezia Giulia">Friuli Venezia Giulia</option>
                                <option value="Lazio">Lazio</option>
                                <option value="Liguria">Liguria</option>
                                <option value="Lombardia">Lombardia</option>
                                <option value="Marche">Marche</option>
                                <option value="Molise">Molise</option>
                                <option value="Piemonte">Piemonte</option>
                                <option value="Puglia">Puglia</option>
                                <option value="Sardegna">Sardegna</option>
                                <option value="Sicilia">Sicilia</option>
                                <option value="Toscana">Toscana</option>
                                <option value="Trentino-alto adige">Trentino-Alto Adige</option>
                                <option value="Umbria">Umbria</option>
                                <option value="Valle d'aosta">Valle d'Aosta</option>
                                <option value="Veneto">Veneto</option>
                            </select>
                        
                        <label for="citta" class="testoS testoLarge">Città</label>
                        <input class="cento testoN testoMedium" type="text" name="citta" id="citta"/>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="cittaConteggio"> 0</span>/100</p>
                        
                        <label for="argomentoCompetenza" class="testoS testoLarge">Argomento di Competenza</label>
                            <select class="testoN testoMedium" name="argomentoCompetenza">
                                <option value="Architettura">Architettura</option>
                                <option value="Arte">Arte</option>
                                <option value="Chimica">Chimica</option>
                                <option value="Cinema">Cinema</option>
                                <option value="Diritto">Diritto</option>
                                <option value="Economia">Economia</option>
                                <option value="Filosofia">Filosofia</option>
                                <option value="Informatica">Informatica</option>
                                <option value="Ingegneria">Ingegneria</option>
                                <option value="Letteratura">Letteratura</option>
                                <option value="Matematica">Matematica</option>
                                <option value="Medicina">Medicina</option>
                                <option value="Musica">Musica</option>
                                <option value="Politica">Politica</option>
                                <option value="Scienza">Scienza</option>
                                <option value="Sport">Sport</option>
                                <option value="Storia antica">Storia Antica</option>
                                <option value="Storia moderna">Storia Moderna</option>
                                <option value="Teologia">Teologia</option>
                                <option value="Videogiochi">Videogiochi</option>
                            </select>
                        
                        <label for="titoloStudio" class="testoS testoLarge">Titolo di Studio</label>
                        <input class="cento testoN testoMedium" type="text" name="titoloStudio" id="titoloStudio"/>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="titoloStudioConteggio"> 0</span>/100</p>

                        <label for="numeroTelefono" class="testoS testoLarge">Numero di Telefono</label>
                        <input class="testoN testoMedium" type="text" name="numeroTelefono" id="numeroTelefono"/>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="numeroTelefonoConteggio"> 0</span>/10</p>

                        <label for="impiegoAttuale" class="testoS testoLarge">Impiego Attuale</label>
                        <input class="cento testoN testoMedium" type="text" name="impiegoAttuale" id="impiegoAttuale"/>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="impiegoAttualeConteggio"> 0</span>/100</p>
                        
                        <label for="file" class="testoS testoLarge">Inserisci un'immagine:</label>
                        <input class="testoN testoMedium" name="file" type="file" accept="image/*"/>
                        
                        <label for="descrizioneImmagine" class="testoS testoLarge">Descrizione Immagine</label>
                        <input class="duecento testoN testoMedium" type="text" name="descrizioneImmagine" id="descrizioneImmagine"/>
                        <p class="charCount testoN testoMedium">Caratteri: <span id="descrizioneImmagineConteggio"> 0</span>/200</p>
                        
                        <button type="submit" class="testoS testoLarge">Invia Dati</button>
                    </form>
                </div>
            </div>
        </c:if>
        
        <jsp:include page="commonFooter.jsp"/>  
    </body>
</html>