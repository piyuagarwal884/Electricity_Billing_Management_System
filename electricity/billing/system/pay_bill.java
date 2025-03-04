package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class pay_bill extends JFrame implements ActionListener {
    String meter;
    Choice month_search;
    JButton pay,back;
    pay_bill(String meter){
        this.meter = meter;
        setBounds(300,150,900,600);
        setLayout(null);

        JLabel head = new JLabel("Pay Bill");
        head.setFont(new Font("Tahoma",Font.BOLD,24));
        head.setBounds(120,5,400,30);
        add(head);

        JLabel meternum = new JLabel("Meter Number");
        meternum.setBounds(35,80,200,20);
        add(meternum);

        JLabel meternumtext = new JLabel("");
        meternumtext.setBounds(300,80,200,20);
        add(meternumtext);

        JLabel name = new JLabel("Name");
        name.setBounds(35,140,200,20);
        add(name);

        JLabel nametext = new JLabel("");
        nametext.setBounds(300,140,200,20);
        add(nametext);

        JLabel month = new JLabel("Month");
        month.setBounds(35,200,200,20);
        add(month);

        month_search = new Choice();
        month_search.setBounds(300,200,200,20);
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

        JLabel unit = new JLabel("Unit");
        unit.setBounds(35,260,200,20);
        add(unit);

        JLabel unittext = new JLabel("");
        unittext.setBounds(300,260,200,20);
        add(unittext);

        JLabel totalbill = new JLabel("Total Bill");
        totalbill.setBounds(35,320,200,20);
        add(totalbill);

        JLabel billtext = new JLabel("");
        billtext.setBounds(300,320,200,20);
        add(billtext);

        JLabel status = new JLabel("Status");
        status.setBounds(35,380,200,20);
        add(status);

        JLabel statustext = new JLabel("");
        statustext.setBounds(300,380,200,20);
        statustext.setForeground(Color.RED);
        add(statustext);

        try {
            Database db = new Database();
            ResultSet resultSet = db.statement.executeQuery("select * from newcustomer where Meter_no = '"+meter+"' ");
            while (resultSet.next()){
                meternumtext.setText(meter);
                nametext.setText(resultSet.getString("Name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        month_search.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Database db = new Database();
                try{
                    ResultSet resultSet = db.statement.executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+month_search.getSelectedItem()+"'");
                    while (resultSet.next()){
                        unittext.setText(resultSet.getString("unit"));
                        billtext.setText(resultSet.getString("total_bill"));
                        statustext.setText(resultSet.getString("status"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100,460,100,25);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230,460,100,25);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==pay){
            try{
                Database db = new Database();
                db.statement.executeUpdate("update bill set status = 'Paid' where meter_no = '"+meter+"' and month = '"+month_search+"'");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            setVisible(false);
            new payment_bill(meter);
        }
        else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new pay_bill("");
    }
}
