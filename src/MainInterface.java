import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

class BabyOfAuthenticator extends Authenticator
{
	public PasswordAuthentication getPasswordAuthentication()
	{
		PasswordAuthentication pa = 
	    new PasswordAuthentication
	    ("cetpademo19@gmail.com", "cetpademo@123");

		return pa;
	}
}

public class MainInterface extends JFrame implements ActionListener {

	JButton admin = new JButton();
	JButton student = new JButton();
	JOptionPane msg = new JOptionPane();
	
	
	
	public MainInterface()
	{
	   setLayout(null);
	   setTitle("Main Interface");
  	   setBounds(300,100, 800, 600);
  	   setVisible(true);
  	   setDefaultCloseOperation(EXIT_ON_CLOSE);
  	   setResizable(false);
		
  	   admin.setText("ADMIN");
	   student.setText("STUDENT");
	
	   
	   admin.setBounds    (300,180,200,50);
	   student.setBounds (300,280,200,50);
	
	   
	   add(admin);
	   add(student);
	  
	   
	   admin.addActionListener(this);
	   student.addActionListener(this);
	 
  	   
  	 
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()==admin)
		{
			super.dispose();
			PleaseWait wait = new PleaseWait();
			Random r = new Random();
			int i = r.nextInt(9999);
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
				Connection c = DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net/sql12372328","sql12372328","kGTAF8RBfA");
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
				body1.setContent("<b>This is your Secret key</b><br><br>"+key, "text/html");
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
		if(ae.getSource()==student)
		{
			super.dispose();
			Student stu = new Student();
		}
		
	}

	
	
	
}
