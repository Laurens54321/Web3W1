package ui.controller;

import domain.model.AuthorizationException;
import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveAllPersonsHandler extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, AuthorizationException, IOException {
        Person p = (Person) request.getAttribute("person");
        if (p == null || !p.isAdmin()) throw new AuthorizationException();
        else {
            getDB().deleteAllPersons();
            request.getSession().removeAttribute("person");
        }

        response.sendRedirect("Servlet?command=Overview");
    }
}
