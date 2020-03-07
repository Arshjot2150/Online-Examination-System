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

public class Login extends JFrame implements ActionListener{

	JLabel l1 = new JLabel();
	JLabel l2 = new JLabel();
	JLabel l3 = new JLabel();
	JLabel l4 = new JLabel();
	Font font = new Font("",Font.PLAIN,22);
	
	JButton login = new JButton(); 
	JButton forgot = new JButton();
	
	JTextField id = new JTextField();
	JPasswordField pass = new JPasswordField();
	JOptionPane msg = new JOptionPane();
	
	public Login()
	{
		setLayout(null);
		setTitle("Log in");
	    setBounds(300,100, 800, 600);
  	    setVisible(true);
	  	setDefaultCloseOperation(EXIT_ON_CLOSE);
	  	setResizable(false);
		
		
		l1.setBounds(100, 100, 300, 50);
		l1.setText("Email ID");
		l1.setFont(font);
	  	add(l1);
	  	
		l2.setBounds(135, 125, 300, 50);
		l2.setText("or");
		l2.setFont(font);
	  	add(l2);
	  	
		l3.setBounds(100, 150, 300, 50);
		l3.setText("Mobile No.");
		l3.setFont(font);
	  	add(l3);
	  	
	  	
	  	l4.setBounds(100, 230, 200, 50);
	  	l4.setText("Password");
	  	l4.setFont(font);
	  	add(l4);
	  	
	  	id.setBounds(275, 125, 300, 50);
	  	id.setFont(font);
	  	add(id);
		

	  	pass.setBounds(275, 230, 300, 50);
	  	pass.setFont(font);
	  	add(pass);
	  	
	  	login.setBounds(350, 310, 150, 50);
	  	login.setText("LOG IN");
	  	login.setFont(font);
	  	add(login);
	  	
	  	forgot.setBounds(100, 410, 250, 50);
	  	forgot.setText("Forgot Password");
	  	forgot.setFont(font);
	  	add(forgot);
	  	
	  	login.addActionListener(this);
	  	forgot.addActionListener(this);
	  	
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String str = "";
		char[] pa = pass.getPassword();
	    str=String.copyValueOf(pa);
//		System.out.println(str);
	    int flag = 0;
		if(ae.getSource()==login)
		{
			if(!(id.getText().isEmpty() || str.isEmpty()))
			{
				if(id.getText().contains("@"))
				{
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection c = DriverManager.getConnection("jdbc:mysql://remotemysql.com/bJWPCJNbjl","bJWPCJNbjl","HR1aj2sZme");
						Statement s = c.createStatement();
						ResultSet rs = s.executeQuery("select * from students where email='"+id.getText()+"'");
						while(rs.next())
						{	
							flag=1;
							if(rs.getString("password").equals(str))
							{
							msg.showMessageDialog(this, "Welcome "+rs.getString("name"));
							super.dispose();
							Stutopics stu = new Stutopics(id.getText());
							}
							else
							{
							msg.showMessageDialog(this, "You entered Wrong Password");
							}
						}
						if(flag==0)
						{
							msg.showMessageDialog(this, "Please enter valid Email ID or Mobile No.");
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
				else
				{
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection c = DriverManager.getConnection("jdbc:mysql://remotemysql.com/bJWPCJNbjl","bJWPCJNbjl","HR1aj2sZme");
						Statement s = c.createStatement();
						ResultSet rs = s.executeQuery("select * from students where mobile='"+id.getText()+"'");
						
						while(rs.next())
						{	
							flag=1;
							if(rs.getString("password").equals(str))
							{
							msg.showMessageDialog(this, "Welcome "+rs.getString("name"));
							super.dispose();
							Stutopics stu = new Stutopics(id.getText());
							}
							else
							{
							msg.showMessageDialog(this, "You entered Wrong Password");
							}
						}
						if(flag==0)
						{
							msg.showMessageDialog(this, "Please enter valid Email ID or Mobile No.");
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
				
			}
			else
			{
				msg.showMessageDialog(this, "No Fields are allowed to be empty");
			}
			
			
		}
		if(ae.getSource()==forgot)
		{
			super.dispose();
			Forgot f = new Forgot();
			
			
		}
		
		
		
	}
	
	/*
	public static void main(String[] args) {
		Login sign = new Login();
	}
	
	*/
}
