package View;

import Controller.EmployeeController;
import Controller.ExportExcel;
import Model.Database;
import Model.EmployeeModel;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class  EmployeePanel extends JPanel {
    // attributes
    private Database database;
    private final Color backGroundColor = new Color(245, 245, 251);
    private final Color backGroundBlue = new Color(78, 138, 201);
    private final Color lineBorder = new Color(99, 200, 221);
    private final Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
    private JButton btnSua, btnThemMoi, btnXoa, btnXuatFile;
    public static DefaultTableModel dtmDsNhanVien;
    private final Dimension dimenButton = new Dimension(160, 38);
    private JRadioButton rbtnMaNhanVien, rbtnTenNhanVien, rbtnDiaChi, rbtnDienThoai, rbtnChucVu, rbtnNgaySinh, rbtnNgayBatDauLam, rbtnGioiTinh;
    private JTable tbDsNhanVien;
    private JTextField txtTimKiem;
    private final Dimension dimenTextField = new Dimension(200, 30);
    private final Font fontTextField = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

    // constructor
    public EmployeePanel(Database database) {
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
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTitle.setForeground(new Color(78, 138, 211));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        pnTop.add(lblTitle, BorderLayout.CENTER);

        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));

        tbDsNhanVien = new JTable();
        tbDsNhanVien.setBackground(backGroundColor);
        tbDsNhanVien.setForeground(Color.BLACK);
        tbDsNhanVien.setDefaultEditor(Object.class, null);
        tbDsNhanVien.setRowHeight(25);
        tbDsNhanVien.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        tbDsNhanVien.setShowGrid(true);
        tbDsNhanVien.setGridColor(Color.BLACK);

        JTableHeader tableHeader = tbDsNhanVien.getTableHeader();
        tableHeader.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        tableHeader.setBackground(new Color(79, 138, 201));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setOpaque(true);
        tableHeader.setReorderingAllowed(true);
        tableHeader.setBorder(BorderFactory.createLineBorder(lineBorder));
        ((DefaultTableCellRenderer) tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        dtmDsNhanVien = new DefaultTableModel();
        dtmDsNhanVien.addColumn("Mã");
        dtmDsNhanVien.addColumn("Họ tên");
        dtmDsNhanVien.addColumn("Địa chỉ");
        dtmDsNhanVien.addColumn("SĐT");
        dtmDsNhanVien.addColumn("Chức vụ");
        dtmDsNhanVien.addColumn("Ngày sinh");
        dtmDsNhanVien.addColumn("Ngày làm");
        dtmDsNhanVien.addColumn("Giới tính");
        tbDsNhanVien.setModel(dtmDsNhanVien);
        JScrollPane scrollDanhSachKH = new JScrollPane(tbDsNhanVien, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDanhSachKH.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4),
                BorderFactory.createLineBorder(lineBorder,2)));

        DefaultTableCellRenderer cellRendererCenter = new DefaultTableCellRenderer();
        cellRendererCenter.setHorizontalAlignment(JLabel.CENTER);
        tbDsNhanVien.getColumnModel().getColumn(0).setCellRenderer(cellRendererCenter);
        tbDsNhanVien.getColumnModel().getColumn(1).setCellRenderer(cellRendererCenter);
        tbDsNhanVien.getColumnModel().getColumn(2).setCellRenderer(cellRendererCenter);
        tbDsNhanVien.getColumnModel().getColumn(3).setCellRenderer(cellRendererCenter);
        tbDsNhanVien.getColumnModel().getColumn(4).setCellRenderer(cellRendererCenter);
        tbDsNhanVien.getColumnModel().getColumn(5).setCellRenderer(cellRendererCenter);
        tbDsNhanVien.getColumnModel().getColumn(6).setCellRenderer(cellRendererCenter);
        tbDsNhanVien.getColumnModel().getColumn(7).setCellRenderer(cellRendererCenter);
        pnCenter.add(scrollDanhSachKH, BorderLayout.CENTER);

        try {
            showListEmployee(EmployeeController.getAllEmployee(database));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Panel bottom

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
        pnTimKiem.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5),
                BorderFactory.createLineBorder(lineBorder, 2)));


        rbtnMaNhanVien = new JRadioButton("Mã nhân viên");
        rbtnMaNhanVien.setFont(font);
        rbtnTenNhanVien = new JRadioButton("Họ và tên");
        rbtnTenNhanVien.setFont(font);
        rbtnDiaChi = new JRadioButton("Địa chỉ");
        rbtnDiaChi.setFont(font);
        rbtnDienThoai = new JRadioButton("Số điện thoại");
        rbtnDienThoai.setFont(font);
        rbtnChucVu = new JRadioButton("Chức vụ");
        rbtnChucVu.setFont(font);
        rbtnNgaySinh = new JRadioButton("Ngày sinh");
        rbtnNgaySinh.setFont(font);
        rbtnNgayBatDauLam = new JRadioButton("Ngày bắt đầu làm");
        rbtnNgayBatDauLam.setFont(font);
        rbtnGioiTinh = new JRadioButton("Giới tính");
        rbtnGioiTinh.setFont(font);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbtnMaNhanVien);
        buttonGroup.add(rbtnTenNhanVien);
        buttonGroup.add(rbtnDiaChi);
        buttonGroup.add(rbtnDienThoai);
        buttonGroup.add(rbtnChucVu);
        buttonGroup.add(rbtnNgaySinh);
        buttonGroup.add(rbtnNgayBatDauLam);
        buttonGroup.add(rbtnGioiTinh);

        txtTimKiem = new JTextField(20);
        txtTimKiem.setPreferredSize(dimenTextField);
        txtTimKiem.setFont(fontTextField);
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tìm kiếm");


        pnTimKiem.add(txtTimKiem);
        pnTimKiem.add(rbtnMaNhanVien);
        pnTimKiem.add(rbtnTenNhanVien);
        pnTimKiem.add(rbtnDiaChi);
        pnTimKiem.add(rbtnDienThoai);
        pnTimKiem.add(rbtnChucVu);
        pnTimKiem.add(rbtnNgaySinh);
        pnTimKiem.add(rbtnNgayBatDauLam);
        pnTimKiem.add(rbtnGioiTinh);
        //Panel add ảnh
        JPanel pnImage = new JPanel();
        pnImage.setLayout(new BorderLayout());
        JLabel imageEmployee = new JLabel();
        imageEmployee.setIcon(new FlatSVGIcon(Objects.requireNonNull(EmployeePanel.class.getResource("/Images/EmployeeSVG.svg"))));
        imageEmployee.setHorizontalAlignment(JLabel.CENTER);
        pnImage.add(imageEmployee, BorderLayout.CENTER);

        pnEast.add(pnTimKiem, BorderLayout.NORTH);
        pnEast.add(pnImage, BorderLayout.CENTER);

        JPanel pnCenterMain = new JPanel();
        pnCenterMain.setLayout(new BorderLayout());
        pnCenterMain.add(pnCenter, BorderLayout.CENTER);
        pnCenterMain.add(pnSouth, BorderLayout.SOUTH);

        this.add(pnTop, BorderLayout.NORTH);
        this.add(pnCenterMain, BorderLayout.CENTER);
        this.add(pnEast, BorderLayout.EAST);
    }

    private EmployeeModel getEmployee() {
        EmployeeModel employee = new EmployeeModel();
        return employee;
    }

    private void addEvents() {
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelected = tbDsNhanVien.getSelectedRow();
                if (rowSelected != -1) {
                    EmployeeModel employeeModel = new EmployeeModel();
                    employeeModel.setMaNhanVien((String) tbDsNhanVien.getValueAt(rowSelected, 0));
                    employeeModel.setHoTenNhanVien((String) tbDsNhanVien.getValueAt(rowSelected, 1));
                    employeeModel.setDiaChiNhanVien((String) tbDsNhanVien.getValueAt(rowSelected, 2));
                    employeeModel.setSdtNhanVien((String) tbDsNhanVien.getValueAt(rowSelected, 3));
                    employeeModel.setChucVuNhanVien((String) tbDsNhanVien.getValueAt(rowSelected, 4));
                    try {
                        employeeModel.setNgaySinhNhanVien(new SimpleDateFormat("dd/MM/yyyy").parse((String) tbDsNhanVien.getValueAt(rowSelected, 5)));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        employeeModel.setNgayBatDauLam((new SimpleDateFormat("dd/MM/yyyy")).parse((String) tbDsNhanVien.getValueAt(rowSelected, 6)));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    employeeModel.setGioiTinh((String) tbDsNhanVien.getValueAt(rowSelected, 7));

                    new EditEmployeeDialog(MainUI.frame, "Chỉnh sửa thông tin nhân viên", employeeModel,
                            database);
                } else {
                    JOptionPane.showMessageDialog(MainUI.frame, "Bạn chưa chọn đối tượng cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnThemMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployeeDialog(MainUI.frame, "Thêm nhân viên", database);
            }
        });

        btnXuatFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(MainUI.frame);
                File fileSaveEmployee = fileChooser.getSelectedFile();
                if (fileSaveEmployee != null) {
                    String filePath = fileSaveEmployee.getPath();
                    if (!filePath.endsWith(".xlsx") || !filePath.endsWith(".xls")) {
                        filePath = filePath + ".xlsx";
                    }
                    ExportExcel.export(tbDsNhanVien, filePath);
                    JOptionPane.showMessageDialog(MainUI.frame, "Xuất file excel thành công", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelected = tbDsNhanVien.getSelectedRow();
                if (rowSelected != -1) {
                    String maNhanVien = (String) tbDsNhanVien.getValueAt(rowSelected, 0);
                    try {
                        if (EmployeeController.deleteEmployee(database, maNhanVien)) {
                            showListEmployee(EmployeeController.getAllEmployee(database));
                            JOptionPane.showMessageDialog(MainUI.frame, "Xoá thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(MainUI.frame, "Xóa thất bại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(MainUI.frame, "Bạn chưa chọn đối tượng cần xoá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                showListEmployee(listFiltered());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                showListEmployee(listFiltered());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                showListEmployee(listFiltered());
            }
        });

        rbtnMaNhanVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListEmployee(listFiltered());
            }
        });

        rbtnTenNhanVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListEmployee(listFiltered());
            }
        });
        rbtnDiaChi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListEmployee(listFiltered());
            }
        });
        rbtnDienThoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListEmployee(listFiltered());
            }
        });
        rbtnChucVu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListEmployee(listFiltered());
            }
        });
        rbtnGioiTinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListEmployee(listFiltered());
            }
        });
        rbtnNgaySinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListEmployee(listFiltered());
            }
        });
        rbtnNgayBatDauLam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListEmployee(listFiltered());
            }
        });

    }

    private List<EmployeeModel> listFiltered() {
        List<EmployeeModel> listLater = new ArrayList<>();
        String searchText = txtTimKiem.getText().toLowerCase();
        try {
            List<EmployeeModel> list = EmployeeController.getAllEmployee(database);
            for (EmployeeModel employee : list) {
                if (txtTimKiem.getText().isEmpty()) {
                    listLater.add(employee);
                } else {
                    if (rbtnMaNhanVien.isSelected()) {
                        if (employee.getMaNhanVien().toLowerCase().contains(searchText)) {
                            listLater.add(employee);
                        }
                    } else if (rbtnTenNhanVien.isSelected()) {
                        if (employee.getHoTenNhanVien().toLowerCase().contains(searchText)) {
                            listLater.add(employee);
                        }
                    } else if (rbtnDiaChi.isSelected()) {
                        if (employee.getDiaChiNhanVien().toLowerCase().contains(searchText)) {
                            listLater.add(employee);
                        }
                    } else if (rbtnDienThoai.isSelected()) {
                        if (employee.getSdtNhanVien().toLowerCase().contains(searchText)) {
                            listLater.add(employee);
                        }
                    } else if (rbtnChucVu.isSelected()) {
                        if (employee.getChucVuNhanVien().toLowerCase().contains(searchText)) {
                            listLater.add(employee);
                        }
                    } else if (rbtnGioiTinh.isSelected()) {
                        if (employee.getGioiTinh().toLowerCase().contains(searchText)) {
                            listLater.add(employee);
                        }
                    } else if (rbtnNgaySinh.isSelected()) {
                        String ngaySinh = new SimpleDateFormat("dd/MM/yyyy").format(employee.getNgaySinhNhanVien());
                        if (ngaySinh.contains(searchText)) {
                            listLater.add(employee);
                        }
                    } else if (rbtnNgayBatDauLam.isSelected()) {
                        String ngayBatDauLam = new SimpleDateFormat("dd/MM/yyyy").format(employee.getNgayBatDauLam());
                        if (ngayBatDauLam.contains(searchText)) {
                            listLater.add(employee);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listLater;
    }


    public static void showListEmployee(List<EmployeeModel> listEmployee) {
        dtmDsNhanVien.setRowCount(0);
        for (EmployeeModel employeeModel : listEmployee) {
            Vector<String> vector = new Vector<>();
            vector.add(employeeModel.getMaNhanVien());
            vector.add(employeeModel.getHoTenNhanVien());
            vector.add(employeeModel.getDiaChiNhanVien());
            vector.add(employeeModel.getSdtNhanVien());
            vector.add(employeeModel.getChucVuNhanVien());
            vector.add(new SimpleDateFormat("dd/MM/yyyy").format(employeeModel.getNgaySinhNhanVien()));
            vector.add(new SimpleDateFormat("dd/MM/yyyy").format(employeeModel.getNgayBatDauLam()));
            vector.add(employeeModel.getGioiTinh());
            dtmDsNhanVien.addRow(vector);
        }
    }

    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        JFrame frame = new JFrame();
        frame.setTitle("Quản lý nhân viên");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().add(new EmployeePanel(new Database()));
        frame.setVisible(true);
    }
}
