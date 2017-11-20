package agh.cs.lab;

import javafx.geometry.Pos;

import java.util.*;

/**
 * Created by Student11 on 2017-11-07.
 */
public abstract class AbstractWorldMap implements IWorldMap {

    protected List<HayStack> hayStacks = new ArrayList<HayStack>();
    protected List<Car> cars = new ArrayList<Car>();


    protected Map mCars = new LinkedHashMap<Position, Car>();



    @Override
    public Object objectAt(Position position) {

        if(mCars.containsKey(position)) {
            return mCars.get(position);
        }

        for(HayStack hayStack : hayStacks) {
            if(position.equals(hayStack.getPosition())) return hayStack;
        }
        return null;
    }

    @Override
    public boolean isOccupied(Position position) {

        if(mCars.containsKey(position))
            return true;

        for(HayStack hayStack : hayStacks) {
            if(position.equals(hayStack.getPosition())) return true;
        }

        return false;
    }

    @Override
    public void run(MoveDirection[] directions) {
        if(mCars.isEmpty()) return;

        int currentCarNumber = 0;
        Collection cars = mCars.values();
        Object[] carList = cars.toArray();

        for(MoveDirection dir : directions) {

            Car currentCar = (Car)carList[currentCarNumber % carList.length];
            Position previousPosition = currentCar.getPosition();

            currentCar.move(dir);

            if(currentCar.getPosition() != previousPosition) {
                mCars.remove(previousPosition);
                mCars.put(currentCar.getPosition(), currentCar);
            }


            currentCarNumber++;
        }
    }



}
