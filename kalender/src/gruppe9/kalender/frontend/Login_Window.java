package gruppe9.kalender.frontend;

import gruppe9.kalender.client.ApiCaller;
import gruppe9.kalender.client.CalResponse;
import gruppe9.kalender.client.Client;
import gruppe9.kalender.client.Database;
import gruppe9.kalender.client.ServerPuller;
import gruppe9.kalender.model.Person;
import gruppe9.kalender.user.Bruker;


public class Login_Window extends javax.swing.JFrame implements ApiCaller {

	/** Creates new form Login_Window */
	public Login_Window() 
	{
		this.setVisible(true);
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	public void attemptLogin()
	{
		String email = user_field.getText();
		String pswd = pass_field.getText();
		if(email.contains("@") && pswd.length() > 2){
			Database.login(this, email, pswd);
		}else{
			System.out.println("Wrong email or password!");
		}

	}

	public void callBack(CalResponse response)
	{
		if(response.confirmLogin()){			
			Main_Window window = new Main_Window(this);
			window.setVisible(true);
			this.setVisible(false);
		} else {
			user_field.setToolTipText(response.getMsg());
			System.out.println(response.getMsg());
			//Force tooltip ?
		}
	}
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		user_field = new javax.swing.JTextField();
		user_label = new javax.swing.JLabel();
		pass_label = new javax.swing.JLabel();
		pass_field = new javax.swing.JPasswordField();
		login_button = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		java.awt.event.ActionListener listener = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				attemptLogin();
			}
		};   
		user_field.addActionListener(listener);
		pass_field.addActionListener(listener);
		login_button.addActionListener(listener);
		user_label.setText("Brukernavn:");
		pass_label.setText("Passord:");
		login_button.setText("Logg inn");
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(94, 94, 94)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(login_button)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup()
														.addComponent(user_label)
														.addGap(3, 3, 3))
														.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																.addComponent(pass_label)
																.addGap(4, 4, 4)))
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																		.addComponent(pass_field)
																		.addComponent(user_field, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
																		.addContainerGap(135, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap(53, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(user_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(user_label))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(pass_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(pass_label))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(login_button)
										.addGap(44, 44, 44))
				);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void user_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_fieldActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_user_fieldActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Login_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Login_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Login_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Login_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new Login_Window().setVisible(true);
			}
		});
	}
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton login_button;
	private javax.swing.JLabel user_label;
	private javax.swing.JLabel pass_label;
	private javax.swing.JPasswordField pass_field;
	private javax.swing.JTextField user_field;
	// End of variables declaration//GEN-END:variables
}
