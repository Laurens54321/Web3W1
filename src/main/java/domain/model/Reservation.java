package domain.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Reservation implements Comparable {
    private String userid;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String field;
    private String phonenr;
    private String email;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public Reservation(LocalTime startTime, LocalTime endTime, String field, Person attendee, LocalDate date) {
        setUserid(String.valueOf(Math.random()*100000));
        setStartTime(startTime);
        setEndTime(endTime);
        setField(field);
        setDate(date);
    }

    public Reservation () { setUserid(String.valueOf(Math.random()*100000)); }

    public Reservation (Person person){
        setUserid(person.getUserid());
    }

    public Reservation(String userid, LocalTime startTime, LocalTime endTime, String field, LocalDate date, String phonenr, String email) {
        setUserid(userid);
        setStartTime(startTime);
        setEndTime(endTime);
        setField(field);
        setDate(date);
        setPhonenr(phonenr);
        setEmail(email);
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public LocalTime getStartTime() { return startTime; }

    public String getStartTimeString() {
        if (startTime == null) return null;
        return startTime.toString();
    }

    public void setStartTime(LocalTime startTime) {
        if (startTime == null) throw new IllegalArgumentException("Reservation must have an start date.");
        this.startTime = startTime;
    }

    public void setStartTimeString(String startTimeString){

        if (startTimeString == null || startTimeString.isEmpty()) throw new IllegalArgumentException("Reservation must have an start date.");

        try{
            this.date = LocalDate.parse(startTimeString, formatter);
            this.startTime = LocalTime.parse(startTimeString, formatter);
        } catch (Exception e){
            throw new IllegalArgumentException("Wrong startTime format:\n" + e.getMessage());
        }
    }

    public LocalTime getEndTime() { return endTime; }

    public String getEndTimeString() {
        if (endTime == null) return null;
        return endTime.toString();
    }

    public void setEndTime(LocalTime endTime) {
        if (endTime == null) throw new IllegalArgumentException("Reservation must have an end date.");
        if (startTime != null) {
            if (endTime.isBefore(startTime)) throw new IllegalArgumentException("End date must be after start date");
        }
        this.endTime = endTime;
    }

    public void setEndTimeString(String endTimeString){
        LocalTime endTime;

        if (endTimeString == null || endTimeString.isEmpty()) throw new IllegalArgumentException("Reservation must have an end date.");

        try{
            endTime = LocalTime.parse(endTimeString);
            setEndTime(endTime);
        } catch (DateTimeParseException e){
            throw new IllegalArgumentException("Wrong endTime format");
        }
    }

    public String getField() { return field; }

    public void setField(String field) {
        if (field == null || field.isEmpty()) throw new IllegalArgumentException("Reservation must have a field");
        else this.field = field;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateString() { return date.toString(); }

    public void setPhonenr(String phonenr) {
        this.phonenr = phonenr;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenr() {
        return phonenr;
    }

    public String getEmail() {
        return email;
    }

    public boolean isDuringReservation(LocalTime time){
        if (time.isAfter(startTime) && time.isBefore(endTime)){
            return true;
        }
        else return false;
    }

    public String getCoronaString(){
        return "window.location.href='Servlet?command=Corona&reservation=" + userid + "'";
    }


    @Override
    public int compareTo(Object o) {
        if (!o.getClass().equals(this.getClass())) throw new IllegalArgumentException();
        Reservation that = (Reservation) o;

        if (this.startTime.compareTo(that.startTime) < 0) {
            return -1;
        } else if (this.startTime.compareTo(that.startTime) > 0) {
            return 1;
        }

        if (this.endTime.compareTo(that.endTime) < 0) {
            return -1;
        } else if (this.endTime.compareTo(that.endTime) > 0) {
            return 1;
        }

        if (this.field.compareTo(that.field) < 0) {
            return -1;
        } else if (this.field.compareTo(that.field) > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "startTime=" + getStartTimeString() +
                ", endTime=" + getStartTimeString() +
                ", field='" + field + '\'' +
                '}';
    }
}
