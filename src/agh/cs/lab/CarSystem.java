package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student31 on 2017-10-10.
 */
public class    CarSystem {
    public static void main(String args[]) {

        String[] s = {"f", "b", "r", "l" ,"f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};

        MoveDirection[] directions = new OptionsParser().parse(s);

        List<HayStack> hayStacks = new ArrayList<HayStack>();

        hayStacks.add(new HayStack(new Position(-4,-4)));
        hayStacks.add(new HayStack(new Position(7,7)));
        hayStacks.add(new HayStack(new Position(3,6)));
        hayStacks.add(new HayStack(new Position(2,0)));

        IWorldMap map = new UnboundedMap(hayStacks);
        map.place(new Car(map));
        map.place(new Car(map,3,4));
        map.run(directions);

        System.out.println(map.toString());

    }
}
