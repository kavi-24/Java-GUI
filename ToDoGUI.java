// Project: GUI To Do List App

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

class AppFrame extends JFrame {
    private TitleBar title;
    private ButtonPanel btnPanel;
    private List list;

    private JButton addTask;
    private JButton clear;

    AppFrame() {

        title = new TitleBar();
        list = new List();
        btnPanel = new ButtonPanel();

        this.setSize(400, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(title, BorderLayout.NORTH);
        this.add(btnPanel, BorderLayout.SOUTH);
        this.add(list, BorderLayout.CENTER);

        addTask = btnPanel.getAddTask();
        clear = btnPanel.getClear();

        addListeners();

        this.setVisible(true);
    }

    public void addListeners() {

        addTask.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Task task = new Task();
                list.add(task);
                list.updateNumber();
                task.getDone().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        task.changeState();
                        revalidate();
                    }   
                }
                );
                revalidate();
            }
        });

    }

}

class TitleBar extends JPanel {

    TitleBar() {
        this.setPreferredSize(new Dimension(400, 80));
        this.setBackground(Color.darkGray);
        JLabel titleText = new JLabel("To Do List");
        titleText.setPreferredSize(new Dimension(200, 80));
        titleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
        titleText.setForeground(Color.LIGHT_GRAY);
        titleText.setHorizontalAlignment(JLabel.CENTER);

        this.add(titleText);
    }

}

class ButtonPanel extends JPanel {
    private JButton addTask;
    private JButton clear;

    Border emptyBorder = BorderFactory.createEmptyBorder();

    ButtonPanel() {
        this.setPreferredSize(new Dimension(400, 60));
        // this.setBackground(Color.red);

        addTask = new JButton("Add Task");
        addTask.setBorder(emptyBorder);
        addTask.setFont(new Font("Sans-serif", Font.PLAIN, 20));
        this.add(addTask);

        // this.add(Box.createHorizontalStrut(20)); // Gap

        // clear = new JButton("Clear Completed");
        // clear.setBorder(emptyBorder);
        // clear.setFont(new Font("Sans-serif", Font.PLAIN, 20));
        // this.add(clear);
    }

    public JButton getAddTask() {
        return addTask;
    }

    public JButton getClear() {
        return clear;
    }

}

class List extends JPanel {
    List() {
        GridLayout layout = new GridLayout(10, 1);
        layout.setVgap(5);
        this.setLayout(layout);
        this.setBackground(Color.LIGHT_GRAY);
    }
    public void updateNumber() {
        Component[] listItems = this.getComponents();
        for (int i=0; i < listItems.length; i++) {
            if (listItems[i] instanceof Task) {
                ((Task)listItems[i]).changeIndex(i+1);
            }
        }
    }
}

class Task extends JPanel {

    private JLabel index;
    private JTextField taskName;
    private JButton done;

    private boolean checked;

    Task() {
        this.setPreferredSize(new Dimension(40, 20));
        this.setBackground(Color.GRAY);

        this.setLayout(new BorderLayout());
        checked = false;

        index = new JLabel("");
        index.setPreferredSize(new Dimension(20, 20));
        index.setHorizontalAlignment(JLabel.CENTER);
        this.add(index, BorderLayout.WEST);

        taskName = new JTextField("Your Task Here");
        taskName.setBorder(BorderFactory.createEmptyBorder());
        taskName.setBackground(Color.gray);
        taskName.setFont(new Font("Sans-serif", Font.BOLD, 20));
        this.add(taskName, BorderLayout.CENTER);

        done = new JButton("DONE");
        done.setPreferredSize(new Dimension(40, 20));
        done.setBorder(BorderFactory.createEmptyBorder());
        this.add(done, BorderLayout.EAST);
    }

    public JButton getDone() {
        return done;
    }

    public void changeIndex(int num) {
        this.index.setText(num+"");
        this.revalidate();
    }

    public void changeState() {
        if (!checked) {
            this.setBackground(Color.green);
            taskName.setBackground(Color.green);
            checked = true;
            done.setText("UNDO");
        }
        else {
            this.setBackground(Color.gray);
            taskName.setBackground(Color.gray);
            checked = false;
            done.setText("DONE");
        }
    }
}

public class ToDoGUI {
    public static void main(String[] args) {
        new AppFrame();
    }
}
