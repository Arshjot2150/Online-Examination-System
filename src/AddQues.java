import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class AddQues extends JFrame implements ActionListener 
{


	 JLabel l1 = new JLabel();
	 JTextArea ques = new JTextArea();
	 JTextArea a1 = new JTextArea();
	 JTextArea a2 = new JTextArea();
	 JTextArea a3 = new JTextArea();
	 JTextArea a4 = new JTextArea();
//	 JTextArea a5 = new JTextArea();
	 JScrollPane pane1;
	 
	 JLabel l2 = new JLabel();		
	 JLabel l3 = new JLabel();
	 JLabel l4 = new JLabel();
	 JLabel l5 = new JLabel();
	 JLabel l6 = new JLabel();
	 JLabel l7 = new JLabel();
	 
	 JButton submit = new JButton();
	 
	 Font font = new Font("",Font.PLAIN,22);
	 Font font1 = new Font("",Font.ITALIC,22);
	 JComboBox select = new JComboBox(); 
	 JComboBox select1 = new JComboBox(); 
	 JOptionPane msg = new JOptionPane();
	 
	public AddQues()
	{
		setLayout(null);
		setTitle("Add Questions");
		setBounds(300,100, 800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
			
		l1.setText("Select Topic Name");
	  	l1.setBounds(100,20,250,80);
		l1.setFont(font);
	  	add(l1);
	  	
	  	l2.setText("Question");
	  	l2.setBounds(5,110,250,80);
	  	l2.setFont(font);
	  	add(l2);
	  	
	  	l3.setText("Ans.1");
	  	l3.setBounds(20,310,100,30);
	  	l3.setFont(font);
	  	add(l3);
	  	
	  	l4.setText("Ans.2");
	  	l4.setBounds(20,350,100,30);
	  	l4.setFont(font);
	  	add(l4);
	  	
	  	l5.setText("Ans.3");
	  	l5.setBounds(20,390,100,30);
	  	l5.setFont(font);
	  	add(l5);
	  	
	  	l6.setText("Ans.4");
	  	l6.setBounds(20,430,100,30);
	  	l6.setFont(font);
	  	add(l6);
	  	
	  	l7.setText("Correct Answer");
	  	l7.setBounds(470,275,200,65);
	  	l7.setFont(font);
	  	add(l7);
		
		a1.setFont(font);
	  	add(a1);
	  	a1.setBounds(100,300,300,35);
	  	
	  	a2.setBounds(100,345,300,35);
	  	a2.setFont(font);
	  	add(a2);
	  	
	  	a3.setBounds(100,390,300,35);
	  	a3.setFont(font);
	  	add(a3);
		
	  	a4.setBounds(100,435,300,35);
	  	a4.setFont(font);
	  	add(a4);
	  	
	  	select1.setBounds(470,325,230,50);
	  	select1.setFont(font1);
	  	add(select1);
	  	select1.addItem("Option 1");
	  	select1.addItem("Option 2");
	  	select1.addItem("Option 3");
	  	select1.addItem("Option 4");
	  	

	  	select.setBounds(400,35,250,50);
	  	select.setFont(font);
	  	add(select);
	  	
	  	
	  	pane1= new JScrollPane(ques);
	  	pane1.setBounds(100,130,550,150);  
	  	ques.setFont(font);
	  	ques.setLineWrap(true);
	  	add(pane1);
	  	
	  	
	  	submit.setText("Submit");
	  	submit.setFont(font);
	  	submit.setBounds(475,400,150,65);
	  	add(submit);
	  	
	  	submit.addActionListener(this);
	  	
	  
	  	 try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://remotemysql.com/bJWPCJNbjl","bJWPCJNbjl","HR1aj2sZme");
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("select * from topics");
				while(rs.next())
				{
					select.addItem(rs.getString("topics"));
					
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
		if(ae.getSource()==submit)
		{
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://remotemysql.com/bJWPCJNbjl","bJWPCJNbjl","HR1aj2sZme");
				Statement s = c.createStatement();
				int a = select1.getSelectedIndex();
				a=a+1;
			    String str=""+a;
				s.executeUpdate("insert into "+select.getSelectedItem()+" values('"+ques.getText()+"','"+a1.getText()+"','"+a2.getText()+"','"+a3.getText()+"','"+a4.getText()+"','"+str+"')");
				msg.showMessageDialog(this, "Question is added to selected topic");
				super.dispose();
				Admin admin = new Admin();
				
				
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
		AddQues a = new AddQues();
	}
	*/
	
	
}
