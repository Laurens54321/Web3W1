package ui.controller;

import domain.model.AuthorizationException;
import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInHandler extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, AuthorizationException, IOException {
        String userid = request.getParameter("userid");
        Person person = getDB().findPerson(userid);

        boolean loginAccept = false;
        String password = request.getParameter("password");

        if (person != null && !password.isEmpty()){
            loginAccept = person.isCorrectPassword(password);
            System.out.println("Logged in as " + person.toString() );

            if (loginAccept){
                request.getSession().setAttribute("person", person);
            }
        }

        if (person != null && !loginAccept) {
            request.setAttribute("useridPreviousValue", userid);
            request.setAttribute("errors", "Password Incorrect");
        }
        if (!userid.isEmpty() && person == null){
            request.setAttribute("errors", "Username not found");
        }

        response.sendRedirect("profile.jsp");
    }
}
