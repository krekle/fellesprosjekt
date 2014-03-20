/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Panel.java
 *
 * Created on Mar 11, 2014, 12:53:42 PM
 */
package gruppe9.kalender.frontend;

import gruppe9.kalender.client.ApiCaller;
import gruppe9.kalender.client.CalResponse;
import gruppe9.kalender.client.Database;
import gruppe9.kalender.model.Meeting;
import gruppe9.kalender.model.Person;
import gruppe9.kalender.user.Bruker;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author krake
 */
public class Panel extends javax.swing.JPanel implements ChangeListener, ApiCaller
{
    
    /** Creates new form Panel 
     * @param main */
	Main_Window main;
	ArrayList<Meeting> meetings = new ArrayList<Meeting>();
	private ArrayList<Person> people = new ArrayList<Person>();
	private String name;
	
    public Panel(JScrollBar scroller, Main_Window main, String name) 
    {
    	this.main = main;
    	this.name = name;
        initComponents();
        refresh();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    public String getName(){
    	return name;
    }
    public void addMe()
    {
		for(int x = 0; x< meetings.size(); x++)
		{
			if(meetings.get(x).getParticipants().contains(Bruker.getInstance().getUser()))
			{
				meetings.remove(x);
			}
		}
		for(Meeting m : Bruker.getInstance().getAvtaler())
		{
			addMeeting(m);
			System.out.println(m.toString());
		}
    	refresh();
    }
    public void addPerson(Person p)
    {
    	if(!people.contains(p))
    	{
    		people.add(p);
    		Database.getMeetings(this, p.getId());
    	}
    	refresh();
    }
    public void removePerson(Person p){
    	if(people.contains(p))
    	{
    		people.remove(p);
    		meetings = new ArrayList<Meeting>();
    		for(Person pe: people)
    		{
    			Database.getMeetings(this, pe.getId());
    		}
    		refresh();
    	}
    }
    public void refresh()
    {
    	if(meetings == null)
    	{
    		return;
    	}
        Collections.sort(meetings);
        for(int x = 0; x<5; x++)
        {
        	DefaultListModel<Meeting> avtaler = new DefaultListModel<Meeting>();
        	ArrayList<String> boop = new ArrayList<String>();
        	for(Meeting meeting : meetings)
        	{
				if(
    				x==meeting.getDayOfWeek() 
    				&& meeting.getWeekOfYear() == main.getWeek()
    				&& meeting.getYear() == main.getYear()
    				&& !boop.contains(meeting.toString())
        		  )
        		{
        			avtaler.addElement(meeting);
        			boop.add(meeting.toString());
        		}
        	}
        	switch(x)
			{
			case 0:
				mandag_list.setModel(avtaler);
				break;
			case 1:
				tirsdag_list.setModel(avtaler);
				break;
			case 2:
				onsdag_list.setModel(avtaler);
				break;
			case 3:
				torsdag_list.setModel(avtaler);
				break;
			case 4:
				fredag_list.setModel(avtaler);
				break;
			}
        }

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    public void addAvtale(Meeting a)
    {
    	DefaultListModel<Meeting> X;
    	
    	switch(a.getDayOfWeek())
    	{
    	case 1:
    		X = (DefaultListModel<Meeting>) mandag_list.getModel();
    		X.addElement(a);
    		mandag_list.setModel(sortModel(X));
    		break;
    	case 2:
    		X = (DefaultListModel<Meeting>) tirsdag_list.getModel();
    		X.addElement(a);
    		tirsdag_list.setModel(sortModel(X));
    		break;
    	case 3:
    		X = (DefaultListModel<Meeting>) onsdag_list.getModel();
    		X.addElement(a);
    		onsdag_list.setModel(sortModel(X));
    		break;
    	case 4:
    		X = (DefaultListModel<Meeting>) torsdag_list.getModel();
    		X.addElement(a);
    		torsdag_list.setModel(sortModel(X));
    		break;
    	case 5:
    		X = (DefaultListModel<Meeting>) fredag_list.getModel();
    		X.addElement(a);
    		fredag_list.setModel(sortModel(X));
    		break;
    	}
    }
    private DefaultListModel<Meeting> sortModel(DefaultListModel<Meeting> model){
    	//gets all existing elements
    	ArrayList<Meeting> meetings = new ArrayList<Meeting>();
    	for (int i = 0; i < model.getSize(); i++) {
			meetings.add(model.getElementAt(i));
		}
    	Collections.sort(meetings);
    	DefaultListModel<Meeting> newModel = new DefaultListModel<Meeting>();
    	for (int i = 0; i < meetings.size(); i++) {
    		newModel.addElement(meetings.get(i));			
		}
    	return newModel;
    }
    private void initComponents() 
    {   mondayScrollPane = new javax.swing.JScrollPane();
        mandag_list = new javax.swing.JList(new DefaultListModel<Meeting>());
        tuesdayScrollPane = new javax.swing.JScrollPane();
        tirsdag_list = new javax.swing.JList(new DefaultListModel<Meeting>());
        wednesdayScrollPane = new javax.swing.JScrollPane();
        onsdag_list = new javax.swing.JList(new DefaultListModel<Meeting>());
        thursdayScrollPane = new javax.swing.JScrollPane();
        torsdag_list = new javax.swing.JList(new DefaultListModel<Meeting>());
        fridayScrollPane = new javax.swing.JScrollPane();
        fredag_list = new javax.swing.JList(new DefaultListModel<Meeting>());
        jLabel1 = new javax.swing.JLabel("Mandag");
        jLabel2 = new javax.swing.JLabel("Tirsdag");
        jLabel3 = new javax.swing.JLabel("Onsdag");
        jLabel4 = new javax.swing.JLabel("Torsdag");
        jLabel6 = new javax.swing.JLabel("Fredag");

        setMaximumSize(null);
        ListSelectionListener listener = new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) 
			{
				JList X = ((JList) e.getSource());
				setSelected(Integer.parseInt(((JList) e.getSource()).getName()));
			}
		};
        Avtale_renderer renderer = new Avtale_renderer();
        mandag_list.setAutoscrolls(false);
        mondayScrollPane.setViewportView(mandag_list);
        mondayScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mondayScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        mandag_list.setCellRenderer(renderer);
        DefaultListModel X = new DefaultListModel();
        mandag_list.setModel(X);
        mandag_list.setName("0");
        mandag_list.addListSelectionListener(listener);
       
        
        tirsdag_list.setAutoscrolls(false);
        tuesdayScrollPane.setViewportView(tirsdag_list);
        tuesdayScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tuesdayScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        tirsdag_list.setCellRenderer(renderer);
        tirsdag_list.setName("1");
        tirsdag_list.addListSelectionListener(listener);
        
        onsdag_list.setAutoscrolls(false);
        wednesdayScrollPane.setViewportView(onsdag_list);
        wednesdayScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        wednesdayScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        onsdag_list.setCellRenderer(renderer);
        onsdag_list.setName("2");
        onsdag_list.addListSelectionListener(listener);
        
        torsdag_list.setAutoscrolls(false);
        thursdayScrollPane.setViewportView(torsdag_list);
        thursdayScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        thursdayScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        torsdag_list.setCellRenderer(renderer);
        DefaultListModel Y = new DefaultListModel();
        torsdag_list.setModel(Y);
        torsdag_list.setName("3");
        torsdag_list.addListSelectionListener(listener);
        
        fredag_list.setAutoscrolls(false);
        fridayScrollPane.setViewportView(fredag_list);
        fridayScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        fridayScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        fredag_list.setCellRenderer(renderer);
        fredag_list.setName("4");
        fredag_list.addListSelectionListener(listener);
        


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mondayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tuesdayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wednesdayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thursdayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fridayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel6)))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mondayScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(tuesdayScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(thursdayScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(wednesdayScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(fridayScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)))
        );
        
        
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList mandag_list;
    private javax.swing.JList tirsdag_list;
    private javax.swing.JList onsdag_list;
    private javax.swing.JList torsdag_list;
    private javax.swing.JList fredag_list;
    private javax.swing.JScrollPane mondayScrollPane;
    private javax.swing.JScrollPane tuesdayScrollPane;
    private javax.swing.JScrollPane wednesdayScrollPane;
    private javax.swing.JScrollPane thursdayScrollPane;
    private javax.swing.JScrollPane fridayScrollPane;
    // End of variables declaration//GEN-END:variables
    private DefaultListModel<Meeting> monday_model;
	public void setSelected(int day)
	{
		try{
		switch(day)
		{
		case 0:
			main.setMeeting((Meeting) mandag_list.getModel().getElementAt(mandag_list.getSelectedIndex()));
			tirsdag_list.clearSelection();
			onsdag_list.clearSelection();
			torsdag_list.clearSelection();
			fredag_list.clearSelection();
			break;
		case 1:
			main.setMeeting((Meeting) tirsdag_list.getModel().getElementAt(tirsdag_list.getSelectedIndex()));
			mandag_list.clearSelection();
			onsdag_list.clearSelection();
			torsdag_list.clearSelection();
			fredag_list.clearSelection();
			break;
		case 2:
			main.setMeeting((Meeting) onsdag_list.getModel().getElementAt(onsdag_list.getSelectedIndex()));
			mandag_list.clearSelection();
			tirsdag_list.clearSelection();
			torsdag_list.clearSelection();
			fredag_list.clearSelection();
			break;
		case 3:
			main.setMeeting((Meeting) torsdag_list.getModel().getElementAt(torsdag_list.getSelectedIndex()));
			mandag_list.clearSelection();
			tirsdag_list.clearSelection();
			onsdag_list.clearSelection();
			fredag_list.clearSelection();
			break;
		case 4:
			main.setMeeting((Meeting) fredag_list.getModel().getElementAt(fredag_list.getSelectedIndex())); 
			mandag_list.clearSelection();
			tirsdag_list.clearSelection();
			onsdag_list.clearSelection();
			torsdag_list.clearSelection();
			break;		
		}}
		catch(Exception e)
		{
//			e.printStackTrace();
		}
	}
	
    public JScrollPane getMondayScrollPane()
    {
    	return mondayScrollPane;
    }
    public JScrollPane getTuesdayScrollPane()
    {
    	return tuesdayScrollPane;
    }
    public JScrollPane getOnsdagScrollPane()
    {
    	return wednesdayScrollPane;
    }
    public JScrollPane getThursdayScrollPane()
    {
    	return thursdayScrollPane;
    }
    public JScrollPane getFridayScrollPane()
    {
    	return fridayScrollPane;
    }
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
	public ArrayList<Person> getPeople() {
		return people;
	}
	public void setPeople(ArrayList<Person> people) {
		this.people = people;
	}
	@Override
	public void callBack(CalResponse response) 
	{
		ArrayList<Meeting> toAdd = response.getOtherMeetings();
		if(toAdd == null){return;}
		for(Meeting m : toAdd)
		{
			if(!meetings.contains(m)){
				meetings.add(m);				
			}
		}
		refresh();
	}
	public void addMeeting(Meeting meeting) 
	{
		if(!this.meetings.contains(meeting))
		{
			this.meetings.add(meeting);
		}
		refresh();
	}
	public void removeMeeting(Meeting meeting)
	{
		if(this.meetings.contains(meeting)){
			this.meetings.remove(meeting);
		}
		refresh();
	}
	
    
   
}
