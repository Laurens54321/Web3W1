package ui.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OverviewHandler extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.setAttribute("DB", DB.getPersons());
        return "personoverview.jsp";
    }
}
