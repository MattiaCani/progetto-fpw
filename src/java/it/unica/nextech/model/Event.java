package it.unica.nextech.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Evento inseribile da un utente registrato, gli eventi vengono visualizzati nella pagina elencoEventi. 
 * @author nextech
 */
public class Event {
    private long id_evento;
    private String id_utente;
    
    private String titolo;
    private String descrizione;
    private String organizzatore;
    private String luogo;
    private LocalDateTime dataInizio; 
    private LocalDateTime dataFine;
    private Double costo;
    private String immagineEvento;
    
    private Immagine infoImmagine;
    
    public Event(){}
    
    public Event(String id_utente, String titolo, String descrizione, String organizzatore,
            String luogo, LocalDateTime dataInizio, LocalDateTime dataFine, Double costo, String immagineEvento){
    
        this.id_utente = id_utente;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.organizzatore = organizzatore;
        this.luogo = luogo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.costo = costo;
        this.immagineEvento = immagineEvento;
    }

    public String getImmagineEvento() {
        return immagineEvento;
    }

    public void setImmagineEvento(String immagineEvento) {
        this.immagineEvento = immagineEvento;
    }
    

    public long getId_evento() {
        return id_evento;
    }

    public void setId_evento(long id_evento) {
        this.id_evento = id_evento;
    }

    public String getId_utente() {
        return id_utente;
    }

    public void setId_utente(String id_utente) {
        this.id_utente = id_utente;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getOrganizzatore() {
        return organizzatore;
    }

    public void setOrganizzatore(String organizzatore) {
        this.organizzatore = organizzatore;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Immagine getInfoImmagine() {
        return infoImmagine;
    }

    public void setInfoImmagine(Immagine infoImmagine) {
        this.infoImmagine = infoImmagine;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.titolo);
        return hash;
    }

    //Due eventi sono uguali se hanno lo stesso titolo
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
        final Event other = (Event) obj;
        return Objects.equals(this.titolo, other.titolo);
    }
        
}