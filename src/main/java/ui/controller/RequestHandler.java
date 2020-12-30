package ui.controller;

import domain.model.NotAuthorizedException;
import domain.model.ContactTracingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {

    protected ContactTracingService DB;

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NotAuthorizedException, IOException;

    public void setDB(ContactTracingService service) { this.DB = service; }

    public ContactTracingService getDB() { return DB; }
}
