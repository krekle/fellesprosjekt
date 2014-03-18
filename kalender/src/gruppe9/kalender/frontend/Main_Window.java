package gruppe9.kalender.frontend;
import gruppe9.kalender.client.ApiCaller;
import gruppe9.kalender.client.CalResponse;
import gruppe9.kalender.client.Client;
import gruppe9.kalender.client.Database;
import gruppe9.kalender.model.Meeting;
import gruppe9.kalender.model.Person;
import gruppe9.kalender.user.Bruker;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author krake, Berg
 */
public class Main_Window extends javax.swing.JFrame implements ApiCaller
{
	Login_Window login;
	Client client;
	private Panel Felles;
	private int current_week = 0;
	private int current_year = 2014;
	static boolean popupExists = false;
	private Notification_Window notifications;
	/** Creates new form Main_Window */
    public Main_Window(Login_Window login) 
    {
    	this.login = login;
    	Database.getAllPeople(this);
    	Database.getMeetings(this, Bruker.getInstance().getUser().getId());    		
    	Database.getAlerts(this);
    	Database.getNotifications(this);
    	Database.getGroups(this);

    	//Henter avtalene til brukeren basert på id som ligger i Bruker.java
    	// Resultatet kommer til callBack() metoden.    	
    	initComponents();

    	
    	Panel me = new Panel(week_list_scroller, this);
        me.addPerson(Bruker.getInstance().getUser());
        tabWindow.addTab("Me", me);
        Felles = new Panel(week_list_scroller, this);
        tabWindow.addTab("Felles", Felles);
        Panel groupXPanel = new Panel(week_list_scroller, this);
        for (int i = 0; i < Bruker.getInstance().getGroups().size(); i++) {
        	groupXPanel = new Panel(week_list_scroller, this);
			tabWindow.addTab(Bruker.getInstance().getGroups().get(i).getName(), groupXPanel);
		} //TODO: Men dette skulle vi kanskje ikke ha med ??
        tabWindow.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) 
			{
				System.out.println(tabWindow.getSelectedComponent());
				if(tabWindow.getTitleAt(tabWindow.getSelectedIndex()).equals("Felles"))
				{
					felles_deltakere_box.setVisible(true);
				}
				else
				{
					felles_deltakere_box.setVisible(false);
				}
			}
		});
        felles_deltakere_box.setEditable(false);
        felles_deltakere_box.setVisible(false);
        felles_deltakere_box.setRenderer(new PersonRenderer());
        for(Person p : Bruker.getInstance().getAllPeople())
        {
        	felles_deltakere_box.addItem(p);
        }
        felles_deltakere_box.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				((Person) felles_deltakere_box.getSelectedItem()).setAdded();
				if(!Felles.getPeople().contains((Person) felles_deltakere_box.getSelectedItem()))
				{
					Felles.addPerson((Person) felles_deltakere_box.getSelectedItem());					
				}
				else
				{
					Felles.removePerson((Person) felles_deltakere_box.getSelectedItem());
				}
