package electricity.billing.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField usertext,passinput;//Global declaration
    Choice loginchoice;
    JButton loginbutton,cancelbutton,signupbutton;
    Login(){
        super("Login");//'Super' syntex hamesha first staement hei hoga (ye sirf title box k upper kei hadding h)
        getContentPane().setBackground(Color.lightGray);

        //ye username vaala textfeild bnayega
        JLabel username = new JLabel("UserName");//JLabel text ko display krvane k kaam aata h but isse ham image bhi display krva skte h
        username.setBounds(300,60,100,20);
        add(username);

        usertext = new JTextField();
        usertext.setBounds(400,60,150,20);
        add(usertext);

        //ye password k liye textfeild bnayega
        JLabel password = new JLabel("Password");
        password.setBounds(300,100,100,20);
        add(password);

        passinput = new JTextField();
        passinput.setBounds(400,100,150,20);
        add(passinput);

        //ye login as ka drop box bnega
        JLabel logins = new JLabel("Login As");
        logins.setBounds(300,140,100,20);
        add(logins);

        loginchoice = new Choice();
        loginchoice.add("Admin");//drop box k items
        loginchoice.add("Customer");//drop box k items
        loginchoice.setBounds(400,140,150,20);
        add(loginchoice);

        //Yaha login button bnaya h
        loginbutton = new JButton("Login");
        loginbutton.setBounds(280,180,100,20);
        loginbutton.addActionListener(this);
        add(loginbutton);

        //yaha signup button bnega
        signupbutton = new JButton("Sign up");
        signupbutton.setBounds(450,180,100,20);
        signupbutton.addActionListener(this);
        add(signupbutton);

        //Yaha Cancel Button bnaya h
        cancelbutton = new JButton("Cancel");
        cancelbutton.setBounds(370,220,100,20);
        cancelbutton.addActionListener(this);
        add(cancelbutton);

        //yaha koi bhi ek image set krenge box m
        ImageIcon profile1 = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image profiletwo = profile1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon fprofile = new ImageIcon(profiletwo);
        JLabel profile = new JLabel(fprofile);
        profile.setBounds(4,5,250,250);
        add(profile);

        //Yaha hamne login page kei tab create krr di h
        setSize(640,300);
        setLocation(400,200);
        setLayout(null);//ye Layout ko pura clear krr dega
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loginbutton){
            String username = usertext.getText();
            String password = passinput.getText();
            String user = loginchoice.getSelectedItem();

            try{
                Database c = new Database();
                String query = "select * from signup where username = '"+username+"' and password = '"+password+"' and usertype = '"+user+"'";
                ResultSet resultSet = c.statement.executeQuery(query);

                if (resultSet.next()){
                    String meter = resultSet.getString("meter_no");
                    setVisible(false);
                    new MainScreen(user,meter);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Invalid Login");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource()==signupbutton) {
            setVisible(false);
            new Signup();
            
        } else if (e.getSource()==cancelbutton) {
            setVisible(false);
            new Signup();
            
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}