//Codice per la gestione dell'ingrandimento e diminuzione del font
//Avviene attraverso l'aggiunta e la rimozione di una classe che specifica la grandezza del font
//Sono presenti classi per i vari valori standard di font (small, medium, large e altro)
//I vari header hanno grandezze diverse, e si distinguono due tipi di testo con grandezze diverse (testoNormale e testoSpeciale)
$(document).ready(function() {
    
    //Rimuove tutte le classi che gestiscono il font
    function rimozioneClassi(){
        $("*").removeClass("testoXXXLarge testoXXLarge testoXLarge testoLarge testoMedium testoSmall");
    }
    
    //Aggiunge la classse specificata nell'elemento specificato
    function aggiuntaClasse(elementoDOM, classeAggiuntiva){
        $(elementoDOM).toggleClass(classeAggiuntiva);
    }
    
    $(".increaseFont, .decreaseFont, .normalFont").click(function(){
       var type = $(this).val();
       
        if(type === 'increase') {
            rimozioneClassi();
            
            aggiuntaClasse("h2", "testoXXXLarge");
            aggiuntaClasse("h3", "testoXXLarge");
            aggiuntaClasse("h4, .testoS", "testoXLarge");
            aggiuntaClasse(".testoN", "testoLarge");
            
            //Segnalazione del bottone per il cambio del font attualmente attivo
            $("button").attr('id', ' ');
            $(".increaseFont").attr('id', 'activeButton');
            
        } else if(type === 'decrease') {
            rimozioneClassi();
            
            aggiuntaClasse("h2", "testoXLarge");
            aggiuntaClasse("h3", "testoLarge");
            aggiuntaClasse("h4, .testoS", "testoMedium");
            aggiuntaClasse(".testoN", "testoSmall");
            
            //Segnalazione del bottone per il cambio del font attualmente attivo
            $("button").attr('id', ' ');
            $(".decreaseFont").attr('id', 'activeButton');
            
        } else if(type === 'normal') {
            rimozioneClassi();
            
            aggiuntaClasse("h2", "testoXXLarge");
            aggiuntaClasse("h3", "testoXLarge");
            aggiuntaClasse("h4, .testoS", "testoLarge");
            aggiuntaClasse(".testoN", "testoMedium");
            
            //Segnalazione del bottone per il cambio del font attualmente attivo
            $("button").attr('id', ' ');
            $(".normalFont").attr('id', 'activeButton');
        }
    });
    
 });