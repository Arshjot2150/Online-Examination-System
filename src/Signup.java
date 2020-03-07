import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Signup extends JFrame implements ActionListener{

	JLabel name = new JLabel();
	JLabel email = new JLabel();
	JLabel mobile = new JLabel();
	JLabel qualification = new JLabel();
	JLabel year = new JLabel();
	JLabel pass = new JLabel();
	
	JTextField name1 = new JTextField();
	JTextField email1 = new JTextField();
	JTextField mobile1 = new JTextField();
	JTextField qualification1 = new JTextField();
//	JTextField year1 = new JTextField();
//	JTextField pass1 = new JTextField();
	JPasswordField pass1 = new JPasswordField();
	
	JOptionPane msg = new JOptionPane();
	
	JComboBox year1 = new JComboBox();
	
	JButton signup = new JButton(); 
	
	Font font = new Font("",Font.PLAIN,22);
	
	
	public Signup()
	{
		setLayout(null);
		setTitle("Sign Up");
	    setBounds(300,100, 800, 600);
  	    setVisible(true);
	  	setDefaultCloseOperation(EXIT_ON_CLOSE);
	  	setResizable(false);
		
	  	name.setBounds(100, 60, 200, 50);
	  	name.setText("Name");
	  	name.setFont(font);
	  	add(name);
	  	
	  	email.setBounds(100, 110, 200, 50);
	  	email.setText("Email");
	  	email.setFont(font);
	  	add(email);
	  	
	  	mobile.setBounds(100, 160, 200, 50);
	  	mobile.setText("Mobile");
	  	mobile.setFont(font);
	  	add(mobile);
	  	
	  	qualification.setBounds(100, 210, 200, 50);
	  	qualification.setText("Qualification");
	  	qualification.setFont(font);
	  	add(qualification);
	  	
	  	year.setBounds(100, 260, 200, 50);
	  	year.setText("Year of Passing");
	  	year.setFont(font);
	  	add(year);
	  	
	  	pass.setBounds(100, 310, 200, 50);
	  	pass.setText("Password");
	  	pass.setFont(font);
	  	add(pass);
	  	
	  	
	  	
	  	name1.setBounds(300, 60, 350, 50);
	  	name1.setFont(font);
	  	add(name1);
	  	
	  	email1.setBounds(300, 110, 350, 50);
	  	email1.setFont(font);
	  	add(email1);
	  	
	  	mobile1.setBounds(300, 160, 350, 50);
	  	mobile1.setFont(font);
	  	add(mobile1);
	  	
	  	qualification1.setBounds(300, 210, 350, 50);
	  	qualification1.setFont(font);
	  	add(qualification1);
	  	
	  	year1.setBounds(300, 260, 350, 50);
	  	year1.setFont(font);
	  
		String date =""+ LocalDate.now();
	  	String[] d = date.split("-");
	  	int year2 = Integer.parseInt(d[0]);
	  	
	  	year1.addItem(year2-3);
	  	year1.addItem(year2-2 );
	  	year1.addItem(year2-1);
		year1.addItem(year2);
		year1.addItem(year2+1);
	  	
	  	add(year1);
	  	
	  	pass1.setBounds(300, 310, 350, 50);
    	pass1.setFont(font);
	  	add(pass1);
	  	
	  	signup.setBounds(370, 400, 200, 50);
	  	signup.setFont(font);
	  	signup.setText("SIGN UP");
	  	add(signup);
	  	
	  	signup.addActionListener(this);
		
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==signup)
		{  
			String str = "";
			char[] pa = pass1.getPassword();
		    str=String.copyValueOf(pa);
			if(!(name1.getText().isEmpty() || email1.getText().isEmpty() || mobile1.getText().isEmpty() || qualification1.getText().isEmpty() || str.isEmpty()) )	
			{
               if(email1.getText().contains("@") )
        {    	   
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://remotemysql.com/bJWPCJNbjl","bJWPCJNbjl","HR1aj2sZme");
				Statement s = c.createStatement();
				s.executeUpdate("insert into students values('"+name1.getText()+"','"+email1.getText()+"','"+mobile1.getText()+"','"+qualification1.getText()+"','"+year1.getSelectedItem()+"','"+str+"')");
				msg.showMessageDialog(this, "Welcome "+name1.getText()+",We are Redirecting you to Login Page");
				super.dispose();
				Login l = new Login();
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				msg.showMessageDialog(this, "Email ID or Mobile No. already existed");
			}
        }	
               else
               {
            	   msg.showMessageDialog(this, "Please enter valid Email ID");
               }
			}
			else
			{
				msg.showMessageDialog(this, "No Fields are allowed to be empty");
			}
		}
	}
	
	
	public static void main(String[] args) {
		Signup sign = new Signup();
	}
	
	
	
	
}
