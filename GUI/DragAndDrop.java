package GUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Point;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

public class DragAndDrop {
    public static void main(String[] args) {
        // MyFrame myFrame = new MyFrame();
        new MyFrame();
    }
}

class MyFrame extends JFrame {

    DragPanel dragPanel = new DragPanel();

    MyFrame() {
        this.add(dragPanel);
        this.setTitle("Drag & Drop");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}

class DragPanel extends JPanel {

    ImageIcon image = new ImageIcon("./smiley.png");
    final int WIDTH = image.getIconWidth();
    final int HEIGHT = image.getIconHeight();
    Point imageCorner;
    Point previousPt;

    DragPanel() {

        imageCorner = new Point(0, 0);
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        image.paintIcon(this, g, (int) imageCorner.getX(), (int) imageCorner.getY());

    }

    private class ClickListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            previousPt = e.getPoint();
        }

    }

    private class DragListener extends MouseMotionAdapter {

        public void mouseDragged(MouseEvent e) {
            Point currentPoint = e.getPoint();
            imageCorner.translate(
                (int)(currentPoint.getX() - previousPt.getX()),
                (int)(currentPoint.getY() - previousPt.getY())
                );
            /*
             * void java.awt.Point.translate(int dx, int dy)
             * Translates this point, at location (x,y), by dx along the x axis and dy along
             * the y axis so that it now represents the point (x+dx,y+dy).
             * 
             * 
             */
            previousPt = currentPoint;
            repaint();
        }

    }

}
