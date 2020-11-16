import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class zakaz extends javax.swing.JFrame {

    public zakaz() {
	initComponents();
	
	show_Parts_In_Table();
	
	jTextArea1.setEditable(false);
	
	MotherBoard MB = new MotherBoard();
	CPU cpu = new CPU();
	VideoCards VC = new VideoCards();
	SSD ssd = new SSD();
	Memory mem = new Memory();
	BP bp = new BP();
	BOX box = new BOX();
	
	jTextField_MB.setEditable(false);
	jTextField_CPU.setEditable(false);
	jTextField_VC.setEditable(false);
	jTextField_SSD.setEditable(false);
	jTextField_RAM.setEditable(false);
	jTextField_BP.setEditable(false);
	jTextField_BOX.setEditable(false);
	jTextField_ID.setEditable(false);
	
	jTextField_MB.setText(MB.ZAK_MB);
	jTextField_CPU.setText(cpu.ZAK_CPU);
	jTextField_VC.setText(VC.ZAK_VC);
	jTextField_SSD.setText(ssd.ZAK_SSD);
	jTextField_RAM.setText(mem.ZAK_MEM);
	jTextField_BP.setText(bp.ZAK_BP);
	jTextField_BOX.setText(box.ZAK_BOX);
	
	Login log = new Login();
	
	if(log.chLogin==true){
	    jButton_Buy.setVisible(false);
	    jButton_del.setVisible(false);
	}
    }

    public static final String ZAK_ADD = "INSERT INTO `zakaz`(`ID box`, `ID bp`, `ID cpu`, `ID memory`, `ID motherboard`, `ID ssd`, `ID vc`, `ID user`) VALUES (?,?,?,?,?,?,?,?)";
    public static final String ZAK_UPD = "UPDATE `zakaz` SET ``ID box`=?,`ID bp`=?,`ID cpu`=?,`ID memory`=?,`ID motherboard`=?,`ID ssd`=?,`ID vc`=?,`ID user`=? WHERE ID`=?";
    public static final String ZAK_DEL = "DELETE FROM `zakaz` WHERE `ID` = ?";
    
    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
    
    public ArrayList <Parts_DB> get_Parts(){
    
	ArrayList <Parts_DB> Parts_List = new ArrayList <Parts_DB>();
	
	PreparedStatement ps;
	ResultSet rs;
	Login log = new Login();
	int log_id = log.ZAK_ID;
	if(log.chLogin==false){
	    String zak_u_Query = "SELECT * FROM `zakaz` WHERE `ID user` = ?";
	    try {
		ps = Parts_DB.getConection().prepareStatement(zak_u_Query);
		ps.setInt(1, log_id);
		rs = ps.executeQuery();
		Parts_DB parts;
	    
	    while(rs.next()){
	    
		parts = new Parts_DB(rs.getInt("ID"),rs.getInt("ID box"),rs.getInt("ID bp"),rs.getInt("ID cpu"),rs.getInt("ID memory"),
			rs.getInt("ID motherboard"),rs.getInt("ID ssd"),rs.getInt("ID vc"),rs.getInt("ID user"));
		
		Parts_List.add(parts);
	    }
	} catch (SQLException ex) {
		Logger.getLogger(zakaz.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}else{
	    String zak_Query = "SELECT * FROM `zakaz`";
	    try {
		ps = Parts_DB.getConection().prepareStatement(zak_Query);
		rs = ps.executeQuery();
		Parts_DB parts;
	    
	    while(rs.next()){
	    
		parts = new Parts_DB(rs.getInt("ID"),rs.getInt("ID box"),rs.getInt("ID bp"),rs.getInt("ID cpu"),rs.getInt("ID memory"),
			rs.getInt("ID motherboard"),rs.getInt("ID ssd"),rs.getInt("ID vc"),rs.getInt("ID user"));
		
		Parts_List.add(parts);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(zakaz.class.getName()).log(Level.SEVERE, null, ex);
	    }
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
	    row[1] = list.get(i).getID_box();
	    row[2] = list.get(i).getID_bp();
	    row[3] = list.get(i).getID_cpu();
	    row[4] = list.get(i).getID_mem();
	    row[5] = list.get(i).getID_mb();
	    row[6] = list.get(i).getID_ssd();
	    row[7] = list.get(i).getID_vc();
	    row[8] = list.get(i).getID_user();
	    
	    model.addRow(row);
	}
    }
    
    public void Show_Item(int index){
	jTextField_BOX.setText(Integer.toString(get_Parts().get(index).getID_box()));
	jTextField_BP.setText(Integer.toString(get_Parts().get(index).getID_bp()));
        jTextField_CPU.setText(Integer.toString(get_Parts().get(index).getID_cpu()));
	jTextField_MB.setText(Integer.toString(get_Parts().get(index).getID_mb()));
	jTextField_RAM.setText(Integer.toString(get_Parts().get(index).getID_mem()));
	jTextField_SSD.setText(Integer.toString(get_Parts().get(index).getID_ssd()));
	jTextField_VC.setText(Integer.toString(get_Parts().get(index).getID_vc()));
	jTextField_ID.setText(Integer.toString(get_Parts().get(index).getID()));
    }
    
    public boolean checkFields(String ID, String BP, String CPU, String MB, String SSD, int id_zak, String RAM, String VC){
	
	if(jTextField_BP.getText().trim().equals("")
	    ||jTextField_CPU.getText().trim().equals("")||jTextField_MB.getText().equals("")
	    ||jTextField_SSD.getText().trim().equals("")||jTextField_RAM.getText().equals("")
	    ||jTextField_VC.getText().trim().equals("")||jTextField_BOX.getText().trim().equals("")){
	    JOptionPane.showMessageDialog(null, "Вы собрали не весь ПК!" ,"O-o-yу", 2);
	    return false;
	}else{
	    return true;
	}
    }
    
    public boolean check_zak(String ID){
	
	PreparedStatement ps;
	ResultSet rs;
	
	boolean zak_exist = true;
	
	String query = "SELECT * FROM `zakaz` WHERE `ID`=?";
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1  , ID);
	    rs = ps.executeQuery();
	    if(rs.next()){
		zak_exist = false;
		JOptionPane.showMessageDialog(null, "Этот заказ уже имеется!","Внимание!",1);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(zakaz.class.getName()).log(Level.SEVERE, null, ex);
	}
	return zak_exist;
    }
    
    public boolean check_zakaz(String bp_zak,String mem_zak, String cpu_zak,String mb_zak, String ssd_zak,int zak_log,String vc_zak,String box_zak){
	
	PreparedStatement ps;
	ResultSet rs;
	
	boolean zak_exist = true;
	
	String query = "SELECT * FROM `zakaz` WHERE  `ID box`=? AND `ID bp`=? AND `ID cpu`=? AND `ID memory`=? AND `ID motherboard`=? AND `ID ssd`=? AND `ID vc`=? AND `ID user`=?";
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1  ,box_zak);
	    ps.setString(2  ,bp_zak);
	    ps.setString(3  ,cpu_zak);
	    ps.setString(4  ,mem_zak);
	    ps.setString(5  ,mb_zak);
	    ps.setString(6  ,ssd_zak);
	    ps.setString(7  ,vc_zak);
	    ps.setInt(8  ,zak_log);
	    rs = ps.executeQuery();
	    if(rs.next()){
		zak_exist = false;
		JOptionPane.showMessageDialog(null, "Вы уже офомили точно такой же заказ!","Внимание!",1);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(zakaz.class.getName()).log(Level.SEVERE, null, ex);
	}
	return zak_exist;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton_Back = new javax.swing.JButton();
        jButton_Buy = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField_ID = new javax.swing.JTextField();
        jTextField_BP = new javax.swing.JTextField();
        jTextField_SSD = new javax.swing.JTextField();
        jTextField_BOX = new javax.swing.JTextField();
        jTextField_RAM = new javax.swing.JTextField();
        jTextField_VC = new javax.swing.JTextField();
        jTextField_CPU = new javax.swing.JTextField();
        jTextField_MB = new javax.swing.JTextField();
        jLabel_Info_mb = new javax.swing.JLabel();
        jLabel_Info_vc = new javax.swing.JLabel();
        jLabel_Info_ram = new javax.swing.JLabel();
        jLabel_Info_box = new javax.swing.JLabel();
        jLabel_Info_cpu = new javax.swing.JLabel();
        jLabel_Info_ssd = new javax.swing.JLabel();
        jLabel_Info_bp = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton_del = new javax.swing.JButton();

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

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Заказы");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1267, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(34, 49, 63));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 750));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Корпус", "Блок питания", "ЦП", "ОЗУ", "Материнская плата", "SSD", "Видеокарта", "ID покупателя"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton_Back.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Back.setText("Назад");
        jButton_Back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BackActionPerformed(evt);
            }
        });

        jButton_Buy.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_Buy.setText("Оформить заказ");
        jButton_Buy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Buy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BuyActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Материнская плата :");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ЦП :");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Видеокарта :");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ОЗУ :");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Корпус :");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("SSD :");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Блок питания :");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ID :");

        jTextField_ID.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_IDKeyTyped(evt);
            }
        });

        jTextField_BP.setBackground(new java.awt.Color(191, 191, 191));
        jTextField_BP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_BPKeyTyped(evt);
            }
        });

        jTextField_SSD.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_BOX.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_RAM.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_VC.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_CPU.setBackground(new java.awt.Color(191, 191, 191));

        jTextField_MB.setBackground(new java.awt.Color(191, 191, 191));

        jLabel_Info_mb.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel_Info_mb.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Info_mb.setText("?");
        jLabel_Info_mb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_Info_mbMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_Info_mbMouseExited(evt);
            }
        });

        jLabel_Info_vc.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel_Info_vc.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Info_vc.setText("?");
        jLabel_Info_vc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_Info_vcMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_Info_vcMouseExited(evt);
            }
        });

        jLabel_Info_ram.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel_Info_ram.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Info_ram.setText("?");
        jLabel_Info_ram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_Info_ramMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_Info_ramMouseExited(evt);
            }
        });

        jLabel_Info_box.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel_Info_box.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Info_box.setText("?");
        jLabel_Info_box.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_Info_boxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_Info_boxMouseExited(evt);
            }
        });

        jLabel_Info_cpu.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel_Info_cpu.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Info_cpu.setText("?");
        jLabel_Info_cpu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_Info_cpuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_Info_cpuMouseExited(evt);
            }
        });

        jLabel_Info_ssd.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel_Info_ssd.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Info_ssd.setText("?");
        jLabel_Info_ssd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_Info_ssdMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_Info_ssdMouseExited(evt);
            }
        });

        jLabel_Info_bp.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel_Info_bp.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Info_bp.setText("?");
        jLabel_Info_bp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_Info_bpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_Info_bpMouseExited(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(34, 49, 63));
        jTextArea1.setColumns(10);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setDisabledTextColor(new java.awt.Color(34, 49, 63));
        jScrollPane2.setViewportView(jTextArea1);

        jButton_del.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton_del.setText("Удалить");
        jButton_del.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_delActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField_MB, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_RAM, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_VC, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_BOX, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_CPU, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_SSD, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_BP, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_Info_ram, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel_Info_box, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                .addComponent(jLabel_Info_mb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_Info_vc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_Info_cpu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_Info_ssd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_Info_bp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_del)
                        .addGap(332, 332, 332)
                        .addComponent(jButton_Buy))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_MB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_Info_mb))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField_CPU, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel_Info_cpu, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField_VC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel_Info_vc)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_RAM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_Info_ram))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_BOX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_Info_box)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_SSD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Info_ssd))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_BP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Info_bp))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Buy, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_del, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
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

    private void jButton_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BackActionPerformed
        Main main = new Main();
        main.setVisible(true);
        main.pack();
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton_BackActionPerformed

    private void jButton_BuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BuyActionPerformed
    	Login log = new Login();
	MotherBoard MB = new MotherBoard();
	CPU cpu = new CPU();
	VideoCards VC = new VideoCards();
	SSD ssd = new SSD();
	Memory mem = new Memory();
	BP bp = new BP();
	BOX box = new BOX();
	
	String mb_zak = MB.ZAK_MB;
	String cpu_zak = cpu.ZAK_CPU;
	String vc_zak = VC.ZAK_VC;
	String ssd_zak = ssd.ZAK_SSD;
	String mem_zak = mem.ZAK_MEM;
	String bp_zak = bp.ZAK_BP;
	String box_zak = box.ZAK_BOX;
	String id = jTextField_ID.getText();
	int id_zak = log.ZAK_ID;
	
	PreparedStatement ps;
	ResultSet rs;
	if(check_zak(id)){
	    if(checkFields(bp_zak, mem_zak, cpu_zak, mb_zak, ssd_zak, id_zak, vc_zak, box_zak)){
		if(check_zakaz(bp_zak, mem_zak, cpu_zak, mb_zak, ssd_zak, id_zak, vc_zak, box_zak)){
		try {
		    ps = Parts_DB.getConection().prepareStatement(ZAK_ADD);
		    ps.setString(1, box_zak);
		    ps.setString(2, bp_zak);
		    ps.setString(3, cpu_zak);
		    ps.setString(4, mem_zak);
		    ps.setString(5, mb_zak);
		    ps.setString(6, ssd_zak);
		    ps.setString(7, vc_zak);
		    ps.setInt(8, id_zak);
		    ps.execute();
		} catch (SQLException ex) {
		    Logger.getLogger(zakaz.class.getName()).log(Level.SEVERE, null, ex);
		}

	    //mail
		String mes = "Материнская плата :"+MB.a[1]+" "+MB.a[2]+"\n" ;
		mes +="Процессор :"+cpu.a[1]+" "+cpu.a[0]+"\n";
		mes +="Видеокарта :"+VC.a[0]+" "+VC.a[1]+"\n";
		mes +="SSD :"+ ssd.a[0]+" "+ssd.a[1]+"\n";
		mes +="ОЗУ :"+mem.a[0]+" "+mem.a[1]+"\n";
		mes +="Корпус :"+box.a[0]+" "+box.a[1]+"\n";
		mes +="Блок питания :"+bp.a[0]+" "+bp.a[1]+"\n";

		String login = log.ZAK_LOG;

		String query = "SELECT `Tel`,`U_F_Name`,`U_S_Name` FROM `user` WHERE `login` = ?";

		try {
		    ps = Users_DB.getConection().prepareStatement(query);

		    ps.setString(1, login);
		    rs = ps.executeQuery();

		    while(rs.next()){
			String mail = rs.getString(1);
			String name = rs.getString(2);
			String fam = rs.getString(3);
			System.out.println(mail);

			String USER_NAME = "mailforsendmes";  // GMail user name (just the part before "@gmail.com")
			String PASSWORD = "asdlkjgg123"; // GMail password
			String RECIPIENT = mail;

			String from = USER_NAME;
			String pass = PASSWORD;
			String[] to = { RECIPIENT }; // list of recipient email addresses
			String subject = "Компьютерный магазин";
			String body = "Ваш заказ :\n"+mes+"\n"+name+" "+fam;

			sendFromGMail(from, pass, to, subject, body);
			int mb_price =MB.MB_PRICE;
			int cpu_price = cpu.CPU_PRICE;
			int vc_price = VC.VC_PRICE;
			int mem_price = mem.MEM_PRICE;
			int box_price = box.BOX_PRICE;
			int ssd_price = SSD.SSD_PRICE;
			int bp_price = bp.BP_PRICE;
			FileWriter fw = new FileWriter("Чек "+name+" "+fam+".txt");
			fw.write("\tЧЕК \n\n"+mes+"\nК оплате: "+(mb_price+cpu_price+vc_price+mem_price+box_price+ssd_price+bp_price)+"\n"+name+" "+fam);
			fw.close();
		    }
		} catch (SQLException | IOException ex) {
		    Logger.getLogger(zakaz.class.getName()).log(Level.SEVERE, null, ex);
		}
		show_Parts_In_Table();
		JOptionPane.showMessageDialog(null, "Ваш заказ успешно оформлен!","Благодарим за покупку!",JOptionPane.PLAIN_MESSAGE);
	    }
	    }
	}
    }//GEN-LAST:event_jButton_BuyActionPerformed

    private void jTextField_BPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BPKeyTyped
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_BPKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();
	Show_Item(index);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel_Info_mbMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Info_mbMouseEntered

	MotherBoard MB = new MotherBoard();
	
	PreparedStatement ps;
	ResultSet rs;
	
	String query = "SELECT * FROM `motherboard` WHERE `ID` = ?";
	
	String b = "";
	
	String ID_mb = jTextField_MB.getText();
	
	    try {
		ps = Parts_DB.getConection().prepareStatement(query);
		ps.setString(1, ID_mb);
		rs = ps.executeQuery();
		
		    while(rs.next()){
			MB.a[1]=rs.getString(2);
			MB.a[2]=rs.getString(3);
			MB.a[3]=rs.getString(4);
			MB.a[4]=rs.getString(5);
			MB.a[5]=rs.getString(6);
			MB.a[6]=rs.getString(7);
			MB.a[7]=rs.getString(8);
		    }
		    for(int i =1;i<=7;i++){
			b+=MB.a[i]+"\n";
		    }
		    jTextArea1.setText(b);
	    if(jTextField_MB.getText().trim().equals("")){
		jTextArea1.setText("Вы не выбрали\nматеринскую плату!");
	    }
	    } catch (SQLException ex) {
		Logger.getLogger(zakaz.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }//GEN-LAST:event_jLabel_Info_mbMouseEntered

    private void jLabel_Info_mbMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Info_mbMouseExited
        jTextArea1.setText("");
    }//GEN-LAST:event_jLabel_Info_mbMouseExited

    private void jLabel_Info_cpuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Info_cpuMouseEntered
        CPU cpu = new CPU();
	
	PreparedStatement ps;
	ResultSet rs;
	
	String query = "SELECT * FROM `cpu` WHERE `ID` =?";
	
	String b = "";
	
	String ID_cpu = jTextField_CPU.getText();
	
	    try {
		ps = Parts_DB.getConection().prepareStatement(query);
		ps.setString(1, ID_cpu);
		rs = ps.executeQuery();
		
		    while(rs.next()){
			cpu.a[1]=rs.getString(2);
			cpu.a[2]=rs.getString(3);
			cpu.a[3]=rs.getString(4);
			cpu.a[4]=rs.getString(5);
			cpu.a[5]=rs.getString(6);
			cpu.a[6]=rs.getString(7);
			cpu.a[7]=rs.getString(8);
			cpu.a[8]=rs.getString(9);
		    }
		    for(int i =1;i<=8;i++){
			b+=cpu.a[i]+"\n";
		    }
		    jTextArea1.setText(b);
		if(jTextField_CPU.getText().trim().equals("")){
		    jTextArea1.setText("Вы не выбрали\nпроцессор!");
		}
	    } catch (SQLException ex) {
		Logger.getLogger(zakaz.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }//GEN-LAST:event_jLabel_Info_cpuMouseEntered

    private void jLabel_Info_cpuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Info_cpuMouseExited
        jTextArea1.setText("");
    }//GEN-LAST:event_jLabel_Info_cpuMouseExited

    private void jLabel_Info_vcMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Info_vcMouseEntered
        
	VideoCards VC = new VideoCards();
	String ID_vc = jTextField_VC.getText();
	
	String query = "SELECT * FROM `videocard` WHERE `ID` =?";
	
	String b = "";
	
	PreparedStatement ps;
	ResultSet rs;
	
	try {
		ps = Parts_DB.getConection().prepareStatement(query);
		ps.setString(1, ID_vc);
		rs = ps.executeQuery();
		
		    while(rs.next()){
			VC.a[1]=rs.getString(2);
			VC.a[2]=rs.getString(3);
			VC.a[3]=rs.getString(4);
			VC.a[4]=rs.getString(5);
			VC.a[5]=rs.getString(6);
			VC.a[6]=rs.getString(7);
		    }
		    for(int i =1;i<=6;i++){
			b+=VC.a[i]+"\n";
		    }
		    jTextArea1.setText(b);
		if(jTextField_VC.getText().trim().equals("")){
		    jTextArea1.setText("Вы не выбрали\nвидеокарту!");
		}
	    } catch (SQLException ex) {
		Logger.getLogger(zakaz.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }//GEN-LAST:event_jLabel_Info_vcMouseEntered

    private void jLabel_Info_vcMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Info_vcMouseExited
	jTextArea1.setText("");
    }//GEN-LAST:event_jLabel_Info_vcMouseExited

    private void jLabel_Info_ramMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Info_ramMouseEntered
        
	Memory mem = new Memory();
    
	String ID_mem = jTextField_RAM.getText();
	
	String query = "SELECT * FROM `memory` WHERE `ID` =?";
	
	String b = "";
	
	PreparedStatement ps;
	ResultSet rs;
	
	try {
		ps = Parts_DB.getConection().prepareStatement(query);
		ps.setString(1, ID_mem);
		rs = ps.executeQuery();
		
		    while(rs.next()){
			mem.a[1]=rs.getString(2);
			mem.a[2]=rs.getString(3);
			mem.a[3]=rs.getString(4);
			mem.a[4]=rs.getString(5);
			mem.a[5]=rs.getString(6);
			mem.a[6]=rs.getString(7);
			mem.a[7]=rs.getString(8);
			mem.a[8]=rs.getString(9);
			mem.a[9]=rs.getString(10);
		    }
		    for(int i =1;i<=9;i++){
			b+=mem.a[i]+"\n";
		    }
		    jTextArea1.setText(b);
		if(jTextField_RAM.getText().trim().equals("")){
		    jTextArea1.setText("Вы не выбрали\nоперативную память!");
		}
	    } catch (SQLException ex) {
		Logger.getLogger(zakaz.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }//GEN-LAST:event_jLabel_Info_ramMouseEntered

    private void jLabel_Info_ramMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Info_ramMouseExited
        jTextArea1.setText("");
    }//GEN-LAST:event_jLabel_Info_ramMouseExited

    private void jLabel_Info_boxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Info_boxMouseEntered
        BOX box = new BOX();
    
	String ID_box = jTextField_BOX.getText();
	
	String query = "SELECT * FROM `box` WHERE `ID` =?";
	
	String b = "";
	
	PreparedStatement ps;
	ResultSet rs;
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, ID_box);
	    rs = ps.executeQuery();
		
		while(rs.next()){
		    box.a[1]=rs.getString(2);
		    box.a[2]=rs.getString(3);
		    box.a[3]=rs.getString(4);
		    box.a[4]=rs.getString(5);
		    box.a[5]=rs.getString(6);
		}
		    for(int i =1;i<=5;i++){
			b+=box.a[i]+"\n";
		    }
		    jTextArea1.setText(b);
		if(jTextField_BOX.getText().trim().equals("")){
		    jTextArea1.setText("Вы не выбрали\nкорпус!");
		}
	    } catch (SQLException ex) {
		Logger.getLogger(zakaz.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }//GEN-LAST:event_jLabel_Info_boxMouseEntered

    private void jLabel_Info_boxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Info_boxMouseExited
        jTextArea1.setText("");
    }//GEN-LAST:event_jLabel_Info_boxMouseExited

    private void jLabel_Info_ssdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Info_ssdMouseEntered
        
	SSD ssd = new SSD();
	
	String ID_ssd = jTextField_SSD.getText();
	
	String query = "SELECT * FROM `ssd` WHERE `ID` =?";
	
	String b = "";
	
	PreparedStatement ps;
	ResultSet rs;
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, ID_ssd);
	    rs = ps.executeQuery();
		
		while(rs.next()){
		    ssd.a[1]=rs.getString(2);
		    ssd.a[2]=rs.getString(3);
		    ssd.a[3]=rs.getString(4);
		    ssd.a[4]=rs.getString(5);
		}
		    for(int i =1;i<=4;i++){
			b+=ssd.a[i]+"\n";
		    }
		    jTextArea1.setText(b);
		if(jTextField_SSD.getText().trim().equals("")){
		    jTextArea1.setText("Вы не выбрали\nSSD!");
		}
	    } catch (SQLException ex) {
		Logger.getLogger(zakaz.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }//GEN-LAST:event_jLabel_Info_ssdMouseEntered

    private void jLabel_Info_ssdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Info_ssdMouseExited
        jTextArea1.setText("");
    }//GEN-LAST:event_jLabel_Info_ssdMouseExited

    private void jLabel_Info_bpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Info_bpMouseEntered
        BP bp = new BP();
	
	String ID_bp = jTextField_BP.getText();
	
	String query = "SELECT * FROM `bp` WHERE `ID` =?";
	
	String b = "";
	
	PreparedStatement ps;
	ResultSet rs;
	
	try {
	    ps = Parts_DB.getConection().prepareStatement(query);
	    ps.setString(1, ID_bp);
	    rs = ps.executeQuery();
		
		while(rs.next()){
		    bp.a[1]=rs.getString(2);
		    bp.a[2]=rs.getString(3);
		    bp.a[3]=rs.getString(4);
		}
		    for(int i =1;i<=3;i++){
			b+=bp.a[i]+"\n";
		    }
		    jTextArea1.setText(b);
		if(jTextField_BP.getText().trim().equals("")){
		    jTextArea1.setText("Вы не выбрали\nблок питания!");
		}
	    } catch (SQLException ex) {
		Logger.getLogger(zakaz.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }//GEN-LAST:event_jLabel_Info_bpMouseEntered

    private void jLabel_Info_bpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_Info_bpMouseExited
        jTextArea1.setText("");
    }//GEN-LAST:event_jLabel_Info_bpMouseExited

    private void jTextField_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_IDKeyTyped
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_IDKeyTyped

    private void jButton_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_delActionPerformed
	String mb = jTextField_MB.getText();
	String cpu = jTextField_CPU.getText();
	String vc = jTextField_VC.getText();
	String ssd = jTextField_SSD.getText();
	String ram = jTextField_RAM.getText();
	String bp = jTextField_BP.getText();
	String box = jTextField_BOX.getText();
	String ID = jTextField_ID.getText();
