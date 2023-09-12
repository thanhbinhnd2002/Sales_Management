package View;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Objects;

public class InformationDialog extends JDialog {
    // attributes

    // contructor
    public InformationDialog(Window owner) {
        super(owner);
        this.setModal(true);
        initComponents();
        showDialog(owner);
    }

    private void initComponents() {
        // ==================== Top Panel ====================
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());
        pnTop.setBackground(new Color(245, 245, 251));
        JLabel lblImage = new JLabel();
        lblImage.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/logo_soict_hust.svg"))));
        lblImage.setHorizontalAlignment(JLabel.CENTER);
        pnTop.add(lblImage, BorderLayout.CENTER);

        // ==================== Center Panel ====================
        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        pnCenter.setBackground(new Color(245, 245, 251));

        JPanel pnInforProject = new JPanel();
        pnInforProject.setLayout(new BoxLayout(pnInforProject, BoxLayout.Y_AXIS));
        pnInforProject.setBackground(new Color(245, 245, 251));

        JLabel lblTenDeTai = new JLabel();
        lblTenDeTai.setText("Đề tài: Xây dựng phần mềm Quản lý bán hàng");
        lblTenDeTai.setHorizontalAlignment(JLabel.CENTER);
        lblTenDeTai.setForeground(new Color(78, 138, 201));
        lblTenDeTai.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        JPanel pnLblTenDeTai = new JPanel();
        pnLblTenDeTai.setLayout(new BorderLayout());
        pnLblTenDeTai.setBackground(new Color(245, 245, 251));
        pnLblTenDeTai.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        pnLblTenDeTai.add(lblTenDeTai, BorderLayout.CENTER);

        JLabel lblTenHocPhan = new JLabel();
        lblTenHocPhan.setText("Học phần: Java nâng cao - IT3680Q");
        lblTenHocPhan.setHorizontalAlignment(JLabel.CENTER);
        lblTenHocPhan.setForeground(new Color(78, 138, 201));
        lblTenHocPhan.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        JPanel pnLblTenHocPhan = new JPanel();
        pnLblTenHocPhan.setLayout(new BorderLayout());
        pnLblTenHocPhan.setBorder(BorderFactory.createEmptyBorder(5, 0, 20, 0));
        pnLblTenHocPhan.setBackground(new Color(245, 245, 251));
        pnLblTenHocPhan.add(lblTenHocPhan, BorderLayout.CENTER);

