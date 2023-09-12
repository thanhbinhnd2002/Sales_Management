package View;

import Controller.DatabaseConnection;
import Model.Database;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGUtils;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.sql.Connection;
import java.util.Objects;

public class DatabaseDialog extends JDialog {
    // attributes
    private final Database database;
    private JButton btnCheck, btnOK;
    private JLabel lblStatus;

    private JTextField txtNameDatabase, txtPort, txtUsername;
    private JPasswordField txtPassword;

    // contructor
    public DatabaseDialog(Window owner, Database database) {
        super(owner);
        this.setModal(true);
        this.database = database;
        intiComponents();
        addEvents();
        showDialog(owner);
    }

    private void intiComponents() {
        // ==================== Database Config ====================
        JLabel lblNameDatabase = new JLabel("Name Database: ");
        txtNameDatabase = new JTextField(15);
        txtNameDatabase.setText(database.getNameDatabase());
        txtNameDatabase.setEditable(false);
        JPanel pnNameDatabase = new JPanel();
        pnNameDatabase.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnNameDatabase.add(lblNameDatabase);
        pnNameDatabase.add(txtNameDatabase);

        JLabel lblPort = new JLabel("Port: ");
        lblPort.setPreferredSize(lblNameDatabase.getPreferredSize());
        lblPort.setHorizontalAlignment(JLabel.RIGHT);
        txtPort = new JTextField(15);
        txtPort.setText(database.getPort());
        JPanel pnPort = new JPanel();
        pnPort.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnPort.add(lblPort);
        pnPort.add(txtPort);

        JLabel lblUsername = new JLabel("Username: ");
        lblUsername.setPreferredSize(lblNameDatabase.getPreferredSize());
        lblUsername.setHorizontalAlignment(JLabel.RIGHT);
        txtUsername = new JTextField(15);
        txtUsername.setText(database.getUser());
        JPanel pnUsername = new JPanel();
        pnUsername.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnUsername.add(lblUsername);
        pnUsername.add(txtUsername);

        JLabel lblPassword = new JLabel("Password: ");
        lblPassword.setPreferredSize(lblNameDatabase.getPreferredSize());
        lblPassword.setHorizontalAlignment(JLabel.RIGHT);
        txtPassword = new JPasswordField(15);
        txtPassword.setText(database.getPass());
        JPanel pnPassword = new JPanel();
        pnPassword.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnPassword.add(lblPassword);
        pnPassword.add(txtPassword);

        JPanel pnInput = new JPanel();
        pnInput.setLayout(new BoxLayout(pnInput, BoxLayout.Y_AXIS));
        pnInput.add(Box.createVerticalGlue());
        pnInput.add(pnNameDatabase);
        pnInput.add(pnPort);
        pnInput.add(pnUsername);
        pnInput.add(pnPassword);
        pnInput.add(Box.createVerticalGlue());

        JPanel pnButton = new JPanel();
        pnButton.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnCheck = new JButton("Check");
        btnOK = new JButton("OK");
        pnButton.add(Box.createHorizontalStrut(62));
        pnButton.add(btnCheck);
        pnButton.add(btnOK);

        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        pnCenter.add(pnInput, BorderLayout.CENTER);
        pnCenter.add(pnButton, BorderLayout.SOUTH);

        // ==================== Status Panel ====================
        JPanel pnStatusbar = new JPanel();
        pnStatusbar.setLayout(new BorderLayout());
        pnStatusbar.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(EtchedBorder.LOWERED),
                BorderFactory.createEmptyBorder(0, 0, 0, 10)));
        pnStatusbar.setPreferredSize(new Dimension(0, 20));
        lblStatus = new JLabel();
        lblStatus.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblStatus.setHorizontalAlignment(JLabel.RIGHT);
        pnStatusbar.add(lblStatus, BorderLayout.SOUTH);


        Container contentPane = this.getContentPane();
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.add(pnCenter, BorderLayout.CENTER);
        pnMain.add(pnStatusbar, BorderLayout.SOUTH);
        contentPane.add(pnMain);
    }

    private void addEvents() {
        btnCheck.addActionListener(e -> {
            getDatabase();
            Connection conn = DatabaseConnection.getConnection(database);
            if (conn != null) {
                setStatusbarStage(true);
                DatabaseConnection.closeConnection(conn);
            } else {
                setStatusbarStage(false);
            }
        });

        btnOK.addActionListener(e -> {
            getDatabase();
            setVisible(false);
        });
    }

    private void setStatusbarStage(boolean stage) {
        if (stage) {
            lblStatus.setForeground(Color.GREEN);
            lblStatus.setText("Connection successfull!");
        } else {
            lblStatus.setForeground(Color.RED);
            lblStatus.setText("Connection failed!");
        }
    }

    private void getDatabase() {
        database.setNameDatabase(txtNameDatabase.getText());
        database.setPort(txtPort.getText());
        database.setUser(txtUsername.getText());
        database.setPass(String.valueOf(txtPassword.getPassword()));
    }

    private void showDialog(Window owner) {
        this.setTitle("Database Connection");
        this.setIconImages(FlatSVGUtils.createWindowIconImages(Objects.requireNonNull(this.getClass().getResource(
                "/Images/database-icon.svg"))));
        this.setSize(350, 250);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(owner);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        SwingUtilities.invokeLater(() -> {
            Database database = new Database();
            database.setNameDatabase("sales_management");
            database.setPort("3306");
            database.setUser("root");
            database.setPass("");
            new DatabaseDialog(null, database);
        });
    }
}
