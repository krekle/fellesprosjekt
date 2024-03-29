/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Main_Window.java
 *
 * Created on Mar 11, 2014, 12:46:47 PM
 */
package gruppe9.kalender.frontend;

import gruppe9.kalender.client.ApiCaller;
import gruppe9.kalender.client.CalResponse;
import gruppe9.kalender.client.Database;
import gruppe9.kalender.model.Deltaker;
import gruppe9.kalender.model.Group;
import gruppe9.kalender.model.Meeting;
import gruppe9.kalender.model.Person;
import gruppe9.kalender.model.Room;
import gruppe9.kalender.user.Bruker;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ListCellRenderer;

/**
 *
 * @author krake, Berg
 */
public class Edit_Avtale extends javax.swing.JFrame implements ApiCaller {
    private Main_Window main;
    private Meeting meeting;
    private ArrayList<Deltaker> deltakere;
    private boolean edit;
    private ArrayList<Room> rooms;
    private Room room;
    private String start;
    private String slutt;
    private String id;
    private boolean complete;
    private HashMap<Person, String> setStatus = new HashMap<Person, String>();
	private boolean editComplete = false;
    public Edit_Avtale(Main_Window main, Meeting meeting) 
    {
    	initComponents();
    	setMeeting(meeting);
    	if (edit) 
    	{
    		setMeetingFields();
    	
    	}
        this.main = main;
        person_list.setCellRenderer(new list_person_renderer());
        person_list.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==3)
				{
					person_list.setSelectedIndex(person_list.locationToIndex(e.getPoint()));
					JPopupMenu popmenu = new JPopupMenu();
					JMenuItem ikke = new JMenuItem("Udefinert"),
							ja = new JMenuItem("Godtar"),
							nei = new JMenuItem("Avslår");
					ActionListener l = new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							if(person_list.getModel().getElementAt(person_list.getSelectedIndex()) instanceof Person)
							{								
								Person p = person_list.getModel().getElementAt(person_list.getSelectedIndex());
								switch(((JMenuItem) e.getSource()).getText())
								{
								case("Udefinert"):
									//TODO ADD FUNCTIONALITY FOR SETTING PARTICIPATING/NOT PARTICIPATING-STATUS
									setStatus.put(p, "IkkeSvart");
								break;
								case("Godtar"):
									setStatus.put(p, "Deltar");
								break;
								case("Avslår"):
									setStatus.put(p, "Avslaatt");
								break;
								}
							}
						}
					};
					ja.addActionListener(l);nei.addActionListener(l);ikke.addActionListener(l);
					popmenu.add(ikke); popmenu.add(ja); popmenu.add(nei);
					popmenu.show(person_list, e.getPoint().x,e.getPoint().y);

				}
				
			}
		});
        deltaker_combo.setRenderer(new combo_box_person_renderer());
    	start = start_textfield.getText();
    	slutt = slutt_textfield.getText();
    	if (start_textfield.getText().length() == 5 && slutt_textfield.getText().length() == 5) {
    		start = toDateTime(date_textfield.getText(), start);
    		slutt = toDateTime(date_textfield.getText(), slutt);
    		Database.getAvaliableRooms(this, start, slutt);}    
    }
    
    @Override
	public void callBack(CalResponse response) 
    {
		if(response.getRoms() != null){
			rooms = response.getRoms();
			if (!romlist_model.isEmpty()) {
				romlist_model.clear();
			}
			for (Room rom : rooms) {
				romlist_model.addElement(rom);
			}
		}
		else if (response.getDeltakere() != null) {
			deltakere = response.getDeltakere();
		}
		if (response.getCode() != null) {
			if (response.getCode().equals("200") && edit == true && editComplete == true) {
				if (setStatus.size() != 0) {
					String people = "", statuses = "";
					for(Person p: setStatus.keySet())
					{
						people +=p.getId() + ",";
						statuses += setStatus.get(p)+",";
					}
					System.out.println("Skapte møte..");
					System.out.println(meeting.toString());
					System.out.println(people.substring(0, people.length()-1));
					System.out.println(statuses.substring(0, statuses.length()-1));
					Database.addParticipants(this, Integer.toString(meeting.getId()), people.substring(0, people.length()-1), statuses.substring(0, statuses.length()-1));
				}
			}
			else if (response.getCode().equals("200") && complete && !edit  && !harID) {
				id = response.getSimpleResponse("avtaleid");
				String csv = "";
				String csvS = "";
				String people = "";
				String statuses = "";
				for (Object o : ((DefaultListModel) person_list.getModel()).toArray()) {
					if (o instanceof Group){
						for (Person p : ((Group) o).getPeople())
						{
							people += p.getId() + ",";
							statuses += "IkkeSvart,";													
						}
						}
					}
				
				for(Person p: setStatus.keySet())
				{
					if(!Arrays.asList(people.split(",")).contains(p.getId()))
					{
						people +=p.getId() + ",";
						statuses += setStatus.get(p)+",";
					}
				}
				meeting.setId(Integer.parseInt(id));
				Database.addParticipants(null, id, people.substring(0, people.length()-1), statuses.substring(0, statuses.length()-1));
				harID = true;
			}
		}
	}
    
	private void setMeetingFields(){
		avtalenavn_textfield.setText(meeting.getName());
		beskrivelse_textfield.setText(meeting.getDescription());
		//person_list.setListData(meeting.getParticipants().toArray());
		Date date = new Date();
		date.setYear(meeting.getYear()-1900); date.setMonth(meeting.getMonth()-1); date.setDate(meeting.getDayOfMonth()); 
		dateChooser.updateUI();
		dateChooser.ensureDateVisible(date);
		dateChooser.setSelectionDate(date);
		date_textfield.setText(dateChooser.getSelectionDate().getDate()+":"
				+(dateChooser.getSelectionDate().getMonth()+1)+":"
				+(dateChooser.getSelectionDate().getYear()+1900));
		start_textfield.setText(meeting.getStartTime());
		slutt_textfield.setText(meeting.getEndTime());
		romlist_model.addElement(new Room(meeting.getRoom(), "",0,"",0));
		Database.getParticipants(this, meeting);
		ArrayList<Person> p = Bruker.getInstance().getAllPeople();
		ArrayList<Person> deltakende = new ArrayList<Person>();
		for (Person per : p) {
			for (Deltaker d : deltakere) {
				if (per.getId() == d.getPersonID()) {
					deltakende.add(per);
				}
			}
		}
		for (Person pe : deltakende) {
			personlist_model.addElement(pe);
			deltaker_combo.removeItem(pe);
		}
	}
	private void setMeeting(Meeting meeting)
    {
    	this.edit = ((meeting != null) ? true:false);
    	this.meeting = ((meeting != null) ? meeting:new Meeting(0, Bruker.getInstance().getUser().getId(), "", "", "", 0, null, ""));

    	
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        avtale_label = new javax.swing.JLabel();
        avtalenavn_textfield = new javax.swing.JTextField();
        beskrivelse_label = new javax.swing.JLabel();
        beskrivelse_scrollpane = new javax.swing.JScrollPane();
        beskrivelse_textfield = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        deltaker_label = new javax.swing.JLabel();
        deltaker_combo = new javax.swing.JComboBox();
        add_button = new javax.swing.JButton();
        fjern_button = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        person_list = new javax.swing.JList();
        jPanel5 = new javax.swing.JPanel();
        dateChooser = new org.jdesktop.swingx.JXMonthView();
        dato_label = new javax.swing.JLabel();
        date_textfield = new javax.swing.JTextField();
        start_label = new javax.swing.JLabel();
        slutt_label = new javax.swing.JLabel();
        start_textfield = new javax.swing.JTextField();
        slutt_textfield = new javax.swing.JTextField();
        varighet_label = new javax.swing.JLabel();
        varighet_textfield = new javax.swing.JTextField();
        forrige_button = new javax.swing.JButton();
        next_button = new javax.swing.JButton();
        lagre_button = new javax.swing.JButton();
        avbryt_button = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        rom_label = new javax.swing.JLabel();
        rom_textfield = new javax.swing.JTextField();
        velg_label = new javax.swing.JLabel();
        romScrollPane = new javax.swing.JScrollPane();
        rom_list = new javax.swing.JList();
        auto_select_choice = new javax.swing.JRadioButton();
        romlist_model = new DefaultListModel();
        personlist_model = new DefaultListModel();
        rom_textfield.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				rom_list.clearSelection();
			}
		});
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
			public void windowClosing(WindowEvent e) 
			{
				main.setVisible(true);
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(-16777216,true)));

        avtale_label.setText("Avtalenavn:");
        beskrivelse_label.setText("Beskrivelse:");

        beskrivelse_scrollpane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        beskrivelse_scrollpane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        beskrivelse_textfield.setColumns(20);
        beskrivelse_textfield.setRows(5);
        beskrivelse_textfield.setLineWrap(true);
        beskrivelse_scrollpane.setViewportView(beskrivelse_textfield);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beskrivelse_scrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(avtale_label)
                        .addGap(4, 4, 4)
                        .addComponent(avtalenavn_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(beskrivelse_label))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(avtale_label)
                    .addComponent(avtalenavn_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(beskrivelse_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beskrivelse_scrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(-16777216,true)));

        deltaker_label.setText("Deltakere:");

        add_button.setText("Legg til");
        add_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				add_buttonActionPerformed(e);
			}
		});

        fjern_button.setText("Fjern");
        fjern_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				fjern_buttonActionPerformed(e);	
			}
		});
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
       
        person_list.setModel(personlist_model);        
        jScrollPane3.setViewportView(person_list);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(deltaker_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deltaker_combo, 0, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fjern_button)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deltaker_label)
                    .addComponent(deltaker_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fjern_button)
                    .addComponent(add_button))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(-16777216,true)));

        dateChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateChooserActionPerformed(evt);
            }
        });

        dato_label.setText("Dato: ");

        date_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date_textfieldActionPerformed(evt);
            }
        });

        start_label.setText("Start:");
        slutt_label.setText("Slutt:");
        varighet_label.setText("Varighet:");

        forrige_button.setText("<");
        forrige_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forrige_buttonActionPerformed(evt);
            }
        });

        next_button.setText(">");
        next_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(dato_label)
                                .addGap(2, 2, 2)
                                .addComponent(date_textfield))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(start_label)
                                .addGap(4, 4, 4)
                                .addComponent(start_textfield))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(slutt_label)
                                .addGap(6, 6, 6)
                                .addComponent(slutt_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(varighet_label)
                        .addGap(2, 2, 2)
                        .addComponent(varighet_textfield, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(forrige_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                        .addComponent(next_button)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dato_label)
                            .addComponent(date_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(start_label)
                            .addComponent(start_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(slutt_label)
                            .addComponent(slutt_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(forrige_button)
                    .addComponent(next_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(varighet_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(varighet_label))
                .addContainerGap())
        );

        lagre_button.setText("Lagre");
        lagre_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lagre_buttonActionPerformed(evt);
            }
        });

        avbryt_button.setText("Avbryt");
        avbryt_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avbryt_buttonActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(-16777216,true)));

        rom_label.setText("Rom:");
        velg_label.setText("Velg fra liste:");
        rom_list.setModel(romlist_model);
        romScrollPane.setViewportView(rom_list);
        
        auto_select_choice.setText("Velg automatisk");
        auto_select_choice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                auto_select_choiceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(romScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(rom_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rom_textfield, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(velg_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                        .addComponent(auto_select_choice)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rom_label)
                    .addComponent(rom_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(velg_label)
                    .addComponent(auto_select_choice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(romScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(avbryt_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lagre_button)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lagre_button)
                    .addComponent(avbryt_button))
                .addContainerGap())
        );

        pack();
        dateChooser.setDaysOfTheWeek(new String[]{"S","M","Ti","O","To","F","L"});
        Date date = new Date();
        dateChooser.setSelectionDate(date);
        date_textfield.setEditable(false);
    	start_textfield.setToolTipText("Starttidspunkt i formatet HH:MM, f.eks 12:00");
    	start_textfield.setColumns(5);
    	start_textfield.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				start_action(evt);
			}
    	});
    	
    	slutt_textfield.setToolTipText("Sluttidspunkt i formatet HH:MM, f.eks 12:00");
    	slutt_textfield.setColumns(5);
    	slutt_textfield.addActionListener(new java.awt.event.ActionListener() {
    		public void actionPerformed(ActionEvent evt) {
    			slutt_action(evt);
    		}
    	});
    	varighet_textfield.setVisible(false);
    	varighet_label.setVisible(false);
    	DefaultComboBoxModel d = new DefaultComboBoxModel();
    	ArrayList<Person> people = Bruker.getInstance().getAllPeople();
    	ArrayList<Group> grupper = Bruker.getInstance().getGroups();
    	for (Person p : people) {
    		d.addElement(p);
    	}
    	for (Group g : grupper) {
			d.addElement(g);
		}
    	d.addElement("EPOST");
    	deltaker_combo.setModel(d);
    	
    }// </editor-fold>//GEN-END:initComponents

