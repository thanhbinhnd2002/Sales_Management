package View;

import Controller.CustomerController;
import Controller.DatabaseConnection;
import Model.CustomerModel;
import Model.Database;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class EditCustomerDialog extends JDialog {
    private JTextField txtMaKhachHang, txtTenKhachHang, txtDiaChi, txtSDT;
    private final Dimension dimenLabel = new Dimension(190, 25);
    private final Dimension dimenTextField = new Dimension(200, 30);
    private final Color backGroundBlue = new Color(78, 138, 201);
    private JButton btnXacNhan, btnThoat;
    private final Database database;
    private final Dimension dimenButton = new Dimension(160, 38);
    private final Font fontTextField = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

    // constructor

    public EditCustomerDialog(Frame parent, String title, CustomerModel customer,
                              Database database) {
        super(parent, title, true);
        this.database = database;
        initComponents();
        setInforCustomer(customer);
        addEvents();
        showDialog(parent);
    }

    public void initComponents() {
        // The top panel
        JPanel pnTop = new JPanel();
        pnTop.setBackground(Color.WHITE);
        JLabel lblTieuDe = new JLabel("Thông tin khách hàng");
        lblTieuDe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTieuDe.setForeground(backGroundBlue);
        pnTop.add(lblTieuDe);

        //The bottom panel
        JPanel pnBottom = new JPanel();
        pnBottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
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
        btnThoat.setIcon(new FlatSVGIcon(Objects.requireNonNull(CustomerPanel.class.getResource(
                "/Images/24x24" +
                "/exitDialog_24x24.svg"))));
        btnThoat.setBackground(Color.WHITE);
        btnThoat.setForeground(Color.BLACK);
        btnThoat.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        pnBottom.add(btnXacNhan);
        pnBottom.add(btnThoat);


        // The Center panel
        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
        pnCenter.setBackground(Color.WHITE);
        pnCenter.setPreferredSize(new Dimension(400, 0));

        JPanel pnMaKhachHang = new JPanel();
        pnMaKhachHang.setBackground(Color.WHITE);
        txtMaKhachHang = new JTextField();
        txtMaKhachHang.setPreferredSize(dimenTextField);
        txtMaKhachHang.setEditable(false);
        txtMaKhachHang.setFont(fontTextField);
        JLabel lblMaKhachHang = new JLabel("Mã khách hàng: ");
        lblMaKhachHang.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblMaKhachHang.setPreferredSize(dimenLabel);
        pnMaKhachHang.add(lblMaKhachHang);
        pnMaKhachHang.add(txtMaKhachHang);

        JPanel pnTenKhachHang = new JPanel();
        pnTenKhachHang.setBackground(Color.WHITE);
        txtTenKhachHang = new JTextField();
        txtTenKhachHang.setPreferredSize(dimenTextField);
        txtTenKhachHang.setFont(fontTextField);
        JLabel lblTenKH = new JLabel("Tên khách hàng: ");
        lblTenKH.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblTenKH.setPreferredSize(dimenLabel);
        pnTenKhachHang.add(lblTenKH);
        pnTenKhachHang.add(txtTenKhachHang);

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

        pnCenter.add(Box.createVerticalGlue());
        pnCenter.add(pnMaKhachHang);
        pnCenter.add(pnTenKhachHang);
        pnCenter.add(pnDiaChi);
        pnCenter.add(pnSDT);
        pnCenter.add(Box.createVerticalGlue());

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.add(pnTop, BorderLayout.NORTH);
        pnMain.add(pnCenter, BorderLayout.CENTER);
        pnMain.add(pnBottom, BorderLayout.SOUTH);

        Container con = this.getContentPane();
        con.add(pnMain);

    }

    private void setInforCustomer(CustomerModel customer) {
        txtMaKhachHang.setText(customer.getMaKhachHang());
        txtTenKhachHang.setText(customer.getTenKhachHang());
        txtDiaChi.setText(customer.getDiaChi());
        txtSDT.setText(customer.getSoDienThoai());
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
                CustomerModel customer = new CustomerModel();
                customer.setMaKhachHang(txtMaKhachHang.getText());
                customer.setTenKhachHang(txtTenKhachHang.getText());
                customer.setDiaChi(txtDiaChi.getText());
                customer.setSoDienThoai(txtSDT.getText());
                try {
                    if (CustomerController.editCustomer(database, customer)) {
                        try {
                            CustomerPanel.showListCustomer(CustomerController.getAllCustomer(database));
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        dispose();
                        JOptionPane.showMessageDialog(MainUI.frame, "Chỉnh sửa thành công", "Th" +
                                        "ông báo",
                                JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(MainUI.frame, "Chỉnh sửa thất bại", "Thông " +
                                        "báo",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    private void showDialog(Frame parent) {
        this.setSize(600, 450);
        this.setLocationRelativeTo(parent);
        this.setResizable(false);
        this.setVisible(true);
    }
}
