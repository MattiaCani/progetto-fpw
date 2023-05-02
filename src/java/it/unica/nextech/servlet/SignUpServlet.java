package it.unica.nextech.servlet;

import it.unica.nextech.exceptions.*;
import it.unica.nextech.model.ControlliDatabaseFactory;
import it.unica.nextech.model.Immagine;
import it.unica.nextech.model.ImmagineFactory;
import it.unica.nextech.model.User;
import it.unica.nextech.model.UserFactory;
import it.unica.nextech.tools.Utility;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Autenticazione dei valori di SignUp inseriti dall'utente.
 * Se la servlet viene chiamata tramite inserimento manuale nell'URL da parte
 * dell'utente allora rimanda all'index. Se viene chiamata in maniera legittima
 * fa la validazione dei dati inseriti. Se ci sono dati non corrispondenti alle 
 * specifiche chiama un eccezione e reindirizza alla pagina di errore. 
 * @author nextech
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
@MultipartConfig
public class SignUpServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        request.setCharacterEncoding("utf-8");
                
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email"); 
        String numeroTelefono = request.getParameter("numeroTelefono");
        String regione = request.getParameter("regione");
        String citta = request.getParameter("citta");
        String argomentoCompetenza = request.getParameter("argomentoCompetenza");
        String titoloStudio = request.getParameter("titoloStudio");
        String impiegoAttuale = request.getParameter("impiegoAttuale");
        
        String descrizioneImmagine = request.getParameter("descrizioneImmagine");
        
        try{
            request.getPart("file");
        } catch(ServletException e) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        Part file = request.getPart("file");

        try(InputStream contenutoFile = file.getInputStream()) {
            
            Utility.checkOnlyChar("nome", nome, Utility.STRING_LENGTH);
            Utility.checkOnlyChar("cognome", cognome, Utility.STRING_LENGTH);
            Utility.checkString("username", username, Utility.USERNAME_LENGTH);
            Utility.checkString("password", password, Utility.PASSWORD_LENGTH);
            Utility.checkEmail("email", email, Utility.STRING_LENGTH);
            Utility.checkOnlyChar("citta", citta, Utility.STRING_LENGTH);
            Utility.checkString("titoloStudio", titoloStudio, Utility.STRING_LENGTH);
            Utility.checkOnlyNum("numeroTelefono", numeroTelefono, Utility.NUMEROTELEFONO_LENGTH);
            Utility.checkString("impiegoAttuale", impiegoAttuale, Utility.STRING_LENGTH);
            Utility.checkString("descrizioneImmagine", descrizioneImmagine, Utility.DESCRIZIONE_IMMAGINE);
            
            if(ControlliDatabaseFactory.getInstance().hasElement("User", username))
                throw new InvalidParamException("L'utente inserito è già registrato");
                        
            String servletPath = getServletContext().getRealPath("/");
                
            new File(servletPath.substring(0, servletPath.length() - 10) + "web/uploads/" + username + "/").mkdirs();
            String[] fileSplit = file.getSubmittedFileName().split("\\.(?=[^\\.]+$)");
                
            File daSalvare = File.createTempFile(fileSplit[0], "." + fileSplit[1], new File(servletPath.substring(0, servletPath.length() - 10) + "web/uploads/" + username + "/"));
            Files.copy(contenutoFile, daSalvare.toPath(), StandardCopyOption.REPLACE_EXISTING);

            String URL = "uploads/" + username + "/" + daSalvare.getName();
            
            User utente = new User(username, password, nome, cognome, email, 
                    numeroTelefono, regione, citta, argomentoCompetenza, titoloStudio, impiegoAttuale, URL);
            
            ImmagineFactory.getInstance().addImmagine(new Immagine(URL,  daSalvare.getName(), descrizioneImmagine));
            
            if(UserFactory.getInstance().insertUser(utente))
                response.sendRedirect("registrazioneConfermata.jsp");
            else
                throw new InvalidParamException("Si è verificato un problema con il Database, contattare l'amministratore");
   
        } catch(InvalidParamException e){

            session.invalidate();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("erroreInserimento.jsp").forward(request, response);

        } catch(InvalidServletException e) {

            response.sendRedirect("index.jsp");
            
            //L'immagine non è stata inserita
        } catch(ArrayIndexOutOfBoundsException e) {
            
            session.invalidate();
            request.setAttribute("errorMessage", "Attenzione - Immagine non inserita correttamente");
            request.getRequestDispatcher("erroreInserimento.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}