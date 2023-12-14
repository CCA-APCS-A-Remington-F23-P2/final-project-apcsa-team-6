import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Mole extends Thing
{

  private Image image;
  private int time;
  private int squishTime;
  private boolean squished;

  public Mole(int x, int y, int w, int h)
  {
    super(x, y, w, h);
    try
    {
      URL url = getClass().getResource("media/mole.png");
      image = ImageIO.read(url);
    }
    catch(Exception e)
    {
    }
    time = 0;
  }

  public int getTime()
  {
    return time;
  }

  public void setSquished()
  {
    squished = true;
    setY(getY() + 34);
    setHeight(40);
  }

  public boolean getSquished()
  {
    return squished;
  }

  public int getSquishTime()
  {
    return squishTime;
  }

  public void draw(Graphics window)
  {
    window.drawImage(image, getX(), getY(), getWidth(), getHeight(),null);
    time += 5;
    if (squished) {
      squishTime += 5;
    }
  }
}