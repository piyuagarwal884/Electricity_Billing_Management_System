package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Update_info extends JFrame implements ActionListener {
    String meter;
    JLabel nametext;
    JTextField addresstext,citytext,statetext,mailtext,phonenotext,meterText;
    JButton update,cancel;
    Update_info(String meter){
        this.meter = meter;

        setBounds(400,150,777,450);
        getContentPane().setBackground(new Color(229,255,227));
        setLayout(null);

        JLabel heading = new JLabel("Update Customer Information");
        heading.setBounds(50,10,400,40);
        heading.setFont(new Font("serif",Font.BOLD,20));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(30,70,100,20);
        add(name);

        nametext = new JLabel("");
        nametext.setBounds(150,70,150,20);
        add(nametext);

        JLabel Meter = new JLabel("Meter");
        Meter.setBounds(30,110,100,20);
        add(Meter);

        meterText = new JTextField();
        meterText.setBounds(150,110,150,20);
        add(meterText);

        JLabel address = new JLabel("Address");
        address.setBounds(30,150,100,20);
        add(address);

        addresstext = new JTextField();
        addresstext.setBounds(150,160,150,20);
        add(addresstext);

        JLabel city = new JLabel("City");
        city.setBounds(30,190,100,20);
        add(city);

        citytext = new JTextField();
        citytext.setBounds(150,190,150,20);
        add(citytext);

        JLabel state = new JLabel("State");
        state.setBounds(30,230,100,20);
        add(state);

        statetext = new JTextField();
        statetext.setBounds(150,230,150,20);
        add(statetext);

        JLabel mail = new JLabel("E-Mail");
        mail.setBounds(30,270,100,20);
        add(mail);

        mailtext = new JTextField();
        mailtext.setBounds(150,270,150,20);
        add(mailtext);

        JLabel phoneno = new JLabel("Phone Number");
        phoneno.setBounds(30,310,100,20);
        add(phoneno);

        phonenotext = new JTextField();
        phonenotext.setBounds(150,310,150,20);
        add(phonenotext);

        try{
            Database database = new Database();
            ResultSet resultSet = database.statement.executeQuery("select * from newcustomer where Meter_no = '"+meter+"'");
            if (resultSet.next()){
                nametext.setText(resultSet.getString("Name"));
                meterText.setText(resultSet.getString("Meter_no"));
                addresstext.setText(resultSet.getString("address"));
                citytext.setText(resultSet.getString("city"));
                statetext.setText(resultSet.getString("state"));
                mailtext.setText(resultSet.getString("email"));
                phonenotext.setText(resultSet.getString("phone_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        update = new JButton("Update");
        update.addActionListener(this);
        update.setBounds(50,360,120,25);
        update.setBackground(new Color(33,106,145));
        update.setForeground(Color.WHITE);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBackground(new Color(33,106,145));
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(200,360,120,25);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/update.png"));
        Image img = imageIcon.getImage().getScaledInstance(400,410,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(img);
        JLabel Img = new JLabel(imageIcon1);
        Img.setBounds(360,0,400,410);
        add(Img);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==update){
            String saddress = addresstext.getText();
            String scity = citytext.getText();
            String sstate = statetext.getText();
            String semail = mailtext.getText();
            String sphone = phonenotext.getText();

            try{
                Database db = new Database();
                db.statement.executeUpdate("update newcustomer set address = '"+saddress+"',city = '"+scity+"',state = '"+sstate+"',email = '"+semail+"',phone_no = '"+sphone+"' where Meter_no = '"+meter+"'");

                JOptionPane.showMessageDialog(null,"User Information Updated Successfully");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Update_info("");
    }
}
