package gameplay;
import java.awt.Graphics;
public abstract class Moving implements Move {
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private int speed;
    public Moving(int x, int y, int w, int h) {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
    }
    public void setPos( int x, int y) {
        xPos = x;
        yPos = y;
    }
    public void setX( int x ) {
        xPos = x;
    }
    public void setY( int y ) {
        yPos = y;
    }
    public int getX() {
        return xPos;
    }
    public int getY() {
        return yPos;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void setSpeed( int s ) {
        speed = s;
    }
    public int getSpeed() {
        return speed;
    }
    public abstract void move(String direction);
    public abstract void draw(Graphics window);
    public String toString() {
        return getX() + " " + getY() + " " + getWidth() + " " + getHeight();
    }
}