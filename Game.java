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


  //https://www.developer.com/java/java-buttons/

  // instance variables
  private Hammer hammer;
  private Grid grid;

  private int score;
  private int highScore;
  private boolean gameOver;
  private boolean gameStart;
  private boolean lev1;
  private boolean lev2;
  private boolean lev3;
  private int hammerSpeed;
  private int bombSpeed;
  private int moleSpeed;
  private int removeSpeed;

  private BufferedImage back;
  private boolean[] keys;

  private long currentTime;

  public Game() {
    // instantiate objects
    hammer = new Hammer(100, 100, 80, 80, 2, 1);
    grid = new Grid(3);

    currentTime = System.currentTimeMillis();

    score = 0;
    highScore = 0;
    gameOver = false;
    gameStart = true;
    lev1 = false;
    lev2 = false;
    lev3 = false;

    keys = new boolean[4];
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
    graphToBack.fillRect(0, 0, 540, 570);

    if (gameStart){
      graphToBack.setColor(Color.BLACK);
      graphToBack.drawString("Press 1 for Level 1", 225, 140);
      graphToBack.drawString("Press 2 for Level 2", 225, 165);
      graphToBack.drawString("Press 3 for Level 3", 225, 190);

      if (keys[2]) lev1 = true;
      else lev1 = false;
      if (keys[3]) lev2 = true;
      else lev2 = false;
      if (keys[4]) lev3 = true;
      else lev3 = false;

      gameStart = false;
    }


    if (!gameOver && !gameStart) {
      graphToBack.setColor(Color.BLACK);
      graphToBack.drawString("Score: " + score, 15, 15);

      //level 1
      if (lev1) {
        hammerSpeed = 1;
        bombSpeed = 4000;
        moleSpeed = 2000;
        removeSpeed = 2000;
      }

      //level 2
      if (lev2) {
        hammerSpeed = 2;
        bombSpeed = 3000;
        moleSpeed = 3000;
        removeSpeed = 1000;
      }

      //level 3
      if (lev3) {
        hammerSpeed = 3;
        bombSpeed = 2000;
        moleSpeed = 4000;
        removeSpeed = 750;
      }
      hammer.setXSpeed(hammerSpeed + 1);
      hammer.setYSpeed(hammerSpeed);

      if (keys[0]) {
        hammer.pressed();
        for (int i = 0; i < grid.getMoles().size(); i++){
          if (hammer.didOverlap(grid.getMoles().get(i))){
            score++;
            grid.removeMole(i);
          }
        }
        for (int i = 0; i < grid.getBombs().size(); i++){
          if (hammer.didOverlap(grid.getBombs().get(i))){
            gameOver = true;
          }
        }
      }
      else {
        hammer.released();
      }

      // change direction if hammer leaves the grid
      if (hammer.getX() < 100 || hammer.getX() + hammer.getWidth() > 455){
        hammer.setXSpeed(-hammer.getXSpeed());
      }
      if (hammer.getY() < 100 || hammer.getY() + hammer.getHeight() > 455){
        hammer.setYSpeed(-hammer.getYSpeed());
      }

      // randomly add mole and bombs
      if (System.currentTimeMillis() - currentTime > (int)(Math.random()*moleSpeed + 1000)) {
        grid.addMole();
        currentTime = System.currentTimeMillis();
      }

      if (System.currentTimeMillis() - currentTime > (int)(Math.random()*bombSpeed + 1000)) {
        grid.addBomb();
        currentTime = System.currentTimeMillis();
      }

      // remove moles and bombs
      for (int i = 0; i < grid.getMoles().size(); i++){
        if (grid.getMoles().get(i).getTime() == removeSpeed){
          grid.removeMole(i);
        }
      }
      for (int j = 0; j < grid.getBombs().size(); j++){
        if (grid.getBombs().get(j).getTime() == removeSpeed){
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
      graphToBack.drawString("Score: " + score, 250, 190);
      graphToBack.drawString("High Score: " + 0, 240, 215);
      graphToBack.drawString("Press 1 for Level 1", 225, 240);
      graphToBack.drawString("Press 2 for Level 2", 225, 265);
      graphToBack.drawString("Press 3 for Level 3", 225, 290);

      if (keys[1])
      {
        gameOver = false;
        score = 0;
        lev1 = true; lev2 = false; lev3 = false;
      }
      if (keys[2])
      {
        gameOver = false;
        score = 0;
        lev1 = false; lev2 = true; lev3 = false;
      }
      if (keys[3])
      {
        gameOver = false;
        score = 0;
        lev1 = false; lev2 = false; lev3 = true;
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
    if (e.getKeyCode() == KeyEvent.VK_1)
    {
      keys[1] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_2)
    {
      keys[2] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_3)
    {
      keys[3] = true;
    }
    repaint();
  }

  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keys[0] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_1)
    {
      keys[1] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_2)
    {
      keys[2] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_3)
    {
      keys[3] = false;
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