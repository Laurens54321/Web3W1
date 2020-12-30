package ui.util;

import domain.model.NotAuthorizedException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;

public class Authorization {

    public static void checkrole(HttpServletRequest request, Person.Role[] roles) throws NotAuthorizedException {
        Boolean authorized = false;
        Person currentUser = (Person) request.getSession().getAttribute("person");

        if (currentUser == null){
            for (Person.Role role : roles) {
                if (role.equals(Person.Role.guest)) authorized = true;
            }
        }
        else{
            for (Person.Role role : roles) {
                if (role.equals(currentUser.getRole())) authorized = true;
            }
        }
        if (!authorized){
            System.out.println("Unauthorized page requested");
            throw new NotAuthorizedException();
        }
    }
}
