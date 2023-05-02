package it.unica.nextech.model;

import it.unica.nextech.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory per la comunicazione sulle immagini col database.
 * @author nextech
 */
public class ImmagineFactory {
    private static ImmagineFactory instance;

    private ImmagineFactory(){}

    public static ImmagineFactory getInstance(){
        if(instance == null){
            instance = new ImmagineFactory();
        }

        return instance;
    }

    /**
     * Consente di aggiugnere un immagine al database.
     * @param image
     * @return True se l'aggiunta avviene con successo, False altrimenti
     */
    public boolean addImmagine(Immagine image){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            conn = DatabaseManager.getInstance().getDbConnection();
            
            String update = "INSERT INTO immagine VALUES (?, ?, ?)";
            
            stmt = conn.prepareStatement(update);
            stmt.setString(1, image.getPercorso());
            stmt.setString(2, image.getNomeFile());
            stmt.setString(3, image.getDescrizioneImg());
            
            int righe = stmt.executeUpdate();

            return righe == 1;
            
        } catch(SQLException e){
            
            Logger.getLogger(ImmagineFactory.class.getName())
                    .log(Level.SEVERE, null, e);
            
        } finally {
            try { set.close(); } catch(Exception e) {}
            try { stmt.close(); } catch(Exception e) {}
            try { conn.close(); } catch(Exception e) {}
        }
        
        return false;
    }

    /**
     * Restituisce le immagini presenti nel database.
     * @return Lista di immagini
     */
    public ArrayList<Immagine> getImmagini(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        
        ArrayList<Immagine> risultato = new ArrayList<>();
        
        try {
        
            conn = DatabaseManager.getInstance().getDbConnection();
            
            String query = "SELECT * FROM immagine";
            stmt = conn.prepareStatement(query);
            set = stmt.executeQuery();

            while(set.next()){
                
                Immagine img = new Immagine();
                img.setPercorso(set.getString("percorso"));
                img.setNomeFile(set.getString("nomeFile"));
                img.setDescrizioneImg(set.getString("descrizioneImg"));
                
                risultato.add(img);
            }
            
            return risultato;
            
        } catch(SQLException e){
            Logger.getLogger(ImmagineFactory.class.getName())
                    .log(Level.SEVERE, null, e);
            
        } finally {
            try { set.close(); } catch(Exception e) {}
            try { stmt.close(); } catch(Exception e) {}
            try { conn.close(); } catch(Exception e) {}
        }

        return null;
    }
}