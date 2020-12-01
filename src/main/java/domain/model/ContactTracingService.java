package domain.model;

import domain.db.CoronaTestDBSQL;
import domain.db.PersonDBSQL;
import domain.db.ReservationDBSQL;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ContactTracingService {
    private PersonDBSQL personDBSQL = new PersonDBSQL();
    private ReservationDBSQL reservationDBSQL = new ReservationDBSQL();
    private CoronaTestDBSQL coronaTestDBSQL = new CoronaTestDBSQL();

    public ContactTracingService() { }

    public ArrayList<Person> getPersons() {
        return getPersonDb().getAll();
    }

    public void addPerson(Person person) {
        getPersonDb().add(person);
    }

    public Person findPerson(String userid){
        ArrayList<Person> personList = getPersons();
        for (Person p : personList) {
            if (p.getUserid().equals(userid)) return p;
        }
        return null;
    }

    public void deletePerson(String id) {
        getPersonDb().remove(id);
    }

    private PersonDBSQL getPersonDb() { return personDBSQL; }
    private ReservationDBSQL getReservationDb() { return reservationDBSQL; }

    public ArrayList<Reservation> getReservations(LocalTime startTime, LocalTime enddate) {
        if (startTime == null) throw new DomainException("startTime cannot be empty for search");
        if (enddate == null) throw new DomainException("endDate cannot be empty for search");
        ArrayList<Reservation> out = new ArrayList<>();
        for (Reservation r : getAllReservations() ) {
            if (startTime.isAfter(r.getStartTime()) && startTime.isBefore(r.getEndTime()) && enddate.isBefore(r.getStartTime())) out.add(r);
        }
        return out;
    }

    public void deleteAllPersons(){
        getPersonDb().removeAll();
    }

    public Reservation getUserID(String id) {
        ArrayList<Reservation> reservations = getAllReservations();
        for (Reservation r :
                reservations) {
            if (r.getUserid().equals(id)) return r;
        }
        return null;
    }

    public ArrayList<Reservation> getAllReservations() { return getReservationDb().getAll(); }

    public void addReservation(Reservation reservation) { getReservationDb().add(reservation); }

    public ArrayList<Reservation> getInRangeReservationsByUserid(String userid) {
        ArrayList<CoronaTest> tests = getTestByUserid(userid);
        if (tests == null || tests.isEmpty()){
            return null;
        }
        else {
            Set<Reservation> returnList = new HashSet<>();
            ArrayList<Reservation> reservations =  reservationDBSQL.getReservationWithUserid(userid);
            for (Reservation r : reservations) {
                for (CoronaTest test : tests) {
                    if (r.getDate().isAfter(test.getDate().minusWeeks(1))){
                        returnList.add(r);
                    }
                }
            }
            return new ArrayList<>(returnList);
        }
    }

    public ArrayList<Reservation> getReservationsByUserid(String userid) {
        return reservationDBSQL.getReservationWithUserid(userid);
    }

    public ArrayList<CoronaTest> getAllTests() { return coronaTestDBSQL.getAll(); }

    public void addTest(CoronaTest test) { coronaTestDBSQL.add(test); }

    public ArrayList<CoronaTest> getTestByUserid(String userid) { return coronaTestDBSQL.getTestByUserid(userid); }

    public void deleteAllReservations() { reservationDBSQL.removeAll();}
}