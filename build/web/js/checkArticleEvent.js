$(document).ready(function(){
    
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
    $(".cento").on("keydown", controlloNumCaratteri(100));
    $(".duecento").on("keydown", controlloNumCaratteri(200));
    $("#descrizione, #testo").on("keydown", controlloNumCaratteri(5000));
      
    //Versione modificata di validaInput presente in checkUser.js per operare su un campo numerico
    //Controlla che l'input dell'utente sia coerente con la regex che accetta solo numeri e i numeri rientrino nel range desiderato
    //In caso contrario inserisce sotto l'input utente un avviso che indica l'errore e colora di rosso l'input
    //Inoltre fin quando è presente l'avviso, l'input sarà contrassegnato con la classe error
    $("#costo").on("change input", function(){
        
        if(!$(this).val().match(/^$^\d*\.?\d+$/) && !($(this).val() > 0.0 && $(this).val() <= 10000.0)){

            $(this).addClass("error");
            $(this).addClass("erroreInput");
            $(this).removeClass("inputCorretto");

            if($("#erroreCosto").length === 0) {
                $(this).after("<p id='erroreCosto'>Il prezzo puo' contenere solo numeri tra 1 e 10000</p>");
                $("#erroreCosto").toggleClass("erroreInput testoN testoMedium");
            } 

        } else {
            $(this).removeClass("error");
            $(this).removeClass("erroreInput");
            $(this).addClass("inputCorretto");
            $("#erroreCosto").remove();
        }
    });
    
    //Controllano se l'utente ha inserito i dati in tutti gli input
    //Se l'utente ha tralasciato un inserimento lo avvisa con un alert e non permette l'invio dei dati
    //La registrazione di un utente inoltre controlla se sono presenti degli errori dovuti a qualche campo con particolari requisiti
    //Se trova errori allora non permette all'utente di inviare i dati e lo avvisa con un alert
    $("#newEvent").submit(function(e){
       $("#newEvent input").each(function(index){
            if(!$("#newEvent input").eq(index).val()){
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
    
    $("#newArticle").submit(function (e){
        $("#newArticle input").each(function(index){
            if(!$("#newArticle input").eq(index).val()){
                alert("Hai lasciato dei campi vuoti !");
                e.preventDefault();
                return false;
            }   
        });
    });
      
});
