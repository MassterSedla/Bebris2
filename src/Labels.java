import javax.swing.*;
import java.awt.*;

public class Labels extends JLabel {


    public Labels(String text, int height){
        this.setLayout(null);
        this.setText(text);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setForeground(new Color(224, 3, 20));
        this.setFont(new Font("MV Boli", Font.PLAIN, 15));
        this.setBackground(new Color(255, 255, 255));
        this.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setOpaque(true);
        this.setBounds(210, height, 100, 20);
    }
    public Labels(int text, int height){
        this.setLayout(null);
        this.setText(String.valueOf(text));
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setForeground(new Color(224, 3, 20));
        this.setFont(new Font("MV Boli", Font.PLAIN, 15));
        this.setBackground(new Color(255, 255, 255));
        this.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setOpaque(true);
        this.setBounds(210, height, 100, 20);
    }
    public void changeLabels(int text, int height) {
        this.setLayout(null);
        this.setText(String.valueOf(text));
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setForeground(new Color(224, 3, 20));
        this.setFont(new Font("MV Boli", Font.PLAIN, 15));
        this.setBackground(new Color(255, 255, 255));
        this.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setOpaque(true);
        this.setBounds(210, height, 100, 20);
    }
}
