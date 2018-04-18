package lesson3.task1;

/**
 * На игровом поле находится робот. Позиция робота на поле описывается двумя целочисленным координатами: X и Y. Ось X смотрит слева направо, ось Y — снизу вверх. (Помните, как рисовали графики функций в школе?)
 * В начальный момент робот находится в некоторой позиции на поле. Также известно, куда робот смотрит: вверх, вниз, направо или налево. Ваша задача — привести робота в заданную точку игрового поля.
 * Робот описывается классом Robot. Вы можете пользоваться следующими его методами (реализация вам неизвестна):
 * <code>
 * public Direction getDirection() {// текущее направление взгляда}
 * public int getX() {// текущая координата X}
 * public int getY() {// текущая координата Y}
 * public void turnLeft() {// повернуться на 90 градусов против часовой стрелки}
 * public void turnRight() {// повернуться на 90 градусов по часовой стрелке}
 * public void stepForward() {// шаг в направлении взгляда // за один шаг робот изменяет одну свою координату на единицу}
 * </code>
 * Direction, направление взгляда робота,  — это перечисление:
 * <code>
 * public enum Direction {
 * UP,
 * DOWN,
 * LEFT,
 * RIGHT
 * }
 * </code>
 *
 * @author Starovoytov
 * @since 05.04.2018
 */
public class Robot {
    public static final String TURN_LEFT = "turnLeft" + System.lineSeparator();
    public static final String TURN_RIGHT = "turnRight" + System.lineSeparator();
    public static final String STEP_FORWARD = "stepForvard" + System.lineSeparator();

    private int x = 0;
    private int y = 0;
    private Direction currentDirection = Direction.UP;

    private StringBuffer actions = new StringBuffer();

    public static String moveRobot(Robot robot, int toX, int toY) {
        if (toY > robot.getY()) {
            goToY(robot, toY);
            goToX(robot, toX);
        } else {
            goToX(robot, toX);
            goToY(robot, toY);
        }

        return robot.getActions();
    }

    private static void goToY(Robot robot, int toY) {
        if (toY != robot.getY()) {
            if (toY > robot.getY()) {
                switch (robot.getDirection()) {
                    case RIGHT: {
                        robot.turnLeft();
                        break;
                    }
                    case LEFT: {
                        robot.turnRight();
                        break;
                    }
                    case DOWN:
                        robot.turnRight();
                        robot.turnRight();
                }
            } else {
                switch (robot.getDirection()) {
                    case LEFT: {
                        robot.turnLeft();
                        break;
                    }
                    case RIGHT: {
                        robot.turnRight();
                        break;
                    }
                    case UP:
                        robot.turnRight();
                        robot.turnRight();
                }
            }

            while (toY != robot.getY())
                robot.stepForward();
        }
    }

    private static void goToX(Robot robot, int toX) {
        if (toX != robot.getX()) {
            if (toX > robot.getX()) {
                switch (robot.getDirection()) {
                    case DOWN: {
                        robot.turnLeft();
                        break;
                    }
                    case UP: {
                        robot.turnRight();
                        break;
                    }
                    case LEFT:
                        robot.turnRight();
                        robot.turnRight();
                }
            } else {
                switch (robot.getDirection()) {
                    case DOWN: {
                        robot.turnRight();
                        break;
                    }
                    case UP: {
                        robot.turnLeft();
                        break;
                    }
                    case RIGHT:
                        robot.turnRight();
                        robot.turnRight();
                }
            }

            while (toX != robot.getX())
                robot.stepForward();
        }
    }

    public String getActions() {
        return actions.toString();
    }

    public Direction getDirection() {
        return currentDirection;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void turnLeft() {
        actions.append(TURN_LEFT);

        switch (currentDirection) {
            case UP: {
                currentDirection = Direction.LEFT;
                break;
            }
            case LEFT: {
                currentDirection = Direction.DOWN;
                break;
            }
            case DOWN: {
                currentDirection = Direction.RIGHT;
                break;
            }
            case RIGHT: {
                currentDirection = Direction.UP;
            }
        }
    }

    public void turnRight() {
        actions.append(TURN_RIGHT);

        switch (currentDirection) {
            case UP: {
                currentDirection = Direction.RIGHT;
                break;
            }
            case LEFT: {
                currentDirection = Direction.UP;
                break;
            }
            case DOWN: {
                currentDirection = Direction.LEFT;
                break;
            }
            case RIGHT: {
                currentDirection = Direction.DOWN;
            }
        }
    }

    public void stepForward() {
        actions.append(STEP_FORWARD);

        switch (currentDirection) {
            case UP: {
                y++;
                break;
            }
            case LEFT: {
                x--;
                break;
            }
            case DOWN: {
                y--;
                break;
            }
            case RIGHT: {
                x++;
            }
        }
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}