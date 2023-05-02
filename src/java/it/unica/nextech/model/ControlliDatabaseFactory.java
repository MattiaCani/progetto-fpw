package it.unica.nextech.model;

import it.unica.nextech.db.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory per svariati controlli al Database.
 * @author nextech
 */
public class ControlliDatabaseFactory {
    private static ControlliDatabaseFactory instance;
    
    private ControlliDatabaseFactory(){}
    
    public static ControlliDatabaseFactory getInstance(){
        if(instance == null)
            instance = new ControlliDatabaseFactory();
        return instance;
    }
    
    /**
     * Controlla se il database contiene già l'oggetto passato in input.
     * @param tipo               Tipo di oggetto (Utente, Evento, Articolo)
     * @param campoUguaglianza   Campo dell'oggetto da cercare nel database per stabilire la presenza
     * @return                   True se il database al suo interno ha già l'elemento, False altrimenti
     */
    public boolean hasElement(String tipo, String campoUguaglianza){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        
        String tipoOggetto = null;
        String confrontoCampo = null;
                
        try{
            conn = DatabaseManager.getInstance().getDbConnection();
                        
            switch(tipo){
                case "User":
                    tipoOggetto = "utente";
                    confrontoCampo = "username";
                    break;
                case "Event":
                    tipoOggetto = "evento";
                    confrontoCampo = "titolo";
                    break;
                case "Article":
                    tipoOggetto = "articolo";
                    confrontoCampo = "titolo";
                    break;
            }
            
            String query = "SELECT * FROM " + tipoOggetto + " WHERE " + confrontoCampo + " = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, campoUguaglianza);
            
            set = stmt.executeQuery();
            
            if(set.next())
                return true;
                        
        } catch(SQLException e){
            Logger.getLogger(EventFactory.class.getName())
                    .log(Level.SEVERE, null, e);
            
        } finally {
            try { stmt.close(); } catch(Exception e) {}
            try { conn.close(); } catch(Exception e) {}
            try { set.close(); } catch(Exception e) {}
        }
        
        return false;
    }
}
