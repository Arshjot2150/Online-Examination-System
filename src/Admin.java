import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Admin extends JFrame implements ActionListener {

	JButton addtopic = new JButton();
    JButton deltopic = new JButton();
    JButton addq = new JButton();
    JButton delq = new JButton();
 	
	
	public Admin()
	{
		 setLayout(null);
		   setTitle("Admin Dashboard");
	  	   setBounds(300,100, 800, 600);
	  	   setVisible(true);
	  	   setDefaultCloseOperation(EXIT_ON_CLOSE);
	  	   setResizable(false);
		
	  	addtopic.setText("ADD A TOPIC");
	  	deltopic.setText("DELETE A TOPIC");
	  	addq.setText("ADD QUESTIONS IN TOPIC");
	  	delq.setText("DELETE QUESTIONS IN TOPIC");
	  	
  	   
	  	addtopic.setBounds (275,75,250,50);
	  	deltopic.setBounds (275,175,250,50);
	  	addq.setBounds     (275,275,250,50);
	  	delq.setBounds     (275,375,250,50);
	  	
  	   
  	   add(addtopic);
  	   add(deltopic);
  	   add(addq);
  	   add(delq);
  	  
	  	   
  	addtopic.addActionListener(this);
  	deltopic.addActionListener(this);
  	addq.addActionListener(this);
  	delq.addActionListener(this);
  	
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==addtopic)
		{
			super.dispose();
			AddTopic at = new AddTopic();
			
			
		}
		if(ae.getSource()==deltopic)
		{
			super.dispose();
			DelTopic dt = new DelTopic();
			
			
		}
		if(ae.getSource()==addq)
		{
			super.dispose();
			AddQues aq = new AddQues(); 
			
			
		}
		if(ae.getSource()==delq)
		{
			super.dispose();
			DelQues dq = new DelQues();
			
			
		}
		
		
	}
	/*
	public static void main(String[] args)
	{
		Admin admin = new Admin();
	}
	*/
}



