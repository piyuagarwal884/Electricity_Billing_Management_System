package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class customer_details extends JFrame implements ActionListener {
    Choice searchMetercho,nameCho;
    JTable table;
    JButton search,print,close;
    customer_details(){

        super("Customer Details");
        getContentPane().setBackground(new Color(192,186,234));
        setSize(700,500);
        setLocation(400,200);
        setLayout(null);

        JLabel search_meter = new JLabel("Search By Meter Number");
        search_meter.setBounds(20,20,150,20);
        add(search_meter);

        searchMetercho = new Choice();
        searchMetercho.setBounds(180,20,150,20);
        add(searchMetercho);
        try {
            Database db = new Database();
            ResultSet resultSet = db.statement.executeQuery("select * from newcustomer");
            while (resultSet.next()){
                searchMetercho.add(resultSet.getString("Meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel searchByname = new JLabel("Search By Name");
        searchByname.setBounds(400,20,100,20);
        add(searchByname);

        nameCho = new Choice();
        nameCho.setBounds(520,20,150,20);
        add(nameCho);
        try {
            Database db = new Database();
            ResultSet resultSet = db.statement.executeQuery("select * from newcustomer");
            while (resultSet.next()){
                nameCho.add(resultSet.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        try{
            Database db = new Database();
            ResultSet resultSet = db.statement.executeQuery("SELECT * FROM newcustomer");
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
        if (e.getSource() == search){
            String query_search = "select * from newcustomer where Meter_no = '"+searchMetercho.getSelectedItem()+"' and Name = '"+nameCho.getSelectedItem()+"'";
            try{
                Database db = new Database();
                ResultSet resultSet = db.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == print) {
           try{
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
        new customer_details();
    }
}
