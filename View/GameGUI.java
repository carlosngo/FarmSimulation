package View;

import Controller.*;
import Model.Inventory;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.LineBorder;
import sun.audio.*;


public class GameGUI extends JFrame implements ActionListener, MouseListener {
    public static final int MAX_ROW = 10;
    public static final int MAX_COL = 5;
    public final static int ROCKY = 0;
    public final static int UNPLOWED = 1;
    public final static int PLOWED = 2;
    public final static int PLANTED = 3;
    public final static int READY_TO_HARVEST = 4;
    public final static int WITHERED = 5;
    private JButton[][] tileButtons;
    private JLabel nameLabel, level, type, exp, money, selected;
    private JTextArea description, log;
    private JButton mainmenu, register, watercan, plow, pickaxe, fertilizer, buyFertilizer, seeds, help;
    private JPanel pVeggie, pFlower, pTree, seedmenuMotherPnl;
    private GameGUIController controller;
    private HashMap<String, BufferedImage> plantImages = new HashMap<>();
    private ArrayList<SeedPanel> seedPanels;

    public GameGUI(GameGUIController controller) {
        this.controller = controller;
        plantImages = new HashMap<>();
        tileButtons = new JButton[MAX_ROW][MAX_COL];
        seedPanels = new ArrayList<>();
        initGameGUI();
        /*
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;
        ContinuousAudioDataStream loop = null;
        
        try{
            BGM = new AudioStream(new FileInputStream("Pineapple Overture.mp3"));
            MD = BGM.getData();
            loop = new ContinuousAudioDataStream(MD);
        }
        catch(IOException e){
            System.out.println("Unable to load audio.");
        }
        MGP.start(loop);
        */
    }

    public void setNameLabel(String name) {
        nameLabel.setText("Name: " + name);
     
    }

    public void setLevel(int lvl) {
        level.setText("Level: " + lvl);
     
    }

    public void setExp(int exp) {
        this.exp.setText("EXP: " + exp + " / " + ((exp / 500 + 1) * 500));
    }
    
    public void setType(String type) {
        this.type.setText("Type: " + type);
     
    }

    public void setMoney(double money) {
        this.money.setText("Money: " + money);
        this.money.setFont(new Font("Arial", Font.PLAIN, 30));
    }
    
    public void setDescription(String description) {
        this.description.setText(description);
    }
    
    public void setSelected(String s) {
        selected.setText("Selected: " + s);
        selected.setFont(new Font("Arial", Font.PLAIN, 30));
    }
    
    public ArrayList<SeedPanel> getSeedPanels() {
        return seedPanels;
    }
    
    public JButton[][] getTileButtons() {
        return tileButtons;
    }

    public JPanel getpVeggie() {
        return pVeggie;
    }

    public JPanel getpFlower() {
        return pFlower;
    }

    public JPanel getpTree() {
        return pTree;
    }
    
    public JPanel getSeedMenu() {
        return seedmenuMotherPnl;
    }
    
    public JTextArea getLog() {
        return log;
    }
    
