import java.awt.*;

class DrawableRect extends Rectangle {
    Color outColor;

    public DrawableRect(int x1, int y1, int x2, int y2, Color outColor) {
        super(x1, y1, x2, y2);
        this.outColor = outColor;
    }

    public void draw(Graphics g) {
        g.setColor(outColor);
        g.drawRect(x1, y1, x2 - x1, y2 - y1);
    }
}