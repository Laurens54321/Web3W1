package ui.controller;

import domain.model.AuthorizationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OverviewHandler extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, AuthorizationException, IOException {
        try{
            request.setAttribute("DB", DB.getPersons());
            request.setAttribute("reservations", DB.getAllReservations());
        } catch(Exception e){
            request.setAttribute("errors", e.getMessage());
        }
        request.getRequestDispatcher("personoverview.jsp").forward(request,response);
    }
}
