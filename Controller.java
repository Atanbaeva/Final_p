package sample;

import javafx.scene.shape.Rectangle;

public class Controller {

    public static final int MOT = Main.mo, SIZE = Main.si;
    public static int YMAX = Main.x_m,
            X_mx = Main.y_m;
    public static int[][] SET = Main.SET;

    public static void MoveRight(Form form) {
        if (form.a.getX() + MOT <= X_mx - SIZE && form.b.getX() + MOT <= X_mx - SIZE
                && form.c.getX() + MOT <= X_mx - SIZE && form.d.getX() + MOT <= X_mx - SIZE) {
            int movea = SET[((int) form.a.getX() / SIZE) + 1][((int) form.a.getY() / SIZE)];
            int moveb = SET[((int) form.b.getX() / SIZE) + 1][((int) form.b.getY() / SIZE)];
            int movec = SET[((int) form.c.getX() / SIZE) + 1][((int) form.c.getY() / SIZE)];
            int moved = SET[((int) form.d.getX() / SIZE) + 1][((int) form.d.getY() / SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setX(form.a.getX() + MOT);
                form.b.setX(form.b.getX() + MOT);
                form.c.setX(form.c.getX() + MOT);
                form.d.setX(form.d.getX() + MOT);
            }
        }
    }

    public static void MoveLeft(Form form) {
        if (form.a.getX() - MOT >= 0 && form.b.getX() - MOT >= 0 && form.c.getX() - MOT >= 0
                && form.d.getX() - MOT >= 0) {
            int movea = SET[((int) form.a.getX() / SIZE) - 1][((int) form.a.getY() / SIZE)];
            int moveb = SET[((int) form.b.getX() / SIZE) - 1][((int) form.b.getY() / SIZE)];
            int movec = SET[((int) form.c.getX() / SIZE) - 1][((int) form.c.getY() / SIZE)];
            int moved = SET[((int) form.d.getX() / SIZE) - 1][((int) form.d.getY() / SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setX(form.a.getX() - MOT);
                form.b.setX(form.b.getX() - MOT);
                form.c.setX(form.c.getX() - MOT);
                form.d.setX(form.d.getX() - MOT);
            }
        }
    }

    public static Form makeRect() {
        int block = (int) (Math.random() * 100);
        String name;
        Rectangle a = new Rectangle(SIZE-1, SIZE-1), b = new Rectangle(SIZE-1, SIZE-1), c = new Rectangle(SIZE-1, SIZE-1),
                d = new Rectangle(SIZE-1, SIZE-1);
        if (block < 15) {
            a.setX(X_mx / 2 - SIZE);
            b.setX(X_mx / 2 - SIZE);
            b.setY(SIZE);
            c.setX(X_mx / 2);
            c.setY(SIZE);
            d.setX(X_mx / 2 + SIZE);
            d.setY(SIZE);
            name = "j";
        } else if (block < 30) {
            a.setX(X_mx / 2 + SIZE);
            b.setX(X_mx / 2 - SIZE);
            b.setY(SIZE);
            c.setX(X_mx / 2);
            c.setY(SIZE);
            d.setX(X_mx / 2 + SIZE);
            d.setY(SIZE);
            name = "l";
        } else if (block < 45) {
            a.setX(X_mx / 2 - SIZE);
            b.setX(X_mx / 2);
            c.setX(X_mx / 2 - SIZE);
            c.setY(SIZE);
            d.setX(X_mx / 2);
            d.setY(SIZE);
            name = "o";
        } else if (block < 60) {
            a.setX(X_mx / 2 + SIZE);
            b.setX(X_mx / 2);
            c.setX(X_mx / 2);
            c.setY(SIZE);
            d.setX(X_mx / 2 - SIZE);
            d.setY(SIZE);
            name = "s";
        } else if (block < 75) {
            a.setX(X_mx / 2 - SIZE);
            b.setX(X_mx / 2);
            c.setX(X_mx / 2);
            c.setY(SIZE);
            d.setX(X_mx / 2 + SIZE);
            name = "t";
        } else if (block < 90) {
            a.setX(X_mx / 2 + SIZE);
            b.setX(X_mx / 2);
            c.setX(X_mx / 2 + SIZE);
            c.setY(SIZE);
            d.setX(X_mx / 2 + SIZE + SIZE);
            d.setY(SIZE);
            name = "z";
        } else {
            a.setX(X_mx / 2 - SIZE - SIZE);
            b.setX(X_mx / 2 - SIZE);
            c.setX(X_mx / 2);
            d.setX(X_mx / 2 + SIZE);
            name = "i";
        }
        return new Form(a, b, c, d, name);
    }

}
