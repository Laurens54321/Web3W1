package ui.controller;

import domain.model.NotAuthorizedException;
import domain.model.Person;
import domain.model.Reservation;
import ui.util.Authorization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class YourReservationsHandler extends RequestHandler{

    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NotAuthorizedException, IOException {
        Person.Role[] roles = {Person.Role.user, Person.Role.administrator};
        Authorization.checkrole(request, roles);
        LocalDate from = null;
        LocalDate until = null;

        try{
            String fromString = request.getParameter("from");
            if (fromString != null && !fromString.isEmpty()){
                from = LocalDate.parse(fromString, formatter2);
                request.setAttribute("fromPreviousValue", fromString);
            }
            String untilString = request.getParameter("until");
            if (untilString != null && !untilString.isEmpty()){
                until = LocalDate.parse(untilString, formatter2);
                request.setAttribute("untilPreviousValue", untilString);
            }
        } catch (Exception e){
            request.setAttribute("messages", "Error while parsing selected range");
            request.getRequestDispatcher("yourreservations.jsp").forward(request,response);
        }

        Person user = (Person) request.getSession().getAttribute("person");
        ArrayList<Reservation> reservations = getDB().getInRangeReservationsByUserid(user.getUserid(), from, until);

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
