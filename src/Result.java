import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Result extends JFrame implements ActionListener {

	JLabel l1 = new JLabel();
	JLabel l2 = new JLabel();
	JLabel l3 = new JLabel();
	JLabel l4 = new JLabel();
	JLabel l5 = new JLabel();
	JLabel l6 = new JLabel();
	JLabel l7 = new JLabel();
	JLabel l8 = new JLabel();
	JLabel l9 = new JLabel();
	
	JButton logout = new JButton();
	
	JButton getresult = new  JButton();

	JOptionPane msg = new JOptionPane();  
	
	Font font = new Font("",Font.PLAIN,22);
	Font font1 = new Font("",Font.BOLD,28);
	
	String str="";
	
	class BabyOfAuthenticator extends Authenticator
	{
		public PasswordAuthentication getPasswordAuthentication()
		{
			PasswordAuthentication pa = 
		    new PasswordAuthentication("cetpademo19@gmail.com","cetpademo@123");
		  

			return pa;
		}
	}
	
	
	public Result()
	{
		setLayout(null);
		setTitle("Result");
		setBounds(300,100, 800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		l1.setBounds(300,20,500,50);
		l1.setFont(font1);
		l1.setText("Your Report");
		add(l1);
		
		l2.setBounds(50,100,600,50);
		l2.setFont(font);
		add(l2);
		
		l3.setBounds(50,150,600,50);
		l3.setFont(font);
		add(l3);
		
		l4.setBounds(50,200,600,50);
		l4.setFont(font);
		add(l4);
		
		l5.setBounds(50,250,600,50);
		l5.setFont(font);
		add(l5);
		
		l6.setBounds(50,300,600,50);
		l6.setFont(font);
		add(l6);
		
		l7.setBounds(50,350,600,50);
		l7.setFont(font);
		add(l7);
		
		l8.setBounds(50,400,600,50);
		l8.setFont(font);
		add(l8);
		
		l9.setBounds(50,450,600,50);
		l9.setFont(font);
		add(l9);
		
		
		logout.setBounds(550,500,150,50);
		logout.setFont(font);
		logout.setText("Log Out");
		add(logout);
		
		
		logout.addActionListener(this);
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://remotemysql.com/bJWPCJNbjl","bJWPCJNbjl","HR1aj2sZme");
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from tests");
			while(rs.last())
			{
				l2.setText("Student ID----------------->  "+rs.getString("email"));
				l3.setText("Mobile No.----------------->  "+rs.getString("mobile"));
				l4.setText("Topic Name--------------->  "+rs.getString("topic"));
				l5.setText("Date------------------------->  "+rs.getString("date"));
				l6.setText("Total Questions---------->  "+rs.getString("total"));
				l7.setText("Correct Answers--------->  "+rs.getString("correct"));
				l8.setText("Wrong Anwsers---------->  "+rs.getString("wrong"));
				l9.setText("Skipped Answers-------->  "+rs.getString("skipped"));
				
				
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
				message.setSubject("Your Result");
				message.setFrom(new InternetAddress("cetpademo19@gmail.com"));
				
				str=str+"<html><head><title>Result</title></head><body>Student ID : "+rs.getString("email")+"<br>Mobile No. : "+rs.getString("mobile")+"<br>Topic Name : "+rs.getString("topic")+"<br>Date : "+rs.getString("date")+"<br>Total Questions : "+rs.getString("total")+"<br>Correct Answers : "+rs.getString("correct")+"<br>Wrong Answers : "+rs.getString("wrong")+"<br>Skipped Answers : "+rs.getString("skipped")+"</body></html>";                        
				
				InternetAddress address = new InternetAddress(rs.getString("email"));
		    	message.setRecipient(Message.RecipientType.TO, address);
		    	MimeBodyPart body1 = new MimeBodyPart();
		        body1.setContent(str, "text/html");
		        MimeMultipart part = new MimeMultipart();
		        part.addBodyPart(body1);
		        message.setContent(part);
		        Transport.send(message);
		        msg.showMessageDialog(this,"Result is sent to Registered Email ID "+rs.getString("email"));
				
				break;
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.showMessageDialog(this, "Please check your internet connection!!");

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.showMessageDialog(this, "Please check your internet connection!!");

		}
		
		
	}
	

	public void actionPerformed(ActionEvent ae)
	{
		super.dispose();
		Student stu = new Student();
		
	}
	/*
	public static void main(String[] args) {
		Result r = new Result();
	
	}
	*/
	
	
}
