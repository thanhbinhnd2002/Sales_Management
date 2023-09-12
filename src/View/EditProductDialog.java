package View;


import Controller.ProductController;
import Model.Database;
import Model.ProductModel;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class EditProductDialog extends JDialog {
    private JTextField txtMaSanPham, txtTenSanPham, txtGia, txtDonVi, txtLoai, txtSoLuong;
    private JDatePicker txtHan;
    private JButton btnXacNhan, btnThoat;
    private final Dimension dimenLabel = new Dimension(200, 25);
    private final Dimension dimenTextField = new Dimension(200, 30);
    private final Color backGroundBlue = new Color(78, 138, 201);
    private final Database database;
    private final ProductModel product;


    public EditProductDialog(Window owner, String title, ProductModel product, Database database) {
        super(owner);
        this.setTitle(title);
        this.setModal(true);
        this.database = database;
        this.product = product;
        initComponents();
        setInforProduct(product);

        addEvents();
        showDialog(owner);

    }

    private void initComponents() {
        //The top panel
        JPanel pnTop = new JPanel();
        pnTop.setBackground(new Color(245, 245, 251));
        JLabel lblTieuDe = new JLabel("Thông tin sản phẩm");
        lblTieuDe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTieuDe.setForeground(backGroundBlue);
        pnTop.add(lblTieuDe);


        //The right panel
        JPanel pnCenter = new JPanel();
        pnCenter.setBackground(new Color(245, 245, 251));
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));

        JPanel pnMaSanPham = new JPanel();
        pnMaSanPham.setBackground(new Color(245, 245, 251));
        txtMaSanPham = new JTextField();
        txtMaSanPham.setEditable(false);
        txtMaSanPham.setPreferredSize(dimenTextField);
        txtMaSanPham.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        JLabel lblMaSanPham = new JLabel("Mã sản phẩm: ");
        lblMaSanPham.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblMaSanPham.setPreferredSize(dimenLabel);
        pnMaSanPham.add(lblMaSanPham);
        pnMaSanPham.add(txtMaSanPham);

        JPanel pnTenSanPham = new JPanel();
        pnTenSanPham.setBackground(new Color(245, 245, 251));
        txtTenSanPham = new JTextField();
        txtTenSanPham.setPreferredSize(dimenTextField);
        txtTenSanPham.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        JLabel lblTenSanPham = new JLabel("Tên sản phẩm: ");
        lblTenSanPham.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblTenSanPham.setPreferredSize(dimenLabel);
        pnTenSanPham.add(lblTenSanPham);
        pnTenSanPham.add(txtTenSanPham);

        JPanel pnGia = new JPanel();
        pnGia.setBackground(new Color(245, 245, 251));
        txtGia = new JTextField();
        txtGia.setPreferredSize(dimenTextField);
        txtGia.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        JLabel lblGia = new JLabel("Giá: ");
        lblGia.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblGia.setBackground(new Color(245, 245, 251));
        lblGia.setPreferredSize(dimenLabel);
        pnGia.add(lblGia);
        pnGia.add(txtGia);

        JPanel pnDonVi = new JPanel();
        pnDonVi.setBackground(new Color(245, 245, 251));
        txtDonVi = new JTextField();
        txtDonVi.setPreferredSize(dimenTextField);
        txtDonVi.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        JLabel lblDonVi = new JLabel("Đơn vị: ");
        lblDonVi.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblDonVi.setPreferredSize(dimenLabel);
        pnDonVi.add(lblDonVi);
        pnDonVi.add(txtDonVi);

        JPanel pnLoai = new JPanel();
        pnLoai.setBackground(new Color(245, 245, 251));
        txtLoai = new JTextField();
        txtLoai.setPreferredSize(dimenTextField);
        txtLoai.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        JLabel lblLoai = new JLabel("Loại: ");
        lblLoai.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblLoai.setPreferredSize(dimenLabel);
        pnLoai.add(lblLoai);
        pnLoai.add(txtLoai);

        JPanel pnHan = new JPanel();
        pnHan.setBackground(new Color(245, 245, 251));
        txtHan = new JDatePicker(product.getHan());
        txtHan.setPreferredSize(dimenTextField);
        txtHan.getComponent(0).setPreferredSize(new Dimension(170, 30));
        txtHan.getComponent(1).setPreferredSize(new Dimension(30, 30));
        txtHan.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        JLabel lblHan = new JLabel("Hạn: ");
        lblHan.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblHan.setPreferredSize(dimenLabel);
        pnHan.add(lblHan);
        pnHan.add(txtHan);

        JPanel pnSoLuong = new JPanel();
        pnSoLuong.setBackground(new Color(245, 245, 251));
        txtSoLuong = new JTextField();
        txtSoLuong.setPreferredSize(dimenTextField);
        txtSoLuong.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        JLabel lblSoLuong = new JLabel("Số lượng: ");
        lblSoLuong.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblSoLuong.setPreferredSize(dimenLabel);
        pnSoLuong.add(lblSoLuong);
        pnSoLuong.add(txtSoLuong);


        pnCenter.add(Box.createVerticalGlue());
        pnCenter.add(pnMaSanPham);
        pnCenter.add(pnTenSanPham);
        pnCenter.add(pnDonVi);
        pnCenter.add(pnLoai);
        pnCenter.add(pnHan);
        pnCenter.add(pnGia);
        pnCenter.add(pnSoLuong);
        pnCenter.add(Box.createVerticalGlue());

        JPanel pnSouth = new JPanel();
        pnSouth.setBackground(new Color(245, 245, 251));
        pnSouth.setLayout(new FlowLayout(FlowLayout.CENTER));


        btnXacNhan = new JButton("Xác nhận");
        btnXacNhan.setIcon(new FlatSVGIcon(Objects.requireNonNull(EditProductDialog.class.getResource("/Images/24x24/checked_24x24.svg"))));
        btnXacNhan.setBackground(Color.WHITE);
        btnXacNhan.setForeground(Color.BLACK);
        btnXacNhan.setPreferredSize(new Dimension(160, 38));
        btnXacNhan.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 19));
        JPanel pnbtnXacNhan = new JPanel();
        pnbtnXacNhan.setBackground(new Color(245, 245, 251));
        pnbtnXacNhan.add(btnXacNhan);

        btnThoat = new JButton("Thoát");
        btnThoat.setIcon(new FlatSVGIcon(Objects.requireNonNull(EditProductDialog.class.getResource("/Images/24x24/exitDialog_24x24.svg"))));
        btnThoat.setBackground(Color.WHITE);
        btnThoat.setForeground(Color.BLACK);
        btnThoat.setPreferredSize(new Dimension(160, 38));
        btnThoat.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 19));
        JPanel pnbtnThoat = new JPanel();
        pnbtnThoat.setBackground(new Color(245, 245, 251));
        pnbtnThoat.add(btnThoat);

        pnSouth.add(pnbtnXacNhan);
        pnSouth.add(pnbtnThoat);

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.add(pnTop, BorderLayout.NORTH);
        pnMain.add(pnCenter, BorderLayout.CENTER);
        pnMain.add(pnSouth, BorderLayout.SOUTH);

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
                ProductModel product = new ProductModel();
                product.setMaSanPham(txtMaSanPham.getText());
                product.setTenSanPham(txtTenSanPham.getText());
                product.setGia(txtGia.getText());
                product.setLoai(txtLoai.getText());
                product.setDonVi(txtDonVi.getText());
                product.setSoLuong(txtSoLuong.getText());
                Date han = (Date) txtHan.getModel().getValue();
                if (han != null) {
                    product.setHan(han);
                    try {
                        if (ProductController.updateProduct(database, product)) {
                            showListProduct(ProductController.getAllProducts(database));
                            dispose();
                            JOptionPane.showMessageDialog(MainUI.frame, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(MainUI.frame, "Sửa thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else
                    JOptionPane.showMessageDialog(MainUI.frame, "Bạn chưa chọn ngày", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    private void showListProduct(List<ProductModel> listProduct) {

        ProductPanel.dtmDanhSachSP.setRowCount(0);
        for (ProductModel ProductModel : listProduct) {
            Vector<String> vector = new Vector<>();
            vector.add(ProductModel.getMaSanPham());
            vector.add(ProductModel.getTenSanPham());
            vector.add(ProductModel.getDonVi());
            vector.add(ProductModel.getLoai());
            vector.add(new SimpleDateFormat("dd/MM/yyyy").format(ProductModel.getHan()));
            vector.add(ProductModel.getGia());
            vector.add(ProductModel.getSoLuong());
            ProductPanel.dtmDanhSachSP.addRow(vector);
        }
    }

    private void setInforProduct(ProductModel Product) {
        txtMaSanPham.setText(Product.getMaSanPham());
        txtTenSanPham.setText(Product.getTenSanPham());
        txtDonVi.setText(Product.getDonVi());
        txtLoai.setText(Product.getLoai());
        txtGia.setText(Product.getGia());
        txtSoLuong.setText(Product.getSoLuong());
    }

    private void showDialog(Window owner) {
        this.setSize(809, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(owner);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setVisible(true);
    }
}