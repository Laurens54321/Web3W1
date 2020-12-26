package ui.controller;

import domain.model.CoronaTest;
import domain.model.NotAuthorizedException;
import domain.model.Person;
import domain.model.Reservation;
import ui.util.Authorization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class SearchHandler extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NotAuthorizedException, IOException {
        Person.Role[] roles = {Person.Role.user, Person.Role.administrator};
        Authorization.checkrole(request, roles);

        Person p = (Person) request.getSession().getAttribute("person");
        CoronaTest test = getDB().getLatestTestByUserid(p.getUserid());

        if (test == null) request.setAttribute("message", "You have not registered a test yet");
        ArrayList<Reservation> reservations = getDB().getInRangeReservationsByUserid(p.getUserid(), test.getDate(), LocalDate.now());

        if (reservations == null || reservations.isEmpty()) request.setAttribute("message", "No reservations were found since your last positive test");
        else{
            request.setAttribute("message", "Search will show you the reservations since your last positive test ");
            request.setAttribute("reservations", reservations);
        }
        request.getRequestDispatcher("search.jsp").forward(request,response);
    }
}