        JPanel pnGridInfo = new JPanel();
        pnGridInfo.setLayout(new GridLayout(4, 2, 20, 5));
        pnGridInfo.setBackground(new Color(245, 245, 251));
        pnGridInfo.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 100));

        JLabel lblGVHD = new JLabel("Giảng viên hướng dẫn: ");
        lblGVHD.setForeground(new Color(0x9c100f));
        lblGVHD.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        lblGVHD.setHorizontalAlignment(JLabel.LEFT);
        pnGridInfo.add(lblGVHD);

        JLabel lblTenGVHD = new JLabel("TS. Nguyễn Tuấn Dũng");
        lblTenGVHD.setForeground(new Color(78, 138, 201));
        lblTenGVHD.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        lblTenGVHD.setHorizontalAlignment(JLabel.LEFT);
        pnGridInfo.add(lblTenGVHD);

        JLabel lblMaLop = new JLabel("Mã lớp: ");
        lblMaLop.setForeground(new Color(0x9c100f));
        lblMaLop.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        lblMaLop.setHorizontalAlignment(JLabel.LEFT);
        pnGridInfo.add(lblMaLop);

        JLabel lblTenMaLop = new JLabel("131224 - Nhóm 2");
        lblTenMaLop.setForeground(new Color(78, 138, 201));
        lblTenMaLop.setHorizontalAlignment(JLabel.LEFT);
        lblTenMaLop.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        pnGridInfo.add(lblTenMaLop);

        JLabel lblNNLT = new JLabel("Ngôn ngữ lập trình: ");
        lblNNLT.setForeground(new Color(0x9c100f));
        lblNNLT.setHorizontalAlignment(JLabel.LEFT);
        lblNNLT.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        pnGridInfo.add(lblNNLT);

        JLabel lblTenNNLT = new JLabel("Java - JavaSwing MVC");
        lblTenNNLT.setForeground(new Color(78, 138, 201));
        lblTenNNLT.setHorizontalAlignment(JLabel.LEFT);
        lblTenNNLT.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        pnGridInfo.add(lblTenNNLT);

        JLabel lblCSDL = new JLabel("Cơ sở dữ liệu: ");
        lblCSDL.setForeground(new Color(0x9c100f));
        lblCSDL.setHorizontalAlignment(JLabel.LEFT);
        lblCSDL.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        pnGridInfo.add(lblCSDL);

        JLabel lblTenCSDL = new JLabel("MySQL");
        lblTenCSDL.setHorizontalAlignment(JLabel.LEFT);
        lblTenCSDL.setForeground(new Color(78, 136, 201));
        lblTenCSDL.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        pnGridInfo.add(lblTenCSDL);

        JLabel lblDsThanhVien = new JLabel("Danh sách thành viên: ");
        lblDsThanhVien.setForeground(new Color(78, 138, 201));
        lblDsThanhVien.setHorizontalAlignment(JLabel.LEFT);
        lblDsThanhVien.setFont(new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, 16));
        lblDsThanhVien.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 0));
        JPanel pnLblDsThanhVien = new JPanel();
        pnLblDsThanhVien.setLayout(new BorderLayout());
        pnLblDsThanhVien.setBackground(new Color(245, 245, 251));
        pnLblDsThanhVien.add(lblDsThanhVien, BorderLayout.CENTER);

        pnInforProject.add(Box.createVerticalGlue());
        pnInforProject.add(pnLblTenDeTai);
        pnInforProject.add(pnLblTenHocPhan);
        pnInforProject.add(pnGridInfo);
        pnInforProject.add(pnLblDsThanhVien);
        pnInforProject.add(Box.createVerticalGlue());

        JTable tblDsThanhVien = new JTable();
        tblDsThanhVien.setBackground(new Color(245, 245, 251));
        tblDsThanhVien.setForeground(Color.BLACK);
        tblDsThanhVien.setDefaultEditor(Object.class, null);
        tblDsThanhVien.setRowHeight(30);
        tblDsThanhVien.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        tblDsThanhVien.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tblDsThanhVien.setShowHorizontalLines(true);
        tblDsThanhVien.setShowVerticalLines(true);

        JTableHeader tableHeader = tblDsThanhVien.getTableHeader();
        tableHeader.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        tableHeader.setBackground(new Color(78, 138, 201));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setOpaque(false);
        tableHeader.setReorderingAllowed(false);
        tableHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ((DefaultTableCellRenderer) tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("STT");
        defaultTableModel.addColumn("Họ và tên");
        defaultTableModel.addColumn("MSSV");
        defaultTableModel.addColumn("Ghi chú");
        defaultTableModel.addRow(new Object[]{1, "Phạm Ngọc Hải", "20207601", "Nhóm trưởng"});
        defaultTableModel.addRow(new Object[]{2, "Phạm Thanh Bình", "20207587", ""});
        defaultTableModel.addRow(new Object[]{3, "Nguyễn Duy Thái", "20207630", ""});
        tblDsThanhVien.setModel(defaultTableModel);

        DefaultTableCellRenderer cellRendererCenter = new DefaultTableCellRenderer();
        cellRendererCenter.setHorizontalAlignment(JLabel.CENTER);
        tblDsThanhVien.getColumnModel().getColumn(0).setCellRenderer(cellRendererCenter);
        tblDsThanhVien.getColumnModel().getColumn(1).setCellRenderer(cellRendererCenter);
        tblDsThanhVien.getColumnModel().getColumn(2).setCellRenderer(cellRendererCenter);
        tblDsThanhVien.getColumnModel().getColumn(3).setCellRenderer(cellRendererCenter);

        JScrollPane scrDsThanhVien = new JScrollPane(tblDsThanhVien, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrDsThanhVien.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        scrDsThanhVien.setBackground(new Color(245, 245, 251));

        pnCenter.add(pnInforProject, BorderLayout.NORTH);
        pnCenter.add(scrDsThanhVien, BorderLayout.CENTER);

        // implementation the main panel
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.setBackground(Color.LIGHT_GRAY);
        pnMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnMain.setBackground(new Color(245, 245, 251));
        pnMain.add(pnTop, BorderLayout.NORTH);
        pnMain.add(pnCenter, BorderLayout.CENTER);

        // add pnMain to the content pane
        Container con = this.getContentPane();
        con.add(pnMain, BorderLayout.CENTER);
    }

    private void showDialog(Window owner) {
        this.setTitle("Thông tin");
        this.setIconImages(FlatSVGUtils.createWindowIconImages(Objects.requireNonNull(this.getClass().getResource(
                "/Images/icon.svg"))));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(850, 550);
        this.setLocationRelativeTo(owner);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        SwingUtilities.invokeLater(() -> new InformationDialog(null));
    }
}
