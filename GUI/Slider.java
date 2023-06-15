package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Slider {
    public static void main(String[] args) {
        new SliderDemo();
    }
}

class SliderDemo implements ChangeListener {

    JFrame frame;
    JPanel panel;
    JLabel label;
    JSlider slider;

    SliderDemo() {

        frame = new JFrame("Slider Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        label = new JLabel();
        slider = new JSlider(0, 150, 75); // (min, max, initialVal)

        slider.setPreferredSize(new Dimension(400, 200));
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(15);

        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(30);

        slider.setPaintLabels(true);
        slider.setFont(new Font("MV Boli", Font.PLAIN, 15));

        // slider.setPreferredSize(new Dimension(200, 350));
        slider.setOrientation(SwingConstants.VERTICAL);

        slider.addChangeListener(this);

        // Hold Ctrl+Shift+U and type 2103 or 00BA
        label.setText("℃= " + slider.getValue());
        // Without modifying stateChanged(), the label value won't change
        label.setFont(new Font("MV Boli", Font.PLAIN, 25));

        panel.add(slider);
        panel.add(label);
        frame.add(panel);
        frame.setSize(420, 420);
        frame.setVisible(true);


    }

    @Override
    public void stateChanged(ChangeEvent e) {
        label.setText("℃= " + slider.getValue());
    }

}