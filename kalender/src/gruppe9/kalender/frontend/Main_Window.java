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

import gruppe9.kalender.user.Bruker;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

/**
 *
 * @author krake
 */
public class Main_Window extends javax.swing.JFrame {

	Login_Window login;
    /** Creates new form Main_Window */
    public Main_Window(Login_Window login) 
    {
    	this.login = login;
        initComponents();
        for (int x = 0; x<5; x++){
            tabWindow.addTab("Gruppe "+x, new Panel());
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

        tabWindow = new javax.swing.JTabbedPane();
        lists_scroller = new javax.swing.JScrollBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        kom_avtale_list = new javax.swing.JList();
        kom_møte_label = new javax.swing.JLabel();
        søkefelt = new javax.swing.JTextField();
        forrige_button = new javax.swing.JButton();
        neste_button = new javax.swing.JButton();
        avtale_panel = new javax.swing.JPanel();
        beskr_label = new javax.swing.JLabel();
        deltaker_label = new javax.swing.JLabel();
        tidspkt_label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        deltaker_list = new javax.swing.JList();
        eier_label = new javax.swing.JLabel();
        avslå_choice = new javax.swing.JRadioButton();
        varsling_label = new javax.swing.JLabel();
        dato_label = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        avtale_label = new javax.swing.JLabel();
        endre_label = new javax.swing.JLabel();
        delta_choice = new javax.swing.JRadioButton();
        rediger_button = new javax.swing.JButton();
        slett_button = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        beskrivelse_area = new javax.swing.JTextArea();
        uke_label = new javax.swing.JLabel();
        top_panel = new javax.swing.JPanel();
        login_info_label = new javax.swing.JLabel();
        loggut_button = new javax.swing.JButton();
        notification_button = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        kom_avtale_list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(kom_avtale_list);

        kom_møte_label.setText("Kommende Møter:");

        søkefelt.setText("Uke...");

        forrige_button.setFont(new java.awt.Font("Dialog", 1, 18));
        forrige_button.setText("<");

        neste_button.setFont(new java.awt.Font("Dialog", 1, 18));
        neste_button.setText(">");
        neste_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neste_buttonActionPerformed(evt);
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

        avslå_choice.setText("Avslå ");
        avslå_choice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avslå_choiceActionPerformed(evt);
            }
        });

        varsling_label.setText("Varsling:");

        dato_label.setText("Dato:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "E-Mail", "Snail-Mail", "Trompet", "Alarm" }));

        avtale_label.setText("Avtale:");

        endre_label.setText("Endre:");

        delta_choice.setText("Delta");
        delta_choice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delta_choiceActionPerformed(evt);
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

        javax.swing.GroupLayout avtale_panelLayout = new javax.swing.GroupLayout(avtale_panel);
        avtale_panel.setLayout(avtale_panelLayout);
        avtale_panelLayout.setHorizontalGroup(
            avtale_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(avtale_panelLayout.createSequentialGroup()
                .addGroup(avtale_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(avtale_panelLayout.createSequentialGroup()
                        .addComponent(avtale_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                        .addComponent(delta_choice)
                        .addGap(3, 3, 3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, avtale_panelLayout.createSequentialGroup()
                        .addContainerGap(185, Short.MAX_VALUE)
                        .addComponent(avslå_choice))
                    .addGroup(avtale_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(beskr_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eier_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tidspkt_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dato_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                    .addComponent(deltaker_label)
                    .addComponent(varsling_label)
                    .addComponent(endre_label))
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
            .addComponent(jComboBox1, 0, 259, Short.MAX_VALUE)
            .addComponent(rediger_button, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
            .addComponent(slett_button, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
        );
        avtale_panelLayout.setVerticalGroup(
            avtale_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(avtale_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(avtale_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delta_choice)
                    .addComponent(avtale_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(avslå_choice)
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
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(endre_label)
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

        login_info_label.setFont(new java.awt.Font("SansSerif", 1, 12));
        login_info_label.setText("Logget inn som"+ Bruker.getUsername()+", PhD");
        loggut_button.setText("Logg ut");
        loggut_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loggut_buttonActionPerformed(evt);
            }
        });

        
        
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
        javax.swing.GroupLayout top_panelLayout = new javax.swing.GroupLayout(top_panel);
        top_panel.setLayout(top_panelLayout);
        top_panelLayout.setHorizontalGroup(
            top_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(login_info_label, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 561, Short.MAX_VALUE)
                .addComponent(notification_button, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loggut_button)
                .addContainerGap())
        );
        top_panelLayout.setVerticalGroup(
            top_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(top_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login_info_label)
                    .addComponent(loggut_button)
                    .addComponent(notification_button))
                .addGap(8, 8, 8))
        );

        jSeparator1.setForeground(new java.awt.Color(-10197916,true));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(forrige_button, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kom_møte_label)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tabWindow, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(søkefelt, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(lists_scroller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(neste_button, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(avtale_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(top_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addComponent(uke_label)
                .addContainerGap(610, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1015, Short.MAX_VALUE)
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
                                        .addComponent(søkefelt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(174, 174, 174)
                                                .addComponent(forrige_button)
                                                .addGap(41, 41, 41))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lists_scroller, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tabWindow, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(kom_møte_label)
                                                .addGap(7, 7, 7)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(200, 200, 200)
                                        .addComponent(neste_button))))
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

private void neste_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neste_buttonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_neste_buttonActionPerformed

private void avslå_choiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avslå_choiceActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_avslå_choiceActionPerformed

private void delta_choiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delta_choiceActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_delta_choiceActionPerformed

private void rediger_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rediger_buttonActionPerformed
// TODO add your handling code here:
    //REDIGER AVTALE
    Edit_Avtale a = new Edit_Avtale(this);
    a.setVisible(true);
    this.setVisible(false);
    a.setLocation(this.getLocation());
}//GEN-LAST:event_rediger_buttonActionPerformed

private void slett_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slett_buttonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_slett_buttonActionPerformed

private void loggut_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loggut_buttonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_loggut_buttonActionPerformed

private void notification_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notification_buttonActionPerformed
	System.out.println("YOU CLICKED THE NOTIFICATION_BUTTON");
// TODO add your handling code here:
}//GEN-LAST:event_notification_buttonActionPerformed



//TODO add function checking wether or not unseen notifications exist.
private boolean hasNewNotification() {
	// TODO Auto-generated method stub
	return true;
}
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton forrige_button;
    private javax.swing.JButton neste_button;
    private javax.swing.JButton rediger_button;
    private javax.swing.JButton slett_button;
    private javax.swing.JButton loggut_button;
    private javax.swing.JButton notification_button;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel kom_møte_label;
    private javax.swing.JLabel endre_label;
    private javax.swing.JLabel login_info_label;
    private javax.swing.JLabel uke_label;
    private javax.swing.JLabel tidspkt_label;
    private javax.swing.JLabel eier_label;
    private javax.swing.JLabel beskr_label;
    private javax.swing.JLabel deltaker_label;
    private javax.swing.JLabel varsling_label;
    private javax.swing.JLabel dato_label;
    private javax.swing.JLabel avtale_label;
    private javax.swing.JList kom_avtale_list;
    private javax.swing.JList deltaker_list;
    private javax.swing.JPanel avtale_panel;
    private javax.swing.JPanel top_panel;
    private javax.swing.JRadioButton delta_choice;
    private javax.swing.JRadioButton avslå_choice;
    private javax.swing.JScrollBar lists_scroller;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea beskrivelse_area;
    private javax.swing.JTextField søkefelt;
    private javax.swing.JTabbedPane tabWindow;
    // End of variables declaration//GEN-END:variables
}
