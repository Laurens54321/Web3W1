package ui.util;

import domain.model.NotAuthorizedException;
import domain.model.ContactTracingService;
import ui.controller.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    ContactTracingService DB = new ContactTracingService();

    HandlerFactory handlerFactory = new HandlerFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        request.getSession().removeAttribute("message");
        String destination = "RedirectServlet?command=Home";
        try{
            RequestHandler handler = handlerFactory.getHandler(request, response, DB);
            destination = handler.handleRequest(request, response);
        } catch (NotAuthorizedException e){
            request.setAttribute("errors", "You do not have Access to that page");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } catch (Exception e){
            request.setAttribute("errors", e);
            System.out.println(e);
            destination = "error.jsp";
        }
        if (destination.startsWith("Redirect")) {
            response.sendRedirect(destination.replaceFirst("Redirect", ""));
        } else {
            String message = (String) request.getSession().getAttribute("nextMessage");
            request.getSession().removeAttribute("nextMessage");
            request.setAttribute("message", message);
            request.getRequestDispatcher(destination).forward(request, response);
        }
    }
}
