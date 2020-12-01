package ui.controller;

import domain.model.ContactTracingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerFactory {
    public RequestHandler getHandler(HttpServletRequest request, HttpServletResponse response, ContactTracingService DB) {
        String command = request.getParameter("command");
        if (command == null || command.isEmpty()) command = "Home";

        RequestHandler handler = null;

        try {
            Class handlerClass = Class.forName("ui.controller." + command + "Handler");
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setDB(DB);
        } catch (Exception e){
            request.setAttribute("errors", e.getMessage());
            throw new RuntimeException("The requested page could not be found" + "\n" + e.getMessage());
        }
        return handler;
    }
}
