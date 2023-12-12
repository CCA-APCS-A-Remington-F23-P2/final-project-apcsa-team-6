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
  private List <Mole> moles;
  private List <Bomb> bombs;

  public Grid(int size)
  {
    holes = new ArrayList<Hole>();
    moles = new ArrayList<Mole>();
    bombs = new ArrayList<Bomb>();

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        holes.add(new Hole(100 + j * 85, 100 + i * 85, 80, 80));
      }
    }  
  }

  public List<Mole> getMoles()
  {
    return moles;
  }

  public List<Bomb> getBombs()
  {
    return bombs;
  }

  public void addMole()
  {
    while (true)
      {
        int rand = (int) (Math.random() * holes.size());
        if (!holes.get(rand).getOccupied())
        {
          moles.add(new Mole(holes.get(rand).getX(), holes.get(rand).getY(), 80, 80));
          holes.get(rand).setOccupied();
          break;
        }
      }
  }

  public void removeMole(int i)
  {
    int xPos = moles.get(i).getX();
    int yPos = moles.get(i).getY();
    moles.remove(i);

    for (Hole h: holes) {
      if (h.getX() == xPos && h.getY() == yPos) {
        h.setUnoccupied();
      }
    }
  }

  public void addBomb()
  {
    while (true)
      {
        int rand = (int) (Math.random() * holes.size());
        if (!holes.get(rand).getOccupied())
        {
          bombs.add(new Bomb(holes.get(rand).getX(), holes.get(rand).getY(), 80, 80));
          holes.get(rand).setOccupied();
          break;
        }
      }
  }

  public void removeBomb(int i)
  {
    int xPos = bombs.get(i).getX();
    int yPos = bombs.get(i).getY();
      bombs.remove(i);

    for (Hole h: holes) {
      if (h.getX() == xPos && h.getY() == yPos) {
        h.setUnoccupied();
      }
    }
  }

  public void draw(Graphics window)
  {
    for (Hole h : holes) {
      h.draw(window);
    }
    for (Mole m : moles) {
      m.draw(window);
    }
    for (Bomb b : bombs) {
      b.draw(window);
    }
  }
}