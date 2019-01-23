package lolrandom;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Wernich
 */
public class GUI extends javax.swing.JFrame {

    private boolean owned;
    boolean err1, err2, err3, err4;
    private int champIterator = 0;
    private int ownedChamps = 0, meleeChamps = 0, rangedChamps = 0, adChamps = 0, apChamps = 0, topChamps = 0, jungleChamps = 0, midChamps = 0, supportChamps = 0, adcChamps = 0;
    private Champion c1;
    private Champion c2;
    private Champion c3;
    private Champion c4;
    
    public GUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        try {
            this.setIconImage(ImageIO.read(new File("src\\lolrandom\\res\\SmallImg\\Bard.jpg")));
        } catch (IOException ex) {
            System.err.println("Error creating window icon!");
        }
        LoLRandom.loadingPopup.dispose();
        Dimension size = new Dimension(540, 315);
        this.setSize(new Dimension(555, 363));
        pnlMain.setSize(size);
        pnlMain.setLocation(0, 0);
        pnlChampView.setSize(size);
        pnlMain.setLocation(0, 0);
        updateChampView();
        initScreen();
    }
    
    private void initScreen(){
        LinkedList<Champion> chosenChampions = new LinkedList<Champion>();
        for(int i = 0; i<LoLRandom.champions.size(); i++){
            Champion tempC = LoLRandom.champions.get(i);
            if(tempC.isOwned()){
                chosenChampions.add(LoLRandom.champions.get(i));
                ownedChamps++;
                if(tempC.isMelee()){meleeChamps++;}
                if(tempC.isRanged()){rangedChamps++;}
                if(tempC.isAd()){adChamps++;}
                if(tempC.isAp()){apChamps++;}
                if(tempC.isTop()){topChamps++;}
                if(tempC.isJungle()){jungleChamps++;}
                if(tempC.isMid()){midChamps++;}
                if(tempC.isSupp()){supportChamps++;}
                if(tempC.isAdc()){adcChamps++;}
            }
        }
        
        Random r = new Random();
        Champion tempC;
        if(chosenChampions.size() > 0){
            int rand = r.nextInt(chosenChampions.size());
            tempC = chosenChampions.get(rand);
            chbMelee.setSelected(tempC.isMelee());
            chbRanged.setSelected(tempC.isRanged());
            chbAD.setSelected(tempC.isAd());
            chbAP.setSelected(tempC.isAp());
            chbTop.setSelected(tempC.isTop());
            chbJungle.setSelected(tempC.isJungle());
            chbMid.setSelected(tempC.isMid());
            chbSupport.setSelected(tempC.isSupp());
            chbADC.setSelected(tempC.isAdc());
        }else{
            tempC = LoLRandom.champions.get(0);
            chbMelee.setSelected(false);
            chbRanged.setSelected(false);
            chbAD.setSelected(false);
            chbAP.setSelected(false);
            chbTop.setSelected(false);
            chbJungle.setSelected(false);
            chbMid.setSelected(false);
            chbSupport.setSelected(false);
            chbADC.setSelected(false);
        }
                
        lblName.setText(tempC.getName());
        lblDamageType.setText(damageDetails(tempC));
        lblAttackType.setText(attackDetails(tempC));
        lblLaneType.setText(laneDetails(tempC));
        lblPic.setIcon(new ImageIcon(tempC.getPic()));
        
        setEnabledFields();
    }
    
    private void setEnabledFields(){
        if(meleeChamps == 0){
            chbMelee.setEnabled(false);
            chbMelee.setSelected(false);
        }else{
            chbMelee.setEnabled(true);           
        }
        if(rangedChamps == 0){
            chbRanged.setEnabled(false);
            chbRanged.setSelected(false);
        }else{
            chbRanged.setEnabled(true);
        }
        if(adChamps == 0){
            chbAD.setEnabled(false);
            chbAD.setSelected(false);
        }else{
            chbAD.setEnabled(true);
        }
        if(apChamps == 0){
            chbAP.setEnabled(false);
            chbAP.setSelected(false);
        }else{
            chbAP.setEnabled(true);
        }
        if(topChamps == 0){
            chbTop.setEnabled(false);
            chbTop.setSelected(false);
        }else{
            chbTop.setEnabled(true);
        }
        if(jungleChamps == 0){
            chbJungle.setEnabled(false);
            chbJungle.setSelected(false);
        }else{
            chbJungle.setEnabled(true);
        }
        if(midChamps == 0){
            chbMid.setEnabled(false);
            chbMid.setSelected(false);
        }else{
            chbMid.setEnabled(true);
        }
        if(supportChamps == 0){
            chbSupport.setEnabled(false);
            chbSupport.setSelected(false);
        }else{
            chbSupport.setEnabled(true);
        }
        if(adcChamps == 0){
            chbADC.setEnabled(false);
            chbADC.setSelected(false);
        }else{
            chbADC.setEnabled(true);
        }
    }
    
    private void updateChampAmounts(int champNum, boolean value){
        Champion tempC = LoLRandom.champions.get(champNum);
        if(value){
            if(tempC.isMelee()){meleeChamps++;}
            if(tempC.isRanged()){rangedChamps++;}
            if(tempC.isAd()){adChamps++;}
            if(tempC.isAp()){apChamps++;}
            if(tempC.isTop()){topChamps++;}
            if(tempC.isJungle()){jungleChamps++;}
            if(tempC.isMid()){midChamps++;}
            if(tempC.isSupp()){supportChamps++;}
            if(tempC.isAdc()){adcChamps++;}
        }else{
            if(tempC.isMelee()){meleeChamps--;}
            if(tempC.isRanged()){rangedChamps--;}
            if(tempC.isAd()){adChamps--;}
            if(tempC.isAp()){apChamps--;}
            if(tempC.isTop()){topChamps--;}
            if(tempC.isJungle()){jungleChamps--;}
            if(tempC.isMid()){midChamps--;}
            if(tempC.isSupp()){supportChamps--;}
            if(tempC.isAdc()){adcChamps--;}
        }
        
    }
    
    private void updateChampView(){
        int i = champIterator;
        c1 = LoLRandom.champions.get(i);
        c2 = LoLRandom.champions.get(i+1);
        c3 = LoLRandom.champions.get(i+2);
        c4 = LoLRandom.champions.get(i+3);
               
        lblImg1.setIcon(new ImageIcon(c1.getSmallPic()));
        lblName1.setText(c1.getName());
        lblAtt1.setText(attackDetails(c1));
        lblDmg1.setText(damageDetails(c1));
        lblLane1.setText(laneDetails(c1));
        chbOwned1.setSelected(c1.isOwned());
            
        lblImg2.setIcon(new ImageIcon(c2.getSmallPic()));
        lblName2.setText(c2.getName());
        lblAtt2.setText(attackDetails(c2));
        lblDmg2.setText(damageDetails(c2));
        lblLane2.setText(laneDetails(c2));
        chbOwned2.setSelected(c2.isOwned());
        
        lblImg3.setIcon(new ImageIcon(c3.getSmallPic()));
        lblName3.setText(c3.getName());
        lblAtt3.setText(attackDetails(c3));
        lblDmg3.setText(damageDetails(c3));
        lblLane3.setText(laneDetails(c3));
        chbOwned3.setSelected(c3.isOwned());
        
        lblImg4.setIcon(new ImageIcon(c4.getSmallPic()));
        lblName4.setText(c4.getName());
        lblAtt4.setText(attackDetails(c4));
        lblDmg4.setText(damageDetails(c4));
        lblLane4.setText(laneDetails(c4));
        chbOwned4.setSelected(c4.isOwned());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        tabbedPain = new javax.swing.JTabbedPane();
        pnlMain = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        chbTop = new javax.swing.JCheckBox();
        btnRandomChamp = new javax.swing.JButton();
        chbJungle = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        chbMid = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        chbSupport = new javax.swing.JCheckBox();
        lblAttackType = new javax.swing.JLabel();
        chbADC = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblDamageType = new javax.swing.JLabel();
        chbMelee = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        chbRanged = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        lblLaneType = new javax.swing.JLabel();
        chbAD = new javax.swing.JCheckBox();
        jSeparator3 = new javax.swing.JSeparator();
        chbAP = new javax.swing.JCheckBox();
        lblPic = new javax.swing.JLabel();
        pnlChampView = new javax.swing.JPanel();
        lblImg1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblAtt1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblDmg1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblLane1 = new javax.swing.JLabel();
        chbOwned1 = new javax.swing.JCheckBox();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        lblImg2 = new javax.swing.JLabel();
        chbOwned2 = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        lblAtt3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblDmg3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblLane3 = new javax.swing.JLabel();
        lblImg3 = new javax.swing.JLabel();
        chbOwned3 = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        lblAtt4 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblDmg4 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lblLane4 = new javax.swing.JLabel();
        lblImg4 = new javax.swing.JLabel();
        chbOwned4 = new javax.swing.JCheckBox();
        lblLane2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblDmg2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblAtt2 = new javax.swing.JLabel();
        lblName1 = new javax.swing.JLabel();
        lblName4 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        lblName2 = new javax.swing.JLabel();
        lblName3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LoLRandom");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 394, -1, -1));

        tabbedPain.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        lblName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblName.setText("Name");

        chbTop.setText("Top");
        chbTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbTopActionPerformed(evt);
            }
        });

        btnRandomChamp.setText("Randomise Champion!");
        btnRandomChamp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRandomChampActionPerformed(evt);
            }
        });

        chbJungle.setText("Jungle");
        chbJungle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbJungleActionPerformed(evt);
            }
        });

        chbMid.setText("Mid");
        chbMid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbMidActionPerformed(evt);
            }
        });

        jLabel2.setText("Attack Type:");

        chbSupport.setText("Support");
        chbSupport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbSupportActionPerformed(evt);
            }
        });

        lblAttackType.setText("xxxxx");

        chbADC.setText("ADC");
        chbADC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbADCActionPerformed(evt);
            }
        });

        jLabel4.setText("Damage Type:");

        jLabel1.setText("Select your attributes:");

        lblDamageType.setText("xxxxx");

        chbMelee.setText("Melee");
        chbMelee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbMeleeActionPerformed(evt);
            }
        });

        chbRanged.setText("Ranged");
        chbRanged.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbRangedActionPerformed(evt);
            }
        });

        jLabel6.setText("Lane:");

        lblLaneType.setText("xxxxx");

        chbAD.setText("AD");
        chbAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbADActionPerformed(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        chbAP.setText("AP");
        chbAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbAPActionPerformed(evt);
            }
        });

        lblPic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(btnRandomChamp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chbTop)
                            .addComponent(chbJungle)
                            .addComponent(chbMid)
                            .addComponent(chbSupport)
                            .addComponent(chbADC)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chbMelee)
                                    .addComponent(chbAD))
                                .addGap(18, 18, 18)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chbAP)
                                    .addComponent(chbRanged)))
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPic, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLaneType)
                    .addComponent(jLabel4)
                    .addComponent(lblDamageType)
                    .addComponent(jLabel6)
                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(lblAttackType))
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chbMelee)
                            .addComponent(chbRanged))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chbAD)
                            .addComponent(chbAP))
                        .addGap(18, 18, 18)
                        .addComponent(chbTop)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbJungle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbMid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbSupport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbADC)
                        .addGap(15, 15, 15)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnRandomChamp))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(8, 8, 8)
                        .addComponent(lblAttackType)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDamageType)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLaneType))
                    .addComponent(lblPic, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        tabbedPain.addTab("Champ Select", pnlMain);

        pnlChampView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImg1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlChampView.add(lblImg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 60, 60));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        pnlChampView.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 10, 151, 26));

        btnSearch.setText("Search:");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        pnlChampView.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 10, -1, -1));

        jLabel3.setText("Attack:");
        pnlChampView.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        lblAtt1.setText("xxxxx");
        pnlChampView.add(lblAtt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 90, -1));

        jLabel7.setText("Damage:");
        pnlChampView.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        lblDmg1.setText("xxxxx");
        pnlChampView.add(lblDmg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 59, -1));
        pnlChampView.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 45, 105, -1));

        jLabel9.setText("Lane:");
        pnlChampView.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        lblLane1.setText("Top, Jungle, Mid, Support, ADC");
        pnlChampView.add(lblLane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, -1));

        chbOwned1.setText("Owned:");
        chbOwned1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chbOwned1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbOwned1ActionPerformed(evt);
            }
        });
        pnlChampView.add(chbOwned1, new org.netbeans.lib.awtextra.AbsoluteConstraints(447, 70, -1, -1));

        btnPrev.setText("<---");
        btnPrev.setEnabled(false);
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        pnlChampView.add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnNext.setText("--->");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlChampView.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 10, -1, -1));
        pnlChampView.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 237, 510, 10));

        lblImg2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlChampView.add(lblImg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 60, 60));

        chbOwned2.setText("Owned:");
        chbOwned2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chbOwned2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbOwned2ActionPerformed(evt);
            }
        });
        pnlChampView.add(chbOwned2, new org.netbeans.lib.awtextra.AbsoluteConstraints(447, 135, -1, -1));

        jLabel18.setText("Attack:");
        pnlChampView.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, -1));

        lblAtt3.setText("Melee, Ranged");
        pnlChampView.add(lblAtt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 90, -1));

        jLabel20.setText("Damage:");
        pnlChampView.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        lblDmg3.setText("xxxxx");
        pnlChampView.add(lblDmg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 59, -1));

        jLabel22.setText("Lane:");
        pnlChampView.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, -1, -1));

        lblLane3.setText("Top, Jungle, Mid, Support, ADC");
        pnlChampView.add(lblLane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, -1, -1));

        lblImg3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlChampView.add(lblImg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 175, 60, 60));

        chbOwned3.setText("Owned:");
        chbOwned3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chbOwned3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbOwned3ActionPerformed(evt);
            }
        });
        pnlChampView.add(chbOwned3, new org.netbeans.lib.awtextra.AbsoluteConstraints(447, 200, -1, -1));

        jLabel24.setText("Attack:");
        pnlChampView.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 265, -1, -1));

        lblAtt4.setText("xxxxx");
        pnlChampView.add(lblAtt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 285, 90, -1));

        jLabel26.setText("Damage:");
        pnlChampView.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 265, -1, -1));

        lblDmg4.setText("xxxxx");
        pnlChampView.add(lblDmg4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 285, 59, -1));

        jLabel28.setText("Lane:");
        pnlChampView.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 265, -1, -1));

        lblLane4.setText("Top, Jungle, Mid, Support, ADC");
        pnlChampView.add(lblLane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 285, -1, -1));

        lblImg4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlChampView.add(lblImg4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 60, 60));

        chbOwned4.setText("Owned:");
        chbOwned4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chbOwned4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbOwned4ActionPerformed(evt);
            }
        });
        pnlChampView.add(chbOwned4, new org.netbeans.lib.awtextra.AbsoluteConstraints(447, 265, -1, -1));

        lblLane2.setText("Top, Jungle, Mid, Support, ADC");
        pnlChampView.add(lblLane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 155, -1, -1));

        jLabel16.setText("Lane:");
        pnlChampView.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 135, -1, -1));

        jLabel14.setText("Damage:");
        pnlChampView.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 135, -1, -1));

        lblDmg2.setText("xxxxx");
        pnlChampView.add(lblDmg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 155, 59, -1));

        jLabel12.setText("Attack:");
        pnlChampView.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 135, -1, -1));

        lblAtt2.setText("xxxxx");
        pnlChampView.add(lblAtt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 155, 90, -1));

        lblName1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblName1.setText("jLabel5");
        pnlChampView.add(lblName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 45, 170, 20));

        lblName4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblName4.setText("jLabel8");
        pnlChampView.add(lblName4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 170, 20));
        pnlChampView.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 510, 10));
        pnlChampView.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 107, 510, 10));
        pnlChampView.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 172, 510, 10));

        lblName2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblName2.setText("jLabel8");
        pnlChampView.add(lblName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 170, 20));

        lblName3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblName3.setText("jLabel8");
        pnlChampView.add(lblName3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 175, 170, 20));

        tabbedPain.addTab("Champ View", pnlChampView);

        getContentPane().add(tabbedPain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 539, 335));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chbMeleeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbMeleeActionPerformed
        if(err4){
            chbAD.setForeground(Color.BLACK);
            chbAP.setForeground(Color.BLACK);
            chbTop.setForeground(Color.BLACK);
            chbJungle.setForeground(Color.BLACK);
            chbMid.setForeground(Color.BLACK);
            chbSupport.setForeground(Color.BLACK);
            chbADC.setForeground(Color.BLACK);
        }
        chbMelee.setForeground(Color.BLACK);
        chbRanged.setForeground(Color.BLACK);
    }//GEN-LAST:event_chbMeleeActionPerformed

    private void chbRangedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbRangedActionPerformed
        if(err4){
            chbAD.setForeground(Color.BLACK);
            chbAP.setForeground(Color.BLACK);
            chbTop.setForeground(Color.BLACK);
            chbJungle.setForeground(Color.BLACK);
            chbMid.setForeground(Color.BLACK);
            chbSupport.setForeground(Color.BLACK);
            chbADC.setForeground(Color.BLACK);
        }
        chbMelee.setForeground(Color.BLACK);
        chbRanged.setForeground(Color.BLACK);
    }//GEN-LAST:event_chbRangedActionPerformed

    private void chbADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbADActionPerformed
        if(err4){
            chbMelee.setForeground(Color.BLACK);
            chbRanged.setForeground(Color.BLACK);
            chbTop.setForeground(Color.BLACK);
            chbJungle.setForeground(Color.BLACK);
            chbMid.setForeground(Color.BLACK);
            chbSupport.setForeground(Color.BLACK);
            chbADC.setForeground(Color.BLACK);
        }
        chbAD.setForeground(Color.BLACK);
        chbAP.setForeground(Color.BLACK);
    }//GEN-LAST:event_chbADActionPerformed

    private void chbAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbAPActionPerformed
        if(err4){
            chbMelee.setForeground(Color.BLACK);
            chbRanged.setForeground(Color.BLACK);
            chbTop.setForeground(Color.BLACK);
            chbJungle.setForeground(Color.BLACK);
            chbMid.setForeground(Color.BLACK);
            chbSupport.setForeground(Color.BLACK);
            chbADC.setForeground(Color.BLACK);
        }
        chbAD.setForeground(Color.BLACK);
        chbAP.setForeground(Color.BLACK);
    }//GEN-LAST:event_chbAPActionPerformed

    private void chbTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbTopActionPerformed
        if(err4){
            chbMelee.setForeground(Color.BLACK);
            chbRanged.setForeground(Color.BLACK);
            chbAD.setForeground(Color.BLACK);
            chbAP.setForeground(Color.BLACK);
        }
        chbTop.setForeground(Color.BLACK);
        chbJungle.setForeground(Color.BLACK);
        chbMid.setForeground(Color.BLACK);
        chbSupport.setForeground(Color.BLACK);
        chbADC.setForeground(Color.BLACK);
    }//GEN-LAST:event_chbTopActionPerformed

    private void chbJungleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbJungleActionPerformed
        if(err4){
            chbMelee.setForeground(Color.BLACK);
            chbRanged.setForeground(Color.BLACK);
            chbAD.setForeground(Color.BLACK);
            chbAP.setForeground(Color.BLACK);
        }
        chbTop.setForeground(Color.BLACK);
        chbJungle.setForeground(Color.BLACK);
        chbMid.setForeground(Color.BLACK);
        chbSupport.setForeground(Color.BLACK);
        chbADC.setForeground(Color.BLACK);
    }//GEN-LAST:event_chbJungleActionPerformed

    private void chbMidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbMidActionPerformed
        if(err4){
            chbMelee.setForeground(Color.BLACK);
            chbRanged.setForeground(Color.BLACK);
            chbAD.setForeground(Color.BLACK);
            chbAP.setForeground(Color.BLACK);
        }
        chbTop.setForeground(Color.BLACK);
        chbJungle.setForeground(Color.BLACK);
        chbMid.setForeground(Color.BLACK);
        chbSupport.setForeground(Color.BLACK);
        chbADC.setForeground(Color.BLACK);
    }//GEN-LAST:event_chbMidActionPerformed

    private void chbSupportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbSupportActionPerformed
        if(err4){
            chbMelee.setForeground(Color.BLACK);
            chbRanged.setForeground(Color.BLACK);
            chbAD.setForeground(Color.BLACK);
            chbAP.setForeground(Color.BLACK);
        }
        chbTop.setForeground(Color.BLACK);
        chbJungle.setForeground(Color.BLACK);
        chbMid.setForeground(Color.BLACK);
        chbSupport.setForeground(Color.BLACK);
        chbADC.setForeground(Color.BLACK);
    }//GEN-LAST:event_chbSupportActionPerformed

    private void chbADCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbADCActionPerformed
        if(err4){
            chbMelee.setForeground(Color.BLACK);
            chbRanged.setForeground(Color.BLACK);
            chbAD.setForeground(Color.BLACK);
            chbAP.setForeground(Color.BLACK);
        }
        chbTop.setForeground(Color.BLACK);
        chbJungle.setForeground(Color.BLACK);
        chbMid.setForeground(Color.BLACK);
        chbSupport.setForeground(Color.BLACK);
        chbADC.setForeground(Color.BLACK);
    }//GEN-LAST:event_chbADCActionPerformed

    private void btnRandomChampActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRandomChampActionPerformed
        LinkedList<Champion> chosenChampions = new LinkedList<Champion>();
        Champion tempC;
        for(int i = 0; i<LoLRandom.champions.size(); i++){
            tempC = LoLRandom.champions.get(i);
            
            if(tempC.isOwned()){
                if(chbMelee.isSelected() && tempC.isMelee() || chbRanged.isSelected() && tempC.isRanged()){
                    if(chbAD.isSelected() && tempC.isAd() || chbAP.isSelected() && tempC.isAp()){
                        if(chbTop.isSelected() && tempC.isTop() || chbJungle.isSelected() && tempC.isJungle() || chbMid.isSelected() && tempC.isMid() || chbSupport.isSelected() && tempC.isSupp() || chbADC.isSelected() && tempC.isAdc()){
                            chosenChampions.add(tempC);
                        }
                    }               
                }
            }
        }

        if(chosenChampions.size() > 1){
            Random r = new Random();
            int rand = r.nextInt(chosenChampions.size());

            tempC = chosenChampions.get(rand);
            lblName.setText(tempC.getName());
            lblPic.setIcon(new ImageIcon(tempC.getPic()));
            lblAttackType.setText(attackDetails(tempC));
            lblDamageType.setText(damageDetails(tempC));
            lblLaneType.setText(laneDetails(tempC));
            
            chbMelee.setForeground(Color.BLACK);
            chbRanged.setForeground(Color.BLACK);
            chbAD.setForeground(Color.BLACK);
            chbAP.setForeground(Color.BLACK);
            chbTop.setForeground(Color.BLACK);
            chbJungle.setForeground(Color.BLACK);
            chbMid.setForeground(Color.BLACK);
            chbSupport.setForeground(Color.BLACK);
            chbADC.setForeground(Color.BLACK);
        }else if(chosenChampions.size() == 1){
            tempC = chosenChampions.get(0);
            lblName.setText(tempC.getName());
            lblPic.setIcon(new ImageIcon(tempC.getPic()));
            lblAttackType.setText(attackDetails(tempC));
            lblDamageType.setText(damageDetails(tempC));
            lblLaneType.setText(laneDetails(tempC));
            
            chbMelee.setForeground(Color.BLACK);
            chbRanged.setForeground(Color.BLACK);
            chbAD.setForeground(Color.BLACK);
            chbAP.setForeground(Color.BLACK);
            chbTop.setForeground(Color.BLACK);
            chbJungle.setForeground(Color.BLACK);
            chbMid.setForeground(Color.BLACK);
            chbSupport.setForeground(Color.BLACK);
            chbADC.setForeground(Color.BLACK);
        }
        else{
            if(ownedChamps == 0){
                JOptionPane.showMessageDialog(null, "It seems you do not own any Champions.\nGo to the \"Champ View\" tab and select the Champions that you own.");
            }else{
                err1 = false;
                err2 = false;
                err3 = false;
                err4 = false;
                if(!chbMelee.isSelected() && !chbRanged.isSelected()){
                    chbMelee.setForeground(Color.RED);
                    chbRanged.setForeground(Color.RED);
                    err1 = true;
                }else{
                    chbMelee.setForeground(Color.BLACK);
                    chbRanged.setForeground(Color.BLACK);
                }
                if(!chbAD.isSelected() && !chbAP.isSelected()){
                    chbAD.setForeground(Color.RED);
                    chbAP.setForeground(Color.RED);
                    err2 = true;
                }else{
                    chbAD.setForeground(Color.BLACK);
                    chbAP.setForeground(Color.BLACK);
                }
                if(!chbTop.isSelected() && !chbJungle.isSelected() && !chbMid.isSelected() && !chbSupport.isSelected() && !chbADC.isSelected()){
                    chbTop.setForeground(Color.RED);
                    chbJungle.setForeground(Color.RED);
                    chbMid.setForeground(Color.RED);
                    chbSupport.setForeground(Color.RED);
                    chbADC.setForeground(Color.RED);
                    err3 = true;
                }else{
                    chbTop.setForeground(Color.BLACK);
                    chbJungle.setForeground(Color.BLACK);
                    chbMid.setForeground(Color.BLACK);
                    chbSupport.setForeground(Color.BLACK);
                    chbADC.setForeground(Color.BLACK);
                }
                if(!err1 && !err2 && !err3){
                    err4 = true;
                    chbMelee.setForeground(Color.RED);
                    chbRanged.setForeground(Color.RED);
                    chbAD.setForeground(Color.RED);
                    chbAP.setForeground(Color.RED);
                    chbTop.setForeground(Color.RED);
                    chbJungle.setForeground(Color.RED);
                    chbMid.setForeground(Color.RED);
                    chbSupport.setForeground(Color.RED);
                    chbADC.setForeground(Color.RED);
                }
            }
        }
    }//GEN-LAST:event_btnRandomChampActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        int size = LoLRandom.champions.size();
        btnPrev.setEnabled(true);
        if(champIterator < size-4){
            champIterator++;
            updateChampView();  
            if(champIterator == size-4){
                btnNext.setEnabled(false);
            }
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        int size = LoLRandom.champions.size();
        btnNext.setEnabled(true);
        if(champIterator > 0){
            champIterator--;
            updateChampView();  
            if(champIterator == 0){
                btnPrev.setEnabled(false);
            }
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
       search();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        int key = evt.getKeyCode();
        if(key == KeyEvent.VK_ENTER){
            search();
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void chbOwned1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbOwned1ActionPerformed
        boolean value = chbOwned1.isSelected();
        updateOwned(champIterator, value);
        if(value){
            ownedChamps++;
        }else{
            ownedChamps--;
        }
        updateChampAmounts(champIterator, value);
        setEnabledFields();
    }//GEN-LAST:event_chbOwned1ActionPerformed

    private void chbOwned2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbOwned2ActionPerformed
        boolean value = chbOwned2.isSelected();
        updateOwned(champIterator+1, value);
        if(value){
            ownedChamps++;
        }else{
            ownedChamps--;
        }
        updateChampAmounts(champIterator+1, value);
        setEnabledFields();
    }//GEN-LAST:event_chbOwned2ActionPerformed

    private void chbOwned3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbOwned3ActionPerformed
        boolean value = chbOwned3.isSelected();
        updateOwned(champIterator+2, value);
        if(value){
            ownedChamps++;
        }else{
            ownedChamps--;
        }
        updateChampAmounts(champIterator+2, value);
        setEnabledFields();
    }//GEN-LAST:event_chbOwned3ActionPerformed

    private void chbOwned4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbOwned4ActionPerformed
        boolean value = chbOwned4.isSelected();
        updateOwned(champIterator+3, value);
        if(value){
            ownedChamps++;
        }else{
            ownedChamps--;
        }
        updateChampAmounts(champIterator+3, value);
        setEnabledFields();
    }//GEN-LAST:event_chbOwned4ActionPerformed

    private void updateOwned(int champNum, boolean value){
        Scanner scFile = null;     
        String newText = "";
        try {
            scFile = new Scanner(new File("..\\res\\championData.txt"));
        } catch (Exception e) {
            System.err.println("The champion data file was not found!");
            System.exit(1);
        }
        newText = scFile.nextLine()+"\n";
        for(int i = 0; i<LoLRandom.champions.size(); i++){
            if(i != champNum){
                newText += scFile.nextLine() + "\n";
            }else if (i == champNum){
                String temp = scFile.nextLine();
                if(value){
                    temp = temp.substring(0, temp.length()-5);
                    temp += "true";
                    LoLRandom.champions.get(champNum).setOwned(true);
                }else{
                    temp = temp.substring(0, temp.length()-4);
                    temp += "false";
                    LoLRandom.champions.get(champNum).setOwned(false);
                }
                newText += temp + "\n";
            }           
        }

        try {
            FileWriter fw = new FileWriter(new File("..//res//championData.txt"));
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(newText);
            writer.flush();
            writer.close();
            fw.close();
        } catch (IOException ex) {
            System.err.println("Error while attempting to write file!");
        }       
    }
    
    private void search(){
        String searchName = txtSearch.getText().replaceAll(" ", "").replaceAll("\'", "").replaceAll("\\.", "").replaceAll(",", "").replaceAll("\"", "").trim();
        if(searchName.length() > 0){
            for(int i = 0; i<LoLRandom.champions.size(); i++){
                String compareValue = LoLRandom.champions.get(i).getName().replaceAll(" ", "").replaceAll("\'", "").replaceAll("\\.", "").replaceAll(",", "").replaceAll("\"", "").trim();
                if(compareValue.length() >= searchName.length()){
                    compareValue = compareValue.substring(0, searchName.length());
                    if(compareValue.equalsIgnoreCase(searchName)){
                        if(i < LoLRandom.champions.size()-3){
                            champIterator = i;
                        }else{
                            champIterator = LoLRandom.champions.size() -4;
                        }

                        if(i == 0){
                            btnPrev.setEnabled(false);
                            btnNext.setEnabled(true);
                        }else if(i < LoLRandom.champions.size()-4){
                            btnPrev.setEnabled(true);
                            btnNext.setEnabled(true);
                        }else if(i < LoLRandom.champions.size()-3){
                            btnPrev.setEnabled(true);
                            btnNext.setEnabled(false);
                        }else{
                            btnNext.setEnabled(false);
                            btnPrev.setEnabled(true);
                        }
                        updateChampView();
                        txtSearch.setText("");
                        break;
                    }
                }           
            }
        }   
    }
    
    private void setDetailLabels(Champion tempC){
        String text = lblAttackType.getText();
        if(tempC.isMelee()){
            text += "Melee, ";
        }
        if(tempC.isRanged()){
            text += "Ranged, ";
        }
        lblAttackType.setText(text.substring(0, text.length()-2));
        
        text = "";
        if(tempC.isAd()){
            text += "AD, ";
        }
        if(tempC.isAp()){
            text += "AP, ";
        }
        lblDamageType.setText(text.substring(0, text.length()-2));
        
        text = "";
        if(tempC.isTop()){
            text += "Top, ";
        }
        if(tempC.isJungle()){
            text += "Jungle, ";
        }
        if(tempC.isMid()){
            text += "Mid, ";
        }
        if(tempC.isSupp()){
            text += "Support, ";
        }
        if(tempC.isAdc()){
            text += "ADC, ";
        }
        lblLaneType.setText(text.substring(0, text.length()-2));
    }
    
    private String attackDetails(Champion tempC){
        String text = "";
        if(tempC.isMelee()){
            text += "Melee, ";
        }
        if(tempC.isRanged()){
            text += "Ranged, ";
        }
        text = text.substring(0, text.length()-2);
        return text;
    }
    
    private String damageDetails(Champion tempC){
        String text = "";
        if(tempC.isAd()){
            text += "AD, ";
        }
        if(tempC.isAp()){
            text += "AP, ";
        }
        text = text.substring(0, text.length()-2);
        return text;
    }
    
    private String laneDetails(Champion tempC){
         String text = "";
         if(tempC.isTop()){
            text += "Top, ";
        }
        if(tempC.isJungle()){
            text += "Jungle, ";
        }
        if(tempC.isMid()){
            text += "Mid, ";
        }
        if(tempC.isSupp()){
            text += "Support, ";
        }
        if(tempC.isAdc()){
            text += "ADC, ";
        }
        text = text.substring(0, text.length()-2);
        return text;
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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnRandomChamp;
    private javax.swing.JButton btnSearch;
    private javax.swing.JCheckBox chbAD;
    private javax.swing.JCheckBox chbADC;
    private javax.swing.JCheckBox chbAP;
    private javax.swing.JCheckBox chbJungle;
    private javax.swing.JCheckBox chbMelee;
    private javax.swing.JCheckBox chbMid;
    private javax.swing.JCheckBox chbOwned1;
    private javax.swing.JCheckBox chbOwned2;
    private javax.swing.JCheckBox chbOwned3;
    private javax.swing.JCheckBox chbOwned4;
    private javax.swing.JCheckBox chbRanged;
    private javax.swing.JCheckBox chbSupport;
    private javax.swing.JCheckBox chbTop;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lblAtt1;
    private javax.swing.JLabel lblAtt2;
    private javax.swing.JLabel lblAtt3;
    private javax.swing.JLabel lblAtt4;
    private javax.swing.JLabel lblAttackType;
    private javax.swing.JLabel lblDamageType;
    private javax.swing.JLabel lblDmg1;
    private javax.swing.JLabel lblDmg2;
    private javax.swing.JLabel lblDmg3;
    private javax.swing.JLabel lblDmg4;
    private javax.swing.JLabel lblImg1;
    private javax.swing.JLabel lblImg2;
    private javax.swing.JLabel lblImg3;
    private javax.swing.JLabel lblImg4;
    private javax.swing.JLabel lblLane1;
    private javax.swing.JLabel lblLane2;
    private javax.swing.JLabel lblLane3;
    private javax.swing.JLabel lblLane4;
    private javax.swing.JLabel lblLaneType;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblName2;
    private javax.swing.JLabel lblName3;
    private javax.swing.JLabel lblName4;
    private javax.swing.JLabel lblPic;
    private javax.swing.JPanel pnlChampView;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTabbedPane tabbedPain;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
