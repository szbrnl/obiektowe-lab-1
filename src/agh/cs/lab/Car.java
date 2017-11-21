package agh.cs.lab;

import java.util.*;

/**
 * Created by student40 on 2017-10-24.
 */
public class Car extends AbstractMapElement{

    private MapDirection mapDirection;

    //Lista obserwatorow
    private List<IPositionChangeObserver> positionChangeObservers = new ArrayList<IPositionChangeObserver>();

    //private Map _positionChangedObservers = new HashMap<Integer, IPositionChangeObserver>();

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

    private void positionChanged(Position old) {
        for(IPositionChangeObserver observer : positionChangeObservers) {
            observer.positionChanged(old, this.position);
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        positionChangeObservers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        positionChangeObservers.remove(observer);
    }

    public void move(MoveDirection moveDirection) {

        Position pos, oldPosition;

        oldPosition = this.position;

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
            positionChanged(oldPosition);
        }
    }

    public String toString() {
        return mapDirection.name().substring(0,1);
    }
}
