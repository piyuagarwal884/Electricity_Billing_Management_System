package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class bill_details extends JFrame {
    String meter;
    bill_details(String meter){
        this.meter = meter;
        setBounds(400,150,700,650);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JTable table = new JTable();
        try{
            Database db = new Database();
            String query_bill = "select * from bill where Meter_no = '"+meter+"'";
            ResultSet resultSet = db.statement.executeQuery(query_bill);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,0,700,650);
        add(scrollPane);



        setVisible(true);

    }
    public static void main(String[] args) {
        new bill_details("");
    }
}
