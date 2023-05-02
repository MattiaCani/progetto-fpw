package it.unica.nextech.servlet;

import it.unica.nextech.exceptions.*;
import it.unica.nextech.model.ControlliDatabaseFactory;
import it.unica.nextech.model.Event;
import it.unica.nextech.model.EventFactory;
import it.unica.nextech.model.Immagine;
import it.unica.nextech.model.ImmagineFactory;
import it.unica.nextech.model.User;
import it.unica.nextech.tools.Utility;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Autenticazione dei valori inseriti per la creazione di un nuovo evento.
 * Se la servlet viene chiamata tramite inserimento manuale nell'URL da parte
 * dell'utente allora rimanda all'index. Se viene chiamata in maniera legittima
 * fa la validazione dei dati inseriti. Se ci sono dati non corrispondenti alle 
 * specifiche chiama un eccezione e reindirizza alla pagina di errore. 
 * @author nextech
 */
@WebServlet(name = "NuovoEventoServlet", urlPatterns = {"/NuovoEventoServlet"})
@MultipartConfig
public class NuovoEventoServlet extends HttpServlet {

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
        
        HttpSession session = request.getSession(false);
        
        if(session == null || session.getAttribute("userData") == null){
            
            request.setAttribute("errorMessage", "Sessione scaduta");
            request.getRequestDispatcher("erroreInserimento.jsp").forward(request, response);
            
        } else {
            
            request.setCharacterEncoding("utf-8");
            
            String titolo = request.getParameter("titoloEvento");
            String descrizione = request.getParameter("descrizione");
            String organizzatore = request.getParameter("organizzatore");
            String luogo = request.getParameter("luogo");
            
            String descrizioneImmagine = request.getParameter("descrizioneImmagineEvento");
            
            try{
                request.getPart("file");
            } catch(ServletException e) {
                response.sendRedirect("index.jsp");
                return;
            }
            
            Part file = request.getPart("file");

            try(InputStream contenutoFile = file.getInputStream())  {
                
                //Da rivedere
                int lunghezzaData = 16;
                
                Utility.checkString("titolo", titolo, Utility.STRING_LENGTH);
                Utility.checkString("descrizione", descrizione, Utility.TESTO_LENGTH);
                Utility.checkString("organizzatore", organizzatore, Utility.STRING_LENGTH);
                Utility.checkString("luogo", luogo, Utility.STRING_LENGTH);
                
                Utility.checkString("dataInizio", request.getParameter("dataInizio"), lunghezzaData);
                Utility.checkString("dataFine", request.getParameter("dataFine"), lunghezzaData);
                LocalDateTime dataInizio = LocalDateTime.parse(request.getParameter("dataInizio"));
                LocalDateTime dataFine = LocalDateTime.parse(request.getParameter("dataFine"));

                Utility.checkNull("costo", request.getParameter("costo"));
                Double costo = Double.valueOf(request.getParameter("costo"));
                Utility.checkNumber("costo", costo);
                
                Utility.checkString("descrizioneImmagine", descrizioneImmagine, Utility.DESCRIZIONE_IMMAGINE);
                
                if(ControlliDatabaseFactory.getInstance().hasElement("Event", titolo))
                    throw new InvalidParamException("È già presente un evento con lo stesso nome");
                
                User utenteAttuale = (User) session.getAttribute("userData");
                
                String servletPath = getServletContext().getRealPath("/");
                
                new File(servletPath.substring(0, servletPath.length() - 10) + "web/uploads/" + utenteAttuale.getUsername() + "/eventi/").mkdirs();
                String[] fileSplit = file.getSubmittedFileName().split("\\.(?=[^\\.]+$)");
                
                File daSalvare = File.createTempFile(fileSplit[0], "." + fileSplit[1], new File(servletPath.substring(0, servletPath.length() - 10) + "web/uploads/" + utenteAttuale.getUsername() + "/eventi/"));
                Files.copy(contenutoFile, daSalvare.toPath(), StandardCopyOption.REPLACE_EXISTING);
                
                String URL = "uploads/" + utenteAttuale.getUsername() + "/eventi/" + daSalvare.getName();
                                
                Event evento = new Event(utenteAttuale.getUsername(), titolo, descrizione, organizzatore, 
                        luogo, dataInizio, dataFine, costo, URL);
                
                ImmagineFactory.getInstance().addImmagine(new Immagine(URL,  daSalvare.getName(), descrizioneImmagine));

                if(EventFactory.getInstance().insertEvent(evento))
                    response.sendRedirect("inserimentoConfermato.jsp");
                else
                    throw new InvalidParamException("Si è verificato un problema con il Database, contattare l'amministratore");

            } catch(InvalidParamException e){

                session.setAttribute("errorMessage", e.getMessage());
                response.sendRedirect("erroreInserimento.jsp");

            } catch(InvalidServletException x) {

                response.sendRedirect("index.jsp");

            } catch(NumberFormatException x){

                session.setAttribute("errorMessage", "Valore di costo non valido");
                response.sendRedirect("error.jsp");

                //L'immagine non è stata inserita
            } catch(ArrayIndexOutOfBoundsException e) {
            
                session.invalidate();
                request.setAttribute("errorMessage", "Attenzione - Immagine non inserita correttamente");
                request.getRequestDispatcher("erroreInserimento.jsp").forward(request, response);
            }
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