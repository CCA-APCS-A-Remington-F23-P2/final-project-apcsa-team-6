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
  private Mole mole;
  private Bomb bomb;
  
  private BufferedImage back;
  private boolean[] keys;

  public Game() {
    // instantiate objects
    hammer = new Hammer(100, 100, 80, 80, 5);
    grid = new Grid(3);
    mole = new Mole(100, 100, 80, 80);
    bomb = new Bomb (185, 100, 80, 80);

    keys = new boolean[1];
    setBackground(Color.LIGHT_GRAY);
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
    graphToBack.setColor(Color.LIGHT_GRAY);
    graphToBack.fillRect(0, 0, 455, 485);

    if (keys[0]) {
      hammer.pressed();
    }
    else {
      hammer.released();
    }
    
    // draw methods
    grid.draw(graphToBack);
    mole.draw(graphToBack);
    bomb.draw(graphToBack);
    hammer.draw(graphToBack);
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
