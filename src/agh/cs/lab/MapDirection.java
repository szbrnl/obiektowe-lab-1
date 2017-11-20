package agh.cs.lab;

/**
 * Created by student31 on 2017-10-10.
 */
public enum MapDirection {
    North,
    South,
    West,
    East;

    public String toString() {
        switch(this) {
            case North: return "Północ";
            case South: return "Południe";
            case East: return "Wschód";
            case West: return "Zachód";

            default: return null;
        }
    }

    public MapDirection next() {
        switch(this) {
            case North: return East;
            case South: return West;
            case East: return South;
            case West: return North;

            default: return null;
        }
    }

    public MapDirection previous() {
        switch(this) {
            case North: return West;
            case South: return East;
            case East: return North;
            case West: return South;

            default: return null;
        }
    }
}
