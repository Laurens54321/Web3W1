package ui.controller;

import domain.model.NotAuthorizedException;
import domain.model.Person;
import ui.util.Authorization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveAllPersonsHandler extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NotAuthorizedException, IOException {
        Person.Role[] roles = {Person.Role.administrator};
        Authorization.checkrole(request, roles);

        getDB().deleteAllPersons();
        Person p = new Person("admin", "admin@ucll.be", "passwordHash", "ad", "min");
        p.setPasswordString("t");
        getDB().addPerson(p);

        Person p2 = new Person("user", "user@ucll.be", "passwordHash", "Hazel", "Slag");
        p.setPasswordString("t");
        getDB().addPerson(p2);

        request.getSession().removeAttribute("person");
        request.getSession().setAttribute("nextMessage", "All persons were removed from the database");
        return "RedirectServlet?command=Overview";
    }
}
