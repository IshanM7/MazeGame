import java.awt.Rectangle;
import java.awt.Color;
public class Wall{
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Wall(int x,int y, int width, int height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
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
    public void setColor(Color x){
        color = x;
    }
    public void setX(int x){
        x = x;
    }
    public void setY(int y){
        y = y;
    }
    public Rectangle getRect(){
        return new Rectangle(getY()*getHeight(),getX()*getWidth(),getWidth(),getHeight());
    }




}