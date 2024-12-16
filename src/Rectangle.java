public class Rectangle {
    int x1, y1, x2, y2;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Rectangle(int width, int height) {
        this(0, 0, width, height);
    }

    public Rectangle() {
        this(0, 0, 0, 0);
    }

    public void rect_print() {
        System.out.println("(" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ")");
    }

    public void move(int dx, int dy) {
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
    }

    public Rectangle Union(Rectangle other) {
        int minX = Math.min(this.x1, other.x1);
        int minY = Math.min(this.y1, other.y1);
        int maxX = Math.max(this.x2, other.x2);
        int maxY = Math.max(this.y2, other.y2);
        return new Rectangle(minX, minY, maxX, maxY);
    }
}
