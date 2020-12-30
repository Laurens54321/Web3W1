package ui.controller;

import domain.model.NotAuthorizedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OverviewHandler extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NotAuthorizedException, IOException {
        try{
            request.setAttribute("DB", DB.getPersons());
            request.setAttribute("reservations", DB.getAllReservations());
        } catch(Exception e){
            request.setAttribute("errors", e.getMessage());
        }
        return "personoverview.jsp";
    }
}
