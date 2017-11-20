package agh.cs.lab;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by student31 on 2017-10-31.
 */
public class RectangularMap implements IWorldMap {

    private int width;
    private int height;

    private List<Car> cars = new ArrayList<Car>();


    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Position position) {

        if(!(position.smaller(new Position(width,height)) && position.larger(new Position(0,0))))
            return false;

        for(Car car : cars) {
            if(position.equals(car.getPosition())) return false;
        }

        return true;
    }

    @Override
    public boolean place(Car car) {
        if(isOccupied(car.getPosition())) return false;
        if(!canMoveTo(car.getPosition())) return false;
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
            //System.out.println(toString());
            car++;
        }
    }

    @Override
    public boolean isOccupied(Position position) {
        for(Car car : cars) {
            if(position.equals(car.getPosition())) return true;
        }

        return false;
    }

    @Override
    public Object objectAt(Position position) {
        for(Car car : cars) {
            if(position.equals(car.getPosition())) return car;
        }

        return null;
    }

    public String toString() {
        MapVisualizer mV = new MapVisualizer();
        return mV.dump(this, new Position(0,0), new Position(width,height));
    }

}
