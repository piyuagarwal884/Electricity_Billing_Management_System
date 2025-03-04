package electricity.billing.system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class payment_bill extends JFrame implements ActionListener {
    JButton back;
    String meter;
    payment_bill(String meter){
        this.meter = meter;

        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        try{
            j.setPage("https://www.paypal.com/in/home");
            j.setBounds(400,150,800,600);

        } catch (Exception e) {
            e.printStackTrace();
            j.setContentType("text/html");
            j.setText("<html>Error! Error! Error! Error! Error!</html>");
        }

        JScrollPane scrollPane = new JScrollPane(j);
        add(scrollPane);

        back = new JButton("Back");
        back.setBounds(640,20,80,30);
        back.addActionListener(this);
        j.add(back);

        setBounds(400,150,800,600);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new pay_bill(meter);
    }

    public static void main(String[] args) {
        new payment_bill("");
    }
}
