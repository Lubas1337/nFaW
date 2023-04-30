package gameplay;
import gameplay.heros.Boy;
import gameplay.heros.Girl;

import java.io.File;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Grass{
    private LinkedList<int[][]> lvl;
    private Image grass;
    private int [][] grassOn;
    public final static int SIZE = 30;
    private Image lava, water, poison, rGem, bGem, comp, wall;
    private Boy boy;
    private Girl girl;

    private int score;
    public Grass()throws FileNotFoundException {
        score = 0;
        lvl = new LinkedList<int[][]>();
        boy = new Boy();
        girl = new Girl();
        changeStage("levels.txt");
        grassOn = lvl.get(0);

        for(int d = 0; d < lvl.size(); d++){
            for(int a = 0; a< lvl.get(d).length; a++){
                for(int b = 0; b < lvl.get(d)[a].length; b++){
                    System.out.print (lvl.get(d)[a][b]);
                }

                System.out.println ();
            }
            System.out.println ();
        }
        try{
            URL url = getClass().getResource("assets/block.jpg");
            grass = ImageIO.read(url);
            URL url3 = getClass().getResource("assets/lavaBlock.png");
            lava = ImageIO.read(url3);
            URL url4 = getClass().getResource("assets/waterBlock.jpg");
            water = ImageIO.read(url4);
            URL url5 = getClass().getResource("assets/poisonBlock.jpg");
            poison = ImageIO.read(url5);
            URL url6 = getClass().getResource("assets/redPoint.png");
            rGem = ImageIO.read(url6);
            URL url7 = getClass().getResource("assets/blue.png");
            bGem = ImageIO.read(url7);
            URL url8 = getClass().getResource("assets/laver.jpg");
            comp = ImageIO.read(url8);
            URL url9 = getClass().getResource("assets/gate.png");
            wall = ImageIO.read(url9);

        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Image was not found !");
        }
    }
    public void changeStage(String file) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File(file));
        int x = 0;
        int lvlNum = in.nextInt();
        in.nextLine();
        int [] [] temp = new int[20][26];
        for(int c = 0; c < lvlNum; c++)
        {

            for(int b = 0; b< 19; b++){
                String line = in.nextLine();
                String[] lin = line.split(" ");

                for(int a = 0; a < lin.length; a++){
                    temp[x][a] = Integer.parseInt(lin[a]);

                }
                x++;
            }
            lvl.add(temp);
            temp = new int[27][27];
            x= 0;
        }
    }

    public void setLvl(int a){
        grassOn = lvl.get(lvl.indexOf(grassOn)+1 + a);
        boy.setPos(0,400);
        girl.setPos(710,400);
    }

    public int getLvlNum(){
        return lvl.indexOf(grassOn)+1;
    }

    public void changeScore(int a){
        score +=a;
    }
    public void draw(Graphics window){
        for(int a = 0; a < grassOn.length; a++){
            for (int b = 0; b < grassOn[a].length; b++) {
                switch (grassOn[a][b]) {
                    case -2:
                        grassOn[a][b] = 0;
                        break;
                    case 1: case -1:
                        window.drawImage(grass, b * SIZE, a * SIZE, SIZE, SIZE, null);
                        break;
                    case 2: case 3:
                        window.drawImage(comp, b * SIZE, a * SIZE, SIZE, SIZE, null);
                        break;
                    case 4:
                        window.drawImage(lava, b * SIZE, a * SIZE, SIZE, SIZE, null);
                        break;
                    case 12: case 13: case -11:
                        window.drawImage(wall, b * SIZE, a * SIZE, SIZE, SIZE, null);
                        break;
                    case 5:
                        window.drawImage(water, b * SIZE, a * SIZE, SIZE, SIZE, null);
                        break;
                    case 6:
                        window.drawImage(poison, b * SIZE, a * SIZE, SIZE, SIZE, null);
                        break;
                    case 7:
                        window.drawImage(rGem, b * SIZE, a * SIZE, SIZE, SIZE, null);
                        break;
                    case 8:
                        window.drawImage(bGem, b * SIZE, a * SIZE, SIZE, SIZE, null);
                        break;
                }
                window.drawString("Score: " + score, 600, 35);
            }
        }
    }

    public int getHeadLeft(Moving o){
        int x = o.getX() / SIZE;
        int y = o.getY() / SIZE;
        return grassOn[y][x];
    }

    public int getHeadRight(Moving o){
        int x = (o.getX() + o.getWidth())/ SIZE;
        int y = o.getY() / SIZE;
        return grassOn[y][x];
    }

    public int getCenterLeft(Moving o){
        int x = o.getX() / SIZE;
        int y = (o.getY() + o.getHeight() /2) / SIZE;
        return grassOn[y][x];
    }

    public int getCenter(Moving o){
        int x = (o.getX() + o.getWidth()/2)/ SIZE;
        int y = (o.getY() + o.getHeight()/2 ) / SIZE;
        return grassOn[y][x];
    }

    public int getCenterRight(Moving o){
        int x = (o.getX() + o.getWidth())/ SIZE;
        int y = (o.getY() + o.getHeight()/2 ) / SIZE;
        return grassOn[y][x];
    }


    public int getBottomMidOne(Moving o){
        int x = (o.getX() + (2 * o.getWidth()/ 5))/ SIZE;
        int y = (o.getY() + o.getHeight()) / SIZE ;
        return grassOn[y][x];
    }

    public int getBottomMidTwo(Moving o){
        int x = (o.getX() + (o.getWidth() - ((2 * o.getWidth())/5)))/ SIZE;
        int y = (o.getY() + o.getHeight()) / SIZE ;
        return grassOn[y][x];
    }

    public int getBottomMidThree(Moving o){
        int x = (o.getX() + (2 * o.getWidth()/ 7))/ SIZE;
        int y = (o.getY() + o.getHeight()) / SIZE ;
        return grassOn[y][x];
    }

    public int getBottomMidFour(Moving o){
        int x = (o.getX() + (o.getWidth() - ((2 * o.getWidth())/7)))/ SIZE;
        int y = (o.getY() + o.getHeight()) / SIZE ;
        return grassOn[y][x];
    }

    public void comp(Moving o){
        int x = (o.getX() + (2 * o.getWidth()/ 5))/ SIZE;
        int y = (o.getY() + o.getHeight()) / SIZE ;
        if(grassOn[y][x] == 2)
        {
            grassOn[y][x] = 0;
            boolean more = false;
            for(int r = 0; r < grassOn.length; r++)
                for(int c = 0; c < grassOn[r].length;c++)
                {
                    if(grassOn[r][c] == 2)
                        more = true;
                }
            if(!more)
                for(int r = 0; r < grassOn.length; r++)
                    for(int c = 0; c < grassOn[r].length;c++)
                    {
                        if(grassOn[r][c] == 12)
                            grassOn[r][c] = 0;
                    }

        }
        if(grassOn[y][x] == 3)
        {
            grassOn[y][x] = 0;
            boolean more = false;
            for(int r = 0; r < grassOn.length; r++)
                for(int c = 0; c < grassOn[r].length;c++){
                    if(grassOn[r][c] == 3)
                        more = true;
                }
            if(!more)
                for(int r = 0; r < grassOn.length; r++)
                    for(int c = 0; c < grassOn[r].length;c++){
                        if(grassOn[r][c] == 13)
                            grassOn[r][c] = 0;
                    }
        }

    }


    public int getScore(){
        return score;
    }

    public void gem(int whichGem){
        for(int r = 0; r < grassOn.length; r++)
            for(int c = 0; c < grassOn[r].length;c++){
                if(grassOn[r][c] == whichGem)
                    grassOn[r][c] = 0;
            }
    }

    public Boy getFireBoy(){
        return boy;
    }

    public Girl getWaterGirl()
    {
        return girl;
    }

}


