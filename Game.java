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

public class Game extends Canvas implements KeyListener, Runnable {

    // instance variables here

    public Game() {
        // instantiate objects here 

        keys = new boolean[1];
        setBackground(Color.WHITE);
        setVisible(true);
        new Thread(this).start();
        addKeyListener(this);
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        Graphics2D twoDGraph = (Graphics2D)window;
        if (back==null)
          back = (BufferedImage)(createImage(getWidth(),getHeight()));
        Graphics graphToBack = back.createGraphics();

        graphToBack.setColor(Color.BLUE);
        graphToBack.drawString("Whack-a-Mole! ", 25, 50 );
        graphToBack.setColor(Color.WHITE);
        graphToBack.fillRect(0,0,800,600);

        // object draw methods here

        // if space is pressed
        if (keys[0]) {

        }
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

    public void keyTyped(KeyEvent e)
    {
    // no code needed here
    }

    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(5);
                repaint();
            }
        } catch (Exception e) {
        }
    }
}
