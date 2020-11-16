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

public class SSD extends javax.swing.JFrame {

    
    public SSD() {
	initComponents();
	show_Parts_In_Table();
	
	Login log = new Login();
	
	if(log.chLogin==false){
	    jTextField_Maker.setEditable(false);
	    jTextField_Name.setEditable(false);
	    jTextField_Value.setEditable(false);
	    jTextField_Price.setEditable(false);
	    jTextField_ID.setEditable(false);
	    
	    jButton_Add_SSD.setVisible(false);
	    jButton_Delete_SSD.setVisible(false);
	    jButton_Upd_SSD.setVisible(false);
	}else{
	    jButton_Zakaz.setVisible(false);
	}
    }

    public static final String Add_SSD_Query = "INSERT INTO `ssd`(`ID`, `Maker`, `Name`, `Value`, `Price`) VALUES (?,?,?,?,?)";
    public static final String Upd_SSD_Query = "UPDATE `ssd` SET `Maker`=?,`Name`=?,`Value`=?,`Price`=? WHERE `ID`=?";
    public static final String Del_SSD_Query = "DELETE FROM `ssd` WHERE `ID` =?";
    
    public static String ZAK_SSD = "";
    public static int SSD_PRICE;
    
    public static String[] a = new String[5];
    
    public ArrayList <Parts_DB> get_Parts(){
    
	ArrayList <Parts_DB> Parts_List = new ArrayList <Parts_DB>();
	
	Statement ps;
	ResultSet rs;
	
	String MB_Query = "SELECT * FROM `ssd`"; 
	
	try {
	    ps = Parts_DB.getConection().createStatement();
	    rs = ps.executeQuery(MB_Query);
	    Parts_DB parts;
	    
	    while(rs.next()){
	    
		parts = new Parts_DB(rs.getInt("ID"),rs.getString("Maker"),rs.getString("Name"),rs.getString("Value"),rs.getInt("Price"));
		
		Parts_List.add(parts);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(SSD.class.getName()).log(Level.SEVERE, null, ex);
	}
	return Parts_List;
    }
    
    public void show_Parts_In_Table(){
    
	ArrayList <Parts_DB> list = get_Parts();
	DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
	
	model.setRowCount(0);
	Object[] row = new Object[5];
	for(int i = 0; i<list.size();i++){
	    row[0] = list.get(i).getID();
	    row[1] = list.get(i).getMaker();
	    row[2] = list.get(i).getName();
	    row[3] = list.get(i).getValue();
	    row[4] = list.get(i).getPrice();
	    
	    model.addRow(row);
	}
    }
    
    public void Show_Item(int index){
	
	jTextField_Maker.setText(get_Parts().get(index).getMaker());
	jTextField_Name.setText(get_Parts().get(index).getName());
        jTextField_Value.setText(get_Parts().get(index).getValue());
	jTextField_Price.setText(Integer.toString(get_Parts().get(index).getPrice()));
	jTextField_ID.setText(Integer.toString(get_Parts().get(index).getID()));
	
    }
    
    public boolean checkFields(String ID, String Maker,String Name,String Value,String Price){
	
	if(jTextField_ID.getText().trim().equals("")||jTextField_Maker.getText().trim().equals("")
	    ||jTextField_Name.getText().trim().equals("")||jTextField_Value.getText().equals("")
	    ||jTextField_Price.getText().trim().equals("")){
	    JOptionPane.showMessageDialog(null, "Заполните все поля!" ,"O-o-yу", 2);
	    return false;
	}else{
	    return true;
	}
    }
    
    public boolean checkSSD(String Maker,String Name,String Value){
	PreparedStatement ps;
	ResultSet rs;
	boolean MB_exist = false; 
	
	String query = "SELECT * FROM `ssd` WHERE `Maker`=? AND `Name`=? AND `Value`=?";
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, Maker);
	    ps.setString(2, Name);
	    ps.setString(3, Value);
	    rs = ps.executeQuery();
	    if(rs.next()){
		MB_exist = true;
		JOptionPane.showMessageDialog(null,"Этот товар имется!","Ошибка",2);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(SSD.class.getName()).log(Level.SEVERE, null, ex);
	}
	return MB_exist;
    }
    
    public boolean checkID_SSD(String ID){
	
	PreparedStatement ps;
	ResultSet rs;
	boolean MB_exist = false; 
	
	String query = "SELECT * FROM `ssd` WHERE `ID` = ?";
	
	try {
	    ps =Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, ID);
	    rs = ps.executeQuery();
	    if(rs.next()){
		MB_exist = true;
		JOptionPane.showMessageDialog(null,"Этот ID используется другим товаром!","Ошибка",0);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(SSD.class.getName()).log(Level.SEVERE, null, ex);
	}
	return MB_exist;
    }
    
    public boolean Upd_checkSSD(String Maker,String Name,String Value,String Price){
	PreparedStatement ps;
	ResultSet rs;
	boolean MB_exist = false; 
	
	String query = "SELECT * FROM `ssd` WHERE `Maker`=? AND `Name`=? AND `Value`=? AND `Price`=?";
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, Maker);
	    ps.setString(2, Name);
	    ps.setString(3, Value);
	    ps.setString(4, Price);
	    rs = ps.executeQuery();
	    if(rs.next()){
		MB_exist = true;
		JOptionPane.showMessageDialog(null,"Этот товар имется!","Ошибка",2);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(SSD.class.getName()).log(Level.SEVERE, null, ex);
	}
	return MB_exist;
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

