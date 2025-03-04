package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class calculate_bill extends JFrame implements ActionListener {
    Choice mNumber;
    JLabel Rname,Radd;
    TextField Uconsumed;
    Choice months;
    JButton submit,cancel;
    calculate_bill(){
        super("Electricity Billing System");

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xFF88BDD5, true));
        panel.setLayout(null);
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(70,10,300,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(heading);

        JLabel meterNo = new JLabel("Meter Number");

        meterNo.setBounds(50,80,100,20);
        panel.add(meterNo);

        mNumber = new Choice();
        try {
            Database db = new Database();
            ResultSet resultSet = db.statement.executeQuery("select * from newcustomer");
            while (resultSet.next()){
                mNumber.add(resultSet.getString("Meter_no"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        mNumber.setBounds(180,80,150,20);
        panel.add(mNumber);

        JLabel name = new JLabel("Name");
        name.setBounds(50,120,100,20);
        panel.add(name);

        Rname = new JLabel("");
        Rname.setBounds(180,120,150,20);
        panel.add(Rname);

        JLabel add = new JLabel("Address");
        add.setBounds(50,160,100,20);
        panel.add(add);

        Radd = new JLabel("");
        Radd.setBounds(180,160,150,20);
        panel.add(Radd);

        try{
            Database db = new Database();
            ResultSet resultSet = db.statement.executeQuery("select * from newcustomer where Meter_no = '"+mNumber.getSelectedItem()+"'");
            while (resultSet.next()){
                Rname.setText(resultSet.getString("Name"));
                Radd.setText(resultSet.getString("address"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }

        mNumber.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    Database db = new Database();
                    ResultSet resultSet = db.statement.executeQuery("select * from newcustomer where Meter_no = '"+mNumber.getSelectedItem()+"'");
                    while (resultSet.next()){
                        Rname.setText(resultSet.getString("Name"));
                        Radd.setText(resultSet.getString("address"));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JLabel Cunit = new JLabel("Unit Consumed");
        Cunit.setBounds(50,200,100,20);
        panel.add(Cunit);

        Uconsumed = new TextField();
        Uconsumed.setBounds(180,200,150,20);
        panel.add(Uconsumed);

        JLabel Month = new JLabel("Month");
        Month.setBounds(50,240,100,20);
        panel.add(Month);

        months = new Choice();
        months.setBounds(180,240,150,20);
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        panel.add(months);

        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setBounds(80,300,80,25);
        panel.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setBounds(200,300,80,25);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icon/budget.png"));
        Image img1 = img.getImage().getScaledInstance(250,280,Image.SCALE_DEFAULT);
        ImageIcon Img1 = new ImageIcon(img1);
        JLabel label = new JLabel(Img1);
        add(label,"East");


        setSize(650,400);
        setLocation(400,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            String mNum = mNumber.getSelectedItem();
            String Ucon = Uconsumed.getText();
            String mon = months.getSelectedItem();

            int totalBill = 0;
            int units = Integer.parseInt(Ucon);
            String Query_tax = "select * from tax";

            try{
                Database db = new Database();
                ResultSet resultSet = db.statement.executeQuery(Query_tax);
                while (resultSet.next()){
                    totalBill += units * Integer.parseInt(resultSet.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(resultSet.getString("meter_rent"));
                    totalBill += Integer.parseInt(resultSet.getString("service_charge"));
                    totalBill += Integer.parseInt(resultSet.getString("service_tax"));
                    totalBill += Integer.parseInt(resultSet.getString("swach_bharat_tax"));
                    totalBill += Integer.parseInt(resultSet.getString("fixed_tax"));

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                Database c = new Database();
                String query_total_bill = "INSERT INTO bill (meter_no, month, unit, total_bill, status) " +
                        "VALUES ('" + mNum + "', '" + mon + "', '" + Ucon + "', '" + totalBill + "', 'Not Paid')";

                c.statement.executeUpdate(query_total_bill);

                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                this.setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            this.setVisible(false);
        }

    }

    public static void main(String[] args) {
        new calculate_bill();
    }
}
