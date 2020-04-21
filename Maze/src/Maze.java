import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Color;
public class Maze extends JPanel implements KeyListener,Runnable
{
    private int width = 50;
    private int height = 50;
    private JFrame frame;
    private int a = 80;
    private int b = 80;
    private Thread thread;
    private Color wallColor = new Color(.1f,.4f,.1f,1f);;
    private Color Transparent = new Color(0f,0f,0f,0f );
    private boolean gameOn=true;
    private boolean right=false;
    private boolean left=false;
    private boolean up=false;
    private boolean down=false;
    private ArrayList<Wall> walls;
    private ArrayList<Door> doors;
    private int dim = 20;
    private Hero hero;
    private Hero Monster;
    private int keycounter;
    private Wall key;
    private Wall key2;
    private Wall key3;
    private Wall key4;
    private Wall mainkey;
    private Door door;
    private Door door2;
    private Door door3;
    private Door door4;
    private Door maindoor;
    private int dir;
    private int stepcounter;
    private String[][] free;
    private boolean[] keysFound=new boolean[5];


    public Maze()
    {
        frame=new JFrame("Maze");
        frame.add(this);
        createMaze("/Users/Ishan/Maze/src/MazeText");

        doors = new ArrayList<Door>();
        hero = new Hero(10,5,dim,dim,Color.MAGENTA,Color.WHITE);
        Monster = new Hero(1,39,dim,dim,new Color(1f,.5f,.1f,1f),new Color(1f,.5f,.1f,.1f));
        key = new Wall(2,2,dim,dim,Color.YELLOW);
        key2 = new Wall(7,21,dim,dim,Color.YELLOW);
        key3 = new Wall(11,38,dim,dim,Color.YELLOW);
        key4 = new Wall(3,39,dim,dim,Color.YELLOW);
        mainkey = new Wall(5,25,dim,dim,Color.PINK);
        doors.add(door = new Door(5,22,dim,dim,Color.BLUE,false));
        doors.add(door2 = new Door(5,28,dim,dim,Color.BLUE,false));
        doors.add(door3 = new Door(2,25,dim,dim,Color.BLUE,false));
        doors.add(door4 = new Door(8,25,dim,dim,Color.BLUE,false));
        doors.add(maindoor = new Door(5,40,dim,dim,wallColor,false));
        //instantiate hero

        frame.addKeyListener(this);
        frame.setSize(1300,750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        thread=new Thread(this);
        thread.start();


    }

    public void createMaze(String fileName)
    {
        walls = new ArrayList<Wall>();
        File name = new File(fileName);
        try
        {
            BufferedReader input = new BufferedReader(new FileReader(name));
            int row=0;
            int c = 0;
            String text;
            while( (text=input.readLine())!= null)
            {
                for(int col = 0; col < text.length(); col++){
                    c = col;
                    if(text.charAt(col) == '*'){
                        walls.add(new Wall(row,col,dim,dim,Color.RED));
                    }

                }
                row++;
                //store maze in here
            }

        }
        catch (IOException io)
        {
            System.err.println("File does not exist");
        }
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,frame.getWidth(),frame.getHeight());
        if(stepcounter <= 25){
            wallColor = new Color(.1f,.4f,.1f,1f);
        }
        if(stepcounter <= 50 && stepcounter > 25){
            wallColor = new Color(.1f,.4f,.1f,.9f);

        }
        if(stepcounter <= 75 && stepcounter > 50){
            wallColor = new Color(.1f,.4f,.1f,.8f);
        }
        if(stepcounter <= 100 && stepcounter > 75){
            wallColor = new Color(.1f,.4f,.1f,.7f);
        }
        if(stepcounter <= 125 && stepcounter > 100){
            wallColor = new Color(.1f,.4f,.1f,.6f);
        }
        if(stepcounter <= 150 && stepcounter > 125){
            wallColor = new Color(.1f,.4f,.1f,.5f);
        }
        if(stepcounter <= 175 && stepcounter > 150){
            wallColor = new Color(.1f,.4f,.1f,.4f);
        }
        if(stepcounter <= 200 && stepcounter > 175){
            wallColor = new Color(.1f,.4f,.1f,.3f);
        }
        if(stepcounter <= 225 && stepcounter > 200){
            wallColor = new Color(.1f,.4f,.1f,.2f);
        }
        if(stepcounter <= 250 && stepcounter > 225){
            wallColor = new Color(.1f,.4f,.1f,.1f);
        }


        g2.setColor(wallColor);
        for(Wall wall:walls){
            g2.fill(wall.getRect());
        }
        if(!maindoor.getOpen())
            g2.fill(maindoor.getRect());

        g2.setColor(hero.getBody());
        g2.fill(hero.getRect());
        g2.setColor(hero.getOutline());
        g2.draw(hero.getRect());
        g2.setColor(Monster.getBody());
        g2.fill(Monster.getRect());
        g2.setColor(Monster.getOutline());
        g2.draw(Monster.getRect());


        g2.setColor(key.getColor());
        g2.fill(key.getRect());
        g2.setColor(key2.getColor());
        g2.fill(key2.getRect());
        g2.setColor(key3.getColor());
        g2.fill(key3.getRect());
        g2.setColor(key4.getColor());
        g2.fill(key4.getRect());

        g2.setColor(mainkey.getColor());
        g2.fill(mainkey.getRect());

        g2.setColor(door.getColor());
        g2.fill(door.getRect());

        g2.setColor(door2.getColor());
        g2.fill(door2.getRect());

        g2.setColor(door3.getColor());
        g2.fill(door3.getRect());

        g2.setColor(door4.getColor());
        g2.fill(door4.getRect());



        g2.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.PLAIN,20));

        g.drawString("KeyCounter: "+keycounter,10,350);
        g.drawString("StepCounter: "+stepcounter,200,350);

        if((Math.abs(hero.getX() - Monster.getX()) <= 1) && (Math.abs(hero.getY() - Monster.getY()) <=1))
        {
            g2.setColor(Color.BLACK);
            g2.fillRect(0,0,frame.getWidth(),frame.getHeight());
            g2.setColor(Color.WHITE);
            g.setFont(new Font("Brodway",Font.BOLD,100));
            g.drawString("You Died ",400,400);
            gameOn = false;
        }
        if(hero.getX() == 5 && hero.getY() >= 41)
        {
            g2.setColor(Color.BLACK);
            g2.fillRect(0,0,frame.getWidth(),frame.getHeight());
            g2.setColor(Color.WHITE);
            g.setFont(new Font("Brodway",Font.BOLD,100));
            g.drawString("You Win! ",400,400);
            gameOn = false;
        }
    }
    public void run()
    {
        while(gameOn)
        {


            if(!keysFound[0] && (hero.getX()-key.getX() == 0) && (hero.getY() - key.getY() == 0))
            {
                key.setColor(Transparent);
                door.setColor(Transparent);
                door.setOpen(true);
                keycounter++;
                keysFound[0]=true;
            }
            if(!keysFound[1] && (hero.getX()-key2.getX() == 0) && (hero.getY() - key2.getY() == 0))
            {
                key2.setColor(Transparent);
                door2.setColor(Transparent);
                door2.setOpen(true);
                keycounter++;
                keysFound[1]=true;
            }
            if(!keysFound[2] && (hero.getX()-key3.getX() == 0) && (hero.getY() - key3.getY() == 0))
            {
                key3.setColor(Transparent);
                door3.setColor(Transparent);
                door3.setOpen(true);
                keycounter++;
                keysFound[2]=true;
            }
            if(!keysFound[3] && (hero.getX()-key4.getX() == 0) && (hero.getY() - key4.getY() == 0))
            {
                key4.setColor(Transparent);
                door4.setColor(Transparent);
                door4.setOpen(true);
                keycounter++;
                keysFound[3]=true;
            }
            if(!keysFound[4] && (hero.getX()-mainkey.getX() == 0) && (hero.getY() - mainkey.getY() == 0))
            {
                if(keycounter >= 4)
                {
                    mainkey.setColor(Transparent);
                    maindoor.setColor(Transparent);
                    maindoor.setOpen(true);
                    keycounter = 0;
                    keysFound[4]=true;

                }

            }

            //repaint();


            //move hero (if it can move)
            //pick stuff up if there is stuff to be picked up


            //move other stuff

            //check collisions if stuff is moving

            try
            {
                thread.sleep(20);
            }catch(InterruptedException e)
            {
            }
            repaint();
        }
    }

    public void keyPressed(KeyEvent e)
    {
        dir = e.getKeyCode();
        stepcounter++;
        if(!(Monster.getX() == 5 && Monster.getY() > 40))
            Monster.move(dir,walls,doors);
        if(Monster.getX() == 5 && Monster.getY() > 40)
            Monster.move(37,walls,doors);
        hero.move(dir,walls,doors);


    }
    public void keyReleased(KeyEvent e)
    {
        dir = 0;
    }
    public void keyTyped(KeyEvent e)
    {
    }

    public static void main(String[] args)
    {
        Maze app=new Maze();
    }

}