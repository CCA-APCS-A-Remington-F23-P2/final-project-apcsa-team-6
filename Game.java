import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements KeyListener, Runnable {

  // instance variables
  private Hammer hammer;
  private Grid grid;

  private BufferedImage back;
  private boolean[] keys;

  public Game() {
    // instantiate objects
    hammer = new Hammer(100, 100, 80, 160, 5);
    grid = new Grid(3);

    keys = new boolean[1];
    setBackground(Color.BLACK);
    setVisible(true);
    new Thread(this).start();
    addKeyListener(this);
  }

  public void update(Graphics window) {
    paint(window);
  }

  public void paint(Graphics window) {
    Graphics2D twoDGraph = (Graphics2D) window;
    if (back == null)
      back = (BufferedImage) (createImage(getWidth(), getHeight()));
    Graphics graphToBack = back.createGraphics();

    graphToBack.setColor(Color.BLUE);
    graphToBack.drawString("Whack-a-Mole", 25, 50);
    graphToBack.setColor(Color.LIGHT_GRAY);
    graphToBack.fillRect(0, 0, 800, 600);

    if (keys[0]) {
      hammer.pressed();
    }
    else {
      hammer.released();
    }
    
    // draw methods
    hammer.draw(graphToBack);
    grid.draw(graphToBack);
    twoDGraph.drawImage(back, null, 0, 0);
  }

  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keys[0] = true;
    }
    repaint();
  }

  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keys[0] = false;
    }
    repaint();
  }

  public void keyTyped(KeyEvent e){}

  public void run() {
    try {
      while (true) {
        Thread.currentThread().sleep(5);
        repaint();
      }
    } 
    catch (Exception e) {
    }
  }
}
