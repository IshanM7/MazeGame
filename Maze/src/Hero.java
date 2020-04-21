import java.awt.Color;
import java.awt.Rectangle;
import java.util.*;
public class Hero{
    private int x;
    private int y;
    private int width;
    private int height;
    private Color body;
    private Color outline;
    public Hero(int x, int y, int width, int height, Color body, Color outline){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.body = body;
        this.outline = outline;
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
    public Color getBody(){
        return body;
    }
    public Color getOutline(){
        return outline;
    }
    public Rectangle collisionBox(int x, int y)
    {
        return new Rectangle(y*getHeight(),x*getWidth(),getWidth(),getHeight());
    }



    public Rectangle getRect(){
        return new Rectangle(getY()*getWidth(),getX()*getHeight(),getWidth(),getHeight());
    }
    public void move(int dir, ArrayList<Wall> walls, ArrayList<Door> doors){
        boolean canMove = true;
        switch(dir)
        {
            //left
            case 37:
                for(Wall wall: walls){
                    Rectangle futureLoc = collisionBox(x,y-1);
                    if(futureLoc.intersects(wall.getRect()))
                    {
                        canMove = false;
                        System.out.println("left");
                    }
                }
                for(Door door: doors){
                    Rectangle futureLoc = collisionBox(x,y-1);
                    if(futureLoc.intersects(door.getRect()))
                    {
                        if(door.getOpen())
                            canMove = true;
                        else
                            canMove = false;
                        System.out.println("left");
                    }
                }
                if(canMove)
                    y--;
                break;


            //up
            case 38:
                for(Wall wall: walls){
                    Rectangle futureLoc = collisionBox(x-1,y);
                    if(futureLoc.intersects(wall.getRect()))
                    {
                        canMove = false;
                        System.out.println("up");
                    }
                }
                for(Door door: doors){
                    Rectangle futureLoc = collisionBox(x-1,y);
                    if(futureLoc.intersects(door.getRect()))
                    {
                        if(door.getOpen())
                            canMove = true;
                        else
                            canMove = false;
                        System.out.println("up");
                    }
                }
                if(canMove)
                    x--;

                break;
            //right
            case 39:
                for(Wall wall: walls){
                    Rectangle futureLoc = collisionBox(x,y+1);
                    if(futureLoc.intersects(wall.getRect()))
                    {
                        canMove = false;
                        System.out.println("right");
                    }
                }
                for(Door door: doors){
                    Rectangle futureLoc = collisionBox(x,y+1);
                    if(futureLoc.intersects(door.getRect()))
                    {
                        if(door.getOpen())
                            canMove = true;
                        else
                            canMove = false;
                        System.out.println("right");
                    }
                }
                if(canMove)
                    y++;
                break;
            //down
            case 40:
                for(Wall wall: walls){
                    Rectangle futureLoc = collisionBox(x+1,y);
                    if(futureLoc.intersects(wall.getRect()))
                    {
                        canMove = false;
                        System.out.println("down");
                    }
                }
                for(Door door: doors){
                    Rectangle futureLoc = collisionBox(x+1,y);
                    if(futureLoc.intersects(door.getRect()))
                    {
                        if(door.getOpen())
                            canMove = true;
                        else
                            canMove = false;
                        System.out.println("down");
                    }
                }
                if(canMove)
                    x++;
                break;
        }

    }
}
