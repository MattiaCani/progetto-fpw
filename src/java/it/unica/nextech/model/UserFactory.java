package it.unica.nextech.model;

import it.unica.nextech.db.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory per la comunicazione sugli Utenti con il database.
 * @author nextech
 */
public class UserFactory {
    private static UserFactory instance;
    
    private UserFactory(){}
    
    public static UserFactory getInstance(){
        if(instance == null)
            instance = new UserFactory();
        return instance;
    }
    
    /**
     * Interroga il database e restituisce l'utente corrispondente alle credenziali inserite.
     * Se non trova un utente, vuol dire che le credenziali sono sbagliate e ritorna null.
     * @param username
     * @param password
     * @return L'utente corrispondente alle credenziali oppure Null se non è presente l'utente
     */
    public User getUtenteByUsernamePassword(String username, String password){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        
        try{
            conn = DatabaseManager.getInstance().getDbConnection();
            
            String query = "SELECT * FROM utente JOIN immagine ON utente.fotoProfilo = immagine.percorso WHERE username = ? AND password = ? ";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            set = stmt.executeQuery();
            
            if(set.next()){
                
                User utente = new User();
                utente.setUsername(set.getString("username"));
                utente.setPassword(set.getString("password"));
                utente.setNome(set.getString("nome"));
                utente.setCognome(set.getString("cognome"));
                utente.setEmail(set.getString("email"));
                utente.setNumeroTelefono(set.getString("numeroTelefono"));
                utente.setRegione(set.getString("regione"));
                utente.setCitta(set.getString("citta"));
                utente.setArgomentoCompetenza(set.getString("argomentoCompetenza"));
                utente.setTitoloStudio(set.getString("titoloStudio"));
                utente.setImpiegoAttuale(set.getString("impiegoAttuale"));
                utente.setFotoProfilo(set.getString("fotoProfilo"));
                
                Immagine img = new Immagine();
                img.setPercorso(set.getString("percorso"));
                img.setNomeFile(set.getString("nomeFile"));
                img.setDescrizioneImg(set.getString("descrizioneImg"));
                
                utente.setInfoImmagine(img);
                
                return utente;
                
            } else {
                return null;
            }
            
        } catch(SQLException e){
            Logger.getLogger(UserFactory.class.getName())
                    .log(Level.SEVERE, null, e);
            
        } finally{
            try { set.close(); } catch(Exception e) {}
            try { stmt.close(); } catch(Exception e) {}
            try { conn.close(); } catch(Exception e) {}
        }
        
        return null;
    }
    
    /**
     * Aggiunge un utente al database.
     * @param utente
     * @return True se l'aggiunta avviene con successo, False altrimenti
     */
    public boolean insertUser(User utente){
        Connection conn = null;
        PreparedStatement stmt = null;
                
        try{
            conn = DatabaseManager.getInstance().getDbConnection();
                        
            String update = "INSERT INTO utente (username, password, nome, cognome, "
                    + "email, numeroTelefono, regione, citta, argomentoCompetenza, titoloStudio, impiegoAttuale, fotoProfilo)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            stmt = conn.prepareStatement(update);
            stmt.setString(1, utente.getUsername());
            stmt.setString(2, utente.getPassword());
            stmt.setString(3, utente.getNome());
            stmt.setString(4, utente.getCognome());
            stmt.setString(5, utente.getEmail());
            stmt.setString(6, utente.getNumeroTelefono());
            stmt.setString(7, utente.getRegione());
            stmt.setString(8, utente.getCitta());
            stmt.setString(9, utente.getArgomentoCompetenza());
            stmt.setString(10, utente.getTitoloStudio());
            stmt.setString(11, utente.getImpiegoAttuale());
            stmt.setString(12, utente.getFotoProfilo());
            
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