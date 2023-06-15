import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Mouse extends Frame implements MouseMotionListener{
	Label l;
	Color c=Color.BLUE;

	Mouse(){
		l=new Label();
		l.setBounds(20,40,100,20);
		add(l);

		addMouseMotionListener(this);

		setSize(400,400);
		setLayout(null);
		setVisible(true);
	}

	public void mouseDragged(MouseEvent e) {
		l.setText("X="+e.getX()+", Y="+e.getY());
		Graphics g=getGraphics();
		g.setColor(Color.RED);
		g.fillOval(e.getX(),e.getY(),20,20);
	}
	public void mouseMoved(MouseEvent e) {
		l.setText("X="+e.getX()+", Y="+e.getY());
	}
	public static void main(String[] args) {
		new Mouse();
	}
}
/*
import java.awt.*;
import java.awt.event.*;

public class MouseMotionListenerExample extends Frame implements MouseMotionListener {
	MouseMotionListenerExample(){
		addMouseMotionListener(this);
		setSize(300,300);
		setLayout(null);
		setVisible(true);
	}
	
	public void mouseDragged(MouseEvent e) {
		Graphics g=getGraphics();
		g.setColor(Color.BLUE);
		g.fillOval(e.getX(),e.getY(),20,20);
	}
	
	public void mouseMoved(MouseEvent e) {}

	public static void main(String[] args) {
		new MouseMotionListenerExample();
	}
}
*/

/*
    import java.awt.*;  
    import java.awt.event.MouseEvent;  
    import java.awt.event.MouseMotionListener;  
    public class Paint extends Frame implements MouseMotionListener{  
        Label l;  
        Color c=Color.BLUE;  
        Paint(){  
        l=new Label();  
        l.setBounds(20,40,100,20);  
        add(l);  
          
        addMouseMotionListener(this);  
          
        setSize(400,400);  
        setLayout(null);  
        setVisible(true);  
    }  
    public void mouseDragged(MouseEvent e) {  
        l.setText("X="+e.getX()+", Y="+e.getY());  
        Graphics g=getGraphics();  
        g.setColor(Color.RED);  
        g.fillOval(e.getX(),e.getY(),20,20);  
    }  
    public void mouseMoved(MouseEvent e) {  
        l.setText("X="+e.getX()+", Y="+e.getY());  
    }  
    public static void main(String[] args) {  
        new Paint();  
    }  
    }  
*/

/*
// Java program to illustrate MouseListener
// and MouseMotionListener events
// simultaneously

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Mouse extends Frame implements MouseMotionListener, MouseListener {

	// Jlabels to display the actions of events of MouseMotionListener and MouseListener
	static JLabel label1, label2, label3, label4, label5;

	// default constructor
	Mouse()
	{
	}

	// main class
	public static void main(String[] args)
	{
		// create a frame
		JFrame f = new JFrame("MouseListener and MouseMotionListener");

		// set the size of the frame
		f.setSize(900, 300);

		// close the frame when close button is pressed
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create anew panel
		JPanel p = new JPanel();
		JPanel p1 = new JPanel();

		// set the layout of the frame
		f.setLayout(new FlowLayout());

		JLabel l1, l2;

		l1 = new JLabel("MouseMotionListener events :");

		l2 = new JLabel("MouseLIstener events :");

		// initialize the labels
		label1 = new JLabel("no event ");

		label2 = new JLabel("no event ");

		label3 = new JLabel("no event ");

		label4 = new JLabel("no event ");

		label5 = new JLabel("no event ");

		// create an object of mouse class
		Mouse m = new Mouse();

		// add mouseListener and MouseMotionListenerto the frame
		f.addMouseMotionListener(m);
		f.addMouseListener(m);

		// add labels to the panel
		p.add(l1);
		p.add(label1);
		p.add(label2);
		p1.add(l2);
		p1.add(label3);
		p1.add(label4);
		p1.add(label5);

		// add panel to the frame
		f.add(p);
		f.add(p1);

		f.show();
	}

	// getX() and getY() functions return the
	// x and y coordinates of the current
	// mouse position
	// getClickCount() returns the number of
	// quick consecutive clicks made by the user

	// MouseMotionListener events

	// invoked when mouse button is pressed
	// and dragged from one point to another
	// in a component
	public void mouseDragged(MouseEvent e)
	{
		// update the label to show the point
		// through which point mouse is dragged
		label1.setText("mouse is dragged through point "
					+ e.getX() + " " + e.getY());
	}

	// invoked when the cursor is moved from
	// one point to another within the component
	public void mouseMoved(MouseEvent e)
	{
		// update the label to show the point to which the cursor moved
		label2.setText("mouse is moved to point "
					+ e.getX() + " " + e.getY());
	}

	// MouseListener events

	// this function is invoked when the mouse is pressed
	public void mousePressed(MouseEvent e)
	{

		// show the point where the user pressed the mouse
		label3.setText("mouse pressed at point:"
					+ e.getX() + " " + e.getY());
	}

	// this function is invoked when the mouse is released
	public void mouseReleased(MouseEvent e)
	{

		// show the point where the user released the mouse click
		label3.setText("mouse released at point:"
					+ e.getX() + " " + e.getY());
	}

	// this function is invoked when the mouse exits the component
	public void mouseExited(MouseEvent e)
	{

		// show the point through which the mouse exited the frame
		label4.setText("mouse exited through point:"
					+ e.getX() + " " + e.getY());
	}

	// this function is invoked when the mouse enters the component
	public void mouseEntered(MouseEvent e)
	{

		// show the point through which the mouse entered the frame
		label4.setText("mouse entered at point:"
					+ e.getX() + " " + e.getY());
	}

	// this function is invoked when the mouse is pressed or released
	public void mouseClicked(MouseEvent e)
	{

		// getClickCount gives the number of quick,
		// consecutive clicks made by the user
		// show the point where the mouse is i.e
		// the x and y coordinates
		label5.setText("mouse clicked at point:"
					+ e.getX() + " "
					+ e.getY() + "mouse clicked :" + e.getClickCount());
	}
}

*/


