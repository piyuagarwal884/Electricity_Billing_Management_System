package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {
    Splash(){
        //Picture add krne k liye uski location s
        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icon/Splash/Splash.jpg"));//yaha s picture ka address define hua h
        Image imageOne = imageIcon1.getImage().getScaledInstance(550,400,Image.SCALE_DEFAULT);//yaha hamne picture kei height define kei h
        ImageIcon imageIcon2 = new ImageIcon(imageOne);//Yaha hamne picture ko add krne k liye second imageicon bnaya h
        JLabel imageLabel = new JLabel(imageIcon2);//Ye jo h picture ko store krega khud m
        Component add = add(imageLabel);//Ye picture ko as a label put krega dialogue box m

        //Yaha hamne Java frame bnayi h jo kei hame dikhegi
        setSize(600,400);
        setLocation(500,200);
        setVisible(true);//Jo bhi code likhna h iske upper hei likhna h

        //yaha sleep fuction use kiya h jisse hamari window 3 second k liye khulegi phir apne aap band ho jaayegi
        try {
            Thread.sleep(3000);
            setVisible(false);
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Splash();
    }
}