private String toDateTime(String dato, String tid) {
	
	System.out.println(dato);
	System.out.println(tid);
	String[] date = dato.split(":");
	if (date[0].length() == 1) {
		date[0] = "0" + date[0]; 
	}
	if (date[1].length() == 1) {
		date[1] = "0" + date[1];
	}
	dato = date[0] + date[1] + date[2];
	String year = dato.substring(4);
	String day = dato.substring(0, 2);
	String date1 = dato.substring(2,4);
	String time = tid.substring(0,2);
	String min = tid.substring(3,5);
	String total = year + "-" + date1 + "-" + day + " " + time + ":" + min + ":" + "00";
	System.out.println(total);
	return total;
}
    
protected void start_action(ActionEvent evt) {
	start = start_textfield.getText();
	slutt = slutt_textfield.getText();
	if (start_textfield.getText().length() == 5 && slutt_textfield.getText().length() == 5) {
		start = toDateTime(date_textfield.getText(), start);
		slutt = toDateTime(date_textfield.getText(), slutt);
		Database.getAvaliableRooms(this, start, slutt);
	}
}    
protected void slutt_action(ActionEvent evt) {
	start = start_textfield.getText();
	slutt = slutt_textfield.getText();
	if (start_textfield.getText().length() == 5 && slutt_textfield.getText().length() == 5) {
		start = toDateTime(date_textfield.getText(), start);
		slutt = toDateTime(date_textfield.getText(), slutt);
		Database.getAvaliableRooms(this, start, slutt);
	}
}
protected void fjern_buttonActionPerformed(ActionEvent e) 
{
	if(person_list.getModel().getSize() >0)
	{
		Object obj = person_list.getModel().getElementAt(person_list.getSelectedIndex());
		DefaultListModel newModel = (DefaultListModel) person_list.getModel();
		if(newModel.getElementAt(person_list.getSelectedIndex()) instanceof String)
		{
			//Fortell database at person er borte fra denne avtalen..................
			newModel.removeElementAt(person_list.getSelectedIndex());
		}
		else
		{
			newModel.remove(person_list.getSelectedIndex());
			if(obj instanceof Person)
			{
				person_list.setModel(newModel);
				deltaker_combo.insertItemAt((Person) obj, 0);
				setStatus.remove((Person) obj);
			}
			else
			{
				person_list.setModel(newModel);
				deltaker_combo.insertItemAt((Group) obj,0);
			}	
		}
		if(person_list.getModel().getSize() > 0)
		{
			person_list.setSelectedIndex(0);
		}
	}
	
}

