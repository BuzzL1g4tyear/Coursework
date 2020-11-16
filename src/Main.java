import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;


public class Main extends javax.swing.JFrame {

    public static Color c1 = new Color(34,49,63); 
    
    public Main() {
	initComponents();
	
	Login log = new Login();
	jLabel_Users.setVisible(log.chLogin);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel_Out = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel_MB = new javax.swing.JLabel();
        jLabel_CPU = new javax.swing.JLabel();
        jLabel_VC = new javax.swing.JLabel();
        jLabel_RAM = new javax.swing.JLabel();
        jLabel_BOX = new javax.swing.JLabel();
        jLabel_BP = new javax.swing.JLabel();
        jLabel_SSD = new javax.swing.JLabel();
        jLabel_Users = new javax.swing.JLabel();
        jLabel_Zakaz = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

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
        jLabel4.setText("Компьютерный магазин");

        jLabel_Out.setText("Сменить пользователя");
        jLabel_Out.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel_Out.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_Out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_OutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1007, Short.MAX_VALUE)
                .addComponent(jLabel_Out)
                .addGap(18, 18, 18)
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
                    .addComponent(jLabel4)
                    .addComponent(jLabel_Out))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(34, 49, 63));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 750));

        jLabel_MB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/Материнка1.png"))); // NOI18N
        jLabel_MB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_MB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_MBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_MBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_MBMouseExited(evt);
            }
        });

        jLabel_CPU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/проц.png"))); // NOI18N
        jLabel_CPU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_CPU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_CPUMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_CPUMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_CPUMouseExited(evt);
            }
        });

        jLabel_VC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/видюха.png"))); // NOI18N
        jLabel_VC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_VC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_VCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_VCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_VCMouseExited(evt);
            }
        });

        jLabel_RAM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/ОЗУ.png"))); // NOI18N
        jLabel_RAM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_RAM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_RAMMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_RAMMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_RAMMouseExited(evt);
            }
        });

        jLabel_BOX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/cabinet-case.jpg"))); // NOI18N
        jLabel_BOX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_BOX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_BOXMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_BOXMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_BOXMouseExited(evt);
            }
        });

        jLabel_BP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/Блок питания.png"))); // NOI18N
        jLabel_BP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_BP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_BPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_BPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_BPMouseExited(evt);
            }
        });

        jLabel_SSD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/SSD.png"))); // NOI18N
        jLabel_SSD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_SSD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_SSDMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_SSDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_SSDMouseExited(evt);
            }
        });

        jLabel_Users.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel_Users.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Users.setText("Пользователи");
        jLabel_Users.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_Users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_UsersMouseClicked(evt);
            }
        });

        jLabel_Zakaz.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel_Zakaz.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Zakaz.setText("Заказы");
        jLabel_Zakaz.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_Zakaz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_ZakazMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("i");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel_BOX)
                .addGap(244, 244, 244)
                .addComponent(jLabel_BP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_SSD)
                .addGap(161, 161, 161))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel_MB, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187)
                .addComponent(jLabel_CPU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
                .addComponent(jLabel_VC)
                .addGap(252, 252, 252)
                .addComponent(jLabel_RAM)
                .addGap(55, 55, 55))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Users, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_Zakaz, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel_VC)
                        .addGap(117, 117, 117)
                        .addComponent(jLabel_BP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel_BOX))
                .addGap(146, 146, 146))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel_Users)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_Zakaz)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 95, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel_RAM, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(149, 149, 149)
                                .addComponent(jLabel_SSD, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(167, 167, 167))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_CPU)
                                    .addComponent(jLabel_MB))
                                .addGap(484, 484, 484))))))
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

    private void jLabel_MBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_MBMouseClicked
        MotherBoard MB = new MotherBoard();
    
	MB.setVisible(true);
	MB.pack();
	MB.setLocationRelativeTo(null);
	MB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.dispose();
	
    }//GEN-LAST:event_jLabel_MBMouseClicked

    private void jLabel_CPUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_CPUMouseClicked
        CPU cpu = new CPU();
    
	cpu.setVisible(true);
	cpu.pack();
	cpu.setLocationRelativeTo(null);
	cpu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.dispose();
	
    }//GEN-LAST:event_jLabel_CPUMouseClicked

    private void jLabel_VCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_VCMouseClicked
        VideoCards VC = new VideoCards();
    
	VC.setVisible(true);
	VC.pack();
	VC.setLocationRelativeTo(null);
	VC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.dispose();
	
    }//GEN-LAST:event_jLabel_VCMouseClicked

    private void jLabel_RAMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_RAMMouseClicked
        Memory mem = new Memory();
    
	mem.setVisible(true);
	mem.pack();
	mem.setLocationRelativeTo(null);
	mem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.dispose();
	
    }//GEN-LAST:event_jLabel_RAMMouseClicked

    private void jLabel_BOXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BOXMouseClicked
        BOX box = new BOX();
    
	box.setVisible(true);
	box.pack();
	box.setLocationRelativeTo(null);
	box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.dispose();
	
    }//GEN-LAST:event_jLabel_BOXMouseClicked

    private void jLabel_BPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BPMouseClicked
        BP bp = new BP();
   
	bp.setVisible(true);
	bp.pack();
	bp.setLocationRelativeTo(null);
	bp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.dispose();
	
    }//GEN-LAST:event_jLabel_BPMouseClicked

    private void jLabel_SSDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_SSDMouseClicked
        SSD ssd = new SSD();
    
	ssd.setVisible(true);
	ssd.pack();
	ssd.setLocationRelativeTo(null);
	ssd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.dispose();
	
    }//GEN-LAST:event_jLabel_SSDMouseClicked

    private void jLabel_UsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_UsersMouseClicked
        profiles prof = new profiles();
    
	prof.setVisible(true);
	prof.pack();
	prof.setLocationRelativeTo(null);
	prof.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.dispose();
	
    }//GEN-LAST:event_jLabel_UsersMouseClicked

    private void jLabel_MBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_MBMouseEntered
        
	Border Label_border = BorderFactory.createMatteBorder(1,1,1,1,Color.white);
	jLabel_MB.setBorder(Label_border);
	
    }//GEN-LAST:event_jLabel_MBMouseEntered

    private void jLabel_MBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_MBMouseExited
        
	Border Label_border = BorderFactory.createMatteBorder(1,1,1,1,c1);
	jLabel_MB.setBorder(Label_border);
	
    }//GEN-LAST:event_jLabel_MBMouseExited

    private void jLabel_CPUMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_CPUMouseEntered
       
	Border Label_border = BorderFactory.createMatteBorder(1,1,1,1,Color.white);
	jLabel_CPU.setBorder(Label_border);
	
    }//GEN-LAST:event_jLabel_CPUMouseEntered

    private void jLabel_CPUMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_CPUMouseExited
        
	Border Label_border = BorderFactory.createMatteBorder(1,1,1,1,c1);
	jLabel_CPU.setBorder(Label_border);
	
    }//GEN-LAST:event_jLabel_CPUMouseExited

    private void jLabel_VCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_VCMouseEntered
        
	Border Label_border = BorderFactory.createMatteBorder(1,1,1,1,Color.white);
	jLabel_VC.setBorder(Label_border);
	
    }//GEN-LAST:event_jLabel_VCMouseEntered

    private void jLabel_VCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_VCMouseExited
        
	Border Label_border = BorderFactory.createMatteBorder(1,1,1,1,c1);
	jLabel_VC.setBorder(Label_border);
	
    }//GEN-LAST:event_jLabel_VCMouseExited

    private void jLabel_RAMMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_RAMMouseEntered
        
	Border Label_border = BorderFactory.createMatteBorder(1,1,1,1,Color.white);
	jLabel_RAM.setBorder(Label_border);
	
    }//GEN-LAST:event_jLabel_RAMMouseEntered

    private void jLabel_RAMMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_RAMMouseExited
       
	Border Label_border = BorderFactory.createMatteBorder(1,1,1,1,c1);
	jLabel_RAM.setBorder(Label_border);
	
    }//GEN-LAST:event_jLabel_RAMMouseExited

    private void jLabel_BOXMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BOXMouseEntered
        
	Border Label_border = BorderFactory.createMatteBorder(1,1,1,1,Color.white);
	jLabel_BOX.setBorder(Label_border);
	
    }//GEN-LAST:event_jLabel_BOXMouseEntered

    private void jLabel_BOXMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BOXMouseExited
        
	Border Label_border = BorderFactory.createMatteBorder(1,1,1,1,c1);
	jLabel_BOX.setBorder(Label_border);
	
    }//GEN-LAST:event_jLabel_BOXMouseExited

    private void jLabel_BPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BPMouseEntered
        
	Border Label_border = BorderFactory.createMatteBorder(1,1,1,1,Color.white);
	jLabel_BP.setBorder(Label_border);
	
    }//GEN-LAST:event_jLabel_BPMouseEntered

    private void jLabel_BPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BPMouseExited
        
	Border Label_border = BorderFactory.createMatteBorder(1,1,1,1,c1);
	jLabel_BP.setBorder(Label_border);
	
    }//GEN-LAST:event_jLabel_BPMouseExited

    private void jLabel_SSDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_SSDMouseEntered
        
	Border Label_border = BorderFactory.createMatteBorder(1,1,1,1,Color.white);
	jLabel_SSD.setBorder(Label_border);
	
    }//GEN-LAST:event_jLabel_SSDMouseEntered

    private void jLabel_SSDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_SSDMouseExited
        
	Border Label_border = BorderFactory.createMatteBorder(1,1,1,1,c1);
	jLabel_SSD.setBorder(Label_border);
	
    }//GEN-LAST:event_jLabel_SSDMouseExited

    private void jLabel_ZakazMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ZakazMouseClicked
        zakaz zak = new zakaz();
	
	zak.setVisible(true);
	zak.pack();
	zak.setLocationRelativeTo(null);
	zak.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.dispose();
	
    }//GEN-LAST:event_jLabel_ZakazMouseClicked

    private void jLabel_OutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_OutMouseClicked
        Login log = new Login();
	
	log.setVisible(true);
	log.pack();
	log.setLocationRelativeTo(null);
	log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.dispose();
	
    }//GEN-LAST:event_jLabel_OutMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        JOptionPane.showMessageDialog(null,"Курсовая работа по учебному предмету КПИЯП","Выполнил Ереметько В.И.",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jLabel1MouseClicked

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
	    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new Main().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_BOX;
    private javax.swing.JLabel jLabel_BP;
    private javax.swing.JLabel jLabel_CPU;
    private javax.swing.JLabel jLabel_MB;
    private javax.swing.JLabel jLabel_Out;
    private javax.swing.JLabel jLabel_RAM;
    private javax.swing.JLabel jLabel_SSD;
    public javax.swing.JLabel jLabel_Users;
    private javax.swing.JLabel jLabel_VC;
    public javax.swing.JLabel jLabel_Zakaz;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