//				felles_deltakere_box.removeItemAt(felles_deltakere_box.getSelectedIndex());
			}
		});
        notifications = new Notification_Window();
        String path = "resources/images/no_notification.png";
        if(hasNewNotification())
        {
        	path = "resources/images/notification.png";
        }
        Image icon =new ImageIcon(path).getImage();
        ImageIcon notify_icon = new ImageIcon(icon.getScaledInstance(27, 27, java.awt.Image.SCALE_SMOOTH));
        notification_button.setIcon(notify_icon);
        kom_møte_list.setCellRenderer(new MeetingRenderer());
        kom_møte_list.addListSelectionListener(new ListSelectionListener() 
        {
			public void valueChanged(ListSelectionEvent e) 
			{
				setMeeting((Meeting)kom_møte_list.getModel().getElementAt(kom_møte_list.getSelectedIndex()));
			}
		});
        updateKomMeetings();
    }

    public void updateKomMeetings()
    {
    	try
    	{
    		ArrayList<Meeting> meetings = Bruker.getInstance().getUser().getMeetings();
    		Collections.sort(meetings);
	        DefaultListModel<Meeting> contentsOfKomMøtList = new DefaultListModel<Meeting>();
	        System.out.println("MEETINGS.SIZE() = " + meetings.size());
	        for(Meeting m : meetings)
	        {
	        	contentsOfKomMøtList.addElement(m);
	        }
	        kom_møte_list.setModel(contentsOfKomMøtList);
        }
    	catch(Exception e)
    	{
    		
    	}
    }
    public void callBack(CalResponse response){
    	try {
    		if(response.getAvtaler()) {
    		//Avtalene ble hentet fra serveren og ligger nå i
    		// Bruker.getInstance().getAvtaler() <--returnerer en ArrayList med Meeting
    		
    			updateKomMeetings();
    		//Her kan man nå kjøre f.eks:
    		//kalenderpanel.setAvtaler(Bruker.getInstance().getAvtaler();
    		}
    		else if (response.getAlerts()) {
    		//Alarmene ble hentet fra serveren og ligger nå i
    		// Bruker.getInstance().getUser().getAlerts() <--returnerer en ArrayList med Alert
	    	}
	    	else if(response.getNotifications())
	    	{
	    		if(Bruker.getInstance().getNotifications() != null)
	    		{
	    			System.out.println("List size: " + Bruker.getInstance().getNotifications().size());
	    		}
	    		else{
	    			System.out.println("No new notifications..");
	    		}
	    	}
	    	else if(response.getAllPeople())
	    	{
	    		for(Person p : Bruker.getInstance().getAllPeople())
	    		{
	    			System.out.println(p.getName() + " - " + p.getEmail());
	    		}
	    	}
	    	else if(response.getGroups()){
	    		System.out.println(Bruker.getInstance().getGroups());
	    	}
    	}
    	catch (Exception e)
    	{
    		System.out.println("TODO: ... ");
    	}

    	//vi må sjekke at response.* metodene funker her. etterhver vil vi også sjekke
    	// response.getVarsler() her
    }
    public void setMeeting(Meeting meeting)
    {
    	beskrivelse_area.setText(meeting.getDescription());
    	avtale_label.setText("Avtale: "+meeting.getId());
    	dato_label.setText("Dato: "+meeting.getDayOfMonth()+"."+(meeting.getMonth()+1)+"."+meeting.getYear());
    	tidspkt_label.setText("Tidspunkt: "+meeting.getStartTime());
    	eier_label.setText("Eier: "+meeting.getCreator());
    	if(meeting.getParticipantListModel() != null)
    	{
    		deltaker_list.setModel(meeting.getParticipantListModel());
    	}
    	if(Bruker.getInstance().getUser().getId() == meeting.getId())
    	{
    		rediger_button.setEnabled(false);
    		slett_button.setEnabled(false);
    	}
    	else
    	{
    		rediger_button.setEnabled(true);
    		slett_button.setEnabled(true);
    	}
    	if(meeting.getParticipants().contains(Bruker.getInstance().getUser()))
    	{
    		decline_choice.setSelected(false);
    		accept_choice.setSelected(true);
    	}
    	else
    	{
    		this.decline_choice.setSelected(true);
    		accept_choice.setSelected(false);
    	}
    	if(meeting.getCreator() != Bruker.getInstance().getUser().getId())
    	{
    		this.rediger_button.setEnabled(false);
    	}
    	else
    	{
    		this.rediger_button.setEnabled(true);
    	}
    	this.current_Avtale = meeting;
    }

    @SuppressWarnings("unchecked")
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
//				client.logOut();
				login = new Login_Window();
				System.out.println("Closing!");
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
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(false);        
        jScrollPane1.setViewportView(kom_møte_list);

        kom_møte_label.setText("Kommende Møter:");

        uke_search.setText("Uke...");
        uke_search.addActionListener(new ActionListener() 
        {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String in = uke_search.getText();
				if(in.matches("[0-9]*") && Integer.parseInt(in) >= 0 && Integer.parseInt(in)<=52)
				{
					current_week = Integer.parseInt(uke_search.getText());
					uke_label.setText(current_week+"/"+current_year);
					((Panel)tabWindow.getSelectedComponent()).refresh();
				}
				
			}
		});

        prev_button.setFont(new java.awt.Font("Dialog", 1, 18));
        prev_button.setText("<");
        prev_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prev_buttonActionPerformed(evt);
            }
        });
        next_button.setFont(new java.awt.Font("Dialog", 1, 18));
        next_button.setText(">");
        next_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_buttonActionPerformed(evt);
            }
        });

        avtale_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(-16777216,true)));

        beskr_label.setText("Beskrivelse:");

        deltaker_label.setText("Deltakere:");

        tidspkt_label.setText("Tidspunkt:");

        jScrollPane2.setViewportView(deltaker_list);

        eier_label.setText("Eier:");

        decline_choice.setText("Avslå ");
        decline_choice.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
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

        varsling_box.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "E-Mail", "Alarm" }));
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
					notifications.setLocation(notification_button.getLocation());
					notifications.setVisible(true);
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

        current_week = (Calendar.getInstance()).get(Calendar.getInstance().WEEK_OF_YEAR);
        current_year = (Calendar.getInstance()).get(Calendar.getInstance().YEAR);
        uke_label.setText(current_week+"/"+current_year);
        System.out.println(current_week);
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

