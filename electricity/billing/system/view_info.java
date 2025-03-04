package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class view_info extends JFrame implements ActionListener {
    String view;
    JButton cancel;
    view_info(String view){
        this.view = view;
        setBounds(350,150,850,650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("View Customer Informations");
        heading.setBounds(250,0,500,40);
        heading.setFont(new Font("serif",Font.BOLD,20));
        add(heading);

        JLabel namelabel = new JLabel("Name");
        namelabel.setBounds(70,80,100,20);
        add(namelabel);

        JLabel namelabeltext = new JLabel("");
        namelabeltext.setBounds(200,80,150,20);
        add(namelabeltext);

        JLabel Meterlabel = new JLabel("Meter Number");
        Meterlabel.setBounds(70,140,100,20);
        add(Meterlabel);

        JLabel Meterlabeltext = new JLabel("");
        Meterlabeltext.setBounds(200,140,150,20);
        add(Meterlabeltext);

        JLabel Addresslabel = new JLabel("Address");
        Addresslabel.setBounds(70,200,100,20);
        add(Addresslabel);

        JLabel Addresslabeltext = new JLabel("");
        Addresslabeltext.setBounds(200,200,150,20);
        add(Addresslabeltext);

        JLabel citylabel = new JLabel("City");
        citylabel.setBounds(500,80,100,20);
        add(citylabel);

        JLabel citylabeltext = new JLabel("");
        citylabeltext.setBounds(600,80,150,20);
        add(citylabeltext);



        JLabel maillabel = new JLabel("Email");
        maillabel.setBounds(500,140,100,20);
        add(maillabel);

        JLabel maillabeltext = new JLabel("");
        maillabeltext.setBounds(600,140,150,20);
        add(maillabeltext);

        JLabel phonelabel = new JLabel("Phone Number");
        phonelabel.setBounds(500,200,100,20);
        add(phonelabel);

        JLabel phonelabeltext = new JLabel("");
        phonelabeltext.setBounds(600,200,150,20);
        add(phonelabeltext);

        try{
            Database db = new Database();
            ResultSet resultSet = db.statement.executeQuery("select * from newcustomer where meter_no = '"+view+"'");
            if(resultSet.next()){
                namelabeltext.setText(resultSet.getString("Name"));
                Meterlabeltext.setText(resultSet.getString("Meter_no"));
                Addresslabeltext.setText(resultSet.getString("address"));
                citylabeltext.setText(resultSet.getString("city"));
                maillabeltext.setText(resultSet.getString("email"));
                phonelabeltext.setText(resultSet.getString("phone_no"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cancel = new JButton("Cancel");
        cancel.setBackground(new Color(24,118,242));
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(220,350,120,25);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon a1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewInfo.png"));
        Image a2 = a1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon a3 = new ImageIcon(a2);
        JLabel label = new JLabel(a3);
        label.setBounds(100,320,600,300);
        add(label);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new view_info("");
    }
}
