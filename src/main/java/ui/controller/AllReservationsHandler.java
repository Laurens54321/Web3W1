package ui.controller;

import domain.model.NotAuthorizedException;
import domain.model.Person;
import ui.util.Authorization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllReservationsHandler extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NotAuthorizedException, IOException {
        Person.Role[] roles = {Person.Role.administrator};
        Authorization.checkrole(request, roles);

        request.setAttribute("reservations", DB.getAllReservations());
        return "allreservations.jsp";
    }
}
