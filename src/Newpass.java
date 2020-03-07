import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Newpass extends JFrame implements ActionListener {


	JLabel l1 = new JLabel();
	Font font = new Font("",Font.PLAIN,22);
	JPasswordField pass = new JPasswordField();
	JButton change = new JButton();
	String email1="";
	JOptionPane msg = new JOptionPane();
	
	public Newpass(String email)
	{
		email1=email;
		setLayout(null);
		setTitle("New Password");
	    setBounds(300,100, 800, 600);
  	    setVisible(true);
	  	setDefaultCloseOperation(EXIT_ON_CLOSE);
	  	setResizable(false);
		
	  	l1.setBounds(100, 100, 300, 50);
		l1.setText("Enter New Password");
		l1.setFont(font);
	  	add(l1);
		
	  	pass.setBounds(325, 100, 300, 50);
	  	pass.setFont(font);
	  	add(pass);
	  	
	  	change.setBounds(325, 200, 250, 50);
	  	change.setText("Change Password");
	  	change.setFont(font);
	  	add(change);
	  	
	  	change.addActionListener(this);
	  	
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String str = "";
		char[] pa = pass.getPassword();
	    str=String.copyValueOf(pa);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://remotemysql.com/bJWPCJNbjl","bJWPCJNbjl","HR1aj2sZme");
			Statement s = c.createStatement();
			s.executeUpdate("update students set password='"+str+"' where email='"+email1+"'");
			msg.showMessageDialog(this, "Password has been changed,We are redirecting you to Login Page");
			super.dispose();
			Login l = new Login();
			
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
