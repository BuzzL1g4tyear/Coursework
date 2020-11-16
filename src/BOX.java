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

public class BOX extends javax.swing.JFrame {

    
    public BOX() {
	initComponents();
	show_Parts_In_Table();
	
	Login log = new Login();
	
	if(log.chLogin==false){
	    jTextField_Maker.setEditable(false);
	    jTextField_Cooling.setEditable(false);
	    jTextField_Form_MB.setEditable(false);
	    jTextField_Type_BOX.setEditable(false);
	    jTextField_Price.setEditable(false);
	    jTextField_ID.setEditable(false);
	    
	    jButton_showMB.setVisible(false);
	    jButton_Del_BOX.setVisible(false);
	    jButton_Upd_BOX.setVisible(false);
	}else{
	    jButton_Zakaz.setVisible(false);
	}
    }

    public static final String Add_BOXQuery = "INSERT INTO `box`(`ID`, `Maker`, `Type_BOX`, `FormMB`, `cooling`, `price`) VALUES (?,?,?,?,?,?)";
    public static final String Upd_BOXQuery = "UPDATE `box` SET `Maker`=?,`Type_BOX`=?,`FormMB`=?,`cooling`=?,`price`=? WHERE `ID`=?";
    public static final String Del_BOXQuery = "DELETE FROM `box` WHERE `ID` = ?";
    
    public static String ZAK_BOX = "";
    
    public static String BOX_FORM = "";
    public static int BOX_PRICE;
    
    public static String[] a = new String[6];
    
    public ArrayList <Parts_DB> get_Parts(){
    
	ArrayList <Parts_DB> Parts_List = new ArrayList <Parts_DB>();
	
	Statement ps;
	ResultSet rs;
	
	String MB_Query = "SELECT * FROM `box`"; 
	
	try {
	    ps = Parts_DB.getConection().createStatement();
	    rs = ps.executeQuery(MB_Query);
	    Parts_DB parts;
	    
	    while(rs.next()){
	    
		parts = new Parts_DB(rs.getInt("ID"),rs.getString("Maker"),rs.getString("Type_BOX"),rs.getString("FormMB"),rs.getString("cooling"),rs.getInt("price"));
		
		Parts_List.add(parts);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(BOX.class.getName()).log(Level.SEVERE, null, ex);
	}
	return Parts_List;
    }
    
    public void show_Parts_In_Table(){
    
	ArrayList <Parts_DB> list = get_Parts();
	DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
	
	model.setRowCount(0);
	Object[] row = new Object[6];
	for(int i = 0; i<list.size();i++){
	    row[0] = list.get(i).getID();
	    row[1] = list.get(i).getMaker();
	    row[2] = list.get(i).getType_BOX();
	    row[3] = list.get(i).getForm();
	    row[4] = list.get(i).getCooling();
	    row[5] = list.get(i).getPrice();
	    
	    model.addRow(row);
	}
    }
    
    public void Show_Item(int index){
	
	jTextField_Maker.setText(get_Parts().get(index).getMaker());
	jTextField_Type_BOX.setText(get_Parts().get(index).getType_BOX());
        jTextField_Form_MB.setText(get_Parts().get(index).getForm());
	jTextField_Cooling.setText(get_Parts().get(index).getCooling());
	jTextField_Price.setText(Integer.toString(get_Parts().get(index).getPrice()));
	jTextField_ID.setText(Integer.toString(get_Parts().get(index).getID()));
	
    }
    
    public boolean checkFields(String ID,String Maker, String Type_BOX, String FormMB, String Cooling,String Price){
	
	if(jTextField_ID.getText().trim().equals("")||jTextField_Maker.getText().trim().equals("")
	    ||jTextField_Form_MB.getText().trim().equals("")||jTextField_Cooling.getText().equals("")
	    ||jTextField_Type_BOX.getText().trim().equals("")||jTextField_Price.getText().trim().equals("")){
	    JOptionPane.showMessageDialog(null, "Заполните все поля!" ,"O-o-yу", 2);
	    return false;
	}else{
	    return true;
	}
    }
    
    public boolean checkBOX(String Maker, String Type_BOX, String FormMB, String Cooling){
	PreparedStatement ps;
	ResultSet rs;
	boolean MB_exist = false; 
	
	String query = "SELECT * FROM `box` WHERE `Maker`= ? AND `Type_BOX`= ? AND `FormMB`= ? AND `cooling` = ?";
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, Maker);
	    ps.setString(2, Type_BOX);
	    ps.setString(3, FormMB);
	    ps.setString(4, Cooling);
	    rs = ps.executeQuery();
	    if(rs.next()){
		MB_exist = true;
		JOptionPane.showMessageDialog(null,"Этот товар имется!","Ошибка",2);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(BOX.class.getName()).log(Level.SEVERE, null, ex);
	}
	return MB_exist;
    }
    
