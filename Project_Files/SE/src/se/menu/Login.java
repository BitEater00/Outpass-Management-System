package se.menu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import se.Student.Student;
import se.Student.StudentData;
import se.mentor.Mentor;
import se.mentor.MentorData;
import se.warden.Warden;
import se.warden.WardenData;

import java.awt.event.*;
import java.util.regex.Pattern;
import java.awt.*;  

public class Login extends JFrame
{
	Login()
	{
		this.setSize(400,300);
		this.setLayout(null);
		loadscreen();
		this.setVisible(true);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(45, 62, 80));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void loadscreen()
	{
		JLabel top = new JLabel("  Login Form");
		top.setOpaque(true);
		top.setBounds(0,0,500,40);
		top.setBackground(new Color(249, 148, 6));
		top.setForeground(Color.WHITE);
		top.setFont(new Font("Tahoma", 1, 24));
		this.add(top);
		
		JLabel user = new JLabel("Username");
		user.setBounds(10,95,100,30);
		user.setForeground(Color.WHITE);
		user.setFont(new Font("Tahoma", 0, 14));
		this.add(user);
		
		JTextField username = new JTextField();
		username.setBounds(120,100,200,20);
		this.add(username);
		
		JLabel pass = new JLabel("Password");
		pass.setBounds(10,145,100,30);
		pass.setForeground(Color.WHITE);
		pass.setFont(new Font("Tahoma", 0, 14));
		this.add(pass);
		
		JPasswordField password = new JPasswordField();
		password.setEchoChar('*');
		password.setBounds(120,150,200,20);
		this.add(password);
		
		JButton login = new JButton("Login");
		login.setBounds(220,190,100,30);
		login.setForeground(Color.WHITE);
		login.setFont(new Font("Tahoma", 0, 14));
		login.setBackground(new Color(57, 185, 255));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String user = username.getText();
				String passw = password.getText();
				JFrame frame = (JFrame)SwingUtilities.getRoot((Component) e.getSource());
				
				boolean student = Pattern.matches("\\d{2}[a-zA-Z]{3}\\d{4}", user);
				if(student)
					if(checker(user , passw , 1))
						MenuLoader(user,1);
					else
						error();
				
				boolean mentor = Pattern.matches("[E][M]\\d*",user);
				if(mentor)
					if(checker(user , passw ,2))
						MenuLoader(user ,2);
					else
						error();
				
				boolean warden = Pattern.matches("[E][H][W]\\d*",user);
				if(warden)
					if(checker(user , passw , 3))
						MenuLoader(user , 3);
					else
						error();
				
				boolean security = Pattern.matches("[E][S]\\d*",user);
				if(security)
					if(checker(user , passw , 4))
						MenuLoader(user,4);
					else
						error();
				
				boolean admin = user.equals("admin");
				if(admin)
					if(passw.equals("password"))
						MenuLoader(user,5);
					else
						error();
			}
		});
		this.add(login);
		JLabel register = new JLabel("Don't have a account?");
		register.setBounds(140,225,200,30);
		register.setForeground(Color.WHITE);
		register.setFont(new Font("Tahoma", 0, 10));
		register.addMouseListener(new MouseListener() 
		{

			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				new RegistrationPage();
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
		this.add(register);
		
	}
	private void MenuLoader(String id, int n)
	{
		switch(n)
		{
		case 1: Student S = new Student();
				StudentData data = S.getStudentById(id);
				new StudentMenu(data,this);
				return;
				
		case 2: Mentor M = new Mentor();
				MentorData Mdata =M.getMentorById(id);
				new MentorMenu(Mdata,this);
				return;
		
		case 3:	Warden W = new Warden();
				WardenData Wdata = W.getWardenById(id);
				new WardenMenu(Wdata,this);
				return;
			
		case 4: new SecurityMenu(this);
				return;
				
		case 5: new Admin(this);
				return;
		}
	}
	private boolean checker(String username , String password , int n)
	{
		switch(n)
		{
		case 1: Student S = new Student();
				String getps = S.getPassword(username);
				return  getps != null && getps.equals(password);
				
		case 2: Mentor M = new Mentor();
				String Wgetps = M.getPassword(username);
				return  Wgetps != null && Wgetps.equals(password);
		
		case 3:	Warden W = new Warden();
				String Mgetps = W.getPassword(username);
				return  Mgetps != null && Mgetps.equals(password);
			
		case 4: return username.equals("ES1001") && password.equals("login");
		
		default : return false;
		}
	}
	private void error()
	{
		JOptionPane.showMessageDialog(this,("Invalid Username/Password"));
	}
	public static void main(String agrs[])
	{
		new Login();
	}
}
