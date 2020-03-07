import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.sql.*;

public class DelQues extends JFrame implements ActionListener {
	
	
	 JLabel l1 = new JLabel();
	 JLabel[] l = new JLabel[10];
	 JCheckBox[] check = new JCheckBox[10];
	 
	 
	 String[] str = new String[10];
	 String[] str1 = new String[10];
	 
	 Font font = new Font("",Font.PLAIN,22);
	 JComboBox select = new JComboBox();
	 JButton get = new JButton(); 
	 JOptionPane msg = new JOptionPane();
	 JButton delete = new JButton();
	 int i=0;
	 
	public DelQues()
	{
		setLayout(null);
		setTitle("Delete Question");
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
	  	
	  	
	  	get.setBounds(400,250,250,50);
	  	get.setText("Get Questions");
	  	get.setFont(font);
	  	add(get);
	  	
	  	
	  	get.addActionListener(this);
	  	
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
		if(ae.getSource()==get) 
	  {	
		try {
			setBounds(200,10, 1024, 800);
		//	l1.setVisible(false);
			select.setVisible(false);
			get.setVisible(false);
			
			l1.setText("Questions From Topic "+select.getSelectedItem());
		  	l1.setBounds(20,5,500,50);
			l1.setFont(font);
		  	add(l1);
		  	
		  	l[0]=new JLabel();
		  	l[0].setText("");
		  	l[0].setBounds(20,60,800,50);
		  	l[0].setFont(font);
		  	add(l[0]);
			
		  	l[1]=new JLabel();
		  	l[1].setText("");
		  	l[1].setBounds(20,120,800,50);
		  	l[1].setFont(font);
		  	add(l[1]);
			
		  	l[2]=new JLabel();
		  	l[2].setText("");
		  	l[2].setBounds(20,180,800,50);
		  	l[2].setFont(font);
		  	add(l[2]);
			
		  	l[3]=new JLabel();
		  	l[3].setText("");
		  	l[3].setBounds(20,240,800,50);
		  	l[3].setFont(font);
		  	add(l[3]);
			
		  	l[4]=new JLabel();
		  	l[4].setText("");
		  	l[4].setBounds(20,300,800,50);
		  	l[4].setFont(font);
		  	add(l[4]);
			
		  	l[5]=new JLabel();
		  	l[5].setText("");
		  	l[5].setBounds(20,360,800,50);
		  	l[5].setFont(font);
		  	add(l[5]);
			
		  	l[6]=new JLabel();
		  	l[6].setText("");
		  	l[6].setBounds(20,420,800,50);
		  	l[6].setFont(font);
		  	add(l[6]);
			
		  	l[7]=new JLabel();
		  	l[7].setText("");
		  	l[7].setBounds(20,480,800,50);
		  	l[7].setFont(font);
		  	add(l[7]);
			
		  	l[8]=new JLabel();
		  	l[8].setText("");
		  	l[8].setBounds(20,540,800,50);
		  	l[8].setFont(font);
		  	add(l[8]);
			
		  	l[9]=new JLabel();
		  	l[9].setText("");
		  	l[9].setBounds(20,600,800,50);
		  	l[9].setFont(font);
		  	add(l[9]);
	  	
		  	check[0]=new JCheckBox();
		  	check[0].setBounds(900,60,500,50);
		  	add(check[0]);
			check[0].setVisible(false);
		  	
			check[1]=new JCheckBox();
		  	check[1].setBounds(900,120,500,50);
		  	add(check[1]);
		  	check[1].setVisible(false);
		  	
		  	check[2]=new JCheckBox();
		  	check[2].setBounds(900,180,500,50);
		  	add(check[2]);
		  	check[2].setVisible(false);
		 
		  	check[3]=new JCheckBox();
		  	check[3].setBounds(900,240,250,50);
		  	add(check[3]);
		  	check[3].setVisible(false);
		  	
		  	check[4]=new JCheckBox();
		  	check[4].setBounds(900,300,500,50);
		  	add(check[4]);
		  	check[4].setVisible(false);
		
		  	check[5]=new JCheckBox();
		  	check[5].setBounds(900,360,500,50);
		  	add(check[5]);
		  	check[5].setVisible(false);
		  	
		  	check[6]=new JCheckBox();
		  	check[6].setBounds(900,420,500,50);
		  	add(check[6]);
		  	check[6].setVisible(false);
		  	
		  	check[7]=new JCheckBox();
		  	check[7].setBounds(900,480,500,50);
		  	add(check[7]);
		  	check[7].setVisible(false);
		  	
		  	check[8]=new JCheckBox();
		  	check[8].setBounds(900,540,500,50);
		  	add(check[8]);
		  	check[8].setVisible(false);
		  	
		  	check[9]=new JCheckBox();
		  	check[9].setBounds(900,600,500,50);
		  	add(check[9]);
		  	check[9].setVisible(false);
		  	
		  	delete.setText("Delete Checked Questions");
		  	delete.setBounds(625,680,340,50);
		  	delete.setFont(font);
		  	add(delete);
		  	
		  	delete.addActionListener(this);
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://remotemysql.com/bJWPCJNbjl","bJWPCJNbjl","HR1aj2sZme");
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from "+select.getSelectedItem());
			 i = 0;
			while(rs.next())
			{   
				str1[i]=rs.getString("question");
			 	str[i]="Q."+(i+1)+") "+rs.getString("question");
			 	check[i].setVisible(true);
			 	l[i].setText(str[i]);
			 	i++;
			}
			
	
					
		//	msg.showMessageDialog(this, "");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.showMessageDialog(this, "Please check your internet connection!!");

		}
		
	  }	
		if(ae.getSource()==delete)
		{
		
			for(int j =0;j<i;j++)
			{
				if(check[j].isSelected())
				{
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection c = DriverManager.getConnection("jdbc:mysql://remotemysql.com/bJWPCJNbjl","bJWPCJNbjl","HR1aj2sZme");
						Statement s = c.createStatement();
						s.executeUpdate("delete from "+select.getSelectedItem()+" where question='"+str1[j]+"'"); 
						
					
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
			msg.showMessageDialog(this, "Selected Question(s) were deleted from selected topics");
		    super.dispose();
		    Admin admin = new Admin();
		}
		
		
		
	}
	/*
public static void main(String[] args) {
		DelQues d = new DelQues();
	}
	*/
	

}
