package se.menu;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;
import se.Student.*;
import se.mentor.Mentor;
import se.mentor.MentorData;
import se.warden.Warden;
import se.warden.WardenData;
import javax.swing.*;

public class Admin extends JFrame
{
	JPanel sidepanel;
	JPanel mainpanel;
	JPanel addmentor;
	
	Admin(JFrame prev)
	{
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
		JLabel menuname = new JLabel("Admin Portal");
		menuname.setForeground(Color.white);
		menuname.setBounds(50,0,200,50);
		
		JButton RRequest = new JButton("Add Mentor");
		RRequest.setBounds(0,100,200,30);
		RRequest.setBackground(Color.WHITE);
		RRequest.setForeground(Color.BLACK);
		RRequest.addActionListener(new MenuButton());
		
		JButton RReques = new JButton("Add Warden");
		RReques.setBounds(0,150,200,30);
		RReques.setBackground(Color.WHITE);
		RReques.setForeground(Color.BLACK);
		RReques.addActionListener(new MenuButton());
		
		JButton StatusMenu = new JButton("Delete");
		StatusMenu.setBounds(0,200,200,30);
		StatusMenu.setBackground(Color.WHITE);
		StatusMenu.setForeground(Color.BLACK);
		StatusMenu.addActionListener(new MenuButton());
		
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
		sidepanel.add(menuname);
		sidepanel.add(StatusMenu);
		sidepanel.add(RReques);
		
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
		AddMentor(mainpanel);
		AddWarden(mainpanel);
		addDelete(mainpanel);
		this.add(mainpanel);
	}	
	