private static final Pattern rfc2822 = Pattern.compile(
        "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
);

protected void add_buttonActionPerformed(ActionEvent e) 
{
	if(deltaker_combo.getModel().getSize()>0)
	{
		if(deltaker_combo.getSelectedItem() instanceof String)
		{
			String mailaddress = JOptionPane.showInputDialog("Skriv inn mail til ekstern bruker:");
			if(mailaddress != null )
			{
				if (rfc2822.matcher(mailaddress).matches()) {
					Database.sendMail(null, mailaddress, 
							("Avtaleinvitasjon " + avtalenavn_textfield.getText().toString()).replace(" ", "[space]"), 
							("Du er invitert til avtale, klokken " + start_textfield.getText().toString() 
							+ " i " + "av " + Bruker.getInstance().getUser().getName()).replace(" ", "[space]"));

					DefaultListModel newModel = (DefaultListModel) person_list.getModel();	
					newModel.addElement(mailaddress);
					person_list.setModel(newModel);
				}else{
					System.err.println("ERROR: mail format");
				}
				
				
			}
		}
		else
		{	Object obj = deltaker_combo.getSelectedItem();
			deltaker_combo.removeItemAt(deltaker_combo.getSelectedIndex());
			DefaultListModel newModel = (DefaultListModel) person_list.getModel();
			if(obj instanceof Person)
			{
				newModel.addElement((Person) obj);
				person_list.setModel(newModel);
				setStatus.put((Person) obj, "IkkeSvart");
			}
			else
			{
				newModel.addElement((Group) obj);
				person_list.setModel(newModel);
			}
		}
	}
}
protected void forrige_buttonActionPerformed(ActionEvent evt) 
{
	editDate(-1);
}
public void editDate(Integer increment)
{
	Date date = new Date();
	date.setMonth(dateChooser.getSelectionDate().getMonth()+increment);
	if(date.getMonth()== 11)
	{
		date.setYear(dateChooser.getSelectionDate().getYear()+increment);
	}
	else{date.setYear(dateChooser.getSelectionDate().getYear());}
	date.setDate(1);
	dateChooser.setSelectionDate(date);
	dateChooser.ensureDateVisible(date);
	date_textfield.setText(date.getDate()+":"+(date.getMonth()+1)+":"+(date.getYear()+1900));
}

