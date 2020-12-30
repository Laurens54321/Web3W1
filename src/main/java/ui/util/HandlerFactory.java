package ui.util;

import domain.model.ContactTracingService;
import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerFactory {
    public RequestHandler getHandler(HttpServletRequest request, HttpServletResponse response, ContactTracingService DB) {
        String command = request.getParameter("command");
        if (command == null || command.isEmpty()) command = "Home";

        RequestHandler handler;

        try {
            Class handlerClass = Class.forName("ui.controller." + command + "Handler");
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setDB(DB);
        } catch (Exception e) {
            throw new RuntimeException("This page does not exist");
        }
        return handler;
    }
}
