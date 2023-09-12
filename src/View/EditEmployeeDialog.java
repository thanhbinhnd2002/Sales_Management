package View;

import Controller.EmployeeController;
import Model.Database;
import Model.EmployeeModel;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class EditEmployeeDialog extends JDialog {
    private JTextField txtMaNhanVien, txtDiaChi, txtChucVu, txtTenNhanVien, txtSDT;
    private JDatePicker txtNgaySinh, txtNgayBatDauLam;
    private final Dimension dimenLabel = new Dimension(150, 25);
    private final Dimension dimenTextField = new Dimension(200, 30);
    private final Color backGroundBlue = new Color(78, 138, 201);
    private JButton btnXacNhan, btnThoat;
    private final Database database;
    private JComboBox<String> chonGioiTinh;
    private final EmployeeModel employee;
    private final Dimension dimenButton = new Dimension(160, 38);
    private final Font fontTextField = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

    public EditEmployeeDialog(Frame parent, String title, EmployeeModel employee, Database database) {
        super(parent, title, true);
        this.database = database;
        this.employee = employee;
        initComponents();
        addEvents();
        showDialog(parent);
    }

    private void initComponents() {
        //The top panel
        JPanel pnTop = new JPanel();
        pnTop.setBackground(Color.WHITE);
        JLabel lblTieuDe = new JLabel("Thông tin nhân viên");
        lblTieuDe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTieuDe.setForeground(backGroundBlue);
        pnTop.add(lblTieuDe);

        //The bottom panel
        JPanel pnBottom = new JPanel();
        pnBottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        pnBottom.setBackground(Color.WHITE);
        pnBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnXacNhan = new JButton("Lưu thay đổi");
        btnXacNhan.setPreferredSize(dimenButton);
        btnXacNhan.setIcon(new FlatSVGIcon(Objects.requireNonNull(CustomerPanel.class.getResource("/Images/24x24" +
                "/checked_24x24.svg"))));
        btnXacNhan.setBackground(Color.WHITE);
        btnXacNhan.setForeground(Color.BLACK);
        btnXacNhan.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        btnThoat = new JButton("Thoát");
        btnThoat.setPreferredSize(dimenButton);
        btnThoat.setIcon(new FlatSVGIcon(Objects.requireNonNull(CustomerPanel.class.getResource("/Images/24x24" +
                "/exitDialog_24x24.svg"))));
        btnThoat.setBackground(Color.WHITE);
        btnThoat.setForeground(Color.BLACK);
        btnThoat.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        pnBottom.add(btnXacNhan);
        pnBottom.add(btnThoat);


        //The center panel
        JPanel pnEast = new JPanel();
        pnEast.setLayout(new BoxLayout(pnEast, BoxLayout.Y_AXIS));
        pnEast.setBackground(Color.WHITE);
        pnEast.setPreferredSize(new Dimension(400, 0));

        JPanel pnMaNhanVien = new JPanel();
        pnMaNhanVien.setBackground(Color.WHITE);
        txtMaNhanVien = new JTextField();
        txtMaNhanVien.setEditable(false);
        txtMaNhanVien.setPreferredSize(dimenTextField);
        txtMaNhanVien.setFont(fontTextField);
        JLabel lblMaNhanVien = new JLabel("Mã nhân viên: ");
        lblMaNhanVien.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblMaNhanVien.setPreferredSize(dimenLabel);
        pnMaNhanVien.add(lblMaNhanVien);
        pnMaNhanVien.add(txtMaNhanVien);

        JPanel pnTenNhanVien = new JPanel();
        pnTenNhanVien.setBackground(Color.WHITE);
        txtTenNhanVien = new JTextField();
        txtTenNhanVien.setPreferredSize(dimenTextField);
        txtTenNhanVien.setFont(fontTextField);
        JLabel lblTenNhanVien = new JLabel("Tên nhân viên: ");
        lblTenNhanVien.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblTenNhanVien.setPreferredSize(dimenLabel);
        pnTenNhanVien.add(lblTenNhanVien);
        pnTenNhanVien.add(txtTenNhanVien);

        JPanel pnDiaChi = new JPanel();
        pnDiaChi.setBackground(Color.WHITE);
        txtDiaChi = new JTextField();
        txtDiaChi.setPreferredSize(dimenTextField);
        txtDiaChi.setFont(fontTextField);
        JLabel lblDiaChi = new JLabel("Địa chỉ: ");
        lblDiaChi.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblDiaChi.setPreferredSize(dimenLabel);
        pnDiaChi.add(lblDiaChi);
        pnDiaChi.add(txtDiaChi);

        JPanel pnSDT = new JPanel();
        pnSDT.setBackground(Color.WHITE);
        txtSDT = new JTextField();
        txtSDT.setPreferredSize(dimenTextField);
        txtSDT.setFont(fontTextField);
        JLabel lblSDT = new JLabel("Số điện thoại: ");
        lblSDT.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblSDT.setPreferredSize(dimenLabel);
        pnSDT.add(lblSDT);
        pnSDT.add(txtSDT);

        JPanel pnChucVu = new JPanel();
        pnChucVu.setBackground(Color.WHITE);
        txtChucVu = new JTextField();
        txtChucVu.setPreferredSize(dimenTextField);
        txtChucVu.setFont(fontTextField);
        JLabel lblChucVu = new JLabel("Chức vụ: ");
        lblChucVu.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblChucVu.setPreferredSize(dimenLabel);
        pnChucVu.add(lblChucVu);
        pnChucVu.add(txtChucVu);

        JPanel pnNgaySinh = new JPanel();
        pnNgaySinh.setBackground(Color.WHITE);
        txtNgaySinh = new JDatePicker(employee.getNgaySinhNhanVien());
        txtNgaySinh.setPreferredSize(dimenTextField);
        txtNgaySinh.getComponent(0).setPreferredSize(new Dimension(160, 30));
        txtNgaySinh.getComponent(1).setPreferredSize(new Dimension(30, 30));
        txtNgaySinh.setFont(fontTextField);
        JLabel lblNgaySinh = new JLabel("Ngày sinh: ");
        lblNgaySinh.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblNgaySinh.setPreferredSize(dimenLabel);
        pnNgaySinh.add(lblNgaySinh);
        pnNgaySinh.add(txtNgaySinh);

        JPanel pnNgayBatDauLam = new JPanel();
        pnNgayBatDauLam.setBackground(Color.WHITE);
        txtNgayBatDauLam = new JDatePicker(employee.getNgayBatDauLam());
        txtNgayBatDauLam.setPreferredSize(dimenTextField);
        txtNgayBatDauLam.getComponent(0).setPreferredSize(new Dimension(160, 30));
        txtNgayBatDauLam.getComponent(1).setPreferredSize(new Dimension(30, 30));
        txtNgayBatDauLam.setFont(fontTextField);
        JLabel lblNgayBatDauLam = new JLabel("Ngày làm việc: ");
        lblNgayBatDauLam.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblNgayBatDauLam.setPreferredSize(dimenLabel);
        pnNgayBatDauLam.add(lblNgayBatDauLam);
        pnNgayBatDauLam.add(txtNgayBatDauLam);

        JPanel pnGioiTinh = new JPanel();
        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblGioiTinh.setPreferredSize(dimenLabel);
        pnGioiTinh.setBackground(Color.WHITE);
        String[] gioiTinh = {"Nam", "Nữ"};
        chonGioiTinh = new JComboBox<>(gioiTinh);
        chonGioiTinh.setPreferredSize(dimenTextField);
        pnGioiTinh.add(lblGioiTinh);
        pnGioiTinh.add(chonGioiTinh);

        txtMaNhanVien.setText(employee.getMaNhanVien());
        txtTenNhanVien.setText(employee.getHoTenNhanVien());
        txtDiaChi.setText(employee.getDiaChiNhanVien());
        txtSDT.setText(employee.getSdtNhanVien());
        txtChucVu.setText(employee.getChucVuNhanVien());
        if (employee.getGioiTinh().equals("Nam")) {
            chonGioiTinh.setSelectedIndex(0);
        } else {
            chonGioiTinh.setSelectedIndex(1);
        }

        pnEast.add(Box.createVerticalGlue());
        pnEast.add(pnMaNhanVien);
        pnEast.add(pnTenNhanVien);
        pnEast.add(pnDiaChi);
        pnEast.add(pnSDT);
        pnEast.add(pnChucVu);
        pnEast.add(pnNgaySinh);
        pnEast.add(pnNgayBatDauLam);
        pnEast.add(pnGioiTinh);
        pnEast.add(Box.createVerticalGlue());

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.add(pnTop, BorderLayout.NORTH);
        pnMain.add(pnEast, BorderLayout.CENTER);
        pnMain.add(pnBottom, BorderLayout.SOUTH);

        Container con = this.getContentPane();
        con.add(pnMain);
    }

    private void addEvents() {
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeModel employee = new EmployeeModel();
                employee.setMaNhanVien(txtMaNhanVien.getText());
                employee.setHoTenNhanVien(txtTenNhanVien.getText());
                employee.setDiaChiNhanVien(txtDiaChi.getText());
                employee.setSdtNhanVien(txtSDT.getText());
                employee.setChucVuNhanVien(txtChucVu.getText());
                employee.setGioiTinh((String) chonGioiTinh.getSelectedItem());
                Date ngaySinhNV = (Date) txtNgaySinh.getModel().getValue();
                Date ngayBatDauLam = (Date) txtNgayBatDauLam.getModel().getValue();
                if (ngaySinhNV != null) {
                    employee.setNgaySinhNhanVien(ngaySinhNV);
                    if (ngayBatDauLam != null) {
                        employee.setNgayBatDauLam(ngayBatDauLam);
                        try {
                            if (EmployeeController.editEmployee(database, employee)) {
                                EmployeePanel.showListEmployee(EmployeeController.getAllEmployee(database));
                                dispose();
                                JOptionPane.showMessageDialog(MainUI.frame, "Chỉnh sửa thành công", "Thông báo",
                                        JOptionPane.INFORMATION_MESSAGE);

                            }else {
                                JOptionPane.showMessageDialog(MainUI.frame, "Chỉnh sửa thất bại", "Thông báo",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(MainUI.frame, "Chưa có ngày bắt đầu làm!");
                    }

                } else {
                    JOptionPane.showMessageDialog(MainUI.frame, "Chưa có ngày sinh nhân viên!");
                }
            }
        });
    }

    private void showDialog(Frame parent) {
        this.setSize(500, 600);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
}
