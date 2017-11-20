package agh.cs.lab;

/**
 * Created by student40 on 2017-10-24.
 */
public class Car {

    private MapDirection mapDirection;
    private Position position;

    private IWorldMap map;

    public Car(IWorldMap map) {
        this.map = map;

        mapDirection = MapDirection.North;
        position = new Position(2, 2);
    }

    public Car(IWorldMap map, int x, int y) {
        position = new Position(x,y);
        mapDirection = mapDirection.North;

        this.map = map;
    }

    public Position getPosition() {
        return this.position;
    }


    public void move(MoveDirection moveDirection) {

        Position pos;

        if (moveDirection == MoveDirection.Right)
            mapDirection = mapDirection.next();

        else if (moveDirection == MoveDirection.Left)
            mapDirection = mapDirection.previous();

        else {

            switch (mapDirection) {
                case North:
                    pos = new Position(0, 1);
                    break;

                case South:
                    pos = new Position(0, -1);
                    break;

                case West:
                    pos = new Position(-1, 0);
                    break;

                case East:
                    pos = new Position(1, 0);
                    break;

                default:
                    pos = new Position(0, 0);

            }

            if (moveDirection == MoveDirection.Forward)
                pos = position.add(pos);
            else if (moveDirection == MoveDirection.Backward)
                pos = position.subtract(pos);


            if(!map.canMoveTo(pos)) return;

            position = pos;
        }
    }

//    private boolean isValidPosition(Position pos) {
//        if (pos.smaller(new Position(4, 4)) && pos.larger(new Position(0, 0))) {
//            return true;
//        }
//
//        return false;
//    }


    public String toString() {
        return mapDirection.name().substring(0,1);
    }
}
