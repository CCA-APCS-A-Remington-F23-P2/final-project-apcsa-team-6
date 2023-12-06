public class Thing
{
    private int xPos;
    private int yPos;
    private int width;
    private int height;

    public Thing(int x, int y)
    {
        xPos = x;
        yPos = y;
        width = 20;
        height = 20;
    }

    public boolean didOverlap(MovingThing other)
    {
    if (getY() + getHeight() < other.getY() || getY() > other.getY() + other.getHeight())
      return false;
    if (getX() + getWidth() < other.getX() || getX() > other.getX() + other.getWidth())
      return false;
    return true;
    }

    public void setPos( int x, int y)
    {
    xPos = x;
    yPos = y;
    }

    public void setX(int x)
    {
        xPos = x;
    }

    public void setY(int y)
    {
        yPos = y;
    }

    public int getX()
    {
        return xPos;
    }

    public int getY()
    {
        return yPos;
    }

    public void setWidth(int w)
    {
        width = w;
    }

    public void setHeight(int h)
    {
        height = h;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}