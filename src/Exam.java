import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Exam extends JFrame implements ActionListener{

	JOptionPane msg = new JOptionPane();
	JLabel l1 = new JLabel();

	 JLabel l2 = new JLabel();		
	 JLabel l3 = new JLabel();
	 JLabel l4 = new JLabel();
	 JLabel l5 = new JLabel();
	 JLabel l6 = new JLabel();
	 JLabel l7 = new JLabel();
	 
	 JRadioButton ans1 = new JRadioButton();
	 JRadioButton ans2 = new JRadioButton();
	 JRadioButton ans3 = new JRadioButton();
	 JRadioButton ans4 = new JRadioButton();
	 ResultSet rs,rs1,rs2,rs3,rs4;
	 Statement s,s1,s2,s3,s4;
	 Connection c;
	 ButtonGroup bg = new ButtonGroup();
	 
	 JButton next = new JButton();
	 
	 JButton skip = new JButton();
	 
	 Font font = new Font("",Font.PLAIN,22);
	 int i =1;
	 int j=2;
	 String topic1="";
	 String id1="";
	 String[] id2 = new String[2];
	 String question="";
	 String tabname="";
	 String mobile="";
	 String email="";
	 int total=0;
	 int correct=0;
	 int wrong=0;
	 int skipped=0;
	 int count=0;
	 

	public Exam(String topic,String id)
	{
		id1=id;
		topic1=topic;
		setLayout(null);
		setTitle("Exam");
		setBounds(300,100, 800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
			
	  	
	  	l2.setText("");
	  	l2.setBounds(15,70,700,80);
	  	l2.setFont(font);
	  	add(l2);
	  	
	  	ans1.setText(""); 
	  	ans1.setBounds(20,210,400,30);
	  	ans1.setFont(font);
	  	add(ans1);
	  	
	  	ans2.setText("");
	  	ans2.setBounds(20,260,400,30);
	  	ans2.setFont(font);
	  	add(ans2);
	  	
	  	ans3.setText("");
	  	ans3.setBounds(20,310,400,30);
	  	ans3.setFont(font);
	  	add(ans3);
	  	
	  	ans4.setText("");
	  	ans4.setBounds(20,360,400,30);
	  	ans4.setFont(font);
	  	add(ans4);
	  	
	  	bg.add(ans1);
	  	bg.add(ans2);
	  	bg.add(ans3);
	  	bg.add(ans4);
	  	
	  	next.setText("Next");
	  	next.setBounds(600,450,120,50);
	  	next.setFont(font);
	  	add(next);
	  	
		skip.setText("Skip");
		skip.setBounds(100,450,120,50);
		skip.setFont(font);
	  	add(skip);
	  	
	  	
	  	next.addActionListener(this);
	  	skip.addActionListener(this);
	  	
	    firstQuestion();
			
	  	
		
		
	}
	
	public void firstQuestion()
	{
		id2 = id1.split("@");
   
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://remotemysql.com/bJWPCJNbjl","bJWPCJNbjl","HR1aj2sZme");
			s4=c.createStatement();
			
			 if(id1.contains("@"))
			    {
			    	email=id1;
			    	s1 = c.createStatement();
			    	rs1 = s1.executeQuery("select * from students where email='"+email+"'");
			    	while(rs1.next())
			    	{
			    		mobile=rs1.getString("mobile");				
			    	}
			    }
			    else
			    {
			    	mobile=id1;
			    	s1 = c.createStatement();
			    	rs1 = s1.executeQuery("select * from students where mobile='"+mobile+"'");
			    	while(rs1.next())
			    	{
			    		email=rs1.getString("email");				
			    	}
			    }
			 s = c.createStatement();
			s.executeUpdate("insert into testno values('"+email+"','"+mobile+"','"+topic1+"',now())");
			rs4 = s4.executeQuery("select * from testno"); 
			while(rs4.next())
			{
				count++;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		e1.printStackTrace();
		msg.showMessageDialog(this, "Please check your internet connection!!");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		tabname=topic1+"_"+id2[0]+"_"+count;
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://remotemysql.com/bJWPCJNbjl","bJWPCJNbjl","HR1aj2sZme");
		    
		    s3 = c.createStatement();
		    s3.executeUpdate("create table "+tabname+" (question text,ans text)");
    	    rs = s.executeQuery("select * from "+topic1);
			while(rs.next())
			{
				question=rs.getString("question");
				l2.setText("Q."+i+")  "+rs.getString("question"));
				ans1.setText("  "+rs.getString("ans1"));
				ans2.setText("  "+rs.getString("ans2"));
				ans3.setText("  "+rs.getString("ans3"));
				ans4.setText("  "+rs.getString("ans4"));
				break;
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
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==next)
		{		

		if(ans1.isSelected())
		{
	//		msg.showMessageDialog(this, "Option 1 is selected");
			try {
				s3.executeUpdate("insert into "+tabname+" values('"+question+"','1')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				msg.showMessageDialog(this, "Please check your internet connection!!");

			}
		}
		else if(ans2.isSelected())
		{
	//		msg.showMessageDialog(this, "Option 2 is selected");
			try {
				s3.executeUpdate("insert into "+tabname+" values('"+question+"','2')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				msg.showMessageDialog(this, "Please check your internet connection!!");

			}
		}
		else if(ans3.isSelected())
		{
	//		msg.showMessageDialog(this, "Option 3 is selected");
			try {
				s3.executeUpdate("insert into "+tabname+" values('"+question+"','3')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				msg.showMessageDialog(this, "Please check your internet connection!!");

			}
		}
		else if(ans4.isSelected())
		{
	//		msg.showMessageDialog(this, "Option 4 is selected");
			try {
				s3.executeUpdate("insert into "+tabname+" values('"+question+"','4')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				msg.showMessageDialog(this, "Please check your internet connection!!");

			}
		}
		else
		{
			msg.showMessageDialog(this, "Question was skipped");
			try {
				s3.executeUpdate("insert into "+tabname+" values('"+question+"','0')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				msg.showMessageDialog(this, "Please check your internet connection!!");

			}
		}
		
		
		
		
		bg.clearSelection();
		
		try {
     		Class.forName("com.mysql.jdbc.Driver");

			while(rs.next())
			{
				i++;
				question=rs.getString("question");
				l2.setText("Q."+i+")  "+rs.getString("question"));
				ans1.setText("  "+rs.getString("ans1"));
				ans2.setText("  "+rs.getString("ans2"));
				ans3.setText("  "+rs.getString("ans3"));
				ans4.setText("  "+rs.getString("ans4"));
				break;
			}
			if(rs.isAfterLast())
			{
			    msg.showMessageDialog(this, "That was last question");
			   
			    
			    rs3 = s.executeQuery("select * from "+topic1);
			    s2=c.createStatement();
			    rs2=s2.executeQuery("select * from "+tabname);
			    while(rs3.next() && rs2.next())
			    {
			        total++;
			     	if(rs3.getString("correct").equals(rs2.getString("ans")))
			     	{
			     		correct++;
			     	}
			     	else if(rs2.getString("ans").equals("0"))
			     	{
			     		skipped++;
			     	}
			     	else
			     	{
			     		wrong++;
			     	}
			    }
			    
			    
			    s.executeUpdate("insert into tests values('"+email+"','"+mobile+"','"+topic1+"',now(),'"+total+"','"+correct+"','"+wrong+"','"+skipped+"')");
			   
                super.dispose();
                Result r = new Result();
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
		
		if(ae.getSource()==skip)
		{
			msg.showMessageDialog(this, "Question was skipped");
			try {
				s3.executeUpdate("insert into "+tabname+" values('"+question+"','0')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				msg.showMessageDialog(this, "Please check your internet connection!!");

			}
			
			
			bg.clearSelection();
			
			try {
	     		Class.forName("com.mysql.jdbc.Driver");

				while(rs.next())
				{
					i++;
					question=rs.getString("question");
					l2.setText("Q."+i+")  "+rs.getString("question"));
					ans1.setText("  "+rs.getString("ans1"));
					ans2.setText("  "+rs.getString("ans2"));
					ans3.setText("  "+rs.getString("ans3"));
					ans4.setText("  "+rs.getString("ans4"));
					break;
				}
				if(rs.isAfterLast())
				{
				    msg.showMessageDialog(this, "That was last question");
				    if(id1.contains("@"))
				    {
				    	email=id1;
				    	s1 = c.createStatement();
				    	rs1 = s1.executeQuery("select * from students where email='"+email+"'");
				    	while(rs1.next())
				    	{
				    		mobile=rs1.getString("mobile");				
				    	}
				    }
				    else
				    {
				    	mobile=id1;
				    	s1 = c.createStatement();
				    	rs1 = s1.executeQuery("select * from students where mobile='"+mobile+"'");
				    	while(rs1.next())
				    	{
				    		email=rs1.getString("email");				
				    	}
				    }
				    
				    rs3 = s.executeQuery("select * from "+topic1);
				    s2=c.createStatement();
				    rs2=s2.executeQuery("select * from "+tabname);
				    while(rs3.next() && rs2.next())
				    {
				        total++;
				     	if(rs3.getString("correct").equals(rs2.getString("ans")))
				     	{
				     		correct++;
				     	}
				     	else if(rs2.getString("ans").equals("0"))
				     	{
				     		skipped++;
				     	}
				     	else
				     	{
				     		wrong++;
				     	}
				    }
				    
				    
				    s.executeUpdate("insert into tests values('"+email+"','"+mobile+"','"+topic1+"',now(),'"+total+"','"+correct+"','"+wrong+"','"+skipped+"')");
				    super.dispose();
				    Result r = new Result();
			
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
	/*
	public static void main(String[] args) {
		
		Exam e = new Exam("Java","arshjotsingh2150@gmail.com");
		
	}
	*/
	
}
