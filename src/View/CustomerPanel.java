package View;

import Controller.CustomerController;
import Controller.DatabaseConnection;
import Controller.ExportExcel;
import Model.CustomerModel;
import Model.Database;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class CustomerPanel extends JPanel {
    // attributes
    private final Database database;
    private final Color backGroundColor = new Color(245, 245, 251);
    private final Color lineBorder = new Color(99, 200, 221);
    private final Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
    private JButton btnSua, btnThemMoi, btnXoa, btnXuatFile;
    public static DefaultTableModel dtmDsKhachHang;
    private JTable tbDsKhachHang;
    private JTextField txtTimKiem;
    private JRadioButton rbtnMaKhachHang, rbtnTenKhachHang, rbtnDiaChiKhachHang, rbtnDienThoaiKhachHang;
    private final Dimension dimenButton = new Dimension(160, 38);
    private final Dimension dimenTextField=  new Dimension(200, 30);
    private final Font fontTextField= new Font(Font.SANS_SERIF, Font.PLAIN , 16);



    // constructor
    public CustomerPanel(Database database) {
        this.database = database;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(245, 245, 251));
        initComponents();
        addEvents();
    }

    private void initComponents() {
        // implementation the top panel
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());
        pnTop.setBackground(new Color(245, 245, 251));
        JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTitle.setForeground(new Color(78, 138, 211));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        pnTop.add(lblTitle, BorderLayout.CENTER);

        // Panel center
        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));


        tbDsKhachHang = new JTable();
        tbDsKhachHang.setBackground(backGroundColor);
        tbDsKhachHang.setForeground(Color.BLACK);
        tbDsKhachHang.setDefaultEditor(Object.class, null);
        tbDsKhachHang.setRowHeight(25);
        tbDsKhachHang.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        tbDsKhachHang.setShowGrid(true);
        tbDsKhachHang.setGridColor(Color.BLACK);

        JTableHeader tableHeader = tbDsKhachHang.getTableHeader();
        tableHeader.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        tableHeader.setBackground(new Color(79, 138, 201));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setOpaque(true);
        tableHeader.setReorderingAllowed(true);
        tableHeader.setBorder(BorderFactory.createLineBorder(lineBorder));
        ((DefaultTableCellRenderer) tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        dtmDsKhachHang = new DefaultTableModel();
        dtmDsKhachHang.addColumn("Mã");
        dtmDsKhachHang.addColumn("Họ tên");
        dtmDsKhachHang.addColumn("Địa chỉ");
        dtmDsKhachHang.addColumn("Số điện thoại");
        tbDsKhachHang.setModel(dtmDsKhachHang);
        JScrollPane scrollDanhSachKH = new JScrollPane(tbDsKhachHang, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollDanhSachNV.setBorder(BorderFactory.createEmptyBorder(4 , 10,4 ,10));
        scrollDanhSachKH.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4),
                BorderFactory.createLineBorder(lineBorder, 2)));

        DefaultTableCellRenderer cellRendererCenter = new DefaultTableCellRenderer();
        cellRendererCenter.setHorizontalAlignment(JLabel.CENTER);
        tbDsKhachHang.getColumnModel().getColumn(0).setCellRenderer(cellRendererCenter);
        tbDsKhachHang.getColumnModel().getColumn(1).setCellRenderer(cellRendererCenter);
        tbDsKhachHang.getColumnModel().getColumn(2).setCellRenderer(cellRendererCenter);
        tbDsKhachHang.getColumnModel().getColumn(3).setCellRenderer(cellRendererCenter);
        pnCenter.add(scrollDanhSachKH, BorderLayout.CENTER);

        try {
            showListCustomer(CustomerController.getAllCustomer(database));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Panel Bottom

        JPanel pnSouth = new JPanel();
        pnSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        pnSouth.setBackground(backGroundColor);
        btnSua = new JButton("Sửa");
        btnSua.setPreferredSize(dimenButton);
        btnSua.setIcon(new FlatSVGIcon(Objects.requireNonNull(CustomerPanel.class.getResource("/Images/24x24/edit_24x24.svg"))));
        btnSua.setBackground(Color.WHITE);
        btnSua.setForeground(Color.BLACK);
        btnSua.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        btnThemMoi = new JButton("Thêm mới");
        btnThemMoi.setPreferredSize(dimenButton);
        btnThemMoi.setIcon(new FlatSVGIcon(Objects.requireNonNull(CustomerPanel.class.getResource("/Images/24x24" +
                "/plus_24x24.svg"))));
        btnThemMoi.setBackground(Color.WHITE);
        btnThemMoi.setForeground(Color.BLACK);
        btnThemMoi.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        btnXoa = new JButton("Xoá");
        btnXoa.setPreferredSize(dimenButton);
        btnXoa.setIcon(new FlatSVGIcon(Objects.requireNonNull(CustomerPanel.class.getResource("/Images/24x24" +
                "/delete_24x24.svg"))));
        btnXoa.setBackground(Color.WHITE);
        btnXoa.setForeground(Color.BLACK);
        btnXoa.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        btnXuatFile = new JButton("Xuất file");
        btnXuatFile.setPreferredSize(dimenButton);
        btnXuatFile.setIcon(new FlatSVGIcon(Objects.requireNonNull(CustomerPanel.class.getResource("/Images/24x24" +
                "/excel_24x24.svg"))));
        btnXuatFile.setBackground(Color.WHITE);
        btnXuatFile.setForeground(Color.BLACK);
        btnXuatFile.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        pnSouth.add(Box.createHorizontalGlue());
        pnSouth.add(btnThemMoi);
        pnSouth.add(btnSua);
        pnSouth.add(btnXoa);
        pnSouth.add(btnXuatFile);
        pnSouth.add(Box.createHorizontalGlue());
        pnSouth.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5),
                BorderFactory.createLineBorder(lineBorder, 2)));

        // East panel
        JPanel pnEast = new JPanel();
        pnEast.setPreferredSize(new Dimension(240, 0));
        pnEast.setLayout(new BorderLayout());
        pnEast.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,
                5, 5, 5), BorderFactory.createLineBorder(lineBorder, 2)));
        JPanel pnTimKiem = new JPanel();
        pnTimKiem.setLayout(new BoxLayout(pnTimKiem, BoxLayout.Y_AXIS));
        pnTimKiem.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,
                5, 0, 5), BorderFactory.createLineBorder(lineBorder, 2)));


        rbtnMaKhachHang = new JRadioButton("Mã khách hàng");
        rbtnMaKhachHang.setFont(font);
        rbtnTenKhachHang = new JRadioButton("Họ và tên");
        rbtnTenKhachHang.setFont(font);
        rbtnDiaChiKhachHang = new JRadioButton("Địa chỉ");
        rbtnDiaChiKhachHang.setFont(font);
        rbtnDienThoaiKhachHang = new JRadioButton("Số điện Thoại");
        rbtnDienThoaiKhachHang.setFont(font);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbtnMaKhachHang);
        buttonGroup.add(rbtnTenKhachHang);
        buttonGroup.add(rbtnDiaChiKhachHang);
        buttonGroup.add(rbtnDienThoaiKhachHang);

        txtTimKiem = new JTextField(20);
        txtTimKiem.setPreferredSize(dimenTextField);
        txtTimKiem.setFont(fontTextField);
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tìm kiếm");

        pnTimKiem.add(txtTimKiem);
        pnTimKiem.add(rbtnDiaChiKhachHang);
        pnTimKiem.add(rbtnMaKhachHang);
        pnTimKiem.add(rbtnTenKhachHang);
        pnTimKiem.add(rbtnDienThoaiKhachHang);

        JPanel pnImage = new JPanel();
        pnImage.setLayout(new BorderLayout());
        JLabel imageEmployee = new JLabel();
        imageEmployee.setIcon(new FlatSVGIcon(Objects.requireNonNull(EmployeePanel.class.getResource("/Images/EmployeeSVG.svg"))));
        imageEmployee.setHorizontalAlignment(JLabel.CENTER);
        pnImage.add(imageEmployee, BorderLayout.CENTER);

        pnEast.add(pnImage, BorderLayout.CENTER);
        pnEast.add(pnTimKiem, BorderLayout.NORTH);

        JPanel pnCenterMain = new JPanel();
        pnCenterMain.setLayout(new BorderLayout());
        pnCenterMain.add(pnCenter, BorderLayout.CENTER);
        pnCenterMain.add(pnSouth, BorderLayout.SOUTH);

        this.add(pnEast, BorderLayout.EAST);
        this.add(pnTop, BorderLayout.NORTH);
        this.add(pnCenterMain, BorderLayout.CENTER);
    }

    private void addEvents() {
        btnThemMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCustomerDialog(MainUI.frame, "Thêm khách hàng", database);
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelected = tbDsKhachHang.getSelectedRow();
                if (rowSelected != -1) {
                    CustomerModel customerModel = new CustomerModel();
                    customerModel.setMaKhachHang((String) tbDsKhachHang.getValueAt(rowSelected, 0));
                    customerModel.setTenKhachHang((String) tbDsKhachHang.getValueAt(rowSelected, 1));
                    customerModel.setDiaChi((String) tbDsKhachHang.getValueAt(rowSelected, 2));
                    customerModel.setSoDienThoai((String) tbDsKhachHang.getValueAt(rowSelected, 3));
                    new EditCustomerDialog(MainUI.frame, "Chỉnh sửa thông tin khách hàng", customerModel, database);
                } else {
                    JOptionPane.showMessageDialog(MainUI.frame, "Bạn chưa chọn đối tượng cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnXuatFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(MainUI.frame);
                File fileSaveEmployee = fileChooser.getSelectedFile();
                if (fileSaveEmployee!= null){
                    String filePath = fileSaveEmployee.getPath();
                    if(!filePath.endsWith(".xlsx") || !filePath.endsWith(".xls")){
                        filePath = filePath + ".xlsx";
                    }
                    ExportExcel.export(tbDsKhachHang, filePath);
                    JOptionPane.showMessageDialog(MainUI.frame, "Xuất file excel thành công", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelected = tbDsKhachHang.getSelectedRow();
                if (rowSelected != -1) {
                    String maKhachHang = (String) tbDsKhachHang.getValueAt(rowSelected, 0);
                    try {
                        if(CustomerController.deleteCustomer(database, maKhachHang)){
                            showListCustomer(CustomerController.getAllCustomer(database));
                            JOptionPane.showMessageDialog(MainUI.frame, "Xoá thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(MainUI.frame, "Xoá thất bại!", "Cảnh " +
                                            "báo", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(MainUI.frame, "Bạn chưa chọn đối tượng cần xoá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                showListCustomer(listFiltered());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                showListCustomer(listFiltered());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                showListCustomer(listFiltered());
            }
        });

        rbtnMaKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListCustomer(listFiltered());
            }
        });

        rbtnTenKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListCustomer(listFiltered());
            }
        });

        rbtnDiaChiKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListCustomer(listFiltered());
            }
        });

        rbtnDienThoaiKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListCustomer(listFiltered());
            }
        });
    }

    private List<CustomerModel> listFiltered() {
        List<CustomerModel> listLater = new ArrayList<>();
        String searchText = txtTimKiem.getText().toLowerCase();
        try {
            List<CustomerModel> list = CustomerController.getAllCustomer(database);
            for (CustomerModel customer : list) {
                if (txtTimKiem.getText().isEmpty()) {
                    listLater.add(customer);
                } else {
                    if (rbtnMaKhachHang.isSelected()) {
                        if (customer.getMaKhachHang().toLowerCase().contains(searchText)) {
                            listLater.add(customer);
                        }
                    } else if (rbtnTenKhachHang.isSelected()) {
                        if (customer.getTenKhachHang().toLowerCase().contains(searchText)) {
                            listLater.add(customer);
                        }
                    } else if (rbtnDiaChiKhachHang.isSelected()) {
                        if (customer.getDiaChi().toLowerCase().contains(searchText)) {
                            listLater.add(customer);
                        }
                    } else if (rbtnDienThoaiKhachHang.isSelected()) {
                        if (customer.getSoDienThoai().toLowerCase().contains(searchText)) {
                            listLater.add(customer);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listLater;
    }

    public static void showListCustomer(List<CustomerModel> listCustomer) {
        dtmDsKhachHang.setRowCount(0);
        for (CustomerModel customerModel : listCustomer) {
            Vector<String> vector = new Vector<>();
            vector.add(customerModel.getMaKhachHang());
            vector.add(customerModel.getTenKhachHang());
            vector.add(customerModel.getDiaChi());
            vector.add(customerModel.getSoDienThoai());
            dtmDsKhachHang.addRow(vector);
        }
    }


    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        JFrame frame = new JFrame();
        frame.setTitle("Quản lý khách hàng");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Database database = new Database("sales_management", "3306", "root", "");
        frame.getContentPane().add(new CustomerPanel(database));

        frame.setVisible(true);
    }
}
