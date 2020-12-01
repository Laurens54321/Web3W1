package ui.controller;

import domain.model.AuthorizationException;
import domain.model.ContactTracingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {

    protected ContactTracingService DB;

    public abstract void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, AuthorizationException, IOException;

    public void setDB(ContactTracingService service) { this.DB = service; }

    public ContactTracingService getDB() { return DB; }
}
