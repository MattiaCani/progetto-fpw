$(document).ready(function(){   
    
    //Regex per determinare i vincoli dei vari input
    var regexSoloCaratteri = /^[a-zA-Z\s]*$/;           //Ammessi solo caratteri e spazi
    var regexEmail = /^$|\S+@\S+\.\S+/;                 //Controlla che abbia i tipici @ e . di una email 
    var regexNumeroTelefono = /^$|^[0-9]+$/;            //Ammessi solo numeri
    
    //Messaggi di errore nel caso nel quale degli input non rispettano i vincoli
    var erroreCaratteri = " non puo' contenere numeri o caratteri speciali";
    var erroreEmail = " non usa una sintassi corretta";
    var erroreNumeroTelefono = " puo' contenere solo numeri";
    
    //Impedisce all'utente di inserire ulteriori caratteri una volta raggiunto il limite
    //Visualizza a schermo anche il conteggio dei caratteri per ogni input
    function controlloNumCaratteri(numCaratteri){
        return function (e){
            var key = e.keyCode || e.charCode;
            
            var nomeStampa = $(this).attr("id") + "Conteggio";

            if($(this).val().length >= numCaratteri)
                if(!(key === 46 || key === 8)) 
                    e.preventDefault();
            
            $(this).keyup(function(){        
                $("#" + nomeStampa).text($(this).val().length);
            });
        };
    }
    $("#numeroTelefono").on("keydown", controlloNumCaratteri(10));
    $(".cento").on("keydown", controlloNumCaratteri(100));
    $(".duecento").on("keydown", controlloNumCaratteri(200));
    $("#usernameLogin, #username").on("keydown", controlloNumCaratteri(50));
    $("#passwordLogin, #password").on("keydown", controlloNumCaratteri(30));
    
    //Controlla che l'input dell'utente sia coerente con la regex specificata
    //In caso contrario inserisce sotto l'input utente un avviso che indica l'errore
    //Inoltre fin quando è presente l'avviso, l'input sarà contrassegnato con la classe error
    function validaInput(regex, tipoErrore){
        return function () {
            var nome = $(this).attr("id");
            var nomeErrore = "errore" + nome;
        
            if(!$(this).val().match(regex)){

                $(this).addClass("error");

                if($("#" + nomeErrore).length === 0) {
                    $(this).after("<p id='" + nomeErrore + "'>" + nome.substring(0,1).toUpperCase() + nome.substring(1).toLowerCase() + tipoErrore + "</p>");
                    $("#" + nomeErrore).toggleClass("erroreInput testoN testoMedium");
                } 

            } else {
                $(this).removeClass("error");
                $("#" + nomeErrore).remove();
            }
        };   
    }
    $("#nome").on("change input", validaInput(regexSoloCaratteri, erroreCaratteri));
    $("#cognome").on("change input", validaInput(regexSoloCaratteri, erroreCaratteri));
    $("#citta").on("change input", validaInput(regexSoloCaratteri, erroreCaratteri));
    $("#email").on("change input", validaInput(regexEmail, erroreEmail));
    $("#numeroTelefono").on("change input", validaInput(regexNumeroTelefono, erroreNumeroTelefono));

    //Controllano se l'utente ha inserito i dati in tutti gli input
    //Se l'utente ha tralasciato un inserimento lo avvisa con un alert e non permette l'invio dei dati
    //La registrazione di un utente inoltre controlla se sono presenti degli errori dovuti a qualche campo con particolari requisiti
    //Se trova errori allora non permette all'utente di inviare i dati e lo avvisa con un alert
    $("#sign-up").submit(function(e){
       $("#sign-up input").each(function(index){
            if(!$("#sign-up input").eq(index).val()){
                alert("Hai lasciato dei campi vuoti !");
                e.preventDefault();
                return false;
            }   
        }); 
        
        if(!($(".error").val().length === 0)){
         
           alert("Alcuni campi contengono caratteri non ammessi !");
           e.preventDefault();
       } 
    });
    
    $("#login").submit(function (e){
        $("#login input").each(function(index){
            if(!$("#login input").eq(index).val()){
                alert("Hai lasciato dei campi vuoti !");
                e.preventDefault();
                return false;
            }   
        });
    });
    
});





