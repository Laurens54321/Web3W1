package ui.controller;

import domain.model.AuthorizationException;
import domain.model.Person;
import domain.model.Reservation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class YourReservationsHandler extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, AuthorizationException, IOException {
        Person p = (Person) request.getAttribute("person");
        if (p == null) throw new AuthorizationException();
        else{
            ArrayList<Reservation> reservations = getDB().getInRangeReservationsByUserid(p.getUserid());
            if (reservations == null || reservations.isEmpty()){
                request.setAttribute("messages", "You have no contacts");
            }
            else{
                request.setAttribute("reservations", reservations);
            }
            request.getRequestDispatcher("yourreservations.jsp").forward(request,response);

        }
    }
}
