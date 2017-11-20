package agh.cs.lab;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Student11 on 2017-11-07.
 */
public class UnboundedMap extends AbstractWorldMap {


    public UnboundedMap(List<HayStack> hayStacks) {
        this.hayStacks = hayStacks;
    }

    @Override
    public boolean canMoveTo(Position position) {

        if(mCars.get(position) != null) return false;


        for(HayStack hayStack : hayStacks) {
            if(position.equals(hayStack.getPosition())) return false;
        }

        return true;
    }

    @Override
    public boolean place(Car car) {
        if(isOccupied(car.getPosition()))
            throw new IllegalArgumentException(car.getPosition().toString() + "is occupied");

        mCars.put(car.getPosition(), car);
        return true;
    }

    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer();

        Collection cars = mCars.values();
        Object[] carList = cars.toArray();

        Position topRight = ((Car)(carList[0])).getPosition();
        Position bottomLeft = ((Car)carList[0]).getPosition();

        for(Object oCar : carList) {
            Car car = (Car) oCar;
            if(car.getPosition().x > topRight.x) topRight = new Position(car.getPosition().x, topRight.y);
            if(car.getPosition().y > topRight.y) topRight = new Position(topRight.x, car.getPosition().y);

            if(car.getPosition().x < bottomLeft.x) bottomLeft = new Position(car.getPosition().x, bottomLeft.y);
            if(car.getPosition().y < bottomLeft.y) bottomLeft = new Position(bottomLeft.x, car.getPosition().y);
        }

        for(HayStack hayStack : hayStacks) {
            if(hayStack.getPosition().x > topRight.x) topRight = new Position(hayStack.getPosition().x, topRight.y);
            if(hayStack.getPosition().y > topRight.y) topRight = new Position(topRight.x, hayStack.getPosition().y);

            if(hayStack.getPosition().x < bottomLeft.x) bottomLeft = new Position(hayStack.getPosition().x, bottomLeft.y);
            if(hayStack.getPosition().y < bottomLeft.y) bottomLeft = new Position(bottomLeft.x, hayStack.getPosition().y);
        }

        return mapVisualizer.dump(this, bottomLeft, topRight);
    }

}
