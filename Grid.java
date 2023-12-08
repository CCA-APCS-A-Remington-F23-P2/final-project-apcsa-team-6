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
  int size;

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
    this.size = size;
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
    int rand = (int) (Math.random() * holes.size());
    moles.add(new Mole(holes.get(rand).getX(), holes.get(rand).getY(), 80, 80));
  }

  public void removeMole(int i)
  {
    moles.remove(i);
  }

  public void addBomb()
  {
    int rand = (int)(Math.random() * holes.size());
    bombs.add(new Bomb(holes.get(rand).getX(), holes.get(rand).getY(), 80, 80));
  }

  public void removeBomb(int i)
  {
    bombs.remove(i);
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