//	int id = Integer.getInteger(jTextField_ID.getText());

	PreparedStatement ps;
	ResultSet rs;
	if(checkFields(box, bp, cpu, mb, ssd, 1, ram, vc))
		try {
		    ps = Parts_DB.getConection().prepareStatement(ZAK_DEL);
		    ps.setString(1, ID);
		    ps.execute();
		    show_Parts_In_Table();
		} catch (SQLException ex) {
		    Logger.getLogger(zakaz.class.getName()).log(Level.SEVERE, null, ex);
		}
    }//GEN-LAST:event_jButton_delActionPerformed

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
	    java.util.logging.Logger.getLogger(zakaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(zakaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(zakaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(zakaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new zakaz().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Back;
    private javax.swing.JButton jButton_Buy;
    private javax.swing.JButton jButton_del;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Info_box;
    private javax.swing.JLabel jLabel_Info_bp;
    private javax.swing.JLabel jLabel_Info_cpu;
    private javax.swing.JLabel jLabel_Info_mb;
    private javax.swing.JLabel jLabel_Info_ram;
    private javax.swing.JLabel jLabel_Info_ssd;
    private javax.swing.JLabel jLabel_Info_vc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField_BOX;
    private javax.swing.JTextField jTextField_BP;
    private javax.swing.JTextField jTextField_CPU;
    private javax.swing.JTextField jTextField_ID;
    private javax.swing.JTextField jTextField_MB;
    private javax.swing.JTextField jTextField_RAM;
    private javax.swing.JTextField jTextField_SSD;
    private javax.swing.JTextField jTextField_VC;
    // End of variables declaration//GEN-END:variables
}
