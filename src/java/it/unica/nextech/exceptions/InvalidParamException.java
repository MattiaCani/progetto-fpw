package it.unica.nextech.exceptions;

/**
 * Eccezione lanciata quando l'utente inserisce valori non ammessi nel form.
 * @author nextech
 */
public class InvalidParamException extends Exception {
    
    public InvalidParamException(String errorMessage){
        super(errorMessage);
    }
}