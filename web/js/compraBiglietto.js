//Alert che si attiva al click per comprare un biglietto
$(document).ready(function(){
    
    $(".compraBiglietto").click(function(){
       var type= $(this).val();
       
        if(type === 'compra'){
            alert("Grazie per l'acquisto!\nIl tuo biglietto elettronico è stato spedito alla tua e-mail!");
        }
    });
 });