private boolean harID = false;
private void lagre_buttonActionPerformed(java.awt.event.ActionEvent evt) 
{
	meeting.setCreator(Bruker.getInstance().getUser().getId());
	meeting.setDescription(beskrivelse_textfield.getText());
	meeting.setName(avtalenavn_textfield.getText());
	meeting.setStart(toDateTime(date_textfield.getText(),start_textfield.getText()));
	System.out.println("Date = " + date_textfield.getText());
	meeting.setEnd(toDateTime(date_textfield.getText(), slutt_textfield.getText()));
	System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
	System.out.println(meeting.getStart());
	System.out.println(rom_list.getSelectedValue() instanceof Room);
	System.out.println(rom_list.getSelectedIndex());
	if (rom_list.getSelectedValue() != null)
	{
		room = (Room) rom_list.getSelectedValue();
		meeting.setPlace("NA");
		meeting.setRoom(room.getId());
	}
	else if (rom_list.getSelectedValue() == null) 
	{
		if (rom_textfield.getText() == "" || rom_textfield.getText() == null) 
		{
			meeting.setPlace("NA");
		}
		else meeting.setPlace(rom_textfield.getText());
	}
	else
	{
		JOptionPane.showMessageDialog(this, "Du må spesifisere et rom eller velge fra listen.");
		return;
	}

	ArrayList list = new ArrayList();
	for (int x = 0; x<person_list.getModel().getSize(); x++) 
	{
		if (person_list.getModel().getElementAt(x) instanceof Person) {
			list.add((Person) person_list.getModel().getElementAt(x));			
		} else {
			list.add(person_list.getModel().getElementAt(x));
		}
	}
	meeting.setParticipants(list);
	if (edit) {
		complete = true;
		System.out.println("Sending update to server");
		Database.updateMeeting(this, meeting);
		
	}
	else {
		complete = true;
		Database.addMeeting(this, meeting);
	}
	ArrayList<Meeting> mongobarn = Bruker.getInstance().getAvtaler();
	if(edit)
	{
		for(Meeting m : mongobarn)
		{
			if(m.getId() == meeting.getId()){
				mongobarn.remove(m);
				break;
			}
		}
	}
	mongobarn.add(meeting);
	Bruker.getInstance().setAvtaler(mongobarn);
	
	if((setStatus.size() != 0 && this.edit))
	{
		for(Person p: setStatus.keySet())
		{
			Database.updateParticipantStatus(this, ""+meeting.getId(), ""+p.getId(), ""+setStatus.get(p));
		}
	}
	System.out.println("I got here..");
	System.out.println(main.getTabs().getComponents());
	for(Component c : main.getTabs().getComponents())
	{
		((Panel) c).addMeeting(meeting);
		((Panel) c).refresh();
	}
	main.setMeeting(meeting);
	main.updateKomMeetings();
	main.setVisible(true);
	this.setVisible(false);
}

