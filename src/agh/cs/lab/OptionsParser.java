package agh.cs.lab;

public class OptionsParser {

    public static MoveDirection[] parse(String arg[]) {

        MoveDirection dirs[] = new MoveDirection[arg.length];
        int newSize = 0;

        for (String s : arg) {
            if (s.equals("f") || s.equals("forward")) {
                dirs[newSize] = MoveDirection.Forward;
                newSize++;
            }
            else if (s.equals("b") || s.equals("backwards")) {
                dirs[newSize] = MoveDirection.Backward;
                newSize++;
            }
            else if (s.equals("r") || s.equals("right")) {
                dirs[newSize] = MoveDirection.Right;
                newSize++;
            }
            else if (s.equals("l") || s.equals("left")) {
                dirs[newSize] = MoveDirection.Left;
                newSize++;
            }

        }

        MoveDirection newDirs[] = new MoveDirection[newSize];
        for (int i = 0; i < newSize; i++)
            newDirs[i] = dirs[i];

        return newDirs;

    }
}
