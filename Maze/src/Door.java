import java.awt.Rectangle;
import java.awt.Color;
public class Door{
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;
    private Boolean open;

    public Door(int x,int y, int width, int height, Color color, Boolean open){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.open = open;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public Color getColor(){
        return color;
    }
    public Boolean getOpen(){
        return open;
    }
    public void setColor(Color x){
        color = x;
    }
    public void setOpen(Boolean x){
        open = x;
    }
    public Rectangle getRect(){
        return new Rectangle(getY()*getHeight(),getX()*getWidth(),getWidth(),getHeight());
    }

}