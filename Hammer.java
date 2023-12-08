import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Hammer extends Thing
{
  private Image image;
  private int speed;

  public Hammer(int x, int y, int w, int h, int s)
  {
    super(x, y, w, h);
    speed = s;
    try
    {
      URL url = getClass().getResource("media/hammer.png");
      image = ImageIO.read(url);
    }
    catch(Exception e)
    {
    }
  }

  public void setSpeed(int s)
  {
    speed = s;
  }

  public int getSpeed() 
  {
    return speed;
  }
  
  public void pressed()
  {
    try
    {
      URL url = getClass().getResource("media/hammer2.png");
      image = ImageIO.read(url);
    }
    catch(Exception e)
    {
    }
  }

  public void released()
  {
    try
    {
      URL url = getClass().getResource("media/hammer.png");
      image = ImageIO.read(url);
    }
    catch(Exception e)
    {
    }
  }

  public void move(String direction)
  {
    if (direction.equals("DOWN"))
      setY(getY() + speed);
    if (direction == "UP")
      setY(getY() - speed);
    if (direction == "LEFT")
      setX(getX() - speed);
    if (direction == "RIGHT")
      setX(getX() + speed);
  }

  public void draw(Graphics window)
  {
    window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
  }
}