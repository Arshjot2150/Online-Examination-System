import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.sql.*;

public class DelTopic extends JFrame implements ActionListener {
	
	
	 JLabel l1 = new JLabel();
	 Font font = new Font("",Font.PLAIN,22);
	 JComboBox select = new JComboBox();
	 JButton delete = new JButton(); 
	 JOptionPane msg = new JOptionPane();
	 
	public DelTopic()
	{
		setLayout(null);
		setTitle("Delete a Topic");
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
	  	
	  	
	  	
	  	delete.setBounds(400,250,250,50);
	  	delete.setText("Delete Topic");
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
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://remotemysql.com/bJWPCJNbjl","bJWPCJNbjl","HR1aj2sZme");
			Statement s = c.createStatement();
			Statement s1 = c.createStatement();
			s1.executeUpdate("delete from topics where topics='"+select.getSelectedItem()+"'");
			s1.executeUpdate("drop table if exists "+select.getSelectedItem());
			msg.showMessageDialog(this, "Topic is deleted from the database");
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
	/*
	public static void main(String[] args)
	{
		DelTopic d = new DelTopic();
	}
    */
}
