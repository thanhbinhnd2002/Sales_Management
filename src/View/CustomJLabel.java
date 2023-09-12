package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomJLabel extends JLabel {
    public CustomJLabel(String text) {
        super(text);
        this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setBorder(BorderFactory.createLineBorder(new Color(75, 102, 147), 2));
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
        this.setOpaque(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                CustomJLabel.this.setBackground(new Color(78, 138, 201));
                CustomJLabel.this.setForeground(Color.BLACK);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                CustomJLabel.this.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                CustomJLabel.this.setBorder(BorderFactory.createLineBorder(new Color(75, 102,
                        147), 2));
            }
        });
    }

    public void setSelected(boolean selected) {
        if (selected) {
            this.setBackground(new Color(78, 138, 201));
            this.setForeground(Color.WHITE);
        } else {
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }
    }
}
