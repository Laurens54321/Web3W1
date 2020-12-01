package ui.controller;

import domain.model.AuthorizationException;
import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class SearchHandler extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, AuthorizationException, IOException {
        Person p = (Person) request.getAttribute("person");
        if (p == null) throw new AuthorizationException();

        Date date = (Date) request.getAttribute("date");

        if (date == null){
            request.setAttribute("errors", "Date cannot be empty for search");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
        else{
            request.getRequestDispatcher("yourreservations.jsp").forward(request,response);
        }
    }
}
