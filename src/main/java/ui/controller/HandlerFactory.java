package ui.controller;

import com.google.common.util.concurrent.Service;
import model.ContactTracingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

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
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | SecurityException e){
            throw new RuntimeException("The requested page could not be found");
        }
        return handler;
    }
}
