   public class ChessGame extends JFrame {
    
    private StartScreen startScreen;
    private DifficultySelectionScreen difficultyScreen;
    private GameModeSelectionScreen gameModeScreen;
    private GameScreen gameScreen;

    public ChessGame() {
        setTitle("Java Modern Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //                      ?        startScreen = new StartScreen(this);
        difficultyScreen = new DifficultySelectionScreen(this);
        gameModeScreen = new GameModeSelectionScreen(this);
        gameScreen = new GameScreen(this);
        
        mainPanel.add(startScreen, "START");
        mainPanel.add(difficultyScreen, "DIFFICULTY");
        mainPanel.add(gameModeScreen, "GAMEMODE");
        mainPanel.add(gameScreen, "GAME");
        
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(true); //                           
        setMinimumSize(new Dimension(900, 700)); //                            ?        setVisible(true);
    }

    public void startGame(boolean vsAI, int difficulty) {
        gameScreen.startNewGame(vsAI, difficulty, false, 1);
        cardLayout.show(mainPanel, "GAME");
    }
    
    public void startAmusementGame(boolean vsAI, int difficulty) {
        gameScreen.startNewGame(vsAI, difficulty, true, 0);
        cardLayout.show(mainPanel, "GAME");
    }

    public void showMenu() {
        cardLayout.show(mainPanel, "START");
    }
    
    public void showDifficultySelection(boolean isAmusement) {
        difficultyScreen.setAmusementMode(isAmusement);
        cardLayout.show(mainPanel, "DIFFICULTY");
    }

    public void showGameModeSelection() {
        cardLayout.show(mainPanel, "GAMEMODE");
    }

    public static void main(String[] args) {
        //                                              ?        // System.setProperty("awt.useSystemAAFontSettings", "on");
        // System.setProperty("swing.aatext", "true");
        
        SwingUtilities.invokeLater(() -> new ChessGame());
    }
}

// ==================== UI           ====================

// 1.                   ?class StartScreen extends JPanel {
    private ChessGame mainFrame;
    private final JButton settingsBtn;

    public StartScreen(ChessGame frame) {
        this.mainFrame = frame;
        setPreferredSize(new Dimension(900, 700));
        setLayout(new BorderLayout());
        setBackground(new Color(26, 28, 36)); //                            

        //             ?        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        //             ?        JLabel title = new JLabel("MODERN CHESS");
        title.setFont(new Font("Segoe UI", Font.BOLD, 64));
        title.setForeground(new Color(97, 218, 251));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(97, 218, 251, 50), 0),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridy++;
        gbc.insets = new Insets(40, 0, 20, 0);
        centerPanel.add(title, gbc);

        //             ?        JLabel subtitle = new JLabel("Modern Chess Experience");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        subtitle.setForeground(new Color(180, 180, 180));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 50, 0);
        centerPanel.add(subtitle, gbc);

        //                   
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        //                   
        Dimension btnSize = new Dimension(280, 70);
        Font btnFont = new Font("Segoe UI", Font.BOLD, 20);

        // PVP          
        JButton pvpBtn = createModernButton("", "Player vs Player", btnSize, btnFont, 
            new Color(97, 218, 251), new Color(130, 230, 255));
        pvpBtn.addActionListener(e -> mainFrame.startGame(false, 0));
        buttonPanel.add(pvpBtn);
        buttonPanel.add(Box.createVerticalStrut(20));

        // PVE          
        JButton pveBtn = createModernButton("", "Player vs AI", btnSize, btnFont,
            new Color(120, 200, 120), new Color(150, 230, 150));
        pveBtn.addActionListener(e -> mainFrame.showDifficultySelection(false));
        buttonPanel.add(pveBtn);
        buttonPanel.add(Box.createVerticalStrut(20));
        
        // Amusement Chess          
        JButton amusementBtn = createModernButton("", "Amusement Chess", btnSize, btnFont,
            new Color(200, 150, 255), new Color(230, 180, 255));
        amusementBtn.addActionListener(e -> mainFrame.showGameModeSelection());
        buttonPanel.add(amusementBtn);
        buttonPanel.add(Box.createVerticalStrut(20));
        
        // Exit Button
        JButton exitBtn = createModernButton("", "Exit", btnSize, btnFont,
            new Color(220, 100, 100), new Color(255, 130, 130));
        exitBtn.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitBtn);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);
        centerPanel.add(buttonPanel, gbc);

        //                      ?        JPanel bottomBar = new JPanel(new BorderLayout());
        bottomBar.setOpaque(false);
        bottomBar.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));

        JLabel infoLabel = new JLabel("Animation, Replay, Timer, Fog of War & More");
        infoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        infoLabel.setForeground(new Color(150, 150, 150));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomBar.add(infoLabel, BorderLayout.CENTER);

        JPanel rightBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightBar.setOpaque(false);
        settingsBtn = createModernButton("", "Settings", new Dimension(140, 45),
                new Font("Segoe UI", Font.BOLD, 14),
                new Color(80, 90, 105), new Color(105, 120, 140));
        settingsBtn.addActionListener(e -> showSettingsDialog());
        rightBar.add(settingsBtn);
        bottomBar.add(rightBar, BorderLayout.EAST);

        add(centerPanel, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);
    }

    private void showSettingsDialog() {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Settings", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel root = new JPanel();
        root.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Sound");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        root.add(title);
        root.add(Box.createVerticalStrut(10));

        int current = Math.round(SoundManager.getVolume() * 100f);
        JLabel volumeLabel = new JLabel("Volume: " + current + "%");
        volumeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        volumeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        root.add(volumeLabel);
        root.add(Box.createVerticalStrut(6));

        JSlider slider = new JSlider(0, 100, current);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setAlignmentX(Component.LEFT_ALIGNMENT);
        slider.addChangeListener(ev -> {
            int v = slider.getValue();
            volumeLabel.setText("Volume: " + v + "%");
            SoundManager.setVolume(v / 100f);
        });
        root.add(slider);
        root.add(Box.createVerticalStrut(14));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        buttons.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton close = new JButton("Close");
        close.addActionListener(e -> dialog.dispose());
        buttons.add(close);
        root.add(buttons);

        dialog.setContentPane(root);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        int width = getWidth();
        int height = getHeight();
        
        //                                  
        GradientPaint gradient = new GradientPaint(
            0, 0, new Color(20, 22, 30, 240),
            0, height, new Color(30, 32, 42, 240)
        );
        g2.setPaint(gradient);
        g2.fillRect(0, 0, width, height);
        
        //                                       ?        g2.setColor(new Color(97, 218, 251, 5));
        g2.setStroke(new BasicStroke(1));
        for (int i = 0; i < width; i += 50) {
            g2.drawLine(i, 0, i, height);
        }
        for (int i = 0; i < height; i += 50) {
            g2.drawLine(0, i, width, i);
        }
        
        //                                     
        drawChessBoardPattern(g2, width, height);
        
        //                            
        // drawChessPiecesDecoration(g2, width, height);
        
        //                                                 ?        int centerX = width / 2;
        int centerY = height / 2;
        RadialGradientPaint radialGradient = new RadialGradientPaint(
            centerX, centerY, Math.min(width, height) / 2,
            new float[]{0f, 1f},
            new Color[]{new Color(97, 218, 251, 3), new Color(97, 218, 251, 0)}
        );
        g2.setPaint(radialGradient);
        g2.fillOval(centerX - width/2, centerY - height/2, width, height);
    }

    private void drawChessBoardPattern(Graphics2D g2, int width, int height) {
        //                                                                      
        int boardSize = Math.min(width, height) / 3;
        int cellSize = boardSize / 8;
        //                            
        int startX = width - boardSize - 50;
        int startY = height - boardSize - 50;
        
        g2.setColor(new Color(97, 218, 251, 15));
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if ((r + c) % 2 == 0) {
                    g2.fillRect(startX + c * cellSize, startY + r * cellSize, cellSize, cellSize);
                }
            }
        }
        
        //                                                       
        int boardSize2 = boardSize / 2;
        int cellSize2 = boardSize2 / 8;
        int startX2 = 50;
        int startY2 = 50;
        
        g2.setColor(new Color(97, 218, 251, 10));
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if ((r + c) % 2 == 0) {
                    g2.fillRect(startX2 + c * cellSize2, startY2 + r * cellSize2, cellSize2, cellSize2);
                }
            }
        }
    }
    
    private void drawChessPiecesDecoration(Graphics2D g2, int width, int height) {
        //                                       ?        g2.setFont(new Font("Serif", Font.PLAIN, 120));
        g2.setColor(new Color(97, 218, 251, 20));
        
        //                      ?        //                      ?        String[] pieces = {"K", "Q", "R", "B", "N", "P"};
        int x1 = 100;
        int y1 = height - 200;
        for (int i = 0; i < pieces.length; i++) {
            g2.drawString(pieces[i], x1 + i * 80, y1);
        }
        
        //                                        ?        int x2 = width - 500;
        int y2 = 150;
        String[] blackPieces = {"k", "q", "r", "b", "n", "p"};
        for (int i = 0; i < blackPieces.length; i++) {
            g2.drawString(blackPieces[i], x2 + i * 80, y2);
        }
    }
    
    private JButton createModernButton(String emojiText, String text, Dimension size, Font font, 
                                       Color normalColor, Color hoverColor) {
        String buttonText = emojiText.isEmpty() ? text : "<html><center>" + emojiText + "<br>" + text + "</center></html>";
        JButton btn = new JButton(buttonText);
        btn.setPreferredSize(size);
        btn.setMinimumSize(size);
        btn.setMaximumSize(size);
        btn.setFont(font);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(false);

        //                            
        final float[] hoverProgress = {0f}; // 0=         , 1=                  
        final boolean[] hovering = {false};
        final javax.swing.Timer[] fadeTimer = {null};

        //                               ?+                   /         
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovering[0] = true;
                MusicManager.playSoundEffect("ui_hover");
                startOrUpdateTimer();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovering[0] = false;
                startOrUpdateTimer();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                btn.setLocation(btn.getX() + 1, btn.getY() + 1);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                btn.setLocation(btn.getX() - 1, btn.getY() - 1);
            }

            private void startOrUpdateTimer() {
                if (fadeTimer[0] != null && fadeTimer[0].isRunning()) return;
                fadeTimer[0] = new javax.swing.Timer(16, ev -> {
                    float target = hovering[0] ? 1f : 0f;
                    float speed = 0.25f; //                             100~150ms          
                    hoverProgress[0] += (target - hoverProgress[0]) * speed;
                    if (Math.abs(target - hoverProgress[0]) < 0.01f) {
                        hoverProgress[0] = target;
                        fadeTimer[0].stop();
                    }
                    btn.putClientProperty("hoverProgress", hoverProgress[0]);
                    btn.repaint();
                });
                fadeTimer[0].start();
            }
        });

        //                      ?        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                JButton button = (JButton) c;
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                //           hoverProgress    ?normal/hover                   ?                Object hp = button.getClientProperty("hoverProgress");
                float t = (hp instanceof Float) ? (Float) hp : (button.getModel().isRollover() ? 1f : 0f);
                t = Math.max(0f, Math.min(1f, t));

                Color bgColor = lerpColor(normalColor, hoverColor, t);

                //                                     
                int arc = 15;
                g2.setColor(bgColor);
                g2.fillRoundRect(0, 0, button.getWidth(), button.getHeight(), arc, arc);
                
                //                            
                if (t > 0f) {
                    g2.setColor(new Color(255, 255, 255, 30));
                    g2.fillRoundRect(0, 0, button.getWidth(), button.getHeight() / 2, arc, arc);
                }
                
                //                   
                g2.setColor(new Color(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), 150));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(1, 1, button.getWidth() - 2, button.getHeight() - 2, arc, arc);
                
                //                   
                g2.setColor(Color.WHITE);
                FontMetrics fm = g2.getFontMetrics(button.getFont());
                String[] lines = button.getText().replaceAll("<[^>]*>", "").split("\n");
                int y = (button.getHeight() - (fm.getHeight() * lines.length)) / 2 + fm.getAscent();
                for (String line : lines) {
                    int x = (button.getWidth() - fm.stringWidth(line)) / 2;
                    //                   
                    g2.setColor(new Color(0, 0, 0, 100));
                    g2.drawString(line, x + 2, y + 2);
                    //                   
                    g2.setColor(Color.WHITE);
                    g2.drawString(line, x, y);
                    y += fm.getHeight();
                }
                
                g2.dispose();
            }
        });
        
        UIAudio.bindClickSound(btn);
        return btn;
    }

    //                                                                        ?    private static Color lerpColor(Color a, Color b, float t) {
        t = Math.max(0f, Math.min(1f, t));
        int r = (int) (a.getRed()   + (b.getRed()   - a.getRed())   * t);
        int g = (int) (a.getGreen() + (b.getGreen() - a.getGreen()) * t);
        int bl = (int) (a.getBlue()  + (b.getBlue()  - a.getBlue())  * t);
        int al = (int) (a.getAlpha() + (b.getAlpha() - a.getAlpha()) * t);
        return new Color(r, g, bl, al);
    }
}

