package agh.cs.lab;

import javafx.geometry.Pos;

import java.lang.annotation.ElementType;
import java.util.*;

/**
 * Created by Student11 on 2017-11-07.
 */
public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {



    protected List<Car> cars = new ArrayList<Car>();

    protected Map mElements = new LinkedHashMap<Position, AbstractMapElement>();


    @Override
    public void positionChanged(Position old, Position pNew) {
        Car car = (Car) mElements.get(old);

        mElements.remove(old);
        mElements.put(pNew, car);
    }

    @Override
    public Object objectAt(Position position) {

        if(mElements.containsKey(position)) {
            return mElements.get(position);
        }

        return null;
    }

    @Override
    public boolean isOccupied(Position position) {

        if(mElements.containsKey(position))
            return true;

        return false;
    }

    @Override
    public void run(MoveDirection[] directions) {
        if(mElements.isEmpty()) return;

        int currentCarNumber = 0;

        for(MoveDirection dir : directions) {

            Car currentCar = (Car)cars.get(currentCarNumber  % cars.size());
            Position previousPosition = currentCar.getPosition();

            currentCar.move(dir);

            currentCarNumber++;
        }
    }

}
