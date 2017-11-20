package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student11 on 2017-11-07.
 */
public class UnboundedMap implements IWorldMap {

    private List<HayStack> hayStacks = new ArrayList<HayStack>();
    private List<Car> cars = new ArrayList<Car>();

    public UnboundedMap(List<HayStack> hayStacks) {
        this.hayStacks = hayStacks;
    }

    @Override
    public boolean canMoveTo(Position position) {
        for(Car car : cars) {
            if(position.equals(car.getPosition())) return false;
        }

        for(HayStack hayStack : hayStacks) {
            if(position.equals(hayStack.getPosition())) return false;
        }

        return true;
    }

    @Override
    public boolean place(Car car) {
        if(isOccupied(car.getPosition()))
            throw new IllegalArgumentException(car.getPosition().toString() + "is occupied");

        cars.add(car);
        return true;
    }

    @Override
    public void run(MoveDirection[] directions) {
        if(cars.size() == 0) return;

        int car = 0;

        for(MoveDirection dir : directions) {
            Car currentCar = cars.get(car % cars.size());
            currentCar.move(dir);
//            System.out.println(toString());
            car++;
        }
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
    public Object objectAt(Position position) {
        for(Car car : cars) {
            if(position.equals(car.getPosition())) return car;
        }
        for(HayStack hayStack : hayStacks) {
            if(position.equals(hayStack.getPosition())) return hayStack;
        }
        return null;
    }

    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer();
        Position topRight = cars.get(0).getPosition();
        Position bottomLeft = cars.get(0).getPosition();

        for(Car car : cars) {
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