// 1.5                            
class DifficultySelectionScreen extends JPanel {
    private ChessGame mainFrame;
    private boolean isAmusementMode = false;

    public DifficultySelectionScreen(ChessGame frame) {
        this.mainFrame = frame;
        initUI();
    }

    public void setAmusementMode(boolean amusement) {
        this.isAmusementMode = amusement;
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(26, 28, 36));

        // Header
        JLabel header = new JLabel("SELECT DIFFICULTY", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 48));
        header.setForeground(new Color(220, 220, 220));
        header.setBorder(BorderFactory.createEmptyBorder(60, 0, 40, 0));
        add(header, BorderLayout.NORTH);

        // Center Container for Cards
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        
        JPanel cardsPanel = new JPanel(new GridLayout(1, 5, 15, 0)); // 5 columns
        cardsPanel.setOpaque(false);

        // Data: Title, Description, SkillLevel
        Object[][] levels = {
            {"Beginner", "Skill 0<br>~800 Elo", 0},
            {"Intermediate", "Skill 5<br>~1500 Elo", 5},
            {"Advanced", "Skill 10<br>~2200 Elo", 10},
            {"Master", "Skill 15<br>~2800 Elo", 15},
            {"God-like", "Skill 20<br>3500+ Elo", 20}
        };

        for (int i = 0; i < levels.length; i++) {
            String title = (String) levels[i][0];
            String desc = (String) levels[i][1];
            int skill = (Integer) levels[i][2];
            boolean isLast = (i == levels.length - 1);

            JButton btn = new JButton();
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(false);
            btn.setOpaque(true);
            btn.setPreferredSize(new Dimension(160, 220)); // Larger cards
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // HTML Styling
            StringBuilder html = new StringBuilder("<html><center>");
            if (isLast) {
                html.append("<div style='color:#FF4444; font-weight:bold; font-size:18px;'>").append(title).append("</div>");
                html.append("<br><br><div style='color:#FF8888; font-weight:bold; font-size:14px;'>").append(desc).append("</div>");
                html.append("<br><div style='color:#FFaaaa; font-size:10px;'>UNBEATABLE</div>");
            } else {
                html.append("<div style='color:#E0E0E0; font-size:18px;'>").append(title).append("</div>");
                html.append("<br><br><div style='color:#AAAAAA; font-size:14px;'>").append(desc).append("</div>");
            }
            html.append("</center></html>");
            btn.setText(html.toString());

            // Color Styling
            Color normalBg, hoverBg, borderColor;
            if (isLast) {
                normalBg = new Color(60, 20, 20);
                hoverBg = new Color(80, 25, 25);
                borderColor = new Color(255, 68, 68);
            } else {
                normalBg = new Color(45, 48, 55);
                hoverBg = new Color(55, 58, 65);
                borderColor = new Color(80, 83, 95);
            }
            
            btn.setBackground(normalBg);
            btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor, isLast ? 2 : 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));

            // Hover Effect
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(hoverBg);
                }
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(normalBg);
                }
            });

            btn.addActionListener(e -> {
                if (isAmusementMode) {
                    mainFrame.startAmusementGame(true, skill);
                } else {
                    mainFrame.startGame(true, skill);
                }
            });
            
            cardsPanel.add(btn);
        }
        
        centerWrapper.add(cardsPanel);
        add(centerWrapper, BorderLayout.CENTER);

        // Bottom - Back Button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
        
        JButton backBtn = new JButton("Back to Menu");
        backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        backBtn.setForeground(new Color(150, 150, 150));
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.setFocusPainted(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { backBtn.setForeground(Color.WHITE); }
            public void mouseExited(MouseEvent e) { backBtn.setForeground(new Color(150, 150, 150)); }
        });
        backBtn.addActionListener(e -> {
            if (isAmusementMode) {
                mainFrame.showGameModeSelection();
            } else {
                mainFrame.showMenu();
            }
        });
        
        bottomPanel.add(backBtn);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Paint a subtle background gradient or pattern if needed
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Dark background gradient
        GradientPaint gradient = new GradientPaint(
            0, 0, new Color(20, 22, 30),
            0, getHeight(), new Color(30, 32, 42)
        );
        g2.setPaint(gradient);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}

// 2.                                                                        
class GameScreen extends JPanel {
    private ChessGame mainFrame;
    private ChessBoard board;
    private SidePanel sidePanel;
    private GameLogic logic;
    private boolean isVsAI;
    private int aiSkillLevel = 5; // 0-20
    private int undoCount = 3;
    
    //                            
    private LinkedList<PGNMove> moveHistory = new LinkedList<>();
    private int replayIndex = -1;
    
    //                      ?    private java.util.Timer gameTimer;
    private int whiteTimeSeconds = 0;
    private int blackTimeSeconds = 0;
    private boolean timerEnabled = false;
    
    //                            
    private boolean fogOfWarEnabled = false;
    private java.util.Timer fogTimer; //                                                    
    private boolean fogDynamicMode = false; //                                             ?    private float fogOpacity = 1.0f; //                            0.0-1.0   ?    private boolean fogTargetVisible = true; //                                                               ?    private long nextFogToggleAtMs = 0L; //                                                       ?    
    //                            ?    private int flashCooldown = 0;
    private Stack<Piece> capturedPieces = new Stack<>();
    
    // Amusement                  
    private boolean isAmusementMode = false;
    private int boardMultiplier = 1; //                      ?   ?   ?    private boolean timerForced = false; //                                        ?    private boolean fogForced = false; //                                              

    // External UCI engine controller (Stockfish)
    private ChessAIController engineAI;
    // Dedicated AI logic for Amusement mode and non-8x8 boards
    private AmusementChessAI amusementAI;

