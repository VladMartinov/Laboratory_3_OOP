import java.applet.Applet;
import java.awt.*;

public class BouncingCircleFirst extends Applet implements Runnable {
    private ColoredRect rect; // Теперь храним ColoredRect
    private int dx = 11, dy = 7; // Движение прямоугольника
    private Thread animator;
    private volatile boolean pleaseStop;

    public void init() {
        int x1 = 100;
        int y1 = 50;
        int x2 = x1 + 100;
        int y2 = y1 + 50;
        Color outColor = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        Color inColor = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        rect = new ColoredRect(x1, y1, x2, y2, outColor, inColor);
    }


    public void paint(Graphics g) {
        rect.draw(g);
    }


    public void animate() {
        Rectangle bounds = new Rectangle();
        int width = rect.x2 - rect.x1;
        int height = rect.y2 - rect.y1;

        // Проверка на столкновение с границами
        if ((rect.x1 + dx < 0) || (rect.x2 + dx > bounds.x2 - bounds.x1)) {
            dx = -dx;
        }
        if ((rect.y1 + dy < 0) || (rect.y2 + dy > bounds.y2 - bounds.y1)) {
            dy = -dy;
        }

        // Перемещение прямоугольника
        rect.move(dx, dy);
        repaint();
    }


    public void run() {
        while (!pleaseStop) {
            animate();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }


    public void start() {
        animator = new Thread(this);
        pleaseStop = false;
        animator.start(); // Запускаем поток
    }


    public void stop() {
        pleaseStop = true;
    }
}