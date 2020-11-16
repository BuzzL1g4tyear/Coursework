import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;

public class Register extends javax.swing.JFrame {
    
    public Register() {
	initComponents();
    }
    public static final String registerUserQuery = "INSERT INTO `user`(`login`, `NN`, `password`, `U_F_Name`, `U_S_Name`, `Tel`) VALUES (?,?,?,?,?,?)";
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPasswordField_pas = new javax.swing.JPasswordField();
        jButton1Login = new javax.swing.JButton();
        jButton2Canсel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField_Login = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField_Name = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField_SecondName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField_Tel = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_NN = new javax.swing.JTextField();
        jLabel_back = new javax.swing.JLabel();
        jLabel_Pas = new javax.swing.JLabel();
        jPasswordField_pas1 = new javax.swing.JPasswordField();
        jLabel_Rep_Pas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(235, 149, 50));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Окно регистрации");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel2.setText("Х");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel3.setText("_");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(34, 49, 63));

        jPasswordField_pas.setBackground(new java.awt.Color(191, 191, 191));

        jButton1Login.setBackground(new java.awt.Color(103, 128, 159));
        jButton1Login.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton1Login.setForeground(new java.awt.Color(255, 255, 255));
        jButton1Login.setText("Создать");
        jButton1Login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1LoginActionPerformed(evt);
            }
        });

        jButton2Canсel.setBackground(new java.awt.Color(207, 0, 15));
        jButton2Canсel.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton2Canсel.setForeground(new java.awt.Color(255, 255, 255));
        jButton2Canсel.setText("Отмена");
        jButton2Canсel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2Canсel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2CanсelActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Логин :");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Пароль :");

        jTextField_Login.setBackground(new java.awt.Color(191, 191, 191));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Имя :");

        jTextField_Name.setBackground(new java.awt.Color(191, 191, 191));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Фамилия :");

        jTextField_SecondName.setBackground(new java.awt.Color(191, 191, 191));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Эл. почта :");

        jTextField_Tel.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_Tel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_TelKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Повторите пароль :");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Никнейм :");

        jTextField_NN.setBackground(new java.awt.Color(191, 191, 191));

        jLabel_back.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_back.setText("Есть аккаунт");
        jLabel_back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_backMouseClicked(evt);
            }
        });

        jLabel_Pas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/eye.png"))); // NOI18N
        jLabel_Pas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_PasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_PasMouseExited(evt);
            }
        });

        jPasswordField_pas1.setBackground(new java.awt.Color(191, 191, 191));

        jLabel_Rep_Pas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/eye.png"))); // NOI18N
        jLabel_Rep_Pas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_Rep_PasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_Rep_PasMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButton2Canсel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1Login)
                .addGap(34, 34, 34))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPasswordField_pas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_Pas))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField_Login, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(jTextField_NN, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addComponent(jTextField_Name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_SecondName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_Tel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPasswordField_pas1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_Rep_Pas)))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(jLabel_back)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_Login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField_NN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(13, 13, 13))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPasswordField_pas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_Pas))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jPasswordField_pas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Rep_Pas)))
                .addGap(25, 25, 25)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField_SecondName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField_Tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2Canсel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1Login, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jLabel_back)
                .addGap(21, 21, 21))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton1LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1LoginActionPerformed
        
	String login = jTextField_Login.getText();
	String NN = jTextField_NN.getText();
	String pas = String.valueOf(jPasswordField_pas.getPassword());
	String reppas = String.valueOf(jPasswordField_pas1.getPassword());
	String name = jTextField_Name.getText();
	String fam = jTextField_SecondName.getText();
	String mail = jTextField_Tel.getText();
	
	if(verifyFields()){
	    if(!checkUsLog(login)){
		if(checkMail(mail)){
		    PreparedStatement ps;
		    ResultSet rs;

		    try {
			ps = Users_DB.getConection().prepareStatement(registerUserQuery);
			ps.setString(1, login);
			ps.setString(2, NN);
			ps.setString(3, pas);
			ps.setString(4, name);
			ps.setString(5, fam);
			ps.setString(6, mail);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Ваш аккаунт успешно создан!","Регистрация завершена!",JOptionPane.PLAIN_MESSAGE);
		    } catch (SQLException ex) {
			Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
	    }
	}
    }//GEN-LAST:event_jButton1LoginActionPerformed

    private void jButton2CanсelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2CanсelActionPerformed
	System.exit(0);
    }//GEN-LAST:event_jButton2CanсelActionPerformed

    private void jLabel_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_backMouseClicked
        Login nf = new Login();
	nf.setVisible(true);
	nf.pack();
	nf.setLocationRelativeTo(null);
	nf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.dispose();
    }//GEN-LAST:event_jLabel_backMouseClicked

    private void jLabel_PasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_PasMouseEntered
        
	jPasswordField_pas.setEchoChar((char)0);
	 
    }//GEN-LAST:event_jLabel_PasMouseEntered

    private void jLabel_PasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_PasMouseExited
        
	jPasswordField_pas.setEchoChar('*');
	
    }//GEN-LAST:event_jLabel_PasMouseExited

    private void jLabel_Rep_PasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Rep_PasMouseEntered
        
	jPasswordField_pas1.setEchoChar((char)0);
	
    }//GEN-LAST:event_jLabel_Rep_PasMouseEntered

    private void jLabel_Rep_PasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Rep_PasMouseExited
        
	jPasswordField_pas1.setEchoChar('*');
	
    }//GEN-LAST:event_jLabel_Rep_PasMouseExited

    private void jTextField_TelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_TelKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
	
	    String login = jTextField_Login.getText();
	    String NN = jTextField_NN.getText();
	    String pas = String.valueOf(jPasswordField_pas.getPassword());
	    String reppas = String.valueOf(jPasswordField_pas1.getPassword());
	    String name = jTextField_Name.getText();
	    String fam = jTextField_SecondName.getText();
	    String tel = jTextField_Tel.getText();

	    if(verifyFields()){
		if(!checkUsLog(login)){
		    PreparedStatement ps;
		    ResultSet rs;

		    try {
			ps = Users_DB.getConection().prepareStatement(registerUserQuery);
			ps.setString(1, login);
			ps.setString(2, NN);
			ps.setString(3, pas);
			ps.setString(4, name);
			ps.setString(5, fam);
			ps.setString(6, tel);
			ps.execute();
		    } catch (SQLException ex) {
			Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
	    }
	}
    }//GEN-LAST:event_jTextField_TelKeyPressed

    public boolean verifyFields(){
	String login = jTextField_Login.getText();
	String NN = jTextField_NN.getText();
	String pas = String.valueOf(jPasswordField_pas.getPassword());
	String reppas = String.valueOf(jPasswordField_pas1.getPassword());
	String name = jTextField_Name.getText();
	String fam = jTextField_SecondName.getText();
	String tel = jTextField_Tel.getText();
	
	if(login.trim().equals("") || NN.trim().equals("") || pas.trim().equals("")|| reppas.trim().equals("") 
	   || name.trim().equals("") || NN.trim().equals("") || fam.trim().equals("")|| tel.trim().equals("")){
	    JOptionPane.showMessageDialog(null,"Одно или более полей не заполнены!","O-o-yу",JOptionPane.ERROR_MESSAGE);
	    return false;
	}else if(!pas.equals(reppas)){
	    JOptionPane.showMessageDialog(null,"Пароли не совпадают!","O-o-yу",JOptionPane.ERROR_MESSAGE);
	    return false;
	}else{
	    return true;
	}
    }
    
    public boolean checkUsLog(String login){
	PreparedStatement ps;
	ResultSet rs;
	boolean login_exist = false; 
	
	String query = "SELECT * FROM `user` WHERE `login` = ?";
	
	try {
	    ps = Users_DB.getConection().prepareStatement(query);
	    ps.setString(1,login);
	    rs = ps.executeQuery();
	    if(rs.next()){
		login_exist = true;
		JOptionPane.showMessageDialog(null,"Этот логин занят!","Ошибка",2);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
	}
	return login_exist;
    }
    
    public boolean checkMail(String mail){
	
	boolean check_exist = false;
	
	if(mail.contains("@") && mail.contains(".")){
	    check_exist = true;
	}else{
	    JOptionPane.showMessageDialog(null, "Почта некорректна!","Ошибка!",1);
	    check_exist = false;
	}
	return check_exist;
    }
    
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
	    java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new Register().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1Login;
    private javax.swing.JButton jButton2Canсel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Pas;
    private javax.swing.JLabel jLabel_Rep_Pas;
    private javax.swing.JLabel jLabel_back;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField_pas;
    private javax.swing.JPasswordField jPasswordField_pas1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField_Login;
    private javax.swing.JTextField jTextField_NN;
    private javax.swing.JTextField jTextField_Name;
    private javax.swing.JTextField jTextField_SecondName;
    private javax.swing.JTextField jTextField_Tel;
    // End of variables declaration//GEN-END:variables
}
