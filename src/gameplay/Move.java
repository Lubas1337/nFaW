package gameplay;
public interface Move{
    void setPos(int x, int y);
    void setX(int x);
    void setY(int y);
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    void setSpeed(int s);
    int getSpeed();
}