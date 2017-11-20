package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student11 on 2017-11-07.
 */
public abstract class AbstractWorldMap implements IWorldMap {

    protected List<HayStack> hayStacks = new ArrayList<HayStack>();
    protected List<Car> cars = new ArrayList<Car>();

    @Override
    public Object objectAt(Position position) {
        for(Car car : cars) {
            if(position.equals(car.getPosition())) return car;
        }
        for(HayStack hayStack : hayStacks) {
            if(position.equals(hayStack.getPosition())) return hayStack;
        }
        return null;
    }

    @Override
    public boolean isOccupied(Position position) {
        for(Car car : cars) {
            if(position.equals(car.getPosition())) return true;
        }
        for(HayStack hayStack : hayStacks) {
            if(position.equals(hayStack.getPosition())) return true;
        }

        return false;
    }

    @Override
    public void run(MoveDirection[] directions) {
        if(cars.size() == 0) return;

        int car = 0;

        for(MoveDirection dir : directions) {
            Car currentCar = cars.get(car % cars.size());
            currentCar.move(dir);
            car++;
        }
    }



}
