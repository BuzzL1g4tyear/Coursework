
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class profiles extends javax.swing.JFrame {

    
    public profiles() {
	initComponents();
	show_Users_In_Table();
	
	Login log = new Login();
    }

    public static final String Upd_Us_Query = "UPDATE `user` SET `login`=? ,`NN`=?,`password`=?,`U_F_Name`=?,`U_S_Name`=?,`Tel`=? WHERE  ID = ?";
    public static final String Del_Us_Query = "DELETE FROM `user` WHERE ID =?";
    
    public ArrayList <Users_DB> get_Users(){
    
	ArrayList <Users_DB> Users_List = new ArrayList <Users_DB>();
	
	Statement ps;
	ResultSet rs;
	
	String MB_Query = "SELECT * FROM `user` WHERE ID >1"; 
	
	try {
	    ps = Users_DB.getConection().createStatement();
	    rs = ps.executeQuery(MB_Query);
	    Users_DB users;
	    
	    while(rs.next()){
	    
		users = new Users_DB(rs.getInt("ID"),rs.getString("login"),rs.getString("NN"),rs.getString("password"),rs.getString("U_F_Name"),rs.getString("U_S_Name"),rs.getString("Tel"));
		
		Users_List.add(users);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(MotherBoard.class.getName()).log(Level.SEVERE, null, ex);
	}
	return Users_List;
    }
    
    public void show_Users_In_Table(){
    
	ArrayList <Users_DB> list = get_Users();
	DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
	
	model.setRowCount(0);
	Object[] row = new Object[7];
	for(int i = 0; i<list.size();i++){
	    row[0] = list.get(i).getID();
	    row[1] = list.get(i).getLogin();
	    row[2] = list.get(i).getNN();
	    row[3] = list.get(i).getPas();
	    row[4] = list.get(i).getFName();
	    row[5] = list.get(i).getSName();
	    row[6] = list.get(i).getEmail();
	    
	    model.addRow(row);
	}
    }
    
    public void Show_Item(int index){
	
	jTextField_Login.setText(get_Users().get(index).getLogin());
	jTextField_NN.setText(get_Users().get(index).getNN());
	jTextField_Pas.setText(get_Users().get(index).getPas());
	jTextField_F_Name.setText(get_Users().get(index).getFName());
	jTextField_S_Name.setText(get_Users().get(index).getSName());
	jTextField_Email.setText(get_Users().get(index).getEmail());
	jTextField_ID.setText(Integer.toString(get_Users().get(index).getID()));
	
    }
    
    public boolean checkFields(String id, String login, String NN, String pas, String fname, String sname, String mail){
	
	if(jTextField_ID.getText().trim().equals("")||jTextField_Login.getText().trim().equals("")
	    ||jTextField_NN.getText().trim().equals("")||jTextField_Pas.getText().equals("")
	    ||jTextField_F_Name.getText().trim().equals("")||jTextField_S_Name.getText().equals("")
	    ||jTextField_Email.getText().trim().equals("")){
	    JOptionPane.showMessageDialog(null, "Заполните все поля!" ,"O-o-yу", 2);
	    return false;
	}else{
	    return true;
	}
    }
    
    public boolean checkUs(String login, String NN, String pas, String fname, String sname, String mail){
	PreparedStatement ps;
	ResultSet rs;
	boolean MB_exist = false; 
	
	String query = "SELECT * FROM `user` WHERE `login`= ? AND `NN`= ? AND `password`= ? AND `U_F_Name`= ? AND `U_S_Name`= ? AND `Tel`= ?";
	
	try {
	    ps = Users_DB.getConection().prepareStatement(query);
	    ps.setString(1, login);
	    ps.setString(2, NN);
	    ps.setString(3, pas);
	    ps.setString(4, fname);
	    ps.setString(5, sname);
	    ps.setString(6, mail);
	    rs = ps.executeQuery();
	    if(rs.next()){
		MB_exist = true;
		JOptionPane.showMessageDialog(null,"Этот пользователь имеется!","Ошибка",2);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
	}
	return MB_exist;
    }
    
    public boolean checkLogin(String Login){
	
	PreparedStatement ps;
	ResultSet rs;
	boolean Login_exist = false; 
	
	String query = "SELECT * FROM `user` WHERE `login`= ?";
	
	try {
	    ps = Users_DB.getConection().prepareStatement(query);
	    ps.setString(1, Login);
	    rs = ps.executeQuery();
	    if(rs.next()){
		Login_exist = true;
		JOptionPane.showMessageDialog(null,"Этот Login используется другим пользователем!","Ошибка",0);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(MotherBoard.class.getName()).log(Level.SEVERE, null, ex);
	}
	return Login_exist;
    }
    
//    public boolean Upd_checkMB(String login, String NN, String pas, String fname, String sname, String mail){
//	PreparedStatement ps;
//	ResultSet rs;
//	boolean MB_exist = false; 
//	
//	String query = "SELECT * FROM `motherboard` WHERE `Maker` = ? AND `Form` = ? AND `Socket` = ? AND `chipset` = ? AND `RAM` = ? AND `TypeMemory` = ? AND `price`=?";
//	
//	try {
//	    ps = Parts_DB.getConection().prepareStatement(query);
//	    ps.setString(1, Maker);
//	    ps.setString(2, Form);
//	    ps.setString(3, Socket);
//	    ps.setString(4, Chipset);
//	    ps.setString(5, RAM);
//	    ps.setString(6, Type_RAM);
//	    ps.setString(7, Price);
//	    rs = ps.executeQuery();
//	    if(rs.next()){
//		MB_exist = true;
//		JOptionPane.showMessageDialog(null,"Этот товар имется!","Ошибка",2);
//	    }
//	} catch (SQLException ex) {
//	    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
//	}
//	return MB_exist;
//    }
    
    public boolean Login_Field(){
	
	String Login = jTextField_Login.getText();
	
	boolean Login_exist = false;
	
	if(Login.isEmpty()){
	    JOptionPane.showMessageDialog(null, "Заполните поле 'Login'!","O-oyу",2);
	    Login_exist = true;
	}
	return Login_exist;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton_Back = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton_Upd_Prof = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jTextField_F_Name = new javax.swing.JTextField();
        jTextField_NN = new javax.swing.JTextField();
        jTextField_Login = new javax.swing.JTextField();
        jTextField_S_Name = new javax.swing.JTextField();
        jTextField_ID = new javax.swing.JTextField();
        jTextField_Email = new javax.swing.JTextField();
        jButton_Delete_Prof = new javax.swing.JButton();
        jTextField_Pas = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(235, 149, 50));

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

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Учётные записи");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(34, 49, 63));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Пароль :");

        jButton_Back.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Back.setText("Назад");
        jButton_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BackActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Фамилия :");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Никнейм :");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Имя :");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Логин :");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Эл. почта :");

        jButton_Upd_Prof.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Upd_Prof.setText("Редактировать");
        jButton_Upd_Prof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Upd_ProfActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Логин", "Никнейм", "Пароль", "Имя", "Фамилия", "Эл. почта"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ID :");

        jTextField_F_Name.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_NN.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Login.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_S_Name.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_ID.setEditable(false);
        jTextField_ID.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_IDKeyTyped(evt);
            }
        });

        jTextField_Email.setBackground(new java.awt.Color(191, 191, 191));

        jButton_Delete_Prof.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Delete_Prof.setText("Удалить");
        jButton_Delete_Prof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Delete_ProfActionPerformed(evt);
            }
        });

        jTextField_Pas.setBackground(new java.awt.Color(191, 191, 191));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_F_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_S_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(127, 127, 127))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Pas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jTextField_NN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_Upd_Prof)
                        .addGap(161, 161, 161)
                        .addComponent(jButton_Delete_Prof)
                        .addGap(73, 73, 73))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_NN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Pas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_F_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField_S_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Upd_Prof, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Delete_Prof, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1147, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1147, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 676, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BackActionPerformed
        Main main = new Main();
        main.setVisible(true);
        main.pack();
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton_BackActionPerformed

    private void jButton_Upd_ProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Upd_ProfActionPerformed

        String Login = jTextField_Login.getText();
        String Email = jTextField_Email.getText();
        String NN = jTextField_NN.getText();
        String Pas = jTextField_Pas.getText();
        String F_Name = jTextField_F_Name.getText();
        String S_Name = jTextField_S_Name.getText();
        String ID = jTextField_ID.getText();

        if(checkFields(ID, Login, NN, Pas, F_Name, S_Name, Email)){
            
                try {
                    PreparedStatement ps;
                    ResultSet rs;

                    ps = Users_DB.getConection().prepareStatement(Upd_Us_Query);

                    ps.setString(1, Login);
		    ps.setString(2, NN);
                    ps.setString(3, Pas);
                    ps.setString(4, F_Name);
                    ps.setString(5, S_Name);
                    ps.setString(6, Email);
                    ps.setString(7, ID);
                    
                    ps.execute();
                    show_Users_In_Table();
                } catch (SQLException ex) {
                    Logger.getLogger(MotherBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
    }//GEN-LAST:event_jButton_Upd_ProfActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();
        Show_Item(index);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_IDKeyTyped
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_IDKeyTyped

    private void jButton_Delete_ProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Delete_ProfActionPerformed

        String ID = jTextField_ID.getText();

        PreparedStatement ps;
        ResultSet rs;

        if(!Login_Field()){
            try {
                ps = Users_DB.getConection().prepareStatement(Del_Us_Query);
                ps.setString(1, ID);
                ps.execute();
                show_Users_In_Table();
            } catch (SQLException ex) {
                Logger.getLogger(MotherBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_Delete_ProfActionPerformed

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
	    java.util.logging.Logger.getLogger(profiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(profiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(profiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(profiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new profiles().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Back;
    private javax.swing.JButton jButton_Delete_Prof;
    private javax.swing.JButton jButton_Upd_Prof;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_Email;
    private javax.swing.JTextField jTextField_F_Name;
    private javax.swing.JTextField jTextField_ID;
    private javax.swing.JTextField jTextField_Login;
    private javax.swing.JTextField jTextField_NN;
    private javax.swing.JTextField jTextField_Pas;
    private javax.swing.JTextField jTextField_S_Name;
    // End of variables declaration//GEN-END:variables
}
