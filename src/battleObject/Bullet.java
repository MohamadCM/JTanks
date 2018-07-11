package battleObject;
/**
 * This class is used for creating and moving bullets
 *
 * @author Mohamad Chaman-Motlagh
 *
 */

import Map.Map;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet{

    private double angle;
    private BufferedImage bulletImage;
    private int X;
    private int Y;
    private final int speed;

    public Bullet(int mouseX, int mouseY, int locationX, int locationY, BufferedImage bulletImage) {
        this.bulletImage = bulletImage;
        X = locationX + 50;
        Y = locationY + 50;
        angle = Math.atan2(mouseY - (locationY + 50), mouseX - (locationX + 50));

        speed = 12;
        for (int i = 0; i < 5; i++) {
            forward();
        }
    }

    public void paint(Graphics2D g2d) {
        forward();
        AffineTransform bulletAT = new AffineTransform();
        bulletAT.setToTranslation(X , Y);
        bulletAT.rotate(angle);
        g2d.drawImage(bulletImage, bulletAT, null);
    }

    public void forward(){
        if (!Map.checkBulletCollision(this)) {
            X += Math.cos(angle) * speed;
            Y += Math.sin(angle) * speed;
        }
    }

    /**
     * @return X location of each bullet
     */
    public int getX() {
        return X;
    }

    /**
     * @return Y location of each bullet
     */
    public int getY() {
        return Y;
    }
}