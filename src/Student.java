import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Student extends JFrame implements ActionListener{

	JButton signup = new JButton();
	JButton login = new JButton();
	
	Font font = new Font("",Font.PLAIN,22);
	
	
	public Student()
	{
		setLayout(null);
		setTitle("Student Interface");
	    setBounds(300,100, 800, 600);
  	    setVisible(true);
	  	setDefaultCloseOperation(EXIT_ON_CLOSE);
	  	setResizable(false);
		
	  	signup.setText("SIGN UP");
	  	login.setText("LOG IN");
		   
	  	signup.setBounds(300,200,200,50);
	  	login.setBounds (300,270,200,50);
		  
		add(signup);
		add(login);
	  	
		signup.addActionListener(this);
	  	login.addActionListener(this);
	  	
	}
	
	
/*
	public static void main(String[] args) {
	Student stu = new Student();
}	
*/	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==signup)
		{
			super.dispose();
			Signup sign = new Signup();
		}
		else
		{
		    super.dispose();
		    Login log = new Login();
		}
	}
	
	
}
