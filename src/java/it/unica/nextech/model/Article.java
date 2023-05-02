package it.unica.nextech.model;

import java.util.Objects;

/**
 * Articolo inseribile da un utente registrato, gli articoli vengono visualizzati nell'index. 
 * @author nextech
 */
public class Article {
    private long id_articolo;
    private String id_utente;
    
    private String titolo;
    private String sottotitolo;
    private String testo;
    private String immagineArticolo;
    
    private Immagine infoImmagine;
    
    public Article(){}
    
    public Article(String id_utente, String titolo, String sottotitolo, String testo, String immagineArticolo){
        
        this.id_utente = id_utente;
        this.titolo = titolo; 
        this.sottotitolo = sottotitolo;
        this.testo = testo;
        this.immagineArticolo = immagineArticolo;
    }

    public long getId_articolo() {
        return id_articolo;
    }

    public void setId_articolo(long id_articolo) {
        this.id_articolo = id_articolo;
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

    public String getSottotitolo() {
        return sottotitolo;
    }

    public void setSottotitolo(String sottotitolo) {
        this.sottotitolo = sottotitolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getImmagineArticolo() {
        return immagineArticolo;
    }

    public void setImmagineArticolo(String immagineArticolo) {
        this.immagineArticolo = immagineArticolo;
    }

    public Immagine getInfoImmagine() {
        return infoImmagine;
    }

    public void setInfoImmagine(Immagine infoImmagine) {
        this.infoImmagine = infoImmagine;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.titolo);
        return hash;
    }

    //Due articoli sono uguali se hanno lo stesso titolo
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
        final Article other = (Article) obj;
        return Objects.equals(this.titolo, other.titolo);
    }

}