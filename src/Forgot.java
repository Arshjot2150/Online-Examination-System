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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Forgot extends JFrame implements ActionListener {

	JLabel l1 = new JLabel();
	JLabel l2 = new JLabel();
	JTextField id = new JTextField();
	Font font = new Font("",Font.PLAIN,22);
	JTextField pass = new JTextField();
	JButton otp = new JButton();
	JButton otp1 = new JButton();
	JOptionPane msg = new JOptionPane();
	String key="";
	
	public Forgot() {
		
		setLayout(null);
		setTitle("Forgot Password");
	    setBounds(300,100, 800, 600);
  	    setVisible(true);
	  	setDefaultCloseOperation(EXIT_ON_CLOSE);
	  	setResizable(false);
	  	
	  	l1.setBounds(100, 100, 300, 50);
		l1.setText("Email ID");
		l1.setFont(font);
	  	add(l1);
		
	  	id.setBounds(275, 100, 300, 50);
	  	id.setFont(font);
	  	add(id);
	  	
		l2.setBounds(100, 170, 200, 50);
	  	l2.setText("OTP");
	  	l2.setFont(font);
	  	l2.setVisible(false);
	  	add(l2);
	  	
	  	pass.setBounds(275, 170, 300, 50);
	  	pass.setFont(font);
	  	pass.setVisible(false);
	  	add(pass);
	  	
	  	otp.setBounds(275, 270, 200, 50);
	  	otp.setText("Request OTP");
	  	otp.setFont(font);
	  	otp.setVisible(true);
	  	add(otp);
	  	
	  	otp1.setBounds(275, 270, 200, 50);
	  	otp1.setText("Verify OTP");
	  	otp1.setFont(font);
	  	otp1.setVisible(false);
	  	add(otp1);
	  	
	  	otp.addActionListener(this);
	  	otp1.addActionListener(this);
	  	
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==otp)
		{
			PleaseWait wait = new PleaseWait();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net/sql12372328","sql12372328","kGTAF8RBfA");
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("select * from students where email='"+id.getText()+"'");
				
				if(rs.next())
				{
					
					Random r = new Random();
					int i = r.nextInt();
					if(i<0)
						i=i*-1;
					key = String.valueOf(i);
					
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
						message.setFrom(new InternetAddress("cetpademo19@gmail.com"));
						message.setSubject("Student Verification");
						InternetAddress address = new InternetAddress(id.getText());
						message.setRecipient(Message.RecipientType.TO, address);
						MimeBodyPart body1 = new MimeBodyPart();
						body1.setContent("<b>This is your OTP</b><br><br>"+key, "text/html");
						MimeMultipart part = new MimeMultipart();
						part.addBodyPart(body1);
						message.setContent(part);
						Transport.send(message);
						
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						msg.showMessageDialog(this, "Please check your internet connection!!");

					}
					
					wait.dispose();
					msg.showMessageDialog(this, "An OTP is sent to your email ID");
					otp.setVisible(false);
					l2.setVisible(true);
					pass.setVisible(true);
					otp1.setVisible(true);
					
				}
				else
				{
					wait.dispose();
					msg.showMessageDialog(this, "This Email ID is not registered");
					
				}
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				msg.showMessageDialog(this, "Please check your internet connection!!");

			}
			
			
			
		}
		if(ae.getSource()==otp1)
		{
			if(pass.getText().equals(key))
			{
				msg.showMessageDialog(this, "Student Verified");
				super.dispose();
				Newpass n = new Newpass(id.getText());
			}
			else
			{
				msg.showMessageDialog(this, "Invalid OTP");
			}
		}
		
	}
	
	public static void main(String[] args) {
		Forgot f = new Forgot();
	}
	
	
	
	
	
	
	
}