    public GameScreen(ChessGame frame) {
        this.mainFrame = frame;
        setLayout(new BorderLayout());
        setOpaque(false); //                         
        
        logic = new GameLogic();
        board = new ChessBoard(logic, this);
        sidePanel = new SidePanel(this);
        amusementAI = new AmusementChessAI();

        // Try to initialize external engine (Stockfish).
        // You can change the default path "stockfish.exe" to an absolute path if needed.
        try {
            engineAI = new ChessAIController("stockfish.exe");
        } catch (Exception ex) {
            ex.printStackTrace();
            engineAI = null;
        }

        add(board, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        //                         
        GradientPaint gradient = new GradientPaint(
            0, 0, new Color(30, 32, 40, 250),
            getWidth(), getHeight(), new Color(40, 42, 50, 250)
        );
        g2.setPaint(gradient);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }

    private Timer aiTimer;

    public void undo() {
        if (undoCount > 0 && logic.canUndo()) {
            
            // If AI is currently "thinking" (timer running), we stop it and only undo the player's move
            if (aiTimer != null && aiTimer.isRunning()) {
                aiTimer.stop();
                logic.undoLastMove(); // Undo Player's move
            } else {
                // Otherwise, undo until it is the Player's turn (White) again
                // This typically means undoing 2 moves (AI + Player)
                // We force at least one undo, then continue if needed
                
                logic.undoLastMove(); // Undo 1 (Opponent)
                
                // If it's not White's turn yet (and we can undo), undo again (Player)
                if (!logic.isWhiteTurn && logic.canUndo()) {
                    logic.undoLastMove();
                }
            }
            
            undoCount--;
            sidePanel.updateUndoButton();
            sidePanel.updateTurn(logic.isWhiteTurn);
            board.repaint();
        }
    }

    
    public int getUndoCount() { return undoCount; }

    public void startNewGame(boolean vsAI, int difficulty, boolean amusementMode, int multiplier) {
        this.isVsAI = vsAI;
        // Interpret difficulty as engine Skill Level (0-20)
        this.aiSkillLevel = difficulty;
        if (engineAI != null) {
            engineAI.setSkillLevel(aiSkillLevel);
        }
        this.undoCount = 3;
        this.moveHistory.clear();
        this.replayIndex = -1;
        this.flashCooldown = 0;
        this.capturedPieces.clear();
        this.isAmusementMode = amusementMode;
        
        if (amusementMode) {
            //                   2         4   ?            Random rand = new Random();
            this.boardMultiplier = (rand.nextInt(2) + 1) * 2; // 2   ?
            logic.resetAmusementBoard(boardMultiplier);
            board.setBoardSize(8 * boardMultiplier);
            
            //                                                                           
            this.fogOfWarEnabled = rand.nextBoolean();
            this.fogDynamicMode = true; //                                    ?            this.fogForced = true; //                              ?            this.fogTargetVisible = this.fogOfWarEnabled;
            this.fogOpacity = this.fogOfWarEnabled ? 1.0f : 0.0f;
            this.nextFogToggleAtMs = System.currentTimeMillis() + 2000; //                                     
            sidePanel.setFogOfWarEnabled(this.fogOfWarEnabled, true); // true                           
            startFogTimer(); //                                           
            
            //                                  
            this.timerEnabled = true;
            this.timerForced = true; //                              ?            whiteTimeSeconds = 0;
            blackTimeSeconds = 0;
            startTimer();
            sidePanel.setTimerEnabled(true, true); // true                           
        } else {
            this.boardMultiplier = 1;
            logic.resetBoard();
            board.setBoardSize(8);
            
            //                                                                              ?            this.fogOfWarEnabled = false;
            this.fogDynamicMode = false;
            this.fogTargetVisible = false;
            this.fogOpacity = 0.0f;
            this.fogForced = false;
            this.timerForced = false;
            stopFogTimer(); //                                           
            sidePanel.setFogOfWarEnabled(false, false);
            sidePanel.setTimerEnabled(timerEnabled, false);
            if (timerEnabled) {
                whiteTimeSeconds = 0;
                blackTimeSeconds = 0;
                startTimer();
            }
        }
        
        sidePanel.updateUndoButton();
        sidePanel.updateTurn(true);
        //                                                                                                                
        board.clearSelection();
        board.repaint();
    }
    
    public boolean isAmusementMode() {
        return isAmusementMode;
    }
    
    public int getBoardMultiplier() {
        return boardMultiplier;
    }
    
    //                      ?    private void startTimer() {
        if (gameTimer != null) {
            gameTimer.cancel();
        }
        gameTimer = new java.util.Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (replayIndex >= 0) return; //                            
                
                if (logic.isWhiteTurn) {
                    whiteTimeSeconds++;
                } else {
                    blackTimeSeconds++;
                }
                sidePanel.updateTimer(whiteTimeSeconds, blackTimeSeconds);
            }
        }, 1000, 1000);
    }
    
    
    //          /                     ?    public void setTimerEnabled(boolean enabled) {
        //                                                    amusement                                       ?        if (timerForced && !enabled) {
            return;
        }
        this.timerEnabled = enabled;
        if (enabled && gameTimer == null) {
            startTimer();
        } else if (!enabled && gameTimer != null && !timerForced) {
            gameTimer.cancel();
            gameTimer = null;
        }
    }
    
    //          /                           
    public void setFogOfWarEnabled(boolean enabled) {
        //                                                       amusement                                       ?        if (fogForced) {
            return;
        }
        this.fogOfWarEnabled = enabled;
        this.fogDynamicMode = false; //                                                      ?        stopFogTimer();
        board.repaint();
    }
    
    public boolean isFogOfWarEnabled() {
        return fogOfWarEnabled;
    }
    
    //                                                                                     
    public boolean isFogCurrentlyVisible() {
        if (!fogDynamicMode) {
            return fogOfWarEnabled;
        }
        //                                                                                                            
        return fogOpacity > 0.2f;
    }
    
    //                               ?    public float getFogOpacity() {
        if (!fogDynamicMode) {
            return fogOfWarEnabled ? 1.0f : 0.0f;
        }
        return fogOpacity;
    }
    
    //                                           
    private void startFogTimer() {
        stopFogTimer(); //                                     
        
        if (!fogDynamicMode) return;
        
        fogTimer = new java.util.Timer();
        Random rand = new Random();
        //                            0   ?                        +                                                                                                
        final float[] targetOpacity = new float[] { fogTargetVisible ? 1.0f : 0.0f };
        //                                                                   ?.5s ~ 6.5s
        final int minHoldMs = 2500;
        final int maxHoldMs = 6500;
        //                                                          ?1.0   ?0ms tick => step=0.05   ?        final float stepPerTick = 0.05f;
        
        fogTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!fogDynamicMode || replayIndex >= 0) return;
                
                long now = System.currentTimeMillis();
                
                //                                                                                           ?                if (now >= nextFogToggleAtMs) {
                    fogTargetVisible = !fogTargetVisible;
                    targetOpacity[0] = fogTargetVisible ? 1.0f : 0.0f;
                    int hold = minHoldMs + rand.nextInt(maxHoldMs - minHoldMs + 1);
                    nextFogToggleAtMs = now + hold;
                }
                
                //                                                                             
                float diff = targetOpacity[0] - fogOpacity;
                if (Math.abs(diff) <= stepPerTick) {
                    fogOpacity = targetOpacity[0];
                } else {
                    fogOpacity += Math.signum(diff) * stepPerTick;
                }
                
                //                            
                SwingUtilities.invokeLater(() -> board.repaint());
            }
        }, 100, 50); //    ?0ms                                                   
    }
    
    //                                           
    private void stopFogTimer() {
        if (fogTimer != null) {
            fogTimer.cancel();
            fogTimer = null;
        }
    }
    
    //                         GN         
    public void recordMove(int sr, int sc, int tr, int tc, Piece captured) {
        if (replayIndex >= 0) return; //                               ?        
        Piece movedPiece = logic.getPiece(tr, tc);
        String moveNotation = toPGNNotation(sr, sc, tr, tc, movedPiece, captured);
        moveHistory.add(new PGNMove(moveNotation, sr, sc, tr, tc, captured));
        
        if (captured != null) {
            capturedPieces.push(captured);
        }
    }
    
    //                GN                              ?    private String toPGNNotation(int sr, int sc, int tr, int tc, Piece p, Piece captured) {
        String pieceChar = "";
        switch (p.type) {
            case KING: pieceChar = "K"; break;
            case QUEEN: pieceChar = "Q"; break;
            case ROOK: pieceChar = "R"; break;
            case BISHOP: pieceChar = "B"; break;
            case KNIGHT: pieceChar = "N"; break;
            case PAWN: pieceChar = ""; break;
        }
        
        String capture = captured != null ? "x" : "";
        String toSquare = "" + (char)('a' + tc) + (8 - tr);
        
        return pieceChar + capture + toSquare;
    }
    
    //                   ?    public void startReplay() {
        if (moveHistory.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No moves to replay", "Replay", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        replayIndex = 0;
        logic.resetBoard();
        board.repaint();
        sidePanel.updateReplayControls(true);
    }
    
    //                      ?    public void replayNext() {
        if (replayIndex < moveHistory.size()) {
            PGNMove move = moveHistory.get(replayIndex);
            logic.movePiece(move.sr, move.sc, move.tr, move.tc);
            replayIndex++;
            board.repaint();
            sidePanel.updateReplayControls(true);
        }
    }
    
    //                      ?    public void replayPrev() {
        if (replayIndex > 0) {
            replayIndex--;
            logic.resetBoard();
            for (int i = 0; i < replayIndex; i++) {
                PGNMove move = moveHistory.get(i);
                logic.movePiece(move.sr, move.sc, move.tr, move.tc);
            }
            board.repaint();
            sidePanel.updateReplayControls(true);
        }
    }
    
    //                   
    public void stopReplay() {
        replayIndex = -1;
        logic.resetBoard();
        for (PGNMove move : moveHistory) {
            logic.movePiece(move.sr, move.sc, move.tr, move.tc);
        }
        board.repaint();
        sidePanel.updateReplayControls(false);
    }
    
    public boolean isReplayMode() {
        return replayIndex >= 0;
    }
    
    public void onTurnEnd() {
        sidePanel.updateTurn(logic.isWhiteTurn);
        
        //                            
        if (flashCooldown > 0) {
            flashCooldown--;
        }
        
        board.repaint();
        
        //                                                               AI                                      ?        if (isVsAI && !logic.isWhiteTurn && replayIndex < 0) {
            //           Timer                                                             
            aiTimer = new Timer(500, e -> {
                performAIMove();
            });
            aiTimer.setRepeats(false);
            aiTimer.start();
        }
    }
    
    private void performAIMove() {
        int boardSize = logic.getBoardSize();

        // For Amusement mode or non-8x8 boards, use dedicated Java AI logic.
        if (isAmusementMode || boardSize != 8 || engineAI == null) {
            int depth = ChessAIController.mapSkillLevelToDepth(aiSkillLevel);
            // To keep UI responsive on large boards, cap depth for the amusement AI.
            if (boardSize > 8) {
                depth = Math.min(depth, 4);
            }
            Move bestMove = amusementAI.findBestMove(logic, depth);
            applyAIMove(bestMove);
            return;
        }

        // Standard 8x8 board in normal mode: use external engine
        engineAI.requestBestMoveAsync(logic, aiSkillLevel, new ChessAIController.AIResultListener() {
            @Override
            public void onBestMoveComputed(Move bestMove) {
                SwingUtilities.invokeLater(() -> applyAIMove(bestMove));
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Apply a chosen move from the engine or amusement AI
    private void applyAIMove(Move bestMove) {
        if (bestMove != null) {
            // AI                  
            Piece captured = logic.movePiece(bestMove.sr, bestMove.sc, bestMove.tr, bestMove.tc);
            if (captured != null) {
                SoundManager.play(SoundEffect.CAPTURE);
            } else {
                SoundManager.play(SoundEffect.MOVE);
            }
            
            //                   
            recordMove(bestMove.sr, bestMove.sc, bestMove.tr, bestMove.tc, captured);
            
            //          ?AI                   
            if (captured != null && captured.type == PieceType.KING) {
                board.handleWin(false); //          (AI)   ?            } else {
                //                      ?(AI                   )
                Piece movedPiece = logic.getPiece(bestMove.tr, bestMove.tc);
                if (movedPiece != null && movedPiece.type == PieceType.PAWN) {
                     int boardSize = logic.getBoardSize();
                     // AI                                                   ow 0                  
                     //                                  ow boardSize-1                                 I                                                                      
                     if ((!movedPiece.isWhite && bestMove.tr == 0) || 
                         (movedPiece.isWhite && bestMove.tr == boardSize - 1)) {
                         logic.promotePiece(bestMove.tr, bestMove.tc, PieceType.QUEEN);
                         SoundManager.play(SoundEffect.PROMOTE);
                     }
                }
                
                //                            ?                if (logic.isInCheck(true)) {
                    board.triggerShake();
                }
                
                onTurnEnd(); //                      ?            }
        } else {
            System.out.println("AI                                                    ");
        }
    }

    public void backToMenu() {
        stopFogTimer(); //                                                       
        mainFrame.showMenu();
    }
    
    public void resetGame() {
        stopFogTimer(); //                                                       
        startNewGame(isVsAI, aiSkillLevel, isAmusementMode, boardMultiplier);
    }
    
    public boolean isPlayerTurn() {
        //                                        ?        if (replayIndex >= 0) return false;
        //             ?PvP                              ?        if (!isVsAI) return true;
        //             ?PvE                                    
        return logic.isWhiteTurn;
    }

    public boolean isVsAI() {
        return isVsAI;
    }
    
    //                                                                                  
    public List<Point> getAttackRange(boolean isWhite) {
        List<Point> range = new ArrayList<>();
        int boardSize = logic.getBoardSize();
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                Piece p = logic.getPiece(r, c);
                if (p != null && p.isWhite == isWhite) {
                    List<Point> moves = logic.getValidMoves(r, c);
                    for (Point m : moves) {
                        boolean exists = false;
                        for (Point existing : range) {
                            if (existing.x == m.x && existing.y == m.y) {
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) {
                            range.add(new Point(m.x, m.y));
                        }
                    }
                    boolean posExists = false;
                    for (Point existing : range) {
                        if (existing.x == c && existing.y == r) {
                            posExists = true;
                            break;
                        }
                    }
                    if (!posExists) {
                        range.add(new Point(c, r));
                    }
                }
            }
        }
        return range;
    }
}

// 3.            ?class SidePanel extends JPanel {
    private GameScreen gameScreen;
    private JLabel turnLabel;
    private JLabel timerLabel;
    private JButton undoBtn;
    private JButton replayBtn, replayPrevBtn, replayNextBtn, replayStopBtn;
    private JCheckBox timerCheckBox, fogCheckBox;
    private boolean timerForced = false;
    private boolean fogForced = false;
        
    public SidePanel(GameScreen screen) {
        this.gameScreen = screen;
        setPreferredSize(new Dimension(220, 640));
        setOpaque(false); //                
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //                   ?        turnLabel = new JLabel("Turn: White");
        turnLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        turnLabel.setForeground(Color.WHITE);
        turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        timerLabel = new JLabel("00:00 / 00:00");
        timerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //          
        JButton resetBtn = createButton("Restart Game");
        resetBtn.addActionListener(e -> gameScreen.resetGame());
        
        undoBtn = createButton("Undo (3)");
        undoBtn.addActionListener(e -> gameScreen.undo());
        
        //                  
        replayBtn = createButton("Replay");
        replayBtn.addActionListener(e -> gameScreen.startReplay());
        
        replayPrevBtn = createButton("Prev");
        replayPrevBtn.addActionListener(e -> gameScreen.replayPrev());
        replayPrevBtn.setEnabled(false);
        
        replayNextBtn = createButton("Next");
        replayNextBtn.addActionListener(e -> gameScreen.replayNext());
        replayNextBtn.setEnabled(false);
        
        replayStopBtn = createButton("Stop Replay");
        replayStopBtn.addActionListener(e -> gameScreen.stopReplay());
        replayStopBtn.setEnabled(false);
        
        //          
        timerCheckBox = new JCheckBox("Timer");
        timerCheckBox.setForeground(new Color(255, 255, 255, 220));
        timerCheckBox.setOpaque(false);
        timerCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        timerCheckBox.addActionListener(e -> {
            if (!timerForced) {
                gameScreen.setTimerEnabled(timerCheckBox.isSelected());
            }
        });
        UIAudio.bindClickSound(timerCheckBox);
        timerCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                MusicManager.playSoundEffect("ui_hover");
            }
        });

        fogCheckBox = new JCheckBox("Fog of War");
        fogCheckBox.setForeground(new Color(255, 255, 255, 220));
        fogCheckBox.setOpaque(false);
        fogCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        fogCheckBox.addActionListener(e -> {
            if (!fogForced) {
                gameScreen.setFogOfWarEnabled(fogCheckBox.isSelected());
            }
        });
        UIAudio.bindClickSound(fogCheckBox);
        fogCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                MusicManager.playSoundEffect("ui_hover");
            }
        });
        
        JButton menuBtn = createButton("Back to Menu");
        menuBtn.addActionListener(e -> gameScreen.backToMenu());

        add(Box.createVerticalGlue());
        add(turnLabel);
        add(Box.createVerticalStrut(10));
        add(timerLabel);
        add(Box.createVerticalStrut(20));
        add(resetBtn);
        add(Box.createVerticalStrut(10));
        add(undoBtn);
        add(Box.createVerticalStrut(10));
        add(replayBtn);
        add(Box.createVerticalStrut(5));
        add(replayPrevBtn);
        add(Box.createVerticalStrut(5));
        add(replayNextBtn);
        add(Box.createVerticalStrut(5));
        add(replayStopBtn);
        add(Box.createVerticalStrut(10));
        add(timerCheckBox);
        add(Box.createVerticalStrut(5));
        add(fogCheckBox);
        add(Box.createVerticalStrut(10));
        add(menuBtn);
        add(Box.createVerticalGlue());
    }
    
    public void updateUndoButton() {
        undoBtn.setText("Undo (" + gameScreen.getUndoCount() + ")");
        undoBtn.setEnabled(gameScreen.getUndoCount() > 0 && !gameScreen.isReplayMode());
    }

    public void updateTurn(boolean isWhite) {
        turnLabel.setText(isWhite ? "Turn: White" : "Turn: Black");
        turnLabel.setForeground(isWhite ? Color.WHITE : new Color(170, 170, 170));
    }
    
    public void updateTimer(int whiteSeconds, int blackSeconds) {
        String whiteTime = String.format("%d:%02d", whiteSeconds / 60, whiteSeconds % 60);
        String blackTime = String.format("%d:%02d", blackSeconds / 60, blackSeconds % 60);
        timerLabel.setText(whiteTime + " / " + blackTime);
    }
    
    public void updateReplayControls(boolean inReplay) {
        replayPrevBtn.setEnabled(inReplay);
        replayNextBtn.setEnabled(inReplay);
        replayStopBtn.setEnabled(inReplay);
        replayBtn.setEnabled(!inReplay);
    }
    
    //                                                               
    public void setTimerEnabled(boolean enabled, boolean forced) {
        this.timerForced = forced;
        timerCheckBox.setSelected(enabled);
        timerCheckBox.setEnabled(!forced); //                                              
        if (forced) {
            timerCheckBox.setForeground(new Color(200, 200, 200, 150)); //                            
        } else {
            timerCheckBox.setForeground(new Color(255, 255, 255, 220));
        }
    }
    
    //                                                                     
    public void setFogOfWarEnabled(boolean enabled, boolean forced) {
        this.fogForced = forced;
        fogCheckBox.setSelected(enabled);
        fogCheckBox.setEnabled(!forced); //                                              
        if (forced) {
            fogCheckBox.setForeground(new Color(200, 200, 200, 150)); //                            
        } else {
            fogCheckBox.setForeground(new Color(255, 255, 255, 220));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        //                         
        g2.setColor(new Color(40, 44, 52, 200));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        
        //                   
        g2.setColor(new Color(97, 218, 251, 30));
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
    }
    
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(180, 40));
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setForeground(new Color(255, 255, 255, 240));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(97, 218, 251, 100), 1, true),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                MusicManager.playSoundEffect("ui_hover");
                btn.setForeground(new Color(97, 218, 251));
                btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(97, 218, 251, 200), 1, true),
                    BorderFactory.createEmptyBorder(8, 15, 8, 15)
                ));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setForeground(new Color(255, 255, 255, 240));
                btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(97, 218, 251, 100), 1, true),
                    BorderFactory.createEmptyBorder(8, 15, 8, 15)
                ));
            }
        });

        UIAudio.bindClickSound(btn);
        return btn;
    }
}

