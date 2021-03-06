package se.menu;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import se.Student.Student;
import se.request.*;
import javax.swing.*;
import javax.swing.border.Border;

public class SecurityMenu extends JFrame
{
	JPanel mainpanel;
	JPanel inpanel;
	JPanel outpanel;
    ArrayList<RequestData> allapprovedrequest;
	Request getRequest = new Request();
	Student Sobj = new Student();
	SecurityMenu(JFrame prev)
	{
		allapprovedrequest = getRequest.getAllApprovedRequest();		 
		setSize(1010,500);
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
		JLabel menuname = new JLabel("Security Menu");
		menuname.setForeground(Color.white);
		menuname.setBounds(50,0,200,50);
		JLabel title = new JLabel("Requests");
		title.setBounds(600,0,410,50);
		JButton IntimeMenu = new JButton("In-Time");
		IntimeMenu.setBounds(0,150,200,30);
		IntimeMenu.setBackground(Color.WHITE);
		IntimeMenu.setForeground(Color.BLACK);
		IntimeMenu.addActionListener(new MenuButton());
		JButton OuttimeMenu = new JButton("Out-Time");
		OuttimeMenu.setBounds(0,200,200,30);
		OuttimeMenu.setBackground(Color.WHITE);
		OuttimeMenu.setForeground(Color.BLACK);
		OuttimeMenu.addActionListener(new MenuButton());
		sidepanel.add(IntimeMenu);
		sidepanel.add(OuttimeMenu);
		sidepanel.add(menuname);
		this.add(title);
		
		JButton logout = new JButton("Logout");
		logout.setBounds(0,250,200,30);
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
		
		sidepanel.setBounds(0, 0,200,500);
		sidepanel.setBackground(new Color(39,39,39));
		sidepanel.setLayout(null);
		this.add(sidepanel);
	}
	private void addMainPanel()
	{
		
		mainpanel = new JPanel();
		mainpanel.setBounds(200,60,800,400);
		mainpanel.setBackground(Color.WHITE);
		mainpanel.setLayout(new CardLayout());
		addInrequest(mainpanel);
		addOutrequest(mainpanel);
		this.add(mainpanel);
	}
	private void addInrequest(JPanel obj)
	{
		inpanel = new JPanel();
		inpanel.setBounds(200,50,750,500);
		inpanel.setBackground(Color.WHITE);
		inpanel.setLayout(new BoxLayout (inpanel, BoxLayout.Y_AXIS));
		for(RequestData x : allapprovedrequest)
			if(x.intime == null && x.outtime != null)
				inpanel.add(getRequestPanel(x,true));
		obj.add(inpanel , "inmenu");
	}
	private void addOutrequest(JPanel obj)
	{
		outpanel = new JPanel();
		outpanel.setBounds(200,50,750,500);
		outpanel.setBackground(Color.WHITE);
		outpanel.setLayout(new BoxLayout (outpanel, BoxLayout.Y_AXIS));
		for(RequestData x : allapprovedrequest)
		{
			if(x.intime == null && x.outtime == null)
				outpanel.add(getRequestPanel(x,false));
		}
		obj.add(outpanel , "outmenu");
	}
	private JPanel getRequestPanel(RequestData request , boolean type)
	{
		Border blackline = BorderFactory.createLineBorder(Color.black);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(blackline);
		JLabel requestId = new JLabel("Request ID: " + request.RequestId);
		requestId.setBounds(5,0,500,20);
		panel.add(requestId);
		
		JLabel StudentId = new JLabel("Student ID: "+request.StudentId);
		StudentId.setBounds(5,30,500,20);
		panel.add(StudentId);
		
		JButton button = new JButton("Update");
		button.setBounds(600,12,100,26);
		if(type)
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println(request.RequestId);
					String id = request.RequestId;
					Date time = new Date();
					if(getRequest.updateIntime(id, time) && Sobj.updatestatus(request.StudentId, 0))
					{
						inpanel.remove(panel);
						inpanel.revalidate();
						inpanel.repaint();
						RequestData deldata = getRequest.getRequestById(request.RequestId);
						if(getRequest.checkIfStudentLate(deldata))
						{
							JOptionPane.showMessageDialog(panel,"Student is late");
							Student obj = new Student();
							obj.updatetimeslate(request.StudentId);
						}
					}
				}
			});
		else
		{
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println(request.RequestId);
					String id = request.RequestId;
					Date time = new Date();
					if(getRequest.updateOuttime(id, time) && Sobj.updatestatus(request.StudentId, 1))
					{
						RequestData deldata = getRequest.getRequestById(request.RequestId);
						if(deldata == null)
							return;
						inpanel.add(getRequestPanel(deldata,true));
						inpanel.revalidate();
						inpanel.repaint();
						outpanel.remove(panel);
						outpanel.revalidate();
						outpanel.repaint();
					}
				}
			});
		}
		panel.add(button);
		
		return panel;
	}
    class MenuButton implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String name = e.getActionCommand();
			CardLayout obj = (CardLayout) mainpanel.getLayout();
			if(name.equals("In-Time"))
			{
				obj.show(mainpanel, "inmenu");
			}
			else
			{
				obj.show(mainpanel,"outmenu");
			}
		}
		
	}
    public static void main(String agrs[])
	{
		new SecurityMenu(new JFrame());
	}
}

