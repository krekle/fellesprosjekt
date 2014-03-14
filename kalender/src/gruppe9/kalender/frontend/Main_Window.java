package gruppe9.kalender.frontend;
import gruppe9.kalender.client.Client;
import gruppe9.kalender.model.Meeting;
import gruppe9.kalender.user.Bruker;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

/**
 *
 * @author krake
 */
public class Main_Window extends javax.swing.JFrame {

	Login_Window login;
	Client client;
	static boolean popupExists = false;
    /** Creates new form Main_Window */
    public Main_Window(Login_Window login, Client client) 
    {
    	this.client = client;
    	this.login = login;
        initComponents();
        tabWindow.addTab("Me", new Panel(week_list_scroller));
        tabWindow.addTab("Felles", new Panel(week_list_scroller));
        for (int x = 1; x<4; x++){
            tabWindow.addTab("Gruppe "+x, new Panel(week_list_scroller));
        }
        //System.out.println(this.avtale_panel.getSize());
        //Avtale a = new Avtale();
        //avtale_panel = a;
        //a.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

    	this.addWindowListener(new WindowListener() 
    	{
			
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
			public void windowClosing(WindowEvent e) 
			{
				client.logOut();
				System.out.println("Closing!");
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        tabWindow = new javax.swing.JTabbedPane();
        week_list_scroller = new javax.swing.JScrollBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        kom_møte_list = new javax.swing.JList();
        kom_møte_label = new javax.swing.JLabel();
        uke_search = new javax.swing.JTextField();
        prev_button = new javax.swing.JButton();
        next_button = new javax.swing.JButton();
        avtale_panel = new javax.swing.JPanel();
        beskr_label = new javax.swing.JLabel();
        deltaker_label = new javax.swing.JLabel();
        tidspkt_label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        deltaker_list = new javax.swing.JList();
        eier_label = new javax.swing.JLabel();
        decline_choice = new javax.swing.JRadioButton();
        varsling_label = new javax.swing.JLabel();
        dato_label = new javax.swing.JLabel();
        varsling_box = new javax.swing.JComboBox();
        avtale_label = new javax.swing.JLabel();
        kom_møte_label0 = new javax.swing.JLabel();
        accept_choice = new javax.swing.JRadioButton();
        rediger_button = new javax.swing.JButton();
        slett_button = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        beskrivelse_area = new javax.swing.JTextArea();
        uke_label = new javax.swing.JLabel();
        top_panel = new javax.swing.JPanel();
        info_label = new javax.swing.JLabel();
        logout_button = new javax.swing.JButton();
        notification_button = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        create_avtale_button = new javax.swing.JButton();
        felles_deltakere_box = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        kom_møte_list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(kom_møte_list);

        kom_møte_label.setText("Kommende Møter:");

        uke_search.setText("Uke...");

        prev_button.setFont(new java.awt.Font("Dialog", 1, 18));
        prev_button.setText("<");

        next_button.setFont(new java.awt.Font("Dialog", 1, 18));
        next_button.setText(">");
        next_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_buttonActionPerformed(evt);
            }
        });

        avtale_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(-16777216,true)));

        beskr_label.setText("Beskrivelse:");

        deltaker_label.setText("Deltakere");

        tidspkt_label.setText("Tidspunkt:");

        deltaker_list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Johanne", "Pedro", "Jesus", "McCain", "Fritz" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(deltaker_list);

        eier_label.setText("Eier:");

        decline_choice.setText("Avslå ");
        decline_choice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decline_choiceActionPerformed(evt);
            }
        });
        
        accept_choice.setText("Delta");
        accept_choice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accept_choiceActionPerformed(evt);
            }
        });
        ButtonGroup AvslåDelta = new ButtonGroup();
        AvslåDelta.add(decline_choice);
        AvslåDelta.add(accept_choice);

        varsling_label.setText("Varsling:");

        dato_label.setText("Dato:");

        varsling_box.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "E-Mail", "Snail-Mail", "Trompet", "Alarm" }));
        varsling_box.addActionListener(new ActionListener() 
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					if(!Main_Window.popupExists)
					{
						Varsel_Popup p = new Varsel_Popup(varsling_box.getSelectedItem().toString(), null);
						p.setVisible(true);
						Main_Window.popupExists = true;
					}
			}
		});
        avtale_label.setText("Avtale:");

        kom_møte_label0.setText("Endre:");

        accept_choice.setText("Delta");
        accept_choice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accept_choiceActionPerformed(evt);
            }
        });


        rediger_button.setText("Rediger Avtale");
        rediger_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rediger_buttonActionPerformed(evt);
            }
        });
        
        slett_button.setText("Slett Avtale");
        slett_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slett_buttonActionPerformed(evt);
            }
        });

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        beskrivelse_area.setColumns(20);
        beskrivelse_area.setLineWrap(true);
        beskrivelse_area.setRows(5);
        jScrollPane3.setViewportView(beskrivelse_area);

        
        Image icon =new ImageIcon("resources/images/notification.png").getImage();
        ImageIcon notify_icon = new ImageIcon(icon.getScaledInstance(27, 27, java.awt.Image.SCALE_SMOOTH));
        notification_button.setIcon(notify_icon);

        
        //Listener som tar seg av endring av ikon for notifikasjonsknapp 
        notification_button.addMouseListener(new MouseListener() {
			private boolean isHovering = false;
			public void setImage(String image_path)
			{
				Image icon =new ImageIcon(image_path).getImage();
				ImageIcon notify_icon = new ImageIcon(icon.getScaledInstance(27, 27, java.awt.Image.SCALE_SMOOTH));
				notification_button.setIcon(notify_icon);
			}
			@Override
			public void mouseReleased(MouseEvent e)
			{
				if(isHovering)
				{					
					if(hasNewNotification())
					{
						setImage("resources/images/notification_highlighted.png");
					}
					else
					{
						setImage("resources/images/no_notification_highlighted.png");
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e){
				if(isHovering)
				{					
					if(hasNewNotification())
					{
						setImage("resources/images/notification_clicked.png");
					}
					else
					{
						setImage("resources/images/no_notification_clicked.png");
					}
				}
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if(hasNewNotification())
				{
					setImage("resources/images/notification.png");
				}
				else
				{
					setImage("resources/images/no_notification.png");
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				isHovering=true;
				if(hasNewNotification())
				{
					setImage("resources/images/notification_highlighted.png");
			    }
				else
				{
					setImage("resources/images/no_notification_highlighted.png");
				}// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{

			}
		});
        javax.swing.GroupLayout avtale_panelLayout = new javax.swing.GroupLayout(avtale_panel);
        avtale_panel.setLayout(avtale_panelLayout);
        avtale_panelLayout.setHorizontalGroup(
            avtale_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(avtale_panelLayout.createSequentialGroup()
                .addGroup(avtale_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(avtale_panelLayout.createSequentialGroup()
                        .addComponent(avtale_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                        .addComponent(accept_choice)
                        .addGap(3, 3, 3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, avtale_panelLayout.createSequentialGroup()
                        .addContainerGap(185, Short.MAX_VALUE)
                        .addComponent(decline_choice))
                    .addGroup(avtale_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(beskr_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eier_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tidspkt_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dato_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                    .addComponent(deltaker_label)
                    .addComponent(varsling_label)
                    .addComponent(kom_møte_label0))
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
            .addComponent(varsling_box, 0, 259, Short.MAX_VALUE)
            .addComponent(rediger_button, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
            .addComponent(slett_button, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
        );
        avtale_panelLayout.setVerticalGroup(
            avtale_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(avtale_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(avtale_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accept_choice)
                    .addComponent(avtale_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(decline_choice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dato_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tidspkt_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eier_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beskr_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deltaker_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(varsling_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(varsling_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(kom_møte_label0)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rediger_button, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slett_button, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        uke_label.setText("UKE X  ");
        uke_label.setToolTipText("");
        uke_label.setAlignmentY(0.0F);

        top_panel.setForeground(new java.awt.Color(-1118482,true));

        info_label.setFont(new java.awt.Font("SansSerif", 1, 12));
        info_label.setText("Logget inn som " + Bruker.getInstance().getUser().getName());

        logout_button.setText("Logg ut");
        logout_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout_buttonActionPerformed(evt);
            }
        });

        notification_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notification_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout top_panelLayout = new javax.swing.GroupLayout(top_panel);
        top_panel.setLayout(top_panelLayout);
        top_panelLayout.setHorizontalGroup(
            top_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(info_label, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 561, Short.MAX_VALUE)
                .addComponent(notification_button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logout_button)
                .addContainerGap())
        );
        top_panelLayout.setVerticalGroup(
            top_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(top_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(info_label)
                    .addComponent(logout_button)
                    .addComponent(notification_button))
                .addGap(8, 8, 8))
        );

        jSeparator1.setForeground(new java.awt.Color(-10197916,true));
        create_avtale_button.setText("Lag Avtale");
        create_avtale_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				create_avtale_buttonActionPerformed(e);
			}
		});

        felles_deltakere_box.setEditable(true);
        felles_deltakere_box.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1015, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(prev_button, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(kom_møte_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                                .addComponent(felles_deltakere_box, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                            .addComponent(tabWindow, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(uke_search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(week_list_scroller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(next_button, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addComponent(uke_label)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(create_avtale_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(avtale_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(top_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(uke_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(174, 174, 174)
                                                .addComponent(prev_button)
                                                .addGap(41, 41, 41))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(week_list_scroller, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tabWindow, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(kom_møte_label)
                                                    .addComponent(felles_deltakere_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(7, 7, 7)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(create_avtale_button)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(200, 200, 200)
                                        .addComponent(next_button))))
                            .addComponent(uke_label))
                        .addGap(8, 8, 8))
                    .addComponent(avtale_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        
        notification_button.setOpaque(false);
        notification_button.setBorderPainted(false);
        notification_button.setContentAreaFilled(false);
    }// </editor-fold>//GEN-END:initComponents

private void next_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next_buttonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_next_buttonActionPerformed

private void decline_choiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decline_choiceActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_decline_choiceActionPerformed

private void accept_choiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accept_choiceActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_accept_choiceActionPerformed

private void rediger_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rediger_buttonActionPerformed
// TODO add your handling code here:
    //REDIGER AVTALE
    Edit_Avtale a = new Edit_Avtale(this, getAvtale());
    a.setVisible(true);
    this.setVisible(false);
    a.setLocation(this.getLocation());
}//GEN-LAST:event_rediger_buttonActionPerformed

private void create_avtale_buttonActionPerformed(java.awt.event.ActionEvent evt) {
	    Edit_Avtale a = new Edit_Avtale(this, null);
	    a.setVisible(true);
	    this.setVisible(false);
	    a.setLocation(this.getLocation());
	}
private void slett_buttonActionPerformed(java.awt.event.ActionEvent evt)
{//GEN-FIRST:event_slett_buttonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_slett_buttonActionPerformed

private void logout_buttonActionPerformed(java.awt.event.ActionEvent evt) 
{
	client.logOut();
	this.setVisible(false);
	login.setVisible(true);
}

private void notification_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notification_buttonActionPerformed
System.out.println(notification_button.getSize());// TODO add your handling code here:
}//GEN-LAST:event_notification_buttonActionPerformed



private Meeting current_Avtale = null;
public Meeting getAvtale()
{
	return null;
}

public void setMeeting(Meeting avtale)
{
	this.current_Avtale = avtale;
	
}

public void setMeetingFields(Meeting meeting){
	dato_label.setText("dato: " + meeting.getDayOfMonth()+"."+meeting.getMonth());
	tidspkt_label.setText("Tidspunkt: " + meeting.getStartTime() + " - " + meeting.getEndTime());
	
}

    private boolean hasNewNotification() {
    	// TODO Auto-generated method stub
    	return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton prev_button;
    private javax.swing.JButton next_button;
    private javax.swing.JButton rediger_button;
    private javax.swing.JButton slett_button;
    private javax.swing.JButton logout_button;
    private javax.swing.JButton notification_button;
    private javax.swing.JButton create_avtale_button;
    private javax.swing.JComboBox varsling_box;
    private javax.swing.JComboBox felles_deltakere_box;
    private javax.swing.JLabel kom_møte_label;
    private javax.swing.JLabel kom_møte_label0;
    private javax.swing.JLabel info_label;
    private javax.swing.JLabel uke_label;
    private javax.swing.JLabel tidspkt_label;
    private javax.swing.JLabel eier_label;
    private javax.swing.JLabel beskr_label;
    private javax.swing.JLabel deltaker_label;
    private javax.swing.JLabel varsling_label;
    private javax.swing.JLabel dato_label;
    private javax.swing.JLabel avtale_label;
    private javax.swing.JList kom_møte_list;
    private javax.swing.JList deltaker_list;
    private javax.swing.JPanel avtale_panel;
    private javax.swing.JPanel top_panel;
    private javax.swing.JRadioButton accept_choice;
    private javax.swing.JRadioButton decline_choice;
    private javax.swing.JScrollBar week_list_scroller;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea beskrivelse_area;
    private javax.swing.JTextField uke_search;
    private javax.swing.JTabbedPane tabWindow;
    // End of variables declaration//GEN-END:variables
}