    public boolean checkID_BOX(String ID){
	
	PreparedStatement ps;
	ResultSet rs;
	boolean BOX_exist = false; 
	
	String query = "SELECT * FROM `box` WHERE `ID` = ?";
	
	try {
	    ps =Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, ID);
	    rs = ps.executeQuery();
	    if(rs.next()){
		BOX_exist = true;
		JOptionPane.showMessageDialog(null,"Этот ID используется другим товаром!","Ошибка",0);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(BOX.class.getName()).log(Level.SEVERE, null, ex);
	}
	return BOX_exist;
    }
    
    public boolean Upd_checkBOX(String Maker, String Type_BOX, String FormMB, String Cooling,String Price){
	PreparedStatement ps;
	ResultSet rs;
	boolean BOX_exist = false; 
	
	String query = "SELECT * FROM `box` WHERE `Maker`= ? AND `Type_BOX`= ? AND `FormMB`= ? AND `cooling` = ? AND `price` = ?";
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, Maker);
	    ps.setString(2, Type_BOX);
	    ps.setString(3, FormMB);
	    ps.setString(4, Cooling);
	    ps.setString(5, Price);
	    rs = ps.executeQuery();
	    if(rs.next()){
		BOX_exist = true;
		JOptionPane.showMessageDialog(null,"Этот товар имется!","Ошибка",2);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(BOX.class.getName()).log(Level.SEVERE, null, ex);
	}
	return BOX_exist;
    }
    
    public boolean Compatibility(String FormMB){
	MotherBoard MB = new MotherBoard();
	
	boolean Com_exist = false;
	String MB_Form = MB.FORM;
	
	    if(MB_Form.equals(FormMB)){
		Com_exist=true;
		System.out.println("OK");
	    }else if(MB_Form.equals("")){
		Com_exist = true;
	    }else{
		Com_exist = false;
		JOptionPane.showMessageDialog(null, "Выберите товар с "+MB_Form+" формой!","Товар не совместим!",0);
	    }
	
	return Com_exist;
    }
    
    public boolean ID_Field(){
	
	String ID = jTextField_ID.getText();
	
	boolean ID_exist = false;
	
	if(ID.isEmpty()){
	    JOptionPane.showMessageDialog(null, "Заполните поле 'ID'!","O-oyу",2);
	    ID_exist = true;
	}
	return ID_exist;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton_Back = new javax.swing.JButton();
        jButton_showMB = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_Price = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField_ID = new javax.swing.JTextField();
        jTextField_Maker = new javax.swing.JTextField();
        jTextField_Type_BOX = new javax.swing.JTextField();
        jTextField_Form_MB = new javax.swing.JTextField();
        jTextField_Cooling = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton_Del_BOX = new javax.swing.JButton();
        jButton_Upd_BOX = new javax.swing.JButton();
        jButton_Zakaz = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(34, 49, 63));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Тип системного блока :");

        jButton_Back.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Back.setText("Назад");
        jButton_Back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BackActionPerformed(evt);
            }
        });

        jButton_showMB.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_showMB.setText("Добавить");
        jButton_showMB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_showMBActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Охлаждение :");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Цена :");

        jTextField_Price.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_Price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_PriceKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Форма материнской платы :");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Производитель :");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ID :");

        jTextField_ID.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_IDKeyTyped(evt);
            }
        });

        jTextField_Maker.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Type_BOX.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Form_MB.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Cooling.setBackground(new java.awt.Color(191, 191, 191));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Производитель", "Тип корпуса", "Форма материнской платы", "Охлаждение", "Цена"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton_Del_BOX.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Del_BOX.setText("Удалить");
        jButton_Del_BOX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Del_BOXActionPerformed(evt);
            }
        });

        jButton_Upd_BOX.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Upd_BOX.setText("Редактировать");
        jButton_Upd_BOX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Upd_BOXActionPerformed(evt);
            }
        });

        jButton_Zakaz.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Zakaz.setText("Заказать");
        jButton_Zakaz.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Zakaz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ZakazActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(120, 120, 120)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField_Maker, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_Type_BOX, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_Form_MB, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_Cooling, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton_Zakaz)
                                .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_Del_BOX)
                        .addGap(76, 76, 76)
                        .addComponent(jButton_Upd_BOX)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jButton_showMB))
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Maker, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Type_BOX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Form_MB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Cooling, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_showMB, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Del_BOX, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Upd_BOX, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Zakaz, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 78, 1180, 630));

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
        jLabel1.setText("Корпус");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1043, Short.MAX_VALUE)
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
                .addContainerGap(31, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BackActionPerformed
        Main main = new Main();
        main.setVisible(true);
        main.pack();
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton_BackActionPerformed

    private void jButton_showMBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_showMBActionPerformed

        String Maker = jTextField_Maker.getText();
        String Type_BOX = jTextField_Type_BOX.getText();
        String MB_Form = jTextField_Form_MB.getText();
        String Cooling = jTextField_Cooling.getText();
        String Price = jTextField_Price.getText();
        String ID = jTextField_ID.getText();

        if(checkFields(ID, Maker, Type_BOX, Price, Cooling, Price)){
	    if(!checkBOX(Maker, Type_BOX, Price, Cooling)){
		if(!checkID_BOX(ID)){
		    PreparedStatement ps;
		    ResultSet rs;

		    try {
			ps = Parts_DB.getConection().prepareStatement(Add_BOXQuery);
			ps.setString(1, ID);
			ps.setString(2, Maker);
			ps.setString(3, Type_BOX);
			ps.setString(4, MB_Form);
			ps.setString(5, Cooling);
			ps.setString(6, Price);
			ps.execute();
			show_Parts_In_Table();
		    } catch (SQLException ex) {
			Logger.getLogger(BOX.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
	    }
	}
    }//GEN-LAST:event_jButton_showMBActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jTextField_PriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_PriceKeyTyped
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_PriceKeyTyped

    private void jTextField_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_IDKeyTyped
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_IDKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();
	Show_Item(index);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton_Del_BOXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Del_BOXActionPerformed
        
	String ID = jTextField_ID.getText();
	    if(!ID_Field()){
		    PreparedStatement ps;
		    ResultSet rs;

		    try {
			ps = Parts_DB.getConection().prepareStatement(Del_BOXQuery);
			ps.setString(1, ID);
			ps.execute();
			show_Parts_In_Table();
		    } catch (SQLException ex) {
			Logger.getLogger(BOX.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
    }//GEN-LAST:event_jButton_Del_BOXActionPerformed

    private void jButton_Upd_BOXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Upd_BOXActionPerformed
        
	String Maker = jTextField_Maker.getText();
        String Type_BOX = jTextField_Type_BOX.getText();
        String MB_Form = jTextField_Form_MB.getText();
        String Cooling = jTextField_Cooling.getText();
        String Price = jTextField_Price.getText();
        String ID = jTextField_ID.getText();

        if(checkFields(ID, Maker, Type_BOX, Price, Cooling, Price)){
	    if(!Upd_checkBOX(Maker, Type_BOX, Price, Cooling, Price)){
		
		    PreparedStatement ps;
		    ResultSet rs;

		try {
			ps = Parts_DB.getConection().prepareStatement(Upd_BOXQuery);
			ps.setString(1, Maker);
			ps.setString(2, Type_BOX);
			ps.setString(3, MB_Form);
			ps.setString(4, Cooling);
			ps.setString(5, Price);
			ps.setString(6, ID);
			ps.execute();
			show_Parts_In_Table();
		    } catch (SQLException ex) {
			Logger.getLogger(BOX.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
	}
	
    }//GEN-LAST:event_jButton_Upd_BOXActionPerformed

    private void jButton_ZakazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ZakazActionPerformed
	String Maker = jTextField_Maker.getText();
        String Type_BOX = jTextField_Type_BOX.getText();
        String FormMB = jTextField_Form_MB.getText();
        String Cooling = jTextField_Cooling.getText();
        String Price = jTextField_Price.getText();
        String ID = jTextField_ID.getText();
	
	if(checkFields(ID, Maker, Type_BOX, FormMB, Cooling, Price)){
	    if(Compatibility(FormMB)){
		BOX_FORM = FormMB;
		ZAK_BOX = ID;
		
		a[0]= Maker;
		a[1]= Type_BOX;
		a[2]= FormMB;
		a[3]= Cooling;
		a[4]= Price;
		a[5]= ID;
		BOX_PRICE = Integer.parseInt(a[4]);
	    }
	    
	}
    }//GEN-LAST:event_jButton_ZakazActionPerformed

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
	    java.util.logging.Logger.getLogger(BOX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(BOX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(BOX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(BOX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new BOX().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Back;
    private javax.swing.JButton jButton_Del_BOX;
    private javax.swing.JButton jButton_Upd_BOX;
    private javax.swing.JButton jButton_Zakaz;
    private javax.swing.JButton jButton_showMB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_Cooling;
    private javax.swing.JTextField jTextField_Form_MB;
    private javax.swing.JTextField jTextField_ID;
    private javax.swing.JTextField jTextField_Maker;
    private javax.swing.JTextField jTextField_Price;
    private javax.swing.JTextField jTextField_Type_BOX;
    // End of variables declaration//GEN-END:variables
}
