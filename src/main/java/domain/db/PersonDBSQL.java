package domain.db;

import domain.model.Person;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;

public class PersonDBSQL {
    private Connection connection;
    private String schema;

    public PersonDBSQL(){
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    /**
     * Stores the person in SQL Database
     *
     * @param person The person to be added to the database
     * @throws DbException if person is null
     * @throws DbException if person is in the database already
     *
     */

    public void add(Person person){
        if (person == null){
            throw new DbException("Cannot add empty person to database");
        }

        ArrayList<String> members = getUserIDs();
        if (members.contains(person.getUserid())) throw new DbException("User with that username already exists");
        System.out.println("Succesfully checked userid against " + members.size() + " members");
        
        String sql = String.format("INSERT INTO %s.members (userid, firstName, lastName, email, password, role) VALUES (?, ?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, person.getUserid());
            statementSQL.setString(2, person.getFirstName());
            statementSQL.setString(3, person.getLastName());
            statementSQL.setString(4, person.getEmail());
            statementSQL.setString(5, person.getPasswordHash());
            statementSQL.setString(6, person.getRoleString());
            statementSQL.execute();
            System.out.println("Added person to sql database");
        } catch (SQLException e){
            throw new DbException(e);
        }
    }

    public ArrayList<Person> getAll() {
        ArrayList<Person> personList = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.members", this.schema);

        try{
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            ResultSet result = statementSQL.executeQuery();
            while(result.next()) {
                String userid = result.getString("userid");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                String email = result.getString("email");
                String password = result.getString("password");
                String role = result.getString("role");
                Person p = new Person(userid, email, password, firstName, lastName, role);
                personList.add(p);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return personList;
    }

    public void remove(String userid){
        String sql = String.format("DELETE FROM %s.members WHERE userid='" + userid +"'", this.schema);
        try{
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.executeQuery();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    public void removeAll(){
        String sql = String.format("DELETE FROM %s.members *", this.schema);
        try{
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.execute();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    private ArrayList<String> getUserIDs(){
        ArrayList<String> userids = new ArrayList<>();
        String sql = String.format("SELECT userid FROM %s.members", this.schema);

        try{
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            ResultSet result = statementSQL.executeQuery();
            while(result.next()) {
                String userid = result.getString("userid");
                userids.add(userid);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return userids;
    }
}
