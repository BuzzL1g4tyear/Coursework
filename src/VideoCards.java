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

public class VideoCards extends javax.swing.JFrame {

    
    public VideoCards() {
	initComponents();
	show_Parts_In_Table();
	
	Login log = new Login();
	
	if(log.chLogin==false){
	    jTextField_Maker.setEditable(false);
	    jTextField_Name.setEditable(false);
	    jTextField_Cooling.setEditable(false);
	    jTextField_Type_G_Mem.setEditable(false);
	    jTextField_Value_G_Mem.setEditable(false);
	    jTextField_Price.setEditable(false);
	    jTextField_ID.setEditable(false);
	    
	    jButton_Add_VC.setVisible(false);
	    jButton_Del_VC.setVisible(false);
	    jButton_Upd_VC.setVisible(false);
	}else{
	    jButton_Zakaz.setVisible(false);
	}
    }

    public static final String Add_VideoCardsQuery = "INSERT INTO `videocard`(`ID`, `Maker`, `Name`, `VMemory`, `TypeMemory`, `cooling`, `price`) VALUES (?,?,?,?,?,?,?)";
    public static final String Upd_VideoCardsQuery = "UPDATE `videocard` SET `Maker`= ?,`Name`= ?,`VMemory`= ?,`TypeMemory`= ?,`cooling`= ?,`price`= ? WHERE `ID`= ?";
    public static final String Del_VideoCardsQuery = "DELETE FROM `videocard` WHERE ID = ?";
    
    public static String ZAK_VC = "";
    public static int VC_PRICE;
    
    public static String[] a = new String[7];
    
    
    public ArrayList <Parts_DB> get_Parts(){
    
	ArrayList <Parts_DB> Parts_List = new ArrayList <Parts_DB>();
	
	Statement ps;
	ResultSet rs;
	
	String MB_Query = "SELECT * FROM `videocard`"; 
	
	try {
	    ps = Parts_DB.getConection().createStatement();
	    rs = ps.executeQuery(MB_Query);
	    Parts_DB parts;
	    
	    while(rs.next()){
	    
		parts = new Parts_DB(rs.getInt("ID"),rs.getString("Maker"),rs.getString("Name"),rs.getString("VMemory"),rs.getString("TypeMemory"),rs.getString("cooling"),rs.getInt("price"));
		
		Parts_List.add(parts);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(VideoCards.class.getName()).log(Level.SEVERE, null, ex);
	}
	return Parts_List;
    }
    
    public void show_Parts_In_Table(){
    
	ArrayList <Parts_DB> list = get_Parts();
	DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
	
	model.setRowCount(0);
	Object[] row = new Object[7];
	for(int i = 0; i<list.size();i++){
	    row[0] = list.get(i).getID();
	    row[1] = list.get(i).getMaker();
	    row[2] = list.get(i).getName();
	    row[3] = list.get(i).getVmem();
	    row[4] = list.get(i).getType_G_Mem();
	    row[5] = list.get(i).getCooling();
	    row[6] = list.get(i).getPrice();
	    
	    model.addRow(row);
	}
    }
    
    public void Show_Item(int index){
	jTextField_ID.setText(Integer.toString(get_Parts().get(index).getID()));
	jTextField_Maker.setText(get_Parts().get(index).getMaker());
        jTextField_Name.setText(get_Parts().get(index).getName());
	jTextField_Value_G_Mem.setText(get_Parts().get(index).getVmem());
	jTextField_Type_G_Mem.setText(get_Parts().get(index).getType_G_Mem());
	jTextField_Cooling.setText(get_Parts().get(index).getCooling());
	jTextField_Price.setText(Integer.toString(get_Parts().get(index).getPrice()));
	
    }
    
    public boolean checkFields(String ID,String Maker, String Name, String Value_G_Mem, String Type_G_Mem, String cooling, String Price){
	
	if(jTextField_ID.getText().trim().equals("")||jTextField_Maker.getText().trim().equals("")
	    ||jTextField_Name.getText().trim().equals("")||jTextField_Value_G_Mem.getText().equals("")
	    ||jTextField_Type_G_Mem.getText().trim().equals("")||jTextField_Cooling.getText().equals("")
	    ||jTextField_Price.getText().trim().equals("")){
	    JOptionPane.showMessageDialog(null, "Заполните все поля!" ,"O-o-yу", 2);
	    return false;
	}else{
	    return true;
	}
    }
    
    public boolean checkVC(String Maker, String Name, String Value_G_Mem, String Type_G_Mem, String Cooling,String Price){
	PreparedStatement ps;
	ResultSet rs;
	boolean VC_exist = false; 
	
	String query = "SELECT * FROM `videocard` WHERE `Maker`=? AND `Name`=? AND `VMemory`=? AND `TypeMemory`=? AND `cooling`=?";
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, Maker);
	    ps.setString(2, Name);
	    ps.setString(3, Value_G_Mem);
	    ps.setString(4, Type_G_Mem);
	    ps.setString(5, Cooling);
	    rs = ps.executeQuery();
	    if(rs.next()){
		VC_exist = true;
		JOptionPane.showMessageDialog(null,"Этот товар имется!","Ошибка",2);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(VideoCards.class.getName()).log(Level.SEVERE, null, ex);
	}
	return VC_exist;
    }
    