// ====================                             ====================

class ChessBoard extends JPanel {
    private GameLogic logic;
    private GameScreen gameScreen;
    private Point selectedPixel = null; 
    private List<Point> possibleMoves = new ArrayList<>();
    
    //                   
    private AnimationData currentAnimation = null;
    private java.util.Timer animationTimer;
    
    //                   
    private int shakeOffsetX = 0, shakeOffsetY = 0;
    private java.util.Timer shakeTimer;
    
    //                      ?    private int cellSize = 80;
    private int boardOffsetX = 0;
    private int boardOffsetY = 0;
    private int boardSize = 8; //                     ?x8, 16x16, 32x32   ?
    private final Color COLOR_LIGHT = new Color(240, 217, 181, 200);
    private final Color COLOR_DARK = new Color(181, 136, 99, 200);
    private final Color COLOR_HIGHLIGHT = new Color(130, 151, 105, 150);
    private final Color COLOR_VALID_MOVE = new Color(100, 200, 100, 120);
    private final Color COLOR_CAPTURE_MOVE = new Color(255, 50, 50, 180);

    public ChessBoard(GameLogic logic, GameScreen screen) {
        this.logic = logic;
        this.gameScreen = screen;
        setOpaque(false); //                         
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!gameScreen.isPlayerTurn()) return;
                
                updateCellSize();
                int col = (int)((e.getX() - boardOffsetX) / cellSize);
                int row = (int)((e.getY() - boardOffsetY) / cellSize);
                if (col >= 0 && col < boardSize && row >= 0 && row < boardSize) {
                    handleSelection(row, col);
                    repaint();
                }
            }
        });
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateCellSize();
                repaint();
            }
        });
    }
    
    public void setBoardSize(int size) {
        this.boardSize = size;
        //                                                                                                   ?        possibleMoves.clear();
        selectedPixel = null;
        updateCellSize();
        repaint();
    }
    
    //                                                                         ?    public void clearSelection() {
        possibleMoves.clear();
        selectedPixel = null;
        repaint();
    }
    
    private void updateCellSize() {
        int width = getWidth();
        int height = getHeight();
        int minDimension = Math.min(width, height);
        cellSize = minDimension / boardSize;
        boardOffsetX = (width - cellSize * boardSize) / 2;
        boardOffsetY = (height - cellSize * boardSize) / 2;
    }

    private void handleSelection(int r, int c) {
        if (selectedPixel == null) {
            Piece p = logic.getPiece(r, c);
            if (p != null && p.isWhite == logic.isWhiteTurn) {
                selectedPixel = new Point(c, r);
                possibleMoves = logic.getValidMoves(r, c);
            }
        } else {
            boolean moved = false;
            for (Point m : possibleMoves) {
                if (m.x == c && m.y == r) {
                    //                   ?                    Piece targetP = logic.getPiece(r, c);
                    boolean isWin = targetP != null && targetP.type == PieceType.KING;
                    
                    //                            ?                    boolean isCheck = logic.isInCheck(!logic.isWhiteTurn);
                    
                    //                            
                    startAnimation(selectedPixel.y, selectedPixel.x, r, c);
                    
                    Piece captured = logic.movePiece(selectedPixel.y, selectedPixel.x, r, c);
                    moved = true;
                    if (captured != null) {
                        SoundManager.play(SoundEffect.CAPTURE);
                    } else {
                        SoundManager.play(SoundEffect.MOVE);
                    }
                    
                    //                         GN
                    gameScreen.recordMove(selectedPixel.y, selectedPixel.x, r, c, captured);
                    
                    //                                       ?                    if (isCheck) {
                        triggerShake();
                    }
                    
                    if (isWin) {
                        repaint();
                        // movePiece                                                        !logic.isWhiteTurn
                        handleWin(!logic.isWhiteTurn);
                        return;
                    }
                    
                    //                   ?                    Piece p = logic.getPiece(r, c);
                    if (p != null && p.type == PieceType.PAWN) {
                        int boardSize = logic.getBoardSize();
                        if ((p.isWhite && r == 0) || (!p.isWhite && r == boardSize - 1)) {
                            paintImmediately(0,0,getWidth(),getHeight());
                            promotePawn(r, c);
                        }
                    }
                    
                    selectedPixel = null;
                    possibleMoves.clear();
                    gameScreen.onTurnEnd(); //                            
                    break;
                }
            }
            //                                                         ?            if (!moved) {
                selectedPixel = null;
                possibleMoves.clear();
                Piece p = logic.getPiece(r, c);
                if (p != null && p.isWhite == logic.isWhiteTurn) {
                    selectedPixel = new Point(c, r);
                    possibleMoves = logic.getValidMoves(r, c);
                }
            }
        }
    }
    
    //                            
    private void startAnimation(int sr, int sc, int tr, int tc) {
        if (animationTimer != null) {
            animationTimer.cancel();
        }
        
        updateCellSize();
        currentAnimation = new AnimationData(sr, sc, tr, tc, cellSize, boardOffsetX, boardOffsetY);
        animationTimer = new java.util.Timer();
        animationTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                currentAnimation.update();
                SwingUtilities.invokeLater(() -> repaint());
                if (currentAnimation.isComplete()) {
                    animationTimer.cancel();
                    currentAnimation = null;
                }
            }
        }, 0, 16);
    }
    
    public void handleWin(boolean whiteWon) {
        // whiteWon                                  rue=               alse=            ?        String winner = whiteWon ? "White" : "Black";

        //                vE                                     PvP                               ?        if (gameScreen.isVsAI()) {
            boolean humanWon = whiteWon; //                                                       
            SoundManager.play(humanWon ? SoundEffect.WIN : SoundEffect.LOSE);
        } else {
            SoundManager.play(SoundEffect.WIN);
        }
        
        Object[] options = {"New Game", "Undo", "Exit"};
        int n = JOptionPane.showOptionDialog(this,
            winner + " Wins! \nGame Over.",
            "Victory",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]);

        if (n == 0) { // New Game
            gameScreen.resetGame();
        } else if (n == 1) { // Undo
            gameScreen.undo();
        }
    }
    
    //                                                 ?    public void triggerShake() {
        if (shakeTimer != null) {
            shakeTimer.cancel();
        }
        
        shakeOffsetX = 0;
        shakeOffsetY = 0;
        final int[] shakeCount = {0};
        final int MAX_SHAKES = 10;
        
        shakeTimer = new java.util.Timer();
        shakeTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (shakeCount[0] >= MAX_SHAKES) {
                    shakeOffsetX = 0;
                    shakeOffsetY = 0;
                    shakeTimer.cancel();
                    SwingUtilities.invokeLater(() -> repaint());
                    return;
                }
                
                Random rand = new Random();
                shakeOffsetX = (rand.nextInt(7) - 3) * 2;
                shakeOffsetY = (rand.nextInt(7) - 3) * 2;
                shakeCount[0]++;
                SwingUtilities.invokeLater(() -> repaint());
            }
        }, 0, 30);
    }

    private void promotePawn(int r, int c) {
        String[] options = {"Queen", "Rook", "Bishop", "Knight"};
        int choice = JOptionPane.showOptionDialog(this, "Promote Pawn:", "Promotion",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        PieceType[] types = {PieceType.QUEEN, PieceType.ROOK, PieceType.BISHOP, PieceType.KNIGHT};
        if(choice < 0) choice = 0;
        logic.promotePiece(r, c, types[choice]);
        SoundManager.play(SoundEffect.PROMOTE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        //                                  
        g2.setColor(new Color(30, 32, 40, 200));
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        //                           
        if (shakeOffsetX != 0 || shakeOffsetY != 0) {
            g2.translate(shakeOffsetX, shakeOffsetY);
        }

        updateCellSize();
        
        //                   
        List<Point> visibleSquares = null;
        //                                                   ?        if (gameScreen.isFogCurrentlyVisible() && !gameScreen.isReplayMode()) {
            visibleSquares = gameScreen.getAttackRange(logic.isWhiteTurn);
        }
        
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                int x = boardOffsetX + c * cellSize;
                int y = boardOffsetY + r * cellSize;
                
                boolean isVisible = true;
                if (visibleSquares != null) {
                    isVisible = false;
                    for (Point p : visibleSquares) {
                        if (p.x == c && p.y == r) {
                            isVisible = true;
                            break;
                        }
                    }
                }
                
                if (isVisible) {
                    g2.setColor((r + c) % 2 == 0 ? COLOR_LIGHT : COLOR_DARK);
                    g2.fillRect(x, y, cellSize, cellSize);
                    if (selectedPixel != null && selectedPixel.x == c && selectedPixel.y == r) {
                        g2.setColor(COLOR_HIGHLIGHT);
                        g2.fillRect(x, y, cellSize, cellSize);
                    }
                } else {
                    //                                  ?+                                                                            
                    float opacity = gameScreen.getFogOpacity();
                    int a1 = Math.max(0, Math.min(255, (int)(210 * opacity)));
                    int a2 = Math.max(0, Math.min(255, (int)(160 * opacity)));
                    //                                                                                           ?                    if (((r + c) & 1) == 0) {
                        a1 = (int)(a1 * 0.95f);
                        a2 = (int)(a2 * 0.90f);
                    }
                    GradientPaint fogGrad = new GradientPaint(
                        x, y, new Color(0, 0, 0, a1),
                        x + cellSize, y + cellSize, new Color(0, 0, 0, a2)
                    );
                    g2.setPaint(fogGrad);
                    g2.fillRect(x, y, cellSize, cellSize);
                    g2.setPaint(null);
                }
            }
        }
        
        //                                                                                  
        for (Point m : possibleMoves) {
            //                                                       ?            if (m.x < 0 || m.x >= boardSize || m.y < 0 || m.y >= boardSize) {
                continue; //                                                 ?            }
            
            int x = boardOffsetX + m.x * cellSize;
            int y = boardOffsetY + m.y * cellSize;
            
            //                                                                                  ?            if (x < boardOffsetX || x >= boardOffsetX + boardSize * cellSize ||
                y < boardOffsetY || y >= boardOffsetY + boardSize * cellSize) {
                continue; //                                                       
            }
            
            Piece targetPiece = logic.getPiece(m.y, m.x);
            if (targetPiece != null) {
                g2.setColor(COLOR_CAPTURE_MOVE);
                g2.setStroke(new BasicStroke(Math.max(2, cellSize / 16)));
                int padding = cellSize / 16;
                g2.drawOval(x + padding, y + padding, cellSize - padding * 2, cellSize - padding * 2);
                g2.setColor(new Color(255, 50, 50, 60));
                g2.fillOval(x + padding * 2, y + padding * 2, cellSize - padding * 4, cellSize - padding * 4);
            } else {
                g2.setColor(COLOR_VALID_MOVE);
                int dotSize = cellSize / 4;
                g2.fillOval(x + cellSize / 2 - dotSize / 2, y + cellSize / 2 - dotSize / 2, dotSize, dotSize);
            }
        }
        
        //                   
        List<Point> visibleSquaresForPieces = null;
        //                                                   ?        if (gameScreen.isFogCurrentlyVisible() && !gameScreen.isReplayMode()) {
            visibleSquaresForPieces = gameScreen.getAttackRange(logic.isWhiteTurn);
        }
        
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (visibleSquaresForPieces != null) {
                    boolean isVisible = false;
                    for (Point p : visibleSquaresForPieces) {
                        if (p.x == c && p.y == r) {
                            isVisible = true;
                            break;
                        }
                    }
                    if (!isVisible) continue;
                }
                
                if (currentAnimation != null && currentAnimation.sr == r && currentAnimation.sc == c) {
                    continue;
                }
                Piece p = logic.getPiece(r, c);
                if (p != null) {
                    drawPiece(g2, p, r, c);
                }
            }
        }
        
        //                                     
        if (currentAnimation != null) {
            Piece animPiece = logic.getPiece(currentAnimation.tr, currentAnimation.tc);
            if (animPiece != null) {
                drawPieceAt(g2, animPiece, currentAnimation.currentX, currentAnimation.currentY);
            }
        }
    }

    private void drawPiece(Graphics2D g2, Piece p, int r, int c) {
        int x = boardOffsetX + c * cellSize;
        int y = boardOffsetY + r * cellSize;
        drawPieceAt(g2, p, x, y);
    }
    
    private void drawPieceAt(Graphics2D g2, Piece p, float x, float y) {
        int fontSize = (int)(cellSize * 0.8);
        // Use a font known to support symbols well on Windows to hopefully avoid HBShaper issues
        g2.setFont(new Font("Segoe UI Symbol", Font.PLAIN, fontSize));
        String sym = "";
        switch (p.type) {
            case PAWN:   sym = p.isWhite ? "\u2659" : "\u265F"; break;
            case ROOK:   sym = p.isWhite ? "\u2656" : "\u265C"; break;
            case KNIGHT: sym = p.isWhite ? "\u2658" : "\u265E"; break;
            case BISHOP: sym = p.isWhite ? "\u2657" : "\u265D"; break;
            case QUEEN:  sym = p.isWhite ? "\u2655" : "\u265B"; break;
            case KING:   sym = p.isWhite ? "\u2654" : "\u265A"; break;
        }
        FontMetrics fm = g2.getFontMetrics();
        float px = x + (cellSize - fm.stringWidth(sym)) / 2f;
        float py = y + (cellSize - fm.getHeight()) / 2f + fm.getAscent();
        
        //                                              ?        g2.setColor(new Color(0, 0, 0, 100));
        g2.drawString(sym, px + 2, py + 2);
        g2.setColor(p.isWhite ? new Color(255, 255, 255, 220) : new Color(0, 0, 0, 220));
        g2.drawString(sym, px, py);
    }
}

