import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.net.*;
public class Server  implements ActionListener {
   
    JTextField text; 
    JPanel t;
   static  Box vertical = Box.createVerticalBox();
   static  JFrame j = new JFrame();
  static  DataOutputStream out;
   Server() {
        /*adding pannel for the name dp call icon on the frame  */
         j. setLayout(null);
         JPanel p = new JPanel();
          p.setBackground(new Color(7,94,84));
          p.setBounds(0,0,450,60);
          p.setLayout(null);
         j. add(p);

   /*Adding back icon */
          ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("3.png"));
          Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
          ImageIcon i3 = new ImageIcon(i2);
          JLabel back = new JLabel(i3);
          back.setBounds(5,15,25,25);
          p.add(back);

  /*Action on clicking back buttion */
          back.addMouseListener(new MouseAdapter(){
               public void mouseClicked(MouseEvent ae) {
                 System.exit(0);
               }
          });


   /*Adding a profile picture  */
   
   ImageIcon i4= new ImageIcon(ClassLoader.getSystemResource("profile3.png"));
          Image i5 = i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
          ImageIcon i6 = new ImageIcon(i5);
          JLabel profile = new JLabel(i6);
          profile.setBounds(40,10,50,50);
          p.add(profile);

  /* Adding video call icon  */
     ImageIcon i7= new ImageIcon(ClassLoader.getSystemResource("video.png"));
          Image i8 = i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
          ImageIcon i9 = new ImageIcon(i8);
          JLabel video = new JLabel(i9);
          video.setBounds(300,20,30,30);
          p.add(video);


           /* Adding telephone icon  */
     ImageIcon i10= new ImageIcon(ClassLoader.getSystemResource("phone.png"));
          Image i11 = i10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
          ImageIcon i12 = new ImageIcon(i11);
          JLabel tele = new JLabel(i12);
          tele.setBounds(350,20,35,30);
          p.add(tele);


          /* Adding 3 dot  icon  */
     ImageIcon i13= new ImageIcon(ClassLoader.getSystemResource("3icon.png"));
          Image i14 = i13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
          ImageIcon i15 = new ImageIcon(i14);
          JLabel dot = new JLabel(i15);
          dot.setBounds(400,20,10,25);
          p.add(dot);

          /*adding name and active status  on the frame  */
          JLabel name = new JLabel("Admin1");
          name.setBounds(100,15,100,25);
          name.setForeground(Color.WHITE);
          name.setFont(new Font("SAN_SEFIF", Font.BOLD,16));
          p.add(name);

           JLabel status = new JLabel("Active now ");
          status.setBounds(100,35,100,25);
          status.setForeground(Color.WHITE);
          status.setFont(new Font("SAN_SEFIF", Font.BOLD,12));
          p.add(status);
        
          /*Creating new pannel for the msg display */
           t = new JPanel();
          t.setBounds(5,75,440,570);
          
          j.add(t);
        /*adding a text area */
          text = new JTextField();
          text.setBounds(5,655,310,40);
         j. add(text);
          text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
          j. add(text);

        /*adding a send button */
          JButton send = new JButton("Send");
          send.setBounds(320,655,123,40);
          send.setBackground(new Color(7,94,84));
          send.setForeground(Color.WHITE);
          send.addActionListener(this);
          send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
          j.add(send);


            

         j. setSize(450,700);
          j.setLocation(200,50);
          j.setUndecorated(true);
          j.getContentPane().setBackground(Color.WHITE);
          j.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        try{ /*we use try block because write UTF thorws exectpion */
        String msg =  text.getText();
        
        JPanel p2 = FormatLabel(msg);

        
        t.setLayout(new BorderLayout());
        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);

        vertical.add(Box.createVerticalStrut(10));
        t.add(vertical,BorderLayout.PAGE_START);
        out.writeUTF(msg);
        text.setText("");


        j.repaint();
        j.invalidate();
        j.validate();
        }
        catch(Exception ae){
            ae.printStackTrace();
        }

    }

    public static JPanel FormatLabel(String out){
         JPanel panel = new JPanel();
         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         JLabel output =  new JLabel(out);
          output. setFont(new Font("Tahoma", Font.PLAIN, 15));
         panel.add(output);
          Calendar cal = Calendar.getInstance();
          SimpleDateFormat sf = new SimpleDateFormat("hh:mm");
          JLabel time = new JLabel();
          time.setText(sf.format(cal.getTime()));
          panel.add(time);
         return panel;
    }
    public static void main(String args[]){
        new Server();
         try {
            ServerSocket skt = new ServerSocket(5000);
            while(true){
                Socket s=skt.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                out = new DataOutputStream(s.getOutputStream());

              while(true){
               String msg = in.readUTF();
               JPanel  panel = FormatLabel(msg);

               JPanel left = new JPanel(new BorderLayout());
               left.add(panel,BorderLayout.LINE_START);
               vertical.add(left);
                j.validate();
            }


            }
        }
        catch (Exception e) {
     e.printStackTrace();
    }
    }
}