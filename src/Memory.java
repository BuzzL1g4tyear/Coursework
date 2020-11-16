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

public class Memory extends javax.swing.JFrame {

    
    public Memory() {
	initComponents();
	show_Parts_In_Table();
	
	Login log = new Login();
	
	if(log.chLogin==false){
	    jTextField_Maker.setEditable(false);
	    jTextField_Name.setEditable(false);
	    jTextField_Rate.setEditable(false);
	    jTextField_Timings.setEditable(false);
	    jTextField_Volts.setEditable(false);
	    jTextField_Type_RAM.setEditable(false);
	    jTextField_Value_RAM.setEditable(false);
	    jTextField_Type_RAM.setEditable(false);
	    jTextField_Set.setEditable(false);
	    jTextField_Price.setEditable(false);
	    jTextField_ID.setEditable(false);
	    
	    jButton_showMB.setVisible(false);
	    jButton_Del_Mem.setVisible(false);
	    jButton_Upd_Mem.setVisible(false);
	}else{
	    jButton_Zakaz.setVisible(false);
	}
    }

    public static final String Add_MemQuery = "INSERT INTO `memory`(`ID`, `Maker`, `Name`, `TypeMemory`, `timings`, `rate`, `Volume`, `Seet`, `Volts`, `price`) VALUES (?,?,?,?,?,?,?,?,?,?)";
    public static final String Upd_MemQuery = "UPDATE `memory` SET `Maker`=?,`Name`=?,`TypeMemory`=?,`timings`=?,`rate`=?,`Volume`=?,`Seet`=?,`Volts`=?,`price`=? WHERE `ID`=?";
    public static final String Del_MemQuery = "DELETE FROM `memory` WHERE `ID`=?";
    
    public static String ZAK_MEM = "";
    
    public static int MEM_MB ;
    public static int MEM_PRICE;
    
    public static String[] a = new String[10];
    
    public ArrayList <Parts_DB> get_Parts(){
    
	ArrayList <Parts_DB> Parts_List = new ArrayList <Parts_DB>();
	
	Statement ps;
	ResultSet rs;
	
	String MB_Query = "SELECT * FROM `memory`"; 
	
	try {
	    ps = Parts_DB.getConection().createStatement();
	    rs = ps.executeQuery(MB_Query);
	    Parts_DB parts;
	    
	    while(rs.next()){
	    
		parts = new Parts_DB(rs.getInt("ID"),rs.getString("Maker"),rs.getString("Name"),rs.getString("TypeMemory"),
				    rs.getString("timings"),rs.getString("rate"),rs.getString("Volume"),rs.getString("Seet"),
				    rs.getString("Volts"),rs.getInt("price"));
		
		Parts_List.add(parts);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(Memory.class.getName()).log(Level.SEVERE, null, ex);
	}
	return Parts_List;
    }
    
    public void show_Parts_In_Table(){
    
	ArrayList <Parts_DB> list = get_Parts();
	DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
	
	model.setRowCount(0);
	Object[] row = new Object[10];
	for(int i = 0; i<list.size();i++){
	    row[0] = list.get(i).getID();
	    row[1] = list.get(i).getMaker();
	    row[2] = list.get(i).getName();
	    row[3] = list.get(i).getType_RAM();
	    row[4] = list.get(i).getTimings();
	    row[5] = list.get(i).getRate();
	    row[6] = list.get(i).getValue();
	    row[7] = list.get(i).getSet();
	    row[8] = list.get(i).getVolt();
	    row[9] = list.get(i).getPrice();
	    
	    model.addRow(row);
	}
    }
    
    public void Show_Item(int index){
	
	jTextField_Maker.setText(get_Parts().get(index).getMaker());
	jTextField_Name.setText(get_Parts().get(index).getName());
        jTextField_Timings.setText(get_Parts().get(index).getTimings());
	jTextField_Rate.setText(get_Parts().get(index).getRate());
	jTextField_Value_RAM.setText(get_Parts().get(index).getValue());
	jTextField_Set.setText(get_Parts().get(index).getSet());
	jTextField_Volts.setText(get_Parts().get(index).getVolt());
	jTextField_Type_RAM.setText(get_Parts().get(index).getType_RAM());
	jTextField_Price.setText(Integer.toString(get_Parts().get(index).getPrice()));
	jTextField_ID.setText(Integer.toString(get_Parts().get(index).getID()));
	
    }
    
    public boolean checkFields(String ID,String Maker,String Name,String Rate,String Timings,String Set,String Value_RAM,String Volts,String Type_RAM,String Price){
	
	if(jTextField_ID.getText().trim().equals("")||jTextField_Maker.getText().trim().equals("")
	    ||jTextField_Rate.getText().trim().equals("")||jTextField_Timings.getText().equals("")
	    ||jTextField_Set.getText().isEmpty()||jTextField_Value_RAM.getText().isEmpty()
	    ||jTextField_Volts.getText().trim().equals("")||jTextField_Type_RAM.getText().trim().equals("")
	    ||jTextField_Price.getText().trim().equals("")||jTextField_Name.getText().isEmpty()){
	    JOptionPane.showMessageDialog(null, "Заполните все поля!" ,"O-o-yу", 2);
	    return false;
	}else{
	    return true;
	}
    }
    
    public boolean checkMem(String Maker,String Name,String Rate,String Timings,String Set,String Value_RAM,String Volts,String Type_RAM){
	PreparedStatement ps;
	ResultSet rs;
	boolean MB_exist = false; 
	
	String query = "SELECT * FROM `memory` WHERE `Maker`= ? AND`Name`= ? AND`TypeMemory`= ? AND`timings`= ? AND`rate`= ? AND`Volume`= ? AND`Seet`= ? AND`Volts`= ?";
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, Maker);
	    ps.setString(2, Name);
	    ps.setString(3, Rate);
	    ps.setString(4, Timings);
	    ps.setString(5, Set);
	    ps.setString(6, Value_RAM);
	    ps.setString(7, Volts);
	    ps.setString(8, Type_RAM);
	    rs = ps.executeQuery();
	    if(rs.next()){
		MB_exist = true;
		JOptionPane.showMessageDialog(null,"Этот товар имется!","Ошибка",2);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(Memory.class.getName()).log(Level.SEVERE, null, ex);
	}
	return MB_exist;
    }
    
