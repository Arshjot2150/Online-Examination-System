import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class AdminAuth extends JFrame implements ActionListener{

	  JLabel l1 = new JLabel();
	  JTextField secretkey = new JTextField(); 
	  Font font = new Font("",Font.PLAIN,22);
	  Font font1 = new Font("",Font.PLAIN,20);
	  JButton verify = new JButton();
	  JButton resend = new JButton();
	  JOptionPane msg = new JOptionPane(); 
	
	   public AdminAuth()
	   {
		   msg.showMessageDialog(this, "Please check your Email ID for verification key");
		   setLayout(null);
		   setTitle("Admin Authentication");
	  	   setBounds(300,100, 800, 600);
	  	   setVisible(true);
	  	   setDefaultCloseOperation(EXIT_ON_CLOSE);
	  	   setResizable(false);
		   
	  	   l1.setText("Enter OTP");
	  	   l1.setBounds(200,150,250,80);
		   l1.setFont(font);
	  	   add(l1);
		   
	  	   secretkey.setBounds(340,165,220,50);
	  	   secretkey.setFont(font);
	  	   add(secretkey);
		   
		   verify.setBounds(340,225,120,50);
	  	   verify.setText("Verify");
		   verify.setFont(font);
	  	   add(verify);
		   
	  	   resend.setBounds(100,300,220,50);
	  	   resend.setText("Resend Secret key");
	  	   resend.setFont(font1);
	  	   add(resend);
		   
	  	   verify.addActionListener(this);
	  	   resend.addActionListener(this);
	  	   
	  	   
	   }
	  
	   public void actionPerformed(ActionEvent ae)
	   {
		   if(ae.getSource()==verify)
		   {
			   String key =""; 
			   try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://remotemysql.com/bJWPCJNbjl","bJWPCJNbjl","HR1aj2sZme");
					Statement s = c.createStatement();
					ResultSet rs = s.executeQuery("select * from adminauth");
					while(rs.next())
					{
						key=rs.getString("key");
					}
					if(key.equals(secretkey.getText()))
					{
						super.dispose();
						msg.showMessageDialog(this, "Welcome Administrator");
						Admin admin = new Admin();
						
						
						
					}
					else
					{
						msg.showMessageDialog(this, "Invalid Verification Key");
					}
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.showMessageDialog(this, "Please check your internet connection!!");

				}
				
			   
			   
			   
		   }
		   if(ae.getSource()==resend)
		   {
			    super.dispose();
				PleaseWait wait = new PleaseWait();
				Random r = new Random();
				int i = r.nextInt();
				if(i<0)
					i=i*-1;
				String key = String.valueOf(i);
				String email ="";
				
				
				
				Properties p = new Properties();

				p.put("mail.smtp.host", "smtp.gmail.com");
				p.put("mail.smtp.port", "587");
				p.put("mail.smtp.starttls.enable", "true");
				p.put("mail.smtp.auth", "true");
				p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
				p.put("mail.debug", "true");
				
				BabyOfAuthenticator baby = new BabyOfAuthenticator();

				Session session = Session.getInstance(p, baby);

				MimeMessage message = new MimeMessage(session);
				
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://remotemysql.com/bJWPCJNbjl","bJWPCJNbjl","HR1aj2sZme");
					Statement s = c.createStatement();
					ResultSet rs = s.executeQuery("select * from adminauth");
					while(rs.next())
					{
						email=rs.getString("email");
					}
					Statement s1 = c.createStatement();
					s1.executeUpdate("delete from adminauth");
					s1.executeUpdate("insert into adminauth values('"+email+"','"+key+"')");
					
					
					message.setFrom(new InternetAddress("cetpademo19@gmail.com"));
					message.setSubject("Administrater Authentication");
					InternetAddress address = new InternetAddress(email);
					message.setRecipient(Message.RecipientType.TO, address);
					MimeBodyPart body1 = new MimeBodyPart();
					body1.setContent("<b>This is your OTP</b><br><br>"+key, "text/html");
					MimeMultipart part = new MimeMultipart();
					part.addBodyPart(body1);
					message.setContent(part);
					Transport.send(message);
							
							
					
					
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					msg.showMessageDialog(this, "Please check your internet connection!!");

					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.showMessageDialog(this, "Please check your internet connection!!");

				}
				
				
				
				wait.dispose();
				AdminAuth adminauth = new AdminAuth();
				
			   
			   
			   
		   }
		   
		   
	   }
/*	   public static void main(String[] args) {
			AdminAuth a = new AdminAuth();
		}
	*/
	
	
}