    public HashMap<String, BufferedImage> getPlantImages() {
        return plantImages;
        
    }
    public void initGameGUI(){
        JPanel motherPnl = new JPanel();
        motherPnl.setLayout(new OverlayLayout(motherPnl));
        
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints c = new GridBagConstraints(); 
        
        JPanel leftPanel = new JPanel(new GridBagLayout());
        //leftPanel.setBackground( new Color(241, 241, 241, 50) );
        leftPanel.setOpaque(false);
        JLabel title = new JLabel("MyFarm");
        title.setFont(new Font("Abril Fatface", Font.BOLD, 55));
        //Border border = BorderFactory.createLineBorder(Color.BLUE, 5);    title.setBorder(border);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(title,c);
        
        nameLabel = new JLabel("Name:  ");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        //Border border1 = BorderFactory.createLineBorder(Color.BLUE, 1);   name.setBorder(border1);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(nameLabel,c);
        
        level = new JLabel("Level: ");
        level.setFont(new Font("Arial", Font.PLAIN, 30));
        //Border border2 = BorderFactory.createLineBorder(Color.BLUE, 1);   level.setBorder(border2);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(level,c);
        
        register = new JButton();
        register.setIcon(new ImageIcon(resizeImage("register.png",60,45)));
        register.setActionCommand("Register");
        register.setToolTipText("Register");
        
        //register.setFont(new Font("Arial", Font.PLAIN, 30));
        register.setBackground(new Color(255,255,0));
        //register.setOpaque(false);
        register.setContentAreaFilled(false);
        register.setBorderPainted(false);
        //register.setBorder(BorderFactory.createLineBorder(new Color(0,78,56),1));
        register.addActionListener(this);
        register.addMouseListener(this);
        //Border border5 = BorderFactory.createLineBorder(Color.BLUE, 1);   mainmenu.setBorder(border5);
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(register,c);
        
        exp = new JLabel("Exp: ");
        exp.setFont(new Font("Arial", Font.PLAIN, 30));
        //Border border3 = BorderFactory.createLineBorder(Color.BLUE, 1);   type.setBorder(border3);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(exp,c);
        
        type = new JLabel("Type: ");
        type.setFont(new Font("Arial", Font.PLAIN, 30));
        //Border border3 = BorderFactory.createLineBorder(Color.BLUE, 1);   type.setBorder(border3);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(type,c);
        
        money = new JLabel("Money: ");
        money.setFont(new Font("Arial", Font.PLAIN, 30));
        //Border border4 = BorderFactory.createLineBorder(Color.BLUE, 1);   money.setBorder(border4);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(money,c);
        
        selected = new JLabel("Selected: none");
        selected.setFont(new Font("Arial", Font.PLAIN, 30));
        //Border border4 = BorderFactory.createLineBorder(Color.BLUE, 1);   money.setBorder(border4);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(selected,c);
        /*
        register = new JButton("Register");
        register.setFont(new Font("Arial", Font.PLAIN, 30));
        register.setBackground(new Color(152,251,152));
        register.setOpaque(true);
        //register.setBorderPainted(false);
        register.setBorder(BorderFactory.createLineBorder(new Color(0,78,56),1));
        register.addActionListener(this);
        //Border border5 = BorderFactory.createLineBorder(Color.BLUE, 1);   mainmenu.setBorder(border5);
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(register,c);
        */
        JLabel inventory = new JLabel("Inventory: ");
        inventory.setFont(new Font("Arial", Font.PLAIN, 35));
        //Border border6 = BorderFactory.createLineBorder(Color.BLUE, 1);   inventory.setBorder(border6);
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(inventory,c);
        
        watercan = new JButton(); //"Watering Can"
        //watercan.setFont(new Font("Marker Felt", Font.PLAIN, 30));
        watercan.setBackground(new Color(255,255,0));
        watercan.setContentAreaFilled(false);
        watercan.setToolTipText("Watering Can");
        watercan.setOpaque(false);
        watercan.setBorderPainted(false);
        watercan.setIcon(new ImageIcon(resizeImage("watering can.png",80,65)));
        //watercan.setHorizontalAlignment(SwingConstants.LEFT);
        watercan.setActionCommand("Watering Can");
        watercan.addActionListener(this);
        watercan.addMouseListener(this);
        //Border border6 = BorderFactory.createLineBorder(Color.BLUE, 1);   watercan.setBorder(border6);
        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(watercan,c);
        
        plow = new JButton(); //"Plow"
        //plow.setFont(new Font("Marker Felt", Font.PLAIN, 30));
        plow.setBackground(new Color(255,255,0));
        plow.setContentAreaFilled(false);
        plow.setOpaque(false);
        plow.setBorderPainted(false);
        plow.setToolTipText("Plow");
        //plow.setBorder(BorderFactory.createLineBorder(new Color(0,78,56),1));
        plow.setIcon(new ImageIcon(resizeImage("plow.png",80,65)));
        plow.setHorizontalAlignment(SwingConstants.LEFT);
        plow.setActionCommand("Plow");
        
        plow.addActionListener(this);
        plow.addMouseListener(this);
        //Border border7 = BorderFactory.createLineBorder(Color.ORANGE, 1);   plow.setBorder(border7);
        c.gridx = 1;
        c.gridy = 9;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(plow,c);
        
        pickaxe = new JButton(); //"Pickaxe"
        //pickaxe.setFont(new Font("Marker Felt", Font.PLAIN, 30));
        pickaxe.setBackground(new Color(255,255,0));
        pickaxe.setContentAreaFilled(false);
        pickaxe.setOpaque(false);
        pickaxe.setBorderPainted(false);
        pickaxe.setToolTipText("Pickaxe");
        //pickaxe.setBorder(BorderFactory.createLineBorder(new Color(0,78,56),1));
        pickaxe.setIcon(new ImageIcon(resizeImage("pickaxe.png",80,65)));
        pickaxe.setActionCommand("Pickaxe");
        //pickaxe.setHorizontalAlignment(SwingConstants.LEFT);
        pickaxe.addActionListener(this);
        pickaxe.addMouseListener(this);
        //Border border8 = BorderFactory.createLineBorder(Color.GRAY, 1);   pickaxe.setBorder(border8);
        c.gridx = 2;
        c.gridy = 9;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(pickaxe,c);
        
        
        fertilizer = new JButton(); //"Fertilizer"
        fertilizer.setActionCommand("Fertilizer");
        fertilizer.setToolTipText("Fertilizer");
        //fertilizer.setFont(new Font("Marker Felt", Font.PLAIN, 30));
        fertilizer.setBackground(new Color(255,255,0));
        fertilizer.setContentAreaFilled(false);
        fertilizer.setOpaque(false);
        fertilizer.setBorderPainted(false);
        //fertilizer.setBorder(BorderFactory.createLineBorder(new Color(0,78,56),1));
        fertilizer.setIcon(new ImageIcon(resizeImage("fertilizer.png",80,65)));
        //fertilizer.setHorizontalAlignment(SwingConstants.LEFT);
        fertilizer.addActionListener(this);
        fertilizer.addMouseListener(this);
        //Border border9 = BorderFactory.createLineBorder(Color.GREEN, 1);   fertilizer.setBorder(border9);
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(fertilizer,c);
        
        buyFertilizer = new JButton(); //"Buy"
        //buyFertilizer.setFont(new Font("Marker Felt", Font.PLAIN, 30));
        buyFertilizer.setBackground(new Color(255,255,0));
        buyFertilizer.setContentAreaFilled(false);
        buyFertilizer.setOpaque(false);
        buyFertilizer.setActionCommand("Buy Fertilizer");
        buyFertilizer.setBorderPainted(false);
        buyFertilizer.setToolTipText("Buy Fertilizer");
        //buyFertilizer.setBorder(BorderFactory.createLineBorder(new Color(0,78,56),1));
        buyFertilizer.setIcon(new ImageIcon(resizeImage("add fertilizer.png",80,65)));
        //buyFertilizer.setHorizontalAlignment(SwingConstants.LEFT);
        buyFertilizer.addActionListener(this);
        buyFertilizer.addMouseListener(this);
        //Border border9 = BorderFactory.createLineBorder(Color.GREEN, 1);   fertilizer.setBorder(border9);
        c.gridx = 1;
        c.gridy = 10;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(buyFertilizer,c);
        
        seeds = new JButton(); //"Seeds"
        //seeds.setFont(new Font("Marker Felt", Font.BOLD, 30));
        seeds.setBackground(new Color(255,255,0));
        seeds.setContentAreaFilled(false);
        seeds.setBorderPainted(false);
        seeds.setActionCommand("View Seeds");
        seeds.setOpaque(false);
        seeds.setActionCommand("View Seeds");
        seeds.setToolTipText("View Seeds");
        //seeds.setBorderPainted(false);
        //seeds.setBorder(BorderFactory.createLineBorder(new Color(0,78,56),1));
        seeds.setIcon(new ImageIcon(resizeImage("seeds.png",80,65)));
        seeds.setHorizontalAlignment(SwingConstants.LEFT);
        seeds.addActionListener(this);
        seeds.addMouseListener(this);
        //Border border10 = BorderFactory.createLineBorder(Color.BLUE, 1);  seeds.setBorder(border10);
        c.gridx = 2;
        c.gridy = 10;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(seeds,c);
        /*
        help = new JButton("Help");
        help.setFont(new Font("Marker Felt", Font.BOLD, 30));
        help.setBackground(new Color(152,251,152));
        help.setOpaque(true);
        //help.setBorderPainted(false);
        help.setBorder(BorderFactory.createLineBorder(new Color(0,78,56),1));
        help.setIcon(new ImageIcon(resizeImage("help.png",50,35)));
        help.setHorizontalAlignment(SwingConstants.LEFT);
        help.addActionListener(this);
        //Border border10 = BorderFactory.createLineBorder(Color.BLUE, 1);  seeds.setBorder(border10);
        c.gridx = 0;
        c.gridy = 14;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(help,c);
        
        mainmenu = new JButton(); //"EXIT GAME"
        mainmenu.setFont(new Font("Arial", Font.PLAIN, 30));
        mainmenu.setIcon(new ImageIcon(resizeImage("exit door.png",70,55)));
        mainmenu.setBackground(new Color(152,251,152));
        mainmenu.setOpaque(true);
        //mainmenu.setBorderPainted(false);
        mainmenu.setBorder(BorderFactory.createLineBorder(new Color(0,78,56),1));
        mainmenu.addActionListener(this);
        //Border border5 = BorderFactory.createLineBorder(Color.BLUE, 1);   mainmenu.setBorder(border5);
        c.gridx = 0;
        c.gridy = 11;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(mainmenu,c);
        */
        
        
        seedmenuMotherPnl = new JPanel();
        seedmenuMotherPnl.setLayout(new OverlayLayout(seedmenuMotherPnl));
        
        JPanel seedmenu = new JPanel();
        //seedmenu.setBackground(new Color(0,128,129));
        seedmenu.setOpaque(false);
        seedmenu.setLayout(new BoxLayout(seedmenu, BoxLayout.Y_AXIS));
        Border round3 = new LineBorder(new Color(0,78,56),10,true);
        seedmenu.setBorder(round3);
        seedmenu.setPreferredSize(new Dimension(315, 270));
        
        JPanel seedMenuTitlePanel = new JPanel();
        seedMenuTitlePanel.setLayout(new BoxLayout(seedMenuTitlePanel, BoxLayout.X_AXIS));
        seedMenuTitlePanel.setOpaque(false);
        seedMenuTitlePanel.add(Box.createRigidArea(new Dimension(90, 0)));
        JLabel seedmenuLabel = new JLabel("Seeds");
        seedmenuLabel.setFont(new Font("Arial", Font.BOLD, 26));
        seedmenuLabel.setForeground(new Color(208,240,192));
        //seedmenuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        seedMenuTitlePanel.add(seedmenuLabel); //, BorderLayout.CENTER
        seedMenuTitlePanel.add(Box.createRigidArea(new Dimension(45, 0)));
        JButton help = new JButton(new ImageIcon(resizeImage("help.png",20,17)));
        help.setBackground(new Color(255,255,0));
        help.setToolTipText("Help");
        help.addMouseListener(this);
        help.setContentAreaFilled(false);
        help.setBorderPainted(false);
        help.setOpaque(false);
        help.addActionListener((ActionEvent e) -> {
            controller.getSeedMenu().setVisible(true);
        });
        seedMenuTitlePanel.add(help); //, BorderLayout.EAST
        seedmenu.add(seedMenuTitlePanel);
        //JScrollPane seedMenuLogScroll = new JScrollPane(seedmenu, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS, JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        //seedMenuLogScroll.setOpaque(false);
        //leftPanel.add(seedMenuLogScroll);
        //seedmenu.setVisible(true);
        //seedmenu.setFont(new Font("Arial", Font.PLAIN, 16));
        JTabbedPane tp = new JTabbedPane();
        tp.setBackground(new Color(152,251,152));
        pVeggie = new JPanel();
        JScrollPane scrollPane1 = new JScrollPane(pVeggie,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //scrollPane1.setPreferredSize(new Dimension(300, 240));
        pVeggie.setLayout(new BoxLayout(pVeggie, BoxLayout.X_AXIS));
        //pVeggie.setOpaque(false);
        //tp.add("", scrollPane1);
        pFlower = new JPanel();
        JScrollPane scrollPane2 = new JScrollPane(pFlower,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //scrollPane2.setPreferredSize(new Dimension(300, 240));
        pFlower.setLayout(new BoxLayout(pFlower, BoxLayout.X_AXIS));
        //pFlower.setOpaque(false);
        //tp.add("", scrollPane2);
        pTree = new JPanel();
        JScrollPane scrollPane3 = new JScrollPane(pTree,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //scrollPane3.setPreferredSize(new Dimension(300, 240));
        pTree.setLayout(new BoxLayout(pTree, BoxLayout.X_AXIS));
        //pTree.setOpaque(false);
        //tp.add("", scrollPane3);
        
        String veggie = "Vegetables";
        String flower = "Flower";
        String trees = "Fruit Trees";
        tp.addTab("", new ImageIcon(resizeImage("carrot.png",30,25)), scrollPane1, veggie);
        tp.addTab("", new ImageIcon(resizeImage("stargazer.png",30,25)), scrollPane2, flower);
        tp.addTab("", new ImageIcon(resizeImage("banana.png",30,25)), scrollPane3, trees);
        seedmenu.add(tp);
        
        seedmenu.setAlignmentX(0.5f);
        seedmenu.setAlignmentY(0.5f);
        seedmenuMotherPnl.add(seedmenu);
        
        JLabel woodpic = new JLabel(new ImageIcon(resizeImage("wood.png",315,270)));                   
        woodpic.setAlignmentX(0.5f);
        woodpic.setAlignmentY(0.5f);
        seedmenuMotherPnl.add(woodpic);
        
        c.gridx = 0;
        c.gridy = 11;
        c.gridwidth = 4;
        leftPanel.add(seedmenuMotherPnl,c);
        seedmenuMotherPnl.setVisible(false);
        GridBagConstraints c2 = new GridBagConstraints();
        JPanel middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setOpaque(false);
        
        for(int i=0; i < MAX_ROW; i++){
            for(int j=0;j<MAX_COL;j++){
                tileButtons[i][j] = new JButton();
                tileButtons[i][j].setBackground(new Color(255,255,0)); //76,187,23
                tileButtons[i][j].setBorderPainted(false);
                tileButtons[i][j].setContentAreaFilled(false);
                tileButtons[i][j].addActionListener(this);
                tileButtons[i][j].addMouseListener(this);
                c2.gridx = j;
                c2.gridy = i;
                middlePanel.add(tileButtons[i][j], c2);
            }
        }
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 10;
        c.fill = GridBagConstraints.HORIZONTAL;
        content.add(leftPanel);
        
        content.add(Box.createRigidArea(new Dimension(5,0)));
        c.gridx = 20;
        c.gridy = 0;
        c.gridwidth = 10;
        c.fill = GridBagConstraints.HORIZONTAL;
        content.add(middlePanel);
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        content.addMouseListener(this);
        
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setOpaque(false);
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setOpaque(false);
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        
        JLabel specs = new JLabel("Description:");
        specs.setFont(new Font("Arial", Font.PLAIN, 35));
        specs.setAlignmentX(Component.CENTER_ALIGNMENT);
        specs.setHorizontalAlignment(SwingConstants.LEFT);
        descriptionPanel.add(specs);
        descriptionPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        description = new JTextArea(5,20);
        description.setBackground( new Color(152, 251, 152) ); //new Color(152, 251, 152, 50)
        description.setFont(new Font("Arial", Font.PLAIN, 18));
        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        description.setEditable(false);
        //description.setOpaque(false);
        description.setMargin(new Insets(5,10,5,10));
        JScrollPane descriptionScroll = new JScrollPane(description,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
								  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Border round = new LineBorder(new Color(0,78,56),10,true);
        descriptionScroll.setBorder(round);
        //descriptionScroll.setBorder(BorderFactory.createEmptyBorder());
        descriptionPanel.add(descriptionScroll);
        
        JPanel logPanel = new JPanel();
        logPanel.setOpaque(false);
        logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.Y_AXIS));
        JLabel logLabel = new JLabel("Log:");
        logLabel.setFont(new Font("Arial", Font.PLAIN, 35));
        logLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logPanel.add(logLabel);
        logPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        log = new JTextArea(10,20);
        log.setBackground( new Color(152, 251, 152) );
        log.setFont(new Font("Arial", Font.PLAIN, 18));
        log.setWrapStyleWord(true);
        log.setLineWrap(true);
        log.setEditable(false);
        //log.setOpaque(false);
        log.setMargin(new Insets(5,10,5,10));
        JScrollPane logScroll = new JScrollPane(log, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                                                     ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //logScroll.setBorder(BorderFactory.createEmptyBorder());
        Border round2 = new LineBorder(new Color(0,78,56),10,true);
        logScroll.setBorder(round2);
        logScroll.setOpaque(false);
        logPanel.add(logScroll);
        rightPanel.add(descriptionPanel, BorderLayout.NORTH);
        rightPanel.add(logPanel, BorderLayout.CENTER);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        content.add(rightPanel);
        content.add(Box.createRigidArea(new Dimension(30, 0)));
        
        content.setAlignmentX(0.5f);
        content.setAlignmentY(0.5f);
        motherPnl.add(content);
        
        JLabel grasspic = new JLabel(new ImageIcon(resizeImage("grass.png",1450,850)));                   
        grasspic.setAlignmentX(0.5f);
        grasspic.setAlignmentY(0.5f);
        motherPnl.add(grasspic);
        
        add(motherPnl);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setBackground(Color.WHITE);
        setResizable(false);
        setUndecorated(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLogAction(1); 
        //setLogAction(2);
        //setLogAction(3);
        //setLogAction(4);
        //setLogHarvested(12.78);
    }
    /*
    public static BufferedImage setTileImage(int state, String plant){
        switch(state){
            case 0 : return resizeImage("rocky soil.png",60,60);
            case 1 : return resizeImage("unplowed soil.png",60,60);
            case 2 : return resizeImage("plowed soil.png",60,60);
            case 3 : switch(plant){
                        case "Turnip"   :     return resizeImage("turnip.png",60,60);
                        case "Carrot"   :     return resizeImage("carrot.png",60,60);
                        case "Tomato"   :     return resizeImage("tomato.png",60,60);
                        case "Potato"   :     return resizeImage("potato.png",60,60);
                        case "Rose"     :     return resizeImage("rose.png",60,60);
                        case "Tulip"    :     return resizeImage("tulip.png",60,60);
                        case "Stargazer":     return resizeImage("stargazer.png",60,60);
                        case "Sunflower":     return resizeImage("sunflower.png",60,60);
                        case "Mango"    :     return resizeImage("mango.png",60,60);
                        case "Apple"    :     return resizeImage("apple.png",60,60);
                        case "Banana"   :     return resizeImage("banana.png",60,60);
                        case "Orange"   :     return resizeImage("orange.png",60,60);
                     }
            case 4: return resizeImage("withered soil.png",60,60);
            default : return null;
        }
    }
    */
    public void addPlantImage(String name) {
        plantImages.put(name, resizeImage(name.toLowerCase() + ".png", 65, 65));
    }
    
    public void addVegetablePanel(String name, GameGUIController controller) {
        SeedPanel sp = new SeedPanel(name, 0, controller);
        pVeggie.add(sp.getProduct());
        seedPanels.add(sp);
    }
    
    public void addFlowerPanel(String name, GameGUIController controller) {
        SeedPanel sp = new SeedPanel(name, 0, controller);
        pFlower.add(sp.getProduct());
        seedPanels.add(sp);
    }
    
    public void addTreePanel(String name, GameGUIController controller) {
        SeedPanel sp = new SeedPanel(name, 0, controller);
        pTree.add(sp.getProduct());
        seedPanels.add(sp);
    }
    
    public void setTileImage(int state, String plant, JButton tileButton){
        switch(state){
            case ROCKY : tileButton.setIcon(new ImageIcon(resizeImage("rocky soil.png",65,65))); break;
            case UNPLOWED : tileButton.setIcon(new ImageIcon(resizeImage("unplowed soil.png",65,65))); break;
            case PLOWED : tileButton.setIcon(new ImageIcon(resizeImage("plowed soil.png",65,65))); break;
            case PLANTED : 
                if (plant.isEmpty())
                    tileButton.setIcon(new ImageIcon(resizeImage("leaf.png",65,65))); 
                else
                    tileButton.setIcon(new ImageIcon(resizeImage("seedling.png",65,65))); 
                break;
            case READY_TO_HARVEST : tileButton.setIcon(new ImageIcon(plantImages.get(plant))); break;
            case WITHERED: tileButton.setIcon(new ImageIcon(resizeImage("withered.png",60,60))); break;
            default : System.out.println("Unable to set picture.");;
        }
    }
    
    public static BufferedImage resizeImage(String address, int width, int height) {
        try{
             BufferedImage rawHolder = ImageIO.read(new File(address));
             Image raw = rawHolder.getScaledInstance(width, height, Image.SCALE_SMOOTH);
             BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
             Graphics2D g2d = resized.createGraphics();
             g2d.drawImage(raw, 0, 0, null);
             g2d.dispose();
             return resized;
        }
        catch(IOException e){
             System.out.println("File not found.");
             return null;
        }
    }
    
    public void setLogHarvested(double profit){
        appendLog("Harvest successful. " + profit + " Object Coins added to wallet. Gained 100 EXP");
    }
    
    public void setLogPurchase(double cost) {
        appendLog("Spent " + cost + " OC. Gained 25 EXP");
    }
    
    public void appendLog(String text) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        log.setText(log.getText() + dtf.format(now) + " - " + text + "\n");
    }
    
    public void setLogAction(int code, boolean success){
        switch(code){
            case 1: appendLog(success ? "Plant watered. Gained 10 EXP." : "Watering failed.");      break;
            case 2: appendLog(success ? "Tile plowed. Gained 10 EXP." : "Plowing failed.");        break;
            case 3: appendLog(success ? "Rocks cleared. Gained 10 EXP." : "There are no rocks on the tile.");      break;
            case 4: appendLog(success ? "Tile fertilized. Gained 10 EXP." : "Fertilizing failed");   break;
            case 5: appendLog(success ? "Planted a seed." : "Plant failed."); break;
        }
    }
    
    public void actionPerformed (ActionEvent e){
        controller.updateSelected((JButton)e.getSource());
        
    }
  
    /*
    public static void main(String[] args){
        GameGUI g = new GameGUI("Farmer");
    }
    */
    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (!(e.getSource() instanceof JButton))
                controller.deselect();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { 
        if (e.getSource() instanceof JButton) {
            JButton btn = (JButton)e.getSource();
            btn.setContentAreaFilled(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) { 
        if (e.getSource() instanceof JButton) {
            JButton btn = (JButton)e.getSource();
            btn.setContentAreaFilled(false);
        }
    }
}
