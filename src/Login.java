import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.border.Border;


public class Login extends javax.swing.JFrame {

    
    public Login() {
	initComponents();
	this.setLocationRelativeTo(null);

	this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("1.png")));
	
    }
    
    public static final String log_query = "SELECT * FROM `user` WHERE `login` = ? AND `password` = ?";
    public static final String LOGIN = "admin";
  
    public static boolean chLogin;
    public static String ZAK_LOG;
    public static int ZAK_ID;
    
    public void chLog(){
	String login = jTextField_Login.getText();
	
	if(login.equals(LOGIN)){
	    chLogin = true;
	    System.out.println("Admin");
	}else{
	    chLogin = false;
	    System.out.println("ne admin");
	}
    }
    
    public void exit(){
	System.exit(0);
    }
    
    public void selectID(String login){
	
	PreparedStatement st;
	ResultSet rs;
	
	String Query = "SELECT `ID` FROM `user` WHERE `login`= ?";
	
	try {
	    st = Users_DB.getConection().prepareStatement(Query);
	    st.setString(1, login);
	    rs = st.executeQuery();
	    while(rs.next()){
		int id = rs.getInt(1);
		ZAK_ID = id ;
		System.out.println(id);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
	}
	
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton_Login = new javax.swing.JButton();
        jButton_Canсel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField_Login = new javax.swing.JTextField();
        jPasswordField_pas = new javax.swing.JPasswordField();
        jLabel_Creat = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_exit = new javax.swing.JLabel();
        jLabel_min = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(34, 49, 63));

        jButton_Login.setBackground(new java.awt.Color(103, 128, 159));
        jButton_Login.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton_Login.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Login.setText("Войти");
        jButton_Login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoginActionPerformed(evt);
            }
        });

        jButton_Canсel.setBackground(new java.awt.Color(207, 0, 15));
        jButton_Canсel.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton_Canсel.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Canсel.setText("Выход");
        jButton_Canсel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Canсel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CanсelActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Логин:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Пароль:");

        jTextField_Login.setBackground(new java.awt.Color(191, 191, 191));

        jPasswordField_pas.setBackground(new java.awt.Color(191, 191, 191));
        jPasswordField_pas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField_pasKeyPressed(evt);
            }
        });

        jLabel_Creat.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Creat.setText("Создать аккаунт");
        jLabel_Creat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_Creat.setInheritsPopupMenu(false);
        jLabel_Creat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_CreatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_Canсel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                        .addComponent(jButton_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_Creat)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField_Login, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(jPasswordField_pas)))
                        .addContainerGap(90, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_Login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jPasswordField_pas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Canсel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_Creat)
                .addGap(31, 31, 31))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel1.setBackground(new java.awt.Color(235, 149, 50));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Окно входа");

        jLabel_exit.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel_exit.setText("Х");
        jLabel_exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_exitMouseClicked(evt);
            }
        });

        jLabel_min.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel_min.setText("_");
        jLabel_min.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_min.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_minMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_min)
                .addGap(18, 18, 18)
                .addComponent(jLabel_exit)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_exit)
                            .addComponent(jLabel_min)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoginActionPerformed
        
	PreparedStatement st;
	ResultSet rs;
	
	String login = jTextField_Login.getText();
	String password = String.valueOf(jPasswordField_pas.getPassword());
	
	chLog();
	selectID(login);
	
	try {
	    st = Users_DB.getConection().prepareStatement(log_query);
	    
	    st.setString(1,login);
	    st.setString(2,password);
	
	    rs = st.executeQuery();
	    
	    if(rs.next()){
		Main main = new Main();
		ZAK_LOG = login;
		main.setVisible(true);
		main.pack();
		main.setLocationRelativeTo(null);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.dispose();
	    }
	    else{
		JOptionPane.showMessageDialog(null, "Не верный логин или пароль." , "Ошибка авторизации" , 2);
	    }
	    
	} catch (SQLException ex) {
	    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
	}
	
    }//GEN-LAST:event_jButton_LoginActionPerformed

    private void jLabel_exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_exitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel_exitMouseClicked

    private void jLabel_minMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel_minMouseClicked

    private void jButton_CanсelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CanсelActionPerformed
        
	exit();
	
    }//GEN-LAST:event_jButton_CanсelActionPerformed

    private void jLabel_CreatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_CreatMouseClicked
        Register reg = new Register();
	reg.setVisible(true);
	reg.pack();
	reg.setLocationRelativeTo(null);
	reg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.dispose();
    }//GEN-LAST:event_jLabel_CreatMouseClicked

    private void jPasswordField_pasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField_pasKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
	
	    PreparedStatement st;
	    ResultSet rs;

	    String login = jTextField_Login.getText();
	    String password = String.valueOf(jPasswordField_pas.getPassword());

	    chLog();
	    selectID(login);
	    
	    try {
		st = Users_DB.getConection().prepareStatement(log_query);

		st.setString(1,login);
		st.setString(2,password);

		rs = st.executeQuery();
		
		if(rs.next()){
		    Main main = new Main();
		    ZAK_LOG = login;
		    main.setVisible(true);
		    main.pack();
		    main.setLocationRelativeTo(null);
		    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.dispose();
		}
		else{
		    JOptionPane.showMessageDialog(null, "Не верный логин или пароль." , "Ошибка авторизации" , 2);
		}

	    } catch (SQLException ex) {
		Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }//GEN-LAST:event_jPasswordField_pasKeyPressed
    
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
	    java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>
	//</editor-fold>

	/* Create and display the form */
	
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new Login().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Canсel;
    private javax.swing.JButton jButton_Login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_Creat;
    private javax.swing.JLabel jLabel_exit;
    private javax.swing.JLabel jLabel_min;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField_pas;
    private javax.swing.JTextField jTextField_Login;
    // End of variables declaration//GEN-END:variables
}
