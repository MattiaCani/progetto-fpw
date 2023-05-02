package it.unica.nextech.model;

import it.unica.nextech.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory per la comunicazione sugli Articoli con il database.
 * @author nextech
 */
public class ArticleFactory {
    private static ArticleFactory instance;
    
    public static ArticleFactory getInstance(){
        if(instance == null)
            instance = new ArticleFactory();
        return instance;
    }
    
    /**
     * Interroga il database e restituisce tutti gli articoli presenti in esso.
     * @return Una lista di articoli
     */
    public List<Article> getAllArticoli(){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        
        List<Article> articoli = new ArrayList<>();
        
        try{
            conn = DatabaseManager.getInstance().getDbConnection();
            
            String query = "SELECT * FROM articolo JOIN immagine ON articolo.immagineArticolo = immagine.percorso";
            
            stmt = conn.prepareStatement(query);
            set = stmt.executeQuery();
            
            while(set.next()){
                
                Article articolo = new Article();
                articolo.setId_articolo(set.getLong("id_articolo"));
                articolo.setId_utente(set.getString("id_utente"));
                articolo.setTitolo(set.getString("titolo"));
                articolo.setSottotitolo(set.getString("sottotitolo"));
                articolo.setTesto(set.getString("testo"));
                articolo.setImmagineArticolo(set.getString("immagineArticolo"));
                
                Immagine img = new Immagine();
                img.setPercorso(set.getString("percorso"));
                img.setNomeFile(set.getString("nomeFile"));
                img.setDescrizioneImg(set.getString("descrizioneImg"));
                
                articolo.setInfoImmagine(img);
                
                articoli.add(articolo);
            }
            
            return articoli;
            
        } catch(SQLException e){
            Logger.getLogger(EventFactory.class.getName())
                    .log(Level.SEVERE, null, e);
            
        } finally{
            try { set.close(); } catch(Exception e) {}
            try { stmt.close(); } catch(Exception e) {}
            try { conn.close(); } catch(Exception e) {}
        }
        
        return null;
    }
    
     /**
     * Aggiunge un articolo al database.
     * @param articolo
     * @return True se l'articolo viene aggiunto con successo, False altrimenti
     */
    public boolean insertArticle(Article articolo){
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = DatabaseManager.getInstance().getDbConnection();
            
            String update = "INSERT INTO articolo (id_articolo, id_utente, titolo, sottotitolo, testo, immagineArticolo)"
                    + "VALUES (default, ?, ?, ?, ?, ?)";
            
            stmt = conn.prepareStatement(update);
            stmt.setString(1, articolo.getId_utente());
            stmt.setString(2, articolo.getTitolo());
            stmt.setString(3, articolo.getSottotitolo());
            stmt.setString(4, articolo.getTesto());
            stmt.setString(5, articolo.getImmagineArticolo());
            
            int success = stmt.executeUpdate();
            
            return success == 1; 
            
        } catch(SQLException e){
            Logger.getLogger(EventFactory.class.getName())
                    .log(Level.SEVERE, null, e);
            
        } finally {
            try { stmt.close(); } catch(Exception e) {}
            try { conn.close(); } catch(Exception e) {}
        }
        
        return false;
    }
}