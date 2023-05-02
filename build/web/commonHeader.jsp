<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <header>
            <div>
                <img title="Logo" alt="Logo di Conference" src="img\loghi\logoNero.png" width="100" height="100">
                <h1>Conference</h1>
            </div>
        </header>
        
        <!-- Div che viene chiuso nel Footer -->
        <div class="col-d-12 col-s-12 fontTesto">
           
            <nav class="navbar">
                <ul>
                    <li class="${param.paginaCorrente == 'index' ? 'active' : ''}"><a href="index.jsp">Home</a></li>
                    <li class="${param.paginaCorrente == 'about' ? 'active' : ''}"><a href="about.jsp">Chi siamo</a></li>
                    <li class="${param.paginaCorrente == 'login' ? 'active' : ''}"><a href="login.jsp">Login</a></li>
                    <li class="${param.paginaCorrente == 'events' ? 'active' : ''}"><a href="elencoEventi.jsp">Eventi in programma</a></li>
                    <li class="${param.paginaCorrente == 'new' ? 'active' : ''}"><a href="nuovoEvento.jsp">Inserisci un nuovo evento</a></li>
                    <li class="${param.paginaCorrente == 'personal' ? 'active' : ''}"><a href="paginaPersonale.jsp">Pagina personale</a></li>
                </ul>
            </nav>
                
    </body>
</html>