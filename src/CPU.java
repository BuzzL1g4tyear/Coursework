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

public class CPU extends javax.swing.JFrame {

    
    public CPU() {
	initComponents();
	show_Parts_In_Table();
	
	Login log = new Login();
	
	if(log.chLogin==false){
	    jTextField_Maker.setEditable(false);
	    jTextField_Name.setEditable(false);
	    jTextField_Socket.setEditable(false);
	    jTextField_Cores.setEditable(false);
	    jTextField_Rate.setEditable(false);
	    jTextField_Type.setEditable(false);
	    jTextField_TDP.setEditable(false);
	    jTextField_Price.setEditable(false);
	    jTextField_ID.setEditable(false);
	    
	    jButton_Add_СPU.setVisible(false);
	    jButton_Del_CPU.setVisible(false);
	    jButton_Upd_CPU.setVisible(false);
	}else{
	    jButton_Zakaz.setVisible(false);
	}
	
    }

    public static final String Add_CPUQuery = "INSERT INTO `cpu`(`ID`, `Name`, `Maker`, `Cores`, `rate`, `Socket`, `Type`, `TDP`, `Price`) VALUES (?,?,?,?,?,?,?,?,?)";
    public static final String Upd_CPUQuery = "UPDATE `cpu` SET `Name`=?,`Maker`=?,`Cores`=?,`rate`=?,`Socket`=?,`Type`=?,`TDP`=?,`Price`=? WHERE `ID`=?";
    public static final String Del_CPUQuery = "DELETE FROM `cpu` WHERE `ID`= ?";
    
    public static String ZAK_CPU = "";
    
    public static String CPU_MB = "";
    public static int CPU_PRICE ;
    
    public static String[] a = new String[9];
    
    public ArrayList <Parts_DB> get_Parts(){
    
	ArrayList <Parts_DB> Parts_List = new ArrayList <Parts_DB>();
	
	Statement ps;
	ResultSet rs;
	
	String CPU_Query = "SELECT * FROM `cpu`"; 
	
	try {
	    ps = Parts_DB.getConection().createStatement();
	    rs = ps.executeQuery(CPU_Query);
	    Parts_DB parts;
	    
	    while(rs.next()){
	    
		parts = new Parts_DB(rs.getInt("ID"),rs.getString("Name"),rs.getString("Maker"),rs.getString("Cores"),rs.getString("Rate"),rs.getString("Socket"),rs.getString("Type"), rs.getString("TDP"),rs.getInt("price"));
		
		Parts_List.add(parts);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
	}
	return Parts_List;
    }
    
    public void show_Parts_In_Table(){
    
	ArrayList <Parts_DB> list = get_Parts();
	DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
	
	model.setRowCount(0);
	Object[] row = new Object[9];
	for(int i = 0; i<list.size();i++){
	    row[0] = list.get(i).getID();
	    row[1] = list.get(i).getName();
	    row[2] = list.get(i).getMaker();
	    row[3] = list.get(i).getCores();
	    row[4] = list.get(i).getRate();
	    row[5] = list.get(i).getSocket();
	    row[6] = list.get(i).getType();
	    row[7] = list.get(i).getTDP();
	    row[8] = list.get(i).getPrice();
	    
	    model.addRow(row);
	}
    }
    
    public void Show_Item(int index){
	jTextField_Name.setText(get_Parts().get(index).getName());
	jTextField_Maker.setText(get_Parts().get(index).getMaker());
	jTextField_Cores.setText(get_Parts().get(index).getCores());
	jTextField_Rate.setText(get_Parts().get(index).getRate());
        jTextField_Socket.setText(get_Parts().get(index).getSocket());
	jTextField_Type.setText(get_Parts().get(index).getType());
	jTextField_TDP.setText(get_Parts().get(index).getTDP());
	jTextField_Price.setText(Integer.toString(get_Parts().get(index).getPrice()));
	jTextField_ID.setText(Integer.toString(get_Parts().get(index).getID()));
	
    }
    
    public boolean verifyFields(){
	
	String Name = jTextField_Name.getText();
	String Maker = jTextField_Name.getText();
	String Cores = jTextField_Cores.getText();
	String Rate = jTextField_Rate.getText();
	String Socket = jTextField_Socket.getText();
	String Type = jTextField_Type.getText();
	String TDP = jTextField_TDP.getText();
	String ID = jTextField_ID.getText();
	String Price = jTextField_Price.getText();
	
	if(Name.trim().equals("") || Price.trim().equals("")
	   || Maker.trim().equals("") || Cores.trim().equals("")
	   || Rate.trim().equals("") || Socket.trim().equals("")
	   || Type.trim().equals("") || TDP.trim().equals("")
	   || ID.trim().equals("")){
	    JOptionPane.showMessageDialog(null,"Одно или более полей не заполнены!","O-o-yу",2);
	    return false;
	}else{
	    return true;
	}
    }
    
    public boolean checkCPU(String Name,String Maker, String Cores, String Rate, String Socket,String Type, String TDP, String Price){
	PreparedStatement ps;
	ResultSet rs;
	boolean MB_exist = false; 
	
	String query = "SELECT * FROM `cpu` WHERE `Name`= ? AND `Maker`= ? AND `Cores`= ? AND `rate`= ? AND `Socket`= ? AND `Type`= ? AND `TDP`= ? AND `Price`= ?";
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, Name);
	    ps.setString(2, Maker);
	    ps.setString(3, Cores);
	    ps.setString(4, Rate);
	    ps.setString(5, Socket);
	    ps.setString(6, Type);
	    ps.setString(7, TDP);
	    ps.setString(8, Price);
	    rs = ps.executeQuery();
	    if(rs.next()){
		MB_exist = true;
		JOptionPane.showMessageDialog(null,"Этот товар имется!","Ошибка",2);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
	}
	return MB_exist;
    }
    
