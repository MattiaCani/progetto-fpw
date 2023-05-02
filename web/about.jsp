<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Conference&Culture - Chi siamo</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="author" content="NexTech">
        <meta name="description" content="Informazioni sull'azienda">
        <meta name="keywords" content="azienda, informazioni, team">
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
            <jsp:param name="paginaCorrente" value="about"/>
        </jsp:include>
        
            <div class="mobilePadding col-d-8 col-s-12 fontTesto">
                <div id="index">
                    <h3 class="testoXLarge">Indice</h3>
                    <nav>
                       <ul>
                           <li><a href="#info" class="testoN testoMedium">Storia dell'azienda</a></li>
                           <li><a href="#team" class="testoN testoMedium">Il nostro team</a></li>
                           <li><a href="#mascotte" class="testoN testoMedium">La nostra mascotte</a></li>
                       </ul> 
                    </nav>
                </div>

                <div id="info">
                    <h3 class="testoXLarge">Storia dell'azienda</h3>
                    <div>
                        <p class="testoN testoMedium">Estate del 2004, <b>Mattia</b> classe 1977 laureato in informatica e media communication marketing
                            cerca di dare una svolta alla sua vita, creando un servizio innovativo. Partito da una piccola
                            startup, il progetto della piattaforma di organizzazione eventi ha ricevuto un notevole supporto 
                            da parte della community Kickstarter che ha visto nella giovinezza di una persona tanta voglia 
                            di fare. <br><br> Mattia incontra <b>Roberta</b> in un garage, dove lei si accingeva a smanettare con dei computer e da lì
                            si capì subito che le competenze di Roberta avrebbero giovato particolarmente alla neonata azienda.
                            Dal 2007 infatti Roberta fornisce un notevole supporto tecnico alla piattaforma, facendo rigorosi check up 
                            di sicurezza al sistema per tenere gli utenti al sicuro da malintenzionati che vorrebbero accedere ai loro dati. <br><br>
                            <b>Federico</b>, cresciuto a pane e videogiochi, entra ufficialmente nel team a partire dal 2009. Le sue competenze
                            tecnico-logiche di consentono di mantenere la base di dati consistente, consentendo a Roberta di interfacciarsi con i
                            dati degli utenti, fornendo a loro dei servizi sempre più all'avanguardia e al passo con i tempi. <br><br> Dal 2020 
                            l'azienda assume soltanto scimpanzè con un QI molto alto. La loro manodopera costa meno di quella umana e non
                            sono rappresentati da alcun sindacato, quindi è possibile sfruttarli.
                        </p>
                    </div>
                </div>
                                
                <div id="team">
                    <h3 class="testoXLarge">Conosciamoci meglio</h3>
                    
                    <div id="flex">
                        <div class="founder col-d-12 col-s-12">
                            <b class="testoS testoLarge"><i>Roberta<br>Angioni</i></b>
                            <img title="Foto di Roberta Angioni" alt="Gatto che urla" src="img\foto-fondatori\GattoUrlante.jpg" width="100" height="100">
                            <p class="testoN testoMedium">Ha un debole per i gattini coccolosi e un amore profondo per l'informatica. Laureata presso il 
                                prestigioso "<i>Samsung Innovation Campus</i>" costituisce una risorsa molto importante per la sicurezza della 
                                nostra azienda. La Russia sta cercando di penetrare il nostro sito web? Dovrà fare i conti con Roberta.
                            </p>
                        </div>

                        <div class="founder col-d-12 col-s-12">
                            <b class="testoS testoLarge"><i>Mattia<br>Cani</b>
                            <img title="Foto di Mattia Cani" alt="Patrick dal cartone animato: Spongebob" src="img\foto-fondatori\Patrick.jpg" width="100" height="100">
                            <p class="testoN testoMedium"></i>Dopo aver imparato a leggere e a scrivere per poter giocare col computer della sorella, Mattia ama nerdare 
                                sui computer da quando aveva 4 anni. Dopo essersi laureato, ha investito nella startup che in futuro sarebbe diventata 
                                questa stessa azienda. Ad oggi gestisce l'amministrazione di NexTech Corporation assieme ai suoi fedelissimi compagni 
                                Roberta e Federico.
                            </p> 
                        </div>

                        <div class="founder col-d-12 col-s-12">
                            <b class="testoS testoLarge"><i>Federico<br>Placentino</i></b>
                            <div>
                                <img title="Foto di Federico Placentino" alt="Saul Goodman dalla serie TV Better Call Saul" src="img\foto-fondatori\BetterCallSaul-Foto.jpg" width="100" height="100">
                                <p class="testoN testoMedium">Appassionatissimo di videogiochi e pesca, decide di specializzarsi nelle strutture dati in cui essi 
                                    si basano. Ha contribuito allo sviluppo del catalogo della fauna  del famoso gioco di simulazione "<i>Animal Crossing</i>" 
                                    introducendo il pesce DAGRR. Gestisce le basi di dati del nostro servizio e minuziosamente setaccia i dati per verificare 
                                    la loro correttezza e coerenza, in modo tale da evitare blocchi e sovraccarichi del sistema.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div id="mascotte">
                    <h3  class="testoXLarge">La nostra Mascotte</h3>

                    <div>
                        <img title="Mascotte Nextech" alt="Scimmia con cappotto, mascotte di Nextech" src="img\foto-fondatori\MascotteFoto.jpeg" width="100" height="100" class="col-d-6 col-s-8">
                        <p class="testoN testoMedium">Si chiama <b>Tritolo</b> ed è il cuore di Nextech. 
                            <br>È molto più intelligente di noi e infatti probabilmente verrà promossa a direttrice dell'azienda.
                        </p>
                        <img title="Mascotte Nextech" alt="Scimmia che gioca a calcio, mascotte di Nextech" src="img\foto-fondatori\Mascotte.gif" width="100" height="100" class="col-d-6 col-s-8">
                        <p class="testoN testoMedium">Abbiamo passato tante avventure insieme.
                            <br>Una delle nostre preferite è quando ha provato a giocare a calcio per la prima volta.
                        </p>
                        <img title="Mascotte Nextech" alt="Scimmia al computer, mascotte di Nextech" src="img\foto-fondatori\MascotteAlPc.gif" width="100" height="100" class="col-d-6 col-s-8">
                        <p class="testoN testoMedium">Altrimenti potete ammirarla in ufficio intenta a risolvere quella dannata navbar che non risulta sticky.
                            <br>È una scimmia tanto intelligente che riuscirà a prendere la laurea prima di noi.
                        </p>
                    </div>
                    
                </div>
            </div>
        
        <jsp:include page="commonFooter.jsp"/>  
    </body>
</html>