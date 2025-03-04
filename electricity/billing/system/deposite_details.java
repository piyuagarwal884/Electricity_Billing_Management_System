package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;

public class deposite_details extends JFrame implements ActionListener {
    Choice meter_search,month_search;
    JTable table;
    JButton search,print,close;
    deposite_details(){
        super("Deposit Details");
        getContentPane().setBackground(new Color(192,186,234));
        setSize(700,500);
        setLocation(400,200);
        setLayout(null);

        JLabel search_meter = new JLabel("Search By Meter Number");
        search_meter.setBounds(20,20,150,20);
        add(search_meter);

        meter_search = new Choice();
        meter_search.setBounds(200,20,150,20);
        try{
            Database db = new Database();
            ResultSet resultSet = db.statement.executeQuery("select * from bill");
            while (resultSet.next()){
                meter_search.add(resultSet.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        add(meter_search);

        JLabel month = new JLabel("Search by Month");
        month.setBounds(400,20,100,20);
        add(month);

        month_search = new Choice();
        month_search.setBounds(500,20,150,20);
        month_search.add("January");
        month_search.add("February");
        month_search.add("March");
        month_search.add("April");
        month_search.add("May");
        month_search.add("June");
        month_search.add("July");
        month_search.add("August");
        month_search.add("September");
        month_search.add("October");
        month_search.add("November");
        month_search.add("December");
        add(month_search);

        table = new JTable();
        try{
            Database db = new Database();
            ResultSet resultSet = db.statement.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,700,500);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);

        search = new JButton("Search");
        search.setBackground(Color.WHITE);
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(Color.WHITE);
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBackground(Color.WHITE);
        close.setBounds(600,70,80,20);
        close.addActionListener(this);
        add(close);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search){
            String query_search = "select * from bill where meter_no = '"+meter_search.getSelectedItem()+"' and month = '"+month_search.getSelectedItem()+"'";

            try{
                Database db = new Database();
                ResultSet resultSet = db.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource()==print) {
           try {
               table.print();
           } catch (Exception ex) {
               ex.printStackTrace();
           }
        }
        else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new deposite_details();
    }
}
