package it.unica.nextech.model;

import java.util.Objects;

/**
 * Utente, registrabile attraverso la pagina di login.
 * @author nextech
 */
public class User {
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String email;
    private String numeroTelefono;
    private String regione;
    private String citta;
    private String argomentoCompetenza;
    private String titoloStudio;
    private String impiegoAttuale;
    private String fotoProfilo;
    
    private Immagine infoImmagine;
    
    public User(){}

    public User(String username, String password, String nome, String cognome, String email, String numeroTelefono, 
            String regione, String citta, String argomentoCompetenza, String titoloStudio, String impiegoAttuale, String fotoProfilo) {
        
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.numeroTelefono = numeroTelefono;
        this.regione = regione;
        this.citta = citta;
        this.argomentoCompetenza = argomentoCompetenza;
        this.titoloStudio = titoloStudio;
        this.impiegoAttuale = impiegoAttuale;
        this.fotoProfilo = fotoProfilo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getArgomentoCompetenza() {
        return argomentoCompetenza;
    }

    public void setArgomentoCompetenza(String argomentoCompetenza) {
        this.argomentoCompetenza = argomentoCompetenza;
    }

    public String getTitoloStudio() {
        return titoloStudio;
    }

    public void setTitoloStudio(String titoloStudio) {
        this.titoloStudio = titoloStudio;
    }

    public String getImpiegoAttuale() {
        return impiegoAttuale;
    }

    public void setImpiegoAttuale(String impiegoAttuale) {
        this.impiegoAttuale = impiegoAttuale;
    }

    public String getFotoProfilo() {
        return fotoProfilo;
    }

    public void setFotoProfilo(String fotoProfilo) {
        this.fotoProfilo = fotoProfilo;
    }
    
    public Immagine getInfoImmagine() {
        return infoImmagine;
    }

    public void setInfoImmagine(Immagine infoImmagine) {
        this.infoImmagine = infoImmagine;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.username);
        return hash;
    }

    //Due utenti sono uguali se hanno lo stesso username
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.username, other.username);
    } 
}