package ui.controller;

import model.ContactTracingService;
import model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    ContactTracingService DB = new ContactTracingService();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        String command = "home";
        if (request.getParameter("command") != null){
            command = request.getParameter("command");
        }

        String destination;
        switch(command){
            case "Overview":
                destination = overview(request, response);
                break;
            default:
                destination = home(request, response);
                break;
        }

        request.getRequestDispatcher(destination).forward(request,response);
    }



    private String home(HttpServletRequest request, HttpServletResponse response){
        return "index.jsp";
    }

    private String overview(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("DB", DB.getPersons());
        return "personoverview.jsp";
    }
}
