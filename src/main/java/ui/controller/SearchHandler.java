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

public class SearchHandler extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NotAuthorizedException, IOException {
        Person.Role[] roles = {Person.Role.user, Person.Role.administrator};
        Authorization.checkrole(request, roles);

        Person p = (Person) request.getSession().getAttribute("person");
        CoronaTest test = getDB().getLatestTestByUserid(p.getUserid());

        if (test == null){
            request.setAttribute("errors", "You have not registered a test yet");
        }
        ArrayList<Reservation> reservations = getDB().getInRangeReservationsByUserid(p.getUserid(), test.getDate(), LocalDate.now());
        if (reservations == null || reservations.isEmpty()){
            request.setAttribute("errors", "There have been no reservations since the last test");
        }
        else{
            request.setAttribute("reservations", reservations);
        }
        return "search.jsp";
    }
}
