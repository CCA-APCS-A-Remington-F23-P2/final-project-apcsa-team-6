import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Hammer extends Thing
{

  private int speed;

  public Hammer()
  {
    this(0,0,20,20);
    speed = 5;
  }

  public Hammer(int x, int y)
  {
    this(x,y,20,20);
    speed = 5;
  }

  public Hammer(int x, int y, int s)
  {
    this(x,y,20,20);
    speed = s;
  }

  public Hammer(int x, int y, int w, int h, int s)
  {
    super(x,y,w,h);
    speed = s;
    try
    {
      URL url = getClass().getResource("hammer.png");
      image = ImageIO.read(url);
    }
    catch(Exception e)
    {

    }
  }

  public setSpeed(int s)
  {
    speed = s;
  }

  public int getSpeed() 
  {
    return speed;
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

}