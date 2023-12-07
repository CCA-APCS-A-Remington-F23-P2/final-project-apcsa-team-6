import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Bomb extends Thing
{
  public Bomb(int x, int y)
  {
    super(x,y,40,40);
    try
    {
      URL url = getClass().getResource("bomb.png");
      image = ImageIO.read(url);
    }
    catch(Exception e)
    {
    }
  }

  public void draw(Graphics window)
  {
    window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
  }
}