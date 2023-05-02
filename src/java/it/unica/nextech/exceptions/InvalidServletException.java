package it.unica.nextech.exceptions;

/**
 * Eccezione lanciata quando l'utente inserisce manualmente una Servlet nell'URL.
 * @author nextech
 */
public class InvalidServletException extends Exception {
    
    public InvalidServletException(){
        super("Accesso alla servlet non consentito");
    }
}