private void avbryt_buttonActionPerformed(java.awt.event.ActionEvent evt){
main.setVisible(true);
this.setVisible(false);

}

private void auto_select_choiceActionPerformed(java.awt.event.ActionEvent evt) {
	if (auto_select_choice.isSelected())
	{
		if (!rom_list.isSelectionEmpty()) {
			rom_list.clearSelection();
		}
		rom_list.disable();
		Database.getParticipants(this, meeting);
		room = rooms.get(0);
		for (Room r : rooms) {
			if (r.getSize() > deltakere.size() && r.getSize() < room.getSize()) {
				room = r;		
			}
		}
		meeting.setPlace("NA");
		
	}
	else {
		rom_list.enable();
	}
		
}

private void dateChooserActionPerformed(java.awt.event.ActionEvent evt) {
		
	date_textfield.setText(
				 dateChooser.getSelectionDate().getDate()+":"
				+(0+dateChooser.getSelectionDate().getMonth()+1)+":"
				+(dateChooser.getSelectionDate().getYear()+1900));
	System.out.println("Date..." + date_textfield.getText());
	start = start_textfield.getText();
	slutt = slutt_textfield.getText();
	if (start_textfield.getText().length() == 5 && slutt_textfield.getText().length() == 5) {
		start = toDateTime(date_textfield.getText(), start);
		slutt = toDateTime(date_textfield.getText(), slutt);
		Database.getAvaliableRooms(this, start, slutt);
		}
}

