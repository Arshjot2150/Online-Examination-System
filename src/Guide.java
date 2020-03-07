import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Guide extends JFrame implements ActionListener {

	JLabel l1 = new JLabel();
	JLabel l2 = new JLabel();
	JLabel l3 = new JLabel();
	JLabel l4 = new JLabel();
	
	JButton next = new JButton(); 
	String topic1 = "";
	
	Font font = new Font("",Font.PLAIN,22);
	String id1="";
	
	public Guide(String topic,String id)
	{
		topic1=topic;
		id1=id;
		setLayout(null);
		setTitle("GuideLines For Examination");
		setBounds(300,100, 800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		l1.setText("GuideLines for Examination");
	  	l1.setBounds(50,0,500,50);
		l1.setFont(font);
	  	add(l1);
	  	l2.setText("1.) This will be a multiple choice question exam");
	  	l2.setBounds(50,70,550,50);
		l2.setFont(font);
	  	add(l2);
	  	l3.setText("2.) For each question you will have 4 choices");
	  	l3.setBounds(50,120,550,50);
		l3.setFont(font);
	  	add(l3);
	  	l4.setText("3.) You can skip a question");
	  	l4.setBounds(50,170,550,50);
		l4.setFont(font);
	  	add(l4);
		
		next.setBounds(280,300,200,50);
		next.setText("START");
		next.setFont(font);
	  	add(next);
	  	
	  	next.addActionListener(this);
	  	
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		super.dispose();
		Exam e = new Exam(topic1,id1);
		
	}
/*	
	public static void main(String[] args) {
		Guide g = new Guide("Java");
	}
*/	
	
	
}
