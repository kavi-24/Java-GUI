/*
import java.awt.Graphics;
import java.awt.Dimension.Dimension;
import java.awt.Color;
*/
import java.awt.*;
/*
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
*/
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnakeGame {

  public static void main(String[] args) {
    // GameFrame frame = new GameFrame();
    new GameFrame();
  }
}


class GameFrame extends JFrame {

  GameFrame() {
    /*
        GamePanel panel = new GamePanel();
        this.add(panel);
        */
    this.add(new GamePanel());
    this.setTitle("Snake");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null);
  }
}

class GamePanel extends JPanel implements ActionListener {

  static final int SCREEN_WIDTH = 600;
  static final int SCREEN_HEIGHT = 600;
  static final int UNIT_SIZE = 25;
  static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
  static final int DELAY = 60; // Higher the num, slower the game
  final int x[] = new int[GAME_UNITS];
  final int y[] = new int[GAME_UNITS];
  int bodyParts = 6;
  int applesEaten;
  int appleX;
  int appleY;
  char direction = 'R'; // 'U', 'D', 'L'
  boolean running = false;
  Timer timer;
  Random random;

  GamePanel() {
    random = new Random();
    this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    this.setBackground(Color.BLACK);
    this.setFocusable(true);
    this.addKeyListener(new MyKeyAdapter());
    startGame();
  }

  public void startGame() {
    newApple();
    running = true;
    timer = new Timer(DELAY, this);
    timer.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }

  public void draw(Graphics g) {
    if (running) {
      // for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
      //     g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
      //     g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
      // }

      g.setColor(Color.red);
      g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

      for (int i = 0; i < bodyParts; i++) {
        if (i == 0) { // Head
          g.setColor(Color.green);
          g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        } else { // Body
          g.setColor(new Color(45, 180, 0)); // (rgb)
          g.setColor(
            new Color(
              random.nextInt(255),
              random.nextInt(255),
              random.nextInt(255)
            )
          );
          g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        }
      }
      g.setColor(Color.red);
      g.setFont(new Font("Ink Free", Font.BOLD, 40)); // java.awt.Font
      FontMetrics metrics = getFontMetrics(g.getFont());
      g.drawString(
        "SCORE: " + applesEaten,
        (SCREEN_WIDTH - metrics.stringWidth("SCORE: " + applesEaten)) / 2,
        g.getFont().getSize()
      );
    } else {
      gameOver(g);
    }
  }

  public void newApple() {
    appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
    appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
  }

  public void move() {
    for (int i = bodyParts; i > 0; i--) {
      x[i] = x[i - 1];
      y[i] = y[i - 1];
    }

    switch (direction) {
      case 'U':
        y[0] = y[0] - UNIT_SIZE;
        break;
      case 'D':
        y[0] = y[0] + UNIT_SIZE;
        break;
      case 'L':
        x[0] = x[0] - UNIT_SIZE;
        break;
      case 'R':
        x[0] = x[0] + UNIT_SIZE;
        break;
    }
  }

  public void checkApple() {
    if ((x[0] == appleX) && (y[0] == appleY)) {
      bodyParts += 1;
      applesEaten += 1;
      newApple();
    }
  }

  public void checkCollisions() {
    for (int i = bodyParts; i > 0; i--) {
      if (
        (x[0] == x[i]) && (y[0] == y[i])
      ) { // Head colliding with body
        running = false;
      }
    }

    if (x[0] < 0) {
      running = false;
    }

    if (x[0] > SCREEN_WIDTH) {
      running = false;
    }

    if (y[0] < 0) {
      running = false;
    }

    if (y[0] > SCREEN_HEIGHT) {
      running = false;
    }

    // if ((x[0] < 0) || (x[0] > SCREEN_WIDTH) || (y[0] < 0) || (y[0] > SCREEN_HEIGHT)) {
    //     running = false;
    // }

    if (!running) {
      timer.stop();
    }
  }

  public void gameOver(Graphics g) {
    g.setColor(Color.red);
    g.setFont(new Font("Ink Free", Font.BOLD, 75)); // java.awt.Font
    FontMetrics metrics = getFontMetrics(g.getFont());
    g.drawString(
      "SCORE: " + applesEaten,
      (SCREEN_WIDTH - metrics.stringWidth("SCORE: " + applesEaten)) / 2,
      (SCREEN_HEIGHT / 2) + (SCREEN_HEIGHT / 4)
    );

    g.setColor(Color.red);
    g.setFont(new Font("Ink Free", Font.BOLD, 75)); // java.awt.Font
    FontMetrics metrics2 = getFontMetrics(g.getFont());
    g.drawString(
      "GAME OVER",
      (SCREEN_WIDTH - metrics2.stringWidth("GAME OVER")) / 2,
      SCREEN_HEIGHT / 2
    );
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (running) {
      move();
      checkApple();
      checkCollisions();
    }
    repaint(); // java.awt.Component.repaint()
  }

  public class MyKeyAdapter extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
          if (direction != 'R') {
            direction = 'L';
          }
          break;
        case KeyEvent.VK_RIGHT:
          if (direction != 'L') {
            direction = 'R';
          }
          break;
        case KeyEvent.VK_DOWN:
          if (direction != 'U') {
            direction = 'D';
          }
          break;
        case KeyEvent.VK_UP:
          if (direction != 'D') {
            direction = 'U';
          }
          break;
      }
    }
  }
}