private void next_buttonActionPerformed(java.awt.event.ActionEvent evt)
{
	editDate(1);
}
private void date_textfieldActionPerformed(java.awt.event.ActionEvent evt) 
{

}


    private javax.swing.JButton lagre_button;
    private javax.swing.JButton avbryt_button;
    private javax.swing.JButton forrige_button;
    private javax.swing.JButton next_button;
    private javax.swing.JButton add_button;
    private javax.swing.JButton fjern_button;
    private javax.swing.JComboBox deltaker_combo;
    private javax.swing.JLabel avtale_label;
    private javax.swing.JLabel beskrivelse_label;
    private javax.swing.JLabel rom_label;
    private javax.swing.JLabel velg_label;
    private javax.swing.JLabel dato_label;
    private javax.swing.JLabel start_label;
    private javax.swing.JLabel slutt_label;
    private javax.swing.JLabel varighet_label;
    private javax.swing.JLabel deltaker_label;
    private javax.swing.JList rom_list;
    private javax.swing.JList <Person>person_list;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton auto_select_choice;
    private javax.swing.JScrollPane romScrollPane;
    private javax.swing.JScrollPane beskrivelse_scrollpane;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea beskrivelse_textfield;
    private javax.swing.JTextField avtalenavn_textfield;
    private javax.swing.JTextField rom_textfield;
    private javax.swing.JTextField date_textfield;
    private javax.swing.JTextField start_textfield;
    private javax.swing.JTextField slutt_textfield;
    private javax.swing.JTextField varighet_textfield;
    private org.jdesktop.swingx.JXMonthView dateChooser;
    private DefaultListModel romlist_model;
    private DefaultListModel personlist_model;
    
    private class combo_box_person_renderer extends JLabel implements ListCellRenderer
    {

		@Override
		public JLabel getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) 
		{
			this.setBackground(Color.WHITE);
			if(value == null)
			{
				return null;
			}
			if(value instanceof Person)
			{
				ImageIcon icon = new ImageIcon("resources/images/person.png");
				this.setIcon(icon);
				Person person = (Person) value;
				String name = person.getName();
				String ID = Integer.toString(person.getId());
				this.setText(name + " - " + ID);
			}
			else if(value instanceof String)
			{
				ImageIcon icon = new ImageIcon("resources/images/email.png");
				this.setIcon(icon);
				this.setText("Ekstern bruker");
			}
			else
			{
				ImageIcon icon = new ImageIcon("resources/images/group.png");
				this.setIcon(icon);
				Group group = (Group) value;
				String name = group.getName();
				String ID = Integer.toString(group.getID());
				this.setText("Gruppe "+ID+": " +name);
			}
			return this;
		}
    	
    }
    private class list_person_renderer extends JLabel implements ListCellRenderer
    {
    	public list_person_renderer()
    	{
    		setOpaque(true);
    		
    	}
		@Override
		public JLabel getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) 
		{
			if(isSelected)
			{
				this.setBackground(Color.GRAY);
			}
			else
			{
				this.setBackground(Color.WHITE);
			}
			if(value instanceof Person)
			{
				ImageIcon icon = new ImageIcon("resources/images/person.png");
				Person person = (Person) value;
				String name = person.getName();
				String email = ((Person) value).getEmail();
				setIcon(icon);
				setText(name + " - " + email);
			}

			else if(value instanceof String)
			{
				ImageIcon icon = new ImageIcon("resources/images/email.png");
				this.setIcon(icon);
				this.setText("Ekstern bruker - "+ value.toString());
				
			}
			else
			{
				ImageIcon icon = new ImageIcon("resources/images/group.png");
				Group group = (Group) value;
				String name = group.getName();
				String ID = Integer.toString(group.getID());
				setIcon(icon);
				setText(name + " -  Gruppe" + ID);
			}
			return this;
		}
    	
    }
}
