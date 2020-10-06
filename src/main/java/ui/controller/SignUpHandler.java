package ui.controller;

import model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class SignUpHandler extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        //String userid = request.getParameter("userid");
        //String email = request.getParameter("email");
        //String password = request.getParameter("password");


        Person p = new Person();

        ArrayList<String> errors = new ArrayList();

        setUserId(p, request, errors);
        setFirstName(p, request, errors);
        setLastName(p, request, errors);
        setEmail(p, request, errors);
        setPassword(p, request, errors);

        String destination;
        if (errors.size() > 0){
            request.setAttribute("errors", errors);
            System.out.println("errors to be had");
            return "register.jsp";
        }
        else {
            DB.addPerson(p);
            destination = "Servlet?command=Overview";
        }


        System.out.println("i at least did something");
        return destination;
    }

    private void setUserId(Person person, HttpServletRequest request, ArrayList<String> errors){
        String userid = request.getParameter("userid");
        try {
            person.setUserid(userid);
            request.setAttribute("useridPreviousValue", userid);
        } catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }
    }

    private void setFirstName(Person person, HttpServletRequest request, ArrayList<String> errors){
        String firstname = request.getParameter("firstName");
        try {
            person.setFirstName(firstname);
            request.setAttribute("firstnamePreviousValue", firstname);
        } catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }
    }

    private void setLastName(Person person, HttpServletRequest request, ArrayList<String> errors){
        String lastname = request.getParameter("lastName");
        try {
            person.setLastName(lastname);
            request.setAttribute("lastnamePreviousValue", lastname);
        } catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }
    }

    private void setEmail(Person person, HttpServletRequest request, ArrayList<String> errors){
        String email = request.getParameter("email");
        try {
            person.setEmail(email);
            request.setAttribute("emailPreviousValue", email);
        } catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }
    }

    private void setPassword(Person person, HttpServletRequest request, ArrayList<String> errors){
        String password = request.getParameter("password");
        try {
            person.setPassword(password);
            request.setAttribute("passswordPreviousValue", password);
        } catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }
    }
}
