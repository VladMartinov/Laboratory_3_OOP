public class Test {
    public static void main(String[] args) {
        Rectangle rect1 = new Rectangle(1, 2, 3, 4);
        Rectangle rect2 = new Rectangle(5, 5);
        Rectangle rect3 = new Rectangle();

        System.out.print("Rect1: ");
        rect1.rect_print();
        System.out.print("Rect2: ");
        rect2.rect_print();
        System.out.print("Rect3: ");
        rect3.rect_print();

        rect1.move(2, 3);
        rect2.move(-1, 1);
        rect3.move(10, 10);

        System.out.print("Rect1 after move: ");
        rect1.rect_print();
        System.out.print("Rect2 after move: ");
        rect2.rect_print();
        System.out.print("Rect3 after move: ");
        rect3.rect_print();

        Rectangle unionRect = rect1.Union(rect2);
        System.out.print("Union of Rect1 and Rect2: ");
        unionRect.rect_print();
    }
}