        jButton_Zakaz = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton_Back = new javax.swing.JButton();
        jButton_Add_SSD = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField_Price = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton_Upd_SSD = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jTextField_Maker = new javax.swing.JTextField();
        jTextField_ID = new javax.swing.JTextField();
        jTextField_Name = new javax.swing.JTextField();
        jButton_Delete_SSD = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField_Value = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jButton_Zakaz.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Zakaz.setText("Заказать");
        jButton_Zakaz.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Zakaz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ZakazActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(34, 49, 63));
        jPanel6.setPreferredSize(new java.awt.Dimension(1200, 750));

        jButton_Back.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Back.setText("Назад");
        jButton_Back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BackActionPerformed(evt);
            }
        });

        jButton_Add_SSD.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Add_SSD.setText("Добавить");
        jButton_Add_SSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_SSDActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Цена :");

        jTextField_Price.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_Price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_PriceKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Производитель :");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Название :");

        jButton_Upd_SSD.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Upd_SSD.setText("Редактировать");
        jButton_Upd_SSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Upd_SSDActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Производитель", "Название", "Объём", "Цена"
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

        jTextField_Maker.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_ID.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_IDKeyTyped(evt);
            }
        });

        jTextField_Name.setBackground(new java.awt.Color(191, 191, 191));

        jButton_Delete_SSD.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Delete_SSD.setText("Удалить");
        jButton_Delete_SSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Delete_SSDActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Объём :");

        jTextField_Value.setBackground(new java.awt.Color(191, 191, 191));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Maker, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel14))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField_Value, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton_Upd_SSD)
                        .addGap(63, 63, 63)
                        .addComponent(jButton_Delete_SSD)
                        .addGap(66, 66, 66)
                        .addComponent(jButton_Add_SSD))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Maker, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Value, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Upd_SSD, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Add_SSD, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Delete_SSD, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(235, 149, 50));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel7.setText("Х");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel8.setText("_");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SSD");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addComponent(jButton_Zakaz)
                .addContainerGap(719, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(570, Short.MAX_VALUE)
                .addComponent(jButton_Zakaz, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jButton_Zakaz.getAccessibleContext().setAccessibleParent(jPanel6);

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

    private void jButton_Add_SSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add_SSDActionPerformed
        String Maker = jTextField_Maker.getText();
        String Name = jTextField_Name.getText();
        String Value = jTextField_Value.getText();
        String Price = jTextField_Price.getText();
        String ID = jTextField_ID.getText();

        if(checkFields(ID, Maker, Value, Price, Price)){
            if(!checkID_SSD(ID)){
                if(!checkSSD(Maker,Name, Value)){
                    try {
                        PreparedStatement ps;
                        ResultSet rs;

                        ps = Parts_DB.getConection().prepareStatement(Add_SSD_Query);

                        ps.setString(1, ID);
                        ps.setString(2, Maker);
                        ps.setString(3, Name);
                        ps.setString(4, Value);
                        ps.setString(5, Price);
                        ps.execute();
                        show_Parts_In_Table();
                    } catch (SQLException ex) {
                        Logger.getLogger(SSD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton_Add_SSDActionPerformed

    private void jTextField_PriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_PriceKeyTyped
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_PriceKeyTyped

    private void jButton_Upd_SSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Upd_SSDActionPerformed

        String Maker = jTextField_Maker.getText();
        String Name = jTextField_Name.getText();
        String Value = jTextField_Value.getText();
        String Price = jTextField_Price.getText();
        String ID = jTextField_ID.getText();

        if(checkFields(ID, Maker, Maker, Price, Price)){
            if(!Upd_checkSSD(Maker, Name, Value,Price)){
                try {
                    PreparedStatement ps;
                    ResultSet rs;

                    ps = Parts_DB.getConection().prepareStatement(Upd_SSD_Query);

                    ps.setString(1, Maker);
		    ps.setString(2, Name);
                    ps.setString(3, Value);
                    ps.setString(4, Price);
                    ps.setString(5, ID);
                    ps.execute();
                    show_Parts_In_Table();
                } catch (SQLException ex) {
                    Logger.getLogger(SSD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButton_Upd_SSDActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();
        Show_Item(index);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_IDKeyTyped
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_IDKeyTyped

    private void jButton_Delete_SSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Delete_SSDActionPerformed

        String ID = jTextField_ID.getText();

        PreparedStatement ps;
        ResultSet rs;

        if(!ID_Field()){
            try {
                ps = Parts_DB.getConection().prepareStatement(Del_SSD_Query);
                ps.setString(1, ID);
                ps.execute();
                show_Parts_In_Table();
            } catch (SQLException ex) {
                Logger.getLogger(SSD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_Delete_SSDActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jButton_ZakazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ZakazActionPerformed
	String ID = jTextField_ID.getText();
	String Maker = jTextField_Maker.getText();
        String Name = jTextField_Name.getText();
        String Value = jTextField_Value.getText();
        String Price = jTextField_Price.getText();
	
	if(checkFields(ID, Maker, Name, Value, Price)){
	    a[0]= Maker;
	    a[1]= Name;
	    a[2]= Value;
	    a[3]= Price;
	    a[4]= ID;
	    ZAK_SSD = ID;
	    SSD_PRICE = Integer.parseInt(a[3]);
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
	    java.util.logging.Logger.getLogger(SSD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(SSD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(SSD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(SSD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new SSD().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Add_SSD;
    private javax.swing.JButton jButton_Back;
    private javax.swing.JButton jButton_Delete_SSD;
    private javax.swing.JButton jButton_Upd_SSD;
    private javax.swing.JButton jButton_Zakaz;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_ID;
    private javax.swing.JTextField jTextField_Maker;
    private javax.swing.JTextField jTextField_Name;
    private javax.swing.JTextField jTextField_Price;
    private javax.swing.JTextField jTextField_Value;
    // End of variables declaration//GEN-END:variables
}
