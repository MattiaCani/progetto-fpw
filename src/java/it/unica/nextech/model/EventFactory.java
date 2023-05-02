package it.unica.nextech.model;

import it.unica.nextech.db.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


/**
 * Factory per la comunicazione sugli Eventi con il database.
 * @author nextech
 */
public class EventFactory {
    private static EventFactory instance;
    
    public static EventFactory getInstance(){
        if(instance == null)
            instance = new EventFactory();
        return instance;
    }
    
    /**
     * Interroga il database e restituisce tutti gli eventi presenti in esso.
     * @return Una lista di eventi.
     */
    public List<Event> getAllEventi(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        
        List<Event> eventi = new ArrayList<>();
        
        try{
            conn = DatabaseManager.getInstance().getDbConnection();
            
            String query = "SELECT * FROM evento JOIN immagine ON evento.immagineEvento = immagine.percorso";
            
            stmt = conn.prepareStatement(query);
            set = stmt.executeQuery();
            
            while(set.next()){
                
                Event evento = new Event();
                evento.setId_evento(set.getLong("id_evento"));
                evento.setId_utente(set.getString("id_utente"));
                evento.setTitolo(set.getString("titolo"));
                evento.setDescrizione(set.getString("descrizione"));
                evento.setOrganizzatore(set.getString("organizzatore"));
                evento.setLuogo(set.getString("luogo"));
                
                Timestamp ts1 = set.getTimestamp("dataInizio");
                Timestamp ts2 = set.getTimestamp("dataFine");
                LocalDateTime localDt1 = null, localDt2 = null;
                if(ts1 != null)
                    localDt1 = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts1.getTime()), ZoneOffset.UTC);
                if(ts2 != null)
                    localDt2 = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts2.getTime()), ZoneOffset.UTC);
                
                evento.setDataInizio(localDt1);
                evento.setDataFine(localDt2);
                
                evento.setCosto(set.getDouble("costo"));
                evento.setImmagineEvento(set.getString("immagineEvento"));
                
                Immagine img = new Immagine();
                img.setPercorso(set.getString("percorso"));
                img.setNomeFile(set.getString("nomeFile"));
                img.setDescrizioneImg(set.getString("descrizioneImg"));
                
                evento.setInfoImmagine(img);
                
                eventi.add(evento);
            }
            
            return eventi;
            
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
     * Aggiunge un evento al database.
     * @param evento 
     * @return True se l'aggiunta avviene con successo, False altrimenti
     */
    public boolean insertEvent(Event evento){
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = DatabaseManager.getInstance().getDbConnection();
                 
            String update = "INSERT INTO evento (id_evento, id_utente, titolo, descrizione, "
                    + "organizzatore, luogo, dataInizio, dataFine, costo, immagineEvento)"
                    + "VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            stmt = conn.prepareStatement(update);
            stmt.setString(1, evento.getId_utente());
            stmt.setString(2, evento.getTitolo());
            stmt.setString(3, evento.getDescrizione());
            stmt.setString(4, evento.getOrganizzatore());
            stmt.setString(5, evento.getLuogo());
            
            Timestamp ts1 = Timestamp.valueOf(evento.getDataInizio());
            Timestamp ts2 = Timestamp.valueOf(evento.getDataFine());
            stmt.setTimestamp(6, ts1);
            stmt.setTimestamp(7, ts2);
            
            stmt.setDouble(8, evento.getCosto());
            stmt.setString(9, evento.getImmagineEvento());
            
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
    