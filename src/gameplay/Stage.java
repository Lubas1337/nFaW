package gameplay;


import gameplay.heros.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
    public class Stage extends Canvas implements KeyListener, Runnable {
        private Boy boy;
        private Girl girl;
        private boolean [] keys;
        private BufferedImage back;
        private Grass grass;
        private double boyCount;
        private boolean fly, fly2;
        private Thread thread;
        private int girlCount;
        public Stage() throws IOException {
            girlCount = 0;
            keys = new boolean[10];
            thread = new Thread(this);
            grass = new Grass();
            boy = grass.getFireBoy();
            girl = grass.getWaterGirl();
            thread.start();
            boyCount = 0;
            fly = false;
            this.addKeyListener(this);
            new Thread(this).start();
            setVisible(true);
        }

        public void update(Graphics window) {
            paint(window);
        }



        public void paint(Graphics window) {
            Graphics2D twoDGraph = (Graphics2D)window;
            if(back==null){
                back = (BufferedImage)(createImage(getWidth(),getHeight()));
            }

            Graphics graphToBack = back.createGraphics();
            graphToBack.setColor(Color.BLUE);
            graphToBack.setColor(Color.BLACK);
            graphToBack.fillRect(0,0,this.getWidth(),this.getHeight());
            boolean pause = keys[8];

            switch (grass.getBottomMidOne(boy)) {
                case 5:
                case 6: pause = true; thread.interrupt(); break;
            }

            switch (grass.getBottomMidTwo(boy)) {
                case 5:
                case 6: pause = true; thread.interrupt(); break;
            }

            switch (grass.getBottomMidOne(girl)) {
                case 4:
                case 6: pause = true; thread.interrupt(); break;
            }

            switch (grass.getBottomMidTwo(girl)) {
                case 4:
                case 6: pause = true; thread.interrupt(); break;
            }

        if(!pause) {
            if(grass.getScore()/200 == grass.getLvlNum()) {
                grass.setLvl(0);
            }
            if(keys[0]) {
                if(boy.getX() > 0) {
                    boy.move("LEFT");
                }
                else if(boy.getX() > 0 && (grass.getCenterLeft(boy) == 0 || grass.getCenterLeft(boy) == 7|| grass.getCenterLeft(boy) == 8)) {
                    boy.move("LEFT");
                }
            }
            if(keys[1]) {
                if(boy.getX() < 715 ) {
                    boy.move("RIGHT");
                }
                else if(boy.getX() < 715 && (grass.getCenterRight(boy) == 0 || grass.getCenterRight(boy) == 7|| grass.getCenterRight(boy) ==8))
                    boy.move("RIGHT");
            }
            grass.comp(boy);
            if((keys[3])||(boy.getY() <= 0 || grass.getHeadLeft(boy) != 0 || grass.getHeadRight(boy) != 0)) {
                boyCount = 100;
                fly = false;
            }
            if(fly && boy.getY() > 0 && grass.getHeadLeft(boy) == 0 && grass.getHeadRight(boy) == 0) {
                if(boyCount >= 100)
                    fly = false;
                boy.move("UP");
                boyCount++;
            }
            else if(!fly && boyCount < 100 && keys[2] && boy.getY() > 0 && grass.getHeadLeft(boy) == 0 && grass.getHeadRight(boy) == 0) {
                fly = true;
            }

            else if((grass.getCenter(boy) == 0) && ((grass.getBottomMidFour(boy) ==0) && (grass.getBottomMidThree(boy) ==0)))
                boy.move("DOWN");
            else
                boyCount = 0;

            grass.comp(girl);
            if((keys[9])||(girl.getY() <= 0 || grass.getHeadLeft(girl) != 0 || grass.getHeadRight(girl) != 0)) {
                girlCount = 100;
                fly2 = false;
            }
            if(fly2 && girl.getY() > 0 && grass.getHeadLeft(girl) == 0 && grass.getHeadRight(girl) == 0) {
                if(girlCount >= 100)
                    fly2 = false;
                girl.move("UP");
                girlCount++;
            }
            else if(!fly2 && girlCount < 100 && keys[5] && girl.getY() > 0 && grass.getHeadLeft(girl) == 0 && grass.getHeadRight(girl) == 0) {
                fly2 = true;
            }

            else if((grass.getCenter(girl) == 0) && ((grass.getBottomMidFour(girl) ==0) && (grass.getBottomMidThree(girl) ==0)))
                girl.move("DOWN");
            else
                girlCount = 0;

            if(keys[6]) {
                if(girl.getX() > 0) {
                    girl.move("LEFT");
                }
                else if(girl.getX() > 0 && (grass.getCenterLeft(girl) == 0 || grass.getCenterLeft(girl) == 7|| grass.getCenterLeft(girl) ==8)) {
                    girl.move("LEFT");
                }
            }
            if(keys[7]) {
                if(girl.getX() < 715 ) {
                    girl.move("RIGHT");
                }
                else if(girl.getX() < 715 && (grass.getCenterRight(girl) == 0 || grass.getCenterRight(girl) == 7|| grass.getCenterRight(girl) ==8))
                    girl.move("RIGHT");
            }


        }
        int boyCenter = grass.getCenter(boy);
        int girlCenter = grass.getCenter(girl);

        if (boyCenter == 7 || girlCenter == 8) {
            grass.gem(7);
            grass.changeScore(100);
        } else if (boyCenter == 8 || girlCenter == 7) {
            grass.gem(8);
            grass.changeScore(-100);
        }


        grass.draw(graphToBack);
        boy.draw(graphToBack);
        girl.draw(graphToBack);
        if(pause) {
            graphToBack.drawString("Умер",350,35);
        }
        twoDGraph.drawImage(back, null, 0, 0);
    }


        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    keys[0] = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    keys[1] = true;
                    break;
                case KeyEvent.VK_UP:
                    keys[2] = true;
                    break;
                case KeyEvent.VK_DOWN:
                    keys[3] = true;
                    break;
                case KeyEvent.VK_W:
                    keys[5] = true;
                    break;
                case KeyEvent.VK_A:
                    keys[6] = true;
                    break;
                case KeyEvent.VK_D:
                    keys[7] = true;
                    break;
                case KeyEvent.VK_S:
                    keys[9] = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    keys[8] = !keys[8];
                    break;
                case KeyEvent.VK_Q:
                    grass.setLvl(0);
                    break;
                case KeyEvent.VK_E:
                    grass.setLvl(-2);
                    break;
                case KeyEvent.VK_SPACE:
                    JOptionPane.showMessageDialog(null,"Нетрогай фиолетовый");
                    break;
            }
            repaint();
        }


        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    keys[0] = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    keys[1] = false;
                    break;
                case KeyEvent.VK_UP:
                    keys[2] = false;
                    break;
                case KeyEvent.VK_DOWN:
                    keys[3] = false;
                    break;
                case KeyEvent.VK_W:
                    keys[5] = false;
                    break;
                case KeyEvent.VK_A:
                    keys[6] = false;
                    break;
                case KeyEvent.VK_D:
                    keys[7] = false;
                    break;
                case KeyEvent.VK_S:
                    keys[9] = false;
                    break;
            }
            repaint();
        }



        public void keyTyped(KeyEvent e) {}

    public void run() {
        try {
            while(!thread.interrupted()) {
                thread.currentThread().sleep(8);
                repaint();
            }
        }
        catch(Exception e) {}
    }
}