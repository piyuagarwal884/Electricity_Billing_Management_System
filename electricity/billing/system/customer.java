package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class customer extends JFrame implements ActionListener{
    TextField customerText,Addtext,cityText,stateText,mailText,phoneText;
    JLabel heading,customName,MeterNo,Address,city,state,mail,phone,MeterText;
    JButton next,cancel;

    customer(){
        super("New Customer");

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xFFA7DA05, true));
        panel.setLayout(null);
        add(panel);

        heading = new JLabel("New Customer");
        heading.setBounds(120,10,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(heading);

        customName = new JLabel("Name");
        customName.setBounds(50,80,100,20);
        panel.add(customName);

        customerText = new TextField();
        customerText.setBounds(180,80,150,20);
        panel.add(customerText);

        MeterNo = new JLabel("Meter Number");
        MeterNo.setBounds(50,120,100,20);
        panel.add(MeterNo);

        MeterText = new JLabel("");
        MeterText.setBounds(180,120,150,20);
        panel.add(MeterText);

        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        MeterText.setText("" + Math.abs(number));

        Address = new JLabel("Address");
        Address.setBounds(50,160,100,20);
        panel.add(Address);

        Addtext = new TextField();
        Addtext.setBounds(180,160,150,20);
        panel.add(Addtext);

        city = new JLabel("City");
        city.setBounds(50,200,100,20);
        panel.add(city);

        cityText = new TextField();
        cityText.setBounds(180,200,150,20);
        panel.add(cityText);

        state = new JLabel("State");
        state.setBounds(50,240,100,20);
        panel.add(state);

        stateText = new TextField();
        stateText.setBounds(180,240,150,20);
        panel.add(stateText);

        mail = new JLabel("Email");
        mail.setBounds(50,280,100,20);
        panel.add(mail);

        mailText = new TextField();
        mailText.setBounds(180,280,150,20);
        panel.add(mailText);

        phone = new JLabel("Phone");
        phone.setBounds(50,320,100,20);
        panel.add(phone);

        phoneText = new TextField();
        phoneText.setBounds(180,320,150,20);
        panel.add(phoneText);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setBounds(100,360,80,25);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setBounds(200,360,80,25);
        panel.add(cancel);


        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image I1 = i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon img = new ImageIcon(I1);
        JLabel IMG = new JLabel(img);
        add(IMG,"West");


        setSize(700,500);
        setLocation(400,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next){
            String sname = customerText.getText();
            String saddress = Addtext.getText();
            String smeter = MeterText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String smail = mailText.getText();
            String sphone = phoneText.getText();
            try {
                Database db = new Database();
                String QueryCustomer = null;
                String Query = null;

                QueryCustomer = "insert into newcustomer values('"+sname+"','"+smeter+"','"+saddress+"','"+scity+"','"+sstate+"','"+smail+"','"+sphone+"')";
                db.statement.executeUpdate(QueryCustomer);

                Query = "insert into signup values('"+smeter+"','','"+sname+"','','')";
                db.statement.executeUpdate(Query);

                JOptionPane.showMessageDialog(null,"Customer Details Added Successfully");
                setVisible(false);
                new Metre_inputs(smeter);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new customer();
    }
}
