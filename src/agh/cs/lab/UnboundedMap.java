package agh.cs.lab;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Student11 on 2017-11-07.
 */
public class UnboundedMap extends AbstractWorldMap {


    public UnboundedMap(List<HayStack> hayStacks) {
       for(HayStack hayStack : hayStacks) {
           mElements.put(hayStack.getPosition(), hayStack);
       }

    }

    @Override
    public boolean canMoveTo(Position position) {

        if(mElements.get(position) != null) return false;
        return true;
    }

    @Override
    public boolean place(Car car) {
        if(isOccupied(car.getPosition()))
            throw new IllegalArgumentException(car.getPosition().toString() + "is occupied");

        mElements.put(car.getPosition(), car);
        cars.add(car);

        car.addObserver(this);

        return true;
    }

    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer();

        Collection elements = mElements.values();
        Object[] elementsArray = elements.toArray();

        Position topRight = ((AbstractMapElement)elementsArray[0]).getPosition();
        Position bottomLeft = ((AbstractMapElement)elementsArray[0]).getPosition();

        for(Object oElem : elementsArray) {
            AbstractMapElement elem = (AbstractMapElement)oElem;
            if(elem.getPosition().x > topRight.x) topRight = new Position(elem.getPosition().x, topRight.y);
            if(elem.getPosition().y > topRight.y) topRight = new Position(topRight.x, elem.getPosition().y);

            if(elem.getPosition().x < bottomLeft.x) bottomLeft = new Position(elem.getPosition().x, bottomLeft.y);
            if(elem.getPosition().y < bottomLeft.y) bottomLeft = new Position(bottomLeft.x, elem.getPosition().y);
        }

        return mapVisualizer.dump(this, bottomLeft, topRight);
    }

}
