package it.unica.nextech.servlet;

import it.unica.nextech.exceptions.*;
import it.unica.nextech.model.User;
import it.unica.nextech.model.UserFactory;
import it.unica.nextech.tools.Utility;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Autenticazione dei valori di login inseriti dall'utente.
 * Se l'utente ha inserito credenziali corrette viene creata una sessione 
 * e si reindirizza alla pagina personale, altrimenti l'utente viene 
 * rimandato in una pagina di errore con un messaggio relativo all'errore commesso.
 * Se la servlet viene chiamata tramite inserimento manuale nell'URL da parte
 * dell'utente allora rimanda all'index.
 * @author nextech
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        
        request.setCharacterEncoding("utf-8");
       
        String username = request.getParameter("usernameLogin");
        String password = request.getParameter("passwordLogin");
                         
        try {
            Utility.checkString("username", username, Utility.USERNAME_LENGTH);
            Utility.checkString("password", password, Utility.PASSWORD_LENGTH);
            
            User utente = UserFactory.getInstance().getUtenteByUsernamePassword(username, password);
            
            if(utente == null)
                throw new InvalidParamException("Username o Password non validi");
            
            HttpSession session = request.getSession();
            session.setAttribute("userData", utente); 
            
            session.setAttribute("lastLogin", Utility.convertTime(session.getLastAccessedTime()));
            session.setMaxInactiveInterval(120);
            
            response.sendRedirect("paginaPersonale.jsp"); 
            
        } catch(InvalidParamException e){
            
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("erroreInserimento.jsp").forward(request, response);
            
        } catch(InvalidServletException x) {
                
            response.sendRedirect("index.jsp");
        }
    }

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

}