	private void addDelete(JPanel obj)
	{
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel sear = new JLabel("Delete Mentor");
		sear.setFont(new Font("Tahoma", 1, 24));
		sear.setBounds(10,50,500,30);
		panel.add(sear);
		
		JTextField idS = new JTextField("Enter ID");
		idS.setBounds(10,100,200,30);
		panel.add(idS);
		
		JTextField idss = new JTextField("New Mentor");
		idss.setBounds(220,100,300,30);
		panel.add(idss);
		
		JButton search = new JButton("Delete");
		search.setBounds(570,100,100,30);
		search.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						String from = idS.getText();
						String to = idss.getText();
						if(from.length() == 0 || to.length() == 0)
						{
							JOptionPane.showMessageDialog(panel,"InComplete Form");
							return;
						}
						Mentor mobj = new Mentor();
						boolean one =  Pattern.matches("[E][M]\\d*",from) && mobj.checkId(from);
						boolean two =  Pattern.matches("[E][M]\\d*",to) && mobj.checkId(to);
						if(!(one && two))
						{
							JOptionPane.showMessageDialog(panel,"Invalid Mentor ID");
							return;
						}
						
						Student Sobj = new Student();
						ArrayList<StudentData> Sdata = Sobj.getStudentbyMentor(from);
						if(Sdata == null || Sdata.size() == 0)
						{
							JOptionPane.showMessageDialog(mainpanel,"No Mentee");
						}
						else
							for(StudentData x : Sdata)
							{
								String id = x.Id;
								if(!Sobj.updateMentor(id, to))
								{
									JOptionPane.showMessageDialog(mainpanel,"Try Again");
									return;
								}
							}
						mobj.deleteMentor(from);
						JOptionPane.showMessageDialog(mainpanel,"Mentor Deleted and Swap Complete");
					}
				});
		panel.add(search);
		
		
		
		JLabel Wsear = new JLabel("Delete Warden");
		Wsear.setFont(new Font("Tahoma", 1, 24));
		Wsear.setBounds(10,200,500,30);
		panel.add(Wsear);
		
		JTextField WidS = new JTextField("Enter ID");
		WidS.setBounds(10,250,200,30);
		panel.add(WidS);
		
		JTextField Widss = new JTextField("New Warden");
		Widss.setBounds(220,250,300,30);
		panel.add(Widss);
		
		JButton Wsearch = new JButton("Delete");
		Wsearch.setBounds(570,250,100,30);
		panel.add(Wsearch);
		Wsearch.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						String from = WidS.getText();
						String to = Widss.getText();
						if(from.length() == 0 || to.length() == 0)
						{
							JOptionPane.showMessageDialog(panel,"InComplete Form");
							return;
						}
						
						Warden mobj = new Warden();
						boolean one =  Pattern.matches("[E][H][W]\\d*",from) && mobj.checkId(from);
						boolean two = Pattern.matches("[E][H][W]\\d*", to) && mobj.checkId(to);
						if(!(one && two))
						{
							JOptionPane.showMessageDialog(panel,"Invalid Warden ID");
							return;
						}
						
						WardenData oned = mobj.getWardenById(from);
						WardenData twod = mobj.getWardenById(to);
						if(!oned.HostelId.equals(twod.HostelId))
						{
							JOptionPane.showMessageDialog(panel,"New Warden of other hostel");
							return;
						}
						if(mobj.deleteMentor(from))
						{
							JOptionPane.showMessageDialog(panel,"Completed");
						}
						else
						{
							JOptionPane.showMessageDialog(panel,"Try Again");
						}
					}
					
				});
		
		obj.add(panel,"Delete");
		
	}
	private void AddWarden(JPanel obj)
	{
		JPanel panel = new JPanel();
		panel.setLayout(null);
		String names[] = new String[] {"Employee Id" , "Name" , "EmailID" , "Password" , "Hostel Id"};
		JTextField[] text = new JTextField[names.length];
		int y = 50;
		int i = 0;
		for(String x : names)
		{
			JLabel lo = new JLabel(x);
			lo.setBounds(10,y,200,30);
			text[i] = new JTextField();
			text[i].setBounds(220, y,200,30);
			panel.add(lo);
			panel.add(text[i]);
			y = y+50;
			i++;
		}
		
		JButton save = new JButton("Save");
		save.setBounds(320,y,100,30);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Warden wobj = new Warden();
				for(JTextField x : text)
					if(x.getText().length() == 0)
					{
						JOptionPane.showMessageDialog(panel,"Fill complete form");
						return;
					}
				WardenData wdata = new WardenData();
				wdata.EmployeeId = text[0].getText();
				wdata.name = text[1].getText();
				wdata.Emailid = text[2].getText();
				String password = text[3].getText();
				wdata.HostelId = text[4].getText();
				boolean check = wobj.checkId(wdata.EmployeeId);
				boolean pass = Pattern.matches("[E][H][W]\\d*" , wdata.EmployeeId);
				if(check)
				{
					JOptionPane.showMessageDialog(panel,"User Already Present");
					return;
				}
				if(!pass)
				{
					JOptionPane.showMessageDialog(panel,"Invalid ID");
					return;
				}
				if(!(wdata.HostelId.equals("MH1") || wdata.HostelId.equals("MH2") || wdata.HostelId.equals("LH1")))
				{
					JOptionPane.showMessageDialog(panel,"Invalid Hostel ID");
					return;
				}
				if(password.length() == 0 || password.length() > 10)
				{
					JOptionPane.showMessageDialog(panel,"Password length incorrect");
					return;
				}
				if(wobj.addWarden(wdata, password))
				{
					JOptionPane.showMessageDialog(panel,"Warden Added");
					return;
				}
			}
			
		});
		panel.add(save);
		obj.add(panel , "Add Warden");
	}
	private void AddMentor(JPanel obj)
	{
		JPanel panel = new JPanel();
		panel.setLayout(null);
		String names[] = new String[] {"Employee Id" , "Name" , "EmailID" , "Password"};
		JTextField[] text = new JTextField[names.length];
		int y = 50;
		int i = 0;
		for(String x : names)
		{
			JLabel lo = new JLabel(x);
			lo.setBounds(10,y,200,30);
			text[i] = new JTextField();
			text[i].setBounds(220, y,200,30);
			panel.add(lo);
			panel.add(text[i]);
			y = y+50;
			i++;
		}
		JButton save = new JButton("Save");
		save.setBounds(320,y,100,30);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Mentor mobj = new Mentor();
				for(JTextField x : text)
					if(x.getText().length() == 0)
					{
						JOptionPane.showMessageDialog(panel,"Fill complete form");
						return;
					}
				MentorData mdata = new MentorData();
				mdata.EmployeeId = text[0].getText();
				mdata.name = text[1].getText();
				mdata.Emailid = text[2].getText();
				String password = text[3].getText();
				boolean check = mobj.checkId(mdata.EmployeeId);
				boolean pass = Pattern.matches("[E][M]\\d*" , mdata.EmployeeId);
				if(check)
				{
					JOptionPane.showMessageDialog(panel,"User Already Present");
					return;
				}
				if(!pass)
				{
					JOptionPane.showMessageDialog(panel,"Invalid ID");
					return;
				}
				if(password.length() == 0 || password.length() > 10)
				{
					JOptionPane.showMessageDialog(panel,"Password length incorrect");
					return;
				}
				if(mobj.addMentor(mdata, password))
				{
					JOptionPane.showMessageDialog(panel,"Mentor Added");
					return;
				}
			}
			
		});
		panel.add(save);
		obj.add(panel , "Add Mentor");
	}
	public static void main(String agrs[])
	{
		new Admin(new JFrame());
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

