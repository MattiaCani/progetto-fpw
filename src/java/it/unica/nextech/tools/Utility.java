package it.unica.nextech.tools;

import it.unica.nextech.exceptions.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Vari metodi statici utili a fare la validazione dei parametri inseriti da un utente.
 * @author nextech
 */
public class Utility {
    public static final int USERNAME_LENGTH = 50, PASSWORD_LENGTH = 30, STRING_LENGTH = 100, NUMEROTELEFONO_LENGTH = 10, 
            TESTO_LENGTH = 5000, IMMAGINE_LENGTH = 500, DESCRIZIONE_IMMAGINE = 200;
       
    /**
     * Controlla i valori del form.
     * Se sono null (Servlet inserita manualmente dall'utente nell'url) lancia un eccezione che verrà usata per reindirizzare alla home.
     * Se sono vuoti (L'utente ha lasciato vuoti dei campi) lancia un eccezione con il problema come messaggio.
     * @param paramName
     * @param param
     * @throws InvalidParamException
     * @throws InvalidServletException 
     */
    public static void checkNull(String paramName, String param) throws InvalidParamException, InvalidServletException {
        if(param == null)
            throw new InvalidServletException();
        else if(param.isEmpty())
            throw new InvalidParamException("Valore " + printName(paramName, false) + " mancante");   
    }
      
    /**
     * Controlla se il numero è compreso tra il range specificato.
     * @param paramName
     * @param param
     * @throws InvalidParamException
     * @throws InvalidServletException 
     */
    public static void checkNumber(String paramName, Double param) throws InvalidParamException, InvalidServletException {
        checkNull(paramName, param.toString());
        
        double min = 0;
        double max = 10000;
        
        if(param <= min || param > max)
            throw new InvalidParamException(printName(paramName, true) + " deve essere maggiore di " + min + " e minore di " + max);
    }
    
    /**
     * Controlla se la stringa è minore di una certa lunghezza.
     * @param paramName
     * @param param
     * @param maxLength
     * @throws InvalidParamException
     * @throws InvalidServletException 
     */
    public static void checkString(String paramName, String param, int maxLength) throws InvalidParamException, InvalidServletException {
        checkNull(paramName, param);
        
        if(param.length() > maxLength)
            throw new InvalidParamException(printName(paramName, true) + " non deve superare " + maxLength + " caratteri");
    }
    
    /**
     * Controlla se nella stringa è presente qualcosa di diverso da dei caratteri o degli spazi.
     * Se trova qualcosa allora chiama l'eccezione.
     * @param paramName
     * @param param
     * @param maxLength
     * @throws InvalidParamException
     * @throws InvalidServletException 
     */
    public static void checkOnlyChar(String paramName, String param, int maxLength) throws InvalidParamException, InvalidServletException {
        checkString(paramName, param, maxLength);
        
        if(!param.matches("[a-zA-Z ]+"))
            throw new InvalidParamException(printName(paramName, true) + " non può contenere numeri o caratteri speciali. Inserire solo testo");
    }
    
    /**
     * Controllo con regex per un email corretta.
     * @param paramName
     * @param param
     * @param maxLength
     * @throws InvalidParamException
     * @throws InvalidServletException 
     */
    public static void checkEmail(String paramName, String param, int maxLength) throws InvalidParamException, InvalidServletException {
        checkString(paramName, param, maxLength);
        
        if(!param.matches("\\S+@\\S+\\.\\S+"))
            throw new InvalidParamException("La sintassi della " + printName(paramName, false) + " non è corretta");
    }
    
    /**
     * Controlla se nella stringa sono presenti solo numeri.
     * Se trova qualcosa allora chiama l'eccezione.
     * @param paramName
     * @param param
     * @param maxLength
     * @throws InvalidParamException
     * @throws InvalidServletException 
     */
    public static void checkOnlyNum(String paramName, String param, int maxLength) throws InvalidParamException, InvalidServletException {
        checkString(paramName, param, maxLength);
        
        if(!param.matches("^[0-9]+$"))
            throw new InvalidParamException(printName(paramName, true) + " può contenere solo numeri, non inserire testo");
    }

    /**
     * Utile per la gestione del tempo
     * @param time
     * @return 
     */
    public static String convertTime(long time){
        LocalDateTime localDt = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneOffset.UTC);
            return localDt.toString();
    }
    
    /**
     * Conversione di un eventuale nome scritto in camel case in un formato facilmente leggibile da un utente.
     * In base al valore del booleano si decide se mettere o no l'iniziale maiuscola.
     * @param paramName
     * @param capitalizzare
     * @return 
     */
    private static String printName(String paramName, boolean capitalize){
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1 $2";
        
        paramName = paramName.replaceAll(regex, replacement);
        
        if(capitalize)
            return paramName.substring(0,1).toUpperCase() + paramName.substring(1).toLowerCase();
        else
            return paramName.toLowerCase();
    }
    
    
}