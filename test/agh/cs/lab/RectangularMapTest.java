package agh.cs.lab;

import agh.cs.lab.Car;
import agh.cs.lab.Position;
import agh.cs.lab.RectangularMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Student11 on 2017-11-07.
 */
public class RectangularMapTest {
    @Test
    public void canMoveTo_position_occupied_by_another_car() {
        RectangularMap rectangularMap = new RectangularMap(10,10);
        rectangularMap.place(new Car(rectangularMap, 4,3));
        Car car = new Car(rectangularMap, 4,2);

        rectangularMap.place(car);

        assertFalse(rectangularMap.canMoveTo(new Position(4, 2)));
        assertFalse(rectangularMap.canMoveTo(new Position(4,3)));

    }

    @Test
    public void place_on_occupied_position() {
        RectangularMap rectangularMap = new RectangularMap(10,10);
        rectangularMap.place(new Car(rectangularMap, 4,3));

        assertFalse(rectangularMap.place(new Car(rectangularMap, 4, 3)));
        assertFalse(rectangularMap.place(new Car(rectangularMap,-20, -20)));
    }

    @Test
    public void place_on_free_position() {
        RectangularMap rectangularMap = new RectangularMap(10,10);
        rectangularMap.place(new Car(rectangularMap, 4,3));

        assertTrue(rectangularMap.place(new Car(rectangularMap, 4,2)));
        assertTrue(rectangularMap.place(new Car(rectangularMap, 4,1)));
    }

    @Test
    public void objectAt_free_position() {
        RectangularMap rectangularMap = new RectangularMap(10,10);
        rectangularMap.place(new Car(rectangularMap, 4,3));

        assertEquals(null, rectangularMap.objectAt(new Position(4,1)));
    }

    @Test
    public void objectAt_occupied_position() {
        RectangularMap rectangularMap = new RectangularMap(10,10);
        rectangularMap.place(new Car(rectangularMap, 4,3));

        assertNotNull(rectangularMap.objectAt(new Position(4,3)));

    }

    @Test
    public void run() {

    }

}
