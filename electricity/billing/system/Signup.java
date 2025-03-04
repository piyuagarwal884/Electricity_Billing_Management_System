package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener {
    Choice loginAScho;
    JTextField metertext,employeetext,useridtext,nametext,passtext;
    JButton create,back;


    Signup(){
        super("Signup");
        getContentPane().setBackground(Color.CYAN);

        JLabel createas = new JLabel("Create Account as:");
        createas.setBounds(30,50,125,20);
        add(createas);

        loginAScho = new Choice();
        loginAScho.setBounds(170,50,125,20);
        loginAScho.add("Admin");
        loginAScho.add("Customer");
        add(loginAScho);

        JLabel meterno = new JLabel("Meter Number");
        meterno.setBounds(30,100,125,20);
        meterno.setVisible(false);
        add(meterno);

        metertext = new JTextField();
        metertext.setBounds(170,100,125,20);
        metertext.setVisible(false);
        add(metertext);

        JLabel employer = new JLabel("Employer ID");
        employer.setBounds(30,100,125,20);
        employer.setVisible(true);
        add(employer);

        employeetext = new JTextField();
        employeetext.setBounds(170,100,125,20);
        employeetext.setVisible(true);
        add(employeetext);

        JLabel user = new JLabel("Username");
        user.setBounds(30,135,125,20);
        add(user);

        useridtext = new JTextField();
        useridtext.setBounds(170,135,125,20);
        add(useridtext);

        JLabel name = new JLabel("Name");
        name.setBounds(30,170,125,20);
        add(name);

        nametext = new JTextField();
        nametext.setBounds(170,170,125,20);
        add(nametext);

        metertext.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    Database db = new Database();
                    ResultSet resultSet = db.statement.executeQuery("select * from signup where meter_no ='"+metertext.getText()+"'");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JLabel password = new JLabel("Password");
        password.setBounds(30,205,125,20);
        add(password);

        passtext = new JTextField();
        passtext.setBounds(170,205,125,20);
        add(passtext);

        //ye vaala code ka part hamare drop down box m jo option select ho rha h usko functionability dene k kaam aa rha h jaise kei dropbox m jo bhi select ho uske respective options unlock ho jaaye
        loginAScho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginAScho.getSelectedItem();
                if(user.equals("Customer")) {
                    employer.setVisible(false);
                    employeetext.setVisible(false);
                    meterno.setVisible(true);
                    metertext.setVisible(true);
                }
                else{
                    employer.setVisible(true);
                    employeetext.setVisible(true);
                    meterno.setVisible(false);
                    metertext.setVisible(false);
                }
            }
        });

        create = new JButton("Signup");
        create.setBackground(Color.LIGHT_GRAY);
        create.setBounds(50,250,100,20);
        create.addActionListener(this);
        add(create);

        back = new JButton("Back");
        back.setBackground(Color.LIGHT_GRAY);
        back.setBounds(200,250,100,20);
        back.addActionListener(this);
        add(back);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image imgone = img.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon imgprofile = new ImageIcon(imgone);
        JLabel profile = new JLabel(imgprofile);
        profile.setBounds(330,15,250,250);
        add(profile);


        setSize(600,350);
        setLocation(500,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == create){
            String sloginAs = loginAScho.getSelectedItem();
            String Susername = useridtext.getText();
            String Spass = passtext.getText();
            String Sname = nametext.getText();
            String Smeterno = metertext.getText();
            try {
                Database c = new Database();
                String query = null;
                query = "insert into signup value('"+Smeterno+"','"+Susername+"','"+Sname+"','"+Spass+"','"+sloginAs+"')";

                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null,"Account Created");
                setVisible(false);
                new Login();
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        else if(e.getSource()==back){
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
       new Signup();
    }
}
