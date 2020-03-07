

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.sql.*;

public class Stutopics extends JFrame implements ActionListener {
	
	
	 JLabel l1 = new JLabel();
	 Font font = new Font("",Font.PLAIN,22);
	 JComboBox select = new JComboBox();
	 JButton delete = new JButton(); 
	 JOptionPane msg = new JOptionPane();
	 String id1="";
	 
	public Stutopics(String id)
	{
		id1=id;
		setLayout(null);
		setTitle("Select a Topic");
		setBounds(300,100, 800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
			
		l1.setText("Select Topic Name");
	  	l1.setBounds(100,150,250,80);
		l1.setFont(font);
	  	add(l1);
	  	
	  	select.setBounds(400,165,250,50);
	  	select.setFont(font);
	  	add(select);
	  	
	  	
	  	
	  	delete.setBounds(280,300,200,50);
	  	delete.setText("Next");
	  	delete.setFont(font);
	  	add(delete);
	  	
	  	delete.addActionListener(this);
	  	
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
		super.dispose();
		Guide g = new Guide((String)select.getSelectedItem(),id1);
		
		
	}
/*	public static void main(String[] args) {
		Stutopics s = new Stutopics();
	}
	*/

}
