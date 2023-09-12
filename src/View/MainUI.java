package View;

import Model.Database;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

public class MainUI extends JFrame {
    // attributes
    private final Database database;
    // components
    private JLabel lblBtnLogout;
    private CustomJLabel lblTrangChu, lblNhanVien, lblSanPham, lblKhachHang, lblNhaCungCap;
    private JMenuItem menuItemGithub, menuItemInformation, menuItemExit;
    private JPanel pnCenter;

    private ButtonTool lblBtnHome, lblBtnProduct, lblBtnEmployee, lblBtnSupplier, lblBtnCustomer;

    public static Frame frame;

    // constructor
    public MainUI(String title, Database database) {
        super(title);
        MainUI.frame = this;
        this.database = database;
        initComponents();
        addEvents();
        showWindow();
    }

    private void initComponents() {
        // ==================== Menu bar ====================
        JMenu menuFile = new JMenu("File");
        menuFile.setMnemonic('F');
        menuItemExit = new JMenuItem("Exit");
        menuItemExit.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/24x24/ic_close_24px.svg"))));
        menuItemExit.setAccelerator(KeyStroke.getKeyStroke('Q',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        menuFile.add(menuItemExit);

        JMenu menuHelp = new JMenu("Help");
        menuHelp.setMnemonic('H');
        menuItemInformation = new JMenuItem("Information");
        menuItemInformation.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/Images" +
                "/24x24/info_24px.svg"))));
        menuItemInformation.setAccelerator(KeyStroke.getKeyStroke('I',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        menuItemGithub = new JMenuItem("Github");
        menuItemGithub.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/Images" +
                "/24x24/github_24px.svg"))));
        menuItemGithub.setAccelerator(KeyStroke.getKeyStroke('G',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        menuHelp.add(menuItemGithub);
        menuHelp.add(menuItemInformation);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuHelp);
        this.setJMenuBar(menuBar);

        // ==================== Left Panel ====================
        JPanel pnLeft = new JPanel();
        pnLeft.setLayout(new BorderLayout());
        pnLeft.setBackground(Color.WHITE);
        pnLeft.setPreferredSize(new Dimension(200, 0));

        JLabel lblImage = new JLabel();
        lblImage.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/sales_manager.svg"))));
        lblImage.setHorizontalAlignment(JLabel.CENTER);
        lblImage.setBorder(BorderFactory.createLineBorder(new Color(75, 102, 147), 2));
        JPanel pnLblImage = new JPanel();
        pnLblImage.setBackground(new Color(244, 247, 254));
        pnLblImage.setLayout(new BorderLayout());
        pnLblImage.add(lblImage, BorderLayout.CENTER);

        lblTrangChu = new CustomJLabel("Trang chủ");
        lblTrangChu.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/48x48/home_48px.svg"))));
        lblTrangChu.setBackground(new Color(78, 138, 201));
        lblTrangChu.setForeground(Color.WHITE);
        lblTrangChu.setSelected(true);
        JPanel pnLblTrangChu = new JPanel();
        pnLblTrangChu.setLayout(new BorderLayout());
        pnLblTrangChu.add(lblTrangChu, BorderLayout.CENTER);

        lblSanPham = new CustomJLabel("Sản phẩm");
        lblSanPham.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/48x48/product_48px.svg"))));
        JPanel pnLblSanPham = new JPanel();
        pnLblSanPham.setLayout(new BorderLayout());
        pnLblSanPham.add(lblSanPham, BorderLayout.CENTER);

        lblNhanVien = new CustomJLabel("Nhân viên");
        lblNhanVien.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/48x48/employee_48px.svg"))));
        JPanel pnLblNhanVien = new JPanel();
        pnLblNhanVien.setLayout(new BorderLayout());
        pnLblNhanVien.add(lblNhanVien, BorderLayout.CENTER);

        lblKhachHang = new CustomJLabel("Khách hàng");
        lblKhachHang.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/48x48/customer_48px.svg"))));
        JPanel pnLblKhachHang = new JPanel();
        pnLblKhachHang.setLayout(new BorderLayout());
        pnLblKhachHang.add(lblKhachHang, BorderLayout.CENTER);

        lblNhaCungCap = new CustomJLabel("Nhà cung cấp");
        lblNhaCungCap.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/48x48/supplier_48px.svg"))));
        JPanel pnLblNhaCungCap = new JPanel();
        pnLblNhaCungCap.setLayout(new BorderLayout());
        pnLblNhaCungCap.add(lblNhaCungCap, BorderLayout.CENTER);

        JPanel pnOption = new JPanel();
        pnOption.setLayout(new BoxLayout(pnOption, BoxLayout.Y_AXIS));
        pnOption.add(Box.createVerticalGlue());
        pnOption.add(pnLblTrangChu);
        pnOption.add(pnLblSanPham);
        pnOption.add(pnLblNhanVien);
        pnOption.add(pnLblKhachHang);
        pnOption.add(pnLblNhaCungCap);
        pnOption.add(Box.createVerticalGlue());

        pnLeft.add(pnLblImage, BorderLayout.NORTH);
        pnLeft.add(pnOption, BorderLayout.CENTER);

        // ==================== Tool Panel ====================
        lblBtnLogout = new JLabel("Đăng xuất");
        lblBtnLogout.setForeground(Color.WHITE);
        lblBtnLogout.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblBtnLogout.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/32x32/logout_32px.svg"))));
        lblBtnLogout.setBackground(new Color(78, 138, 201));
        lblBtnLogout.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(1, 2, 1, 10)));
        lblBtnLogout.setOpaque(true);
        JPanel pnBtnLogout = new JPanel();
        pnBtnLogout.setLayout(new BorderLayout());
        pnBtnLogout.add(lblBtnLogout, BorderLayout.CENTER);
        pnBtnLogout.setBorder(BorderFactory.createEmptyBorder(4, 5, 4, 3));
        pnBtnLogout.setBackground(Color.WHITE);

        lblBtnHome = new ButtonTool("/Images/32x32/home_32px.svg");
        lblBtnSupplier = new ButtonTool("/Images/32x32/supplier_32px.svg");
        lblBtnEmployee = new ButtonTool("/Images/32x32/employee_32px.svg");
        lblBtnCustomer = new ButtonTool("/Images/32x32/customer_32px.svg");
        lblBtnProduct = new ButtonTool("/Images/32x32/product_32px.svg");

        JPanel pnTool = new JPanel();
        pnTool.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnTool.setBackground(Color.WHITE);
        pnTool.add(lblBtnHome);
        pnTool.add(lblBtnSupplier);
        pnTool.add(lblBtnEmployee);
        pnTool.add(lblBtnCustomer);
        pnTool.add(lblBtnProduct);

        JPanel pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());
        pnTop.setBackground(Color.WHITE);
        pnTop.setPreferredSize(new Dimension(0, 48));
        pnTop.add(pnBtnLogout, BorderLayout.EAST);
        pnTop.add(pnTool, BorderLayout.CENTER);

        // ==================== Center Panel ====================
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        pnCenter.setBackground(new Color(245, 245, 251));
        pnCenter.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,
                5, 0, 0), BorderFactory.createLineBorder(new Color(78, 138, 201), 4)));
        pnCenter.add(new HomePanel(), BorderLayout.CENTER);

        // ==================== Status bar ====================
        JPanel pnStatusBar = new JPanel();
        pnStatusBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED));


        // ==================== Add Main Panel to Content panel ====================
        Container con = this.getContentPane();
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.add(pnTop, BorderLayout.NORTH);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnLeft, pnCenter);
        splitPane.setOneTouchExpandable(true);
        pnMain.add(splitPane, BorderLayout.CENTER);
        pnMain.add(pnStatusBar, BorderLayout.SOUTH);
        con.add(pnMain);
    }

    private void addEvents() {
        lblBtnLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainUI.this.setVisible(false);
                new LoginView();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblBtnLogout.setBackground(Color.YELLOW);
                lblBtnLogout.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblBtnLogout.setBackground(new Color(78, 138, 201));
                lblBtnLogout.setForeground(Color.WHITE);
            }
        });

        lblTrangChu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectHomePage();
            }
        });

        lblBtnHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectHomePage();
            }
        });

        lblNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectEmployee();
            }
        });

        lblBtnEmployee.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectEmployee();
            }
        });

        lblSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectProduct();
            }
        });

        lblBtnProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectProduct();
            }
        });

        lblKhachHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectCustomer();
            }
        });

        lblBtnCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectCustomer();
            }
        });

        lblNhaCungCap.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectSupplier();
            }
        });

        lblBtnSupplier.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectSupplier();
            }
        });

        menuItemGithub.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(URI.create(
                        "https://github.com/ngochai285bkit/Sales-Management"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        menuItemExit.addActionListener(e -> System.exit(0));

        menuItemInformation.addActionListener(e -> new InformationDialog(MainUI.this));
    }

    private void selectHomePage() {
        lblTrangChu.setSelected(true);
        lblNhaCungCap.setSelected(false);
        lblNhanVien.setSelected(false);
        lblKhachHang.setSelected(false);
        lblSanPham.setSelected(false);
        pnCenter.removeAll();
        pnCenter.add(new HomePanel(), BorderLayout.CENTER);
        pnCenter.updateUI();
    }

    private void selectEmployee() {
        lblTrangChu.setSelected(false);
        lblNhaCungCap.setSelected(false);
        lblNhanVien.setSelected(true);
        lblKhachHang.setSelected(false);
        lblSanPham.setSelected(false);
        pnCenter.removeAll();
        pnCenter.add(new EmployeePanel(database), BorderLayout.CENTER);
        pnCenter.updateUI();
    }

    private void selectProduct() {
        lblTrangChu.setSelected(false);
        lblNhaCungCap.setSelected(false);
        lblNhanVien.setSelected(false);
        lblKhachHang.setSelected(false);
        lblSanPham.setSelected(true);
        pnCenter.removeAll();
        pnCenter.add(new ProductPanel(database), BorderLayout.CENTER);
        pnCenter.updateUI();
    }

    private void selectCustomer() {
        lblTrangChu.setSelected(false);
        lblNhaCungCap.setSelected(false);
        lblNhanVien.setSelected(false);
        lblKhachHang.setSelected(true);
        lblSanPham.setSelected(false);
        pnCenter.removeAll();
        pnCenter.add(new CustomerPanel(database), BorderLayout.CENTER);
        pnCenter.updateUI();
    }

    private void selectSupplier() {
        lblTrangChu.setSelected(false);
        lblNhaCungCap.setSelected(true);
        lblNhanVien.setSelected(false);
        lblKhachHang.setSelected(false);
        lblSanPham.setSelected(false);
        pnCenter.removeAll();
        pnCenter.add(new SupplierPanel(database), BorderLayout.CENTER);
        pnCenter.updateUI();
    }

    private void showWindow() {
        this.setSize(900, 650);
        this.setIconImages(FlatSVGUtils.createWindowIconImages(Objects.requireNonNull(this.getClass().getResource(
                "/Images/icon.svg"))));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        SwingUtilities.invokeLater(() -> new MainUI("Quản lý bán hàng", new Database()));
    }
}
