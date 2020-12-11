package ui.controller;

import domain.model.NotAuthorizedException;
import domain.model.Person;
import domain.model.Reservation;
import ui.util.Authorization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class YourReservationsHandler extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NotAuthorizedException, IOException {
        Person.Role[] roles = {Person.Role.user, Person.Role.administrator};
        Authorization.checkrole(request, roles);

        Person user = (Person) request.getAttribute("person");
        ArrayList<Reservation> reservations = getDB().getInRangeReservationsByUserid(user.getUserid());
        if (reservations == null || reservations.isEmpty()){
            request.setAttribute("messages", "You have no contacts");
            request.getRequestDispatcher("yourreservations.jsp").forward(request,response);
        }
        else{
            request.setAttribute("reservations", reservations);
            request.getRequestDispatcher("yourreservations.jsp").forward(request,response);
        }

    }
}
