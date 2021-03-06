package se.menu;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import se.Student.*;
import se.computation.StudentSummary;
import se.request.*;
import javax.swing.*;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;


public class StudentMenu extends JFrame 
{
	JPanel sidepanel;
	JPanel mainpanel;
	JPanel summary;
	StudentData data;
	Student Sobj;
	Request Robj;
	JPanel requestmenu;
	int[] values;
	StudentSummary SSobj = new StudentSummary();
	StudentMenu(StudentData obj , JFrame prev)
	{
		this.data = obj;
		Sobj = new Student();
		Robj = new Request();
		setSize(1030,550);
		addSidePanel();
		addMainPanel();
		setLayout(null);
		setResizable(false);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prev.dispose();
	}
	private void addSidePanel()
	{
		JPanel sidepanel = new JPanel();
		JLabel menuname = new JLabel("Student Portal");
		menuname.setForeground(Color.white);
		menuname.setBounds(50,0,200,50);
		
		JButton RRequest = new JButton("Raise Request");
		RRequest.setBounds(0,150,200,30);
		RRequest.setBackground(Color.WHITE);
		RRequest.setForeground(Color.BLACK);
		RRequest.addActionListener(new MenuButton());
		
		JButton Summary = new JButton("Summary");
		Summary.setBounds(0,200,200,30);
		Summary.setBackground(Color.WHITE);
		Summary.setForeground(Color.BLACK);
		Summary.addActionListener(new MenuButton());
		
		JButton StatusMenu = new JButton("Status");
		StatusMenu.setBounds(0,250,200,30);
		StatusMenu.setBackground(Color.WHITE);
		StatusMenu.setForeground(Color.BLACK);
		StatusMenu.addActionListener(new MenuButton());
		
		JButton Profile = new JButton("Profile");
		Profile.setBounds(0,300,200,30);
		Profile.setBackground(Color.WHITE);
		Profile.setForeground(Color.BLACK);
		Profile.addActionListener(new MenuButton());
		
		JButton logout = new JButton("Logout");
		logout.setBounds(0,350,200,30);
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
		sidepanel.add(StatusMenu);
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
		addRRMenu(mainpanel);
		addSummary(mainpanel);
		addStatus(mainpanel);
		addProfile(mainpanel);
		this.add(mainpanel);
	}
	private void addRRMenu(JPanel obj)
	{
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JLabel heading = new JLabel("Raise Request Menu");
		heading.setBounds(405,10,810,30);
		panel.add(heading);
		JLabel destination = new JLabel("Destination");
		destination.setBounds(10,75,200,30);
		panel.add(destination);
		
		JTextField desvalue = new JTextField();
		desvalue.setColumns(10);
		desvalue.setBounds(220, 75,200,20);
		panel.add(desvalue);
		
		JLabel type = new JLabel("Request Type");
		type.setBounds(10,110,200,30);
		panel.add(type);
		
		String request[] = new String[]{"Outing"  , "Vacation" , "Medical"};
		JComboBox cb=new JComboBox(request);    
	    cb.setBounds(220,110,90,20);    
	    panel.add(cb);
	    
	    JLabel fdate = new JLabel("From Date");
	    fdate.setBounds(10,150,200,30);
	    panel.add(fdate);
	    
	    JDateChooser fchooser = new JDateChooser();
	    fchooser.setBounds(220,150,200,30);
	    //fchooser.setDateFormatString("dd-mm-yyyy");
	    panel.add(fchooser);
	    
	    JLabel tdate = new JLabel("To Date");
	    tdate.setBounds(10,190,200,30);
	    panel.add(tdate);
	    
	    JDateChooser tchooser = new JDateChooser();
	    tchooser.setBounds(220,190,200,30);
	    //tchooser.setDateFormatString("dd-mm-yyyy");
	    panel.add(tchooser);
	    
	    JButton raise = new JButton("Raise Request");
	    raise.setBounds(10,250,200,30);
	    raise.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		String destination = desvalue.getText();
	    		int type = cb.getSelectedIndex()+1;
	    		Date fromdate = fchooser.getDate();
	    		Date todate = tchooser.getDate();
	    		
	    		if(destination.length() == 0 || type == -1 || fromdate == null || todate == null)
	    		{
	    			JOptionPane.showMessageDialog(panel,"Fill complete form");
	    			return;
	    		}
	    		
	    		if(destination.length() > 10)
	    		{
	    			JOptionPane.showMessageDialog(panel,"Check Destination length.");
	    			return;
	    		}
	    		
	    		System.out.println(new Date());
	    		System.out.println(fromdate);
	    		if(fromdate.compareTo(new Date()) < 0)
	    		{
	    			JOptionPane.showMessageDialog(panel,"Please select proper dates");
	    			return;
	    		}
	    		
	    		if(fromdate.compareTo(todate) >= 0)
	    		{
	    			JOptionPane.showMessageDialog(panel,"The to-date should be after the from-date");
	    			return;
	    		}
	    		
	    		RequestData Rdata = new RequestData();
	    		Rdata.StudentId = data.Id;
	    		Rdata.RequestId = Robj.getRequestId(data.Id);
	    		Rdata.fromDate = fromdate;
	    		Rdata.toDate = todate;
	    		Rdata.Destination = destination;
	    		Rdata.RequestType = type;
	    		Rdata.Status = 1;
	    		
	    		if(Robj.addRequest(Rdata))
	    		{
	    			requestmenu.add(getRequest(Rdata , true));
	    			requestmenu.revalidate();
	    			requestmenu.repaint();
	    			JOptionPane.showMessageDialog(panel,("Request Saved\nId:" + Rdata.RequestId));
	    			desvalue.setText("");
	    			fchooser.setDate(null);
	    			tchooser.setDate(null);
	    		}
	    	}
	    });
	    panel.add(raise);
	    
		obj.add(panel , "Raise Request");
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
	private void addStatus(JPanel obj)
	{
		requestmenu = new JPanel();
		JScrollPane listScroller = new JScrollPane(requestmenu);
		listScroller.setPreferredSize(new Dimension(800,550));
		requestmenu.setPreferredSize(new Dimension(800, 550));
		listScroller.setAlignmentX(RIGHT_ALIGNMENT);
		ArrayList<RequestData> rdata = Robj.getRequestbyStudent(data.Id);
		requestmenu.setLayout(new BoxLayout(requestmenu, BoxLayout.PAGE_AXIS));
		if(rdata != null)
			for(RequestData x : rdata)
			{
				if(x.Status != 6 && x.Status != 3 && x.Status != 5)
					requestmenu.add(getRequest(x , true));
				else if(x.Status == 6 || x.Status == 3 || x.Status == 5)
					requestmenu.add(getRequest(x , false));
				requestmenu.add(Box.createRigidArea(new Dimension(0,5)));
			}
		obj.add(listScroller , "Status");
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
		
		String todate = new SimpleDateFormat("dd-MM-yyyy").format(request.toDate);
		String fromdate = new SimpleDateFormat("dd-MM-yyyy").format(request.fromDate);
		
		JLabel From = new JLabel("From Date: " + fromdate);
		From.setBounds(450,0,200,20);
		panel.add(From);
		
		JLabel To = new JLabel("To Date: "+ todate);
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
		else if(request.RequestType == 3)
			type = "Medical";
		JLabel Type = new JLabel("Type: "+ type);
		Type.setBounds(255,40,250,20);
		panel.add(Type);
		
		String status = "";
		int SR = request.Status;
		if(SR == 1)
			status = "At Mentor";
		else if(SR == 3 || SR == 5)
			status = "Declined";
		else if(SR == 6)
			status = "Approved";
		else if(SR == 2)
			status = "At Warden";
		else if(SR == 4)
			status = "Reconsideration";
		
		JLabel stat = new JLabel("Status: " + status);
		stat.setBounds(450,40,150,30);
		panel.add(stat);
		
		if(add)
		{
			JButton delete = new JButton("Delete");
			delete.setBounds(620,40,100,30);
			panel.add(delete);
			delete.addActionListener( new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					if(Robj.deleteRequest(request.RequestId))
					{
						requestmenu.remove(panel);
						requestmenu.revalidate();
						requestmenu.repaint();
					}
				}
			});
		}
		return panel;
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
		
		JLabel uppass = new JLabel("Update Password");
		uppass.setBounds(10, 445 ,200,30);
		panel.add(uppass);
		
		JTextField newpass = new JTextField("New Pasword");
		newpass.setBounds(220,450,100,20);
		panel.add(newpass);
		
		JButton update = new JButton("Update");
		update.setBounds(350,450,100,20);
		update.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) 
			{
				String newpassword = newpass.getText();
				if(newpassword.length() == 0 || newpassword.length() > 10)
				{
					JOptionPane.showMessageDialog(panel,"Enter correct password");
					return;
				}
				
				if(Sobj.updatePassword(data.Id, newpassword))
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
		Student obj = new Student();
		new StudentMenu(obj.getStudentById("21BEC2929") , new JFrame());
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
}
