/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Notification_Window.java
 *
 * Created on Mar 17, 2014, 2:41:52 PM
 */
package gruppe9.kalender.frontend;

import gruppe9.kalender.client.ApiCaller;
import gruppe9.kalender.client.CalResponse;
import gruppe9.kalender.client.Database;
import gruppe9.kalender.model.Notification;
import gruppe9.kalender.user.Bruker;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author krake
 */
public class Notification_Window extends javax.swing.JFrame{

    /** Creates new form Notification_Window 
     * @param main_Window */
	private Notification_Window THIS = this;
	ArrayList<Notification> notifications = new ArrayList<Notification>();
    public Notification_Window() 
    {
        initComponents();
        if(Bruker.getInstance().getNotifications() != null)
        {
        	notifications = Bruker.getInstance().getNotifications();
        }
        DefaultListModel<Notification> notes = new DefaultListModel<Notification>();
        if(notifications != null)
        {
        	for(Notification note : notifications)
        	{
        		notes.addElement(note);
        	}
        	this.jList1.setModel(notes);        	
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    public void setVisibleAndUpdate(boolean b)
    {
    	setVisible(b);
    	System.out.println("In setvisibleandupdate...");
        DefaultListModel<Notification> notes = new DefaultListModel<Notification>();
        if(notifications != null && notifications.size() > 0)
        {
        	for(Notification note : notifications)
        	{
        		notes.addElement(note);
        	}
        }
        this.jList1.setModel(notes);        	
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
    	this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jList1.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) 
			{
				System.out.println(jList1.getSelectedIndex());
				if(jList1.getSelectedIndex()>0){
					System.out.println(jList1.getSelectedIndex());
					Notification note = (Notification) jList1.getModel().getElementAt(jList1.getSelectedIndex());
					jLabel1.setText(""+Bruker.getInstance().getUser().getId());
					jLabel2.setText("Avtale "+note.getMeetingId());
					jLabel3.setText(note.getTime());
					jTextArea1.setText(note.getDescription());
				}
				else
				{
					jLabel1.setText(Bruker.getInstance().getUser().getId()+"");
					jLabel2.setText("Ingen notifikasjon");
					jLabel3.setText("Ingen notifikasjon");
					jTextArea1.setText("Ingen nye notifikasjoner.");
					
				}
			}
		});
        jList1.setCellRenderer(new NotificationRenderer());
        jScrollPane3.setViewportView(jTextPane1);


        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jScrollPane1.setViewportView(jList1);

        jLabel2.setText("Person");

        jLabel3.setText("Avtale ");

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Her vil det stå masse informasjon.."+"\n"
        		+ "om du hadde mottatt noen notifikasjoner. ");
        jScrollPane2.setViewportView(jTextArea1);

        jLabel1.setText("Min id: "+Bruker.getInstance().getUser().getId());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pack();
        this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				ArrayList<Object> notes = new ArrayList<Object>();
				for(int x = 0; x < jList1.getModel().getSize(); x++)
				{
					Notification n = (Notification) jList1.getModel().getElementAt(x);
					if(n.hasBeenRead)
					{
						notes.add(n);
					}
					
					
				}
				Database d = new Database(0, notes);
			}
			@Override
			public void windowClosed(WindowEvent e){}
			@Override
			public void windowActivated(WindowEvent e){}
		});
    }
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane1;
    
    private class NotificationRenderer extends JLabel implements ListCellRenderer
    {
    	public NotificationRenderer(){this.setOpaque(true);}

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) 
		{
			Notification curNote = (Notification) value;
			if(isSelected)
			{
				this.setBackground(Color.CYAN);
				this.setText("SETT" + "  -   Avtale " + curNote.getMeetingId() + "  -  " + curNote.getTime());
				curNote.hasBeenRead = true;
			}
			else
			{
				if(curNote.hasBeenRead)
				{
					this.setBackground(Color.GRAY);
				}
				else
				{
					this.setBackground(Color.WHITE);
				}
				this.setText((curNote.hasBeenRead ? "SETT":"IKKE SETT")+ "  -   Avtale " + curNote.getMeetingId() + "  -  " + curNote.getTime());
			}
			return this;
		}	
    }
	public boolean hasUnread() 
	{
		if(notifications == null){return false;}
		for(Notification note: this.notifications)
		{
			if(!note.hasBeenRead)
			{
				return true;
			}
		}
		return false;
	}
	public void addNotification(Notification o) 
	{
		if(o != null){
			o.hasBeenRead = false;
			notifications.add(o);
			DefaultListModel<Notification> notes = new DefaultListModel<Notification>();
			for(Notification note : notifications)
			{
				notes.addElement(note);
			}
			this.jList1.setModel(notes);			
		}
	}
	
}
