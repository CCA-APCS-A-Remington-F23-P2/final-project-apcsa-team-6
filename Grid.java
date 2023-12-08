import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Grid
{
  private List<Hole> holes;
  int size;

  public Grid(int size)
  {
    holes = new ArrayList<Hole>();
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        add(new Hole(100 + j * 85, 100 + i * 85, 80, 80));
      }
    }
    this.size = size;
  }

  public void add(Hole hole)
  {
    holes.add(hole);
  }

  public void draw(Graphics window)
  {
    for (Hole h : holes) {
      h.draw(window);
    }
  }
}