class GameLogic {
    private Piece[][] board;
    private int boardSize = 8;
    public boolean isWhiteTurn = true;
    private Stack<MoveRecord> history = new Stack<>();

    public GameLogic() {
        resetBoard();
    }
    
    public int getBoardSize() {
        return boardSize;
    }

    public void resetBoard() {
        boardSize = 8;
        board = new Piece[8][8];
        history.clear();
        //          
        board[0][0] = new Piece(PieceType.ROOK, false); board[0][7] = new Piece(PieceType.ROOK, false);
        board[0][1] = new Piece(PieceType.KNIGHT, false); board[0][6] = new Piece(PieceType.KNIGHT, false);
        board[0][2] = new Piece(PieceType.BISHOP, false); board[0][5] = new Piece(PieceType.BISHOP, false);
        board[0][3] = new Piece(PieceType.QUEEN, false);
        board[0][4] = new Piece(PieceType.KING, false);
        for(int i=0; i<8; i++) board[1][i] = new Piece(PieceType.PAWN, false);

        //          
        board[7][0] = new Piece(PieceType.ROOK, true); board[7][7] = new Piece(PieceType.ROOK, true);
        board[7][1] = new Piece(PieceType.KNIGHT, true); board[7][6] = new Piece(PieceType.KNIGHT, true);
        board[7][2] = new Piece(PieceType.BISHOP, true); board[7][5] = new Piece(PieceType.BISHOP, true);
        board[7][3] = new Piece(PieceType.QUEEN, true);
        board[7][4] = new Piece(PieceType.KING, true);
        for(int i=0; i<8; i++) board[6][i] = new Piece(PieceType.PAWN, true);
        
        isWhiteTurn = true;
    }
    
    public void resetAmusementBoard(int multiplier) {
        boardSize = 8 * multiplier;
        board = new Piece[boardSize][boardSize];
        history.clear();
        
        int pawnRows = multiplier; //                      ?   ?2         4   ?4   ?        
        //                            
        int blackBackRow = 0;
        
        //                                                          ?        int pieceSpacing = boardSize / 8; //                   
        board[blackBackRow][0] = new Piece(PieceType.ROOK, false);
        board[blackBackRow][boardSize-1] = new Piece(PieceType.ROOK, false);
        board[blackBackRow][pieceSpacing] = new Piece(PieceType.KNIGHT, false);
        board[blackBackRow][boardSize-1-pieceSpacing] = new Piece(PieceType.KNIGHT, false);
        board[blackBackRow][pieceSpacing*2] = new Piece(PieceType.BISHOP, false);
        board[blackBackRow][boardSize-1-pieceSpacing*2] = new Piece(PieceType.BISHOP, false);
        board[blackBackRow][pieceSpacing*3] = new Piece(PieceType.QUEEN, false);
        board[blackBackRow][pieceSpacing*4] = new Piece(PieceType.KING, false);
        
        //                                                                         
        for (int row = 1; row <= pawnRows; row++) {
            for (int col = 0; col < boardSize; col++) {
                board[row][col] = new Piece(PieceType.PAWN, false);
            }
        }
        
        //                            
        int whiteBackRow = boardSize - 1;
        
        board[whiteBackRow][0] = new Piece(PieceType.ROOK, true);
        board[whiteBackRow][boardSize-1] = new Piece(PieceType.ROOK, true);
        board[whiteBackRow][pieceSpacing] = new Piece(PieceType.KNIGHT, true);
        board[whiteBackRow][boardSize-1-pieceSpacing] = new Piece(PieceType.KNIGHT, true);
        board[whiteBackRow][pieceSpacing*2] = new Piece(PieceType.BISHOP, true);
        board[whiteBackRow][boardSize-1-pieceSpacing*2] = new Piece(PieceType.BISHOP, true);
        board[whiteBackRow][pieceSpacing*3] = new Piece(PieceType.QUEEN, true);
        board[whiteBackRow][pieceSpacing*4] = new Piece(PieceType.KING, true);
        
        //                                                                                                            
        //                                  awnRows+1                                                               ?        int whitePawnStartRow = boardSize - 1 - pawnRows;
        for (int row = whitePawnStartRow; row < boardSize - 1; row++) {
            for (int col = 0; col < boardSize; col++) {
                //                                                                                                             
                if (board[row][col] == null) {
                    board[row][col] = new Piece(PieceType.PAWN, true);
                }
            }
        }
        
        isWhiteTurn = true;
    }

    public Piece getPiece(int r, int c) {
        if (r<0||r>=boardSize||c<0||c>=boardSize) return null;
        return board[r][c];
    }

    //                                                                                    ?    public Piece movePiece(int sr, int sc, int tr, int tc) {
        Piece p = board[sr][sc];
        Piece captured = board[tr][tc];
        
        // Record for undo
        history.push(new MoveRecord(sr, sc, tr, tc, p, captured));
        
