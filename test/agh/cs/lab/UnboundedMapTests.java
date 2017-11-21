package agh.cs.lab;

import com.sun.nio.sctp.IllegalReceiveException;

import org.junit.Test;
import org.testng.Assert;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Student11 on 2017-11-07.
 */
public class UnboundedMapTests {

    @Test
    public void general_test() {

    }

    @Test
    public void canMoveTo_free_position() {
        List<HayStack> hayStacks = new ArrayList<HayStack>();

        hayStacks.add(new HayStack(new Position(-4, -4)));
        hayStacks.add(new HayStack(new Position(7, 7)));
        hayStacks.add(new HayStack(new Position(3, 6)));
        hayStacks.add(new HayStack(new Position(2, 0)));

        IWorldMap map = new UnboundedMap(hayStacks);

        Car car1 = new Car(map, 2,3);
        Car car2 = new Car(map, 3,3);

        map.place(car1);
        map.place(car2);

        String[] arg = {"b", "f", "b", "f", "f","f","b", "f", "b", "f"};

        map.run(OptionsParser.parse(arg));

        assertEquals(new Position(3,5), car2.getPosition());
        assertEquals(new Position(2,1), car1.getPosition());
    }

    @Test
    public void canMoveTo_position_occupied_by_HayStack() {

    }

    @Test
    public void canMoveTo_position_occupied_by_another_car() {
        List<HayStack> hayStacks = new ArrayList<HayStack>();

        hayStacks.add(new HayStack(new Position(-4, -4)));
        hayStacks.add(new HayStack(new Position(7, 7)));
        hayStacks.add(new HayStack(new Position(3, 6)));
        hayStacks.add(new HayStack(new Position(2, 0)));

        IWorldMap map = new UnboundedMap(hayStacks);

        Car car1 = new Car(map, 2,3);
        Car car2 = new Car(map, 3,3);

        map.place(car1);
        map.place(car2);

        String[] arg = {"l", "l", "r", "f"};

        map.run(OptionsParser.parse(arg));

        assertEquals(new Position(2,3), car1.getPosition());
        assertEquals(new Position(3,3), car2.getPosition());


    }

    @Test
    public void place_on_free_position(){
        List<HayStack> hayStacks = new ArrayList<HayStack>();

        hayStacks.add(new HayStack(new Position(-4, -4)));
        hayStacks.add(new HayStack(new Position(7, 7)));
        hayStacks.add(new HayStack(new Position(3, 6)));
        hayStacks.add(new HayStack(new Position(2, 0)));

        IWorldMap map = new UnboundedMap(hayStacks);

        map.place(new Car(map, 2,3));

        try {
            map.place(new Car(map, 2, 4));
        }
        catch (Exception ex) {
            Assert.assertEquals(ex instanceof IllegalArgumentException, true);
        }
    }

    @Test
    public void place_on_occupied_position(){
        List<HayStack> hayStacks = new ArrayList<HayStack>();

        hayStacks.add(new HayStack(new Position(-4, -4)));
        hayStacks.add(new HayStack(new Position(7, 7)));
        hayStacks.add(new HayStack(new Position(3, 6)));
        hayStacks.add(new HayStack(new Position(2, 0)));

        IWorldMap map = new UnboundedMap(hayStacks);

        map.place(new Car(map, 2,3));

        try {
            map.place(new Car(map, 2, 3));
        }
        catch (Exception ex) {
            Assert.assertEquals(ex instanceof IllegalArgumentException, true);
        }

    }

    @Test
    public void isOccupied_by_HayStack() {

    }

    @Test
    public void isOccupied_by_another_car() {

    }

    @Test
    public void isOccupied_on_free_position() {

    }

    @Test
    public void objectAt_free_position() {

    }

    @Test
    public void objectAt_occupied_position() {
        List<HayStack> hayStacks = new ArrayList<HayStack>();

        hayStacks.add(new HayStack(new Position(-4, -4)));
        hayStacks.add(new HayStack(new Position(7, 7)));
        hayStacks.add(new HayStack(new Position(3, 6)));
        hayStacks.add(new HayStack(new Position(2, 0)));

        IWorldMap map = new UnboundedMap(hayStacks);

        map.place(new Car(map, 2,3));

        assertEquals(null, map.objectAt(new Position(0,0)));
        assertNotNull(map.objectAt(new Position(2,3)));

    }


}
