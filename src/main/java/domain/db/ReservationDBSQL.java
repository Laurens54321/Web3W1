package domain.db;

import domain.model.ContactTracingService;
import domain.model.DomainException;
import domain.model.Person;
import domain.model.Reservation;
import util.DbConnectionService;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReservationDBSQL {

    private Connection connection;
    private String schema;

    public ReservationDBSQL(){
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    public void add(Reservation reservation){
        if (reservation == null){
            throw new DbException("Cannot add empty person to database");
        }
        System.out.println("should not even get here");
        String sql = String.format("INSERT INTO %s.reservations (userid, starttime, endtime, field, date, phonenr, email) VALUES (?, ?, ?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, reservation.getUserid());
            statementSQL.setTime(2, Time.valueOf(reservation.getStartTime()));
            statementSQL.setTime(3, Time.valueOf(reservation.getEndTime()));
            statementSQL.setString(4, reservation.getField());
            statementSQL.setDate(5, Date.valueOf(reservation.getDate()));
            statementSQL.setString(6, reservation.getPhonenr());
            statementSQL.setString(7, reservation.getEmail());
            statementSQL.execute();
            System.out.println("Added reservation to sql database");
        } catch (SQLException e){
            throw new DbException(e);
        }
    }

    public ArrayList<Reservation> getAll() {
        ArrayList<Reservation> reservationList;
        String sql = String.format("SELECT * FROM %s.reservations", this.schema);

        try{
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            ResultSet result = statementSQL.executeQuery();
            if (result == null){
                System.out.println("No reservations found");
                return null;
            }
            reservationList = processResultSet(result);
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return reservationList;
    }

    public ArrayList<Reservation> getReservationWithUserid(String userid){
        ArrayList<Reservation> reservations = null;
        String sql = String.format("SELECT * FROM %s.reservations WHERE userid = ?", this.schema);
        try{
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, userid);
            ResultSet result = statementSQL.executeQuery();
            if (result == null){
                System.out.println("No reservations found");
                return null;
            }
            reservations = processResultSet(result);
        } catch (SQLException e){
            throw new DbException(e);
        }
        return reservations;
    }

    private ArrayList<Reservation> processResultSet(ResultSet result){
        ArrayList<Reservation> resultSet = new ArrayList<>();
        try{
            while(result.next()) {
                String userid = result.getString("userid");
                LocalTime startTime = result.getTime("starttime").toLocalTime();
                LocalTime endTime = result.getTime("endtime").toLocalTime();
                String field = result.getString("field");
                LocalDate date = result.getDate("date").toLocalDate();
                String phonenr = result.getString("phonenr");
                String email = result.getString("email");



                Reservation r = new Reservation(userid, startTime, endTime, field, date, phonenr, email);
                resultSet.add(r);
            }
            System.out.println("Succesfully loaded " + resultSet.size() + " reservations");
        } catch(SQLException e){
            throw new DbException(e);
        } catch(NullPointerException e){
            throw new DbException(e);
        }

        return resultSet;
    }

    public void removeAll(){
        String sql = String.format("DELETE FROM %s.reservations *", this.schema);
        try{
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.execute();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

}
