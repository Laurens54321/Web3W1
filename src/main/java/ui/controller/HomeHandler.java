package ui.controller;

import domain.model.AuthorizationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeHandler extends RequestHandler{

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, AuthorizationException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
