package domain.model;

import domain.db.DbException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CoronaTest {
    String userid;
    LocalDate date;
    boolean positive;

    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CoronaTest(String userid, LocalDate date, boolean positive){
        setUserid(userid);
        setDate(date);
        setTest(positive);
    }

    public CoronaTest() { }

    public void setUserid(String userid) { this.userid = userid; }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setDateString(String date){
        if (date.isEmpty() || date == null) throw new DbException("Test date cannot be empty");
        try{
            this.date = LocalDate.parse(date);
        } catch (Exception e){
            throw new DbException("Test date string was unable to be parsed");
        }
    }

    public void setTest(boolean test){
        this.positive = test;
    }

    public String getUserid() { return userid; }

    public LocalDate getDate() {
        return date;
    }

    public boolean isPositive() {
        return positive;
    }
}