    public boolean checkID_VC(String ID){
	
	PreparedStatement ps;
	ResultSet rs;
	boolean VC_exist = false; 
	
	String query = "SELECT * FROM `videocard` WHERE `ID` = ?";
	
	try {
	    ps =Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, ID);
	    rs = ps.executeQuery();
	    if(rs.next()){
		VC_exist = true;
		JOptionPane.showMessageDialog(null,"Этот ID используется другим товаром!","Ошибка",0);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(VideoCards.class.getName()).log(Level.SEVERE, null, ex);
	}
	return VC_exist;
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
    
    public boolean Upd_checkVC(String Maker ,String Name, String Type_G_Mem, String Value_G_Mem, String Cooling,String Price){
	PreparedStatement ps;
	ResultSet rs;
	boolean VC_exist = false; 
	
	String query = "SELECT * FROM `videocard` WHERE `Maker` = ? AND`Name`= ? AND`VMemory`= ? AND`TypeMemory`= ? AND`cooling`= ? AND`price`= ?";
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, Maker);
	    ps.setString(2, Name);
	    ps.setString(3, Value_G_Mem);
	    ps.setString(4, Type_G_Mem);
	    ps.setString(5, Cooling);
	    ps.setString(6, Price);
	    rs = ps.executeQuery();
	    if(rs.next()){
		VC_exist = true;
		JOptionPane.showMessageDialog(null,"Этот товар имется!","Ошибка",2);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(VideoCards.class.getName()).log(Level.SEVERE, null, ex);
	}
	return VC_exist;
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
        jButton_Add_VC = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_Price = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField_Name = new javax.swing.JTextField();
        jTextField_Maker = new javax.swing.JTextField();
        jTextField_Type_G_Mem = new javax.swing.JTextField();
        jTextField_Cooling = new javax.swing.JTextField();
        jTextField_Value_G_Mem = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField_ID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton_Upd_VC = new javax.swing.JButton();
        jButton_Del_VC = new javax.swing.JButton();
        jButton_Zakaz = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jLabel1.setText("Видеокарты");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1049, Short.MAX_VALUE)
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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 80));

        jPanel2.setBackground(new java.awt.Color(34, 49, 63));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Тип графической памяти :");

        jButton_Back.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Back.setText("Назад");
        jButton_Back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BackActionPerformed(evt);
            }
        });

        jButton_Add_VC.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Add_VC.setText("Добавить");
        jButton_Add_VC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_VCActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Название :");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Тип охлаждения :");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Цена :");

        jTextField_Price.setBackground(new java.awt.Color(191, 191, 191));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Объём графической памяти :");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Производитель :");

        jTextField_Name.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Maker.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Type_G_Mem.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Cooling.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Value_G_Mem.setBackground(new java.awt.Color(191, 191, 191));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ID :");

        jTextField_ID.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_IDKeyTyped(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Производитель", "Название", "Объём графической памяти", "Тип графической памяти", "Тип охлаждения", "Цена"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton_Upd_VC.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Upd_VC.setText("Редактировать");
        jButton_Upd_VC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Upd_VCActionPerformed(evt);
            }
        });

        jButton_Del_VC.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Del_VC.setText("Удалить");
        jButton_Del_VC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Del_VCActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_Maker, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Cooling, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField_Type_G_Mem)
                                .addComponent(jTextField_Value_G_Mem, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(231, 231, 231)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_Zakaz)
                            .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_Del_VC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Upd_VC)
                        .addGap(76, 76, 76)
                        .addComponent(jButton_Add_VC))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Maker, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Type_G_Mem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Value_G_Mem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Cooling, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Add_VC, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Upd_VC, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Del_VC, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Zakaz, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 78, 1240, 620));

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

    private void jButton_Add_VCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add_VCActionPerformed

        String Maker = jTextField_Maker.getText();
        String Type_G_Mem = jTextField_Type_G_Mem.getText();
        String Value_G_Mem = jTextField_Value_G_Mem.getText();
        String Cooling = jTextField_Cooling.getText();
        String Name = jTextField_Name.getText();
        String Price = jTextField_Price.getText();
	String ID = jTextField_ID.getText();
	
	if(checkFields(ID, Maker, Name, Value_G_Mem, Type_G_Mem, Cooling, Price)){
	    if(!checkID_VC(ID)){
		if(!checkVC(Maker, Name,Value_G_Mem, Type_G_Mem,Cooling,Price)){
		    PreparedStatement ps;
		    ResultSet rs;

		    try {
			ps = Parts_DB.getConection().prepareStatement(Add_VideoCardsQuery);
			ps.setString(1, ID);
			ps.setString(2, Maker);
			ps.setString(3, Name);
			ps.setString(4, Value_G_Mem);
			ps.setString(5, Type_G_Mem);
			ps.setString(6, Cooling);
			ps.setString(7, Price);
			ps.execute();
			show_Parts_In_Table();
		    } catch (SQLException ex) {
			Logger.getLogger(VideoCards.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
	    }
	}
    }//GEN-LAST:event_jButton_Add_VCActionPerformed

    private void jTextField_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_IDKeyTyped
        if(!Character.isDigit(evt.getKeyChar())){
	    evt.consume();
	}
    }//GEN-LAST:event_jTextField_IDKeyTyped

    private void jButton_Upd_VCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Upd_VCActionPerformed
        
	String Maker = jTextField_Maker.getText();
        String Type_G_Mem = jTextField_Type_G_Mem.getText();
        String Value_G_Mem = jTextField_Value_G_Mem.getText();
        String Cooling = jTextField_Cooling.getText();
        String Name = jTextField_Name.getText();
        String Price = jTextField_Price.getText();
	String ID = jTextField_ID.getText();
	
	if(checkFields(ID, Maker, Name, Type_G_Mem,Value_G_Mem ,Cooling, Price)){
	    if(!Upd_checkVC(Maker, Name, Type_G_Mem,Value_G_Mem ,Cooling,Price)){
		    PreparedStatement ps;
		    ResultSet rs;

		    try {
			ps = Parts_DB.getConection().prepareStatement(Upd_VideoCardsQuery);
			ps.setString(1, Maker);
			ps.setString(2, Name);
			ps.setString(3, Value_G_Mem);
			ps.setString(4, Type_G_Mem);
			ps.setString(5, Cooling);
			ps.setString(6, Price);
			ps.setString(7, ID);
			ps.execute();
			show_Parts_In_Table();
		    } catch (SQLException ex) {
			Logger.getLogger(VideoCards.class.getName()).log(Level.SEVERE, null, ex);
		    }
		
	    }
	}
    }//GEN-LAST:event_jButton_Upd_VCActionPerformed

    private void jButton_Del_VCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Del_VCActionPerformed
       String ID = jTextField_ID.getText();
	
	if(!ID_Field()){
	    PreparedStatement ps;
	    ResultSet rs;
	
	    try {
		ps = Parts_DB.getConection().prepareStatement(Del_VideoCardsQuery);
		ps.setString(1, ID);
		ps.execute();
		show_Parts_In_Table();
	    } catch (SQLException ex) {
		Logger.getLogger(VideoCards.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }//GEN-LAST:event_jButton_Del_VCActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();
	Show_Item(index);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton_ZakazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ZakazActionPerformed
	
	String Maker = jTextField_Maker.getText();
        String Type_G_Mem = jTextField_Type_G_Mem.getText();
        String Value_G_Mem = jTextField_Value_G_Mem.getText();
        String Cooling = jTextField_Cooling.getText();
        String Name = jTextField_Name.getText();
        String Price = jTextField_Price.getText();
	String ID = jTextField_ID.getText();
	
	if(checkFields(ID, Maker, Name, Value_G_Mem, Type_G_Mem, Cooling, Price)){
	    ZAK_VC = ID;
	    a[0]=Maker;
	    a[1]=Name;
	    a[2]=Value_G_Mem;
	    a[3]=Type_G_Mem;
	    a[4]=Cooling;
	    a[5]=Price;
	    a[6]=ID;
	    VC_PRICE=Integer.parseInt(a[5]);
	}
    }//GEN-LAST:event_jButton_ZakazActionPerformed

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
	    java.util.logging.Logger.getLogger(VideoCards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(VideoCards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(VideoCards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(VideoCards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new VideoCards().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Add_VC;
    private javax.swing.JButton jButton_Back;
    private javax.swing.JButton jButton_Del_VC;
    private javax.swing.JButton jButton_Upd_VC;
    private javax.swing.JButton jButton_Zakaz;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_Cooling;
    private javax.swing.JTextField jTextField_ID;
    private javax.swing.JTextField jTextField_Maker;
    private javax.swing.JTextField jTextField_Name;
    private javax.swing.JTextField jTextField_Price;
    private javax.swing.JTextField jTextField_Type_G_Mem;
    private javax.swing.JTextField jTextField_Value_G_Mem;
    // End of variables declaration//GEN-END:variables
}
