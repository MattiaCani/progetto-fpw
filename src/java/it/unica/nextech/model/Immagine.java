package it.unica.nextech.model;

import java.util.Objects;

/**
 * Immagine inseribile da un utente, come foto profilo o immagine evento.
 * @author nextech
 */
public class Immagine {
    private String percorso;
    private String nomeFile;
    private String descrizioneImg;
    
    public Immagine() {}
    public Immagine(String percorso, String nomeFile, String descrizione){
        
        this.percorso = percorso;
        this.nomeFile = nomeFile;
        this.descrizioneImg = descrizione;
    }

    public String getPercorso() {
        return percorso;
    }

    public void setPercorso(String percorso) {
        this.percorso = percorso;
    }

    public String getNomeFile() {
        return nomeFile;
    }

    public void setNomeFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    public String getDescrizioneImg() {
        return descrizioneImg;
    }

    public void setDescrizioneImg(String descrizione) {
        this.descrizioneImg = descrizione;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.percorso);
        return hash;
    }

    //Due immagini sono uguali se hanno lo stesso percorso.
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
        final Immagine other = (Immagine) obj;
        return Objects.equals(this.percorso, other.percorso);
    }

   
   
}
