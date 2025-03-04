package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class generate_bill extends JFrame implements ActionListener {
    String meter;
    Choice month_search;
    JTextArea area;
    JButton generate;
    generate_bill(String meter){
    this.meter = meter;
    setBounds(500,30,500,700);
    setLayout(new BorderLayout());
    JPanel pane = new JPanel();

    JLabel head = new JLabel("Generate Bill");

    JLabel meter_no = new JLabel(meter);

        month_search = new Choice();
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

        area = new JTextArea(50,15);
        area.setText("\n\n\t ---------- Click on the ----------\n\t ----------Generate Bill");
        area.setFont(new Font("Senserif",Font.ITALIC, 15));
        JScrollPane pane1 = new JScrollPane(area);

        generate = new JButton("Generate Bill");
        generate.addActionListener(this);

        add(pane1);

        pane.add(head);
        pane.add(meter_no);
        pane.add(month_search);

        add(pane,"North");
        add(generate,"South");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            Database db =new Database();
            String smonth = month_search.getSelectedItem();
            area.setText("\n Power Limited \n Electricity Bill For Month of "+smonth+",2025\n\n\n");
            ResultSet resultSet = db.statement.executeQuery("select * from newcustomer where Meter_no = '"+meter+"'");
            if (resultSet.next()){
                area.append("\n    Customer Name        : "+resultSet.getString("Name"));
                area.append("\n    Customer Meter Number: "+resultSet.getString("Meter_no"));
                area.append("\n    Customer Address     : "+resultSet.getString("address"));
                area.append("\n    Customer City        : "+resultSet.getString("city"));
                area.append("\n    Customer State       : "+resultSet.getString("state"));
                area.append("\n    Customer Email       : "+resultSet.getString("email"));
                area.append("\n    Customer Phone Number       : "+resultSet.getString("phone_no"));

            }

            resultSet = db.statement.executeQuery("select * from meter_info where meter_no ='"+meter+"'");
            if (resultSet.next()){
                area.append("\n    Customer Meter Location        : "+resultSet.getString("Meter_Location"));
                area.append("\n    Customer Meter Type: "+resultSet.getString("Meter_type"));
                area.append("\n    Customer Phase Code   : "+resultSet.getString("Phase_code"));
                area.append("\n    Customer Bill Type        : "+resultSet.getString("Bill_type"));
                area.append("\n    Customer Days      : "+resultSet.getString("Days"));


            }
            resultSet = db.statement.executeQuery("select * from tax");
            if (resultSet.next()){
                area.append("\n    Cost Per Unit        : "+resultSet.getString("cost_per_unit"));
                area.append("\n   Meter Rent: "+resultSet.getString("meter_rent"));
                area.append("\n   Service Charge   : "+resultSet.getString("service_charge"));
                area.append("\n   Service Tax        : "+resultSet.getString("service_tax"));
                area.append("\n   Swacch Bharat Acss     : "+resultSet.getString("Swach_bharat_tax"));
                area.append("\n   Fixed Tax     : "+resultSet.getString("Fixed_tax"));

            }
            resultSet = db.statement.executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+month_search.getSelectedItem()+"'");
            if (resultSet.next()) {
                area.append("\n    Current Month       : " + resultSet.getString("month"));
                area.append("\n   Units Consumed: " + resultSet.getString("unit"));
                area.append("\n   Total Charges   : " + resultSet.getString("total_bill"));
                area.append("\n Total Payable: "+resultSet.getString("total_bill"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new generate_bill("");
    }
}
