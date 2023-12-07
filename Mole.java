import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Mole extends Thing
{
  public Mole(int x, int y)
  {
    super(x,y);
    try
    {
      URL url = getClass().getResource("mole.png");
      image = ImageIO.read(url);
    }
    catch(Exception e)
    {
    }
  }
}