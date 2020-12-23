package ui.controller;

import domain.model.CoronaTest;
import domain.model.NotAuthorizedException;
import domain.model.Person;
import ui.util.Authorization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class SearchHandler extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NotAuthorizedException, IOException {
        Person.Role[] roles = {Person.Role.user, Person.Role.administrator};
        Authorization.checkrole(request, roles);

        Person p = (Person) request.getSession().getAttribute("person");
        ArrayList<CoronaTest> tests = getDB().getTestByUserid(p.getUserid());

        if (tests == null || tests.isEmpty()){
            request.setAttribute("errors", "You have not registered a test yet");
            request.getRequestDispatcher("search.jsp").forward(request,response);
        }
        else{
            request.getRequestDispatcher("search.jsp").forward(request,response);
        }
    }
}