        board[tr][tc] = p;
        board[sr][sc] = null;
        isWhiteTurn = !isWhiteTurn;
        return captured;
    }

    public void undoLastMove() {
        if (!history.isEmpty()) {
            MoveRecord r = history.pop();
            board[r.sr][r.sc] = r.moved;
            board[r.tr][r.tc] = r.captured;
            
            // Restore moved piece properties if needed?
            // Actually `r.moved` is the object reference.
            // But wait, if pawn upgraded?
            // Upgrade changes type of `r.moved`. We need to revert type too?
            // Simpler: MoveRecord stores piece type too?
            // Or just store upgrading logic. 
            // For now let's assume no upgrade reversion or handle it simply:
            // Since we store Reference `r.moved`, if we changed its type in `promotePiece`, it's changed in `r.moved` too.
            // We need to revert type!
            if (r.moved.type != r.oldType) {
                 r.moved.type = r.oldType;
            }
            
            isWhiteTurn = !isWhiteTurn;
        }
    }
    
    public boolean canUndo() { return !history.isEmpty(); }

    //                   
    public void undoMove(int sr, int sc, int tr, int tc, Piece captured) {
        Piece p = board[tr][tc];
        board[sr][sc] = p;
        board[tr][tc] = captured;
        isWhiteTurn = !isWhiteTurn; //                   
    }
    
    //                                                    
    public boolean isInCheck(boolean isWhite) {
        //                            
        int kingR = -1, kingC = -1;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                Piece p = board[r][c];
                if (p != null && p.type == PieceType.KING && p.isWhite == isWhite) {
                    kingR = r;
                    kingC = c;
                    break;
                }
            }
            if (kingR != -1) break;
        }
        
        if (kingR == -1) return false;
        
        //                                                                         ?        boolean opponentIsWhite = !isWhite;
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                Piece p = board[r][c];
                if (p != null && p.isWhite == opponentIsWhite) {
                    List<Point> moves = getValidMoves(r, c);
                    for (Point m : moves) {
                        if (m.y == kingR && m.x == kingC) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public void promotePiece(int r, int c, PieceType type) {
        if(board[r][c] != null) board[r][c].type = type;
    }

    public List<Point> getValidMoves(int r, int c) {
        List<Point> moves = new ArrayList<>();
        Piece p = board[r][c];
        if (p == null) return moves;

        switch (p.type) {
            case PAWN:
                int dir = p.isWhite ? -1 : 1;
                //                                                                                              ?                //       musement                                                                                                                                                                                       ?                int pawnRows = boardSize / 8; //                      ?   ?2         4   ?4         
                int startRow;
                if (p.isWhite) {
                    //                                                    awnRows+1                                                      ?                    //             ?6x16               awnRows=2                        ow 13   ?4                     ?3
                    startRow = boardSize - 1 - pawnRows;
                } else {
                    //                                                       ?pawnRows                                                      
                    //             ?6x16               awnRows=2                        ow 1   ?                     ?
                    //                                                                                  
                    startRow = pawnRows;
                }
                
                //                   
                if (getPiece(r + dir, c) == null) {
                    moves.add(new Point(c, r + dir));
                    //                                                                  ?                    if (r == startRow && getPiece(r + 2 * dir, c) == null)
                        moves.add(new Point(c, r + 2 * dir));
                }
                //                                                                               
                checkCapture(moves, p, r + dir, c - 1);
                checkCapture(moves, p, r + dir, c + 1);
                break;
            case ROOK:
                addLines(moves, p, r, c, 1, 0); addLines(moves, p, r, c, -1, 0);
                addLines(moves, p, r, c, 0, 1); addLines(moves, p, r, c, 0, -1);
                break;
            case BISHOP:
                addLines(moves, p, r, c, 1, 1); addLines(moves, p, r, c, 1, -1);
                addLines(moves, p, r, c, -1, 1); addLines(moves, p, r, c, -1, -1);
                break;
            case QUEEN:
                addLines(moves, p, r, c, 1, 0); addLines(moves, p, r, c, -1, 0);
                addLines(moves, p, r, c, 0, 1); addLines(moves, p, r, c, 0, -1);
                addLines(moves, p, r, c, 1, 1); addLines(moves, p, r, c, 1, -1);
                addLines(moves, p, r, c, -1, 1); addLines(moves, p, r, c, -1, -1);
                break;
            case KNIGHT:
                int[][] k = {{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};
                for (int[] m : k) checkMove(moves, p, r + m[0], c + m[1]);
                break;
            case KING:
                for (int dr = -1; dr <= 1; dr++)
                    for (int dc = -1; dc <= 1; dc++)
                        if (dr!=0 || dc!=0) checkMove(moves, p, r + dr, c + dc);
                break;
        }
        return moves;
    }

    private void addLines(List<Point> moves, Piece p, int r, int c, int dr, int dc) {
        int nr = r + dr, nc = c + dc;
        while (nr>=0 && nr<boardSize && nc>=0 && nc<boardSize) {
            Piece t = board[nr][nc];
            if (t == null) moves.add(new Point(nc, nr));
            else {
                if (t.isWhite != p.isWhite) moves.add(new Point(nc, nr));
                break;
            }
            nr += dr; nc += dc;
        }
    }
    private void checkMove(List<Point> moves, Piece p, int r, int c) {
        if (r>=0 && r<boardSize && c>=0 && c<boardSize) {
            Piece t = board[r][c];
            if (t == null || t.isWhite != p.isWhite) moves.add(new Point(c, r));
        }
    }
    private void checkCapture(List<Point> moves, Piece p, int r, int c) {
        if (r>=0 && r<boardSize && c>=0 && c<boardSize) {
            Piece t = board[r][c];
            if (t != null && t.isWhite != p.isWhite) moves.add(new Point(c, r));
        }
    }
}

/**
 * ChessAIController
 *
 * External engine controller using the UCI protocol (e.g. Stockfish).
 * - Process management: start/stop engine process with ProcessBuilder.
 * - FEN conversion: convert current GameLogic board to FEN string.
 * - Asynchronous search: run "position fen [FEN]" + "go depth [N]" in a worker thread.
 * - Result parsing: parse "bestmove [move]" (e.g. e2e4) and convert it to Move.
 * - Difficulty control: map Skill Level (0-20) to search depth.
 * - Error handling: handle missing engine binary or crashes and clean up on JVM shutdown.
 */
class ChessAIController {

    public interface AIResultListener {
        void onBestMoveComputed(Move bestMove);
        void onFailure(Exception e);
    }

    private final String enginePath;
    private Process process;
    private BufferedWriter engineWriter;
    private BufferedReader engineReader;
    private final Object ioLock = new Object();
    private volatile boolean running = false;

    public ChessAIController(String enginePath) throws IOException {
        this.enginePath = enginePath;
        startEngine();
    }

    private void startEngine() throws IOException {
        File engineFile = new File(enginePath);
        if (!engineFile.exists() || !engineFile.isFile()) {
            throw new IOException("Stockfish engine not found at: " + engineFile.getAbsolutePath());
        }

        ProcessBuilder pb = new ProcessBuilder(engineFile.getAbsolutePath());
        pb.redirectErrorStream(true);
        process = pb.start();
        running = true;

        engineWriter = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        engineReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        // Ensure the engine process is destroyed when the JVM exits
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown, "Stockfish-Shutdown"));

        // Initialize UCI
        sendCommand("uci");
        readUntilStartsWith("uciok");
        sendCommand("isready");
        readUntilStartsWith("readyok");
    }

    private void sendCommand(String cmd) throws IOException {
        synchronized (ioLock) {
            if (!running) return;
            engineWriter.write(cmd);
            engineWriter.newLine();
            engineWriter.flush();
        }
    }

    private void readUntilStartsWith(String token) throws IOException {
        String line;
        while ((line = engineReader.readLine()) != null) {
            if (line.startsWith(token)) {
                return;
            }
        }
        throw new IOException("Engine terminated before sending: " + token);
    }

    /**
     * Set engine Skill Level (0-20).
     */
    public void setSkillLevel(int level) {
        if (!running) return;
        int clamped = Math.max(0, Math.min(20, level));
        try {
            sendCommand("setoption name Skill Level value " + clamped);
        } catch (IOException e) {
            // Ignore; engine might have exited
        }
    }

    /**
     * Map Skill Level (0-20) to search depth.
     *
     * Skill Level  Depth    Difficulty label     Approx Elo   Description
     * 0           1-2      Beginner             ~800         Blunders frequently.
     * 5           5        Intermediate         ~1500        Club player.
     * 10          10       Advanced             ~2200        Strong master.
     * 15          15       Master               ~2800        Super-GM level.
     * 20          20+      God-like             3500+        Practically unbeatable.
     */
    public static int mapSkillLevelToDepth(int skillLevel) {
        if (skillLevel <= 0) return 2;
        if (skillLevel <= 4) return 4;
        if (skillLevel == 5) return 5;
        if (skillLevel <= 9) return 8;
        if (skillLevel == 10) return 10;
        if (skillLevel <= 14) return 12;
        if (skillLevel == 15) return 15;
        if (skillLevel <= 19) return 18;
        return 22; // Skill 20 and above
    }

    /**
     * Asynchronously request best move from the engine using current GameLogic state.
     * The result is delivered via AIResultListener on a background thread; UI code
     * should marshal back to Swing's EDT (as done in GameScreen).
     */
    public void requestBestMoveAsync(GameLogic logic, int skillLevel, AIResultListener listener) {
        if (!running || listener == null) return;
        if (logic.getBoardSize() != 8) {
            listener.onFailure(new IllegalStateException("External engine supports only 8x8 boards."));
            return;
        }

        new Thread(() -> {
            try {
                String fen = toFEN(logic);
                int depth = mapSkillLevelToDepth(skillLevel);

                setSkillLevel(skillLevel);

                synchronized (ioLock) {
                    sendCommand("position fen " + fen);
                    sendCommand("go depth " + depth);
                }

                String bestMoveLine = null;
                String line;
                while ((line = engineReader.readLine()) != null) {
                    if (line.startsWith("bestmove")) {
                        bestMoveLine = line;
                        break;
                    }
                }

                if (bestMoveLine == null) {
                    listener.onFailure(new IOException("Engine did not return bestmove."));
                    return;
                }

                String[] parts = bestMoveLine.split("\\s+");
                if (parts.length < 2 || "0000".equals(parts[1])) {
                    listener.onFailure(new IOException("Engine returned no legal move."));
                    return;
                }

                String uciMove = parts[1];
                Move move = uciToMove(uciMove, logic);
                if (move == null) {
                    listener.onFailure(new IOException("Failed to convert UCI move: " + uciMove));
                } else {
                    listener.onBestMoveComputed(move);
                }
            } catch (Exception ex) {
                listener.onFailure(ex);
            }
        }, "Stockfish-Worker").start();
    }

    /**
     * Convert current board in GameLogic to a simple FEN string.
     * Note: castling rights and en-passant are not tracked in this game,
     * so they are always set to "-" which is acceptable for engine play.
     */
    public String toFEN(GameLogic logic) {
        int size = logic.getBoardSize();
        if (size != 8) {
            throw new IllegalStateException("FEN conversion only supports 8x8 boards.");
        }

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < 8; r++) {
            int emptyCount = 0;
            for (int c = 0; c < 8; c++) {
                Piece p = logic.getPiece(r, c);
                if (p == null) {
                    emptyCount++;
                } else {
                    if (emptyCount > 0) {
                        sb.append(emptyCount);
                        emptyCount = 0;
                    }
                    char ch = switch (p.type) {
                        case PAWN -> 'p';
                        case ROOK -> 'r';
                        case KNIGHT -> 'n';
                        case BISHOP -> 'b';
                        case QUEEN -> 'q';
                        case KING -> 'k';
                    };
                    if (p.isWhite) {
                        ch = Character.toUpperCase(ch);
                    }
                    sb.append(ch);
                }
            }
            if (emptyCount > 0) {
                sb.append(emptyCount);
            }
            if (r != 7) {
                sb.append('/');
            }
        }

        // Side to move
        char side = logic.isWhiteTurn ? 'w' : 'b';

        // No castling, no en passant, zero halfmove clock, fullmove number 1
        return sb + " " + side + " - - 0 1";
    }

    /**
     * Convert a UCI move like "e2e4" or "g7g8q" into an internal Move.
     * Promotion piece is ignored here; GameLogic will handle promotion.
     */
    public Move uciToMove(String uci, GameLogic logic) {
        if (uci == null || uci.length() < 4) return null;
        try {
            int fromFile = uci.charAt(0) - 'a';
            int fromRank = uci.charAt(1) - '0';
            int toFile = uci.charAt(2) - 'a';
            int toRank = uci.charAt(3) - '0';

            // FEN / UCI ranks: '1' is bottom (row 7), '8' is top (row 0)
            int fromRow = 8 - fromRank;
            int fromCol = fromFile;
            int toRow = 8 - toRank;
            int toCol = toFile;

            int size = logic.getBoardSize();
            if (fromRow < 0 || fromRow >= size || toRow < 0 || toRow >= size ||
                fromCol < 0 || fromCol >= size || toCol < 0 || toCol >= size) {
                return null;
            }

            return new Move(fromRow, fromCol, toRow, toCol);
        } catch (Exception e) {
            return null;
        }
    }

    public void shutdown() {
        running = false;
        try {
            if (engineWriter != null) {
                engineWriter.close();
            }
        } catch (IOException ignored) {}
        try {
            if (engineReader != null) {
                engineReader.close();
            }
        } catch (IOException ignored) {}
        if (process != null) {
            process.destroy();
        }
    }
}

class Move {
    int sr, sc, tr, tc;
    public Move(int sr, int sc, int tr, int tc) {
        this.sr=sr; this.sc=sc; this.tr=tr; this.tc=tc;
    }
}

/**
 * AmusementChessAI
 *
 * Lightweight minimax-based AI used only for Amusement mode and non-8x8 boards.
 * It uses the same Skill Level -> depth mapping as the Stockfish controller,
 * but with an internal evaluation function that favours fun, dynamic play
 * (center control, pawn advancement, mobility, king safety).
 */
class AmusementChessAI {

    public Move findBestMove(GameLogic logic, int depth) {
        // AI always plays Black (false) in this game design.
        java.util.List<Move> allMoves = generateAllMoves(logic, false);
        if (allMoves.isEmpty()) return null;

        Move bestMove = null;
        int maxEval = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (Move move : allMoves) {
            Piece captured = logic.movePiece(move.sr, move.sc, move.tr, move.tc);
            int eval = minimax(logic, depth - 1, alpha, beta);
            logic.undoMove(move.sr, move.sc, move.tr, move.tc, captured);

            if (eval > maxEval) {
                maxEval = eval;
                bestMove = move;
            }
            alpha = Math.max(alpha, eval);
            if (beta <= alpha) break;
        }
        return bestMove;
    }

    private int minimax(GameLogic logic, int depth, int alpha, int beta) {
        if (depth == 0) return evaluateBoard(logic);

        boolean isWhiteTurn = logic.isWhiteTurn;
        java.util.List<Move> moves = generateAllMoves(logic, isWhiteTurn);
        if (moves.isEmpty()) {
            // No legal moves: treat as losing position for the side to move.
            return isWhiteTurn ? Integer.MAX_VALUE / -2 : Integer.MAX_VALUE / 2;
        }

        if (isWhiteTurn) {
            // White is the human side -> minimizing player.
            int minEval = Integer.MAX_VALUE;
            for (Move move : moves) {
                Piece captured = logic.movePiece(move.sr, move.sc, move.tr, move.tc);
                int eval = minimax(logic, depth - 1, alpha, beta);
                logic.undoMove(move.sr, move.sc, move.tr, move.tc, captured);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) break;
            }
            return minEval;
        } else {
            // Black is the AI side -> maximizing player.
            int maxEval = Integer.MIN_VALUE;
            for (Move move : moves) {
                Piece captured = logic.movePiece(move.sr, move.sc, move.tr, move.tc);
                int eval = minimax(logic, depth - 1, alpha, beta);
                logic.undoMove(move.sr, move.sc, move.tr, move.tc, captured);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) break;
            }
            return maxEval;
        }
    }

    private int evaluateBoard(GameLogic logic) {
        int score = 0;
        int boardSize = logic.getBoardSize();
        boolean isAmusementMode = boardSize > 8;

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                Piece p = logic.getPiece(r, c);
                if (p != null) {
                    int val = getPieceValue(p.type);

                    // Base material value
                    if (!p.isWhite) score += val;
                    else score -= val;

                    if (isAmusementMode) {
                        // 1. Center control bonus
                        int centerBonus = getCenterControlBonus(r, c, boardSize);
                        if (!p.isWhite) score += centerBonus;
                        else score -= centerBonus;

                        // 2. Pawn advancement bonus
                        if (p.type == PieceType.PAWN) {
                            int pawnAdvanceBonus = getPawnAdvanceBonus(r, p.isWhite, boardSize);
                            if (!p.isWhite) score += pawnAdvanceBonus;
                            else score -= pawnAdvanceBonus;
                        }

                        // 3. Mobility bonus
                        int mobility = logic.getValidMoves(r, c).size();
                        int mobilityBonus = mobility * 2;
                        if (!p.isWhite) score += mobilityBonus;
                        else score -= mobilityBonus;

                        // 4. King safety
                        if (p.type == PieceType.KING) {
                            int safetyBonus = getKingSafetyBonus(logic, r, c, p.isWhite, boardSize);
                            if (!p.isWhite) score += safetyBonus;
                            else score -= safetyBonus;
                        }
                    }
                }
            }
        }

        // Check bonuses
        if (logic.isInCheck(true)) { // White in check
            score += 50;
        }
        if (logic.isInCheck(false)) { // Black in check
            score -= 50;
        }

        return score;
    }

    private int getCenterControlBonus(int r, int c, int boardSize) {
        int centerR = boardSize / 2;
        int centerC = boardSize / 2;
        int distFromCenter = Math.abs(r - centerR) + Math.abs(c - centerC);
        int maxDist = boardSize;
        return Math.max(0, 5 - (distFromCenter * 5 / maxDist));
    }

    private int getPawnAdvanceBonus(int r, boolean isWhite, int boardSize) {
        if (isWhite) {
            int distanceFromTop = r;
            return (boardSize - distanceFromTop) * 2;
        } else {
            int distanceFromBottom = boardSize - 1 - r;
            return (boardSize - distanceFromBottom) * 2;
        }
    }

    private int getKingSafetyBonus(GameLogic logic, int kingR, int kingC, boolean isWhite, int boardSize) {
        int safety = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                int nr = kingR + dr;
                int nc = kingC + dc;
                if (nr >= 0 && nr < boardSize && nc >= 0 && nc < boardSize) {
                    Piece p = logic.getPiece(nr, nc);
                    if (p != null && p.isWhite == isWhite) {
                        safety += 3;
                    }
                }
            }
        }
        return safety;
    }

    private int getPieceValue(PieceType type) {
        return switch (type) {
            case PAWN -> 10;
            case KNIGHT -> 30;
            case BISHOP -> 30;
            case ROOK -> 50;
            case QUEEN -> 90;
            case KING -> 900;
        };
    }

    private java.util.List<Move> generateAllMoves(GameLogic logic, boolean isWhite) {
        java.util.List<Move> all = new java.util.ArrayList<>();
        int boardSize = logic.getBoardSize();
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                Piece p = logic.getPiece(r, c);
                if (p != null && p.isWhite == isWhite) {
                    java.util.List<Point> dests = logic.getValidMoves(r, c);
                    for (Point d : dests) {
                        all.add(new Move(r, c, d.y, d.x));
                    }
                }
            }
        }
        return all;
    }
}

