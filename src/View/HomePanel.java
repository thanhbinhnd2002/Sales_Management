package View;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class HomePanel extends JPanel {
    private final String[] image = {"image1.svg", "image2.svg", "image3.svg", "image4.svg", "image5.svg", "image6.svg",
            "image7.svg", "image8.svg"};
    private static int index = 0;

    // components
    private JLabel lblImage;
    private Timer ChangeImage;

    // constructor
    public HomePanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(245, 245, 251));
        initComponents();
        ChangeImage.start();
    }

    private void initComponents() {
        // implementation the top panel
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());
        pnTop.setBackground(new Color(245, 245, 251));
        JLabel lblTitle = new JLabel("HỆ THỐNG QUẢN LÝ BÁN HÀNG");
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTitle.setForeground(new Color(78, 138, 211));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        pnTop.add(lblTitle, BorderLayout.CENTER);

        // implementation the center panel
        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        pnCenter.setBackground(new Color(245, 245, 251));
        lblImage = new JLabel();
        lblImage.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/home/" + image[index]))));
        ChangeImage = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index++;
                index = index % 8;
                lblImage.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                        "/Images/home/" + image[index]))));
                lblImage.updateUI();
            }
        });

        lblImage.setHorizontalAlignment(JLabel.CENTER);
        pnCenter.add(lblImage, BorderLayout.CENTER);

        this.add(pnTop, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        JFrame frame = new JFrame();
        frame.setTitle("Home");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().add(new HomePanel());
        frame.setVisible(true);
    }
}
