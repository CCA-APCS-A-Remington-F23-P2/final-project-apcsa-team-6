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

  private int score;
  private int highScore;
  private boolean gameOver;

  private BufferedImage back;
  private boolean[] keys;

  private long currentTime;

  public Game() {
    // instantiate objects
    hammer = new Hammer(100, 100, 80, 80, 3, 2);
    grid = new Grid(3);

    currentTime = System.currentTimeMillis();


    score = 0;
    highScore = 0;
    gameOver = false;

    keys = new boolean[2];
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
    graphToBack.setColor(Color.BLACK);
    graphToBack.drawString("Score: " + score, 15, 15);

    if (!gameOver){
      if (keys[0]) {
        hammer.pressed();
        for (int i = 0; i < grid.getMoles().size(); i++){
          if (hammer.didOverlap(grid.getMoles().get(i))){
            score++;
            grid.removeMole(i);
          }
          else if (hammer.didOverlap(grid.getBombs().get(i))){
            gameOver = true;
          }
        }
      }
      else {
        hammer.released();
      }

      // change direction if hammer leaves the grid
      if (hammer.getX() < 100 || hammer.getX() + hammer.getWidth() > 355){
        hammer.setXSpeed(-hammer.getXSpeed());
      }
      if (hammer.getY() < 100 || hammer.getY() + hammer.getHeight() > 355){
        hammer.setYSpeed(-hammer.getYSpeed());
      }

      // randomly add mole and bombs
      if (System.currentTimeMillis() - currentTime > (int)(Math.random()*4000 + 1000)) {
        grid.addMole();
        currentTime = System.currentTimeMillis();
      }

      if (System.currentTimeMillis() - currentTime > (int)(Math.random()*4000 + 1000)) {
        grid.addBomb();
        currentTime = System.currentTimeMillis();
      }


      // remove moles and bombs
      for (int i = 0; i < grid.getMoles().size(); i++){
        if (grid.getMoles().get(i).getTime() == 1000){
          grid.removeMole(i);
        }
      }
      for (int j = 0; j < grid.getBombs().size(); j++){
        if (grid.getBombs().get(j).getTime() == 1000){
          grid.removeBomb(j);
        }
      }

      // draw methods
      grid.draw(graphToBack);
      hammer.move();
      hammer.draw(graphToBack);
    }

    if (gameOver) {
      if (score > highScore)highScore = score;
        graphToBack.setColor(Color.BLACK);
      graphToBack.drawString("Score: " + score, 200, 190);
      graphToBack.drawString("High Score: " + highScore, 185, 220);
      graphToBack.drawString("Press 'r' To Restart", 180, 250);

      if (keys[1])
      {
        gameOver = false;
        score = 0;
      }
    }

    twoDGraph.drawImage(back, null, 0, 0);
  }

  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keys[0] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_R)
    {
      keys[1] = true;
    }
    repaint();
  }

  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keys[0] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_R)
    {
      keys[1] = false;
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