class MoveRecord {
    int sr, sc, tr, tc;
    Piece moved, captured;
    PieceType oldType;
    public MoveRecord(int sr, int sc, int tr, int tc, Piece moved, Piece captured) {
        this.sr=sr; this.sc=sc; this.tr=tr; this.tc=tc; 
        this.moved=moved; this.captured=captured;
        this.oldType = moved.type;
    }
}

// PGN                  
class PGNMove {
    String notation;
    int sr, sc, tr, tc;
    Piece captured;
    
    public PGNMove(String notation, int sr, int sc, int tr, int tc, Piece captured) {
        this.notation = notation;
        this.sr = sr;
        this.sc = sc;
        this.tr = tr;
        this.tc = tc;
        this.captured = captured;
    }
}

enum PieceType { PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING }

class Piece {
    public PieceType type;
    public boolean isWhite;
    public Piece(PieceType type, boolean isWhite) {
        this.type = type;
        this.isWhite = isWhite;
    }
}

//                      ?class AnimationData {
    int sr, sc, tr, tc;
    float startX, startY, targetX, targetY;
    float currentX, currentY;
    private static final float LERP_FACTOR = 0.15f;
    
    public AnimationData(int sr, int sc, int tr, int tc, int cellSize, int offsetX, int offsetY) {
        this.sr = sr;
        this.sc = sc;
        this.tr = tr;
        this.tc = tc;
        this.startX = offsetX + sc * cellSize;
        this.startY = offsetY + sr * cellSize;
        this.targetX = offsetX + tc * cellSize;
        this.targetY = offsetY + tr * cellSize;
        this.currentX = startX;
        this.currentY = startY;
    }
    
    public void update() {
        currentX += (targetX - currentX) * LERP_FACTOR;
        currentY += (targetY - currentY) * LERP_FACTOR;
    }
    
    public boolean isComplete() {
        return Math.abs(currentX - targetX) < 1 && Math.abs(currentY - targetY) < 1;
    }
}

// ====================                    ====================

enum SoundEffect {
    // volumeScale                                                                     ?hover/click                            
    HOVER("hover.wav", 0.3f),
    CLICK("click.wav", 1.45f),
    MOVE("move.wav", 1.0f),
    CAPTURE("capture.wav", 1.0f),
    PROMOTE("promote.wav", 1.0f),
    WIN("win.wav", 1.0f),
    LOSE("lose.wav", 1.0f);
    
    final String fileName;
    final float volumeScale;

    SoundEffect(String fileName, float volumeScale) {
        this.fileName = fileName;
        this.volumeScale = volumeScale;
    }
}

/**
 *                                     ? * -             ?`assets/sounds/`                    wav          
 * -                            /                                             beep                              ? */
class SoundManager {
    private static final Path SOUND_DIR = Paths.get("assets", "sounds");
    private static final ConcurrentHashMap<String, Boolean> missingCache = new ConcurrentHashMap<>();
    private static final Preferences PREFS = Preferences.userRoot().node("ModernChess");
    private static volatile float volume = clamp01(PREFS.getFloat("sound.volume", 0.8f));

    /**
     *                               ?                                       ?beep   ?     *                                                                                                                                        ?Dchess.beepOnMissing=true
     */
    private static final boolean BEEP_ON_MISSING = Boolean.parseBoolean(
            System.getProperty("chess.beepOnMissing", "false")
    );
    
    public static void play(SoundEffect effect) {
        if (effect == null) return;
        playFile(effect.fileName, effect.volumeScale);
    }

    public static float getVolume() {
        return volume;
    }

    public static void setVolume(float v) {
        float nv = clamp01(v);
        volume = nv;
        try {
            PREFS.putFloat("sound.volume", nv);
        } catch (Exception ignored) {
            // Preferences                                                                            ?        }
    }

    private static float clamp01(float v) {
        if (Float.isNaN(v)) return 0.8f;
        if (v < 0f) return 0f;
        if (v > 1f) return 1f;
        return v;
    }

    private static float clamp(float v, float min, float max) {
        if (Float.isNaN(v)) return min;
        if (v < min) return min;
        if (v > max) return max;
        return v;
    }

    private static void applyVolumeIfSupported(Clip clip, float effectScale) {
        if (clip == null) return;
        try {
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                // volume 0..1 -> dB   ?                                                       ?20*log10(v)
                //                                        ?1            ?click                                                                   ?                float v = clamp(volume * effectScale, 0f, 2.0f);
                float dB = (v <= 0.0001f) ? gain.getMinimum() : (float) (20.0 * Math.log10(v));
                dB = Math.max(gain.getMinimum(), Math.min(gain.getMaximum(), dB));
                gain.setValue(dB);
            }
        } catch (Exception ignored) {
        }
    }
    
    private static void playFile(String fileName, float effectScale) {
        if (fileName == null || fileName.isEmpty()) return;
        String key = SOUND_DIR.resolve(fileName).toString();
        if (Boolean.TRUE.equals(missingCache.get(key))) {
            if (BEEP_ON_MISSING) Toolkit.getDefaultToolkit().beep();
            return;
        }
        
        File file = new File(key);
        if (!file.exists() || !file.isFile()) {
            missingCache.put(key, true);
            System.err.println("[SoundManager] Missing sound file: " + file.getAbsolutePath());
            if (BEEP_ON_MISSING) Toolkit.getDefaultToolkit().beep();
            return;
        }
        
        //                                      UI
        new Thread(() -> {
            try (AudioInputStream ais = AudioSystem.getAudioInputStream(file)) {
                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                applyVolumeIfSupported(clip, effectScale);
                clip.addLineListener(ev -> {
                    if (ev.getType() == LineEvent.Type.STOP || ev.getType() == LineEvent.Type.CLOSE) {
                        try { clip.close(); } catch (Exception ignored) {}
                    }
                });
                clip.start();
            } catch (Exception e) {
                //                                            missing                                             /                                                               
                System.err.println("[SoundManager] Failed to play sound: " + file.getAbsolutePath());
                e.printStackTrace();
                if (BEEP_ON_MISSING) Toolkit.getDefaultToolkit().beep();
            }
        }, "sound-" + fileName).start();
    }
}

/**
 *                    / UI                                                                 UI                      ? */
class MusicManager {
    public static void playSoundEffect(String key) {
        if (key == null) return;
        switch (key) {
            case "ui_hover":
                SoundManager.play(SoundEffect.HOVER);
                break;
            default:
                //           key                      ?                break;
        }
    }
}

/**
 * UI                                     
 */
