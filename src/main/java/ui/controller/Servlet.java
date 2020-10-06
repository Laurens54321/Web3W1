package ui.controller;

import model.ContactTracingService;
import model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

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
        String destination;
        try{
            RequestHandler handler = handlerFactory.getHandler(request, response, DB);
            destination = handler.handleRequest(request, response);
        } catch (Exception e){
            request.setAttribute("error", e.getMessage());
            destination = "error.jsp";
        }

        request.getRequestDispatcher(destination).forward(request,response);
    }

}
