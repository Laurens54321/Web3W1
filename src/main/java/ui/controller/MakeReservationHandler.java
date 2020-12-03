package ui.controller;

import domain.db.DbException;
import domain.model.AuthorizationException;
import domain.model.DomainException;
import domain.model.Person;
import domain.model.Reservation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class MakeReservationHandler extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, AuthorizationException, IOException {


        ArrayList<String> errors = new ArrayList();

        Person p = (Person) request.getAttribute("person");
        if (p == null) throw new AuthorizationException();
        else{
            Reservation r = new Reservation(p);
            setstartTime(r, request, errors);
            setendTime(r, request, errors);
            setField(r, request, errors);
            setPhonenr(r, request, errors);
            setEmail(r, request, errors);

            System.out.println("Errors so far after processing fields:\n" + errors);
            if (errors.size() > 0){
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("Servlet?command=Profile").forward(request,response);
            }
            try{
                DB.addReservation(r);
            } catch (Exception e){
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("Servlet?command=Profile").forward(request,response);
            }

            System.out.println(errors);
        }


        request.getRequestDispatcher("Servlet?command=Overview").forward(request,response);
    }


    private void setstartTime(Reservation r, HttpServletRequest request, ArrayList<String> errors){
        try {
            String startTime = request.getParameter("startTime");
            request.setAttribute("startTimePreviousValue", startTime);
            r.setStartTimeString(startTime);
        } catch (DbException exc){
            errors.add(exc.getMessage());
        }
    }

    private void setendTime(Reservation r, HttpServletRequest request, ArrayList<String> errors){
        try {
            String endTime = request.getParameter("endTime");
            request.setAttribute("endTimePreviousValue", endTime);
            r.setEndTimeString(endTime);
        } catch (DbException exc){
            errors.add(exc.getMessage());
        }
    }

    private void setField(Reservation r, HttpServletRequest request, ArrayList<String> errors){

        try {
            String field = request.getParameter("field");
            request.setAttribute("fieldPreviousValue", field);
            r.setField(field);

        } catch (DbException exc){
            errors.add(exc.getMessage());
        }
    }

    private void setPhonenr(Reservation r, HttpServletRequest request, ArrayList<String> errors) {
        String phonenr = request.getParameter("phonenr");
        try{
            r.setPhonenr(phonenr);
            request.setAttribute("phonenrPreviousValue", phonenr);
        } catch(DbException e){
            errors.add(e.getMessage());
        }

    }

    private void setEmail(Reservation r, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try{
            r.setEmail(email);
            request.setAttribute("emailPreviousValue", email);
        } catch(DbException e){
            errors.add(e.getMessage());
        }

    }
}
