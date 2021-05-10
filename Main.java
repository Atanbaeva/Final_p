package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
public class Main extends Application {

    public static final int mo = 25, si = 25;
    public static int y_m = si * 12, x_m = si * 24;
    public static int[][] SET = new int[y_m / si][x_m / si];
    private static Pane GP = new Pane();
    private static Form object;
    private static Scene scene = new Scene(GP, y_m + 150, x_m);
    public static int sc = 0;
    private static int top = 0;
    private static boolean game = true;
    private static Form nextObj = Controller.makeRect();
    private static int Line = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        for (int[] a : SET) {
            Arrays.fill(a, 0);
        }

        Line line = new Line(y_m, 0, y_m, x_m);
        Text scoretext = new Text("Score: ");
        scoretext.setStyle("-fx-font: 20 arial;");
        scoretext.setY(50);
        scoretext.setX(y_m + 5);
        //Text level = new Text("Lines: ");
        //level.setStyle("-fx-font: 20 arial;");
        //level.setY(100);
        //level.setX(y_m + 5);
        //level.setFill(Color.GREEN);
        GP.getChildren().addAll(scoretext, line);

        Form a = nextObj;
        boolean b = GP.getChildren().addAll(a.a, a.b, a.d, a.c);
        moveOnKeyPress(a);
        object = a;
        nextObj = Controller.makeRect();
        stage.setScene(scene);
        stage.setTitle("Final Project");
        stage.show();

        Timer fall = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if (object.a.getY() == 0 || object.b.getY() == 0 || object.c.getY() == 0
                                || object.d.getY() == 0)
                            top++;
                        else
                            top = 0;

                        if (top == 2) {
                            Text over = new Text("GAME OVER");
                            over.setFill(Color.RED);
                            over.setStyle("-fx-font: 70 arial;");
                            over.setY(250);
                            over.setX(10);
                            GP.getChildren().add(over);
                            game = false;
                        }
                        if (top == 15) {
                            System.exit(0);
                        }

