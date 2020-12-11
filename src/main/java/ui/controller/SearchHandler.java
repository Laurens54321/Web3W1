package ui.controller;

import domain.model.NotAuthorizedException;
import domain.model.Person;
import ui.util.Authorization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class SearchHandler extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NotAuthorizedException, IOException {
        Person.Role[] roles = {Person.Role.user, Person.Role.administrator};
        Authorization.checkrole(request, roles);

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
