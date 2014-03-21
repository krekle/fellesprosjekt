/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Varsel_Popup.java
 *
 * Created on Mar 13, 2014, 3:53:58 PM
 */
package gruppe9.kalender.frontend;

import gruppe9.kalender.client.Database;
import gruppe9.kalender.model.Alert;
import gruppe9.kalender.model.Meeting;

import javax.swing.JFileChooser;

/**
 *
 * @author krake
 */
public class Varsel_Popup extends javax.swing.JFrame {

    /** Creates new form Varsel_Popup */
    Alert alert;
    Meeting meeting;
    @SuppressWarnings("unused")
	public Varsel_Popup(String type, Meeting avtale) 
    {
    	meeting = avtale;
        initComponents();
        Alert A = null;
        type_label.setText(type);
        if(type.equals("E-mail"))
        {
        	A = null; //avtale.getEmailAlert();
        }
        else
        {
        	A = null; //avtale.getSoundAlert();
        }
        if(A != null)
        {
        	this.alert = A;
        	this.date_time_text.setText(A.getTime());
        	this.melding_text.setText(A.getDesciption());
        	if(type.equals("Alarm"))
        	{
        		this.sound_label.setText(A.getSound());
        	}
        }
        else
        {
        	this.alert = new Alert("","","",1,"");
        }
    }
    public String getAlertType()
    {
    	return type_label.getText(); 
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jFileChooser1 = new javax.swing.JFileChooser();
        type_label = new javax.swing.JLabel();
        type_selection_box = new javax.swing.JComboBox();
        datetime_label = new javax.swing.JLabel();
        date_time_text = new javax.swing.JTextField();
        date_time_text.setText(meeting.getStart());
        jScrollPane1 = new javax.swing.JScrollPane();
        melding_text = new javax.swing.JTextArea();
        melding_label = new javax.swing.JLabel();
        save_button = new javax.swing.JButton();
        cancel_button = new javax.swing.JButton();
        sound_button = new javax.swing.JButton();
        sound_label = new javax.swing.JLabel();
        setResizable(false);

        type_label.setText("Type:");

        type_selection_box.setVisible(false);

        datetime_label.setText("Dato-Tid:");
        date_time_text.setToolTipText("Tast inn dato og tid i formatet yyyy-mm-dd tt:mm:ss.");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        melding_text.setColumns(20);
        melding_text.setLineWrap(true);
        melding_text.setRows(5);
        melding_text.setToolTipText("");
        jScrollPane1.setViewportView(melding_text);

        melding_label.setText("Melding:");

        save_button.setText("Lagre");
        save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_buttonActionPerformed(evt);
            }
        });

        cancel_button.setText("Avbryt");
        cancel_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_buttonActionPerformed(evt);
            }
        });

        sound_button.setText("Lyd");
        sound_button.setVisible(false);
        sound_button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sound_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sound_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(datetime_label)
                    .addComponent(type_label)
                    .addComponent(melding_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sound_label)
                        .addContainerGap())
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(date_time_text, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(sound_button, 0, 0, Short.MAX_VALUE)
                                .addComponent(save_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancel_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(24, 24, 24))
                        .addComponent(type_selection_box, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(type_label)
                    .addComponent(type_selection_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datetime_label)
                    .addComponent(save_button)
                    .addComponent(date_time_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancel_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sound_button))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(melding_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sound_label)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sound_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sound_buttonActionPerformed
        JFileChooser FC = new JFileChooser();
        int returnValue = FC.showOpenDialog(this);
        sound_label.setText(FC.getSelectedFile().getName().toString());
    }
    private void save_buttonActionPerformed(java.awt.event.ActionEvent evt)
    {
    	alert.setDesciption(melding_text.getText());
    	alert.setTime(this.date_time_text.getText());    	
    	alert.setType("E-mail");
    	alert.setMeetingID(meeting.getId());
    	Database.addAlert(null, alert);
    	this.setVisible(false);
    	Main_Window.popupExists = false;
    }
    private void cancel_buttonActionPerformed(java.awt.event.ActionEvent evt) 
    {
    	Main_Window.popupExists = false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton save_button;
    private javax.swing.JButton cancel_button;
    private javax.swing.JButton sound_button;
    private javax.swing.JComboBox type_selection_box;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel type_label;
    private javax.swing.JLabel sound_label;
    private javax.swing.JLabel datetime_label;
    private javax.swing.JLabel melding_label;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea melding_text;
    private javax.swing.JTextField date_time_text;
    // End of variables declaration//GEN-END:variables
	public void setAlert(Alert a) 
	{
		this.date_time_text.setText(a.getTime());
		this.melding_text.setText(a.getDesciption());
		this.sound_label.setText(a.getSound());
	}
}