                        if (game) {
                            M_D(object);
                            scoretext.setText("Score: " + Integer.toString(sc));
                           // level.setText("Lines: " + Integer.toString(Line));
                        }
                    }
                });
            }
        };
        fall.schedule(task, 0, 300);
    }

    private void moveOnKeyPress(Form form) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case RIGHT:
                        Controller.MoveRight(form);
                        break;
                    case DOWN:
                        M_D(form);
                        sc++;
                        break;
                    case LEFT:
                        Controller.MoveLeft(form);
                        break;
                    case UP:
                        MoveTurn(form);
                        break;
                }
            }
        });
    }

    private void MoveTurn(Form form) {
        int f = form.form;
        Rectangle a = form.a;
        Rectangle b = form.b;
        Rectangle c = form.c;
        Rectangle d = form.d;
        switch (form.getName()) {
            case "j":
                if (f == 1 && cB(a, 1, -1) && cB(c, -1, -1) && cB(d, -2, -2)) {
                    M_R(form.a);
                    M_D(form.a);
                    M_D(form.c);
                    M_L(form.c);
                    M_D(form.d);
                    M_D(form.d);
                    M_L(form.d);
                    M_L(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, -2, 2)) {
                    M_D(form.a);
                    M_L(form.a);
                    M_L(form.c);
                    M_U(form.c);
                    M_L(form.d);
                    M_L(form.d);
                    M_U(form.d);
                    M_U(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, 1) && cB(c, 1, 1) && cB(d, 2, 2)) {
                    M_L(form.a);
                    M_U(form.a);
                    M_U(form.c);
                    M_R(form.c);
                    M_U(form.d);
                    M_U(form.d);
                    M_R(form.d);
                    M_R(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 2, -2)) {
                    M_U(form.a);
                    M_R(form.a);
                    M_R(form.c);
                    M_D(form.c);
                    M_R(form.d);
                    M_R(form.d);
                    M_D(form.d);
                    M_D(form.d);
                    form.changeForm();
                    break;
                }
                break;
            case "l":
                if (f == 1 && cB(a, 1, -1) && cB(c, 1, 1) && cB(b, 2, 2)) {
                    M_R(form.a);
                    M_D(form.a);
                    M_U(form.c);
                    M_R(form.c);
                    M_U(form.b);
                    M_U(form.b);
                    M_R(form.b);
                    M_R(form.b);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, -1, -1) && cB(b, 2, -2) && cB(c, 1, -1)) {
                    M_D(form.a);
                    M_L(form.a);
                    M_R(form.b);
                    M_R(form.b);
                    M_D(form.b);
                    M_D(form.b);
                    M_R(form.c);
                    M_D(form.c);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, 1) && cB(c, -1, -1) && cB(b, -2, -2)) {
                    M_L(form.a);
                    M_U(form.a);
                    M_D(form.c);
                    M_L(form.c);
                    M_D(form.b);
                    M_D(form.b);
                    M_L(form.b);
                    M_L(form.b);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(b, -2, 2) && cB(c, -1, 1)) {
                    M_U(form.a);
                    M_R(form.a);
                    M_L(form.b);
                    M_L(form.b);
                    M_U(form.b);
                    M_U(form.b);
                    M_L(form.c);
                    M_U(form.c);
                    form.changeForm();
                    break;
                }
                break;
            case "o":
                break;
            case "s":
                if (f == 1 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    M_D(form.a);
                    M_L(form.a);
                    M_L(form.c);
                    M_U(form.c);
                    M_U(form.d);
                    M_U(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    M_U(form.a);
                    M_R(form.a);
                    M_R(form.c);
                    M_D(form.c);
                    M_D(form.d);
                    M_D(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    M_D(form.a);
                    M_L(form.a);
                    M_L(form.c);
                    M_U(form.c);
                    M_U(form.d);
                    M_U(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    M_U(form.a);
                    M_R(form.a);
                    M_R(form.c);
                    M_D(form.c);
                    M_D(form.d);
                    M_D(form.d);
                    form.changeForm();
                    break;
                }
                break;
            case "t":
                if (f == 1 && cB(a, 1, 1) && cB(d, -1, -1) && cB(c, -1, 1)) {
                    M_U(form.a);
                    M_R(form.a);
                    M_D(form.d);
                    M_L(form.d);
                    M_L(form.c);
                    M_U(form.c);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, 1, -1) && cB(d, -1, 1) && cB(c, 1, 1)) {
                    M_R(form.a);
                    M_D(form.a);
                    M_L(form.d);
                    M_U(form.d);
                    M_U(form.c);
                    M_R(form.c);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, -1) && cB(d, 1, 1) && cB(c, 1, -1)) {
                    M_D(form.a);
                    M_L(form.a);
                    M_U(form.d);
                    M_R(form.d);
                    M_R(form.c);
                    M_D(form.c);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, -1, 1) && cB(d, 1, -1) && cB(c, -1, -1)) {
                    M_L(form.a);
                    M_U(form.a);
                    M_R(form.d);
                    M_D(form.d);
                    M_D(form.c);
                    M_L(form.c);
                    form.changeForm();
                    break;
                }
                break;
            case "z":
                if (f == 1 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    M_U(form.b);
                    M_R(form.b);
                    M_L(form.c);
                    M_U(form.c);
                    M_L(form.d);
                    M_L(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    M_D(form.b);
                    M_L(form.b);
                    M_R(form.c);
                    M_D(form.c);
                    M_R(form.d);
                    M_R(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    M_U(form.b);
                    M_R(form.b);
                    M_L(form.c);
                    M_U(form.c);
                    M_L(form.d);
                    M_L(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    M_D(form.b);
                    M_L(form.b);
                    M_R(form.c);
                    M_D(form.c);
                    M_R(form.d);
                    M_R(form.d);
                    form.changeForm();
                    break;
                }
                break;
            case "i":
                if (f == 1 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    M_U(form.a);
                    M_U(form.a);
                    M_R(form.a);
                    M_R(form.a);
                    M_U(form.b);
                    M_R(form.b);
                    M_D(form.d);
                    M_L(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    M_D(form.a);
                    M_D(form.a);
                    M_L(form.a);
                    M_L(form.a);
                    M_D(form.b);
                    M_L(form.b);
                    M_U(form.d);
                    M_R(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    M_U(form.a);
                    M_U(form.a);
                    M_R(form.a);
                    M_R(form.a);
                    M_U(form.b);
                    M_R(form.b);
                    M_D(form.d);
                    M_L(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    M_D(form.a);
                    M_D(form.a);
                    M_L(form.a);
                    M_L(form.a);
                    M_D(form.b);
                    M_L(form.b);
                    M_U(form.d);
                    M_R(form.d);
                    form.changeForm();
                    break;
                }
                break;
        }
    }

    private void RemoveRows(Pane pane) {
        ArrayList<Node> rects = new ArrayList<Node>();
        ArrayList<Integer> lines = new ArrayList<Integer>();
        ArrayList<Node> newrects = new ArrayList<Node>();
        int full = 0;
        for (int i = 0; i < SET[0].length; i++) {
            for (int j = 0; j < SET.length; j++) {
                if (SET[j][i] == 1)
                    full++;
            }
            if (full == SET.length)
                lines.add(i);
            //lines.add(i + lines.size());
            full = 0;
        }
        if (lines.size() > 0)
            do {
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                sc += 50;
                Line++;

                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() == lines.get(0) * si) {
                        SET[(int) a.getX() / si][(int) a.getY() / si] = 0;
                        pane.getChildren().remove(node);
                    } else
                        newrects.add(node);
                }

                for (Node node : newrects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() < lines.get(0) * si) {
                        SET[(int) a.getX() / si][(int) a.getY() / si] = 0;
                        a.setY(a.getY() + si);
                    }
                }
                lines.remove(0);
                rects.clear();
                newrects.clear();
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    try {
                        SET[(int) a.getX() / si][(int) a.getY() / si] = 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                rects.clear();
            } while (lines.size() > 0);
    }

    private void M_D(Rectangle rect) {
        if (rect.getY() + mo < x_m)
            rect.setY(rect.getY() + mo);

    }

    private void M_R(Rectangle rect) {
        if (rect.getX() + mo <= y_m - si)
            rect.setX(rect.getX() + mo);
    }

    private void M_L(Rectangle rect) {
        if (rect.getX() - mo >= 0)
            rect.setX(rect.getX() - mo);
    }

    private void M_U(Rectangle rect) {
        if (rect.getY() - mo > 0)
            rect.setY(rect.getY() - mo);
    }

    private void M_D(Form form) {
        if (form.a.getY() == x_m - si || form.b.getY() == x_m - si || form.c.getY() == x_m - si
                || form.d.getY() == x_m - si || moveA(form) || moveB(form) || moveC(form) || moveD(form)) {
            SET[(int) form.a.getX() / si][(int) form.a.getY() / si] = 1;
            SET[(int) form.b.getX() / si][(int) form.b.getY() / si] = 1;
            SET[(int) form.c.getX() / si][(int) form.c.getY() / si] = 1;
            SET[(int) form.d.getX() / si][(int) form.d.getY() / si] = 1;
            RemoveRows(GP);

            Form a = nextObj;
            nextObj = Controller.makeRect();
            object = a;
            GP.getChildren().addAll(a.a, a.b, a.c, a.d);
            moveOnKeyPress(a);
        }

        if (form.a.getY() + mo < x_m && form.b.getY() + mo < x_m && form.c.getY() + mo < x_m
                && form.d.getY() + mo < x_m) {
            int movea = SET[(int) form.a.getX() / si][((int) form.a.getY() / si) + 1];
            int moveb = SET[(int) form.b.getX() / si][((int) form.b.getY() / si) + 1];
            int movec = SET[(int) form.c.getX() / si][((int) form.c.getY() / si) + 1];
            int moved = SET[(int) form.d.getX() / si][((int) form.d.getY() / si) + 1];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setY(form.a.getY() + mo);
                form.b.setY(form.b.getY() + mo);
                form.c.setY(form.c.getY() + mo);
                form.d.setY(form.d.getY() + mo);
            }
        }
    }

    private boolean moveA(Form form) {
        return (SET[(int) form.a.getX() / si][((int) form.a.getY() / si) + 1] == 1);
    }

    private boolean moveB(Form form) {
        return (SET[(int) form.b.getX() / si][((int) form.b.getY() / si) + 1] == 1);
    }

    private boolean moveC(Form form) {
        return (SET[(int) form.c.getX() / si][((int) form.c.getY() / si) + 1] == 1);
    }

    private boolean moveD(Form form) {
        return (SET[(int) form.d.getX() / si][((int) form.d.getY() / si) + 1] == 1);
    }

    private boolean cB(Rectangle rect, int x, int y) {
        boolean xb = false,yb = false ;
        if (x >= 0)
            xb = rect.getX() + x * mo <= y_m - si;
        if (x < 0)
            xb = rect.getX() + x * mo >= 0;
        if (y >= 0)
            yb = rect.getY() - y * mo > 0;
        if (y < 0)
            yb = rect.getY() + y * mo < x_m;
        return xb && yb && SET[((int) rect.getX() / si) + x][((int) rect.getY() / si) - y] == 0;
    }

}