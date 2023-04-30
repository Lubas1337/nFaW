package gameplay.heros;
import gameplay.Moving;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.net.*;

public class Girl extends Moving {
    private Image image;
    public Girl() { this(0, 400, 40, 40, 1);}

    public Girl(int x, int y, int w, int h, int s) {
        super(x, y, w, h);
        setSpeed(s);
        try {
            URL url = getClass().getResource("blueEntity.png");
            image = ImageIO.read(url);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Image was not found !");
        }
    }

    public void move(String direction) {
        switch (direction.toUpperCase()) {
            case "LEFT": setX(getX() - getSpeed());
                break;
            case "RIGHT": setX(getX() + getSpeed());
                break;
            case "UP": setY(getY() - getSpeed());
                break;
            case "DOWN": setY(getY() + getSpeed());
                break;
            default: break;
        }
    }



    public void draw(Graphics window) {
        window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }

    public String toString() {
        return super.toString() + getSpeed();
    }
}
