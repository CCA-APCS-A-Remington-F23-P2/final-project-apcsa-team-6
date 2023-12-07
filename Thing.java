public class Thing
{
    private int xPos;
    private int yPos;
    private int width;
    private int height;

    public Thing(int x, int y, int w, int h)
    {
      xPos = x;
      yPos = y;
      width = w;
      height = h;
    }

    public boolean didOverlap(Thing other)
    {
    if (getY() + getHeight() < other.getY() || getY() > other.getY() + other.getHeight())
      return false;
    if (getX() + getWidth() < other.getX() || getX() > other.getX() + other.getWidth())
      return false;
    return true;
    }

    public void copyTransparent(Picture fromPic, int startRow, int startCol)
    {
      Pixel fromPixel = null;
      Pixel toPixel = null;
      Pixel[][] toPixels = this.getPixels2D();
      Pixel[][] fromPixels = fromPic.getPixels2D();
      for (int fromRow = 0, toRow = startRow; 
           fromRow < fromPixels.length &&
           toRow < toPixels.length; 
           fromRow++, toRow++)
      {
        for (int fromCol = 0, toCol = startCol; 
             fromCol < fromPixels[0].length &&
             toCol < toPixels[0].length;  
             fromCol++, toCol++)
        {
          fromPixel = fromPixels[fromRow][fromCol];
          toPixel = toPixels[toRow][toCol];
          if (fromPixel.getBlue() != 255 && fromPixel.getRed() != 255 && fromPixel.getGreen() != 255) {
            toPixel.setColor(fromPixel.getColor());
          }
        }
      }   
    }

    public abstract void draw(Graphics window);

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