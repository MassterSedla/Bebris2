import javax.swing.*;
import java.awt.*;

public class Buttons extends JButton{
    public Buttons(String text, int height){
        this.setLayout(null);
        this.setText(text);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setForeground(new Color(224, 3, 20));
        this.setFont(new Font("MV Boli", Font.BOLD, 17));
        this.setBackground(new Color(0, 205, 205));
        this.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 255), 1));
        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setOpaque(true);
        this.setBounds(80, height, 150, 30);
    }
}