    public boolean checkID_Mem(String ID){
	
	PreparedStatement ps;
	ResultSet rs;
	boolean MB_exist = false; 
	
	String query = "SELECT * FROM `memory` WHERE `ID` = ?";
	
	try {
	    ps =Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, ID);
	    rs = ps.executeQuery();
	    if(rs.next()){
		MB_exist = true;
		JOptionPane.showMessageDialog(null,"Этот ID используется другим товаром!","Ошибка",0);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(Memory.class.getName()).log(Level.SEVERE, null, ex);
	}
	return MB_exist;
    }
    
    public boolean Upd_checkMem(String Maker,String Name,String Rate,String Timings,String Set,String Value_RAM,String Volts,String Type_RAM,String Price){
	PreparedStatement ps;
	ResultSet rs;
	boolean MB_exist = false; 
	
	String query = "SELECT * FROM `memory` WHERE `Maker`= ? AND`Name`= ? AND`TypeMemory`= ? AND`timings`= ? AND`rate`= ? AND`Volume`= ? AND`Seet`= ? AND`Volts`= ? AND `price` = ?";
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, Maker);
	    ps.setString(2, Name);
	    ps.setString(3, Rate);
	    ps.setString(4, Timings);
	    ps.setString(5, Set);
	    ps.setString(6, Value_RAM);
	    ps.setString(7, Volts);
	    ps.setString(8, Type_RAM);
	    ps.setString(9, Price);
	    rs = ps.executeQuery();
	    if(rs.next()){
		MB_exist = true;
		JOptionPane.showMessageDialog(null,"Этот товар имется!","Ошибка",2);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(Memory.class.getName()).log(Level.SEVERE, null, ex);
	}
	return MB_exist;
    }
    
    public boolean Compatibility(int Set){
	
	MotherBoard MB = new MotherBoard();
	
	boolean Com_exist = false;
	int MB_RAM = MB.RAMM;
	
	    if(MB_RAM >= Set){
		Com_exist=true;
		System.out.println("OK");
	    }else if(MB_RAM ==0){
		Com_exist=true;
	    }else{
		Com_exist = false;
		JOptionPane.showMessageDialog(null, "Выберите товар с "+MB_RAM+" количеством плашек ОЗУ!","Товар не совместим!",0);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton_Back = new javax.swing.JButton();
        jButton_showMB = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_Price = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField_Type_RAM = new javax.swing.JTextField();
        jTextField_Rate = new javax.swing.JTextField();
        jTextField_Value_RAM = new javax.swing.JTextField();
        jTextField_Maker = new javax.swing.JTextField();
        jTextField_Set = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_Name = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField_Timings = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField_Volts = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField_ID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton_Del_Mem = new javax.swing.JButton();
        jButton_Upd_Mem = new javax.swing.JButton();
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
        jLabel1.setText("Оперативная память");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1070, Short.MAX_VALUE)
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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 80));

        jPanel2.setBackground(new java.awt.Color(34, 49, 63));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Тип оперативной памяти :");

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
        jLabel9.setText("Кол-во плашек в комплекте :");

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
        jLabel11.setText("Объём оперативной памяти :");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Производитель :");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Частота :");

        jTextField_Type_RAM.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Rate.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_Rate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_RateKeyTyped(evt);
            }
        });

        jTextField_Value_RAM.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_Value_RAM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_Value_RAMKeyTyped(evt);
            }
        });

        jTextField_Maker.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Set.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_Set.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_SetKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Название :");

        jTextField_Name.setBackground(new java.awt.Color(191, 191, 191));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Тайминги :");

        jTextField_Timings.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_Timings.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_TimingsKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Вольтаж :");

        jTextField_Volts.setBackground(new java.awt.Color(191, 191, 191));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("ID :");

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
                "ID", "Производитель", "Название", "Тип ОЗУ", "Тайминги", "Частота", "Объём ОЗУ", "Кол-во плашек ОЗУ", "Вольтаж", "Цена"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton_Del_Mem.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Del_Mem.setText("Удалить");
        jButton_Del_Mem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Del_MemActionPerformed(evt);
            }
        });

        jButton_Upd_Mem.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Upd_Mem.setText("Редактировать");
        jButton_Upd_Mem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Upd_MemActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(32, 32, 32)
                        .addComponent(jTextField_Set, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(35, 35, 35)
                        .addComponent(jTextField_Value_RAM, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(353, 353, 353)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_Timings, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Type_RAM, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118)
                        .addComponent(jTextField_Maker, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel16)
                            .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(222, 222, 222)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_Zakaz)
                            .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Volts, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_Del_Mem)
                        .addGap(116, 116, 116)
                        .addComponent(jButton_Upd_Mem)
                        .addGap(85, 85, 85)
                        .addComponent(jButton_showMB))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Maker, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Type_RAM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Timings, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Value_RAM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Set, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Volts, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(93, 93, 93)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_showMB, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Del_Mem, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Upd_Mem, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Zakaz, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1350, 710));

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

    private void jButton_showMBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_showMBActionPerformed

        String Maker = jTextField_Maker.getText();
        String Name = jTextField_Name.getText();
        String Type_RAM = jTextField_Type_RAM.getText();
        String Value_RAM = jTextField_Value_RAM.getText();
        String Rate = jTextField_Rate.getText();
        String Set = jTextField_Set.getText();
        String Timings = jTextField_Timings.getText();
        String Price = jTextField_Price.getText();
        String Volts = jTextField_Volts.getText();
        String ID = jTextField_ID.getText();
	
	if(checkFields(ID, Maker, Name, Rate, Timings, Set, Value_RAM, Volts, Type_RAM, Price)){
	    if(!checkID_Mem(ID)){
		if(!checkMem(Maker, Name, Rate, Timings, Set, Value_RAM, Volts, Type_RAM)){
		    PreparedStatement ps;
		    ResultSet rs;

		    try {
			ps = Parts_DB.getConection().prepareStatement(Add_MemQuery);
			ps.setString(1, ID);
			ps.setString(2, Maker);
			ps.setString(3, Name);
			ps.setString(4, Type_RAM);
			ps.setString(5, Timings);
			ps.setString(6, Rate);
			ps.setString(7, Value_RAM);
			ps.setString(8, Set);
			ps.setString(9, Volts);
			ps.setString(10, Price);
			ps.execute();
			show_Parts_In_Table();
		    } catch (SQLException ex) {
			Logger.getLogger(Memory.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
	    }
	}
    }//GEN-LAST:event_jButton_showMBActionPerformed

    private void jTextField_PriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_PriceKeyTyped
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_PriceKeyTyped

    private void jTextField_RateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_RateKeyTyped
        if(!Character.isDigit(evt.getKeyChar())){
	    evt.consume();
	}
    }//GEN-LAST:event_jTextField_RateKeyTyped

    private void jTextField_Value_RAMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Value_RAMKeyTyped
        if(!Character.isDigit(evt.getKeyChar())){
	    evt.consume();
	}
    }//GEN-LAST:event_jTextField_Value_RAMKeyTyped

    private void jTextField_SetKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_SetKeyTyped
        if(!Character.isDigit(evt.getKeyChar())){
	    evt.consume();
	}
    }//GEN-LAST:event_jTextField_SetKeyTyped

    private void jTextField_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_IDKeyTyped
        if(!Character.isDigit(evt.getKeyChar())){
	    evt.consume();
	}
    }//GEN-LAST:event_jTextField_IDKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();
	Show_Item(index);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField_TimingsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_TimingsKeyTyped
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_TimingsKeyTyped

    private void jButton_Del_MemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Del_MemActionPerformed
        String ID = jTextField_ID.getText();
	
	if(!ID_Field()){
	
	    PreparedStatement ps;
	    ResultSet rs;
	    
	    try {
		ps = Parts_DB.getConection().prepareStatement(Del_MemQuery);
		ps.setString(1,ID);
		ps.execute();
		show_Parts_In_Table();
	    } catch (SQLException ex) {
		Logger.getLogger(Memory.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }//GEN-LAST:event_jButton_Del_MemActionPerformed

    private void jButton_Upd_MemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Upd_MemActionPerformed
        String Maker = jTextField_Maker.getText();
        String Name = jTextField_Name.getText();
        String Type_RAM = jTextField_Type_RAM.getText();
        String Value_RAM = jTextField_Value_RAM.getText();
        String Rate = jTextField_Rate.getText();
        String Set = jTextField_Set.getText();
        String Timings = jTextField_Timings.getText();
        String Price = jTextField_Price.getText();
        String Volts = jTextField_Volts.getText();
        String ID = jTextField_ID.getText();
	
	if(checkFields(ID, Maker, Name, Rate, Timings, Set, Value_RAM, Volts, Type_RAM, Price)){
	    if(!Upd_checkMem(Maker, Name, Rate, Timings, Set, Value_RAM, Volts, Type_RAM, Price)){
		PreparedStatement ps;
		ResultSet rs;

		try {
		    ps = Parts_DB.getConection().prepareStatement(Upd_MemQuery);
		    ps.setString(1, Maker);
		    ps.setString(2, Name);
		    ps.setString(3, Type_RAM);
		    ps.setString(4, Timings);
		    ps.setString(5, Rate);
		    ps.setString(6, Value_RAM);
		    ps.setString(7, Set);
		    ps.setString(8, Volts);
		    ps.setString(9, Price);
		    ps.setString(10, ID);
		    ps.execute();
		    show_Parts_In_Table();
		} catch (SQLException ex) {
		    Logger.getLogger(Memory.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
	}
    }//GEN-LAST:event_jButton_Upd_MemActionPerformed

    private void jButton_ZakazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ZakazActionPerformed
	
	String Maker = jTextField_Maker.getText();
        String Name = jTextField_Name.getText();
        String Type_RAM = jTextField_Type_RAM.getText();
        String Value_RAM = jTextField_Value_RAM.getText();
        String Rate = jTextField_Rate.getText();
        String set = jTextField_Set.getText();
        String Timings = jTextField_Timings.getText();
        String Price = jTextField_Price.getText();
        String Volts = jTextField_Volts.getText();
        String ID = jTextField_ID.getText();
	int Set = Integer.parseInt(set);
	
	if(checkFields(ID, Maker, Name, Rate, Timings, set, Value_RAM, Volts, Type_RAM, Price)){
	    if(Compatibility(Set)){
		MEM_MB = Set; 
		
		a[0]=Maker;
		a[1]=Name;
		a[2]=Rate;
		a[3]=Timings;
		a[4]=set;
		a[5]=Value_RAM;
		a[6]=Volts;
		a[7]=Type_RAM;
		a[8]=Price;
		a[9]=ID;
		
		ZAK_MEM = ID;
	    }
	    MEM_PRICE = Integer.parseInt(a[8]);
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
	    java.util.logging.Logger.getLogger(Memory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(Memory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(Memory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(Memory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new Memory().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Back;
    private javax.swing.JButton jButton_Del_Mem;
    private javax.swing.JButton jButton_Upd_Mem;
    private javax.swing.JButton jButton_Zakaz;
    private javax.swing.JButton jButton_showMB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_ID;
    private javax.swing.JTextField jTextField_Maker;
    private javax.swing.JTextField jTextField_Name;
    private javax.swing.JTextField jTextField_Price;
    private javax.swing.JTextField jTextField_Rate;
    private javax.swing.JTextField jTextField_Set;
    private javax.swing.JTextField jTextField_Timings;
    private javax.swing.JTextField jTextField_Type_RAM;
    private javax.swing.JTextField jTextField_Value_RAM;
    private javax.swing.JTextField jTextField_Volts;
    // End of variables declaration//GEN-END:variables
}
