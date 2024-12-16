import java.awt.*;

class ColoredRect extends DrawableRect {
    Color inColor;

    public ColoredRect(int x1, int y1, int x2, int y2, Color outColor, Color inColor) {
        super(x1, y1, x2, y2, outColor);
        this.inColor = inColor;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(inColor);
        g.fillRect(x1, y1, x2 - x1, y2 - y1);
        g.setColor(outColor);
        g.drawRect(x1, y1, x2 - x1, y2 - y1);
    }
}