package se.computation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import se.Student.Student;
import se.Student.StudentData;


public class ProfileGenerator extends JFrame 
{
	StudentData data;
	Student Sobj;
	JPanel sidepanel;
	JPanel mainpanel;
	JPanel summary;
	JPanel profile;
	int[] values;
	StudentSummary SSobj = new StudentSummary();
	public ProfileGenerator(String id)
	{
		Sobj = new Student();
		data = Sobj.getStudentById(id);
		ProfileGenerator();
	}
	
	public ProfileGenerator(StudentData data)
	{
		this.data = data;
		ProfileGenerator();
	}
	
	private void ProfileGenerator()
	{
		setSize(1030,550);
		addSidePanel();
		addMainPanel();
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	private void addSidePanel()
	{
		JPanel sidepanel = new JPanel();
		JLabel menuname = new JLabel("Student Profile");
		menuname.setForeground(Color.white);
		menuname.setBounds(50,0,200,50);
		
		JButton Summary = new JButton("Summary");
		Summary.setBounds(0,50,200,30);
		Summary.setBackground(Color.WHITE);
		Summary.setForeground(Color.BLACK);
		Summary.addActionListener(new MenuButton());
		
		JButton Profile = new JButton("Profile");
		Profile.setBounds(0,100,200,30);
		Profile.setBackground(Color.WHITE);
		Profile.setForeground(Color.BLACK);
		Profile.addActionListener(new MenuButton());
		
		JButton logout = new JButton("Exit");
		logout.setBounds(0,150,200,30);
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
		addSummary(mainpanel);
		addProfile(mainpanel);
		this.add(mainpanel);
	}
	private void addSummary(JPanel obj)
	{
		values = SSobj.getData(data.Id);
		summary = new JPanel();
		summary.setLayout(null);
		JLabel heading = new JLabel("Summary");
		heading.setBounds(405,10,810,30);
		summary.add(heading);
		String[] head = new String[] {"Number Total Requests" , "Number of Active Requests" , 
				"Number of Approved Requests","Number of Rejected Request","Time's Late"};  
		
		int y = 100;
		int i = 0;
		for(String x : head)
		{
			JLabel Lobj = new JLabel(x);
			Lobj.setBounds(10,y,200,30);
			summary.add(Lobj);
			JLabel Lobjd;
			if(i < 4)
				Lobjd = new JLabel(""+values[i]);
			else
				Lobjd = new JLabel(""+data.late);
			Lobjd.setBounds(220,y,50,30);
			summary.add(Lobjd);
			i++;
			y +=50;
		}
		obj.add(summary , "Summary");
	}
	private void addProfile(JPanel obj)
	{
		JPanel panel = new JPanel();
		JLabel heading = new JLabel("Profile");
		heading.setBounds(405,5,810,30);
		panel.add(heading);
		
		JLabel id = new JLabel("Student Id");
		id.setBounds(10,45,200,30);
		JLabel idvalue = new JLabel(data.Id);
		idvalue.setBounds(220,45,200,30);
		JLabel name = new JLabel("Name");
		name.setBounds(10,75,200, 30);
		JLabel namevalue = new JLabel(data.Name);
		namevalue.setBounds(220, 75, 200, 30);
		JLabel email = new JLabel("Email:");
		email.setBounds(10,105,200,30);
		JLabel valueemail = new JLabel(data.Email);
		valueemail.setBounds(220,105,200,30);
		JLabel room = new JLabel("Room No.:");
		room.setBounds(10,135, 200, 30);
		JLabel roomvalue = new JLabel(""+data.RoomNumber);
		roomvalue.setBounds(220,135,200,30);
		
		JLabel head = new JLabel("Mentor details");
		head.setBounds(405,175,200,30);
		JLabel Mname = new JLabel("Name");
		Mname.setBounds(10,205,200, 30);
		JLabel Mnamevalue = new JLabel(data.MentorDetails.name);
		Mnamevalue.setBounds(220,205, 200, 30);
		JLabel Memail = new JLabel("Email:");
		Memail.setBounds(10,235,200,30);
		JLabel Mvalueemail = new JLabel(data.MentorDetails.Emailid);
		Mvalueemail.setBounds(220,235,200,30);
		
		JLabel head2 = new JLabel("Warden details");
		head2.setBounds(405,275,200,30);
		panel.add(head2);
		JLabel Wname = new JLabel("Name");
		Wname.setBounds(10,305,200, 30);
		panel.add(Wname);
		JLabel Wnamevalue = new JLabel(data.WardenData.name);
		Wnamevalue.setBounds(220,305, 200, 30);
		panel.add(Wnamevalue);
		JLabel Wemail = new JLabel("Email:");
		Wemail.setBounds(10,335,200,30);
		panel.add(Wemail);
		JLabel Wvalueemail = new JLabel(data.WardenData.Emailid);
		Wvalueemail.setBounds(220,335,200,30);
		panel.add(Wvalueemail);
		JLabel hostel = new JLabel("Hostel ID");
		hostel.setBounds(10,365, 200, 30);
		panel.add(hostel);
		JLabel hostelvalue = new JLabel(data.WardenData.HostelId);
		hostelvalue.setBounds(220,365, 200, 30);
		panel.add(hostelvalue);
		
		panel.setLayout(null);
		panel.add(id);
		panel.add(idvalue);
		panel.add(name);
		panel.add(namevalue);
		panel.add(email);
		panel.add(valueemail);
		panel.add(room);
		panel.add(roomvalue);
		panel.add(head);
		panel.add(Mname);
		panel.add(Mnamevalue);
		panel.add(Memail);
		panel.add(Memail);
		panel.add(Mvalueemail);
		
		obj.add(panel , "Profile");
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
	public static void main(String ars[])
	{
		new ProfileGenerator("21BEC2929");
	}
}
