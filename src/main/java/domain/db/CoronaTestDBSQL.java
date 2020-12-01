package domain.db;

import domain.model.CoronaTest;
import util.DbConnectionService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CoronaTestDBSQL {
    private Connection connection;
    private String schema;

    public CoronaTestDBSQL(){
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    public void add(CoronaTest test){
        if (test == null) throw  new DbException("Cannot add empty test to database");

        String sql = String.format("INSERT INTO %s.coronatests (userid, date, positive) VALUES (?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, test.getUserid());
            statementSQL.setDate(2,  java.sql.Date.valueOf(test.getDate()));
            statementSQL.setBoolean(3, test.isPositive());
            statementSQL.execute();
            System.out.println("Added test to database");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public ArrayList<CoronaTest> getAll(){
        ArrayList<CoronaTest> testList = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.coronatests", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            ResultSet result = statementSQL.executeQuery();

            while(result.next()){
                String userid = result.getString("userid");
                LocalDate date = result.getDate("date").toLocalDate();
                Boolean positive = result.getBoolean("positive");
                CoronaTest coronaTest = new CoronaTest(userid, date, positive);
                testList.add(coronaTest);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return testList;
    }

    public ArrayList<CoronaTest> getTestByUserid(String userid){
        ArrayList<CoronaTest> testList = new ArrayList<>();
        for (CoronaTest t : getAll()) {
            if (t.getUserid().equals(userid)) testList.add(t);
        }
        return  testList;
    }
}
