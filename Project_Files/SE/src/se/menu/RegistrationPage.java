package se.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import se.Student.Student;
import se.Student.StudentData;
import se.mentor.Mentor;
import se.mentor.MentorData;
import se.warden.WardenData;

public class RegistrationPage extends JFrame
{
	
	RegistrationPage()
	{
		this.setLayout(null);
		this.setSize(500,600);
		loadscreen();
		this.setResizable(false);
		this.setVisible(true);
		this.getContentPane().setBackground(new Color(45, 62, 80));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void loadscreen()
	{
		JLabel heading  = new JLabel("  Registration Form");
		heading.setBounds(0,0,400,40);
		heading.setBackground(new Color(249, 148, 6));
		heading.setForeground(Color.WHITE);
		heading.setFont(new Font("Tahoma", 1, 24));
		heading.setOpaque(true);
		this.add(heading);
		
		String[] names = new String[] {"Name","ID","Email","Gender","Room Number","Hostel","Mentor Id","Password"};
		JLabel[] label = new JLabel[names.length];
		JTextField[] text = new JTextField[names.length]; 
		int counter = 0;
		int y = 70;
		for(String x : names)
		{
			label[counter] = new JLabel(x);
			label[counter].setBounds(10,y,200,30);
			label[counter].setForeground(Color.WHITE);
			label[counter].setFont(new Font("Tahoma", 0, 14));
			this.add(label[counter]);
			
			text[counter] = new JTextField();
			text[counter].setBounds(220,y,200,20);
			this.add(text[counter]);	
			y = y + 50;
			counter++;
		}
		
		JButton check = new JButton("Check");
		check.setBounds(10,470,100,25);
		this.add(check);
		
		JButton save = new JButton("Register");
		save.setBounds(300,470,100,25);
		save.setEnabled(false);
		this.add(save);
		
		JLabel login = new JLabel("Back to Login");
		login.setBackground(new Color(249, 148, 6));
		login.setBounds(400,0,100,40);
		login.setForeground(Color.WHITE);
		login.setFont(new Font("Tahoma", 0, 14));
		login.setOpaque(true);
		login.addMouseListener(new MouseListener() 
		{

			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				new Login();
				JFrame frame = (JFrame)SwingUtilities.getRoot((Component) arg0.getSource());
				frame.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}	
		});
		this.add(login);
		
		
		Mentor mobj = new Mentor();
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				for(JTextField x : text)
					if(x.getText().length() == 0)
					{
						JOptionPane.showMessageDialog(check.getParent(),"Fill complete form");
						return;
					}
				
				String passwordString = text[7].getText();
				if(passwordString.length() > 10)
				{
					JOptionPane.showMessageDialog(check.getParent(),"Password length should be less than 10");
					return;
				}
				if(!(Pattern.matches("\\d{2}[a-zA-Z]{3}\\d{4}", text[1].getText()) && Pattern.matches("\\d{3}", text[4].getText())))
				{
					JOptionPane.showMessageDialog(check.getParent(),"Student ID not proper");
					return;
				}
				String id = text[6].getText();
				String hostel = text[5].getText();
				String gender = ""+text[3].getText().charAt(0);
				String hg = ""+text[5].getText().charAt(0);
				gender = gender.toUpperCase();
				if(!hg.equals(gender))
				{
					JOptionPane.showMessageDialog(check.getParent(),("Please choose proper hostel"));
					return;
				}
				boolean pass = Pattern.matches("[E][M]\\d*",id);
				if(pass && mobj.checkId(id))
					if(hostel.equals("MH1") || hostel.equals("MH2") || hostel.equals("MH3"))
						save.setEnabled(true);
					else
						JOptionPane.showMessageDialog(check.getParent(),("Invalid HostelId"));
				else
					JOptionPane.showMessageDialog(check.getParent(),"Mentor ID not proper");
			}
		});
		
		save.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentData data = new StudentData();
				data.Name =  text[0].getText();
				data.Id =    text[1].getText();
				data.Email = text[2].getText();
				data.gender = ""+text[3].getText().charAt(0);
				data.RoomNumber = Integer.parseInt(text[4].getText());
				data.HostelName = text[5].getText();
				data.MentorDetails = mobj.getMentorById(text[6].getText());
				String password = text[7].getText();
				
				Student obj = new Student();
				if(obj.addStudent(data, password))
				{
					JOptionPane.showMessageDialog(check.getParent(),"Saved Data");
					for(JTextField x : text)
						x.setText("");
				}
				else
					JOptionPane.showMessageDialog(check.getParent(),"Try Again");
			}
		});
	}
	
	public static void main(String agrs[])
	{
		new RegistrationPage();
	}
}
