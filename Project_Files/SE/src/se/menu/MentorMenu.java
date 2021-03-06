package se.menu;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;

import se.Student.Student;
import se.Student.StudentData;
import se.computation.MS;
import se.computation.ProfileGenerator;
import se.mentor.Mentor;
import se.mentor.MentorData;
import se.request.Request;
import se.request.RequestData;

public class MentorMenu extends JFrame
{
	JPanel sidepanel;
	JPanel mainpanel;
	JPanel requestmenu;
	JPanel summarymenu;
	JPanel profile;
	MentorData data;
	Mentor Mobj;
	Request Robj;
	
	MentorMenu(MentorData obj , JFrame prev)
	{
		this.data = obj;
		Mobj = new Mentor();
		Robj = new Request();
		setSize(1030,550);
		addSidePanel();
		addMainPanel();
		setLayout(null);
		setVisible(true);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prev.dispose();
	}
	
	private void addSidePanel()
	{
		JPanel sidepanel = new JPanel();
		JLabel menuname = new JLabel("Mentor Portal");
		menuname.setForeground(Color.white);
		menuname.setBounds(50,0,200,50);
		
		JButton RRequest = new JButton("Requests");
		RRequest.setBounds(0,150,200,30);
		RRequest.setBackground(Color.WHITE);
		RRequest.setForeground(Color.BLACK);
		RRequest.addActionListener(new MenuButton());
		
		JButton Summary = new JButton("Summary");
		Summary.setBounds(0,200,200,30);
		Summary.setBackground(Color.WHITE);
		Summary.setForeground(Color.BLACK);
		Summary.addActionListener(new MenuButton());
		
		JButton Profile = new JButton("Profile");
		Profile.setBounds(0,250,200,30);
		Profile.setBackground(Color.WHITE);
		Profile.setForeground(Color.BLACK);
		Profile.addActionListener(new MenuButton());
		
		JButton logout = new JButton("Logout");
		logout.setBounds(0,300,200,30);
		logout.setBackground(Color.WHITE);
		logout.setForeground(Color.BLACK);
		logout.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						int result = JOptionPane.showConfirmDialog(sidepanel,"Sure? You want to exit?", "Swing Tester",
					               JOptionPane.YES_NO_OPTION,
					               JOptionPane.QUESTION_MESSAGE);
						if(result == JOptionPane.YES_OPTION) 
						{
							JFrame del = (JFrame)sidepanel.getParent().getParent().getParent().getParent();
							del.dispose();
					    }
					}
					
				});
		sidepanel.add(logout);
		sidepanel.add(RRequest);
		sidepanel.add(Summary);
		sidepanel.add(menuname);
		sidepanel.add(Profile);
		
		sidepanel.setBounds(0, 0,200,500);
		sidepanel.setBackground(new Color(39,39,39));
		sidepanel.setLayout(null);
		this.add(sidepanel);
	}
	private void addMainPanel()
	{
		mainpanel = new JPanel();
		mainpanel.setBounds(200,0,810,500);
		mainpanel.setBackground(Color.WHITE);
		mainpanel.setLayout(new CardLayout());
		addRMenu(mainpanel);
		addSummary(mainpanel);
		addProfile(mainpanel);
		this.add(mainpanel);
	}
	private void addRMenu(JPanel obj)
	{
		requestmenu = new JPanel();
		JScrollPane listScroller = new JScrollPane(requestmenu);
		listScroller.setPreferredSize(new Dimension(800,550));
		requestmenu.setPreferredSize(new Dimension(800, 550));
		listScroller.setAlignmentX(RIGHT_ALIGNMENT);
		ArrayList<RequestData> rdata = Robj.getRequestbyMentor(data.EmployeeId);
		requestmenu.setLayout(new BoxLayout(requestmenu, BoxLayout.PAGE_AXIS));
		if(rdata != null)
			for(RequestData x : rdata)
			{
				if(x.Status == 1)
					requestmenu.add(getRequest(x , false));
				else if(x.Status == 4)
					requestmenu.add(getRequest(x , true));
				requestmenu.add(Box.createRigidArea(new Dimension(0,5)));
			}
		obj.add(listScroller , "Requests");
	}
	private JPanel getRequest(RequestData request , boolean add)
	{
		Border blackline = BorderFactory.createLineBorder(Color.black);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(blackline);
		panel.setSize(new Dimension(800,200));
		
		JLabel requestId = new JLabel("Request ID: " + request.RequestId);
		requestId.setBounds(5,0,250,20);
		panel.add(requestId);
		
		JLabel StudentId = new JLabel("Student ID: "+request.StudentId);
		StudentId.setBounds(255,0,200,20);
		panel.add(StudentId);
		
		JLabel From = new JLabel("From Date: " + request.fromDate);
		From.setBounds(450,0,200,20);
		panel.add(From);
		
		JLabel To = new JLabel("To Date: "+ request.toDate);
		To.setBounds(650,0,200,20);
		panel.add(To);
		
		JLabel Destination = new JLabel("Destination: " + request.Destination);
		Destination.setBounds(5,40,250,20);
		panel.add(Destination);
		
		String type = "";
		if(request.RequestType == 1)
			type = "Outing";
		else if(request.RequestType == 2)
			type = "Vacation";
		else
			type = "Medical";
		JLabel Type = new JLabel("Type: "+ type);
		Type.setBounds(255,40,250,20);
		panel.add(Type);
		
		JButton accept = new JButton("Accept");
		accept.setBounds(450,40,100,30);
		panel.add(accept);
		accept.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(request.Status == 4 ? Robj.updateRequestbyMentor(request.RequestId, 6) : Robj.updateRequestbyMentor(request.RequestId, 2))
				{
					requestmenu.remove(panel);
					requestmenu.revalidate();
					requestmenu.repaint();
				}
			}
			
		});
		
		JButton decline = new JButton("Decline");
		decline.setBounds(550,40,100,30);
		panel.add(decline);
		decline.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(Robj.updateRequestbyMentor(request.RequestId, 3))
						{
							requestmenu.remove(panel);
							requestmenu.revalidate();
							requestmenu.repaint();
						}
					}
				});
		
		
		if(add)
		{
			JButton view = new JButton("View Comment");
			view.setBounds(650,40,130,30);
			view.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					 JOptionPane.showMessageDialog(requestmenu,request.internalComment);
				}
			});
			panel.add(view);
		}
		return panel;
	}
	private void addSummary(JPanel obj)
	{
		MS MSobj = new MS();
		int[] values = MSobj.getDataM(data.EmployeeId);
		
		JPanel panel = new JPanel();
		JLabel heading = new JLabel("Summary");
		heading.setBounds(405,5,810,30);
		panel.add(heading);
		JLabel one = new JLabel("Total Active requests");
		one.setBounds(10, 100,200, 30);
		JLabel oned = new JLabel(""+values[1]);
		oned.setBounds(220, 100,200, 30);
		JLabel two = new JLabel("Total Requests");
		two.setBounds(10,150,200,30);
		JLabel twod = new JLabel(""+values[0]);
		twod.setBounds(200,150,200,30);
		panel.add(one);
		panel.add(two);
		panel.add(oned);
		panel.add(twod);
		panel.setLayout(null);
		
		JLabel sear = new JLabel("Search for Student");
		sear.setFont(new Font("Tahoma", 1, 24));
		sear.setBounds(10,200,500,30);
		panel.add(sear);
		
		JTextField idS = new JTextField("Enter ID");
		idS.setBounds(10,250,300,30);
		panel.add(idS);
		
		JButton search = new JButton("Search");
		search.setBounds(370,250,100,30);
		panel.add(search);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String id = idS.getText();
				Student delobj = new Student();
				if(id.length() == 0 || !Pattern.matches("\\d{2}[a-zA-Z]{3}\\d{4}",id) || !delobj.checkId(id))
				{
					JOptionPane.showMessageDialog(panel,"Incorrect Id");
					idS.setText("");
					return;
				}
				
				new ProfileGenerator(id);
			}	
		});
		
		JLabel vmentee = new JLabel("View Mentee List");
		vmentee.setFont(new Font("Tahoma", 1, 24));
		vmentee.setBounds(10,400,300,30);
		panel.add(vmentee);
		
		JButton view = new JButton("View");
		view.setBounds(370,400,100,30);
		panel.add(view);
		view.addActionListener(new MenteeList());
		
		obj.add(panel , "Summary");
	}
	private void addProfile(JPanel obj)
	{
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JLabel heading = new JLabel("Profile");
		heading.setBounds(405,5,810,30);
		panel.add(heading);
		JLabel id = new JLabel("Employee Id");
		id.setBounds(10,100,200,30);
		JLabel idvalue = new JLabel(data.EmployeeId);
		idvalue.setBounds(220,100,200,30);
		JLabel name = new JLabel("Name");
		name.setBounds(10,150,200, 30);
		JLabel namevalue = new JLabel(data.name);
		namevalue.setBounds(220, 150, 200, 30);
		JLabel email = new JLabel("Email:");
		email.setBounds(10,200,200,30);
		JLabel valueemail = new JLabel(data.Emailid);
		valueemail.setBounds(220,200,200,30);
		
		panel.add(id);
		panel.add(idvalue);
		panel.add(name);
		panel.add(namevalue);
		panel.add(email);
		panel.add(valueemail);
		
		JLabel uppass = new JLabel("Update Password");
		uppass.setBounds(10, 345 ,200,30);
		panel.add(uppass);
		
		JTextField newpass = new JTextField("New Pasword");
		newpass.setBounds(220,350,100,20);
		panel.add(newpass);
		
		JButton update = new JButton("Update");
		update.setBounds(350,350,100,20);
		update.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) 
			{
				String newpassword = newpass.getText();
				if(newpassword.length() == 0 || newpassword.length() > 10)
				{
					JOptionPane.showMessageDialog(panel,"Enter correct password");
					return;
				}
				
				if(Mobj.updateMentorPassword(data.EmployeeId,newpassword))
				{
					JOptionPane.showMessageDialog(panel,"Password Updated");
					newpass.setText("");
				}
			}
			
		});
		panel.add(update);
		
		obj.add(panel , "Profile");
	}
	public static void main(String agrs[])
	{
		Mentor obj = new Mentor();
		new MentorMenu(obj.getMentorById("EM7812") , new JFrame());
	}
	class MenuButton implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String name = e.getActionCommand();
			CardLayout obj = (CardLayout) mainpanel.getLayout();
			obj.show(mainpanel, name);
		}
		
	}
	class MenteeList implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			JFrame f = new JFrame();
			f.setSize(810,500);
			Student obj = new Student();
			ArrayList<StudentData> Sdata = obj.getStudentbyMentor(data.EmployeeId);
			if(Sdata == null || Sdata.size() == 0)
			{
				JOptionPane.showMessageDialog(mainpanel,"No Mentee");
				return;
			}
			
			JPanel del = new JPanel();
			JScrollPane scr = new JScrollPane(del);
			scr.setPreferredSize(new Dimension(800,450));
			del.setPreferredSize(new Dimension(800, 450));
			scr.setAlignmentX(RIGHT_ALIGNMENT);
			del.setLayout(new BoxLayout(del, BoxLayout.PAGE_AXIS));
			
			for(StudentData x : Sdata)
			{
				JPanel m = makepanel(x);
				del.add(m);
			}
			f.getContentPane().add(scr);
			f.setVisible(true);
		}
		
		public JPanel makepanel(StudentData x)
		{
			Border blackline = BorderFactory.createLineBorder(Color.black);
			JPanel rp = new JPanel();
			rp.setLayout(null);
			rp.setBorder(blackline);
			rp.setSize(700, 45);
			JLabel name = new JLabel("Name: " + x.Name);
			JLabel Id = new JLabel("Roll No.: " + x.Id);
			JButton view = new JButton("View Full Profile");
			
			name.setBounds(10,10,100,30);
			Id.setBounds(150,10,200,30);
			view.setBounds(450,10,250,30);
			
			rp.add(name);
			rp.add(Id);
			rp.add(view);
			
			view.addActionListener(
					new ActionListener() 
					{
						public void actionPerformed(ActionEvent e) 
						{
							new ProfileGenerator(x);
						}
						
					});
			return rp;
		}
		
	}
}