class UIAudio {
    public static void bindClickSound(AbstractButton button) {
        if (button == null) return;
        //                                                                            ?                                                              
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (button.isEnabled()) {
                    SoundManager.play(SoundEffect.CLICK);
                }
            }
        });
    }
}

 
 / /   1 . 8   ZJT      3 !}   Y   ^p        ( P V P / P V E ) 
 
 c l a s s   G a m e M o d e S e l e c t i o n S c r e e n   e x t e n d s   J P a n e l   { 
 
         p r i v a t e   C h e s s G a m e   m a i n F r a m e ; 
 
 
 
         p u b l i c   G a m e M o d e S e l e c t i o n S c r e e n ( C h e s s G a m e   f r a m e )   { 
 
                 t h i s . m a i n F r a m e   =   f r a m e ; 
 
                 s e t L a y o u t ( n e w   B o r d e r L a y o u t ( ) ) ; 
 
                 s e t B a c k g r o u n d ( n e w   C o l o r ( 2 6 ,   2 8 ,   3 6 ) ) ; 
 
 
 
                 / /   H e a d e r 
 
                 J L a b e l   h e a d e r   =   n e w   J L a b e l ( " S E L E C T   G A M E   M O D E " ,   S w i n g C o n s t a n t s . C E N T E R ) ; 
 
                 h e a d e r . s e t F o n t ( n e w   F o n t ( " S e g o e   U I " ,   F o n t . B O L D ,   4 8 ) ) ; 
 
                 h e a d e r . s e t F o r e g r o u n d ( n e w   C o l o r ( 2 2 0 ,   2 2 0 ,   2 2 0 ) ) ; 
 
                 h e a d e r . s e t B o r d e r ( B o r d e r F a c t o r y . c r e a t e E m p t y B o r d e r ( 6 0 ,   0 ,   4 0 ,   0 ) ) ; 
 
                 a d d ( h e a d e r ,   B o r d e r L a y o u t . N O R T H ) ; 
 
 
 
                 / /   C e n t e r   C o n t a i n e r 
 
                 J P a n e l   c e n t e r W r a p p e r   =   n e w   J P a n e l ( n e w   G r i d B a g L a y o u t ( ) ) ; 
 
                 c e n t e r W r a p p e r . s e t O p a q u e ( f a l s e ) ; 
 
                 
 
                 J P a n e l   c a r d s P a n e l   =   n e w   J P a n e l ( n e w   G r i d L a y o u t ( 1 ,   2 ,   3 0 ,   0 ) ) ;   / /   2   c o l u m n s 
 
                 c a r d s P a n e l . s e t O p a q u e ( f a l s e ) ; 
 
 
 
                 / /   O p t i o n s 
 
                 S t r i n g [ ] [ ]   o p t i o n s   =   { 
 
                         { " P L A Y E R   v s   P L A Y E R " ,   " L o c a l   m u l t i p l a y e r \ n P l a y   w i t h   a   f r i e n d " } , 
 
                         { " P L A Y E R   v s   A I " ,   " C h a l l e n g e   t h e   e n g i n e \ n S e l e c t   d i f f i c u l t y   n e x t " } 
 
                 } ; 
 
 
 
                 f o r   ( i n t   i   =   0 ;   i   <   o p t i o n s . l e n g t h ;   i + + )   { 
 
                         S t r i n g   t i t l e   =   o p t i o n s [ i ] [ 0 ] ; 
 
                         S t r i n g   d e s c   =   o p t i o n s [ i ] [ 1 ] ; 
 
                         b o o l e a n   i s A I   =   ( i   = =   1 ) ; 
 
 
 
                         J B u t t o n   b t n   =   n e w   J B u t t o n ( ) ; 
 
                         b t n . s e t F o c u s P a i n t e d ( f a l s e ) ; 
 
                         b t n . s e t C o n t e n t A r e a F i l l e d ( f a l s e ) ; 
 
                         b t n . s e t O p a q u e ( t r u e ) ; 
 
                         b t n . s e t P r e f e r r e d S i z e ( n e w   D i m e n s i o n ( 3 0 0 ,   4 0 0 ) ) ; 
 
                         b t n . s e t C u r s o r ( n e w   C u r s o r ( C u r s o r . H A N D _ C U R S O R ) ) ; 
 
 
 

 
 / /   1 . 8   ZJT   W   Y3 !}      Y   Z^p   _   {  ( P V P / P V E ) 
 
 c l a s s   G a m e M o d e S e l e c t i o n S c r e e n   e x t e n d s   J P a n e l   { 
 
         p r i v a t e   C h e s s G a m e   m a i n F r a m e ; 
 
 
 
         p u b l i c   G a m e M o d e S e l e c t i o n S c r e e n ( C h e s s G a m e   f r a m e )   { 
 
                 t h i s . m a i n F r a m e   =   f r a m e ; 
 
                 s e t L a y o u t ( n e w   B o r d e r L a y o u t ( ) ) ; 
 
                 s e t B a c k g r o u n d ( n e w   C o l o r ( 2 6 ,   2 8 ,   3 6 ) ) ; 
 
 
 
                 / /   H e a d e r 
 
                 J L a b e l   h e a d e r   =   n e w   J L a b e l ( " S E L E C T   G A M E   M O D E " ,   S w i n g C o n s t a n t s . C E N T E R ) ; 
 
                 h e a d e r . s e t F o n t ( n e w   F o n t ( " S e g o e   U I " ,   F o n t . B O L D ,   4 8 ) ) ; 
 
                 h e a d e r . s e t F o r e g r o u n d ( n e w   C o l o r ( 2 2 0 ,   2 2 0 ,   2 2 0 ) ) ; 
 
                 h e a d e r . s e t B o r d e r ( B o r d e r F a c t o r y . c r e a t e E m p t y B o r d e r ( 6 0 ,   0 ,   4 0 ,   0 ) ) ; 
 
                 a d d ( h e a d e r ,   B o r d e r L a y o u t . N O R T H ) ; 
 
 
 
                 / /   C e n t e r   C o n t a i n e r 
 
                 J P a n e l   c e n t e r W r a p p e r   =   n e w   J P a n e l ( n e w   G r i d B a g L a y o u t ( ) ) ; 
 
                 c e n t e r W r a p p e r . s e t O p a q u e ( f a l s e ) ; 
 
                 
 
                 J P a n e l   c a r d s P a n e l   =   n e w   J P a n e l ( n e w   G r i d L a y o u t ( 1 ,   2 ,   3 0 ,   0 ) ) ;   / /   2   c o l u m n s 
 
                 c a r d s P a n e l . s e t O p a q u e ( f a l s e ) ; 
 
 
 
                 / /   O p t i o n s 
 
                 S t r i n g [ ] [ ]   o p t i o n s   =   { 
 
                         { " P L A Y E R   v s   P L A Y E R " ,   " L o c a l   m u l t i p l a y e r \ n P l a y   w i t h   a   f r i e n d " } , 
 
                         { " P L A Y E R   v s   A I " ,   " C h a l l e n g e   t h e   e n g i n e \ n S e l e c t   d i f f i c u l t y   n e x t " } 
 
                 } ; 
 
 
 
                 f o r   ( i n t   i   =   0 ;   i   <   o p t i o n s . l e n g t h ;   i + + )   { 
 
                         S t r i n g   t i t l e   =   o p t i o n s [ i ] [ 0 ] ; 
 
                         S t r i n g   d e s c   =   o p t i o n s [ i ] [ 1 ] ; 
 
                         b o o l e a n   i s A I   =   ( i   = =   1 ) ; 
 
 
 
                         J B u t t o n   b t n   =   n e w   J B u t t o n ( ) ; 
 
                         b t n . s e t F o c u s P a i n t e d ( f a l s e ) ; 
 
                         b t n . s e t C o n t e n t A r e a F i l l e d ( f a l s e ) ; 
 
                         b t n . s e t O p a q u e ( t r u e ) ; 
 
                         b t n . s e t P r e f e r r e d S i z e ( n e w   D i m e n s i o n ( 3 0 0 ,   4 0 0 ) ) ; 
 
                         b t n . s e t C u r s o r ( n e w   C u r s o r ( C u r s o r . H A N D _ C U R S O R ) ) ; 
 
 
 
                         / /   H T M L   S t y l i n g 
 
                         S t r i n g B u i l d e r   h t m l   =   n e w   S t r i n g B u i l d e r ( " < h t m l > < c e n t e r > " ) ; 
 
                         h t m l . a p p e n d ( " < d i v   s t y l e = ' c o l o r : # F F F F F F ;   f o n t - w e i g h t : b o l d ;   f o n t - s i z e : 2 4 p x ; ' > " ) . a p p e n d ( t i t l e ) . a p p e n d ( " < / d i v > " ) ; 
 
                         h t m l . a p p e n d ( " < b r > < d i v   s t y l e = ' c o l o r : # A A A A A A ;   f o n t - s i z e : 1 6 p x ; ' > " ) . a p p e n d ( d e s c . r e p l a c e ( " \ n " ,   " < b r > " ) ) . a p p e n d ( " < / d i v > " ) ; 
 
                         h t m l . a p p e n d ( " < / c e n t e r > < / h t m l > " ) ; 
 
                         b t n . s e t T e x t ( h t m l . t o S t r i n g ( ) ) ; 
 
 
 
                         C o l o r   n o r m a l B g   =   n e w   C o l o r ( 4 5 ,   4 8 ,   5 5 ) ; 
 
                         C o l o r   h o v e r B g   =   n e w   C o l o r ( 5 5 ,   5 8 ,   6 5 ) ; 
 
                         C o l o r   b o r d e r C o l o r   =   n e w   C o l o r ( 8 0 ,   8 3 ,   9 5 ) ; 
 
                         
 
                         b t n . s e t B a c k g r o u n d ( n o r m a l B g ) ; 
 
                         b t n . s e t B o r d e r ( B o r d e r F a c t o r y . c r e a t e C o m p o u n d B o r d e r ( 
 
                                 B o r d e r F a c t o r y . c r e a t e L i n e B o r d e r ( b o r d e r C o l o r ,   1 ) , 
 
                                 B o r d e r F a c t o r y . c r e a t e E m p t y B o r d e r ( 2 0 ,   2 0 ,   2 0 ,   2 0 ) 
 
                         ) ) ; 
 
 
 
                         / /   H o v e r   E f f e c t 
 
                         b t n . a d d M o u s e L i s t e n e r ( n e w   M o u s e A d a p t e r ( )   { 
 
                                 p u b l i c   v o i d   m o u s e E n t e r e d ( M o u s e E v e n t   e )   { 
 
                                         b t n . s e t B a c k g r o u n d ( h o v e r B g ) ; 
 
                                         b t n . s e t B o r d e r ( B o r d e r F a c t o r y . c r e a t e C o m p o u n d B o r d e r ( 
 
                                                 B o r d e r F a c t o r y . c r e a t e L i n e B o r d e r ( n e w   C o l o r ( 1 0 0 ,   1 0 0 ,   1 2 0 ) ,   2 ) , 
 
                                                 B o r d e r F a c t o r y . c r e a t e E m p t y B o r d e r ( 1 9 ,   1 9 ,   1 9 ,   1 9 ) 
 
                                         ) ) ; 
 
                                 } 
 
                                 p u b l i c   v o i d   m o u s e E x i t e d ( M o u s e E v e n t   e )   { 
 
                                         b t n . s e t B a c k g r o u n d ( n o r m a l B g ) ; 
 
                                         b t n . s e t B o r d e r ( B o r d e r F a c t o r y . c r e a t e C o m p o u n d B o r d e r ( 
 
                                                 B o r d e r F a c t o r y . c r e a t e L i n e B o r d e r ( b o r d e r C o l o r ,   1 ) , 
 
                                                 B o r d e r F a c t o r y . c r e a t e E m p t y B o r d e r ( 2 0 ,   2 0 ,   2 0 ,   2 0 ) 
 
                                         ) ) ; 
 
                                 } 
 
                         } ) ; 
 
 
 
                         b t n . a d d A c t i o n L i s t e n e r ( e   - >   { 
 
                                 i f   ( i s A I )   { 
 
                                         / /   G o   t o   d i f f i c u l t y   s e l e c t i o n   ( s e t   a m u s i n g   m o d e   =   t r u e ) 
 
                                         m a i n F r a m e . s h o w D i f f i c u l t y S e l e c t i o n ( t r u e ) ; 
 
                                 }   e l s e   { 
 
                                         / /   S t a r t   P V P   i m m e d i a t e l y 
 
                                         m a i n F r a m e . s t a r t A m u s e m e n t G a m e ( f a l s e ,   0 ) ; 
 
                                 } 
 
                         } ) ; 
 
                         
 
                         c a r d s P a n e l . a d d ( b t n ) ; 
 
                 } 
 
                 
 
                 c e n t e r W r a p p e r . a d d ( c a r d s P a n e l ) ; 
 
                 a d d ( c e n t e r W r a p p e r ,   B o r d e r L a y o u t . C E N T E R ) ; 
 
 
 
                 / /   B o t t o m   -   B a c k   B u t t o n 
 
                 J P a n e l   b o t t o m P a n e l   =   n e w   J P a n e l ( n e w   F l o w L a y o u t ( F l o w L a y o u t . C E N T E R ) ) ; 
 
                 b o t t o m P a n e l . s e t O p a q u e ( f a l s e ) ; 
 
                 b o t t o m P a n e l . s e t B o r d e r ( B o r d e r F a c t o r y . c r e a t e E m p t y B o r d e r ( 2 0 ,   0 ,   4 0 ,   0 ) ) ; 
 
                 
 
                 J B u t t o n   b a c k B t n   =   n e w   J B u t t o n ( " B a c k   t o   M e n u " ) ; 
 
                 b a c k B t n . s e t F o n t ( n e w   F o n t ( " S e g o e   U I " ,   F o n t . P L A I N ,   1 6 ) ) ; 
 
                 b a c k B t n . s e t F o r e g r o u n d ( n e w   C o l o r ( 1 5 0 ,   1 5 0 ,   1 5 0 ) ) ; 
 
                 b a c k B t n . s e t C o n t e n t A r e a F i l l e d ( f a l s e ) ; 
 
                 b a c k B t n . s e t B o r d e r P a i n t e d ( f a l s e ) ; 
 
                 b a c k B t n . s e t F o c u s P a i n t e d ( f a l s e ) ; 
 
                 b a c k B t n . s e t C u r s o r ( n e w   C u r s o r ( C u r s o r . H A N D _ C U R S O R ) ) ; 
 
                 b a c k B t n . a d d M o u s e L i s t e n e r ( n e w   M o u s e A d a p t e r ( )   { 
 
                         p u b l i c   v o i d   m o u s e E n t e r e d ( M o u s e E v e n t   e )   {   b a c k B t n . s e t F o r e g r o u n d ( C o l o r . W H I T E ) ;   } 
 
                         p u b l i c   v o i d   m o u s e E x i t e d ( M o u s e E v e n t   e )   {   b a c k B t n . s e t F o r e g r o u n d ( n e w   C o l o r ( 1 5 0 ,   1 5 0 ,   1 5 0 ) ) ;   } 
 
                 } ) ; 
 
                 b a c k B t n . a d d A c t i o n L i s t e n e r ( e   - >   m a i n F r a m e . s h o w M e n u ( ) ) ; 
 
                 
 
                 b o t t o m P a n e l . a d d ( b a c k B t n ) ; 
 
                 a d d ( b o t t o m P a n e l ,   B o r d e r L a y o u t . S O U T H ) ; 
 
         } 
 
         
 
         @ O v e r r i d e 
 
         p r o t e c t e d   v o i d   p a i n t C o m p o n e n t ( G r a p h i c s   g )   { 
 
                 s u p e r . p a i n t C o m p o n e n t ( g ) ; 
 
                 G r a p h i c s 2 D   g 2   =   ( G r a p h i c s 2 D )   g ; 
 
                 g 2 . s e t R e n d e r i n g H i n t ( R e n d e r i n g H i n t s . K E Y _ A N T I A L I A S I N G ,   R e n d e r i n g H i n t s . V A L U E _ A N T I A L I A S _ O N ) ; 
 
                 G r a d i e n t P a i n t   g r a d i e n t   =   n e w   G r a d i e n t P a i n t ( 
 
                         0 ,   0 ,   n e w   C o l o r ( 2 0 ,   2 2 ,   3 0 ) , 
 
                         0 ,   g e t H e i g h t ( ) ,   n e w   C o l o r ( 3 0 ,   3 2 ,   4 2 ) 
 
                 ) ; 
 
                 g 2 . s e t P a i n t ( g r a d i e n t ) ; 
 
                 g 2 . f i l l R e c t ( 0 ,   0 ,   g e t W i d t h ( ) ,   g e t H e i g h t ( ) ) ; 
 
         } 
 
 } 
 
 
