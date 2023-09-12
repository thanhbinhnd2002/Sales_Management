package View;

import Controller.LoginController;
import Model.Database;
import Model.LoginModel;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Objects;

public class LoginView extends JFrame {
    // attributes
    private int mouseX;
    private int mouseY;

    // components
    private JPanel pnMain;
    private JLabel lblBtnClose;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    private JCheckBox chkShowPassword;
    private JButton btnLogin;

    private JLabel lblSqlConfig;
    private Database database;

    // constructor
    public LoginView() {
        super();
        initComponents();
        addEvents();
        showWindow();
    }

    private void initComponents() {
        // implementation the left panel
        JPanel pnLeft = new JPanel();
        pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));
        pnLeft.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("HỆ THỐNG QUẢN LÝ BÁN HÀNG");
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTitle.setForeground(new Color(25, 118, 211));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        JPanel pnLblLogo = new JPanel();
        pnLblLogo.setLayout(new BorderLayout());
        pnLblLogo.setBackground(Color.WHITE);
        pnLblLogo.add(lblTitle, BorderLayout.CENTER);

        JLabel lblImage = new JLabel();
        lblImage.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/bg-login.svg"))));
        lblImage.setHorizontalAlignment(JLabel.CENTER);
        JPanel pnLblImage = new JPanel();
        pnLblImage.setLayout(new BorderLayout());
        pnLblImage.setBackground(Color.WHITE);
        pnLblImage.add(lblImage, BorderLayout.CENTER);

        JLabel lblDevInfo = new JLabel("Created by Haiqqez");
        lblDevInfo.setFont(new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, 15));
        lblDevInfo.setForeground(new Color(25, 118, 211));
        lblDevInfo.setHorizontalAlignment(JLabel.CENTER);
        JPanel pnLblDevInfo = new JPanel();
        pnLblDevInfo.setLayout(new BorderLayout());
        pnLblDevInfo.setBackground(Color.WHITE);
        pnLblDevInfo.add(lblDevInfo, BorderLayout.CENTER);

        pnLeft.add(Box.createVerticalGlue());
        pnLeft.add(pnLblLogo);
        pnLeft.add(pnLblImage);
        pnLeft.add(pnLblDevInfo);
        pnLeft.add(Box.createVerticalGlue());

        // implementation the right panel
        JPanel pnRight = new JPanel();
        pnRight.setLayout(new BorderLayout());
        pnRight.setBackground(new Color(25, 118, 211));
        pnRight.setPreferredSize(new Dimension(350, 0));

        lblBtnClose = new JLabel();
        lblBtnClose.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images" +
                "/32x32/ic_close_32px.svg"))));
        lblBtnClose.setBorder(BorderFactory.createLineBorder(new Color(25, 118, 211), 2));
        lblBtnClose.setHorizontalAlignment(JLabel.CENTER);
        JPanel pnTopRight = new JPanel();
        pnTopRight.setLayout(new BorderLayout());
        pnTopRight.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 5));
        pnTopRight.setBackground(new Color(25, 118, 211));
        pnTopRight.add(lblBtnClose, BorderLayout.EAST);

        JLabel lblLogin = new JLabel("Đăng nhập");
        lblLogin.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 34));
        lblLogin.setForeground(Color.WHITE);
        lblLogin.setHorizontalAlignment(JLabel.CENTER);
        JPanel pnLblLogin = new JPanel();
        pnLblLogin.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnLblLogin.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        pnLblLogin.setBackground(new Color(25, 118, 211));
        pnLblLogin.add(lblLogin);

        JLabel lblUsername = new JLabel("Tên đăng nhập:");
        lblUsername.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        lblUsername.setForeground(new Color(120, 225, 255));
        JPanel pnLblUsername = new JPanel();
        pnLblUsername.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnLblUsername.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        pnLblUsername.setBackground(new Color(25, 118, 211));
        pnLblUsername.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setPreferredSize(new Dimension(250, 30));
        txtUsername.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtUsername.setForeground(Color.BLACK);
        txtUsername.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        txtUsername.setBackground(Color.WHITE);
        JPanel pnTxtUsername = new JPanel();
        pnTxtUsername.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnTxtUsername.setBorder(BorderFactory.createEmptyBorder(0, 40, 10, 40));
        pnTxtUsername.setBackground(new Color(25, 118, 211));
        pnTxtUsername.add(txtUsername);

        JLabel lblPassword = new JLabel("Mật khẩu: ");
        lblPassword.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        lblPassword.setForeground(new Color(120, 225, 255));
        JPanel pnLblPassword = new JPanel();
        pnLblPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnLblPassword.setBackground(new Color(25, 118, 211));
        pnLblPassword.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        pnLblPassword.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(250, 30));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtPassword.setForeground(Color.BLACK);
        txtPassword.setBackground(Color.WHITE);
        txtPassword.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        txtPassword.setEchoChar('\u25cf');
        JPanel pnTxtPassword = new JPanel();
        pnTxtPassword.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnTxtPassword.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        pnTxtPassword.setBackground(new Color(25, 118, 211));
        pnTxtPassword.add(txtPassword);

        chkShowPassword = new JCheckBox();
        chkShowPassword.setText("Hiển thị mật khẩu");
        chkShowPassword.setSelected(false);
        chkShowPassword.setBackground(new Color(25, 118, 211));
        chkShowPassword.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        chkShowPassword.setForeground(Color.WHITE);
        JPanel pnChkShowPassword = new JPanel();
        pnChkShowPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnChkShowPassword.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        pnChkShowPassword.setBackground(new Color(25, 118, 211));
        pnChkShowPassword.add(chkShowPassword);

        database = new Database("sales_management", "3306", "root", "");
        btnLogin = new JButton("Đăng nhập");
        btnLogin.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        btnLogin.setForeground(new Color(25, 118, 211));
        lblSqlConfig = new JLabel();
        lblSqlConfig.setBorder(BorderFactory.createLineBorder(new Color(25, 118, 211), 1));
        lblSqlConfig.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/24x24/database-icon_24px.svg"))));
        JPanel pnBtnLogin = new JPanel();
        pnBtnLogin.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnBtnLogin.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 40));
        pnBtnLogin.setBackground(new Color(25, 118, 211));
        pnBtnLogin.add(lblSqlConfig);
        pnBtnLogin.add(btnLogin);

        JPanel pnInforLogin = new JPanel();
        pnInforLogin.setLayout(new BoxLayout(pnInforLogin, BoxLayout.Y_AXIS));
        pnInforLogin.setBackground(new Color(25, 118, 211));
        pnInforLogin.add(Box.createVerticalGlue());
        pnInforLogin.add(pnLblLogin);
        pnInforLogin.add(pnLblUsername);
        pnInforLogin.add(pnTxtUsername);
        pnInforLogin.add(pnLblPassword);
        pnInforLogin.add(pnTxtPassword);
        pnInforLogin.add(pnChkShowPassword);
        pnInforLogin.add(pnBtnLogin);
        pnInforLogin.add(Box.createVerticalGlue());

        pnRight.add(pnTopRight, BorderLayout.NORTH);
        pnRight.add(pnInforLogin, BorderLayout.CENTER);

        // implementation the main panel
        pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLineBorder(new Color(240, 240, 240), 10)));
        pnMain.setBackground(Color.WHITE);
        pnMain.add(pnLeft, BorderLayout.CENTER);
        pnMain.add(pnRight, BorderLayout.EAST);

        // Add main panel to the content pane
        Container con = this.getContentPane();
        con.setLayout(new BorderLayout());
        con.add(pnMain, BorderLayout.CENTER);
    }

    private void addEvents() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent event) {
                for (double i = 0.0; i <= 1.0; i = i + 0.1) {
                    LoginView.this.setOpacity((float) i);
                    try {
                        Thread.sleep(25);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        pnMain.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        pnMain.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent event) {
                setLocation(LoginView.this.getX() + event.getX() - mouseX,
                        LoginView.this.getY() + event.getY() - mouseY);
            }
        });

        lblBtnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblBtnClose.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblBtnClose.setBorder(BorderFactory.createLineBorder(new Color(25, 118, 211), 2));
            }
        });

        chkShowPassword.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    txtPassword.setEchoChar((char) 0);
                } else {
                    txtPassword.setEchoChar('\u25cf');
                }
            }
        });

        chkShowPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    chkShowPassword.setSelected(!chkShowPassword.isSelected());
                }
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login();
            }
        });

        lblSqlConfig.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                new DatabaseDialog(LoginView.this, database);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblSqlConfig.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblSqlConfig.setBorder(BorderFactory.createLineBorder(new Color(25, 118, 211), 1));
            }
        });
    }

    private LoginModel getUser() {
        return new LoginModel(txtUsername.getText(), String.valueOf(txtPassword.getPassword()));
    }

    private boolean checkInput() {
        if (txtUsername.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập Tên đăng nhập!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtUsername.requestFocus();
            return false;
        } else if (String.valueOf(txtPassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập Mật khẩu!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtPassword.requestFocus();
            return false;
        }
        return true;
    }

    private void Login() {
        if (checkInput()) {
            LoginModel account = this.getUser();
            try {
                if (LoginController.checkData(database, account)) {
                    this.setVisible(false);
                    new MainUI("Quản lý bán hàng", database);
                } else {
                    JOptionPane.showMessageDialog(LoginView.this, "Tài khoản không tồn tại!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showWindow() {
        this.setSize(850, 450);
        this.setResizable(false);
        this.setIconImages(FlatSVGUtils.createWindowIconImages(Objects.requireNonNull(this.getClass().getResource(
                "/Images/icon.svg"))));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        SwingUtilities.invokeLater(LoginView::new);
    }
}
