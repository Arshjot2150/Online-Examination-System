import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddTopic extends JFrame implements ActionListener{
 
	  JLabel l1 = new JLabel();
	  JTextField addtopic = new JTextField(); 
	  Font font = new Font("",Font.PLAIN,22);
	  JButton add = new JButton();   
	  JOptionPane msg = new JOptionPane();
	
	
	 public AddTopic()
	 {
	   setLayout(null);
	   setTitle("Add a Topic");
	   setBounds(300,100, 800, 600);
	   setVisible(true);
	   setDefaultCloseOperation(EXIT_ON_CLOSE);
	   setResizable(false);
	   
	   l1.setText("Enter Topic Name");
  	   l1.setBounds(100,150,250,80);
	   l1.setFont(font);
  	   add(l1);
	   
  	   addtopic.setBounds(340,165,220,50);
  	   addtopic.setFont(font);
  	   add(addtopic);
	   
       add.setBounds(340,225,200,50);
       add.setText("Add Topic");
       add.setFont(font);
  	   add(add);
	 
	   add.addActionListener(this);
	 
	 
	 }
	 public void actionPerformed(ActionEvent ae)
	 {
		 
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://remotemysql.com/bJWPCJNbjl","bJWPCJNbjl","HR1aj2sZme");
			Statement s = c.createStatement();
			s.executeUpdate("insert into topics values('"+addtopic.getText()+"')");
			s.executeUpdate("create table "+addtopic.getText()+" (question text,ans1 text,ans2 text,ans3 text,ans4 text,correct text)");
			msg.showMessageDialog(this, "Topic is added to the database");
			super.dispose();
			Admin admin = new Admin();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			msg.showMessageDialog(this, "Topic Name already existed");
		}
		 
		
		 
	 }
	 
/*	 public static void main(String[] args) {
			AddTopic d = new AddTopic();
		}
*/	
}
