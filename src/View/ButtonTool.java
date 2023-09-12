package View;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class ButtonTool extends JLabel {
    public ButtonTool(String pathImage) {
        this.setBackground(Color.WHITE);
        this.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(pathImage))));
        this.setOpaque(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ButtonTool.this.setBackground(Color.YELLOW);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ButtonTool.this.setBackground(Color.WHITE);
            }
        });
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(2, 10, 2, 10)));
    }
}
