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

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import gruppe9.kalender.model.Group;
import gruppe9.kalender.model.Meeting;
import gruppe9.kalender.model.Person;

/**
 *
 * @author krake
 */
public class Edit_Avtale extends javax.swing.JFrame {
    private Main_Window main;
    Meeting meeting;
    public Edit_Avtale(Main_Window main, Meeting meeting) 
    {
    	initComponents();
    	setMeeting(meeting);
        this.main = main;
        person_list.setCellRenderer(new list_person_renderer());
        deltaker_combo.setRenderer(new combo_box_person_renderer());
//        deltaker_combo.setEditable(true);
    }
    private void setMeeting(Meeting meeting)
    {
    	if(meeting != null)
    	{
    		this.meeting = meeting; 
    		return;
    	}
    	else
    	{
    	}
    	
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
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
        stop_textfield = new javax.swing.JTextField();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        rom_list = new javax.swing.JList();
        auto_select_choice = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(-16777216,true)));

        avtale_label.setText("Avtalenavn:");

        avtalenavn_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avtalenavn_textfieldActionPerformed(evt);
            }
        });

        beskrivelse_label.setText("Beskrivelse:");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(-16777216,true)));

        deltaker_label.setText("Deltakere:");
        Person Lars = new Person(0,"Lars", 97133287,"Johannesburgveien 38, Oslo, Norge", "Lajohanburg@gmail.com");
        deltaker_combo.addItem(Lars);
        Person Arne = new Person(1,"Arne nilsen", 88370299,"Fredrikshavn, Zimbabwe", "AniZimb@zimbambwe.gov");
        deltaker_combo.addItem(Arne);
        Lars = new Person(2,"Kjell", 93201839,"Nordpolen", "santa@clause.sexy");
        deltaker_combo.addItem(Lars);
        Group g = new Group("The League");
        g.setID(10);g.setDescription("Kjekke karer som liker å spise pai.");
        g.addPerson(Lars); g.addPerson(Arne);
        deltaker_combo.addItem(g);

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

        person_list.setModel(new DefaultListModel());
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

        varighet_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varighet_textfieldActionPerformed(evt);
            }
        });

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
                                .addComponent(stop_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                            .addComponent(stop_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        rom_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rom_textfieldActionPerformed(evt);
            }
        });

        velg_label.setText("Velg fra liste:");

        rom_list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(rom_list);

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
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
    	
    	stop_textfield.setToolTipText("Sluttidspunkt i formatet HH:MM, f.eks 12:00");
    	stop_textfield.setColumns(5);
    }// </editor-fold>//GEN-END:initComponents

protected void fjern_buttonActionPerformed(ActionEvent e) 
{
	if(person_list.getModel().getSize() >0)
	{
		Object obj = person_list.getModel().getElementAt(person_list.getSelectedIndex());
		DefaultListModel newModel = (DefaultListModel) person_list.getModel();
		newModel.remove(person_list.getSelectedIndex());
		if(obj instanceof Person)
		{
			person_list.setModel(newModel);
			deltaker_combo.addItem((Person) obj);
		}
		else
		{
			person_list.setModel(newModel);
			deltaker_combo.addItem((Group) obj);
		}
	}
	
}
protected void add_buttonActionPerformed(ActionEvent e) 
{
	if(deltaker_combo.getModel().getSize()>0)
	{
		Object obj = deltaker_combo.getSelectedItem();
		deltaker_combo.removeItemAt(deltaker_combo.getSelectedIndex());
		DefaultListModel newModel = (DefaultListModel) person_list.getModel();
		if(obj instanceof Person)
		{
			newModel.addElement((Person) obj);
			person_list.setModel(newModel);
		}
		else
		{
			newModel.addElement((Group) obj);
			person_list.setModel(newModel);
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
	System.out.println(dateChooser.getSelectionDate().getYear() +":"+dateChooser.getSelectionDate().getMonth());
	dateChooser.ensureDateVisible(date);
	date_textfield.setText(date.getDate()+":"+(date.getMonth()+1)+":"+(date.getYear()+1900));
}
private void lagre_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lagre_buttonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_lagre_buttonActionPerformed

private void avtalenavn_textfieldActionPerformed(java.awt.event.ActionEvent evt)
{//GEN-FIRST:event_avtalenavn_textfieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_avtalenavn_textfieldActionPerformed

private void varighet_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varighet_textfieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_varighet_textfieldActionPerformed

private void rom_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rom_textfieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_rom_textfieldActionPerformed

private void avbryt_buttonActionPerformed(java.awt.event.ActionEvent evt){
main.setVisible(true);
this.setVisible(false);

}

private void auto_select_choiceActionPerformed(java.awt.event.ActionEvent evt) {
}

private void dateChooserActionPerformed(java.awt.event.ActionEvent evt) 
{
	date_textfield.setText(
					 dateChooser.getSelectionDate().getDate()+":"
					+(dateChooser.getSelectionDate().getMonth()+1)+":"
					+(dateChooser.getSelectionDate().getYear()+1900));
}

private void next_buttonActionPerformed(java.awt.event.ActionEvent evt)
{
	editDate(1);
}
private void date_textfieldActionPerformed(java.awt.event.ActionEvent evt) 
{
	System.out.println("textfield3!");
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
    private javax.swing.JList person_list;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton auto_select_choice;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField avtalenavn_textfield;
    private javax.swing.JTextField rom_textfield;
    private javax.swing.JTextField date_textfield;
    private javax.swing.JTextField start_textfield;
    private javax.swing.JTextField stop_textfield;
    private javax.swing.JTextField varighet_textfield;
    private org.jdesktop.swingx.JXMonthView dateChooser;
    
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
			else
			{
				ImageIcon icon = new ImageIcon("resources/images/group.png");
				this.setIcon(icon);
				Group group = (Group) value;
				String name = group.getName();
				String ID = Integer.toString(group.getID());
				this.setText("Gruppe "+ID+": " +name);
			}
			System.out.println(" ");
			return this;
		}
    	
    }
    private class list_person_renderer extends JLabel implements ListCellRenderer
    {

    	public list_person_renderer()
    	{
    		this.setOpaque(true);
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
				this.setIcon(icon);
				Person person = (Person) value;
				String name = person.getName();
				String ID = Integer.toString(person.getId());
				String email = ((Person) value).getEmail(); 
				this.setText(name + " - " + ID + " - " + email);
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
}