    public boolean checkID_CPU(){
	
	String ID = jTextField_ID.getText();
	String query = "SELECT * FROM cpu WHERE ID =?";
	
	PreparedStatement ps;
	ResultSet rs;
	boolean CPU_exist = false;
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, ID);
	    rs = ps.executeQuery();
	    if(rs.next()){
		JOptionPane.showMessageDialog(null, "Этот ID используется другим товаром!","Ошибка",0);
		CPU_exist = true;
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
	}
	return CPU_exist;
    }
    
    public boolean Upd_checkCPU(String Name, String Maker, String Cores, String Rate, String Socket, String Type, String Price){
	PreparedStatement ps;
	ResultSet rs;
	boolean MB_exist = false; 
	
	String query = "SELECT * FROM `cpu` WHERE `Name`=? AND `Maker`=? AND `Cores`=? AND `rate`=? AND `Socket`=? AND `Type`=? AND `TDP`=? AND `Price`=?";
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, Name);
	    ps.setString(2, Maker);
	    ps.setString(3, Cores);
	    ps.setString(4, Rate);
	    ps.setString(5, Socket);
	    ps.setString(6, Type);
	    ps.setString(7, Price);
	    rs = ps.executeQuery();
	    if(rs.next()){
		MB_exist = true;
		JOptionPane.showMessageDialog(null,"Этот товар имется!","Ошибка",2);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
	}
	return MB_exist;
    }
    
    public boolean Compatibility(String Socket){
	
	MotherBoard MB = new MotherBoard();
	
	boolean Com_exist = false;
	String MB_Socket = MB.SOCKET;
	
	    if(MB_Socket.equals(Socket)){
		Com_exist=true;
		System.out.println("OK");
	    }else if(MB_Socket.equals("")){
		Com_exist=true;
	    }else{
		Com_exist = false;
		JOptionPane.showMessageDialog(null, "Выберите товар с "+MB_Socket+" cокетом!","Товар не совместим!",0);
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
        jButton_Add_СPU = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_Price = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField_Name = new javax.swing.JTextField();
        jTextField_Maker = new javax.swing.JTextField();
        jTextField_Socket = new javax.swing.JTextField();
        jTextField_Rate = new javax.swing.JTextField();
        jTextField_Cores = new javax.swing.JTextField();
        jTextField_Type = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jTextField_ID = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField_TDP = new javax.swing.JTextField();
        jButton_Upd_CPU = new javax.swing.JButton();
        jButton_Del_CPU = new javax.swing.JButton();
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
        jLabel1.setText("Процессор");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1035, Short.MAX_VALUE)
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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 80));

        jPanel2.setBackground(new java.awt.Color(34, 49, 63));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Частота :");

        jButton_Back.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Back.setText("Назад");
        jButton_Back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BackActionPerformed(evt);
            }
        });

        jButton_Add_СPU.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Add_СPU.setText("Добавить");
        jButton_Add_СPU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add_СPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_СPUActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Тип поставки :");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Сокет :");

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
        jLabel11.setText("Кол-во ядер :");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Производитель :");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Название :");

        jTextField_Name.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Maker.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Socket.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Rate.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Cores.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Type.setBackground(new java.awt.Color(191, 191, 191));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Название", "Производитель", "Кол-во ядер", "Частота", "Сокет", "Тип поставки", "TDP", "Цена"
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

        jTextField_ID.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_IDKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("TDP :");

        jTextField_TDP.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_TDP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_TDPKeyTyped(evt);
            }
        });

        jButton_Upd_CPU.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Upd_CPU.setText("Редактировать");
        jButton_Upd_CPU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Upd_CPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Upd_CPUActionPerformed(evt);
            }
        });

        jButton_Del_CPU.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Del_CPU.setText("Удалить");
        jButton_Del_CPU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Del_CPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Del_CPUActionPerformed(evt);
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
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField_Cores, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_Socket, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_Type, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField_Maker, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addGap(120, 120, 120)
                            .addComponent(jTextField_Name))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel15)
                                .addComponent(jLabel14))
                            .addGap(169, 169, 169)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField_Price)
                                    .addComponent(jTextField_TDP, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButton_Zakaz))))
                    .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_Del_CPU)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Upd_CPU)
                        .addGap(98, 98, 98)
                        .addComponent(jButton_Add_СPU)))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Maker, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Cores, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jTextField_Socket, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Type, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_TDP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addGap(72, 72, 72)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Add_СPU, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Upd_CPU, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Del_CPU, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Zakaz, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 78, 1210, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jButton_Add_СPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add_СPUActionPerformed

        String ID = jTextField_ID.getText();
	String Name = jTextField_Name.getText();
        String Maker = jTextField_Maker.getText();
        String Cores = jTextField_Cores.getText();
        String Rate = jTextField_Rate.getText();
        String Socket = jTextField_Socket.getText();
        String Type = jTextField_Type.getText();
        String TDP = jTextField_TDP.getText();
        String Price = jTextField_Price.getText();

        if(verifyFields()){
	    if(!checkCPU(Name, Maker, Cores, Rate, Socket, Type, TDP, Price)){
		if(!checkID_CPU()){
		    PreparedStatement ps;
		    ResultSet rs;

		    try {
			ps = Parts_DB.getConection().prepareStatement(Add_CPUQuery);
			ps.setString(1, ID);
			ps.setString(2, Name);
			ps.setString(3, Maker);
			ps.setString(4, Cores);
			ps.setString(5, Rate);
			ps.setString(6, Socket);
			ps.setString(7, Type);
			ps.setString(8, TDP);
			ps.setString(9, Price);
			ps.execute();
			show_Parts_In_Table();
		    } catch (SQLException ex) {
			Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
	    }
        }
    }//GEN-LAST:event_jButton_Add_СPUActionPerformed

    private void jButton_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BackActionPerformed
	Main main = new Main();
        main.setVisible(true);
        main.pack();
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton_BackActionPerformed

    private void jTextField_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_IDKeyTyped
       if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_IDKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();
	Show_Item(index);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField_TDPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_TDPKeyTyped
       if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_TDPKeyTyped

    private void jButton_Upd_CPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Upd_CPUActionPerformed
        
	String ID = jTextField_ID.getText();
	String Name = jTextField_Name.getText();
        String Maker = jTextField_Maker.getText();
        String Cores = jTextField_Cores.getText();
        String Rate = jTextField_Rate.getText();
        String Socket = jTextField_Socket.getText();
        String Type = jTextField_Type.getText();
        String TDP = jTextField_TDP.getText();
        String Price = jTextField_Price.getText();
	
	if(verifyFields()){
	    if(!checkCPU(Name, Maker, Cores, Rate, Socket, Type, TDP, Price)){
		PreparedStatement ps;
		ResultSet rs;

		try {
		    ps = Parts_DB.getConection().prepareStatement(Upd_CPUQuery);
		    ps.setString(1,Name);
		    ps.setString(2,Maker);
		    ps.setString(3,Cores);
		    ps.setString(4,Rate);
		    ps.setString(5,Socket);
		    ps.setString(6,Type);
		    ps.setString(7,TDP);
		    ps.setString(8,Price);
		    ps.setString(9,ID);
		    ps.execute();
		    show_Parts_In_Table();
		} catch (SQLException ex) {
		    Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
	}
    }//GEN-LAST:event_jButton_Upd_CPUActionPerformed

    private void jButton_Del_CPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Del_CPUActionPerformed
        
	String ID = jTextField_ID.getText();
	
	PreparedStatement ps;
	if(!ID_Field()){
	    try {
		ps = Parts_DB.getConection().prepareStatement(Del_CPUQuery);
		ps.setString(1, ID);
		ps.execute();
		show_Parts_In_Table();
	    } catch (SQLException ex) {
		Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }//GEN-LAST:event_jButton_Del_CPUActionPerformed

    private void jButton_ZakazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ZakazActionPerformed
       
    	String ID = jTextField_ID.getText();
	String Name = jTextField_Name.getText();
        String Maker = jTextField_Maker.getText();
        String Cores = jTextField_Cores.getText();
        String Rate = jTextField_Rate.getText();
        String Socket = jTextField_Socket.getText();
        String Type = jTextField_Type.getText();
        String TDP = jTextField_TDP.getText();
        String Price = jTextField_Price.getText();
	
	if(verifyFields()){
	    if(Compatibility(Socket)){
		ZAK_CPU = ID;
		CPU_MB = Socket;
		
		a[0]=Name;
		a[1]=Maker;
		a[2]=Cores;
		a[3]=Rate;
		a[4]=Socket;
		a[5]=Type;
		a[6]=TDP;
		a[7]=Price;
		a[8]=ID;
		CPU_PRICE = Integer.parseInt(a[7]);
	    }
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
	    java.util.logging.Logger.getLogger(CPU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(CPU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(CPU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(CPU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new CPU().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Add_СPU;
    private javax.swing.JButton jButton_Back;
    private javax.swing.JButton jButton_Del_CPU;
    private javax.swing.JButton jButton_Upd_CPU;
    private javax.swing.JButton jButton_Zakaz;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_Cores;
    private javax.swing.JTextField jTextField_ID;
    private javax.swing.JTextField jTextField_Maker;
    private javax.swing.JTextField jTextField_Name;
    private javax.swing.JTextField jTextField_Price;
    private javax.swing.JTextField jTextField_Rate;
    private javax.swing.JTextField jTextField_Socket;
    private javax.swing.JTextField jTextField_TDP;
    private javax.swing.JTextField jTextField_Type;
    // End of variables declaration//GEN-END:variables
}
