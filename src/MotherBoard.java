import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class MotherBoard extends javax.swing.JFrame {

    public MotherBoard() {
	initComponents();
	Login log = new Login();
	
	if(log.chLogin==false){
	    jTextField_Maker.setEditable(false);
	    jTextField_Form.setEditable(false);
	    jTextField_Socket.setEditable(false);
	    jTextField_Chipset.setEditable(false);
	    jTextField_RAM.setEditable(false);
	    jTextField_Type_RAM.setEditable(false);
	    jTextField_Price.setEditable(false);
	    jTextField_ID.setEditable(false);
	    
	    jButton_AddMB.setVisible(false);
	    jButton_DeleteMB.setVisible(false);
	    jButton_UpdMB.setVisible(false);
	}else{
	    jButton_Zakaz.setVisible(false);
	}
	show_Parts_In_Table();
    }
    
    public static final String Add_MB_Query = "INSERT INTO `motherboard`(`ID`,`Maker`, `Form`, `Socket`, `chipset`, `RAM`, `TypeMemory`, `price`) VALUES (?,?,?,?,?,?,?,?)";
    public static final String Upd_MB_Query = "UPDATE `motherboard` SET `Maker`=?,`Form`=?,`Socket`=?,`chipset`=?,`RAM`=?,`TypeMemory`=?,`price`=? WHERE ID = ?";
    public static final String Del_MB_Query = "DELETE FROM `motherboard` WHERE `ID` = ?";
    public static String SOCKET = "";
    public static String FORM = "";
    public static int RAMM;
    public static int MB_PRICE;
    
    public static String ZAK_MB = "";
    
    public static String[] a = new String[8];
    
    public ArrayList <Parts_DB> get_Parts(){
    
	ArrayList <Parts_DB> Parts_List = new ArrayList <Parts_DB>();
	
	Statement ps;
	ResultSet rs;
	
	String MB_Query = "SELECT * FROM `motherboard`"; 
	
	try {
	    ps = Parts_DB.getConection().createStatement();
	    rs = ps.executeQuery(MB_Query);
	    Parts_DB parts;
	    
	    while(rs.next()){
	    
		parts = new Parts_DB(rs.getInt("ID"),rs.getString("Maker"),rs.getString("Form"),rs.getString("Socket"),rs.getString("chipset"),rs.getString("RAM"),rs.getString("TypeMemory"),rs.getInt("price"));
		
		Parts_List.add(parts);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(MotherBoard.class.getName()).log(Level.SEVERE, null, ex);
	}
	return Parts_List;
    }
    
    public void show_Parts_In_Table(){
    
	ArrayList <Parts_DB> list = get_Parts();
	DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
	
	model.setRowCount(0);
	Object[] row = new Object[8];
	for(int i = 0; i<list.size();i++){
	    row[0] = list.get(i).getID();
	    row[1] = list.get(i).getMaker();
	    row[2] = list.get(i).getForm();
	    row[3] = list.get(i).getSocket();
	    row[4] = list.get(i).getChipset();
	    row[5] = list.get(i).getRAM();
	    row[6] = list.get(i).getType_RAM();
	    row[7] = list.get(i).getPrice();
	    
	    model.addRow(row);
	}
    }
    
    public void Show_Item(int index){
	
	jTextField_Maker.setText(get_Parts().get(index).getMaker());
	jTextField_Form.setText(get_Parts().get(index).getForm());
        jTextField_Socket.setText(get_Parts().get(index).getSocket());
	jTextField_Chipset.setText(get_Parts().get(index).getChipset());
	jTextField_RAM.setText(get_Parts().get(index).getRAM());
	jTextField_Type_RAM.setText(get_Parts().get(index).getType_RAM());
	jTextField_Price.setText(Integer.toString(get_Parts().get(index).getPrice()));
	jTextField_ID.setText(Integer.toString(get_Parts().get(index).getID()));
	
    }
    
    public boolean checkFields(String ID,String Maker, String Form, String Socket, String Chipset, String RAM, String Type_RAM, String Price){
	
	if(jTextField_ID.getText().trim().equals("")||jTextField_Maker.getText().trim().equals("")
	    ||jTextField_Form.getText().trim().equals("")||jTextField_Socket.getText().equals("")
	    ||jTextField_Chipset.getText().trim().equals("")||jTextField_RAM.getText().equals("")
	    ||jTextField_Type_RAM.getText().trim().equals("")||jTextField_Price.getText().trim().equals("")){
	    JOptionPane.showMessageDialog(null, "Заполните все поля!" ,"O-o-yу", 2);
	    return false;
	}else{
	    return true;
	}
    }
    
    public boolean checkMB(String Maker, String Form, String Socket, String Chipset, String RAM, String Type_RAM, String Price){
	PreparedStatement ps;
	ResultSet rs;
	boolean MB_exist = false; 
	
	String query = "SELECT * FROM `motherboard` WHERE `Maker` = ? AND `Form` = ? AND `Socket` = ? AND `chipset` = ? AND `RAM` = ? AND `TypeMemory` = ?";
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, Maker);
	    ps.setString(2, Form);
	    ps.setString(3, Socket);
	    ps.setString(4, Chipset);
	    ps.setString(5, RAM);
	    ps.setString(6, Type_RAM);
	    rs = ps.executeQuery();
	    if(rs.next()){
		MB_exist = true;
		JOptionPane.showMessageDialog(null,"Этот товар имется!","Ошибка",2);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(MotherBoard.class.getName()).log(Level.SEVERE, null, ex);
	}
	return MB_exist;
    }
    
    public boolean checkID_MB(String ID){
	
	PreparedStatement ps;
	ResultSet rs;
	boolean MB_exist = false;
	
	String query = "SELECT * FROM `motherboard` WHERE `ID` = ?";
	
	try {
	    ps =Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, ID);
	    rs = ps.executeQuery();
	    if(rs.next()){
		MB_exist = true;
		JOptionPane.showMessageDialog(null,"Этот ID используется другим товаром!","Ошибка",0);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(MotherBoard.class.getName()).log(Level.SEVERE, null, ex);
	}
	return MB_exist;
    }
    
    public boolean Upd_checkMB(String Maker, String Form, String Socket, String Chipset, String RAM, String Type_RAM, String Price){
	PreparedStatement ps;
	ResultSet rs;
	boolean MB_exist = false; 
	
	String query = "SELECT * FROM `motherboard` WHERE `Maker` = ? AND `Form` = ? AND `Socket` = ? AND `chipset` = ? AND `RAM` = ? AND `TypeMemory` = ? AND `price`=?";
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, Maker);
	    ps.setString(2, Form);
	    ps.setString(3, Socket);
	    ps.setString(4, Chipset);
	    ps.setString(5, RAM);
	    ps.setString(6, Type_RAM);
	    ps.setString(7, Price);
	    rs = ps.executeQuery();
	    if(rs.next()){
		MB_exist = true;
		JOptionPane.showMessageDialog(null,"Этот товар имется!","Ошибка",2);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(MotherBoard.class.getName()).log(Level.SEVERE, null, ex);
	}
	return MB_exist;
    }
    
     public boolean Compatibility(String MB_Form,int RAM,String Socket){
	
	BOX box = new BOX();
	Memory mem = new Memory();
	CPU cpu = new CPU();
	
	boolean Com_exist = false;
	String Box_form = box.BOX_FORM;
	String Mem_mb =  String.valueOf(mem.MEM_MB);
	int mem_mb = mem.MEM_MB;
	String Cpu_mb = cpu.CPU_MB;
	
	
	    if(Box_form.isEmpty() && Mem_mb.isEmpty() && Cpu_mb.isEmpty()){
		Com_exist=true;
		System.out.println("OK");
	    }else if(Box_form.equals(MB_Form) && Mem_mb.equals(RAM) && Cpu_mb.equals(Socket)){
		System.out.println("OKK");
		Com_exist = true;
	    }
	    if(Box_form.isEmpty()){
		System.out.println("BOX OK");
		Com_exist = true;
	    }else if(!Box_form.equals(MB_Form)){
		JOptionPane.showMessageDialog(null, "Выберите товар с "+Box_form+" формой!","Товар не совместим!",0);
		Com_exist = false;
	    }
	    if(Mem_mb.isEmpty()){
		System.out.println("MEM OK");
		Com_exist = true;
	    }else if(mem_mb>=RAM){
		JOptionPane.showMessageDialog(null, "Выберите товар с "+mem_mb+" слотами для ОЗУ!","Товар не совместим!",0);
		Com_exist = false;
	    }
	    if(Cpu_mb.isEmpty()){
		System.out.println("CPU OK");
		Com_exist = true;
	    }else if(!Cpu_mb.equals(Socket)){
		JOptionPane.showMessageDialog(null, "Выберите товар с "+Cpu_mb+" сокетом!","Товар не совместим!",0);
		Com_exist = false;
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
        jButton_AddMB = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_Price = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton_UpdMB = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jTextField_RAM = new javax.swing.JTextField();
        jTextField_Socket = new javax.swing.JTextField();
        jTextField_Chipset = new javax.swing.JTextField();
        jTextField_Maker = new javax.swing.JTextField();
        jTextField_Type_RAM = new javax.swing.JTextField();
        jTextField_ID = new javax.swing.JTextField();
        jTextField_Form = new javax.swing.JTextField();
        jButton_DeleteMB = new javax.swing.JButton();
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
        jLabel1.setText("Материнская плата");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1064, Short.MAX_VALUE)
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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, -1));

        jPanel2.setBackground(new java.awt.Color(34, 49, 63));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Чипсет :");

        jButton_Back.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Back.setText("Назад");
        jButton_Back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BackActionPerformed(evt);
            }
        });

        jButton_AddMB.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_AddMB.setText("Добавить");
        jButton_AddMB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddMBActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Тип памяти :");

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
        jLabel11.setText("Кол-во слотов для ОЗУ :");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Производитель :");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Форм-фактор :");

        jButton_UpdMB.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_UpdMB.setText("Редактировать");
        jButton_UpdMB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UpdMBActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Производитель", "Форм-фактор", "Сокет", "Чипсет", "ОЗУ", "Тип ОЗУ", "Цена"
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

        jTextField_RAM.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Socket.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Chipset.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Maker.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_Type_RAM.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_ID.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_IDKeyTyped(evt);
            }
        });

        jTextField_Form.setBackground(new java.awt.Color(191, 191, 191));

        jButton_DeleteMB.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_DeleteMB.setText("Удалить");
        jButton_DeleteMB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteMBActionPerformed(evt);
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
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField_Maker, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField_RAM, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField_Chipset, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField_Type_RAM, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField_Socket, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_Form, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton_Zakaz)
                                .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_UpdMB)
                        .addGap(153, 153, 153)
                        .addComponent(jButton_DeleteMB)
                        .addGap(108, 108, 108)
                        .addComponent(jButton_AddMB))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                            .addComponent(jTextField_Maker, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Socket, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jTextField_Chipset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_RAM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Type_RAM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Form, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_UpdMB, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_AddMB, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_DeleteMB, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Zakaz, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 76, 1330, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton_AddMBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddMBActionPerformed
        String Maker = jTextField_Maker.getText();
	String Form = jTextField_Form.getText();
        String Socket = jTextField_Socket.getText();
	String Chipset = jTextField_Chipset.getText();
	String RAM = jTextField_RAM.getText();
	String Type_RAM = jTextField_Type_RAM.getText();
	String Price = jTextField_Price.getText();
	String ID = jTextField_ID.getText();
	
	if(checkFields(ID,Maker, Form, Socket,Chipset, RAM, Type_RAM, Price)){
	    if(!checkID_MB(ID)){
		if(!checkMB(Maker, Form, Socket,Chipset,RAM, Type_RAM, Price)){
		    try {
			PreparedStatement ps;
			ResultSet rs;

			ps = Parts_DB.getConection().prepareStatement(Add_MB_Query);

			ps.setString(1, ID);
			ps.setString(2, Maker);
			ps.setString(3, Form);
			ps.setString(4, Socket);
			ps.setString(5, Chipset);
			ps.setString(6, RAM);
			ps.setString(7, Type_RAM);
			ps.setString(8, Price);
			ps.execute();
			show_Parts_In_Table();
		    } catch (SQLException ex) {
			Logger.getLogger(MotherBoard.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
	    }
	}
    }//GEN-LAST:event_jButton_AddMBActionPerformed

    private void jButton_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BackActionPerformed
        Main main = new Main();
	main.setVisible(true);
	main.pack();
	main.setLocationRelativeTo(null);
	main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.dispose();
    }//GEN-LAST:event_jButton_BackActionPerformed

    private void jTextField_PriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_PriceKeyTyped
       if(!Character.isDigit(evt.getKeyChar())){
	    evt.consume();
	}
    }//GEN-LAST:event_jTextField_PriceKeyTyped

    private void jButton_UpdMBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UpdMBActionPerformed
        
	String Maker = jTextField_Maker.getText();
	String Form = jTextField_Form.getText();
        String Socket = jTextField_Socket.getText();
	String Chipset = jTextField_Chipset.getText();
	String RAM = jTextField_RAM.getText();
	String Type_RAM = jTextField_Type_RAM.getText();
	String Price = jTextField_Price.getText();
	String ID = jTextField_ID.getText();
	
	if(checkFields(ID,Maker, Form, Socket,Chipset, RAM, Type_RAM, Price)){
	    if(!Upd_checkMB(Maker, Form, Socket,Chipset,RAM, Type_RAM,Price)){
		try {
		PreparedStatement ps;
		ResultSet rs;

		ps = Parts_DB.getConection().prepareStatement(Upd_MB_Query);

		ps.setString(1, Maker);
		ps.setString(2, Form);
		ps.setString(3, Socket);
		ps.setString(4, Chipset);
		ps.setString(5, RAM);
		ps.setString(6, Type_RAM);
		ps.setString(7, Price);
		ps.setString(8, ID);
		ps.execute();
		show_Parts_In_Table();
		    } catch (SQLException ex) {
		      Logger.getLogger(MotherBoard.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
	}
    }//GEN-LAST:event_jButton_UpdMBActionPerformed

    private void jTextField_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_IDKeyTyped
        if(!Character.isDigit(evt.getKeyChar())){
	    evt.consume();
	}
    }//GEN-LAST:event_jTextField_IDKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
	int index = jTable1.getSelectedRow();
	Show_Item(index);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton_DeleteMBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteMBActionPerformed
        
	String ID = jTextField_ID.getText();
	
	PreparedStatement ps;
	ResultSet rs;
	
	if(!ID_Field()){
	    try {
		ps = Parts_DB.getConection().prepareStatement(Del_MB_Query);
		ps.setString(1, ID);
		ps.execute();
		show_Parts_In_Table();
	    } catch (SQLException ex) {
		Logger.getLogger(MotherBoard.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }//GEN-LAST:event_jButton_DeleteMBActionPerformed

    private void jButton_ZakazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ZakazActionPerformed

	String Maker = jTextField_Maker.getText();
	String Form = jTextField_Form.getText();
        String Socket = jTextField_Socket.getText();
	String Chipset = jTextField_Chipset.getText();
	String Type_RAM = jTextField_Type_RAM.getText();
	String Price = jTextField_Price.getText();
	String ID = jTextField_ID.getText();
	String ram = jTextField_RAM.getText();
	int RAM = Integer.parseInt(ram);
	
	if(Compatibility(Form, RAM, Socket)){
	    if(checkFields(ID, Maker, Form, Socket, Chipset, ram, Type_RAM, Price)){
		SOCKET = Socket;
		FORM = Form;
		RAMM = RAM;
		ZAK_MB = ID;
	    for(int i=0;i<=0;i++){
		    a[0] = ID;
		    a[1] = Maker;
		    a[2] = Form;
		    a[3] = Socket;
		    a[4] = Chipset;
		    a[5] = Type_RAM;
		    a[6] = ram;
		    a[7] = Price;
		    
		    MB_PRICE = Integer.parseInt(a[7]);
		}
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
	    java.util.logging.Logger.getLogger(MotherBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(MotherBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(MotherBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(MotherBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new MotherBoard().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_AddMB;
    private javax.swing.JButton jButton_Back;
    private javax.swing.JButton jButton_DeleteMB;
    private javax.swing.JButton jButton_UpdMB;
    private javax.swing.JButton jButton_Zakaz;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JTextField jTextField_Chipset;
    private javax.swing.JTextField jTextField_Form;
    private javax.swing.JTextField jTextField_ID;
    private javax.swing.JTextField jTextField_Maker;
    private javax.swing.JTextField jTextField_Price;
    private javax.swing.JTextField jTextField_RAM;
    public javax.swing.JTextField jTextField_Socket;
    private javax.swing.JTextField jTextField_Type_RAM;
    // End of variables declaration//GEN-END:variables
}