private void next_buttonActionPerformed(java.awt.event.ActionEvent evt) 
{
	current_week++;
	if(current_week == 53){current_week = 1; current_year++;}
	uke_label.setText(current_week+"/"+current_year);
	for(Object o : tabWindow.getComponents())
	{
		((Panel) o).refresh();
	}
	System.out.println("Current year: " +current_year);
}
private void prev_buttonActionPerformed(java.awt.event.ActionEvent evt) 
{
	current_week--;
	if(current_week == 0){current_week = 52; current_year--;}
	uke_label.setText(current_week+"/"+current_year);
	for(Object o : tabWindow.getComponents())
	{
		((Panel) o).refresh();
	}
}

private void decline_choiceActionPerformed(java.awt.event.ActionEvent evt) {
	if(current_Avtale.getParticipants().contains(Bruker.getInstance().getUser())) {
		
		ArrayList<Person> participates = current_Avtale.getParticipants();
		participates.remove(Bruker.getInstance().getUser());
		current_Avtale.setParticipants(participates);
		Database.updateParticipantStatus(this, Integer.toString(current_Avtale.getId()), Integer.toString(Bruker.getInstance().getUser().getId()), "Avslaatt");
	}
}

private void accept_choiceActionPerformed(java.awt.event.ActionEvent evt) 
{
	current_Avtale.addPerson(Bruker.getInstance().getUser());
	Database.updateParticipantStatus(this, Integer.toString(current_Avtale.getId()), Integer.toString(Bruker.getInstance().getUser().getId()), "Deltar");
}

private void rediger_buttonActionPerformed(java.awt.event.ActionEvent evt) 
{
    Edit_Avtale a = new Edit_Avtale(this, getAvtale());
    a.setVisible(true);
    this.setVisible(false);
    a.setLocation(this.getLocation());
}

private void create_avtale_buttonActionPerformed(java.awt.event.ActionEvent evt) 
{
	    Edit_Avtale a = new Edit_Avtale(this, null);
	    a.setVisible(true);
	    this.setVisible(false);
	    a.setLocation(this.getLocation());
}
private void slett_buttonActionPerformed(java.awt.event.ActionEvent evt)
{
	
}

private void logout_buttonActionPerformed(java.awt.event.ActionEvent evt) 
{
	this.setVisible(false);
	login.setVisible(true);
}

private void notification_buttonActionPerformed(java.awt.event.ActionEvent evt) 
{
	
}



private Meeting current_Avtale = null;

public Meeting getAvtale()
{
	return current_Avtale;
}
    private boolean hasNewNotification() 
    {
    	return this.notifications.hasUnread();
    }
	public int getWeek() 
	{
		return current_week;
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
	public int getYear() {
		// TODO Auto-generated method stub
		return current_year;
	}
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
    
    
    private class PersonRenderer extends JLabel implements ListCellRenderer
    {

    	public PersonRenderer(){this.setOpaque(false);}
    	boolean added = false;
		@Override
		public Component getListCellRendererComponent
		(
				JList list, Object value,
				int index, boolean isSelected, 
				boolean cellHasFocus		)
		{
			if(((Person) value).getAdded())
			{
				this.setBackground(Color.GRAY);
			}
			else
			{
				this.setBackground(Color.WHITE);
			}
			if(cellHasFocus)
			{
				this.setBackground(Color.LIGHT_GRAY);
			}
			this.setText(value.toString());
			return this;
		}
    }
    
    private class MeetingRenderer extends JTextArea implements ListCellRenderer
    {

    	public MeetingRenderer(){this.setOpaque(false);}
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) 
		{	
			if(isSelected){this.setBackground(Color.LIGHT_GRAY);}
			else{this.setBackground(Color.WHITE);}
			Meeting m = (Meeting) value;
			String name = m.getName();
			String date = m.getStart();
			String start = m.getStartTime();
			String end =m.getEndTime();
			String desc = m.getDescription();
			this.setText(name + " - " +date + "\n"
					+ start +" -> " + end +"\n"
					+ desc);
			return this;
		}
    	
    	
    }
}
