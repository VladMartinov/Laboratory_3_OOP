import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingCircleSecond extends Applet implements Runnable {
    private static final int RECT_SIZE = 30;
    private static final int NUM_RECTANGLES = 10;
    private static final int NUM_DRAWABLE_RECTANGLES = 10;
    private static final int NUM_COLORED_RECTANGLES = 10;
    private List<Rectangle> rectangles;
    private List<Integer> dxList;
    private List<Integer> dyList;

    Thread animator;
    volatile boolean pleaseStop;
    Random random = new Random();

    @Override
    public void init() {
        rectangles = new ArrayList<>();
        dxList = new ArrayList<>();
        dyList = new ArrayList<>();

        // Создаем экземпляры Rectangle
        for (int i = 0; i < NUM_RECTANGLES; i++) {
            int initialX = random.nextInt(getWidth() - RECT_SIZE);
            int initialY = random.nextInt(getHeight() - RECT_SIZE);
            rectangles.add(new Rectangle(initialX, initialY, initialX + RECT_SIZE, initialY + RECT_SIZE));
            dxList.add(random.nextInt(10) + 5);
            dyList.add(random.nextInt(10) + 5);

        }

        // Создаем экземпляры DrawableRect
        for (int i = 0; i < NUM_DRAWABLE_RECTANGLES; i++) {
            int initialX = random.nextInt(getWidth() - RECT_SIZE);
            int initialY = random.nextInt(getHeight() - RECT_SIZE);
            int randomColor1 = random.nextInt(256);
            int randomColor2 = random.nextInt(256);
            int randomColor3 = random.nextInt(256);
            Color outColor = new Color(randomColor1,randomColor2,randomColor3);
            rectangles.add(new DrawableRect(initialX, initialY, initialX + RECT_SIZE, initialY + RECT_SIZE, outColor));
            dxList.add(random.nextInt(10) + 5);
            dyList.add(random.nextInt(10) + 5);

        }

        // Создаем экземпляры ColoredRect
        for (int i = 0; i < NUM_COLORED_RECTANGLES; i++) {
            int initialX = random.nextInt(getWidth() - RECT_SIZE);
            int initialY = random.nextInt(getHeight() - RECT_SIZE);
            int randomColor1 = random.nextInt(256);
            int randomColor2 = random.nextInt(256);
            int randomColor3 = random.nextInt(256);
            int randomColor4 = random.nextInt(256);
            int randomColor5 = random.nextInt(256);
            int randomColor6 = random.nextInt(256);
            Color outColor = new Color(randomColor1,randomColor2,randomColor3);
            Color inColor = new Color(randomColor4, randomColor5, randomColor6);
            rectangles.add(new ColoredRect(initialX, initialY, initialX + RECT_SIZE, initialY + RECT_SIZE, outColor, inColor));
            dxList.add(random.nextInt(10) + 5);
            dyList.add(random.nextInt(10) + 5);
        }
    }


    @Override
    public void paint(Graphics g) {
        if (rectangles != null) {
            for (Rectangle rect : rectangles) {
                if (rect instanceof DrawableRect) {
                    ((DrawableRect) rect).draw(g);
                }
                else if (rect instanceof ColoredRect) {
                    ((ColoredRect) rect).draw(g);
                }
            }
        }
    }

    public void animate() {
        Rectangle bounds = new Rectangle();
        for (int i = 0; i < rectangles.size(); i++) {
            Rectangle rect = rectangles.get(i);
            int dx = dxList.get(i);
            int dy = dyList.get(i);
            if ((rect.x1 + dx < 0) || (rect.x2 + dx > bounds.x2 - bounds.x1)) {
                dx = -dx;
                dxList.set(i, dx);
            }
            if ((rect.y1 + dy < 0) || (rect.y2 + dy > bounds.y2 - bounds.y1)) {
                dy = -dy;
                dyList.set(i, dy);
            }
            rect.move(dx, dy);

        }
        repaint();
    }


    @Override
    public void run() {
        while (!pleaseStop) {
            animate();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void start() {
        animator = new Thread(this);
        pleaseStop = false;
        animator.start();
    }

    @Override
    public void stop() {
        pleaseStop = true;
    }
}