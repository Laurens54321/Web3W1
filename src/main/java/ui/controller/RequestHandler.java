package ui.controller;

import model.ContactTracingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {

    protected ContactTracingService DB;

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException;

    public void setDB(ContactTracingService service) { this.DB = service; }

    public ContactTracingService getDB() { return DB; }
}
