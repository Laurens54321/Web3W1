package ui.controller;

import domain.model.NotAuthorizedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;

public class ProfileHandler extends RequestHandler{

    private static DateTimeFormatter startTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm");
    private static DateTimeFormatter endTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NotAuthorizedException, IOException {
        if (request.getSession().getAttribute("person") != null){
            request.setAttribute("startTimePreviousValue", LocalDateTime.now().format(startTimeFormatter));
            request.setAttribute("endTimePreviousValue", LocalTime.now().plus(Duration.ofHours(2)).format(endTimeFormatter));
        }
        return "profile.jsp";
    }
}
