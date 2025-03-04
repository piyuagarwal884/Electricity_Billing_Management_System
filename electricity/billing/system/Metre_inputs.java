package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class Metre_inputs extends JFrame implements ActionListener {
    JLabel  Heading,Mno,Mlocation,Meter,MeterType,Phase,Bill;

    Choice Mloc,Mtype,Pcode,BillType;

    JButton submit;
    String Meter_no;

    Metre_inputs(String Meter_no){
        this.Meter_no = Meter_no;

        Panel pane = new Panel();
        pane.setLayout(null);
        pane.setBackground(new Color(0xFFA7DA05, true));
        add(pane);

        Heading = new JLabel("Meter Information");
        Heading.setBounds(120,10,200,20);
        Heading.setFont(new Font("Tahoma",Font.BOLD,20));
        pane.add(Heading);

        Mno = new JLabel("Meter Number");
        Mno.setBounds(50,80,100,20);
        pane.add(Mno);

        Meter = new JLabel(Meter_no);
        Meter.setBounds(180,80,150,20);
        pane.add(Meter);

        Mlocation = new JLabel("Meter Location");
        Mlocation.setBounds(50,120,100,20);
        pane.add(Mlocation);

        Mloc = new Choice();
        Mloc.setBounds(180,120,150,20);
        Mloc.add("Outside");
        Mloc.add("Inside");
        pane.add(Mloc);

        MeterType = new JLabel("Meter Type");
        MeterType.setBounds(50,160,100,20);
        pane.add(MeterType);

        Mtype = new Choice();
        Mtype.setBounds(180,160,150,20);
        Mtype.add("Electric Meter");
        Mtype.add("Solar Meter");
        Mtype.add("Smart Meter");
        pane.add(Mtype);

        Phase = new JLabel("Phase Code");
        Phase.setBounds(50,200,100,20);
        pane.add(Phase);

        Pcode = new Choice();
        Pcode.setBounds(180,200,150,20);
        Pcode.add("011");
        Pcode.add("022");
        Pcode.add("033");
        Pcode.add("044");
        Pcode.add("055");
        Pcode.add("066");
        Pcode.add("077");
        Pcode.add("088");
        Pcode.add("099");
        pane.add(Pcode);

        Bill = new JLabel("Bill Type");
        Bill.setBounds(50,240,100,20);
        pane.add(Bill);

        BillType = new Choice();
        BillType.setBounds(180,240,150,20);
        BillType.add("Normal");
        BillType.add("Industrial");
        pane.add(BillType);

        JLabel normal = new JLabel("30 Days Billing ...");
        normal.setBounds(50,280,150,20);
        pane.add(normal);

        JLabel Note = new JLabel("Note:-");
        Note.setBounds(50,320,150,20);
        pane.add(Note);

        JLabel note = new JLabel("By Default Bill is Calculated for 30 Days only");
        note.setBounds(50,340,300,20);
        pane.add(note);

        submit = new JButton("Submit");
        submit.setBounds(150,380,100,20);
        submit.addActionListener(this);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        pane.add(submit);

        setLayout(new BorderLayout());
        add(pane,"Center");

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icon/details.png"));
        Image Img = img.getImage().getScaledInstance(220,200,Image.SCALE_DEFAULT);
        ImageIcon IMg = new ImageIcon(Img);
        JLabel label = new JLabel(IMg);
        add(label,"West");

        setSize(700,500);
        setLocation(400,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            String smeter = Meter_no;
            String slocation = Mloc.getSelectedItem();
            String smetertype = Mtype.getSelectedItem();
            String sphase = Pcode.getSelectedItem();
            String sbill = BillType.getSelectedItem();
            String sdays = "30";

            String Query_Meter = "insert into meter_info values('"+smeter+"','"+slocation+"','"+smetertype+"','"+sphase+"','"+sbill+"','"+sdays+"')";

            try{
                Database db = new Database();
                db.statement.executeUpdate(Query_Meter);

                JOptionPane.showMessageDialog(null,"Information saved Successfully");
                setVisible(false);

            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
        else{

        }
    }

    public static void main(String[] args) {
        new Metre_inputs("");
    }
}
