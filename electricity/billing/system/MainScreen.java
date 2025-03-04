package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame implements ActionListener {
    String acctype,meter_pass;
    MainScreen(String acctype , String meter_pass){
        this.acctype = acctype;
        this.meter_pass = meter_pass;

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/ebs.png"));
        Image imageone = imageIcon.getImage().getScaledInstance(1530,830,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(imageone);
        JLabel label = new JLabel(imageIcon1);
        add(label);

        //Menu Bar create krne k liye h
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Menu Bar m kya kya aayega vo yaha s initialise hoga
        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("serif",Font.PLAIN,15));
        //"Menu" ko menubar m add krne k liye h

        JMenuItem newcustomer = new JMenuItem("New Customer");
        newcustomer.setFont(new Font("monospaced",Font.PLAIN,14 ));
        newcustomer.addActionListener(this);
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icon/newcustomer.png"));
        Image img1 = img.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        newcustomer.setIcon(new ImageIcon(img1));
        menu.add(newcustomer);

        JMenuItem customerDetail = new JMenuItem("Customer Details");
        customerDetail.setFont(new Font("monospaced",Font.PLAIN,14 ));
        customerDetail.addActionListener(this);
        ImageIcon Cd = new ImageIcon(ClassLoader.getSystemResource("icon/customerDetails.png"));
        Image Cd1 = Cd.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        customerDetail.setIcon(new ImageIcon(Cd1));
        menu.add(customerDetail);

        JMenuItem depoDetail = new JMenuItem("Deposit Details");
        depoDetail.setFont(new Font("monospaced",Font.PLAIN,14 ));
        depoDetail.addActionListener(this);
        ImageIcon dD = new ImageIcon(ClassLoader.getSystemResource("icon/depositdetails.png"));
        Image dD1 = dD.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        depoDetail.setIcon(new ImageIcon(dD1));
        menu.add(depoDetail);

        JMenuItem calculatebill = new JMenuItem("Calculate Bill");
        calculatebill.setFont(new Font("monospaced",Font.PLAIN,14 ));
        calculatebill.addActionListener(this);
        ImageIcon Cb = new ImageIcon(ClassLoader.getSystemResource("icon/calculatorbills.png"));
        Image Cb1 = Cb.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        calculatebill.setIcon(new ImageIcon(Cb1));
        menu.add(calculatebill);

        JMenu info = new JMenu("Information");
        info.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem udinfo = new JMenuItem("Update Information");
        udinfo.setFont(new Font("monospaced",Font.PLAIN,14 ));
        udinfo.addActionListener(this);
        ImageIcon ud = new ImageIcon(ClassLoader.getSystemResource("icon/refresh.png"));
        Image ud1 = ud.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        udinfo.setIcon(new ImageIcon(ud1));
        info.add(udinfo);

        JMenuItem view = new JMenuItem("View Information");
        view.setFont(new Font("monospaced",Font.PLAIN,14 ));
        view.addActionListener(this);
        ImageIcon vi = new ImageIcon(ClassLoader.getSystemResource("icon/viewInfo.png"));
        Image vi1 = vi.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        view.setIcon(new ImageIcon(vi1));
        info.add(view);

        JMenu user = new JMenu("User");
        user.setFont(new Font("serif",Font.PLAIN,15));

        JMenuItem pay = new JMenuItem("Pay Bill");
        pay.setFont(new Font("monospaced",Font.PLAIN,14));
        pay.addActionListener(this);
        ImageIcon pb = new ImageIcon(ClassLoader.getSystemResource("icon/pay.png"));
        Image pb1 = pb.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        pay.setIcon(new ImageIcon(pb1));
        user.add(pay);

        JMenuItem billdetail = new JMenuItem("Bill Details");
        billdetail.setFont(new Font("monospaced",Font.PLAIN,14));
        billdetail.addActionListener(this);
        ImageIcon bd = new ImageIcon(ClassLoader.getSystemResource("icon/detail.png"));
        Image bd1 = bd.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        billdetail.setIcon(new ImageIcon(bd1));
        user.add(billdetail);

        JMenu bill = new JMenu("Bills");
        bill.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem generate = new JMenuItem("Generate Bill");
        generate.setFont(new Font("monospaced",Font.PLAIN,14));
        generate.addActionListener(this);
        ImageIcon gen = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image gen1 = gen.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        generate.setIcon(new ImageIcon(gen1));
        bill.add(generate);

        JMenu utility = new JMenu("Utility");
        utility.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setFont(new Font("monospaced",Font.PLAIN,14));
        notepad.addActionListener(this);
        ImageIcon np = new ImageIcon(ClassLoader.getSystemResource("icon/notepad.png"));
        Image np1 = np.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(np1));
        utility.add(notepad);

        JMenuItem calc = new JMenuItem("Calculator");
        calc.setFont(new Font("monospaced",Font.PLAIN,14));
        calc.addActionListener(this);
        ImageIcon cal = new ImageIcon(ClassLoader.getSystemResource("icon/calculator.png"));
        Image cal1 = cal.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        calc.setIcon(new ImageIcon(cal1));
        utility.add(calc);

        JMenu exit = new JMenu("Exit");
        exit.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem eexit = new JMenuItem("Exit");
        eexit.setFont(new Font("monospaced",Font.PLAIN,14));
        eexit.addActionListener(this);
        ImageIcon ex = new ImageIcon(ClassLoader.getSystemResource("icon/exit.png"));
        Image ex1 = ex.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        eexit.setIcon(new ImageIcon(ex1));
        exit.add(eexit);

        if (acctype.equals("Admin")) {
            menuBar.add(menu);
        }else{
        menuBar.add(bill);
        menuBar.add(info);
        menuBar.add(user);
        }
        menuBar.add(utility);
        menuBar.add(exit);

        setLayout(new FlowLayout());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = e.getActionCommand();
        if (msg.equals("New Customer")) {
            new customer();
        } else if (msg.equals("Customer Details")) {
            new customer_details();
        } else if (msg.equals("Deposit Details")) {
            new deposite_details();
        } else if (msg.equals("Calculate Bill")) {
            new calculate_bill();
        } else if (msg.equals("View Information")) {
            new view_info(meter_pass);
        } else if (msg.equals("Update Information")) {
            new Update_info(meter_pass);
        } else if (msg.equals("Bill Details")) {
            new bill_details(meter_pass);
        } else if (msg.equals("Calculator")) {
            try{
                Runtime.getRuntime().exec("Calc.exe");
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (msg.equals("Notepad")) {
            try{
                Runtime.getRuntime().exec("notepad.exe");
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (msg.equals("Pay Bill")) {
            new pay_bill(meter_pass);
        } else if (msg.equals("Generate Bill")) {
            new generate_bill(meter_pass);
        } else{
            setVisible(false);
            new Login();
        }

    }

    public static void main(String[] args) {
        new MainScreen("","");
    }
}
