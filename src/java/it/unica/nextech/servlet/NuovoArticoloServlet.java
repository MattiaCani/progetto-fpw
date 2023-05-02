package it.unica.nextech.servlet;

import it.unica.nextech.exceptions.*;
import it.unica.nextech.model.Article;
import it.unica.nextech.model.ArticleFactory;
import it.unica.nextech.model.ControlliDatabaseFactory;
import it.unica.nextech.model.Immagine;
import it.unica.nextech.model.ImmagineFactory;
import it.unica.nextech.model.User;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Autenticazione dei valori inseriti per la creazione di un nuovo articolo.
 * Se la servlet viene chiamata tramite inserimento manuale nell'URL da parte
 * dell'utente allora rimanda all'index. Se viene chiamata in maniera legittima
 * fa la validazione dei dati inseriti. Se ci sono dati non corrispondenti alle 
 * specifiche chiama un eccezione e reindirizza alla pagina di errore. 
 * @author nextech
 */
@WebServlet(name = "NuovoArticoloServlet", urlPatterns = {"/NuovoArticoloServlet"})
@MultipartConfig
public class NuovoArticoloServlet extends HttpServlet {

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
            
            String titolo = request.getParameter("titoloArticolo");
            String sottotitolo = request.getParameter("sottotitolo");
            String testo = request.getParameter("testo");
            
            String descrizioneImmagine = request.getParameter("descrizioneImmagineArticolo");
            
            try{
                request.getPart("file");
            } catch(ServletException e) {
                response.sendRedirect("index.jsp");
                return;
            }
            
            Part file = request.getPart("file");
            
            try(InputStream contenutoFile = file.getInputStream()) {

                Utility.checkString("titolo", titolo, Utility.STRING_LENGTH);
                Utility.checkString("sottotitolo", sottotitolo, Utility.IMMAGINE_LENGTH);
                Utility.checkString("testo", testo, Utility.TESTO_LENGTH);
                
                Utility.checkString("descrizioneImmagine", descrizioneImmagine, Utility.DESCRIZIONE_IMMAGINE);
                
                if(ControlliDatabaseFactory.getInstance().hasElement("Article", titolo))
                    throw new InvalidParamException("È già presente un articolo con lo stesso nome");
                
                User utenteAttuale = (User) session.getAttribute("userData");
                
                String servletPath = getServletContext().getRealPath("/");
                
                new File(servletPath.substring(0, servletPath.length() - 10) + "web/uploads/" + utenteAttuale.getUsername() + "/articoli/").mkdirs();
                String[] fileSplit = file.getSubmittedFileName().split("\\.(?=[^\\.]+$)");
                
                File daSalvare = File.createTempFile(fileSplit[0], "." + fileSplit[1], new File(servletPath.substring(0, servletPath.length() - 10) + "web/uploads/" + utenteAttuale.getUsername() + "/articoli/"));
                Files.copy(contenutoFile, daSalvare.toPath(), StandardCopyOption.REPLACE_EXISTING);
                
                String URL = "uploads/" + utenteAttuale.getUsername() + "/articoli/" + daSalvare.getName();
                
                Article articolo = new Article(utenteAttuale.getUsername(), titolo, sottotitolo, testo, URL);
                
                ImmagineFactory.getInstance().addImmagine(new Immagine(URL,  daSalvare.getName(), descrizioneImmagine));

                if(ArticleFactory.getInstance().insertArticle(articolo))
                    response.sendRedirect("inserimentoConfermato.jsp");
                else
                    throw new InvalidParamException("Si è verificato un problema con il Database, contattare l'amministratore");

            } catch(InvalidParamException e){

                session.setAttribute("errorMessage", e.getMessage());
                response.sendRedirect("erroreInserimento.jsp");

            } catch(InvalidServletException e) {

                response.sendRedirect("index.jsp");
                
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