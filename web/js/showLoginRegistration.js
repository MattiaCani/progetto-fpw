//Aggiunge o rimuove una classe che permette di nascondere il form di login o di registrazione
$(document).ready(function(){
    
    $(".showLogin, .showRegistration").click(function(){
       var type= $(this).val();
       
        if(type === 'login'){
            $("[id=sign-up]").toggleClass("hideBox");
            $("[id=login]").toggleClass("hideBox");
            
        } else if(type === 'registration'){
            
            $("[id=login]").toggleClass("hideBox");
            $("[id=sign-up]").toggleClass("hideBox");
        }
    });
 });