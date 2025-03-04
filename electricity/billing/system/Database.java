package electricity.billing.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
    Connection connection;
    Statement statement;
    public Database(){
        try{
            //Connection banaya h MySql k saath jisme url m pehle jdbc h phir database ka naam phir localhost + host address + table ka naam + root + password
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill_management","root","piyu_1234");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
