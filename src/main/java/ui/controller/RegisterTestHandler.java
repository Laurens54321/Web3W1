package ui.controller;

import domain.model.AuthorizationException;
import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterTestHandler extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, AuthorizationException, IOException {
        Person p = (Person) request.getSession().getAttribute("person");
        if (p == null) throw new AuthorizationException();
        else  response.sendRedirect("registertest.jsp");
    }
}
