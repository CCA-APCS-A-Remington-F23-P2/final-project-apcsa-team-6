import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Bomb extends Thing
{

  private Image image;
  private int time;

  public Bomb(int x, int y, int w, int h)
  {
    super(x, y, w, h);
    time = 0;
    try
    {
      URL url = getClass().getResource("media/bomb.png");
      image = ImageIO.read(url);
    }
    catch(Exception e)
    {
    }
  }

  public int getTime() {
    return time;
  }

  public void draw(Graphics window)
  {
    window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    time += 5;
  }
}