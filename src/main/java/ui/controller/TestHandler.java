package ui.controller;

import domain.db.DbException;
import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import domain.model.*;
import ui.util.Authorization;

public class TestHandler extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NotAuthorizedException, IOException {
        Person.Role[] roles = {Person.Role.user, Person.Role.administrator};
        Authorization.checkrole(request, roles);

        if (request.getMethod().equals("POST")){
            Person p = (Person) request.getSession().getAttribute("person");
            if (p == null) throw new NotAuthorizedException();
            else{
                ArrayList<String> errors = new ArrayList<>();

                CoronaTest test = new CoronaTest();
                setUserid(test, request, errors);
                setDate(test, request, errors);
                setPositive(test, request, errors);


                if (errors.size() > 0){
                    System.out.println(test.toString());
                    request.setAttribute("errors", errors);
                    return "registertest.jsp";
                } else {
                    try{
                        getDB().addTest(test);
                        request.getSession().setAttribute("nextMessage", "Your test was registered successfully");
                        return "RedirectServlet?command=YourReservations";
                    } catch (Exception e){
                        errors.add(e.getMessage());
                        request.setAttribute("errors", errors);
                        return "registertest.jsp";
                    }
                }
            }
        }
        else{
            return "registertest.jsp";
        }
    }

    private void setUserid(CoronaTest test, HttpServletRequest request, ArrayList<String> errors){
        String userid = ((Person) request.getSession().getAttribute("person")).getUserid();
        try {
            test.setUserid(userid);
            request.setAttribute("useridPreviousValue", userid);
        } catch (DbException exc){
            errors.add(exc.getMessage());
        }
    }

    private void setDate(CoronaTest test, HttpServletRequest request, ArrayList<String> errors){
        String date = request.getParameter("date");
        try {
            System.out.println(date);
            test.setDateString(date);
            request.setAttribute("datePreviousValue", date);
        } catch (DbException exc){
            errors.add(exc.getMessage());
        }
    }

    //All registered tests are positive
    //Change this if needed

    private void setPositive(CoronaTest test, HttpServletRequest request, ArrayList<String> errors){
        try {
            test.setTest(true);
        } catch (DbException exc){
            errors.add(exc.getMessage());
        }
    }

}
