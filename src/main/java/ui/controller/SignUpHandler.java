package ui.controller;

import domain.db.DbException;
import domain.model.AuthorizationException;
import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static domain.model.Person.Role.administrator;

public class SignUpHandler extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, AuthorizationException, IOException {
        //String userid = request.getParameter("userid");
        //String email = request.getParameter("email");
        //String password = request.getParameter("password");

        Person user = (Person) request.getAttribute("person");
        if (user != null) throw new AuthorizationException();


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
            destination = "register.jsp";
        }
        else {
            try{
                DB.addPerson(p);
                destination = "Servlet?command=Overview";
            } catch(DbException e){
                request.setAttribute("errors", e.getMessage());
                destination = "register.jsp";
            }
        }


        response.sendRedirect(destination);
    }

    private void setUserId(Person person, HttpServletRequest request, ArrayList<String> errors){
        String userid = request.getParameter("userid");
        try {
            person.setUserid(userid);
            request.setAttribute("useridPreviousValue", userid);
            if(userid.equals("admin")) person.setRole(administrator);
        } catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }

    }

    private void setFirstName(Person person, HttpServletRequest request, ArrayList<String> errors){
        String firstname = request.getParameter("firstName");
        try {
            person.setFirstName(firstname);
            request.setAttribute("firstNamePreviousValue", firstname);
        } catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }
    }

    private void setLastName(Person person, HttpServletRequest request, ArrayList<String> errors){
        String lastname = request.getParameter("lastName");
        try {
            person.setLastName(lastname);
            request.setAttribute("lastNamePreviousValue", lastname);
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
            person.setPasswordString(password);
            request.setAttribute("passswordPreviousValue", password);
        } catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }
    }
}
